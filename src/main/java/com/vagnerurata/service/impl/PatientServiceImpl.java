package com.vagnerurata.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.vagnerurata.domain.Patient;
import com.vagnerurata.domain.dto.PatientDTO;
import com.vagnerurata.repositories.PatientRepository;
import com.vagnerurata.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Patient findById(Integer id) {
		Optional<Patient> obj = this.patientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(new Patient(), "Paciente não encontrado."));
	}

	@Override
	public Patient findByEmail(String email) {
		Optional<Patient> obj = this.patientRepository.findByEmail(email);
		return obj.orElseThrow(() -> new ObjectNotFoundException(new Patient(), "Email não encontrado."));
	}

	@Override
	public List<Patient> findAll() {
		return this.patientRepository.findAll();
	}

	@Override
	public Patient save(Patient obj) {
		validateEmail(obj.toDTO());
		return this.patientRepository.save(obj);
	}

	@Override
	public Patient update(Integer id, Patient obj) {
		Patient oldObj = this.findById(id);
		oldObj.setName(obj.getName());
		oldObj.setAddress(obj.getAddress());
		oldObj.setBirthDate(obj.getBirthDate());
		oldObj.setEmail(obj.getEmail());
		validateEmail(obj.toDTO());
		return this.save(oldObj);
	}

	@Override
	public void delete(Integer id) {
		this.patientRepository.deleteById(id);
	}

	private void validateEmail(PatientDTO objDTO) {
		Optional<Patient> obj = patientRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema.");
		}
	}
}
