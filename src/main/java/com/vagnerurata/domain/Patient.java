package com.vagnerurata.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vagnerurata.domain.dto.PatientDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_patient")
public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date birthDate;

	private String email;

	private String address;

	private String password;

	public PatientDTO toDTO() {
		var mapper = new ModelMapper();
		return mapper.map(this, PatientDTO.class);
	}
}
