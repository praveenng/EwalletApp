package com.unibrain.enums;

public enum TransactionFlagEnums {

	Deposit('D', "Deposit"),Refund('R', "Refund"),WithDrawn('W',"WithDrawn"),
	Payment_to_business_transaction('B',"Payment to Business Transaction");
	
	
	private Character flag;
	private String flagName;

	private TransactionFlagEnums(Character flag, String flagName) {
		this.flag = flag;
		this.flagName = flagName;
	}
	
	public Character getFlag() {
		return flag;
	}

	public String getFlagName() {
		return flagName;
	}



	public static Character getFlag(String flagName) {
		Character flag = null;
		TransactionFlagEnums[] values = TransactionFlagEnums.values();
		for(TransactionFlagEnums value : values) {
			if(value.getFlagName().equalsIgnoreCase(flagName)) {
				flag= value.getFlag();
			}
			
		}
		return flag;
	}
	
	public static String getFlagName(Character flag){
		String flagValue = null;
		TransactionFlagEnums[] values = TransactionFlagEnums.values();
		for (TransactionFlagEnums transactionStatusEnum : values) {
			if(transactionStatusEnum.getFlag().equals(flag)){
				flagValue = transactionStatusEnum.getFlagName();
			}
		}
		return flagValue;
	}
	
}
