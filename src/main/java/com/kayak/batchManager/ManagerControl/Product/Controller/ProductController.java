package com.kayak.batchManager.ManagerControl.Product.Controller;

import com.kayak.batchManager.ManagerControl.Product.Dto.ProductDTO;
import com.kayak.batchManager.ManagerControl.Product.Entity.ProductModel;
import com.kayak.batchManager.ManagerControl.Product.Service.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/product")
public class ProductController {

    private ProductService productService;
    private ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper){
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDTO){
        ProductModel productModel = modelMapper.map(productDTO, ProductModel.class);
        ProductModel savedProduct = productService.createProduct(productModel);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @GetMapping
    public List<ProductDTO> findAll(){
        return productService.findAllProduct().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO findProductById(@PathVariable Long id) {
        ProductModel product = productService.findProductById(id);
        return modelMapper.map(product, ProductDTO.class);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        ProductModel productModel = modelMapper.map(productDTO, ProductModel.class);
        ProductModel updatedProduct = productService.updateProduct(id, productModel);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct (@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
