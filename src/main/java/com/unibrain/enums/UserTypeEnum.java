package com.unibrain.enums;

public enum UserTypeEnum {
	ewallet_admin(1, "Ewallet Admin"), ewallet_user(2, "Ewallet User");

	private int userTypeValue;
	private String userTypeName;

	private UserTypeEnum(int userTypeValue, String userTypeName) {
		this.userTypeValue = userTypeValue;
		this.userTypeName = userTypeName;
	}

	public int getUserTypeValue() {
		return userTypeValue;
	}

	public String getUserTypeName() {
		return userTypeName;
	}
	
	public static String getUserTypeFulllName(int val){
		String userTypeName = null;
		UserTypeEnum[] values = UserTypeEnum.values();
		for (UserTypeEnum userTypeEnum : values) {
			if(userTypeEnum.getUserTypeValue() == val){
				userTypeName = userTypeEnum.getUserTypeName();
			}
		}
		return userTypeName;
	}
}
