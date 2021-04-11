package com.cognizant.drugservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.drugservice.entity.DrugDetails;
import com.cognizant.drugservice.entity.DrugStock;
import com.cognizant.drugservice.exception.DrugNotFoundException;
import com.cognizant.drugservice.service.DrugService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

public class DrugController {

	@Autowired
	private DrugService drugService;

	@GetMapping("/searchDrugsByID/{id}")
	public DrugDetails getDrugsById(@PathVariable int id) throws DrugNotFoundException{
		log.info("Controller:Inside get Drugs by Id");
		return drugService.getDrugsById(id);
	}

	@GetMapping("/searchDrugsByName/{name}")
	public DrugDetails getDrugsByName(@PathVariable String name) throws DrugNotFoundException {
		log.info("Controller:Inside get Drugs by Name");
		return drugService.getDrugsByName(name);
	}

	@PostMapping("/getDispatchableDrugStock/{id}/{location}")
	public List<Object> getDrugStock(@PathVariable int id, @PathVariable String location) throws DrugNotFoundException{
		log.info("Controller:Inside get Drug Stock");
		return drugService.getDrugStock(id, location);
	}
	
	@GetMapping("/allstock")
	public List<DrugStock> getallStock() throws DrugNotFoundException {
		log.info("Controller:Inside get Drugs by Name");
		return drugService.getAllStock();
	}
	
	@GetMapping("/alldrugs")
	public List<DrugDetails> getallDrugs() throws DrugNotFoundException {
		log.info("Controller:Inside get Drugs by Name");
		return drugService.getAllDrugs();
	}
	//for subscription service
	@GetMapping("/stock/{id}/{location}")
    public DrugStock getDrugStockSubscribe(@PathVariable int id,@PathVariable String location) throws DrugNotFoundException {
			    
				return drugService.getDrugStockSubscribe(id, location);
	}
		
}
