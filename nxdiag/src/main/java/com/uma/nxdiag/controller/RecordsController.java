package com.uma.nxdiag.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uma.nxdiag.services.DPIService;
import com.uma.nxdiag.utils.RecordUtil;
import com.uma.nxdiag.model.DPIRecord;
import com.uma.nxdiag.model.Record;


@RestController
public class RecordsController {
	
	@GetMapping("/records/all")
	public List<DPIRecord> getAll(){
		return DPIService.getAll();		
	}
	
//	@GetMapping("/clients")
//	public Set<Integer> getClients(){
//		return RecordUtil.getClientsList(DPIService.getAll());
//	}
	
	@GetMapping("/records/client")
	public Set<Integer> getClientRecords(@RequestParam(name = "id") int client_id){
		return RecordUtil.getClientRecords(client_id);
	}

}
