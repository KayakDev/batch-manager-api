package com.kayak.batchManager.ManagerControl.Batch.Repository;

import com.kayak.batchManager.ManagerControl.Batch.Entity.BatchModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<BatchModel, Long> {
}
