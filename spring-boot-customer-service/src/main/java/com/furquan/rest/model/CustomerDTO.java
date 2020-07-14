package com.furquan.rest.model;

import javax.validation.constraints.NotNull;

/**
 * @author furquan
 *
 */
public class CustomerDTO {

	 @NotNull(message = "Please customer id")
	private Long custId;

	 @NotNull(message = "Contact Number is mandatory")
	private int contactNumber;
	
	private int status;
	

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerDTO [custId=" + custId + ", contactNumber=" + contactNumber + ", status=" + status + "]";
	}

	

}
