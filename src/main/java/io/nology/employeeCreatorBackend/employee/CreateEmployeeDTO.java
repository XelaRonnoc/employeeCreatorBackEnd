package io.nology.employeeCreatorBackend.employee;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateEmployeeDTO {
	
	@NotBlank
	String firstName;
	
	String middleName; // optional
	
	@NotBlank
	String lastName;
	
	@NotNull
	Date dateOfBirth;
	
	@NotBlank
	String email;
	
	@NotBlank
	String mobileNum;
	
	@NotBlank
	String address;
	
	@NotNull
	Date startDate;
	
	Date endDate; // can be null meaning ongoing
	
	@NotBlank
	String contractType;
	
	@NotBlank
	String contractTime;
	
	@NotNull
	Integer contractedHours;
	

	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
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
	public void setMobileNum(String mobile) {
		this.mobileNum = mobile;
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
	
}
