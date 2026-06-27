package com.brewery.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {
		"spring.datasource.url=jdbc:postgresql://localhost:5432/brewery_db?options=-c%20TimeZone=UTC"
})
@ActiveProfiles("test")
class BreweryManagementApplicationTests {

	@Test
	void contextLoads() {
	}

}
