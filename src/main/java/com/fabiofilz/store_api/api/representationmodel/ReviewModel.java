package com.fabiofilz.store_api.api.representationmodel;

import java.time.OffsetDateTime;

public class ReviewModel {

  private Long id;
  private String description;
  private OffsetDateTime creationDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }
}
