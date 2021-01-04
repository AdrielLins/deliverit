package com.teste.deliverIt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.deliverIt.model.Multa;

public interface MultaRepository extends JpaRepository<Multa, Long>{
	List<Multa> findPercentageRule(String nivel);
}
