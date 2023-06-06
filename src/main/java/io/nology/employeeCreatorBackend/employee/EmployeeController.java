package io.nology.employeeCreatorBackend.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nology.employeeCreatorBackend.exceptions.NotFoundException;
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
		System.out.println(data.getAddress());
		Employee createdEmployee = this.service.create(data);
		return new ResponseEntity<Employee>(createdEmployee, HttpStatus.CREATED);
	}
	

	@GetMapping
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> allEmployees = this.service.findAll();
		
		return new ResponseEntity<List<Employee>>(allEmployees, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findById(@PathVariable Long id) {
		Optional<Employee> maybeEmployee = this.service.findById(id);
		
		if(maybeEmployee.isEmpty()) {
			throw new NotFoundException("Could not find post with id: " + id);
		}
		
		return new ResponseEntity<Employee>(maybeEmployee.get(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteById(@PathVariable Long id){
		boolean deleted = this.service.deleteById(id);
		if(deleted) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		throw new NotFoundException(String.format("Could not delete post with id: %d post does not exist", id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> setById(@PathVariable Long id, @Valid @RequestBody UpdateEmployeeDTO data){
		Optional<Employee> maybeUpdated = this.service.setById(id, data);
		
		if(maybeUpdated.isEmpty()) {
			throw new NotFoundException(String.format("Could not update post with id: %d post does not exist", id));
		}
		
		return new ResponseEntity<Employee>(maybeUpdated.get(), HttpStatus.OK);
	}
}
	
	

