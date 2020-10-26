package com.fabiofilz.store_api.api.representationmodel;

import javax.validation.constraints.NotNull;

public class CustomerIdInput {

  @NotNull
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
