package com.fabiofilz.store_api.domain.service;

import com.fabiofilz.store_api.domain.exception.BusinessRulesException;
import com.fabiofilz.store_api.domain.exception.EntityNotFoundException;
import com.fabiofilz.store_api.domain.model.Customer;
import com.fabiofilz.store_api.domain.model.Purchase;
import com.fabiofilz.store_api.domain.model.Review;
import com.fabiofilz.store_api.domain.model.StatusPurchase;
import com.fabiofilz.store_api.domain.repository.CustomerRepository;
import com.fabiofilz.store_api.domain.repository.PurchaseRepository;
import com.fabiofilz.store_api.domain.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class PurchaseManagerService {

  @Autowired
  private PurchaseRepository purchaseRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private ReviewRepository reviewRepository;

  public Purchase create(Purchase purchase){
    Customer customer = customerRepository.findById(purchase.getCustomer().getId())
        .orElseThrow(() -> new BusinessRulesException("Customer not found"));

    //purchase.setCustomer(customer);
    purchase.setStatus(StatusPurchase.OPEN);
    purchase.setCreationDate(OffsetDateTime.now());

    return purchaseRepository.save(purchase);

  }

  public void closePurchase(Long purchaseId){
    Purchase purchase = getPurchase(purchaseId);

    purchase.close();

    purchaseRepository.save(purchase);

  }

  public Review addReview(Long purchaseId, String description){
    Purchase purchase = getPurchase(purchaseId);

    Review review = new Review();
    review.setCreationDate(OffsetDateTime.now());
    review.setDescription(description);
    review.setPurchase(purchase);

    return reviewRepository.save(review);
  }

  private Purchase getPurchase(Long purchaseId) {
    Purchase purchase = purchaseRepository.findById(purchaseId)
        .orElseThrow(() -> new EntityNotFoundException("Purchase not found"));
    return purchase;
  }

}
