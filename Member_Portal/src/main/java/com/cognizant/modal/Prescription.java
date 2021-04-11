package com.cognizant.modal;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Prescription {
	private Long prescriptionId;
	private String memberId;
	private String memberLocation;
	private String policyNumber;
	private String insuranceProvider;
	private LocalDate prescriptionDate;
	private String drugName;
	private String dosageDefinition;
	private int quantity;
	private String courseDuration;
	private String doctorName;
}
