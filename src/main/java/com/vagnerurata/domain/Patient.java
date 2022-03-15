package com.vagnerurata.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vagnerurata.domain.dto.PatientDTO;
import com.vagnerurata.domain.enums.EPerfil;

import lombok.Data;

@Data
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

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIL")
	private Set<Integer> perfil = new HashSet<>();

	public Patient() {
		super();
		addPerfil(EPerfil.PACIENT);
	}

	public Patient(Integer id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		addPerfil(EPerfil.PACIENT);
	}

	public Set<EPerfil> getPerfil() {
		return perfil.stream().map(x -> EPerfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(EPerfil perfil) {
		this.perfil.add(perfil.getCode());
	}

	public PatientDTO toDTO() {
		var mapper = new ModelMapper();
		return mapper.map(this, PatientDTO.class);
	}

}
