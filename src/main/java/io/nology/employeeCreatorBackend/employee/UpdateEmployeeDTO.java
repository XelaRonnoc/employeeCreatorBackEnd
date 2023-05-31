package io.nology.employeeCreatorBackend.employee;

import java.util.Date;

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
	
	@Size(min = 1)
	@Pattern(regexp = "^(?=\\S).*$", message="Address cannot be and empty string")
	String address;
	
	Date startDate;
	
	Date endDate; // can be null meaning ongoing
	
	@Size(min = 1)
	@Pattern(regexp = "^(?=\\S).*$", message="contract type cannot be and empty string")
	String contractType;
	
	@Size(min = 1)
	@Pattern(regexp = "^(?=\\S).*$", message="contract time cannot be and empty string")
	String contractTime;
	
	Integer contractedHours;
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractTime() {
		return contractTime;
	}

	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}

	public Integer getContractedHours() {
		return contractedHours;
	}

	public void setContractedHours(Integer contractedHours) {
		this.contractedHours = contractedHours;
	}
}
