package com.cognizant.modal;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugDetails {
	
	private int id;
	private String name;
	private int units;
	private int cost;
	private String location;
	private String manufacturer;
	private Date manufacturedDate;
	private Date expiryDate;
	private String medicalComposition;

}
