package com.cognizant.refill.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.cognizant.refill.entity.RefillOrder;

@Repository
public interface RefillRepository extends JpaRepository<RefillOrder,Integer>{

	RefillOrder findBySubscriptionId(int id);

	RefillOrder findByRefillOrderIdAndSubscriptionId(int rid, int sid);


	RefillOrder findByRefillOrderId(int refillid);


}
