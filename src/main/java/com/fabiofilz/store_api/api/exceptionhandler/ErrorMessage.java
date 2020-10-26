package com.fabiofilz.store_api.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

  private Integer status;
  private OffsetDateTime dateTime;
  private String description;
  private List<Field> fields;

  public ErrorMessage(Integer status, OffsetDateTime dateTime, String description, List<Field> fields) {
    this.status = status;
    this.dateTime = dateTime;
    this.description = description;
    this.fields = fields;
  }

  public ErrorMessage(Integer status, OffsetDateTime dateTime, String description) {
    this.status = status;
    this.dateTime = dateTime;
    this.description = description;
  }

  public static class Field {
    private String name;
    private String message;

    public Field(String name, String message) {
      this.name = name;
      this.message = message;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public OffsetDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(OffsetDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Field> getFields() {
    return fields;
  }

  public void setFields(List<Field> fields) {
    this.fields = fields;
  }
}
