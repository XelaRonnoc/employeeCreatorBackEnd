package io.nology.employeeCreatorBackend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import io.nology.employeeCreatorBackend.adapters.LocalDateAdapter;
import io.nology.employeeCreatorBackend.address.Address;
import io.nology.employeeCreatorBackend.contract.Contract;
import io.nology.employeeCreatorBackend.employee.CreateEmployeeDTO;
import io.nology.employeeCreatorBackend.employee.Employee;
import io.nology.employeeCreatorBackend.employee.EmployeeController;
import io.nology.employeeCreatorBackend.employee.EmployeeRepository;
import io.nology.employeeCreatorBackend.employee.EmployeeService;
import io.nology.employeeCreatorBackend.employee.UpdateEmployeeDTO;
import io.nology.employeeCreatorBackend.exceptions.NotFoundException;

import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;  


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
@Import(EmployeeController.class)
public class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	@Autowired
	private JacksonTester<Employee> jsonEmployee;
	
	@Autowired
	private JacksonTester<UpdateEmployeeDTO> jsonUpdateEmployeeDTO;
	
	@Autowired
	private JacksonTester<CreateEmployeeDTO> jsonCreateEmployeeDTO;
	
	@Autowired
	private JacksonTester<List<Employee>> jsonEmployeeList;
	
	static Gson gson;
	
	static Employee employee1;
	static Employee employee2;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
		
		gson = new GsonBuilder().setPrettyPrinting()
		        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
		        .create();
	}
	
	@Test
	void can_Get_Employee_By_Id_When_Exists () throws Exception {
		given(employeeService.findById(2l)).willReturn(Optional.of(employee2));
		
		MockHttpServletResponse response = mockMvc.perform(
				get("/employees/2")
				.accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonEmployee.write(employee2).getJson());
	}
	
	@Test
	void returns_404_If_No_Employee_Find_By_Id () throws Exception {
		NotFoundException exception = new NotFoundException("Could not find employee with id: " + 3);
		given(employeeService.findById(3l)).willThrow(exception);
		
		MockHttpServletResponse response = mockMvc.perform(
				get("/employees/3")
				.accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(response.getContentAsString()).isEqualTo(exception.getMessage());
	}
	
	@Test
	void returns_No_Content_Delete_By_Id() throws Exception {
		
		given(employeeService.deleteById(1l)).willReturn(true);
		
		MockHttpServletResponse response = mockMvc.perform(
				delete("/employees/1")
				.accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
		assertThat(response.getContentAsString())
		.isEqualTo("");
	}
	
	@Test
	void returns_Not_Found_Exception_If_No_Employee_Delete_By_Id() throws Exception {
		NotFoundException exception = new NotFoundException(String.format("Could not delete employee with id: %d employee does not exist", 1));
		given(employeeService.deleteById(3l)).willThrow(exception);
		
		MockHttpServletResponse response = mockMvc.perform(
				delete("/employees/3")
				.accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(response.getContentAsString())
		.isEqualTo(exception.getMessage());
	}
	
	@Test
	void can_Get_All_Employees () throws Exception {
		given(employeeService.findAll()).willReturn(List.of(employee1,employee2));
		
		MockHttpServletResponse response = mockMvc.perform(
				get("/employees")
				.accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonEmployeeList.write(List.of(employee1,employee2)).getJson());
	}
	
	@Test
	void put_By_Id () throws Exception {
		UpdateEmployeeDTO employee1DTO = 
				new UpdateEmployeeDTO(
						"Faramir",
						employee1.getMiddleName(),
						employee1.getLastName(),
						employee1.getDateOfBirth(),
						employee1.getEmail(),
						employee1.getMobileNum(),
						employee1.getAddress(),
						employee1.getContract()
						);

		Employee updatedEmployee1 = employee1;
		updatedEmployee1.setId(1l);
		updatedEmployee1.setFirstName("Faramir");
		given(employeeService.setById(eq(updatedEmployee1.getId()), any(UpdateEmployeeDTO.class))).willReturn(Optional.of(updatedEmployee1));
		MockHttpServletResponse response = mockMvc.perform(
				put("/employees/1").contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(employee1DTO))
				.accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
	void create_new_Employee () throws Exception {
		CreateEmployeeDTO newEmployee = new CreateEmployeeDTO();
		Address newAddress = employee2.getAddress();
		Contract newContract = employee1.getContract();
		newContract.setId(null);
		newAddress.setId(null);
		newEmployee.setAddress(newAddress);
		newEmployee.setContract(newContract);
		newEmployee.setFirstName("Aragorn");
		newEmployee.setMiddleName("son of");
		newEmployee.setLastName("Arathorn");
		LocalDate newDOB = LocalDate.of(2000, 9, 6);
		newEmployee.setDateOfBirth(newDOB);
		newEmployee.setEmail("king@gmail.com");
		newEmployee.setMobileNum("043450834");
		
		Employee employee3 = modelMapper.map(newEmployee,  Employee.class);
		employee3.setId(3l);;
		given(employeeService.create(newEmployee)).willReturn(employee3);
		
		MockHttpServletResponse response = mockMvc.perform(
				post("/employees").contentType(MediaType.APPLICATION_JSON)
				.content(jsonCreateEmployeeDTO.write(newEmployee).getJson())
				.accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
	}

	
}
