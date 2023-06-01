package io.nology.employeeCreatorBackend.employee;

import java.util.Date;

import io.nology.employeeCreatorBackend.address.Address;
import io.nology.employeeCreatorBackend.contract.Contract;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateEmployeeDTO {
	
	@Size(min = 1)
	@Pattern(regexp = "^(?=\\S).*$", message="First name cannot be and empty string")
	String firstName;
	
	String middleName; // optional
	
	@Size(min = 1)
	@Pattern(regexp = "^(?=\\S).*$", message="Last name cannot be and empty string")
	String lastName;
	
	Date dateOfBirth;
	
	@Size(min = 1)
	@Pattern(regexp = "^(?=\\S).*$", message="email cannot be and empty string")
	String email;
	
	@Size(min = 9)
	@Pattern(regexp = "^(?=\\S).*$", message="mobile number must be at least 9 character long")
	String mobileNum;
	
	Address address;
	
	Contract contract;
	
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
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

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

}
