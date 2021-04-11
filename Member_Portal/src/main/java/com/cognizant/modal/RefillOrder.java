package com.cognizant.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefillOrder {

	private int refillId;
	private int subscriptionId;
	private LocalDate refillDate;
	private int refillOrderId;

	// new data

	private int quantity;
	private int cost;

	// private String quantityStatus;not needed
	private String paymentStatus;
}
