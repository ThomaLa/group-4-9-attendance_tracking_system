package com.ase.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ase.service.StudentTutorManagementService;
import com.ase.service.impl.StudentTutorManagementServiceImpl;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class SecurityFilter implements Filter {

	FilterConfig filterConfig = null;
	StudentTutorManagementService studentTutorService = new StudentTutorManagementServiceImpl();
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		String thisUrl = ((HttpServletRequest) servletRequest).getRequestURI();
		UserService userService = UserServiceFactory.getUserService();
		if (thisUrl.startsWith("/_ah")) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else if (thisUrl.startsWith("/tutor")) {
			
			if (((HttpServletRequest) servletRequest).getUserPrincipal() == null) {
				servletResponse.getWriter()
						.println("<p>Please <a href=\"" + userService.createLoginURL(thisUrl) + "\">sign in</a>.</p>");
			} else {
				
				if(!userService.isUserAdmin()){
					((HttpServletResponse)servletResponse).setStatus(HttpServletResponse.SC_FORBIDDEN);
					servletResponse.getWriter()
					.println("<p>Access Denied.</p><p>Please login with appropriate credentials !<a href=\"" + userService.createLoginURL(thisUrl) + "\">sign in</a>.</p>");
				}else{		
					studentTutorService.createTutor( userService.getCurrentUser().getEmail());
					filterChain.doFilter(servletRequest, servletResponse);
				}
			}
			
		} else if (thisUrl.startsWith("/api")) {
			filterChain.doFilter(servletRequest, servletResponse);
		}else {

			if (((HttpServletRequest) servletRequest).getUserPrincipal() == null) {
				servletResponse.getWriter()
						.println("<p>Please <a href=\"" + userService.createLoginURL(thisUrl) + "\">sign in</a>.</p>");
			} else {
				studentTutorService.createStudent(userService.getCurrentUser().getEmail());
				filterChain.doFilter(servletRequest, servletResponse);
			}
		}

	}

}