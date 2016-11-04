package com.full.lrms.main;


import com.full.lrms.pojo.Profile;
import com.fullfun.Classes;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("")
public class LoginLogout {
	String id,ck,name,access,team,uaccess;
	 
	@RequestMapping(method= RequestMethod.POST, value="/Login.do")
	public void signIn(HttpServletRequest req, HttpServletResponse resp,HttpSession session,@RequestParam("email") String email,@RequestParam("password")String password)throws IOException, ServletException,UnknownHostException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q= pm.newQuery(Profile.class);
		q.setFilter(" email == '" + email + "'"+ "&&" + " password == '" + Classes.safe(password) + "'" + "&&" + "status == '1'" );
		List<Profile> results = (List<Profile>) q.execute(email);
		if(!(results.isEmpty())){
			for(Profile e:results)
			{
				access = e.getAccess();
				email = e.getEmail();
				team =e.getTeam();
				uaccess = e._userAccess();
				
				
			}
			Cookie accessCookie=new Cookie("access", access);
			Cookie emailCookie=new Cookie("email", email);
			Cookie teamCookie=new Cookie("team", team);
			Cookie loginCookie=new Cookie("uaccess", uaccess);
			
			resp.addCookie(accessCookie);
			resp.addCookie(emailCookie);
			resp.addCookie(teamCookie);
			resp.addCookie(loginCookie);
			
			resp.sendRedirect("/UserView");
		}else{
			resp.sendRedirect("/");
		}
		
	}

	
	@RequestMapping(method= RequestMethod.GET, value="/Logout.do")
	public static void doLogout(HttpServletResponse resp,HttpServletRequest req) throws IOException,UnknownHostException{
		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		resp.setDateHeader("Expires", 0); // Proxies.
		
		Cookie ck2=new Cookie("access",null);Cookie ck1=new Cookie("email",null);Cookie ck=new Cookie("id",null);Cookie ck3=new Cookie("JSESSIONID",null);Cookie ck4= new Cookie("team",null);
		
		ck.setMaxAge(0);ck1.setMaxAge(0);ck2.setMaxAge(0);ck3.setMaxAge(0);ck4.setMaxAge(0);
        
        resp.addCookie(ck);resp.addCookie(ck1);resp.addCookie(ck2);resp.addCookie(ck3);resp.addCookie(ck4);
        
        resp.sendRedirect("/");
	}
	
	@RequestMapping(value="Signup.do",method={RequestMethod.POST})
	public void Signup(HttpServletRequest req, HttpServletResponse resp,HttpSession session,@RequestParam("fname") String fname,@RequestParam("lname")String lname,@RequestParam("email")String email,@RequestParam("password")String password, @RequestParam("empCode") String empCode,@RequestParam("empId")String empId,@RequestParam("dateOfJoin")String dateOfJoin,@RequestParam("phone")String phone,@RequestParam("team")String team,@RequestParam("role")String role,@RequestParam("dateOfBirth")String dateOfBirth,@RequestParam("panNo")String panNo,@RequestParam("gender")String gender,@RequestParam("accNum")String accNum,@RequestParam("status")String status,@RequestParam("reportTo")String reportTo,@RequestParam("access")String access)
	throws IOException, ServletException,UnknownHostException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q= pm.newQuery(Profile.class);
		q.setFilter("email == '" + email + "'");	
		List<Profile> results = (List<Profile>) q.execute(email);
		
		if(!(results.isEmpty()))
		{
			
			resp.sendRedirect("../Errorpage2");
			
		}	else{
		Profile r = new Profile();
		
		password= Classes.safe(password);
		r.setFirstName(fname);r.setLastName(lname);r.setEmpCode(empCode);r.setEmpId(empId);r.setDateOfJoin(dateOfJoin);r.setPhone(phone);r.setTeam(team);r.setRole(role);
		r.setDateOfBirth(dateOfBirth);r.setPanNo(panNo);r.setGender(gender);r.setAccNum(accNum);r.setEmail(email);r.setPassword(password);r.setStatus(status);
		r.setReport(reportTo);r.setAccess(access);
		
		try {
			pm.makePersistent(r);
			
		} finally {
			pm.close();
			}
		}
		
		this.signIn(req, resp, session, email, password);
   		
   }
	
	
}