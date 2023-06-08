package io.nology.employeeCreatorBackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import io.nology.employeeCreatorBackend.address.Address;
import io.nology.employeeCreatorBackend.contract.Contract;
import io.nology.employeeCreatorBackend.employee.Employee;
import io.nology.employeeCreatorBackend.employee.EmployeeController;
import io.nology.employeeCreatorBackend.employee.EmployeeRepository;
import io.nology.employeeCreatorBackend.employee.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	static Employee employee1;
	static Employee employee2;
	
	@BeforeEach
	 void initEmployees() {
		Address address1 = new Address("10", "Bill Avenue", "Pymble", "NSW", "2073");
		LocalDate date1 = LocalDate.of(2011, 10, 5);
		LocalDate startDate1 = LocalDate.of(2012,3,10);
		LocalDate endDate1 = LocalDate.of(2017, 11, 23);
		Contract contract1 = new Contract(10, "partTime", "permanent", startDate1, endDate1);
		employee1 = new Employee("Gimli", "Son of", "Gloin", date1, "gimli@gmail.com", "0438614321", address1, contract1 );
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