package com.uma.nxdiag.utils;

import java.math.BigDecimal;

import com.uma.nxdiag.model.Record;

import lombok.Data;

@Data
public class RecordUtil {

	public static Record createRecord(String[] records) {
		return new Record(getInt(records[0]), getInt(records[1]), getInt(records[2]), getBigDecimal(records[3]),
				getBigDecimal(records[4]), getBigDecimal(records[5]), getBigDecimal(records[6]), getBigDecimal(records[7]),
				getBigDecimal(records[8]), getBigDecimal(records[9]));
	}

	private static int getInt(String value) {
		Double doubleValue = Double.parseDouble(value);
		return doubleValue.intValue();
	}

	private static BigDecimal getBigDecimal(String value) {
		return new BigDecimal(value);
	}

}
