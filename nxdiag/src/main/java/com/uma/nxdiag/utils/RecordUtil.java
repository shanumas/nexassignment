package com.uma.nxdiag.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.uma.nxdiag.model.DPIRecord;
import com.uma.nxdiag.model.Record;

import lombok.Data;

@Data
public class RecordUtil {

	public static Record createRecord(String[] records) {
		return new Record(getLong(records[0]), getInt(records[1]), getInt(records[2]), getBigDecimal(records[3]),
				getBigDecimal(records[4]), getBigDecimal(records[5]), getBigDecimal(records[6]), getBigDecimal(records[7]),
				getBigDecimal(records[8]), getBigDecimal(records[9]));
	}

	private static long getLong(String value) {
		Double doubleValue = Double.parseDouble(value);
		return doubleValue.longValue();
	}

	private static int getInt(String value) {
		Double doubleValue = Double.parseDouble(value);
		return doubleValue.intValue();
	}

	private static BigDecimal getBigDecimal(String value) {
		return new BigDecimal(value);
	}

//	public static Set<Integer> getClientsList(List<Record> records) {
//		Map<Integer, List<Record>> clientsMap = new HashMap<Integer, List<Record>>();
//		records.stream().forEach(record -> {
//			List<Record> existingList = clientsMap.get(record.getDevice_id());
//			if (existingList == null) {
//				clientsMap.put(record.getClient_id(), Arrays.asList(record));
//			} else {
//				existingList.add(record);
//			}
//		});
//		return clientsMap.keySet();
//	}

	public static Set<Integer> getClientRecords(int client) {
		// TODO Auto-generated method stub
		return null;
	}

}
