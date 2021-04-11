package com.cts.subscription.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class RefillOrder {
	
	public RefillOrder(Long subscriptionId2, LocalDate localDate, Long prescriptionId, int quantity2, int cost, String status) {
		this.subscriptionId=subscriptionId2;
		this.cost=cost;
		this.refillOrderId=prescriptionId;
		this.quantity=quantity2;
		this.paymentStatus=status;
		this.refillDate=localDate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int refillId;
	private Long subscriptionId;
	private LocalDate refillDate;
	private Long refillOrderId;
	
	//new data
	
	private int quantity;
	private int cost;
	
	//private String quantityStatus;not needed
	private String paymentStatus;
}
