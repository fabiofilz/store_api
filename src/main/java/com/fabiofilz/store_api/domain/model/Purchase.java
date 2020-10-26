package com.fabiofilz.store_api.domain.model;

import com.fabiofilz.store_api.domain.ValidationGroups;
import com.fabiofilz.store_api.domain.exception.BusinessRulesException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Purchase {

  // Keep the validation here as well in case there is a manipulation without using the API

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Valid
  @ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
  @ManyToOne
  @NotNull
  private Customer customer;

  @NotBlank
  private String description;

  @NotNull
  private BigDecimal price;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @Enumerated(EnumType.STRING)
  private StatusPurchase status;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private OffsetDateTime creationDate;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private OffsetDateTime endDate;

  @OneToMany(mappedBy = "purchase")
  private List<Review> reviews = new ArrayList<Review>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
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

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public boolean canBeClosed(){
    return !StatusPurchase.OPEN.equals(getStatus());
  }

  public void close() {
    if(canBeClosed()){
      throw new BusinessRulesException("Purchase cannot be closed");
    }

    setStatus(StatusPurchase.CLOSED);
    setEndDate(OffsetDateTime.now());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Purchase purchase = (Purchase) o;
    return Objects.equals(id, purchase.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
