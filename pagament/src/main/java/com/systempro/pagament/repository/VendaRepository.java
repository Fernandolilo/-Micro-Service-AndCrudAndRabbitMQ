package com.systempro.pagament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systempro.pagament.entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>{

}
