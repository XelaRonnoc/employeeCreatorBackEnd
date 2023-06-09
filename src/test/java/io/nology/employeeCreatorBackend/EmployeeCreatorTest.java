package io.nology.employeeCreatorBackend;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;

import io.nology.employeeCreatorBackend.adapters.LocalDateAdapter;
import io.nology.employeeCreatorBackend.address.Address;
import io.nology.employeeCreatorBackend.address.AddressRepository;
import io.nology.employeeCreatorBackend.contract.Contract;
import io.nology.employeeCreatorBackend.contract.ContractRepository;
import io.nology.employeeCreatorBackend.employee.Employee;
import io.nology.employeeCreatorBackend.employee.EmployeeController;
import io.nology.employeeCreatorBackend.employee.EmployeeRepository;
import io.nology.employeeCreatorBackend.employee.EmployeeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class EmployeeCreatorTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	static Employee employee1;
	static Employee employee2;
	
	@BeforeEach
	 void initEmployees() {
		Address address1 = new Address("10", "Bill Avenue", "Pymble", "NSW", "2073");
		address1.setId(1l);
		LocalDate date1 = LocalDate.of(2011, 10, 5);
		LocalDate startDate1 = LocalDate.of(2012,3,10);
		LocalDate endDate1 = LocalDate.of(2017, 11, 23);
		Contract contract1 = new Contract(10, "partTime", "permanent", startDate1, endDate1);
		contract1.setId(1l);
		employee1 = new Employee("Boromir", "Son of", "Denathor", date1, "gondor@gmail.com", "0438614321", address1, contract1 );
		employee1.setId(1l);
		
		Address address2 = new Address("14", "Melvin Avenue", "Gordon", "QLD", "3071");
		address1.setId(2l);
		LocalDate date2 = LocalDate.of(2001, 4, 5);
		LocalDate startDate2 = LocalDate.of(2002,9,18);
		LocalDate endDate2 = LocalDate.of(2017, 11, 23);
		Contract contract2 = new Contract(10, "fullTime", "contract", startDate2, endDate2);
		address1.setId(2l);
		employee2 = new Employee("Gimli", "Son of", "Gloin", date2, "gimli@gmail.com", "0438614321", address2, contract2 );
		employee2.setId(2l);
		
	}
	
	  @Test
	  void shouldUpdateTutorial() throws Exception {
	    long id = 1L;

	    Employee oldEmployee = employee1;
		Employee updatedEmployee1 = oldEmployee;
		updatedEmployee1.setFirstName("Faramir");

	    when(employeeRepository.findById(id)).thenReturn(Optional.of(oldEmployee));
	    when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee1);

	    mockMvc.perform(put("/api/tutorials/{id}", id).contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(updatedEmployee1)))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstName").value(updatedEmployee1.getFirstName()))
	        .andExpect(jsonPath("$.lastName").value(updatedEmployee1.getLastName()))
	        .andExpect(jsonPath("$.dateOfBirth").value(updatedEmployee1.getDateOfBirth()))
	        .andDo(print());
	  }
	  
//	  @Test
//	  void shouldReturnNotFoundUpdateTutorial() throws Exception {
//	    long id = 1L;
//
//	    Tutorial updatedtutorial = new Tutorial(id, "Updated", "Updated", true);
//
//	    when(tutorialRepository.findById(id)).thenReturn(Optional.empty());
//	    when(tutorialRepository.save(any(Tutorial.class))).thenReturn(updatedtutorial);
//
//	    mockMvc.perform(put("/api/tutorials/{id}", id).contentType(MediaType.APPLICATION_JSON)
//	        .content(objectMapper.writeValueAsString(updatedtutorial)))
//	        .andExpect(status().isNotFound())
//	        .andDo(print());
//	  }
}
