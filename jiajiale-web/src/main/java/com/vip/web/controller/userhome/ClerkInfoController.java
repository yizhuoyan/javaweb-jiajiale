package com.vip.web.controller.userhome;

import com.vip.ao.ClerkAo;
import com.vip.ao.ModifyPasswordAo;
import com.vip.dto.JSONResponse;
import com.vip.entity.EmpClerkEntity;
import com.vip.service.ClerkManageService;
import com.vip.vo.ClerkDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clerk")
@Validated
public class ClerkInfoController {

    @Autowired
    ClerkManageService clerkManageService;

    @GetMapping(path = "/{id}")
    public JSONResponse loadClerk(@PathVariable String id) throws Exception {
        final EmpClerkEntity clerkEntity = clerkManageService.loadClerk(id);

        return JSONResponse.ok(ClerkDetailVo.ofOnlyClerk(clerkEntity));
    }

    @PutMapping(path = "/{id}")
    public JSONResponse modifyMyInfo(@PathVariable String id, ClerkAo ao) throws Exception {

        clerkManageService.modifyClerkInfo(id,ao);

        return JSONResponse.ok();
    }
}
