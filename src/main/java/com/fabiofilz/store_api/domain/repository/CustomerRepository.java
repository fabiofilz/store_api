package com.fabiofilz.store_api.domain.repository;

import com.fabiofilz.store_api.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  List<Customer> findByName(String name);

  List<Customer> findByNameContaining(String name);

  Customer findByEmail(String email);
}
