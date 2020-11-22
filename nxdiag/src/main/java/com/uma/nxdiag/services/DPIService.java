package com.uma.nxdiag.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.uma.nxdiag.model.DPIRecord;
import com.uma.nxdiag.model.Record;
import com.uma.nxdiag.utils.DPIRecordsGenerator;
import com.uma.nxdiag.utils.RecordUtil;

@Service
public class DPIService {
	
	//This may be cached if needed
	private static List<DPIRecord> dpiRecords = null;
	
	static {
		DPIRecordsGenerator calculator = new DPIRecordsGenerator(getRaw());
		dpiRecords = calculator.getDPIList();
	}
	
	public static List<DPIRecord> getrecords() {
		return dpiRecords;
	}

	private static List<Record> getRaw() {
		
		List<Record> records = new ArrayList<Record>();

		File excelFile = new File("src/main/resources/static/device_performance_index.xlsx");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(excelFile);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rowIt = sheet.iterator();
		
		//Ignore first row, which contains the header texts
		rowIt.next();
		
		while (rowIt.hasNext()) {
			Row row = rowIt.next();

			// iterate on cells for the current row
			Iterator<Cell> cellIterator = row.cellIterator();
			String[] rowData = new String[10];
			int count=0;

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String value = cell.toString();
				rowData[count++]=value;
			}
			records.add(RecordUtil.createRecord(rowData));
			
		}

		try {
			workbook.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return records;

	}

}
