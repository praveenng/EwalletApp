package com.unibrain.enums;

public enum EmailEventEnum {
	OTP_based_login(1, "OTP based login");

	private int emailEventValue;
	private String emailEventName;

	private EmailEventEnum(int emailEventValue, String emailEventName) {
		this.emailEventValue = emailEventValue;
		this.emailEventName = emailEventName;
	}

	public int getEmailEventValue() {
		return emailEventValue;
	}

	public String getEmailEventName() {
		return emailEventName;
	}
}
