package com.vagnerurata.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vagnerurata.domain.Patient;
import com.vagnerurata.repositories.PatientRepository;
import com.vagnerurata.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Patient> patient = patientRepository.findByEmail(email);
		if (patient.isPresent()) {
			return new UserSS(patient.get().getId(), patient.get().getEmail(), patient.get().getPassword(), patient.get().getPerfil());
		}

		throw new UsernameNotFoundException(email);
	}

}