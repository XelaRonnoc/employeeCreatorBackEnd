package io.nology.employeeCreatorBackend.employee;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Employee create(CreateEmployeeDTO data) {
		// turn dto into and employee
		
		Employee newEmployee = modelMapper.map(data,  Employee.class);
		
		return this.repository.save(newEmployee);
	}
	
	public List<Employee> findAll() {
		return this.repository.findAll();
	}
	
	
	public Optional<Employee> findById(Long id) {
		return this.repository.findById(id);
	}
	
	public boolean deleteById(Long id) {
		Optional<Employee> maybeEmployee = this.findById(id);
		if(maybeEmployee.isPresent()) {
			this.repository.delete(maybeEmployee.get());
			return true;
		}
		return false;
	}
}
