package com.fullfun;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.full.lrms.main.PMF;
import com.full.lrms.pojo.LeaveDetails;

public class Classes {
public static String safe(String input) {
		
		String crypt = null;
		
		if(null == input) return null;
		
		try {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");//MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.update(input.getBytes(), 0, input.length());
		crypt = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return crypt;
	}

public static void leaveTaken(String userId, Integer Days, String leaveType,String email,String Sdate){
	System.out.println("Hello World");
	PersistenceManager pm= PMF.get().getPersistenceManager();
	Integer remianining;
	
	java.util.Date date= new Date();
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	int entitledLeave = cal.get(Calendar.MONTH);
	
	
	Query q = pm.newQuery(LeaveDetails.class);
	
	q.setFilter("userId == '"+userId+"' && " + "leaveType == '"+leaveType+"'");
	List<LeaveDetails> results = (List<LeaveDetails>) q.execute();
	
	if(!(results.isEmpty())){
		System.out.println("hooo ha");
		
		LeaveDetails c = pm.getObjectById(LeaveDetails.class, userId);
		System.out.println(c.getLeaveRemaining());
		
	}
	
	if((results.isEmpty()))
	{
		remianining = entitledLeave-Days;
		LeaveDetails r = new LeaveDetails();
		r.setUserId(userId);
		r.setLeaveTaken(Days);
		r.SetLeaveType(leaveType);
		r.setEntitledLeave(entitledLeave);
		r.setRemaining(remianining);
		r.setEmail(email);
		r.setSDate(Sdate);
		try {
			pm.makePersistent(r);
			
		} finally {
			pm.close();
			}
		
		}
	
	}


	public static void mail(String comments, String Status, String who,String email) {

    String to = email;

    String from = "saif.ali@a-cti.com";

    String host = "smtp.gmail.com";

    Properties properties = System.getProperties();

    properties.setProperty("mail.smtp.host", host);

    Session session = Session.getDefaultInstance(properties);

    try{

       MimeMessage message = new MimeMessage(session);


       message.setFrom(new InternetAddress(from));


       message.addRecipient(Message.RecipientType.TO,
                                new InternetAddress(to));


       message.setSubject("Your leave Request"+Status);
       message.setContent("Hi,<br> This is "+who+" your Leave Request Has been "+Status+" "+comments+"<br />Thank you <br>"+who, "text/html; charset=utf-8");
       
       Transport.send(message);
       System.out.println(message);
    }catch (MessagingException mex) {
       mex.printStackTrace();
    }
 }


}
