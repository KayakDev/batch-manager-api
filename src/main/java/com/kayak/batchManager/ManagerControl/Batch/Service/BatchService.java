package com.kayak.batchManager.ManagerControl.Batch.Service;

import com.kayak.batchManager.ManagerControl.Batch.Entity.BatchModel;
import com.kayak.batchManager.ManagerControl.Batch.Repository.BatchRepository;
import com.kayak.batchManager.ManagerControl.Client.Entity.ClientModel;
import com.kayak.batchManager.ManagerControl.Client.Repository.ClientRepository;
import com.kayak.batchManager.ManagerControl.Product.Entity.ProductModel;
import com.kayak.batchManager.ManagerControl.Product.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public BatchModel createBatch(BatchModel batch) {
        Long clientId = batch.getClient().getId();
        Long productId = batch.getProduct().getId();

        ClientModel client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        batch.setClient(client);
        batch.setProduct(product);

        return batchRepository.save(batch);
    }

    public List<BatchModel> findAllBatches() {
        return batchRepository.findAll();
    }

    public BatchModel findBatchById(Long id) {
        return batchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Batch not found"));
    }

    public BatchModel updateBatch(Long id, BatchModel updatedBatch) {
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

        return batchRepository.save(existingBatch);
    }

    public void deleteBatch(Long id) {
        BatchModel batch = findBatchById(id);
        batchRepository.delete(batch);
    }
}