package io.nology.employeeCreatorBackend.employee;

import java.util.Date;

import io.nology.employeeCreatorBackend.address.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// the rest of the properties added will be extra cols in db
	
	@Column
	private String firstName;
	
	@Column
	private String middleName;
	
	@Column
	private String lastName;
	
	@Column
	private Date dateOfBirth;
	
	@Column
	private String email;
	
	@Column
	private String mobileNum;
	
//	@Column
//	private String address; // may be worth splitting into each part of address and or storing in own table
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@Column
	private Date startDate;
	
	@Column
	private Date endDate;
	
	@Column
	private String contractType;
	
	@Column
	private String contractTime;
	
	@Column
	private Integer contractedHours;
	
	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Employee() {}
	
	public Employee(String firstName, String middleName, String lastName, String email, String mobileNum,
			Address address, Date dateOfBirth, Date startDate, Date endDate, String contractType, String contractTime,
			Integer contractedHours) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNum = mobileNum;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.startDate = startDate;
		this.endDate = endDate;
		this.contractType = contractType;
		this.contractTime = contractTime;
		this.contractedHours = contractedHours;
	}


	
	
	
}
