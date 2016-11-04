package com.full.lrms.pojo;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class LeaveApply {
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey
	Long Id;
	
	@Persistent 
	String name;

	@Persistent
	String email;
	
	@Persistent
	String SDate;
	
	@Persistent
	String EDate;
	
	@Persistent
	String leaveType;
	
	@Persistent
	String comments;
	
	@Persistent
	String userId;
	
	@Persistent
	String approval;
	
	@Persistent
	String team;
	
	
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSDate(String SDate) {
		this.SDate = SDate;
	}
	public void setEDate(String EDate) {
		this.EDate = EDate;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void setApproval(String approval){
		this.approval = approval;
	}
	
	public void setTeam(String team){
		this.team = team;
	}
	
	public String getName(){
		return name;
	}
	public String getEmail(){
		return email;
	}
	public String getSDate(){
		return SDate;
	}
	public String getEDate(){
		return EDate;
	}
	public String getLeaveType(){
		return leaveType;
	}
	public String getComments(){
		return comments;
	}
	public String getApproval(){
		return approval;
	}
	public String getTeam(){
		return team;
	}
	public String getUid(){
		return userId;
	}
}
