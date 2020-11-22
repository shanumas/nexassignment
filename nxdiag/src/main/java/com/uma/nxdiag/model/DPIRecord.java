package com.uma.nxdiag.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class DPIRecord implements Serializable {
	
	private long device_id;
	private int client_id;
	private int office_id;
	private BigDecimal dpi;

	public DPIRecord(long device_id, int client_id, int office_id, BigDecimal dpi) {
		this.device_id = device_id;
		this.client_id = client_id;
		this.office_id = office_id;
		this.dpi = dpi;
	}

	public long getDevice_id() {
		return device_id;
	}

	public void setDevice_id(long device_id) {
		this.device_id = device_id;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getOffice_id() {
		return office_id;
	}

	public void setOffice_id(int office_id) {
		this.office_id = office_id;
	}
	
	public BigDecimal getDpi() {
		return dpi;
	}

	public void setDpi(BigDecimal dpi) {
		this.dpi = dpi;
	}

}
