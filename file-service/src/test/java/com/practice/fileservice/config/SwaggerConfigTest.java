package com.practice.fileservice.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SwaggerConfigTest {

	@Test
	void api() {
		SwaggerConfig swaggerConfig = new SwaggerConfig();
		assertNotNull(swaggerConfig.api());
	}
}