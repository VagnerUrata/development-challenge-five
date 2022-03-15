package com.vagnerurata.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vagnerurata.domain.Patient;
import com.vagnerurata.domain.dto.PatientDTO;
import com.vagnerurata.service.PatientService;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping
	public ResponseEntity<List<PatientDTO>> getAll() {
		List<Patient> obj = this.patientService.findAll();
		List<PatientDTO> objDTO = obj.stream().map(Patient::toDTO).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PatientDTO> getOne(@PathVariable Integer id) {
		Patient obj = this.patientService.findById(id);
		return ResponseEntity.ok().body(obj.toDTO());
	}

	@PostMapping
	public ResponseEntity<PatientDTO> create(@RequestBody PatientDTO objDTO) {
		Patient obj = this.patientService.save(objDTO.toEntity());
		return ResponseEntity.ok().body(obj.toDTO());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PatientDTO> update(@PathVariable Integer id, @RequestBody PatientDTO objDTO) {
		Patient obj = this.patientService.update(id, objDTO.toEntity());
		return ResponseEntity.ok().body(obj.toDTO());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PatientDTO> delete(@PathVariable Integer id) {
		this.patientService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
