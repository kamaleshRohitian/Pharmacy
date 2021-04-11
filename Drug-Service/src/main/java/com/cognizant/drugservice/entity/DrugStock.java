package com.cognizant.drugservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DrugStock {
	@Id
	@NotNull
	private int id;
	@NotNull
	private int drugid;
	@NotNull
	private String location;
	@NotNull
	private int quantity;

}
