package com.kayak.batchManager.ManagerControl.Product.Service;

import com.kayak.batchManager.ManagerControl.Product.Entity.ProductModel;
import com.kayak.batchManager.ManagerControl.Product.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public ProductModel createProduct(ProductModel product){
        return productRepository.save(product);
    }

    public List<ProductModel> findAllProduct(){
        return productRepository.findAll();
    }

    public ProductModel findProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductModel updateProduct(Long id, ProductModel updatedProduct){
        ProductModel existingProduct = findProductById(id);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        ProductModel product = findProductById(id);
        productRepository.delete(product);
    }
 }
