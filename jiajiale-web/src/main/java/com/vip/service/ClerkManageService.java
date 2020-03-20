package com.vip.service;

import com.vip.ao.ClerkAo;
import com.vip.entity.EmpClerkEntity;

public interface ClerkManageService extends  CommonService{

    public EmpClerkEntity loadClerk(String id)throws Exception;
    public void modifyClerkInfo(String id, ClerkAo ao)throws Exception;
}
