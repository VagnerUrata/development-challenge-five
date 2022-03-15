//package com.vagnerurata.domain;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.vagnerurata.repositories.PatientRepository;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//class PatientTest {
//	
//		@Autowired
//		private PatientRepository patientRepository;
//
//		@Autowired
//		private TestEntityManager testEntityManager;
//
//		@Test
//		void findByIdExistTest() {
//
//			Patient patient = this.createValidPatient();
//
//			this.testEntityManager.persist(patient);
//
//			Optional<Patient> response = this.patientRepository.findById(1);
//
//			assertThat(response).isNotNull();
//		}
//
//		@Test
//		void findByIdNotExistsTest() {
//
//			Optional<Patient> response = this.patientRepository.findById(1);
//
//			assertThat(response).isEmpty();
//		}
//
//		@Test
//		void findAllTest() {
//
//			Patient patient = this.createValidPatient();
//
//			this.testEntityManager.persist(patient);
//
//			List<Patient> list = this.patientRepository.findAll();
//
//			assertThat(list.size()).isEqualTo(1);
//		}
//
//		@Test
//		void saveTest() {
//
//			Patient usuario = this.createValidPatient();
//
//			Patient saved = this.patientRepository.save(usuario);
//
//			assertThat(saved.getId()).isNotNull();
//			assertThat(saved.getName()).isEqualTo(usuario.getName());
//			assertThat(saved.getAddress()).isEqualTo(usuario.getAddress());
//			assertThat(saved.getBirthDate()).isEqualTo(usuario.getBirthDate());
//			assertThat(saved.getEmail()).isEqualTo(usuario.getEmail());
//			
//		}
//
//		private Patient createValidPatient() {
//			return Patient.builder().name("Anderson").birthDate("1998, 05, 18").email("boladinho@hotmail.com").address("Avenida Paulista").build();
//		}
//	}