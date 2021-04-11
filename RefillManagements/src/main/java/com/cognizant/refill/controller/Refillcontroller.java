package com.cognizant.refill.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.cognizant.refill.entity.RefillOrder;
import com.cognizant.refill.exception.RefillIdNotpresentException;
import com.cognizant.refill.exception.SubscriptionNotfoundException;
import com.cognizant.refill.repository.RefillRepository;
import com.cognizant.refill.service.DrugDetails;
import com.cognizant.refill.service.DrugStock;
import com.cognizant.refill.service.RefillService;
import com.cognizant.refill.service.Subscription;

import lombok.extern.slf4j.Slf4j;


@RestController @Slf4j
public class Refillcontroller {
	@Autowired
	public RefillService refillservice;
	@Autowired
	public RefillRepository refillRepository;
	@Autowired
	public RestTemplate restTemplate;
	@GetMapping("/viewRefillStatus/{id}")
	public RefillOrder getRefillStatus(@PathVariable int id) throws RefillIdNotpresentException {
		return refillservice.getRefillStatusbyid(id);
	}
	
	@GetMapping("/getRefillDuesAsOfDate/{id}")
	public RefillOrder getRefillDuesAsOfDate(@PathVariable int id) throws  RefillIdNotpresentException {
		return refillservice.getRefillDuesAsOfDate(id);
	}
	
	@PostMapping("/requestAdhocRefill/{Member_ID}/{Subscription_ID}/{Location}")
	public String requestAdhocRefill(@PathVariable int Member_ID,@PathVariable int Subscription_ID,@PathVariable String Location) throws SubscriptionNotfoundException{
		try {
			log.info("hfdskh");
			Subscription subscription = restTemplate.getForObject("http://localhost:8093/getsubscriptionDetails/"+Subscription_ID, Subscription.class);
			log.info(subscription.toString());
			DrugDetails getDrugid = restTemplate.getForObject("http://localhost:8092/searchDrugsByName/"+subscription.getDrugName(), DrugDetails.class);
			log.info(getDrugid.toString());
			
			//DrugStock emps = restTemplate.postForObject("http://localhost:8092/getDispatchableDrugStock/"+getDrugid.getId()+"/"+Location,getDrugid,DrugStock.class);
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			DrugDetails drugdetails=new DrugDetails();
			HttpEntity<DrugDetails> entity = new HttpEntity<DrugDetails>(drugdetails, headers);
			MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
			mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
			restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
			log.info("inside");
			ResponseEntity<List<DrugStock>> responseEntity = restTemplate.exchange("http://localhost:8092/getDispatchableDrugStock/"+getDrugid.getId()+"/"+Location, HttpMethod.POST, entity,  new ParameterizedTypeReference<List<DrugStock>>() {});
			
			List<DrugStock> emps = responseEntity.getBody();
			
			log.info(emps.toString());
			if(emps!=null) return "Success";
			else return "Not available at your Location";
		}
		catch (Exception e) {
			return "Not available at your Location";
		}
		
	}
	
	//new method
	@PostMapping("/refilldatainsert")
	public void datarefillpost(@RequestBody RefillOrder refillOrder) {
		refillOrder.setRefillDate(LocalDate.now());
		refillRepository.save(refillOrder);
	}
	
	//new method
	@PostMapping("/payment/{rid}/{sid}/{amount}")
	public String refillpayment(@PathVariable int rid,@PathVariable int sid, @PathVariable int amount) throws RefillIdNotpresentException {
		try {
		RefillOrder refill=refillRepository.findByRefillOrderIdAndSubscriptionId(rid,sid);
		if(refill==null) throw new RefillIdNotpresentException();
		int payment = refill.getCost();
		if (payment>amount) {
			refill.setCost(Math.abs(amount - payment));
			refillRepository.save(refill);
			return "Balance payment is"+ (Math.abs(amount - payment));
		}
		else if (payment<=amount) {
			refill.setCost(0);
			refill.setPaymentStatus("paid");
			refillRepository.save(refill);
			return " Thanks for payment.. Get the Balance return amount is"+ Math.abs(amount - payment);
		}
		return "Payment unsucessfull";
	}catch (Exception e) {
		throw new RefillIdNotpresentException();
	}
	}
	//new search
	@GetMapping("/search/{sid}/{pid}")
	public RefillOrder getrefillname(@PathVariable int sid,@PathVariable int pid) throws SubscriptionNotfoundException {
		try {
		return refillRepository.findByRefillOrderIdAndSubscriptionId(pid,sid);
		}catch (Exception e) {
			throw new SubscriptionNotfoundException();
		}
	}
}
