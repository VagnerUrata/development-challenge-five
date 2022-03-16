package com.vagnerurata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vagnerurata.domain.Admin;
import com.vagnerurata.domain.enums.EPerfil;
import com.vagnerurata.repositories.AdminRepository;

@Service
public class DBService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instanciaDB() {

		Admin a1 = new Admin(null , "Medcloud", "admin@medcloud.com", encoder.encode("12345"));
		a1.addPerfil(EPerfil.ADMIN);

		adminRepository.save(a1);
	}
}