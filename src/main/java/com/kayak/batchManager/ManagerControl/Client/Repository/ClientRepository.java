package com.kayak.batchManager.ManagerControl.Client.Repository;

import com.kayak.batchManager.ManagerControl.Client.Entity.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientModel, Long>{

    Optional<ClientModel> findByEmail(String email);
}
