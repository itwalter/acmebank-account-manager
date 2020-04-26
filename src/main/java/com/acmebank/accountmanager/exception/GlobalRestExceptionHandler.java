package com.acmebank.accountmanager.exception;

import java.util.LinkedList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.acmebank.accountmanager.entity.response.ErrorResponse;
import com.acmebank.accountmanager.entity.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.error(ex.getClass().getName(), ex);
		final List<String> errors = new LinkedList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		final Response response = new ErrorResponse(BusinessException.INVALID_METHOD_ARGUMENT.getCode(), ex.getMessage());
		return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		logger.error(ex.getClass().getName(), ex);
		final List<String> errors = new LinkedList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		final Response response = new ErrorResponse(BusinessException.BAD_REQUEST.getCode(), ex.getMessage());
		return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		logger.error(ex.getClass().getName(), ex);
		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
				+ ex.getRequiredType();

		final Response response = new ErrorResponse(BusinessException.TYPE_MISMATCH.getCode(), ex.getMessage());
		return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
			final WebRequest request) {
		logger.error(ex.getClass().getName(), ex);
		final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		final Response response = new ErrorResponse(BusinessException.METHOD_ARGUMENT_TYPE_MISMATCH.getCode(), ex.getMessage());
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex,
			final WebRequest request) {
		logger.error(ex.getClass().getName(), ex);
		//
		final List<String> errors = new LinkedList<String>();
		for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			logger.info(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
			errors.add(violation.getMessage());
		}
		final Response response = new ErrorResponse(BusinessException.CONSTRAINT_VIOLATION.getCode(), ex.getMessage());
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.error(ex.getClass().getName(), ex);
		final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
		final Response response = new ErrorResponse(BusinessException.NOT_FOUND.getCode(), ex.getMessage());
		return handleExceptionInternal(ex, response, headers, HttpStatus.NOT_FOUND, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		logger.error(ex.getClass().getName(), ex);
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		final Response response = new ErrorResponse(BusinessException.METHOD_NOT_ALLOWED.getCode(), ex.getMessage());
		return handleExceptionInternal(ex, response, headers, HttpStatus.METHOD_NOT_ALLOWED, request);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {

		logger.error(ex.getClass().getName(), ex);
		final Response response = new ErrorResponse(BusinessException.INTERNAL_ERROR.getCode(), ex.getMessage());
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
}
