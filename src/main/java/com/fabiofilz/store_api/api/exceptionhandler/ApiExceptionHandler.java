package com.fabiofilz.store_api.api.exceptionhandler;

import com.fabiofilz.store_api.domain.exception.BusinessRulesException;
import com.fabiofilz.store_api.domain.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> EntityNotFoundExceptionHandler(BusinessRulesException ex, WebRequest request){
    var status = HttpStatus.NOT_FOUND;
    var errorMessage = new ErrorMessage(status.value(), OffsetDateTime.now(), ex.getMessage());

    return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(BusinessRulesException.class)
  public ResponseEntity<Object> BusinessRulesExceptionHandler(BusinessRulesException ex, WebRequest request){
    var status = HttpStatus.BAD_REQUEST;
    var errorMessage = new ErrorMessage(status.value(), OffsetDateTime.now(), ex.getMessage());

    return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
  }


  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    var fields = new ArrayList<ErrorMessage.Field>();

    for (ObjectError error : ex.getBindingResult().getAllErrors()){
      String name = ((FieldError) error).getField();
      //error.getDefaultMessage();
      String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

      fields.add(new ErrorMessage.Field(name, message));
    }

    var errorMessage = new ErrorMessage(status.value(), OffsetDateTime.now(), "Invalid Field(s)", fields);

    return super.handleExceptionInternal(ex, errorMessage, headers, status, request);
  }
}
