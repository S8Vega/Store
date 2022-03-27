package com.practice.fileservice.error;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class ErrorHandlerTest {

	@InjectMocks
	private ErrorHandler errorHandler;

	@Test
	void illegalArgumentException() {
		Error error = Error.builder()
				.nombre("IllegalArgumentException")
				.mensaje("message")
				.build();
		IllegalArgumentException exception = new IllegalArgumentException(error.getMensaje());

		ResponseEntity<Error> responseError = errorHandler.illegalArgumentException(exception);

		assertEquals(HttpStatus.BAD_REQUEST, responseError.getStatusCode());
		assertEquals(error, responseError.getBody());
	}

	@Test
	void notFoundException() {
		Error error = Error.builder()
				.nombre("ClassNotFoundException")
				.mensaje("message")
				.build();
		ClassNotFoundException exception = new ClassNotFoundException(error.getMensaje());

		ResponseEntity<Error> responseError = errorHandler.notFoundException(exception);

		assertEquals(HttpStatus.NOT_FOUND, responseError.getStatusCode());
		assertEquals(error, responseError.getBody());
	}
}