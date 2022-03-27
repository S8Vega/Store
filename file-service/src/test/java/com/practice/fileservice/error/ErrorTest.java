package com.practice.fileservice.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class ErrorTest {

	@Test
	void constructor() {
		Error error = new Error();

		assertNull(error.getMensaje());
		assertNull(error.getNombre());
	}

}