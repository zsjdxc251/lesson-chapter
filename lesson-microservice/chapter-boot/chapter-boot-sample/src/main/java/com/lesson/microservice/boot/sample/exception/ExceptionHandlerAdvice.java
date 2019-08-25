package com.lesson.microservice.boot.sample.exception;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * @author zhengshijun
 * @version created on 2019/3/28.
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {


	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handler(HttpServletRequest request, HttpServletResponse response, Throwable ex) {
		log.error(request.getRequestURL().toString(), ex);

		return ResponseEntity.status(response.getStatus()).body(ex.getMessage());
	}


	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body,
			HttpHeaders headers, HttpStatus status, @Nullable WebRequest request) {


		if (ex instanceof BindException){
			BindException exception = BindException.class.cast(ex);

			BindingResult result = exception.getBindingResult();
			List<ObjectError> list = result.getAllErrors();

			Map<String,String> errorMessage = Maps.newHashMap();

			list.forEach(item->{
				errorMessage.put(item.getObjectName(),item.getDefaultMessage());

			});


			return new ResponseEntity<>(errorMessage, headers, status);


		}
		log.error(StringUtils.EMPTY, ex);


		return new ResponseEntity<>(ex.getMessage(), headers, status);
	}

}
