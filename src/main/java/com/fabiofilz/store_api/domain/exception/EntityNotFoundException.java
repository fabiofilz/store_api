package com.fabiofilz.store_api.domain.exception;

public class EntityNotFoundException extends BusinessRulesException{

  private static final long serialVersionUID =1L;

  public EntityNotFoundException(String message) {
    super(message);
  }
}
