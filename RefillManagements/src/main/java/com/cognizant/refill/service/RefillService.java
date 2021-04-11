package com.cognizant.refill.service;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognizant.refill.entity.RefillOrder;
import com.cognizant.refill.exception.RefillIdNotpresentException;
import com.cognizant.refill.exception.SubscriptionNotfoundException;
import com.cognizant.refill.repository.RefillRepository;


@Service
public class RefillService {
	@Autowired
	private RefillRepository refillRepository;
	@Autowired
	private RestTemplate restTemplate;
	
	
	public RefillOrder getRefillStatusbyid(int id) throws RefillIdNotpresentException{
		if(refillRepository.findBySubscriptionId(id)==null)
			throw new RefillIdNotpresentException();
		return refillRepository.findBySubscriptionId(id);
	}

	public RefillOrder getRefillDuesAsOfDate(int refillid)throws RefillIdNotpresentException {
		try {
		   return refillRepository.findByRefillOrderId(refillid);
		}
		catch (Exception e) {
			throw new RefillIdNotpresentException();
		}
	}
	
	public List<Object> requestRefill(int policy_id,int member_id, int subscription_id, String location ) throws RefillIdNotpresentException{
		try {
		RefillOrder refill=refillRepository.findBySubscriptionId(subscription_id);
		Subscription subscription=restTemplate.getForObject("http://localhost:8082/getsubscriptionDetails/"+subscription_id, Subscription.class);
		String drugId=subscription.getDrugName();
		DrugStock stock = restTemplate.getForObject("http://localhost:8081/stock/" + drugId + "/" + location,DrugStock.class);
		if (stock.getQuantity() > 0) {
			return Arrays.asList("Refill request sent successfully",refill);
		}
		return Arrays.asList("Refill request failed");
		
	}catch (Exception e) {
		throw new RefillIdNotpresentException();
	}
	
	}
}
