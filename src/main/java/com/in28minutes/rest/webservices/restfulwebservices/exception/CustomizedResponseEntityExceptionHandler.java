package com.in28minutes.rest.webservices.restfulwebservices.exception;

import brave.Tracer;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String TRACE_ID = "|TraceID : ";
    private static final String SPAN_ID = "|SpanID : ";

    @Autowired
    private Tracer tracer;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetails errorDetails = getErrorDetails(ex, status);

        return new ResponseEntity<>(errorDetails, status);
    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetails errorDetails = getErrorDetails(ex, status);

        return new ResponseEntity<>(errorDetails, status);
    }

    private ErrorDetails getErrorDetails(Exception ex, HttpStatus status) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(status.toString());
        errorDetails.setReason(ex.getMessage());
        errorDetails.setMessage(
                TRACE_ID + tracer.currentSpan().context().traceIdString() +
                        SPAN_ID + tracer.currentSpan().context().spanIdString() + "|");
        errorDetails.setStatus(String.valueOf(status.value()));
        errorDetails.setType(ExceptionType.BUSINESS.toString());
        return errorDetails;
    }
}
