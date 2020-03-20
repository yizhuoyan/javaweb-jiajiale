package com.vip.web.controller.userhome;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.vip.ao.AvatarAo;
import com.vip.dto.AccountContext;
import com.vip.dto.AvatarDto;
import com.vip.dto.JSONResponse;
import com.vip.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@RestController
public class AvatarController  {
    @Value("${account.avatar.saveDir}")
    private String avatarSaveDir;
    @Autowired
    UserAccountService accountService;

    @GetMapping("/api/avatar")
    public void show(HttpSession session, HttpServletResponse resp)throws Exception{
        AccountContext ac= (AccountContext) session.getAttribute(AccountContext.class.getName());
        String id=ac.getId();
        AvatarDto avatarDto=accountService.loadAvatar(id);
        //是否存放在本地服务器
        if(avatarDto.isLocalSave()){
            //用户头像存放路径
            resp.setContentType(avatarDto.getContentType());
            File avatar=new File(avatarDto.getSaveLocation());

            try(FileInputStream in=new FileInputStream(avatar);
                ServletOutputStream out=resp.getOutputStream();
            ){
                byte[] bytes=new byte[1024];
                int readLength=0;
                while((readLength=in.read(bytes))!=-1){
                    out.write(bytes,0,readLength);
                }
            }
        }else{
            //存放在其他服务器，直接重定向
            resp.sendRedirect(avatarDto.getSaveLocation());
        }
    }


    @PostMapping("/api/avatar")
    public JSONResponse upload(@RequestParam("avatar") Part avatarPart
                         , HttpSession session)throws Exception{
        //获取当前登录用户
        AccountContext ac= (AccountContext) session.getAttribute(AccountContext.class.getName());
        //获取头像文件类型
        String avatarContentType=avatarPart.getContentType();

        //把帐号id为文件名
        String saveLocation=avatarSaveDir;
        if(saveLocation.endsWith("/")||saveLocation.endsWith("\\")){
            saveLocation+=ac.getId();
        }else{
            saveLocation+="/"+ac.getId();
        }
        //保存文件(如果文件存在，则覆盖)
        avatarPart.write(saveLocation);
        //更新帐号
        AvatarAo ao=new AvatarAo();
        ao.setContentType(avatarContentType);
        ao.setSaveLocation(saveLocation);
        ao.setTotalBytes(avatarPart.getSize());
        ao.setLocalSave(true);
        accountService.changeAvatar(ac.getId(),ao);
        return JSONResponse.ok();
    }


    @PutMapping(path = "/api/account/avatar")
    public JSONResponse setRecommendAvatar(String avatar
            , HttpSession session)throws Exception{
        //获取当前登录用户
        AccountContext ac= (AccountContext) session.getAttribute(AccountContext.class.getName());
        //更新帐号
        AvatarAo ao=new AvatarAo();
        ao.setContentType("image/jpeg");
        ao.setSaveLocation(avatar);
        ao.setTotalBytes(0);
        ao.setLocalSave(false);
        accountService.changeAvatar(ac.getId(),ao);
        return JSONResponse.ok();
    }


}
