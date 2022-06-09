package com.unibrain.enums;

public enum PaymentGatewayBankEnum {

	razorpay(1, "RAZORPAY");
	
	private Integer gateWayId;
	private String gateWayDescription;

	private PaymentGatewayBankEnum(Integer gateWayId, String gateWayDescription) {
		this.gateWayId = gateWayId;
		this.gateWayDescription = gateWayDescription;
	}

	public Integer getGateWayId() {
		return gateWayId;
	}

	public void setGateWayId(Integer gateWayId) {
		this.gateWayId = gateWayId;
	}

	public String getGateWayDescription() {
		return gateWayDescription;
	}

	public void setGateWayDescription(String gateWayDescription) {
		this.gateWayDescription = gateWayDescription;
	}

	public static int getGateWayId(String name) {
		int statusValue = 0;
		PaymentGatewayBankEnum[] values = PaymentGatewayBankEnum.values();
		for (PaymentGatewayBankEnum value : values) {
			if (value.getGateWayDescription().equals(name)) {
				statusValue = value.getGateWayId();
			}

		}
		return statusValue;
	}
}
