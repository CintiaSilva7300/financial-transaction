package com.financialtransaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financialtransaction.model.Installment;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, String> {

}
