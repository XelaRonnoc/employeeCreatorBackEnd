package io.nology.employeeCreatorBackend.employee;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.employeeCreatorBackend.address.Address;
import io.nology.employeeCreatorBackend.address.AddressRepository;
import io.nology.employeeCreatorBackend.contract.Contract;
import io.nology.employeeCreatorBackend.contract.ContractRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired 
	private ContractRepository contractRepository;
	
	public Employee create(CreateEmployeeDTO data) {
		// turn dto into and employee
		
		Employee newEmployee = modelMapper.map(data,  Employee.class);
		Address newAddress = modelMapper.map(data.address, Address.class); 
		Contract newContract = modelMapper.map(data.contract, Contract.class);
		
		this.contractRepository.save(newContract);
		this.addressRepository.save(newAddress);
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
	
	public Optional<Employee> updateById(Long id, UpdateEmployeeDTO data){
		Optional<Employee> maybeEmployee = this.findById(id);
		if(maybeEmployee.isPresent()) {
			Employee existingEmployee = maybeEmployee.get();
			modelMapper.map(data, existingEmployee);
			return Optional.of(this.repository.save(existingEmployee));
		}
		
		return maybeEmployee;
	}
	
	public Optional<Employee> setById(Long id, UpdateEmployeeDTO data){
		Optional<Employee> maybeEmployee = this.findById(id);
		if(maybeEmployee.isPresent()) {
			Employee existingEmployee = maybeEmployee.get();
			modelMapper.map(data,  existingEmployee);
			return Optional.of(this.repository.save(existingEmployee));
		}
		
		return maybeEmployee;
	}
}
