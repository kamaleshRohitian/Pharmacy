package com.cognizant.modal;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Subscription {
	
	
	public Subscription(Long prescriptionId2, String drugName2, String courseDuration, int quantity2, String memberId2,
			LocalDate now, String memberLocation2, String subscriptionStatus) {
		this.subscriptionStatus=subscriptionStatus;
		this.subscriptionDate=now;
		this.quantity=quantity2;
		this.prescriptionId=prescriptionId2;
		this.drugName=drugName2;
		this.memberId=memberId2;
		this.memberLocation=memberLocation2;
		this.refillCycle=courseDuration;
	}
	private Long subscriptionId;
	private Long prescriptionId;
	private String drugName;
	private String refillCycle;
	private int quantity;
	private String memberId;
	private LocalDate subscriptionDate;
	private String memberLocation;
	private String subscriptionStatus;
}
