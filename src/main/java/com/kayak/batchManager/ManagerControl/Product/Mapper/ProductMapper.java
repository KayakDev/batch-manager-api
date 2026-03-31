package com.kayak.batchManager.ManagerControl.Product.Mapper;

import com.kayak.batchManager.ManagerControl.Product.Dto.ProductDTO;
import com.kayak.batchManager.ManagerControl.Product.Entity.ProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(ProductModel productModel);

    ProductModel toEntity(ProductDTO productDTO);
}
