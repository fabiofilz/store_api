package com.fabiofilz.store_api.domain.service;

import com.fabiofilz.store_api.domain.exception.BusinessRulesException;
import com.fabiofilz.store_api.domain.model.Customer;
import com.fabiofilz.store_api.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterCustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public Customer save(Customer customer){

    Customer isThereACustomer = customerRepository.findByEmail(customer.getEmail());

    if (isThereACustomer != null && !isThereACustomer.equals(customer)){
      System.out.println("******* Achei o usuario ");
      throw new BusinessRulesException("There is another Customer using this e-mail");
    }

    return customerRepository.save(customer);
  }

  public void delete(Long customerId){
    customerRepository.deleteById(customerId);
  }
}
