package com.uma.nxdiag.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Record implements Serializable {
	
	private int device_id;
	private int client_id;
	private int office_id;
	private BigDecimal bsod_count;
	private BigDecimal hard_reset_count;
	private BigDecimal boot_speed;
	private BigDecimal logon_duration;
	private BigDecimal cpu_usage;
	private BigDecimal memory_usage;
	private BigDecimal system_free_space;

	public Record(int device_id, int client_id, int office_id, BigDecimal bsod_count, BigDecimal hard_reset_count, BigDecimal boot_speed,
			BigDecimal logon_duration, BigDecimal cpu_usage, BigDecimal memory_usage, BigDecimal system_free_space) {
		this.device_id = device_id;
		this.client_id = client_id;
		this.office_id = office_id;
		this.bsod_count = bsod_count;
		this.hard_reset_count = hard_reset_count;
		this.boot_speed = boot_speed;
		this.logon_duration = logon_duration;
		this.cpu_usage = cpu_usage;
		this.memory_usage = memory_usage;
		this.system_free_space = system_free_space;
	}

	
	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
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

	public BigDecimal getBsod_count() {
		return bsod_count;
	}

	public void setBsod_count(BigDecimal bsod_count) {
		this.bsod_count = bsod_count;
	}

	public BigDecimal getHard_reset_count() {
		return hard_reset_count;
	}

	public void setHard_reset_count(BigDecimal hard_reset_count) {
		this.hard_reset_count = hard_reset_count;
	}

	public BigDecimal getBoot_speed() {
		return boot_speed;
	}
	public void setBoot_speed(BigDecimal boot_speed) {
		this.boot_speed = boot_speed;
	}

	public BigDecimal getLogon_duration() {
		return logon_duration;
	}

	public void setLogon_duration(BigDecimal logon_duration) {
		this.logon_duration = logon_duration;
	}

	public BigDecimal getCpu_usage() {
		return cpu_usage;
	}

	public void setCpu_usage(BigDecimal cpu_usage) {
		this.cpu_usage = cpu_usage;
	}

	public BigDecimal getmemory_usage() {
		return memory_usage;
	}

	public void setmemory_usage(BigDecimal memory_usage) {
		this.memory_usage = memory_usage;
	}

	public BigDecimal getSystem_free_space() {
		return system_free_space;
	}

	public void setSystem_free_space(BigDecimal system_free_space) {
		this.system_free_space = system_free_space;
	}
}
