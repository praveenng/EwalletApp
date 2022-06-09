package com.unibrain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_bank_master")
public class PaymentBankMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bank_id",columnDefinition="serial")  
	private Integer id;
	
	@Column(name="bank_name")
	private String bankName; 
	
	@Column(name="bank_display_name")
	private String bankDisplayName; 
	
	@Column(name="bank_logo")
	private String bankLogo; 
	
	@Column(name="bank_Post_folder")
	private String bankPostFolder; 
	
	@Column(name="bank_receive_folder")
	private String bankReceiveFolder; 
	
	@Column(name="bank_invoke_url")
	private String bankInvokeUrl; 
	
	@Column(name="bank_requery_url")
	private String bankRequeryUrl; 
	
	@Column(name="bank_implementation_type")
	private String bankImplementationtype;
	
	@Column(name="intermediate_payment_gateway_url")
	private String intermediatePaymentGatewayUrl;
	
	@Column(name = "convenience_charges")
	private Character convenienceCharges;

	
	public PaymentBankMaster() {
		super();
	}


	public PaymentBankMaster(Integer id, String bankName, String bankDisplayName, String bankLogo,
			String bankPostFolder, String bankReceiveFolder, String bankInvokeUrl, String bankRequeryUrl,
			String bankImplementationtype, String intermediatePaymentGatewayUrl, Character convenienceCharges) {
		super();
		this.id = id;
		this.bankName = bankName;
		this.bankDisplayName = bankDisplayName;
		this.bankLogo = bankLogo;
		this.bankPostFolder = bankPostFolder;
		this.bankReceiveFolder = bankReceiveFolder;
		this.bankInvokeUrl = bankInvokeUrl;
		this.bankRequeryUrl = bankRequeryUrl;
		this.bankImplementationtype = bankImplementationtype;
		this.intermediatePaymentGatewayUrl = intermediatePaymentGatewayUrl;
		this.convenienceCharges = convenienceCharges;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getBankDisplayName() {
		return bankDisplayName;
	}


	public void setBankDisplayName(String bankDisplayName) {
		this.bankDisplayName = bankDisplayName;
	}


	public String getBankLogo() {
		return bankLogo;
	}


	public void setBankLogo(String bankLogo) {
		this.bankLogo = bankLogo;
	}


	public String getBankPostFolder() {
		return bankPostFolder;
	}


	public void setBankPostFolder(String bankPostFolder) {
		this.bankPostFolder = bankPostFolder;
	}


	public String getBankReceiveFolder() {
		return bankReceiveFolder;
	}


	public void setBankReceiveFolder(String bankReceiveFolder) {
		this.bankReceiveFolder = bankReceiveFolder;
	}


	public String getBankInvokeUrl() {
		return bankInvokeUrl;
	}


	public void setBankInvokeUrl(String bankInvokeUrl) {
		this.bankInvokeUrl = bankInvokeUrl;
	}


	public String getBankRequeryUrl() {
		return bankRequeryUrl;
	}


	public void setBankRequeryUrl(String bankRequeryUrl) {
		this.bankRequeryUrl = bankRequeryUrl;
	}


	public String getBankImplementationtype() {
		return bankImplementationtype;
	}


	public void setBankImplementationtype(String bankImplementationtype) {
		this.bankImplementationtype = bankImplementationtype;
	}


	public String getIntermediatePaymentGatewayUrl() {
		return intermediatePaymentGatewayUrl;
	}


	public void setIntermediatePaymentGatewayUrl(String intermediatePaymentGatewayUrl) {
		this.intermediatePaymentGatewayUrl = intermediatePaymentGatewayUrl;
	}


	public Character getConvenienceCharges() {
		return convenienceCharges;
	}


	public void setConvenienceCharges(Character convenienceCharges) {
		this.convenienceCharges = convenienceCharges;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankDisplayName == null) ? 0 : bankDisplayName.hashCode());
		result = prime * result + ((bankImplementationtype == null) ? 0 : bankImplementationtype.hashCode());
		result = prime * result + ((bankInvokeUrl == null) ? 0 : bankInvokeUrl.hashCode());
		result = prime * result + ((bankLogo == null) ? 0 : bankLogo.hashCode());
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((bankPostFolder == null) ? 0 : bankPostFolder.hashCode());
		result = prime * result + ((bankReceiveFolder == null) ? 0 : bankReceiveFolder.hashCode());
		result = prime * result + ((bankRequeryUrl == null) ? 0 : bankRequeryUrl.hashCode());
		result = prime * result + ((convenienceCharges == null) ? 0 : convenienceCharges.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((intermediatePaymentGatewayUrl == null) ? 0 : intermediatePaymentGatewayUrl.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentBankMaster other = (PaymentBankMaster) obj;
		if (bankDisplayName == null) {
			if (other.bankDisplayName != null)
				return false;
		} else if (!bankDisplayName.equals(other.bankDisplayName))
			return false;
		if (bankImplementationtype == null) {
			if (other.bankImplementationtype != null)
				return false;
		} else if (!bankImplementationtype.equals(other.bankImplementationtype))
			return false;
		if (bankInvokeUrl == null) {
			if (other.bankInvokeUrl != null)
				return false;
		} else if (!bankInvokeUrl.equals(other.bankInvokeUrl))
			return false;
		if (bankLogo == null) {
			if (other.bankLogo != null)
				return false;
		} else if (!bankLogo.equals(other.bankLogo))
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (bankPostFolder == null) {
			if (other.bankPostFolder != null)
				return false;
		} else if (!bankPostFolder.equals(other.bankPostFolder))
			return false;
		if (bankReceiveFolder == null) {
			if (other.bankReceiveFolder != null)
				return false;
		} else if (!bankReceiveFolder.equals(other.bankReceiveFolder))
			return false;
		if (bankRequeryUrl == null) {
			if (other.bankRequeryUrl != null)
				return false;
		} else if (!bankRequeryUrl.equals(other.bankRequeryUrl))
			return false;
		if (convenienceCharges == null) {
			if (other.convenienceCharges != null)
				return false;
		} else if (!convenienceCharges.equals(other.convenienceCharges))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (intermediatePaymentGatewayUrl == null) {
			if (other.intermediatePaymentGatewayUrl != null)
				return false;
		} else if (!intermediatePaymentGatewayUrl.equals(other.intermediatePaymentGatewayUrl))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "PaymentBankMaster [id=" + id + ", bankName=" + bankName + ", bankDisplayName=" + bankDisplayName
				+ ", bankLogo=" + bankLogo + ", bankPostFolder=" + bankPostFolder + ", bankReceiveFolder="
				+ bankReceiveFolder + ", bankInvokeUrl=" + bankInvokeUrl + ", bankRequeryUrl=" + bankRequeryUrl
				+ ", bankImplementationtype=" + bankImplementationtype + ", intermediatePaymentGatewayUrl="
				+ intermediatePaymentGatewayUrl + ", convenienceCharges=" + convenienceCharges + "]";
	}


}
