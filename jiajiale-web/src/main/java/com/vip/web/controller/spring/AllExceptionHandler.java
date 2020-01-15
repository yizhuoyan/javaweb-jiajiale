package com.vip.web.controller.spring;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vip.dto.JSONResponse;
import com.vip.exception.VipException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler
    public @ResponseBody
    JSONResponse handle(BindException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        StringBuilder message=new StringBuilder();
        for(ObjectError error:allErrors){
            message.append(error.getDefaultMessage());
            message.append("\n");
        }
        return JSONResponse.fail(message.toString());
    }

    @ExceptionHandler
    public @ResponseBody
    JSONResponse handle(ConstraintViolationException e) {
        StringBuilder message=new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            message.append(constraintViolation.getMessage());
            message.append("\n");
        }

        return JSONResponse.fail(message.toString());
    }

	@ExceptionHandler
	public @ResponseBody
    JSONResponse handle(VipException e) {

	    return JSONResponse.fail(e.getMessage());
	}
	@ExceptionHandler
	public @ResponseBody
    JSONResponse handle(Exception e) {
		e.printStackTrace();
		return JSONResponse.fail("系统好累好累");
	}
}
