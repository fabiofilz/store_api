package com.fabiofilz.store_api.controller;

import com.fabiofilz.store_api.domain.model.Customer;
import com.fabiofilz.store_api.domain.repository.CustomerRepository;
import com.fabiofilz.store_api.domain.service.RegisterCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private RegisterCustomerService registerCustomerService;

  @GetMapping
  private List<Customer> getAll(){
    return customerRepository.findAll();
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<Customer> findById(@PathVariable Long customerId){
    Optional<Customer> customer = customerRepository.findById(customerId);

    if (customer.isPresent()){
      return ResponseEntity.ok(customer.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Customer add(@Valid @RequestBody Customer customer){
    return registerCustomerService.save(customer);
  }

  @PutMapping("/{customerId}")
  public ResponseEntity<Customer> update(@Valid @PathVariable Long customerId,
                                         @RequestBody Customer customer){

    if (!customerRepository.existsById(customerId)){
      return ResponseEntity.notFound().build();
    }

    customer.setId(customerId);
    customer = registerCustomerService.save(customer);

    return ResponseEntity.ok(customer);
  }

  @DeleteMapping("/{customerId}")
  public ResponseEntity<Void> delete(@PathVariable Long customerId){
    if (!customerRepository.existsById(customerId)){
      return ResponseEntity.notFound().build();
    }

    registerCustomerService.delete(customerId);

    return ResponseEntity.noContent().build();

  }

}
