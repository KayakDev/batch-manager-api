package com.kayak.batchManager.ManagerControl.Batch.Controller;

import com.kayak.batchManager.ManagerControl.Batch.Entity.BatchModel;
import com.kayak.batchManager.ManagerControl.Batch.Service.BatchService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping
    public BatchModel createBatch(@RequestBody @Valid BatchModel batch) {
        return batchService.createBatch(batch);
    }

    @GetMapping
    public List<BatchModel> findAllBatches() {
        return batchService.findAllBatches();
    }

    @GetMapping("/{id}")
    public BatchModel findBatchById(@PathVariable Long id) {
        return batchService.findBatchById(id);
    }

    @PutMapping("/{id}")
    public BatchModel updateBatch(@PathVariable Long id, @RequestBody @Valid BatchModel batch) {
        return batchService.updateBatch(id, batch);
    }

    @DeleteMapping("/{id}")
    public void deleteBatch(@PathVariable Long id) {
        batchService.deleteBatch(id);
    }
}
