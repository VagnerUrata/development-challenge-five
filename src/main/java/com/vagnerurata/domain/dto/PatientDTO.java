package com.vagnerurata.domain.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private Date birthDate;
	
	private String email;
		
	private String address;

	
}
