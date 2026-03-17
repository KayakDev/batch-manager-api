package com.kayak.batchManager.ManagerControl.Product.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductModel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "Product name is necessary")
    @Column(nullable = false)
    private String name;

    @NotBlank (message = "Product description is necessary")
    @Column(nullable = false, length = 500)
    private String description;

    @NotNull (message = "Product price is necessary")
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

}
