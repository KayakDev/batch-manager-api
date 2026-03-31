package com.kayak.batchManager.ManagerControl.Product.Controller;

import com.kayak.batchManager.ManagerControl.Product.Dto.ProductDTO;
import com.kayak.batchManager.ManagerControl.Product.Entity.ProductModel;
import com.kayak.batchManager.ManagerControl.Product.Mapper.ProductMapper;
import com.kayak.batchManager.ManagerControl.Product.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/product")
public class ProductController {

    private ProductService productService;
    private ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper){
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDTO){
        ProductModel productModel = productMapper.toEntity(productDTO);
        ProductModel savedProduct = productService.createProduct(productModel);
        return productMapper.toDTO(savedProduct);
    }

    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable){
        return productService.findAllProduct(pageable).map(productMapper::toDTO);
    }

    @GetMapping("/{id}")
    public ProductDTO findProductById(@PathVariable Long id) {
        ProductModel product = productService.findProductById(id);
        return productMapper.toDTO(product);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        ProductModel productModel = productMapper.toEntity(productDTO);
        ProductModel updatedProduct = productService.updateProduct(id, productModel);
        return productMapper.toDTO(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct (@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
