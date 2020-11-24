package com.uma.nxdiag.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.uma.nxdiag.model.DPIRecord;
import com.uma.nxdiag.model.Record;

public class DPIRecordsGenerator {

	private static final double START_PERCENTILE = 0.02;
	// Assumed this as 99th percentile, which is different from the original
	// requirement (which was 98th)
	private static final double END_PERCENTILE = 0.99;

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
		List<BigDecimal> bsod_count_list = new ArrayList<BigDecimal>();
		List<BigDecimal> hard_reset_count_list = new ArrayList<BigDecimal>();
		List<BigDecimal> boot_speed_list = new ArrayList<BigDecimal>();
		List<BigDecimal> logon_duration_list = new ArrayList<BigDecimal>();
		List<BigDecimal> cpu_usage_list = new ArrayList<BigDecimal>();
		List<BigDecimal> memory_usage_list = new ArrayList<BigDecimal>();
		List<BigDecimal> system_free_space_list = new ArrayList<BigDecimal>();
		
		for (Record record : records) {

			// bsod_count
			BigDecimal current_bsod_count = record.getBsod_count();
			bsod_count_list.add(current_bsod_count);

			// hard_reset_count
			BigDecimal current_hard_reset_count = record.getHard_reset_count();
			hard_reset_count_list.add(current_hard_reset_count);

			// boot_speed
			BigDecimal current_boot_speed = record.getBoot_speed();
			boot_speed_list.add(current_boot_speed);

			// logon_duration
			BigDecimal current_logon_duration = record.getLogon_duration();
			logon_duration_list.add(current_logon_duration);

			// cpu_usage
			BigDecimal current_cpu_usage = record.getCpu_usage();
			cpu_usage_list.add(current_cpu_usage);

			// memory_usage
			BigDecimal current_memory_usage = record.getmemory_usage();
			memory_usage_list.add(current_memory_usage);

			// system_free_space
			BigDecimal current_system_free_space = record.getSystem_free_space();
			system_free_space_list.add(current_system_free_space);

		}

		min_bsod_count = getMin(bsod_count_list);
		max_bsod_count = getMax(bsod_count_list);
		
		min_hard_reset_count = getMin(hard_reset_count_list);
		max_hard_reset_count = getMax(hard_reset_count_list);
		
		min_boot_speed = getMin(boot_speed_list);
		max_boot_speed = getMax(boot_speed_list);
		
		min_logon_duration = getMin(logon_duration_list);
		max_logon_duration = getMax(logon_duration_list);
		
		min_cpu_usage = getMin(cpu_usage_list);
		max_cpu_usage = getMax(cpu_usage_list);
		
		min_memory_usage = getMin(memory_usage_list);
		max_memory_usage = getMax(memory_usage_list);
		
		min_system_free_space = getMin(system_free_space_list);
		max_system_free_space = getMax(system_free_space_list);
	}

	private BigDecimal getMin(List<BigDecimal> bsod_count_list) {
		Collections.sort(bsod_count_list);
		BigDecimal start = bsod_count_list.get(0);
		int size = bsod_count_list.size();
		System.out.println("First:" + start);
		System.out.println("Last:" + bsod_count_list.get(bsod_count_list.size() - 1));
		// locator = (n+1)X(percentileValue/100)
		double locator = (size + 1) * (START_PERCENTILE);// Minimum is second percentile
		int locatorInt = (int) locator;
		double locatorDifference = locator - locatorInt;
		BigDecimal firstValue = bsod_count_list.get(locatorInt);
		BigDecimal secondValue = bsod_count_list.get(locatorInt + 1);
		// percentile = firstLocator + (locatorDifference X locatorDistance)
		BigDecimal secondPercentile = firstValue
				.add((new BigDecimal(locatorDifference).multiply(secondValue.subtract(firstValue))));
		System.out.println("Second percentile is:" + secondPercentile);
		return secondPercentile;
	}

	private BigDecimal getMax(List<BigDecimal> bsod_count_list) {
		Collections.sort(bsod_count_list);
		BigDecimal start = bsod_count_list.get(0);
		int size = bsod_count_list.size();
		System.out.println("First:" + start);
		System.out.println("Last:" + bsod_count_list.get(bsod_count_list.size() - 1));
		// locator = (n+1)X(percentileValue/100)
		double locator = (size + 1) * (END_PERCENTILE);// Minimum is 98th percentile
		int locatorInt = (int) locator;
		double locatorDifference = locator - locatorInt;
		BigDecimal firstLocatorValue = bsod_count_list.get(locatorInt);
		BigDecimal secondLocatorValue = bsod_count_list.get(locatorInt + 1);
		// percentile = firstLocator + (locatorDifference X locatorDistance)
		BigDecimal nintyNinthPercentile = firstLocatorValue
				.add((new BigDecimal(locatorDifference).multiply(secondLocatorValue.subtract(firstLocatorValue))));
		System.out.println("Ninthninth percentile is:" + nintyNinthPercentile);
		return nintyNinthPercentile;
	}

	public List<DPIRecord> getDPIList() {
		List<DPIRecord> dpiList = new ArrayList<DPIRecord>();

		// Calculate max values first
		for (Record record : allRecords) {

			// bsod_count
			BigDecimal current_bsod_count = record.getBsod_count();
			BigDecimal normalized_bsod_count = getNormalizedValue(current_bsod_count, min_bsod_count, max_bsod_count);
			if (normalized_bsod_count != null) {
				// Flip value
				normalized_bsod_count = BigDecimal.ONE.subtract(normalized_bsod_count);
			} else {
				normalized_bsod_count = BigDecimal.ZERO;
			}

			// hard_reset_count
			BigDecimal current_hard_reset_count = record.getHard_reset_count();
			BigDecimal normalized_hard_reset_count = getNormalizedValue(current_hard_reset_count, min_hard_reset_count,
					max_hard_reset_count);
			if (normalized_hard_reset_count != null) {
				normalized_hard_reset_count = BigDecimal.ONE.subtract(normalized_hard_reset_count);
			} else {
				normalized_hard_reset_count = BigDecimal.ZERO;
			}

			// boot_speed
			BigDecimal current_boot_speed = record.getBoot_speed();
			BigDecimal normalized_boot_speed = getNormalizedValue(current_boot_speed, min_boot_speed, max_boot_speed);
			if (normalized_boot_speed != null) {
				normalized_boot_speed = BigDecimal.ONE.subtract(normalized_boot_speed);
			} else {
				normalized_boot_speed = BigDecimal.ZERO;
			}

			// logon_duration
			BigDecimal current_logon_duration = record.getLogon_duration();
			BigDecimal normalized_logon_duration = getNormalizedValue(current_logon_duration, min_logon_duration,
					max_logon_duration);
			if (normalized_logon_duration != null) {
				normalized_logon_duration = BigDecimal.ONE.subtract(normalized_logon_duration);
			} else {
				normalized_logon_duration = BigDecimal.ZERO;
			}

			// cpu_usage
			BigDecimal current_cpu_usage = record.getCpu_usage();
			BigDecimal normalized_cpu_usage = getNormalizedValue(current_cpu_usage, min_cpu_usage, max_cpu_usage);
			if (normalized_cpu_usage != null) {
				normalized_cpu_usage = BigDecimal.ONE.subtract(normalized_cpu_usage);
			} else {
				normalized_cpu_usage = BigDecimal.ZERO;
			}

			// memory_usage
			BigDecimal current_memory_usage = record.getmemory_usage();
			BigDecimal normalized_memory_usage = getNormalizedValue(current_memory_usage, min_memory_usage,
					max_memory_usage);
			if (normalized_memory_usage != null) {
				normalized_memory_usage = BigDecimal.ONE.subtract(normalized_memory_usage);
			} else {
				normalized_memory_usage = BigDecimal.ZERO;
			}

			// system_free_space
			BigDecimal current_system_free_space = record.getSystem_free_space();
			BigDecimal normalized_system_free_space = getNormalizedValue(current_system_free_space,
					min_system_free_space, max_system_free_space);
			if (normalized_system_free_space == null) {
				normalized_system_free_space = BigDecimal.ZERO;
			}

			// Addition of all nomalized valued
			BigDecimal addition_of_normalized = normalized_bsod_count.add(normalized_hard_reset_count)
					.add(normalized_boot_speed).add(normalized_logon_duration).add(normalized_cpu_usage)
					.add(normalized_memory_usage).add(normalized_system_free_space);

			BigDecimal dpi = BigDecimal.TEN.multiply(addition_of_normalized).divide(new BigDecimal(7), 4,
					RoundingMode.DOWN);

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
