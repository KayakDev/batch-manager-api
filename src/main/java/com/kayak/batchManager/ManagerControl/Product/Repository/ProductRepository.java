package com.kayak.batchManager.ManagerControl.Product.Repository;

import com.kayak.batchManager.ManagerControl.Product.Entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
