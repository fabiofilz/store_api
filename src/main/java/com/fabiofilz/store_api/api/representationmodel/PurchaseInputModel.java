package com.fabiofilz.store_api.api.representationmodel;


import org.springframework.lang.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PurchaseInputModel {

  @NotBlank
  private String description;

  @NonNull
  private BigDecimal price;

  @Valid
  @NotNull
  private CustomerIdInput customer;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @NonNull
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(@NonNull BigDecimal price) {
    this.price = price;
  }

  public CustomerIdInput getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerIdInput customer) {
    this.customer = customer;
  }
}
