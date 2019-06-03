package com.vip.ao;

import lombok.Data;

@Data
public class ModifyPasswordAo {
	private String oldPassword;
	private String newPassword;
	private String newPasswordConfirm;
}
