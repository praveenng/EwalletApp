package com.unibrain.enums;

public enum PaymentModeEnum {

	manual('M', "Manual"), epayment('E', "E-Payment");

	private Character paymentModeCharacterValue;
	private String paymentModeFullValue;

	private PaymentModeEnum(Character paymentModeCharacterValue, String paymentModeFullValue) {
		this.paymentModeCharacterValue = paymentModeCharacterValue;
		this.paymentModeFullValue = paymentModeFullValue;
	}

	public Character getPaymentModeCharacterValue() {
		return paymentModeCharacterValue;
	}

	public String getPaymentModeFullValue() {
		return paymentModeFullValue;
	}
	
	public static String getPaymentModeValue(Character c){
		String modeValue = null;
		PaymentModeEnum[] values = PaymentModeEnum.values();
		for (PaymentModeEnum paymentModeEnum : values) {
			if(paymentModeEnum.paymentModeCharacterValue.equals(c)){
				modeValue = paymentModeEnum.getPaymentModeFullValue();
			}
		}
		return modeValue;
	}

}
