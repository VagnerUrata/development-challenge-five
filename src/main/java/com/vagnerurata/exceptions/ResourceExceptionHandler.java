package com.vagnerurata.exceptions;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)

	public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException e,
			HttpServletRequest request) {

		var ve = new ValidationError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação",
				"Erro de validação ao salvar/alterar a entidade", request.getRequestURI());

		List<FieldError> errors = e.getBindingResult().getFieldErrors();

		for (FieldError err : errors) {
			ve.addError(err.getField(), err.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ve);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> noSuchElementException(ObjectNotFoundException e, HttpServletRequest request) {

		StandardError se = StandardError.builder().timestamp(new Date()).status(HttpStatus.NOT_FOUND.value())
				.error("Erro ao achar a entidade").message(e.getMessage()).path(request.getRequestURI()).build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);
	}

	@ExceptionHandler(ObjectNotFoundExceptions.class)
	public ResponseEntity<StandardError> objetoNaoEncontradoException(ObjectNotFoundExceptions e,
			HttpServletRequest request) {

		StandardError se = StandardError.builder().timestamp(new Date()).status(HttpStatus.NOT_FOUND.value())
				.error("Erro ao achar a entidade").message(e.getMessage()).path(request.getRequestURI()).build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);
	}

	@ExceptionHandler(PatientNotFoundExceptions.class)
	public ResponseEntity<StandardError> handler(PatientNotFoundExceptions error, HttpServletRequest request) {

		StandardError erro = StandardError.builder().timestamp(new Date()).message(error.getMessage())
				.path(request.getRequestURI()).status(HttpStatus.BAD_REQUEST.value()).build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e,
			HttpServletRequest request) {

		StandardError se = StandardError.builder().timestamp(new Date()).status(HttpStatus.BAD_REQUEST.value())
				.error("Email já cadastrado").message(e.getMessage()).path(request.getRequestURI()).build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(se);
	}
}
