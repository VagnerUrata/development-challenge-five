package com.vagnerurata.domain.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

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

	@NotNull(message = "Nome obrigatório.")
	private String name;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@NotNull(message = "Data de nascimento obrigatório.")
	private Date birthDate;

	@NotNull(message = "Email obrigatório.")
	private String email;

	@NotNull(message = "Endereço obrigatório.")
	private String address;

	public Patient toEntity() {
		var mapper = new ModelMapper();
		return mapper.map(this, Patient.class);
	}
}
