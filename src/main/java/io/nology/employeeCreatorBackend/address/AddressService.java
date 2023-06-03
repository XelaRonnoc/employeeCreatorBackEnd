package io.nology.employeeCreatorBackend.address;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressService {
	
	
	@Autowired
	private AddressRepository addressRepository;
	
	
	public Optional<Address> findById(Long id) {
		return this.addressRepository.findById(id);
	}
	
}
