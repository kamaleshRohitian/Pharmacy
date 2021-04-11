package com.cognizant.modal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DrugStock {

	private int id;
	private int drugid;
	private String location;
	private int quantity;

}
