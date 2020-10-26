package com.fabiofilz.store_api.domain.repository;

import com.fabiofilz.store_api.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
