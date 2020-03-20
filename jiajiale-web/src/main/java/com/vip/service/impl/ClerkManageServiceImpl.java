package com.vip.service.impl;

import com.vip.ao.ClerkAo;
import com.vip.dao.EmpClerkDao;
import com.vip.dao.SysAccountDao;
import com.vip.entity.EmpClerkEntity;
import com.vip.entity.SysAccountEntity;
import com.vip.service.ClerkManageService;
import com.yizhuoyan.common.BeanMap;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Objects;

@Service
@Slf4j
@Validated
public class ClerkManageServiceImpl implements ClerkManageService {

    @Autowired
    SysAccountDao accountDao;
    @Autowired
    EmpClerkDao clerkDao;

    @Override
    public EmpClerkEntity loadClerk(@NonNull String id) throws Exception {
        EmpClerkEntity clerk=clerkDao.selectById(id);
        assertNotNull("{1}不存在",clerk,id);
        return clerk;
    }

    @Override
    public void modifyClerkInfo(@NonNull String id,@NonNull ClerkAo ao) throws Exception {
        final EmpClerkEntity e = clerkDao.selectById(id);
        assertNotNull("{1}不存在",e,id);

        BeanMap map=new BeanMap(8);
        String newRealName=ao.getRealName();
        if(!Objects.equals(newRealName,e.getRealName())){
            map.put("real_name",newRealName);
        }

        Integer newSex=parseInt(ao.getSex());
        if(!Objects.equals(newSex,e.getSex())){
            map.put("sex",newSex);
        }

        String newWorkEmail=ao.getWorkEmail();
        if(!Objects.equals(newWorkEmail,e.getWorkEmail())){
            map.put("work_email",newWorkEmail);
        }
        String newWorkMobile=ao.getWorkMobile();
        if(!Objects.equals(newWorkMobile,e.getWorkMobile())){
            map.put("work_mobile",newWorkMobile);
        }
        LocalDate newBirthday=parseLocalDate(ao.getBirthday(),"YYYY-MM-dd");
        if(!Objects.equals(newBirthday,e.getBirthday())){
            map.put("birthday",newSex);
        }

        if(map.isEmpty()){
            return;
        }
        clerkDao.update(id,map);

    }
}
