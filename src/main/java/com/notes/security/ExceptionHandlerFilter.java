package com.notes.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notes.exceptions.UnauthorizedUserException;
import com.notes.integrations.Response;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (UnauthorizedUserException e) {
			log.info("UnauthorizedUserException was throwed. {}", e.getMessage());
			Response<String> res = new Response<String>();
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			out.print(mapper.writeValueAsString(res));
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} catch (Exception e) {
			log.info("Exception. {}", e.getMessage());
		}
	}

}
