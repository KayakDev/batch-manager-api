package com.kayak.batchManager.ManagerControl.Product.Service;

import com.kayak.batchManager.ManagerControl.Product.Entity.ProductModel;
import com.kayak.batchManager.ManagerControl.Product.Repository.ProductRepository;
import com.kayak.batchManager.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Transactional
    public ProductModel createProduct(ProductModel product){
        log.info("Creating product with name: {}", product.getName());
        ProductModel saved = productRepository.save(product);
        log.info("Product created with id: {}", saved.getId());
        return saved;
    }

    public Page<ProductModel> findAllProduct(Pageable pageable){
        log.info("Listing all products with pagination: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return productRepository.findAll(pageable);
    }

    public ProductModel findProductById(Long id){
        log.info("Finding product by id: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Transactional
    public ProductModel updateProduct(Long id, ProductModel updatedProduct){
        log.info("Updating product with id: {}", id);
        ProductModel existingProduct = findProductById(id);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());

        ProductModel saved = productRepository.save(existingProduct);
        log.info("Product updated with id: {}", saved.getId());
        return saved;
    }

    @Transactional
    public void deleteProduct(Long id) {
        log.info("Deleting product with id: {}", id);
        ProductModel product = findProductById(id);
        productRepository.delete(product);
        log.info("Product deleted with id: {}", id);
    }
 }
