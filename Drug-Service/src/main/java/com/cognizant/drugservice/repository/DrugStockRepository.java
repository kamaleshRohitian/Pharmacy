package com.cognizant.drugservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.drugservice.entity.DrugStock;

@Repository
public interface DrugStockRepository extends JpaRepository<DrugStock,Integer>{
//	

	List<Object> findByDrugidAndLocationIgnoreCase(int id, String location);

	List<DrugStock> findAllBydrugid(int id);
}
