package com.cognizant.drugservice.service;


import java.util.List;


import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cognizant.drugservice.entity.DrugDetails;
import com.cognizant.drugservice.entity.DrugStock;
import com.cognizant.drugservice.exception.DrugNotFoundException;
import com.cognizant.drugservice.repository.DrugRepository;
import com.cognizant.drugservice.repository.DrugStockRepository;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DrugService {

	@Autowired
	private DrugRepository repo;

	@Autowired
	private DrugStockRepository stockRepo;

	@Transactional
	public List<Object> getDrugStock(int id, String location) throws DrugNotFoundException {
		log.info("Service:Inside get Drugs by id and location");
		try {
			List<Object> drug = stockRepo.findByDrugidAndLocationIgnoreCase(id, location);
			if (drug.isEmpty())
				throw new DrugNotFoundException();
		} catch (Exception e) {
			throw new DrugNotFoundException();
		}
		return stockRepo.findByDrugidAndLocationIgnoreCase(id, location);
	}
	
	@Transactional
	public DrugDetails getDrugsByName(String name) throws DrugNotFoundException {
		log.info("Service:Inside get Drugs by Name");
		try {
			DrugDetails drug = repo.findByNameIgnoreCase(name);
			if (drug == null)
				throw new DrugNotFoundException();
		} catch (Exception e) {
			throw new DrugNotFoundException();
		}
		return repo.findByNameIgnoreCase(name);
	}

	@Transactional
	public DrugDetails getDrugsById(int id) throws DrugNotFoundException{
		log.info("Service:Inside get Drugs by Id");
		if (repo.findById(id).isPresent())
		{
			return repo.findById(id).get();
		}
		else
		{
			throw new DrugNotFoundException();
		}
				
		
	}

	// To get both available and not available stock
	@Transactional
	public List<DrugStock> getAllStock() {
		return stockRepo.findAll();
	}

	@Transactional
	public List<DrugDetails> getAllDrugs() {
		return repo.findAll();
	}
	
	// for Subscription service
	@Transactional
	public DrugStock getDrugStockSubscribe(int id, String location) throws DrugNotFoundException{

		log.info("Service:Inside get Drug Stock");
		List<DrugStock> stock = stockRepo.findAllBydrugid(id);
		for (DrugStock s : stock) {
			if (s.getLocation().equalsIgnoreCase(location)) {
				return s;
			}
		}
		
		throw new DrugNotFoundException();
	}

}
