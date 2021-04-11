package com.cognizant.refill.exception;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandling {
	@ExceptionHandler(RefillIdNotpresentException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Map<String, Object> handleRefillNotFound(final RefillIdNotpresentException exception,final HttpServletRequest request){
		Map<String, Object> body= new LinkedHashMap<>();
		body.put("TimeStamp",new Date());
		body.put("status", HttpStatus.NOT_FOUND);
		body.put("error_code", 404);
		body.put("Message","Refill not found");
		return body;
		
	}
	
	@ExceptionHandler(SubscriptionNotfoundException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Map<String, Object> handleSubscriptionNotFound(final SubscriptionNotfoundException exception,final HttpServletRequest request){
		Map<String, Object> body= new LinkedHashMap<>();
		body.put("TimeStamp",new Date());
		body.put("status", HttpStatus.NOT_FOUND);
		body.put("error_code", 404);
		body.put("Message","Subscription not found");
		return body;
		
	}
}
