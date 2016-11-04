package com.full.lrms.main;

import java.io.IOException;
import java.net.UnknownHostException;
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
import org.springframework.web.servlet.ModelAndView;

import com.full.lrms.pojo.LeaveDetails;
import com.full.lrms.pojo.Profile;
import com.fullfun.Classes;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gson.Gson;


@Controller
@RequestMapping("")
public class PageHandler {
	String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public static String token;
	
	@RequestMapping(method= RequestMethod.GET, value="/UserView")
	public ModelAndView userView(HttpServletResponse resp,HttpServletRequest req,
			@CookieValue("access")String access,@CookieValue("email")String email,
			@CookieValue("team")String team,@CookieValue("uaccess")String uaccess) throws IOException, ArrayIndexOutOfBoundsException,UnknownHostException{
	
		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		resp.setDateHeader("Expires", 0); // Proxies.
		
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		token = channelService.createChannel(team);
		System.out.println(token);
		
		PersistenceManager pm= PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Profile.class);
		q.setFilter("_userAccess == '"+uaccess+"'"+"&&"+"email == '"+email+"'");
		try{
    		if(uaccess!=null && email.matches(EMAIL_REGEX) && access.equals("user")){
    			return new ModelAndView("user-page");
    		}else if (uaccess!=null && email.matches(EMAIL_REGEX) && access.equals("admin")){
    			return new ModelAndView("admin-page");	
    		}else if (uaccess!=null && email.matches(EMAIL_REGEX) && access.equals("manager")){	
    			return new ModelAndView("manager-page");
    		}else{
    			return new ModelAndView("index");
    		}
    	}catch(Exception e){
    		return new ModelAndView("index");
    	}	
	
	}
	
	@RequestMapping(method= RequestMethod.GET, value="/")
	public ModelAndView indexPage(HttpServletResponse resp,HttpServletRequest req) throws IOException, ArrayIndexOutOfBoundsException,UnknownHostException{
		
		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		resp.setDateHeader("Expires", 0); // Proxies.
		
		try{
    		Cookie ck[] = req.getCookies();
    		
    		String access = ck[1].getValue();
    		String email = ck[2].getValue();
    		String id = ck[3].getValue();
    		
    		System.out.println(id);
    		if((id!=null && email.matches(EMAIL_REGEX) && access.equals("admin"))){
    			return new ModelAndView("admin-page");
    		}else if(id!=null && email.matches(EMAIL_REGEX) && access.equals("manager")){
    			return new ModelAndView("admin-page");
    		}else if(id!=null && email.matches(EMAIL_REGEX) && access.equals("user")){
    			return new ModelAndView("user-page");	
    		}
    	}catch(Exception e){
    		return new ModelAndView("index");
    	}
		
		return new ModelAndView("index");
		
		
	}
	
	
	

	
	
}