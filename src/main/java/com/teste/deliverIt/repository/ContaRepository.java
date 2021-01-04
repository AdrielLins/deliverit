package com.teste.deliverIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.deliverIt.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

}
