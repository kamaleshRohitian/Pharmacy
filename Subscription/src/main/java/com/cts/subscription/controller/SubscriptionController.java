package com.cts.subscription.controller;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cts.subscription.entity.Prescription;
import com.cts.subscription.entity.Subscription;
import com.cts.subscription.exception.PrescriptionException;
import com.cts.subscription.exception.PrescriptionListException;
import com.cts.subscription.exception.SubscriptionListEmptyException;
import com.cts.subscription.exception.drugnotfoundexception;
import com.cts.subscription.exception.refillordernullException;
import com.cts.subscription.repository.DrugDetails;
import com.cts.subscription.repository.PrescriptionRepository;
import com.cts.subscription.repository.RefillOrder;
import com.cts.subscription.repository.SubscriptionRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SubscriptionController {

	@Autowired
	private PrescriptionRepository prescriptionRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	@Autowired
	public RestTemplate restTemplate;

	@PostMapping("/subscribe")
	public String subscribe(@RequestBody Prescription prescriptionDetails)
			throws PrescriptionException, drugnotfoundexception {
		try {
		DrugDetails drugDetails = restTemplate.getForObject(
				"http://localhost:8092/searchDrugsByName/" + prescriptionDetails.getDrugName(), DrugDetails.class);
		Optional<Prescription> check = prescriptionRepository.findById(prescriptionDetails.getPrescriptionId());
		if (check.isPresent())
			throw new PrescriptionException();

		log.info("Prescription start");
		prescriptionRepository.save(prescriptionDetails);
		log.info("prescription success");
		log.info("subscibe");
		Subscription subscription = new Subscription(prescriptionDetails.getPrescriptionId(),
				prescriptionDetails.getDrugName(), prescriptionDetails.getCourseDuration(),
				prescriptionDetails.getQuantity(), prescriptionDetails.getMemberId(), LocalDate.now(),
				prescriptionDetails.getMemberLocation(), "active");
		subscriptionRepository.save(subscription);
		log.info(prescriptionDetails.getDrugName());
		
			log.info(drugDetails.toString());
			RefillOrder order = new RefillOrder(subscription.getSubscriptionId(), LocalDate.now(),
					subscription.getPrescriptionId(), subscription.getQuantity(),
					(subscription.getQuantity() / drugDetails.getUnits()) * drugDetails.getCost(), "unpaid");
			restTemplate.postForObject("http://localhost:8094/refilldatainsert", order, RefillOrder.class);
			return "Success Subscribe";
		} catch (Exception e) {
			throw new drugnotfoundexception();
		}

	}

	@GetMapping("/getAllSubscription/{mId}")
	public List<Subscription> getAllSubscriptions(String mId) throws SubscriptionListEmptyException {
		log.info("get subscription for ");

		if (subscriptionRepository.findByMemberId(mId).isEmpty())
			throw new SubscriptionListEmptyException();
		return subscriptionRepository.findByMemberId(mId);

	}

	@GetMapping("/getAllPrescription")
	public List<Prescription> deleteAllSubscriptions() throws PrescriptionListException {
		if (prescriptionRepository.findAll().isEmpty())
			throw new PrescriptionListException();
		return prescriptionRepository.findAll();
	}
	@GetMapping("/unsubscribe/{sid}/{pid}")
	public String getAllSubscriptions(@PathVariable long sid, @PathVariable int pid)
			throws SubscriptionListEmptyException, refillordernullException {
		log.info("get subscription for ");
		if (subscriptionRepository.findById(sid).isEmpty())
			throw new SubscriptionListEmptyException();
		try {
			RefillOrder refillOrder = restTemplate.getForObject("http://localhost:8094/search/" + sid + "/" + pid,
					RefillOrder.class);

			if (refillOrder == null)
				throw new refillordernullException();
			if (refillOrder.getPaymentStatus().equalsIgnoreCase("paid")) {
				subscriptionRepository.deleteById(sid);
				return "Successfully unsubscribed";
			} else if (refillOrder.getPaymentStatus().equalsIgnoreCase("unpaid")) {
				return "please pay the bill amount of Rs." + refillOrder.getCost();
			}

			return "Something went wrong";
		} catch (Exception e) {
			throw new refillordernullException();
		}
	}

	@GetMapping("/getsubscriptionDetails/{sid}")
	public Subscription getSubscriptionid(@PathVariable long sid) throws SubscriptionListEmptyException {
		if (subscriptionRepository.findById(sid).isEmpty())
			throw new SubscriptionListEmptyException();
		return subscriptionRepository.findById(sid).get();
	}
}
