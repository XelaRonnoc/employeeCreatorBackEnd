package io.nology.employeeCreatorBackend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.BDDMockito.given;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.internal.bytebuddy.asm.MemberSubstitution.Substitution.ForMethodInvocation.OfGivenMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.nology.employeeCreatorBackend.address.Address;
import io.nology.employeeCreatorBackend.contract.Contract;
import io.nology.employeeCreatorBackend.employee.Employee;
import io.nology.employeeCreatorBackend.employee.EmployeeController;
import io.nology.employeeCreatorBackend.employee.EmployeeRepository;
import io.nology.employeeCreatorBackend.employee.EmployeeService;
import io.nology.employeeCreatorBackend.exceptions.GlobalExceptionHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class TestingWebApplicationTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@InjectMocks
	private EmployeeService employeeService;

//	@Autowired
	private MockMvc mockMvc;
	
//	@Autowired
	private JacksonTester<Employee> jsonEmployee;
	
	static Employee employee1;
	static Employee employee2;

//	@Test
//	public void shouldReturnDefaultMessage() throws Exception {
//		this.mockMvc.perform(get("/employees")).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("")));
//	}
	
	@BeforeEach
	 void initEmployees() {
		Address address1 = new Address("10", "Bill Avenue", "Pymble", "NSW", "2073");
		LocalDate date1 = LocalDate.of(2011, 10, 5);
		LocalDate startDate1 = LocalDate.of(2012,3,10);
		LocalDate endDate1 = LocalDate.of(2017, 11, 23);
		Contract contract1 = new Contract(10, "partTime", "permanent", startDate1, endDate1);
		employee1 = new Employee("Gimli", "Son of", "Gloin", date1, "gimli@gmail.com", "0438614321", address1, contract1 );
		
		Address address2 = new Address("14", "Melvin Avenue", "Gordon", "QLD", "3071");
		LocalDate date2 = LocalDate.of(2001, 4, 5);
		LocalDate startDate2 = LocalDate.of(2002,9,18);
		LocalDate endDate2 = LocalDate.of(2017, 11, 23);
		Contract contract2 = new Contract(10, "fullTime", "contract", startDate2, endDate2);
		employee2 = new Employee("Gimli", "Son of", "Gloin", date2, "gimli@gmail.com", "0438614321", address2, contract2 );
		
		
		JacksonTester.initFields(this, new ObjectMapper());
		
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
				.setControllerAdvice(new GlobalExceptionHandler())
				.build();
	}
	
	@Test
	void findAll_should_return_employee_list() {
		when(employeeRepository.findAll()).thenReturn(List.of(employee1,employee2));
		List<Employee> employees = this.employeeService.findAll();
		
		assertEquals(List.of(employee1,employee2), employees);
		verify(this.employeeRepository).findAll();
	}
	
	@Test
	void find_by_id_should_return_correct_employee() {
		when(employeeRepository.findById(2l)).thenReturn(Optional.of(employee1));
		Optional<Employee> maybeEmployee = this.employeeService.findById(2l);
		
		assertEquals(employee1, maybeEmployee.get());
		verify(this.employeeRepository).findById(2l);
	}
	
//	@Test
//	public void canRetrieveByIdWhenExists() throws Exception {
//		given(employeeRepository.findById(2l)).willReturn(Optional.of(employee2));
//		
//		MockHttpServletResponse response = mockMvc.perform(
//				get ("/employees/2")
//				.accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
//		
//		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//		assertThat(response.getContentAsString()).isEqualTo(jsonEmployee.write(employee2).getJson());
//	}
	
}