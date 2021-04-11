package com.cognizant.modal;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefillOrderLineItem {
	
	@Id
	private int subscriptionId;
	private int refillOrderId;
	@Transient
	private List<DrugDetails> drugDetails;
    private int quantity;
	

}
