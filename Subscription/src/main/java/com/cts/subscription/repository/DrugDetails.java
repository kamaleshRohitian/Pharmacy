package com.cts.subscription.repository;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DrugDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull
	@Size(min=2, max=50, message="Name should have atleast 2 characters")
	private String name;
	@NotNull
	private int units;
	@NotNull
	private int cost;
	@NotNull
	private String location;
	@NotNull
	private String manufacturer;
	@NotNull
	private Date manufacturedDate;
	@NotNull
	private Date expiryDate;
	@NotNull
	private String medicalComposition;

}
