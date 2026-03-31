package com.kayak.batchManager.ManagerControl.Batch.Service;

import com.kayak.batchManager.ManagerControl.Batch.Entity.BatchModel;
import com.kayak.batchManager.ManagerControl.Batch.Repository.BatchRepository;
import com.kayak.batchManager.ManagerControl.Client.Entity.ClientModel;
import com.kayak.batchManager.ManagerControl.Client.Repository.ClientRepository;
import com.kayak.batchManager.ManagerControl.Product.Entity.ProductModel;
import com.kayak.batchManager.ManagerControl.Product.Repository.ProductRepository;
import com.kayak.batchManager.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class BatchService {

    private final BatchRepository batchRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public BatchService(BatchRepository batchRepository,
                        ClientRepository clientRepository,
                        ProductRepository productRepository) {
        this.batchRepository = batchRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public BatchModel createBatch(BatchModel batch) {
        log.info("Creating batch with code: {}", batch.getCode());
        Long clientId = batch.getClient().getId();
        Long productId = batch.getProduct().getId();

        ClientModel client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        batch.setClient(client);
        batch.setProduct(product);

        BatchModel saved = batchRepository.save(batch);
        log.info("Batch created with id: {}", saved.getId());
        return saved;
    }

    public Page<BatchModel> findAllBatches(Pageable pageable) {
        log.info("Listing all batches with pagination: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return batchRepository.findAll(pageable);
    }

    public BatchModel findBatchById(Long id) {
        log.info("Finding batch by id: {}", id);
        return batchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));
    }

    @Transactional
    public BatchModel updateBatch(Long id, BatchModel updatedBatch) {
        log.info("Updating batch with id: {}", id);
        BatchModel existingBatch = findBatchById(id);

        Long clientId = updatedBatch.getClient().getId();
        Long productId = updatedBatch.getProduct().getId();

        ClientModel client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingBatch.setCode(updatedBatch.getCode());
        existingBatch.setQuantity(updatedBatch.getQuantity());
        existingBatch.setEntryDate(updatedBatch.getEntryDate());
        existingBatch.setStatus(updatedBatch.getStatus());
        existingBatch.setClient(client);
        existingBatch.setProduct(product);

        BatchModel saved = batchRepository.save(existingBatch);
        log.info("Batch updated with id: {}", saved.getId());
        return saved;
    }

    @Transactional
    public void deleteBatch(Long id) {
        log.info("Deleting batch with id: {}", id);
        BatchModel batch = findBatchById(id);
        batchRepository.delete(batch);
        log.info("Batch deleted with id: {}", id);
    }
}