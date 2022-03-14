package com.vagnerurata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vagnerurata.domain.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
