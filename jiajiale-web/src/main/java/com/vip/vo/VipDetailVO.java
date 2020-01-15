package com.vip.vo;

import com.vip.entity.VipDetailEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class VipDetailVO {
    private String id;
    /**vip账号(目前客户手机号)*/
    private String no;

    /**手机号，可作为会员凭证*/
    private String mobile;

    /**账号名称(客户姓名)*/
    private String name;

    /**性别*/
    private Integer sex;

    /**生日*/
    private LocalDate birthday;

    /***/
    private String qq;

    /***/
    private String email;

    /***/
    private String address;

    /**邮编*/
    private String zipCode;

    /**等级*/
    private String rankName;

    private Integer rankId;

    /**总优惠金额*/
    private Integer totalDiscountMoney;

    /**目前消费总金额，单位分元*/
    private Integer totalConsumeMoney;

    /***/
    private String remark;

    /**创建人*/
    private String createUserName;
    private String createUserId;

    /**创建时间*/
    private LocalDateTime createTime;
    /**
     * 最后消费时间
     */
    private LocalDateTime lastConsumeTime;

    public static VipDetailVO of(VipDetailEntity e) {
        if(e==null)return null;
        VipDetailVO vo = new VipDetailVO();
        vo.setName(e.getName());
        vo.setAddress(e.getAddress());
        vo.setTotalDiscountMoney(e.getTotalDiscountMoney());
        vo.setTotalConsumeMoney(e.getTotalConsumeMoney());
        if(e.getCreateUser()==null) {
            vo.setCreateUserName(e.getCreateUser().getName());
            vo.setCreateUserId(e.getCreateUserId());
        }
        vo.setLastConsumeTime(e.getLastConsumeTime());
        vo.setId(e.getId());
        vo.setNo(e.getNo());
        vo.setMobile(e.getMobile());
        vo.setSex(e.getSex()?1:0);
        vo.setBirthday(e.getBirthday());
        vo.setQq(e.getQq());
        vo.setEmail(e.getEmail());
        vo.setZipCode(e.getZipCode());
        if(e.getRank()!=null) {
            vo.setRankName(e.getRank().getName());
            vo.setRankId(e.getRank().getId());
        }
        vo.setRemark(e.getRemark());
        vo.setCreateTime(e.getCreateTime());
        return vo;
    }
}
