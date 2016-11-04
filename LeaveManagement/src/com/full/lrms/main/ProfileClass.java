package com.full.lrms.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.lrms.pojo.Profile;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;

@Controller
@RequestMapping("")

public class ProfileClass {

	@RequestMapping(method=RequestMethod.GET, value="/Profile")
    @ResponseBody
	 public String displayData(HttpServletRequest request,HttpServletResponse resp, ModelMap model,HttpSession session,
			 @CookieValue("access")String access,@CookieValue("email")String email,
				@CookieValue("team")String team,@CookieValue("uaccess")String uaccess) throws IOException {
		
			PersistenceManager pm= PMF.get().getPersistenceManager();
			Query q = pm.newQuery(Profile.class);
			q.setFilter("_userAccess == '"+uaccess+"'"+"&&"+"email == '"+email+"'");
			List<Profile> list= (List<Profile>) q.execute();
			Gson obj = new Gson();
			String retVal = obj.toJson(list);
			return retVal;
	}
	@RequestMapping(method=RequestMethod.GET, value="/ProfileManager")
    @ResponseBody
	 public String displayDataManager(HttpServletRequest req,HttpServletResponse resp, ModelMap model,HttpSession session,@CookieValue("access")String access,@CookieValue("email")String email,
				@CookieValue("team")String team,@CookieValue("uaccess")String uaccess) throws IOException {
		
		if(!(access.equals("manager") && email !=null && uaccess != null))
		{
			resp.sendRedirect("/");
		}
		PersistenceManager pm= PMF.get().getPersistenceManager();
		Query p = pm.newQuery(Profile.class);
		p.setFilter("team == '"+team+"' && email == '"+email+"' && _userAccess == '"+uaccess+"'");
		List<Profile> check = (List<Profile>) p.execute();
		if(!(check.isEmpty())){
			Query q = pm.newQuery(Profile.class);
			q.setFilter("team == '"+team+"'");
			List<Profile> list= (List<Profile>) q.execute();
			Gson obj = new Gson();
			String retVal = obj.toJson(list);
			return retVal;	
		}
		return null;
		
	}
	@RequestMapping(method=RequestMethod.GET, value="/ProfileAdmin")
    @ResponseBody
	 public String displayDataAdmin(HttpServletRequest request,HttpServletResponse resp, ModelMap model,HttpSession session,@CookieValue("access")String access,@CookieValue("email")String email,
				@CookieValue("team")String team,@CookieValue("uaccess")String uaccess) throws IOException {
		
		if(!(access.equals("admin") && email !=null && uaccess != null))
		{
			resp.sendRedirect("/");
		}
		
		PersistenceManager pm= PMF.get().getPersistenceManager();
		
			Query q = pm.newQuery(Profile.class);
			List<Profile> list= (List<Profile>) q.execute();
			Gson obj = new Gson();
			String retVal = obj.toJson(list);
			return retVal;
	}
	
	
	
	@RequestMapping(method= RequestMethod.POST, value="/AddUser")
	public void addEntry(HttpServletRequest request, HttpServletResponse resp, ModelMap model,HttpSession session) throws IOException {
		
		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		resp.setDateHeader("Expires", 0); // Proxies.
		
		Cookie ck[] = request.getCookies();
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		
		PersistenceManager pm= PMF.get().getPersistenceManager();
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		
		String input = buffer.toString();
		JSONObject JSON = null;
		try{
			
			JSON = new JSONObject(input);
			
			String fname=JSON.getString("fname");String lname=JSON.getString("lname");String empCode=JSON.getString("empCode");String empId=JSON.getString("empId");
			String dOJ=JSON.getString("dOJ");String phone=JSON.getString("phone");String team=JSON.getString("team");String role=JSON.getString("role");
			String dOB=JSON.getString("dOB");String panNo=JSON.getString("panNo");String gender=JSON.getString("gender");String accNo=JSON.getString("accNo");
			String email=JSON.getString("email");String password=JSON.getString("password");String status=JSON.getString("status");String reportTo=JSON.getString("reportTo");
			String access=JSON.getString("access");
			
			Query q= pm.newQuery(Profile.class);
			q.setFilter("email == '"+email+"'");
			List<Profile> results = (List<Profile>) q.execute(ck[3].getValue());
			if(!(results.isEmpty()))
			{
				

			}else{
			Profile r=new Profile();
			r.setFirstName(fname);r.setLastName(lname);r.setEmpCode(empCode);r.setEmpId(empId);r.setDateOfJoin(dOJ);r.setPhone(phone);r.setTeam(team);r.setRole(role);
			r.setDateOfBirth(dOB);r.setPanNo(panNo);r.setGender(gender);r.setAccNum(accNo);r.setEmail(email);r.setPassword(password);r.setStatus(status);
			r.setReport(reportTo);r.setAccess(access);
			
			pm.makePersistent(r);
			pm.close();
			Gson obj = new Gson();
			String retVal = obj.toJson(r);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally{
		}
	}
}
