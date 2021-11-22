package com.ml.survey.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ml.survey.common.ApplicationErrorCode;
import com.ml.survey.error.APIError;
import com.ml.survey.exception.ApplicationException;

/**
 * This Class converts the exception thrown in any layer converts them 
 * into APIError.
 * 
 * @author Kaustuv Maji
 */
@ControllerAdvice
public class ExceptionAdvice {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public APIError genericException(Exception e) {
		LOGGER.error(e.getMessage(), e);
		APIError res = new APIError();
		res.setCode(ApplicationErrorCode.SYSTEM_EXCEPTION_CODE.label);
		res.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		res.setDescription(e.getMessage());
		return res;
	}
	
	
	@ResponseBody
	@ExceptionHandler(ApplicationException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public APIError businessException(ApplicationException e) {
		LOGGER.error(e.getMessage(), e);
		APIError res = new APIError();
		res.setCode(e.getCode());
		res.setMessage((HttpStatus.NOT_FOUND.getReasonPhrase()));
		res.setDescription(e.getMessage());
		return res;
	}
}
