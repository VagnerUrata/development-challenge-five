package com.vagnerurata.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vagnerurata.domain.Admin;
import com.vagnerurata.repositories.AdminRepository;
import com.vagnerurata.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Admin> admin = adminRepository.findByEmail(email);
		if (admin.isPresent()) {
			return new UserSS(admin.get().getId(), admin.get().getEmail(), admin.get().getPassword(),
					admin.get().getPerfil());
		}

		throw new UsernameNotFoundException(email);
	}

}