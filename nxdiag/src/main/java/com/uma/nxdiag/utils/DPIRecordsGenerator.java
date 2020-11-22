package com.uma.nxdiag.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.uma.nxdiag.model.DPIRecord;
import com.uma.nxdiag.model.Record;

public class DPIRecordsGenerator {

	private List<Record> allRecords;
	private static BigDecimal min_bsod_count = BigDecimal.ZERO;
	private static BigDecimal max_bsod_count = BigDecimal.ZERO;
	private static BigDecimal min_hard_reset_count = BigDecimal.ZERO;
	private static BigDecimal max_hard_reset_count = BigDecimal.ZERO;
	private static BigDecimal min_boot_speed = BigDecimal.ZERO;
	private static BigDecimal max_boot_speed = BigDecimal.ZERO;
	private static BigDecimal min_logon_duration = BigDecimal.ZERO;
	private static BigDecimal max_logon_duration = BigDecimal.ZERO;
	private static BigDecimal min_cpu_usage = BigDecimal.ZERO;
	private static BigDecimal max_cpu_usage = BigDecimal.ZERO;
	private static BigDecimal min_memory_usage = BigDecimal.ZERO;
	private static BigDecimal max_memory_usage = BigDecimal.ZERO;
	private static BigDecimal min_system_free_space = BigDecimal.ZERO;
	private static BigDecimal max_system_free_space = BigDecimal.ZERO;

	public DPIRecordsGenerator(List<Record> records) {
		this.allRecords = records;
		for (Record record : records) {

			// bsod_count
			BigDecimal current_bsod_count = record.getBsod_count();
			if (current_bsod_count.compareTo(max_bsod_count) > 0) {
				max_bsod_count = current_bsod_count;
			}
			// Set the maximum value as minimum to start with
			min_bsod_count = max_bsod_count;
			if (current_bsod_count.compareTo(min_bsod_count) < 0) {
				min_bsod_count = current_bsod_count;
			}

			// hard_reset_count
			BigDecimal current_hard_reset_count = record.getHard_reset_count();
			if (current_hard_reset_count.compareTo(max_hard_reset_count) > 0) {
				max_hard_reset_count = current_hard_reset_count;
			}
			// Set the maximum value as minimum to start with
			min_hard_reset_count = max_hard_reset_count;
			if (current_hard_reset_count.compareTo(min_hard_reset_count) < 0) {
				min_hard_reset_count = current_hard_reset_count;
			}

			// boot_speed
			BigDecimal current_boot_speed = record.getBoot_speed();
			if (current_boot_speed.compareTo(max_boot_speed) > 0) {
				max_boot_speed = current_boot_speed;
			}
			// Set the maximum value as minimum to start with
			min_boot_speed = max_boot_speed;
			if (current_boot_speed.compareTo(min_boot_speed) < 0) {
				min_boot_speed = current_boot_speed;
			}

			// logon_duration
			BigDecimal current_logon_duration = record.getLogon_duration();
			if (current_logon_duration.compareTo(max_logon_duration) > 0) {
				max_logon_duration = current_logon_duration;
			}
			// Set the maximum value as minimum to start with
			min_logon_duration = max_logon_duration;
			if (current_logon_duration.compareTo(min_logon_duration) < 0) {
				min_logon_duration = current_logon_duration;
			}

			// cpu_usage
			BigDecimal current_cpu_usage = record.getCpu_usage();
			if (current_cpu_usage.compareTo(max_cpu_usage) > 0) {
				max_cpu_usage = current_cpu_usage;
			}
			// Set the maximum value as minimum to start with
			min_cpu_usage = max_cpu_usage;
			if (current_cpu_usage.compareTo(min_cpu_usage) < 0) {
				min_cpu_usage = current_cpu_usage;
			}

			// memory_usage
			BigDecimal current_memory_usage = record.getmemory_usage();
			if (current_memory_usage.compareTo(max_memory_usage) > 0) {
				max_memory_usage = current_memory_usage;
			}
			// Set the maximum value as minimum to start with
			min_memory_usage = max_memory_usage;
			if (current_memory_usage.compareTo(min_memory_usage) < 0) {
				min_memory_usage = current_memory_usage;
			}

			// system_free_space
			BigDecimal current_system_free_space = record.getSystem_free_space();
			if (current_system_free_space.compareTo(max_system_free_space) > 0) {
				max_system_free_space = current_system_free_space;
			}
			// Set the maximum value as minimum to start with
			min_system_free_space = max_system_free_space;
			if (current_system_free_space.compareTo(min_system_free_space) < 0) {
				min_system_free_space = current_system_free_space;
			}

		}
	}

	public List<DPIRecord> getDPIList() {
		List<DPIRecord> dpiList = new ArrayList<DPIRecord>();

		// Calculate max values first
		for (Record record : allRecords) {

			// bsod_count
			BigDecimal current_bsod_count = record.getBsod_count();
			BigDecimal normalized_bsod_count = getNormalizedValue(current_bsod_count, min_bsod_count, max_bsod_count);			
			if(normalized_bsod_count!=null)
			{
				// Flip value
				normalized_bsod_count = BigDecimal.ONE.subtract(normalized_bsod_count);
			}
			else {
				normalized_bsod_count=BigDecimal.ZERO;
			}
			

			// hard_reset_count
			BigDecimal current_hard_reset_count = record.getHard_reset_count();
			BigDecimal normalized_hard_reset_count = getNormalizedValue(current_hard_reset_count, min_hard_reset_count,
					max_hard_reset_count);
			if(normalized_hard_reset_count!=null)
			{
				normalized_hard_reset_count = BigDecimal.ONE.subtract(normalized_hard_reset_count);
			}
			else {
				normalized_hard_reset_count=BigDecimal.ZERO;
			}

			// boot_speed
			BigDecimal current_boot_speed = record.getBoot_speed();
			BigDecimal normalized_boot_speed = getNormalizedValue(current_boot_speed, min_boot_speed, max_boot_speed);
			if(normalized_boot_speed!=null)
			{
				normalized_boot_speed = BigDecimal.ONE.subtract(normalized_boot_speed);
			}
			else {
				normalized_boot_speed=BigDecimal.ZERO;
			}

			// logon_duration
			BigDecimal current_logon_duration = record.getLogon_duration();
			BigDecimal normalized_logon_duration = getNormalizedValue(current_logon_duration, min_logon_duration,
					max_logon_duration);
			if(normalized_logon_duration!=null)
			{
				normalized_logon_duration = BigDecimal.ONE.subtract(normalized_logon_duration);
			}
			else {
				normalized_logon_duration=BigDecimal.ZERO;
			}

			// cpu_usage
			BigDecimal current_cpu_usage = record.getCpu_usage();
			BigDecimal normalized_cpu_usage = getNormalizedValue(current_cpu_usage, min_cpu_usage, max_cpu_usage);
			if(normalized_cpu_usage!=null)
			{
				normalized_cpu_usage = BigDecimal.ONE.subtract(normalized_cpu_usage);
			}
			else {
				normalized_cpu_usage=BigDecimal.ZERO;
			}

			// memory_usage
			BigDecimal current_memory_usage = record.getmemory_usage();
			BigDecimal normalized_memory_usage = getNormalizedValue(current_memory_usage, min_memory_usage,
					max_memory_usage);
			if(normalized_memory_usage!=null)
			{
				normalized_memory_usage = BigDecimal.ONE.subtract(normalized_memory_usage);
			}
			else {
				normalized_memory_usage=BigDecimal.ZERO;
			}

			// system_free_space
			BigDecimal current_system_free_space = record.getSystem_free_space();
			BigDecimal normalized_system_free_space = getNormalizedValue(current_system_free_space,
					min_system_free_space, max_system_free_space);
			if(normalized_system_free_space==null)
			{
				normalized_system_free_space=BigDecimal.ZERO;
			}

			// Addition of all nomalized valued
			BigDecimal addition_of_normalized = normalized_bsod_count.add(normalized_hard_reset_count)
					.add(normalized_boot_speed).add(normalized_logon_duration).add(normalized_cpu_usage)
					.add(normalized_memory_usage).add(normalized_system_free_space);

			BigDecimal dpi = BigDecimal.TEN.multiply(addition_of_normalized).divide(new BigDecimal(7), 4, RoundingMode.DOWN);

			DPIRecord dpiRecord = new DPIRecord(record.getDevice_id(), record.getClient_id(), record.getOffice_id(),
					dpi);
			dpiList.add(dpiRecord);
		}
		return dpiList;

	}

	BigDecimal getNormalizedValue(BigDecimal currentValue, BigDecimal minValue, BigDecimal maxValue) {
		BigDecimal divisor = maxValue.subtract(minValue);
		if (!BigDecimal.ZERO.equals(divisor)) {
//			System.out.println("one:" + currentValue.subtract(minValue));
//			System.out.println("two:" + (currentValue.subtract(minValue)).divide(divisor, 4, RoundingMode.DOWN));
//			System.out.println("three:"
//					+ BigDecimal.ZERO.max((currentValue.subtract(minValue)).divide(divisor, 4, RoundingMode.DOWN)));
			// Set precision to 3 decimals for division
			return BigDecimal.ONE
					.min(BigDecimal.ZERO.max((currentValue.subtract(minValue)).divide(divisor, 4, RoundingMode.DOWN)));
		} else {
			return null;
		}
	}

}
