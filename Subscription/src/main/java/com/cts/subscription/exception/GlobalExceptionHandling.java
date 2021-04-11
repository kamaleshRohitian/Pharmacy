package com.cts.subscription.exception;

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
	private final static String TIMESTAMP = "timestamp";
	private final static String STATUS = "status";
	private final static String ERROR_CODE = "errorCode";
	private final static String Message = "message";

	@ExceptionHandler(PrescriptionException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Map<String, Object> handlePrescriptionNotFound(final PrescriptionException exception,
			final HttpServletRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, new Date());
		body.put(STATUS, "ID Already Present");
		body.put(ERROR_CODE, 404);
		body.put(Message, "Prescription ID already Present");
		return body;

	}

	@ExceptionHandler(refillordernullException.class)
	public @ResponseBody Map<String, Object> handleRefillNotFounds(final refillordernullException exception,
			final HttpServletRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, new Date());
		body.put(STATUS, "refill not present");
		body.put(ERROR_CODE, 404);
		body.put(Message, "Refill order null.please check the credentials");
		return body;
	}

	@ExceptionHandler(drugnotfoundexception.class)
	public @ResponseBody Map<String, Object> handleDrugNotFounds(final drugnotfoundexception exception,
			final HttpServletRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, new Date());
		body.put(STATUS, "Drug Name Not Present");
		body.put(ERROR_CODE, 404);
		body.put(Message, "Drug Name Not available");
		return body;

	}

	@ExceptionHandler(PrescriptionListException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Map<String, Object> handleDrugNotFound(final PrescriptionListException exception,
			final HttpServletRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, new Date());
		body.put(STATUS, "Prescription list empty");
		body.put(ERROR_CODE, 404);
		body.put(Message, "Prescription List is empty");
		return body;

	}

	@ExceptionHandler(SubscriptionListEmptyException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Map<String, Object> handlesubscriptionNotFound(final SubscriptionListEmptyException exception,
			final HttpServletRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(TIMESTAMP, new Date());
		body.put(STATUS, "Subscription list empty");
		body.put(ERROR_CODE, 404);
		body.put(Message, "Subscription list is empty");
		return body;

	}
}
