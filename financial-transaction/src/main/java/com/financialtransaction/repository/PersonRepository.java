package com.financialtransaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financialtransaction.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

}
