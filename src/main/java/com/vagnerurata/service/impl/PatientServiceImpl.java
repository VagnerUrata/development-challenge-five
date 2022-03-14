package com.vagnerurata.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import com.vagnerurata.domain.Patient;
import com.vagnerurata.repositories.PatientRepository;
import com.vagnerurata.service.PatientService;


public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public Patient findById(Integer id) {
		Optional<Patient> obj = this.patientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(new Patient(), "Paciente não encontrado Id: "+ id));
	}

	@Override
	public List<Patient> findAll() {
		return this.patientRepository.findAll();
	}

	@Override
	public Patient save(Patient obj) {
		return this.patientRepository.save(obj);
	}

	@Override
	public Patient update(Integer id, Patient obj) {
		Patient oldObj = this.findById(id);
		oldObj.setName(obj.getName());
		oldObj.setAddress(obj.getAddress());
		oldObj.setBirthDate(obj.getBirthDate());
		oldObj.setEmail(obj.getAddress());
		return this.save(oldObj);
	}

	@Override
	public void delete(Integer id) {
		this.patientRepository.deleteById(id);
	}
}
