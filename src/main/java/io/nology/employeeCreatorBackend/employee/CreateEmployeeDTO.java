package io.nology.employeeCreatorBackend.employee;

import java.time.LocalDate;




import io.nology.employeeCreatorBackend.address.Address;
import io.nology.employeeCreatorBackend.contract.Contract;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateEmployeeDTO {
	
	@NotBlank
	String firstName;
	
	String middleName; // optional
	
	@NotBlank
	String lastName;
	
	@NotNull
	LocalDate dateOfBirth;
	
	@NotBlank
	String email;
	
	@NotBlank
	String mobileNum;
	
	@NotNull
	Address address;
	
	@NotNull
	Contract contract;

	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobile) {
		this.mobileNum = mobile;
	}
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}

	
	
}
