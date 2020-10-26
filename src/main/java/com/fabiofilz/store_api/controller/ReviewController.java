package com.fabiofilz.store_api.controller;

import com.fabiofilz.store_api.api.representationmodel.ReviewInputModel;
import com.fabiofilz.store_api.api.representationmodel.ReviewModel;
import com.fabiofilz.store_api.domain.exception.BusinessRulesException;
import com.fabiofilz.store_api.domain.exception.EntityNotFoundException;
import com.fabiofilz.store_api.domain.model.Purchase;
import com.fabiofilz.store_api.domain.model.Review;
import com.fabiofilz.store_api.domain.repository.PurchaseRepository;
import com.fabiofilz.store_api.domain.service.PurchaseManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("purchases/{purchaseId}/reviews")
public class ReviewController {

  @Autowired
  private PurchaseRepository purchaseRepository;

  @Autowired
  private PurchaseManagerService purchaseManagerService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public List<ReviewModel> getAll(@PathVariable Long purchaseId){
    Purchase purchase = purchaseRepository.findById(purchaseId)
        .orElseThrow(() -> new EntityNotFoundException("Purchase not found"));

    return toCollectionModel(purchase.getReviews());
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ReviewModel save(@PathVariable Long purchaseId,
                          @Valid @RequestBody ReviewInputModel reviewInput){
    Review review = purchaseManagerService.addReview(purchaseId, reviewInput.getDescription());

    return toModel(review);

  }

  private ReviewModel toModel(Review review){
    return modelMapper.map(review, ReviewModel.class);
  }

  private List<ReviewModel> toCollectionModel(List<Review> reviews){
    return reviews.stream()
        .map(review -> toModel(review))
        .collect(Collectors.toList());
  }

}
