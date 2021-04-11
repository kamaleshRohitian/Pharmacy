package com.cts.subscription.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.subscription.entity.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long>{

	Prescription findAllByDrugName(String drugName);

}
