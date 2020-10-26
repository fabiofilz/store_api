package com.fabiofilz.store_api.api.representationmodel;

import javax.validation.constraints.NotBlank;

public class ReviewInputModel {

  @NotBlank
  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
