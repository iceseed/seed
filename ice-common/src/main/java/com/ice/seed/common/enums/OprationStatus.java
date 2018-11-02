package com.ice.seed.common.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统通用状态码枚举
 * @since : 2016年11月9日
 * @author : iceSeed
 * @version : v0.0.1
 */
public enum OprationStatus {

	NORMAL(0, "正常"),
	LOGIN(10001, "请登录"),
	OVERDUE(10002, "令牌过期"),
	ERROR(10003, "系统异常"),
	AGAIN(200, "重新绑定用户信息");

	/**
	 * code消息码
	 */
	@Setter
    @Getter
	private int code;

	/**
	 * 返回消息
	 */
    @Setter
    @Getter
	private String msg;

	private OprationStatus(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}


}
