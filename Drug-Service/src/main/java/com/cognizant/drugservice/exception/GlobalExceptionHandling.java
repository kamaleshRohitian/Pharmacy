package com.cognizant.drugservice.exception;



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
	@ExceptionHandler(DrugNotFoundException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Map<String, Object> handleDrugNotFound(final DrugNotFoundException exception,final HttpServletRequest request){
		Map<String, Object> body= new LinkedHashMap<String,Object>();
		body.put("TimeStamp",new Date());
		body.put("status", HttpStatus.NOT_FOUND);
		body.put("error_code", 404);
		body.put("Message","Drug not found");
		return body;
		
	}

}
