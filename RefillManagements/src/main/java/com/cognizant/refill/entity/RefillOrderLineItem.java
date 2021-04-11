package com.cognizant.refill.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.cognizant.refill.service.DrugDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RefillOrderLineItem {
	
	@Id
	private int subscriptionId;
	private int refillOrderId;
	@Transient
	private List<DrugDetails> drugDetails;
    private int quantity;
	

}
