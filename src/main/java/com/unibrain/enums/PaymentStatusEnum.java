package com.unibrain.enums;

/**
 * @author Shashikanth
 *
 */
public enum PaymentStatusEnum {
	fail(0, "Failure"),sucess(1, "Success"),
	initiated(2,"Initiated"),pending(3,"Pending"),
	challan_generated(4,"Challan Generated"),late(5,"Late"),
	less(6,"Less"),additional(7,"Additional"),duplicate(8,"Duplicate"),epaymentLess(9,"Sucess");

	private Integer statusValue;
	private String statusType;

	private PaymentStatusEnum(Integer statusValue, String statusType) {
		this.statusValue = statusValue;
		this.statusType = statusType;
	}

	public Integer getStatusValue() {
		return statusValue;
	}

	public String getStatusType() {
		return statusType;
	}
	
	public static int getStatus(String name) {
		int statusValue = 0;
		PaymentStatusEnum[] values = PaymentStatusEnum.values();
		for(PaymentStatusEnum value : values) {
			if(value.getStatusType().equalsIgnoreCase(name)) {
				statusValue= value.getStatusValue();
			}
			
		}
		return statusValue;
	}
	
	public static String getPaymentStatus(Integer i){
		String status = null;
		PaymentStatusEnum[] values = PaymentStatusEnum.values();
		for (PaymentStatusEnum paymentStatusEnum : values) {
			if(paymentStatusEnum.getStatusValue().equals(i)){
				status = paymentStatusEnum.getStatusType();
			}
		}
		return status;
	}
	

	 
}
