package io.nology.employeeCreatorBackend;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import io.nology.employeeCreatorBackend.employee.Employee;

import org.springframework.beans.factory.annotation.Value;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
	

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void returnListOfEmployeeObjects() throws Exception {
		assertThat(this.restTemplate.
				getForObject("http://localhost:" + port + "/employees", String.class))
				.contains("");
	}

}
