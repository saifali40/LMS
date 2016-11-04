package com.full.lrms.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import javax.jdo.JDOFatalUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.datanucleus.exceptions.NucleusFatalUserException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.lrms.pojo.LeaveApply;
import com.full.lrms.pojo.LeaveDetails;
import com.full.lrms.pojo.Profile;
import com.fullfun.Classes;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;

@Controller
@RequestMapping("")
public class LeaveDetailsClass {
	
	
	@RequestMapping(method=RequestMethod.GET, value="/Leave")
    @ResponseBody
	 public String displayData(HttpServletRequest request,HttpServletResponse resp, ModelMap model,@CookieValue("access")String access,@CookieValue("email")String email,@CookieValue("uaccess")String uaccess) throws IOException {
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(LeaveDetails.class);
		q.setFilter("email == '"+email+"'"+"&&"+"userId =='"+uaccess+"'");
		List<LeaveDetails> list = (List<LeaveDetails>)q.execute();
		Gson obj = new Gson();
		String retVal = obj.toJson(list);
		return retVal;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/Status")
	@ResponseBody
	public String displayLeaveData(HttpServletRequest req,HttpServletResponse resp, ModelMap model,@CookieValue("access")String access,@CookieValue("email")String email,@CookieValue("uaccess")String uaccess) throws IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(LeaveApply.class);
		q.setFilter("userId == '"+uaccess+"'"+"&&"+"email == '"+email+"'");
		List<LeaveApply> list = (List<LeaveApply>)q.execute();
		Gson obj = new Gson();
		String retVal = obj.toJson(list);
		return retVal;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/TeamLeavestatus")
	@ResponseBody
	public String displayTeamLeaveData(HttpServletRequest req,HttpServletResponse resp, ModelMap model,@CookieValue("access")String access,@CookieValue("email")String email,@CookieValue("uaccess")String uaccess,@CookieValue("team")String team) throws IOException {
		if(!(access.equals("manager") && email !=null && uaccess != null))
		{
			resp.sendRedirect("/");
		}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query p = pm.newQuery(Profile.class);
		p.setFilter("team == '"+team+"' && email == '"+email+"' && _userAccess == '"+uaccess+"'");
		List<Profile> check = (List<Profile>) p.execute();
		if(!(check.isEmpty())){
			Query q = pm.newQuery(LeaveApply.class);
			q.setFilter("team == '"+team+"'");
			List<LeaveApply> list = (List<LeaveApply>)q.execute();
			Gson obj = new Gson();
			String retVal = obj.toJson(list);
			return retVal;	
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/ApplyLeave.do/{id}")
	@ResponseBody
	public String takeLeave(@PathVariable String id,ModelMap model,@CookieValue("team")String team)throws IOException, ArrayIndexOutOfBoundsException{
		System.out.println(id);
		Long key = Long.parseLong(id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
			
		Query p = pm.newQuery(Profile.class);
		p.setFilter("team == '"+team+"'");
		List<Profile> verify = (List<Profile>) p.execute();
		if(!(verify.isEmpty())){
			Query q = pm.newQuery(LeaveApply.class);
			q.setFilter("Id == idParameter");
			q.declareParameters("String idParameter");
			List<LeaveApply> list= (List<LeaveApply>) q.execute(key);
			LeaveApply	dobj	=	(LeaveApply)list.get(0);
			Gson obj = new Gson();
			String retVal = obj.toJson(dobj);
			return retVal;
			
		}else{
			return null;
		}
		
	}
	@RequestMapping(method=RequestMethod.PUT, value="/ApplyLeave.do/{id}")
	public void updateUser(HttpServletRequest request, HttpServletResponse resp,@PathVariable String id,@CookieValue("email")String who) throws IOException{
		
		String k=id;
		long key=Long.parseLong(k);
		int Days = 0;
		PersistenceManager pm= PMF.get().getPersistenceManager();
			
			try{
			StringBuilder buffer = new StringBuilder();
		    BufferedReader reader = request.getReader();
		    String line;
		    
		    while ((line = reader.readLine()) != null) {
		        buffer.append(line);
		    }
		    String data = buffer.toString();
		    

		    JSONObject JSON = null;
			try {
				JSON = new JSONObject(data);
				String Status=JSON.getString("Status");
				Days = (int) JSON.getLong("days");
				String type = JSON.getString("leaveType");
				String Sdate = JSON.getString("Sdate");
				String comments = JSON.getString("comments");
				LeaveApply c = pm.getObjectById(LeaveApply.class, key);
				c.setApproval(Status);
				c.setComments(comments);
				String access = c.getUid();
				String email = c.getEmail();
				com.fullfun.Classes.mail(comments,Status,who,email);
				System.out.println("1");
				if(Status.equals("Approved")){
					System.out.println("Approved");
					System.out.println(email);
					System.out.println(access);
					com.fullfun.Classes.leaveTaken(access, Days, type,email,Sdate);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			catch(Exception e){
				System.out.println(e);
			}

    }
	
	@RequestMapping(method=RequestMethod.POST, value="/ApplyLeave.do")
	public void applyLeave(HttpServletResponse resp,HttpServletRequest req,
			@CookieValue("uaccess")String access,@CookieValue("email")String email,@CookieValue("team")String team)throws IOException, ArrayIndexOutOfBoundsException,NucleusFatalUserException,JDOFatalUserException{
			
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		
		PersistenceManager pm= PMF.get().getPersistenceManager();
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String input = buffer.toString();
		JSONObject JSON = null;
		try{
			JSON = new JSONObject(input);
			String Sdate = JSON.getString("Sdate");
			String Edate = JSON.getString("Edate");
			String type = JSON.getString("leaveType");
			String comments = JSON.getString("comments");
			String status= "submited";
			System.out.println(" access: "+access+" email: "+email+" sdate: "+Sdate+" edate: "+Edate+" type: "+type);
			Query q= pm.newQuery(LeaveApply.class);
			q.setFilter("userId == '"+access+"'"+"&&"+ "SDate == '"+Sdate+"'"+"&&"+"leaveType == '"+type+"'");
			List <LeaveApply> results = (List<LeaveApply>) q.execute();
			if(!(results.isEmpty())){
				
			}else{
				LeaveApply r = new LeaveApply();
				r.setEDate(Edate);
				r.setSDate(Sdate);
				r.setLeaveType(type);
				r.setEmail(email);
				r.setUserId(access);
				r.setComments(comments);
				r.setApproval(status); 
				r.setTeam(team);
				pm.makePersistent(r);
				pm.close();
				Gson obj = new Gson();
				String retVal = obj.toJson(r);
			}
		}catch (JSONException e) {
			e.printStackTrace();
		} finally{
			
		}
	}
	
}
