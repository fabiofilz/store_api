package com.fabiofilz.store_api.api.representationmodel;

import com.fabiofilz.store_api.domain.model.StatusPurchase;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class PurchaseModel {

  private Long purchaseId;
  private CustomerModel customer;
  private String description;
  private BigDecimal price;
  private StatusPurchase status;
  private OffsetDateTime creationDate;
  private OffsetDateTime endDate;

  public Long getPurchaseId() {
    return purchaseId;
  }

  public void setPurchaseId(Long purchaseId) {
    this.purchaseId = purchaseId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public StatusPurchase getStatus() {
    return status;
  }

  public void setStatus(StatusPurchase status) {
    this.status = status;
  }

  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public OffsetDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(OffsetDateTime endDate) {
    this.endDate = endDate;
  }

  public CustomerModel getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerModel customer) {
    this.customer = customer;
  }
}
