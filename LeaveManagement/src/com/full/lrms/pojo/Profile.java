package com.full.lrms.pojo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Profile {
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey
	Long id;
	
	@Persistent
	String firstName;
	
	@Persistent
	String lastName;
	
	@Persistent
	String empCode;
	
	@Persistent
	String empId;
		
	@Persistent
	String dateOfJoin;
	
	@Persistent 
	String phone;
	
	@Persistent
	String team;
	
	@Persistent 
	String role;
	
	@Persistent
	String dateOfBirth;
	
	@Persistent
	String panNo;
	
	@Persistent
	String gender;
	
	@Persistent
	String accNum;
	
	@Persistent
	String email;

	@Persistent
	String password;
	
	@Persistent
	String status;
	
	@Persistent
	String reportTo;
	
	@Persistent
	String access;
	
	@Persistent(valueStrategy = IdGeneratorStrategy.UUIDHEX)
	String _userAccess;
	
// -- setter
	
	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
	public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
    
    public void setEmpId(String empId) {
        this.empId = empId;
    }
    
    public void setDateOfJoin(String dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }
    
	public void setEmail(String email) {
        this.email = email;
    }
    
	public void setPassword(String password) {
        this.password = password;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setReport(String reportTo) {
        this.reportTo = reportTo;
    }
    public void setAccess(String access) {
        this.access = access;
    }
    
// getter   
    public Long getId() {
		return id;
	}
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
	
    public String getEmpCode() {
        return empCode;
    }
    
    public String getEmpId() {
        return empId;
    }

    public String getDateOfJoin() {
        return dateOfJoin;
    }
    
    public String getPhone() {
        return phone;
    }
	
    public String getTeam() {
        return team;
    }
    
    public String getRole() {
        return role;
    }
    
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public String getPanNo() {
        return panNo;
    }
	
    public String getGender() {
        return gender;
    }
    
    public String getAccNum() {
        return accNum;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
	
    public String getStatus() {
        return status;
    }
    
    public String getReportTo() {
        return reportTo;
    }
    
    public String getAccess() {
        return access;
    }
    
    public String _userAccess(){
    	return _userAccess;
    }
    
	public Profile(){
		super();
	}

}
