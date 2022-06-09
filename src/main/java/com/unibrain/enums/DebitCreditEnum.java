package com.unibrain.enums;

public enum DebitCreditEnum {

	Debit('D', "Debit"),Credit('C', "Credit");
	
	private Character debitCreditFlag;
	private String debitCreditValue;

	private DebitCreditEnum(Character debitCreditFlag, String debitCreditValue) {
		this.debitCreditFlag = debitCreditFlag;
		this.debitCreditValue = debitCreditValue;
	}
	
	public Character getDebitCreditFlag() {
		return debitCreditFlag;
	}

	public String getDebitCreditValue() {
		return debitCreditValue;
	}

	public static Character getDebitCreditChar(String flagName) {
		Character flag = null;
		DebitCreditEnum[] values = DebitCreditEnum.values();
		for(DebitCreditEnum value : values) {
			if(value.getDebitCreditValue().equalsIgnoreCase(flagName)) {
				flag= value.getDebitCreditFlag();
			}
			
		}
		return flag;
	}
	
	public static String getDebitCreditName(Character flag){
		String flagValue = null;
		DebitCreditEnum[] values = DebitCreditEnum.values();
		for (DebitCreditEnum transactionStatusEnum : values) {
			if(transactionStatusEnum.getDebitCreditFlag().equals(flag)){
				flagValue = transactionStatusEnum.getDebitCreditValue();
			}
		}
		return flagValue;
	}
	
}
