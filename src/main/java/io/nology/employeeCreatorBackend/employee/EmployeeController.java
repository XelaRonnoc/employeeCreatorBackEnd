package io.nology.employeeCreatorBackend.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	// Dependency injection
	@Autowired // spirng creating instance of EmployeeService automagically
	private EmployeeService service;
	
	// POST
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody CreateEmployeeDTO data) {
		// forward request body to service layer
		Employee createdEmployee = this.service.create(data);
		return new ResponseEntity<Employee>(createdEmployee, HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> allEmployees = this.service.findAll();
		
		return new ResponseEntity<List<Employee>>(allEmployees, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteById(@PathVariable Long id){
		boolean deleted = this.service.deleteById(id);
		if(deleted) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
	
	

