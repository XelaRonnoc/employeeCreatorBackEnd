package io.nology.employeeCreatorBackend.address;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.employeeCreatorBackend.employee.Employee;
import io.nology.employeeCreatorBackend.employee.UpdateEmployeeDTO;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressService {
	
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Optional<Address> findById(Long id) {
		return this.addressRepository.findById(id);
	}
	
	// this doesn't work
	public Optional<Address> setById(Long id, Address data){
		Optional<Address> maybeAddress= this.findById(id);
		if(maybeAddress.isPresent()) {
			Address existingAddress = maybeAddress.get();
			modelMapper.map(data,  existingAddress);
			return Optional.of(this.addressRepository.save(existingAddress));
		}
		
		return maybeAddress;
	}
}
