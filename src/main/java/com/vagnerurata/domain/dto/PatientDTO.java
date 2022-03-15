package com.vagnerurata.domain.dto;

import java.io.Serializable;
import java.util.Date;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vagnerurata.domain.Patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	@JsonFormat(pattern="dd-MM-yyyy")
	private Date birthDate;

	private String email;

	private String address;

	public Patient toEntity() {
		var mapper = new ModelMapper();
		return mapper.map(this, Patient.class);
	}
}
