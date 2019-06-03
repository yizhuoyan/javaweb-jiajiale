package com.vip.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vip.ao.VipAo;
import com.vip.dao.VipDetailDao;
import com.vip.dao.VipRankDao;
import com.vip.dto.AccountContext;
import com.vip.dto.QueryResult;
import com.vip.entity.VipDetailEntity;
import com.vip.exception.VipException;
import com.vip.service.VipManageService;

import lombok.NonNull;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Service
public class VipManageServiceImpl implements VipManageService {
	@Autowired
	VipDetailDao vdao;
	@Autowired
	VipRankDao rdao;

	@Autowired
	Validator validator;

	@Autowired
    MessageSource messageSource;
	@Override
	@Transactional
	public void addVip(@NonNull VipAo ao) throws Exception {

			//1验证参数
		/**手机号，可作为会员凭证*/
		String mobile=$("手机号",ao.getMobile());		
		//TODO 手机号需要验证
		/**账号名称(客户姓名)*/
		String name=$("客户姓名",ao.getName());		
		/**等级*/
		String rankId=$("客户等级",ao.getRankId());
		/**性别*/
		boolean sex="1".equals(ao.getSex());		

		/**生日(1990-01-01) 可选*/
		String birthdayStr=trim(ao.getBirthday());
		LocalDate birthday=null;
		if(birthdayStr!=null) {
			//验证满足格式
			birthday=parseLocalDate(birthdayStr, "yyyy-MM-dd");
		}

		/**QQ可选*/
		String qq=trim(ao.getQq());		

		/**邮箱(可选)*/
		String email=trim(ao.getEmail());		

		/**地址(可选)*/
		String address=trim(ao.getAddress());		

		/**邮编(可选)*/
		String zipCode=trim(ao.getZipCode());		
		/**备注(可选)*/
		String remark=trim(ao.getRemark());		
		
		//2业务逻辑
			//2.1 手机号作为账号,不能已存在
			if(vdao.exists("mobile", mobile)) {
				throw new VipException("mobile-exists","手机号已存在");
			}
			//2.2 客户等级ID要有效
			if(!rdao.exists("id", rankId)) {
				throw new VipException("rankid-illegal","等级id无效");
			}
			VipDetailEntity e=new VipDetailEntity();
			e.setId(uuid());
			e.setName(name);
			e.setTotalDiscountMoney(0);
			e.setTotalConsumeMoney(0);
			e.setLastConsumeTime(null);
			//账号使用手机号
			e.setMobile(mobile);
			e.setAccount(mobile);
			e.setSex(sex);
			e.setBirthday(birthday);
			e.setQq(qq);
			e.setEmail(email);
			e.setAddress(address);
			e.setZipCode(zipCode);
			e.setRemark(remark);
			
			e.setRankId(rankId);
			e.setCreateUserId(AccountContext.currentAccountId());
			e.setCreateTime(LocalDateTime.now());
			vdao.insert(e);
	}

	@Override
	public VipDetailEntity queryVipByMobile(String mobile) throws Exception {
		//1 验证参数
		mobile=$("客户手机号",mobile);
		//2 业务逻辑
		//2.1 TODO:降低数据库压力，如果mobile符合手机号特征，则不进行数据库查找。
		//2.2 执行业务逻辑
			VipDetailEntity vip=vdao.select("mobile", mobile);
			return vip;
	}
	
	@Override
	public VipDetailEntity checkVipById(String id) throws Exception {
		//1 验证参数
				id=$("id",id);
				//2 业务逻辑
				//2.1 TODO:降低数据库压力，如果mobile符合手机号特征，则不进行数据库查找。
				//2.2 执行业务逻辑
					VipDetailEntity vip=vdao.select("id",id);
					return vip;
	}

	@Override
	public QueryResult<VipDetailEntity> queryVipByKey(String key, int pageNo, int pageSize) throws Exception {
		// 1 验证参数(参数清理)
		key = trim(key);
		if (key != null) {
			key = escapeForSqlLike(key);
			key="%"+key+"%";
		}
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize <= 0) {
			pageSize = 5;
		}
		// 2 执行业务逻辑
			PageHelper.startPage(pageNo, pageSize);
			
			PageInfo<VipDetailEntity> page=new PageInfo<>(vdao.selectByKey(key));
			// 3 组装结果
			QueryResult<VipDetailEntity> result=new QueryResult<VipDetailEntity>();
			result.setPageNo(pageNo);
			result.setRows(page.getList());
			result.setTotalRows(page.getTotal());
			result.setPageSize(pageSize);
			return result;
	}

	@Override
	@Transactional
	public void modifyVip(String id, VipAo ao) throws Exception {
		System.out.println(ao);
	}

}
