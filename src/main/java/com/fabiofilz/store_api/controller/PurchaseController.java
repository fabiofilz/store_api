package com.fabiofilz.store_api.controller;

import com.fabiofilz.store_api.api.representationmodel.PurchaseInputModel;
import com.fabiofilz.store_api.api.representationmodel.PurchaseModel;
import com.fabiofilz.store_api.domain.model.Purchase;
import com.fabiofilz.store_api.domain.model.StatusPurchase;
import com.fabiofilz.store_api.domain.repository.PurchaseRepository;
import com.fabiofilz.store_api.domain.service.PurchaseManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

  @Autowired
  private PurchaseRepository purchaseRepository;

  @Autowired
  private PurchaseManagerService purchaseManagerService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public List<PurchaseModel> getAll(){
    return toCollectionModel(purchaseRepository.findAll());
  }

  @GetMapping("/{purchaseId}")
  public ResponseEntity<PurchaseModel> findById(@Valid @PathVariable Long purchaseId){
    Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);

    if (purchase.isPresent()){
      PurchaseModel purchaseModel = toModel(purchase.get());
      return ResponseEntity.ok(purchaseModel);
    }

    return ResponseEntity.notFound().build();
  }

  @PutMapping("/{purchaseId}/close")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void closePurchase(@PathVariable Long purchaseId){
    purchaseManagerService.closePurchase(purchaseId);
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PurchaseModel create(@Valid @RequestBody PurchaseInputModel purchaseInput){
    Purchase purchase = toEntity(purchaseInput);
    return toModel(purchaseManagerService.create(purchase));
  }

  private PurchaseModel toModel(Purchase purchase){
    return modelMapper.map(purchase, PurchaseModel.class);
  }

  private List<PurchaseModel> toCollectionModel(List<Purchase> purchases){
    return purchases.stream()
        .map(purchase -> toModel(purchase))
        .collect(Collectors.toList());
  }

  private Purchase toEntity(PurchaseInputModel purchaseInput){
    return modelMapper.map(purchaseInput, Purchase.class);
  }

}
