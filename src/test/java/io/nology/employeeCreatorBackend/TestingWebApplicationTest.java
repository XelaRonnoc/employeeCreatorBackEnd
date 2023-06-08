package io.nology.employeeCreatorBackend;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import io.nology.employeeCreatorBackend.address.Address;
import io.nology.employeeCreatorBackend.contract.Contract;
import io.nology.employeeCreatorBackend.employee.Employee;
import io.nology.employeeCreatorBackend.employee.EmployeeRepository;
import io.nology.employeeCreatorBackend.employee.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class TestingWebApplicationTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService;

	@Autowired
	private MockMvc mockMvc;
	
	static Employee employee1;
	static Employee employee2;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/employees")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("")));
	}
	
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
}

//{"id":1,"firstName":"Legolas","middleName":"of the","lastName":"Woodland","dateOfBirth":"2000-03-05","email":"lego@gmail.com","mobileNum":"0451678336","address":{"id":1,"streetNumber":"15","streetName":"Christ Street","suburb":"Lindfield","state":"QLD","postCode":"2073"},"contract":{"id":1,"contractedHours":10,"contractTime":"partTime","contractType":"permanent","startDate":"2023-07-20","endDate":"2028-05-18"}},