package com.kayak.batchManager.ManagerControl.Batch.Entity;

import com.kayak.batchManager.ManagerControl.Client.Entity.ClientModel;
import com.kayak.batchManager.ManagerControl.Product.Entity.ProductModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_batch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Code is required")
    @Column(nullable = false, unique = true, length =  50)
    private Integer code;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than zero")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "Entry date is required")
    @Column(nullable = false)
    private LocalDate entryDate;

    @NotBlank(message = "Status is required")
    @Column(nullable = false, length = 30)
    private String status;

    @NotNull(message = "Client is required")
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientModel client;

    @NotNull(message = "Product is required")
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel product;
}
