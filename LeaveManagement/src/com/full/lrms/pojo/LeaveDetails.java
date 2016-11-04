package com.full.lrms.pojo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.full.lrms.main.PMF;

@PersistenceCapable
public class LeaveDetails {

	
	@PrimaryKey
	@Persistent
	String userId;

	@Persistent
	String leaveType;

	@Persistent
	String email;

	@Persistent
	Integer entitledLeave;

	@Persistent
	Integer leaveTaken;

	
	@Persistent
	Integer leaveRemaining;
	
	@Persistent
	String SDate;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void SetLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public void setEntitledLeave(Integer entitledLeave) {
			this.entitledLeave = entitledLeave;
	}

	public void setLeaveTaken(Integer leaveTaken) {		
			this.leaveTaken = leaveTaken;			
	}
	
	
	public void setRemaining(Integer leaveRemaining){
		this.leaveRemaining = leaveRemaining;
	}
	
	public void setSDate(String SDate) {
		this.SDate = SDate;
	}

	
	public String getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public Integer getEntitledLeave() {
		return entitledLeave;
	}

	public Integer getLeaveTaken() {
		return leaveTaken;
	}

	
	public Integer getLeaveRemaining() {
		return leaveRemaining;
	}
	
	public String getSDate(){
		return SDate;
	}
	
	public LeaveDetails() {
		super();
	}

}
