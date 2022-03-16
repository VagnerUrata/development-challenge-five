package com.vagnerurata.exceptions;

public class PatientNotFoundExceptions extends RuntimeException {

	public PatientNotFoundExceptions(String messageError) {
		super(messageError);
	}
}
