package com.cts.subscription.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@ToString
@Table(name = "Subscription")
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
