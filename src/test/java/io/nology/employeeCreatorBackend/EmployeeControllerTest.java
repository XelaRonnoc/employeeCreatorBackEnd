package io.nology.employeeCreatorBackend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import io.nology.employeeCreatorBackend.address.Address;
import io.nology.employeeCreatorBackend.contract.Contract;
import io.nology.employeeCreatorBackend.employee.Employee;
import io.nology.employeeCreatorBackend.employee.EmployeeRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeRepository employeeRepository;

	@Autowired
	private JacksonTester<Employee> jsonEmployee;
	
	@Autowired
	private JacksonTester<List<Employee>> jsonEmployeeList;
	
	static Employee employee1;
	static Employee employee2;
	
	@BeforeEach
	 void initEmployees() {
		Address address1 = new Address("10", "Bill Avenue", "Pymble", "NSW", "2073");
		LocalDate date1 = LocalDate.of(2011, 10, 5);
		LocalDate startDate1 = LocalDate.of(2012,3,10);
		LocalDate endDate1 = LocalDate.of(2017, 11, 23);
		Contract contract1 = new Contract(10, "partTime", "permanent", startDate1, endDate1);
		employee1 = new Employee("Boromir", "Son of", "Denathor", date1, "gimli@gmail.com", "0438614321", address1, contract1 );
		employee1.setId(1l);
		
		Address address2 = new Address("14", "Melvin Avenue", "Gordon", "QLD", "3071");
		LocalDate date2 = LocalDate.of(2001, 4, 5);
		LocalDate startDate2 = LocalDate.of(2002,9,18);
		LocalDate endDate2 = LocalDate.of(2017, 11, 23);
		Contract contract2 = new Contract(10, "fullTime", "contract", startDate2, endDate2);
		employee2 = new Employee("Gimli", "Son of", "Gloin", date2, "gimli@gmail.com", "0438614321", address2, contract2 );
		employee2.setId(2l);
	}
	
	@Test
	void can_Get_Employee_By_Id_When_Exists () throws Exception {
		given(employeeRepository.findById(2l)).willReturn(Optional.of(employee2));
		
		MockHttpServletResponse response = mockMvc.perform(
				get("/employees/2")
				.accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		System.out.println(response.getContentAsString() + " response HERE");
		assertThat(response.getContentAsString()).isEqualTo(jsonEmployee.write(employee2).getJson());
	}
	
	@Test
	void can_Get_All_Employees () throws Exception {
		given(employeeRepository.findAll()).willReturn(List.of(employee1,employee2));
		
		
		MockHttpServletResponse response = mockMvc.perform(
				get("/employees")
				.accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		System.out.println(response.getContentAsString() + " response HERE");
		assertThat(response.getContentAsString()).isEqualTo(jsonEmployeeList.write(List.of(employee1,employee2)).getJson());
	}

	
}
