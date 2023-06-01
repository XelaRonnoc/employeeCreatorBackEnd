package io.nology.employeeCreatorBackend.contract;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.employeeCreatorBackend.address.AddressRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContractService {
	
	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired
	private ModelMapper modelMapper;
}
