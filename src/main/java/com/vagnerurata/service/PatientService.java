package com.vagnerurata.service;

import java.util.List;

import com.vagnerurata.domain.Patient;

public interface PatientService {

	Patient findById(Integer id);

	Patient findByEmail(String email);

	List<Patient> findAll();

	Patient save(Patient obj);

	Patient update(Integer id, Patient obj);

	void delete(Integer id);
}
