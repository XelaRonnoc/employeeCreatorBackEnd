package io.nology.employeeCreatorBackend.contract;



import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "contracts")
public class Contract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Integer contractedHours;
	
	@Column
	private String contractTime;
	
	@Column
	private String contractType;
	
	@Column
	@NotNull
	private LocalDate startDate;

	@Column
	private LocalDate endDate;
	
	
	public Contract() {}


	public Contract(Integer contractedHours, String contractTime, String contractType, LocalDate startDate, LocalDate endDate) {
		super();
		this.contractedHours = contractedHours;
		this.contractTime = contractTime;
		this.contractType = contractType;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}


	public Integer getContractedHours() {
		return contractedHours;
	}


	public void setContractedHours(Integer contractedHours) {
		this.contractedHours = contractedHours;
	}


	public String getContractTime() {
		return contractTime;
	}


	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}


	public String getContractType() {
		return contractType;
	}


	public void setContractType(String contractType) {
		this.contractType = contractType;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	
}
