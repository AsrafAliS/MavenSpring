package com.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	/*
	Id Auto Generated
	employeeName
	employeeAddress
	employeePhone
	employeeSalar
	*/
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String employeename;
	private String employeeaddress;
	private String employeephone;
	private String employeeSalary;
	
	public Employee() {
		
		
	}
	
	
	
	
	
	public Employee(int id, String employeename, String employeeaddress, String employeephone, String employeeSalary) {
		super();
		this.id = id;
		this.employeename = employeename;
		this.employeeaddress = employeeaddress;
		this.employeephone = employeephone;
		this.employeeSalary = employeeSalary;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getEmployeename() {
		return employeename;
	}




	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}




	public String getEmployeeaddress() {
		return employeeaddress;
	}




	public void setEmployeeaddress(String employeeaddress) {
		this.employeeaddress = employeeaddress;
	}




	public String getEmployeephone() {
		return employeephone;
	}




	public void setEmployeephone(String employeephone) {
		this.employeephone = employeephone;
	}




	public String getEmployeeSalary() {
		return employeeSalary;
	}




	public void setEmployeeSalary(String employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	
	

}
