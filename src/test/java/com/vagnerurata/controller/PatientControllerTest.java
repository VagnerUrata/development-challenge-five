package com.vagnerurata.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vagnerurata.domain.Patient;
import com.vagnerurata.domain.dto.PatientDTO;
import com.vagnerurata.service.PatientService;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatientService serviceMock;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void beforeEach() {
		this.objectMapper.findAndRegisterModules();
	}

	@Test
	void buscarIdTest() throws Exception {

		Patient patientFound = this.createValidPatientEntity();
		when(serviceMock.findById(1)).thenReturn(patientFound);

		ResultActions response = mockMvc.perform(get("/patients/").contentType("application/json"));

		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		PatientDTO patientDTO = objectMapper.readValue(resultStr, PatientDTO.class);
		System.out.println(patientDTO);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(patientDTO.getId()).isEqualTo(1);

	}

	@Test
	void buscarTodosUsuariosTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/usuarios").contentType("application/json"));
		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		PatientDTO[] list = objectMapper.readValue(resultStr, PatientDTO[].class);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(list.length >= 0).isTrue();
	}

	@Test
	void Test() throws JsonProcessingException, Exception {
		when(serviceMock.save(any(Patient.class))).thenReturn(this.createValidPatientEntity());
		PatientDTO dto = this.createValidPatientDTO();

		ResultActions response = mockMvc.perform(
				post("/patients").content(objectMapper.writeValueAsString(dto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String objStr = result.getResponse().getContentAsString();

		PatientDTO dtoResult = objectMapper.readValue(objStr, PatientDTO.class);

		assertThat(dtoResult.getId() > 0).isTrue();
		assertThat(dtoResult.getName()).isEqualTo(dto.getName());
		assertThat(dtoResult.getAddress()).isEqualTo(dto.getAddress());
		assertThat(dtoResult.getBirthDate()).isEqualTo(dto.getBirthDate());
		assertThat(dtoResult.getEmail()).isEqualTo(dto.getEmail());
	}

	@Test
	void cadastrarNomeNullTest() throws JsonProcessingException, Exception {
		PatientDTO patient = this.createValidPatientDTO();
		patient.setName(null);

		ResultActions response = mockMvc.perform(
				post("/patients").content(objectMapper.writeValueAsString(patient)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String objStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

//		ValidationError error = objectMapper.readValue(objStr, ValidationError.class);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
//		assertThat(error.getError()).isEqualTo("Erro de Validação");
	}

//	ResultActions response = mockMvc.perform(
//			post("/patients").content(objectMapper.writeValueAsString(patient)).contentType("application/json"));

//	MvcResult result = response.andReturn();

//	String objStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

//	ValidationError error = objectMapper.readValue(objStr, ValidationError.class);

//	assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
//	assertThat(error.getError()).isEqualTo("Erro de Validação");
//	}

	@Test
	void registerBirthDateNullTest() throws JsonProcessingException, Exception {
		PatientDTO patient = this.createValidPatientDTO();
		patient.setBirthDate(null);

		ResultActions response = mockMvc.perform(
				post("/patients").content(objectMapper.writeValueAsString(patient)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String objStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

//		ValidationError error = objectMapper.readValue(objStr, ValidationError.class);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
//		assertThat(error.getError()).isEqualTo("Erro de Validação");
	}

	@Test
	void registerAddressNullTest() throws JsonProcessingException, Exception {
		PatientDTO patient = this.createValidPatientDTO();
		patient.setAddress(null);

		ResultActions response = mockMvc.perform(
				post("/patients").content(objectMapper.writeValueAsString(patient)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String objStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

//		ValidationError error = objectMapper.readValue(objStr, ValidationError.class);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
//		assertThat(error.getError()).isEqualTo("Erro de Validação");
	}

	@Test
	void cadastrarTelefoneNullTest() throws JsonProcessingException, Exception {
		PatientDTO patient = this.createValidPatientDTO();
		patient.setEmail(null);

		ResultActions response = mockMvc.perform(
				post("/usuarios").content(objectMapper.writeValueAsString(patient)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String objStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

//		ValidationError error = objectMapper.readValue(objStr, ValidationError.class);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
//		assertThat(error.getError()).isEqualTo("Erro de Validação");
	}

	@Test
	void cadastrarEmailNullTest() throws JsonProcessingException, Exception {
		PatientDTO usuario = this.createValidPatientDTO();
		usuario.setEmail(null);

	ResultActions response = mockMvc.perform(
			post("/patients").content(objectMapper.writeValueAsString(usuario)).contentType("application/json"));

	MvcResult result = response.andReturn();

	String objStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

//	ValidationError error = objectMapper.readValue(objStr, ValidationError.class);

	assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
//		assertThat(error.getError()).isEqualTo("Erro de Validação");
	}

	@Test
	void cadastrarEnderecoNullTest() throws JsonProcessingException, Exception {
		PatientDTO usuario = this.createValidPatientDTO();
		usuario.setAddress(null);

		ResultActions response = mockMvc.perform(
				post("/usuarios").content(objectMapper.writeValueAsString(usuario)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String objStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

//		ValidationError error = objectMapper.readValue(objStr, ValidationError.class);

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
//		assertThat(error.getError()).isEqualTo("Erro de Validação");
	}

	@Test
	void updateTest() throws Exception {
		when(serviceMock.update(anyInt(), any(Patient.class))).thenReturn(this.createValidPatientEntity());
		PatientDTO dto = this.createValidPatientDTO();

		int id = 1;

		ResultActions response = mockMvc.perform(
				put("/patients/" + id).content(objectMapper.writeValueAsString(dto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String resultStr = result.getResponse().getContentAsString();

		PatientDTO updated = objectMapper.readValue(resultStr, PatientDTO.class);

		assertThat(updated.getId()).isEqualTo(id);
		assertThat(updated.getName()).isEqualTo(dto.getName());
		assertThat(updated.getAddress()).isEqualTo(dto.getAddress());
		assertThat(updated.getBirthDate()).isEqualTo(dto.getBirthDate());
		assertThat(updated.getEmail()).isEqualTo(dto.getEmail());

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

	}

	@Test
	void deleteTest() throws Exception {

		// PREPARO DOS DADOS E DOS MOCKS
		int id = 1;
		ResultActions response = mockMvc.perform(delete("/patients/" + id).contentType("application/json"));
		MvcResult result = response.andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	private PatientDTO createValidPatientDTO() {
		return PatientDTO.builder().id(1).name("Karina").birthDate(null).email("boladinho@hotmail.com")
				.address("Avenida Paulista").build();
	}

	private Patient createValidPatientEntity() {
		return Patient.builder().id(1).name("Karina").birthDate(null).email("boladinho@hotmail.com")
				.address("Avenida Paulista").build();
	}

}
