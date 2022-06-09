package com.unibrain.enums;

public enum ApplicationLogEnum {

	user_registration(1, "Ewallet User Registration"), user_login(2, "Ewallet User Login"), user_login_failure(3,
			"User Login Failure"), user_bank_file_upload(4, "User Bank File Upload");

	private int applicationLogValue;
	private String applicationLogName;

	private ApplicationLogEnum(int applicationLogValue, String applicationLogName) {
		this.applicationLogValue = applicationLogValue;
		this.applicationLogName = applicationLogName;
	}

	public int getApplicationLogValue() {
		return applicationLogValue;
	}

	public String getApplicationLogName() {
		return applicationLogName;
	}
}
