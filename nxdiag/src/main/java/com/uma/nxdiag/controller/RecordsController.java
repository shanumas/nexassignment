package com.uma.nxdiag.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.uma.nxdiag.model.DPIRecord;
import com.uma.nxdiag.services.DPIService;

@RestController
public class RecordsController {

	@Autowired
	private DPIService dpiService;

	@GetMapping("/records/all")
	public List<DPIRecord> getAll() {
		return dpiService.getrecords();
	}

	// Sample http://localhost:8080/records/all
	@GetMapping("/records/client/{id}")
	public List<DPIRecord> getClientRecords(@PathVariable int id) {
		return dpiService.getrecords().stream().filter(record -> record.getClient_id() == id)
				.collect(Collectors.toList());
	}

	// Sample http://localhost:8080/records/office/88
	@GetMapping("/records/office/{id}")
	public List<DPIRecord> getOfficeRecords(@PathVariable int id) {
		return dpiService.getrecords().stream().filter(record -> record.getOffice_id() == id)
				.collect(Collectors.toList());
	}

	// Sample http://localhost:8080/records/device/1800001
	@GetMapping("/records/device/{id}")
	public List<DPIRecord> getDeviceRecords(@PathVariable int id) {
		return dpiService.getrecords().stream().filter(record -> record.getDevice_id() == id)
				.collect(Collectors.toList());
	}

	// Sample http://localhost:8080/records/dpi/lessthan/5.9
	@GetMapping("/records/dpi/lessthan/{value}")
	public List<DPIRecord> getDPILessThan(@PathVariable double value) {
		return dpiService.getrecords().stream().filter(record -> record.getDpi().doubleValue() < value)
				.collect(Collectors.toList());
	}

	// Sample http://localhost:8080/records/dpi/greaterthan/9.5
	@GetMapping("/records/dpi/greaterthan/{value}")
	public List<DPIRecord> getDpiGreaterThan(@PathVariable double value) {
		return dpiService.getrecords().stream().filter(record -> record.getDpi().doubleValue() > value)
				.collect(Collectors.toList());
	}

	// Sample http://localhost:8080/records/dpi/greaterthan/9.5
	@GetMapping("/records/dpi/range/{value}")
	public List<DPIRecord> getDpiRange(@PathVariable double value) {
		return dpiService.getrecords().stream().filter(record -> record.getDpi().doubleValue() > value)
				.collect(Collectors.toList());
	}

}
