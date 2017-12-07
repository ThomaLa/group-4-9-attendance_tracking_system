package com.ase.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ase.entity.Student;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Key;

public class SecurityFilter implements Filter {

	FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest  servletRequest,ServletResponse  servletResponse, FilterChain filterChain)
			throws IOException, ServletException { 
		
		String thisUrl =  ((HttpServletRequest)servletRequest).getRequestURI();
		UserService userService = UserServiceFactory.getUserService();
		if (thisUrl.startsWith("/_ah")) {
			filterChain.doFilter(servletRequest, servletResponse); 
		}else{
			
			
		    if(((HttpServletRequest)servletRequest).getUserPrincipal() == null){
		    	servletResponse.getWriter()
		          .println(
		              "<p>Please <a href=\"" + userService.createLoginURL(thisUrl) + "\">sign in</a>.</p>");
		    }else{
				Student student = ObjectifyService.ofy().cache(false).load().key(Key.create( Student.class, userService.getCurrentUser().getEmail())).now();
				if(student==null){
					Student newStudent = new Student(userService.getCurrentUser().getEmail());
					ObjectifyService.ofy().cache(false).save().entities(newStudent).now();
				}
		    	filterChain.doFilter(servletRequest, servletResponse); 
		    }
		}
		
	}

}