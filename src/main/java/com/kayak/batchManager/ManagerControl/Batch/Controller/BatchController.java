package com.kayak.batchManager.ManagerControl.Batch.Controller;

import com.kayak.batchManager.ManagerControl.Batch.Dto.BatchDTO;
import com.kayak.batchManager.ManagerControl.Batch.Entity.BatchModel;
import com.kayak.batchManager.ManagerControl.Batch.Service.BatchService;
import com.kayak.batchManager.ManagerControl.Client.Entity.ClientModel;
import com.kayak.batchManager.ManagerControl.Client.Repository.ClientRepository;
import com.kayak.batchManager.ManagerControl.Product.Entity.ProductModel;
import com.kayak.batchManager.ManagerControl.Product.Repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private final BatchService batchService;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public BatchController(BatchService batchService, ClientRepository clientRepository, ProductRepository productRepository) {
        this.batchService = batchService;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public BatchDTO createBatch(@RequestBody @Valid BatchDTO batchDTO) {
        BatchModel batch = new BatchModel();
        batch.setCode(batchDTO.getCode());
        batch.setQuantity(batchDTO.getQuantity());
        batch.setEntryDate(batchDTO.getEntryDate());
        batch.setStatus(batchDTO.getStatus());

        ClientModel client = clientRepository.findById(batchDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        ProductModel product = productRepository.findById(batchDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        batch.setClient(client);
        batch.setProduct(product);

        BatchModel savedBatch = batchService.createBatch(batch);
        return mapToDTO(savedBatch);
    }

    @GetMapping
    public Page<BatchDTO> findAllBatches(Pageable pageable) {
        return batchService.findAllBatches(pageable).map(this::mapToDTO);
    }

    @GetMapping("/{id}")
    public BatchDTO findBatchById(@PathVariable Long id) {
        BatchModel batch = batchService.findBatchById(id);
        return mapToDTO(batch);
    }

    @PutMapping("/{id}")
    public BatchDTO updateBatch(@PathVariable Long id, @RequestBody @Valid BatchDTO batchDTO) {
        BatchModel batch = new BatchModel();
        batch.setCode(batchDTO.getCode());
        batch.setQuantity(batchDTO.getQuantity());
        batch.setEntryDate(batchDTO.getEntryDate());
        batch.setStatus(batchDTO.getStatus());

        ClientModel client = clientRepository.findById(batchDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        ProductModel product = productRepository.findById(batchDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        batch.setClient(client);
        batch.setProduct(product);

        BatchModel updatedBatch = batchService.updateBatch(id, batch);
        return mapToDTO(updatedBatch);
    }

    @DeleteMapping("/{id}")
    public void deleteBatch(@PathVariable Long id) {
        batchService.deleteBatch(id);
    }

    private BatchDTO mapToDTO(BatchModel batch) {
        BatchDTO dto = new BatchDTO();
        dto.setId(batch.getId());
        dto.setCode(batch.getCode());
        dto.setQuantity(batch.getQuantity());
        dto.setEntryDate(batch.getEntryDate());
        dto.setStatus(batch.getStatus());
        dto.setClientId(batch.getClient().getId());
        dto.setProductId(batch.getProduct().getId());
        return dto;
    }
}
