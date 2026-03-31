package com.kayak.batchManager.ManagerControl.Client.Service;

import com.kayak.batchManager.ManagerControl.Client.Entity.ClientModel;
import com.kayak.batchManager.ManagerControl.Client.Repository.ClientRepository;
import com.kayak.batchManager.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientModel createClient(ClientModel clientModel){
        log.info("Creating client with email: {}", clientModel.getEmail());
        Optional<ClientModel> clientExist = clientRepository.findByEmail(clientModel.getEmail());

        if (clientExist.isPresent()) {
            log.warn("Attempt to create client with existing email: {}", clientModel.getEmail());
            throw new RuntimeException("Já existe um cliente cadastrado com esse email");
        }
        ClientModel saved = clientRepository.save(clientModel);
        log.info("Client created with id: {}", saved.getId());
        return saved;
    }

    public List<ClientModel> listAllClient() {
        log.info("Listing all clients");
        return clientRepository.findAll();
    }

    public ClientModel findById(Long id) {
        log.info("Finding client by id: {}", id);
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    @Transactional
    public ClientModel uptadeClient(Long id, ClientModel clientUpdated) {
        log.info("Updating client with id: {}", id);
        ClientModel clientExist = findById(id);

        clientExist.setName(clientUpdated.getName());
        clientExist.setPhone(clientUpdated.getPhone());
        clientExist.setEmail(clientUpdated.getEmail());

        ClientModel saved = clientRepository.save(clientExist);
        log.info("Client updated with id: {}", saved.getId());
        return saved;
    }

    @Transactional
    public void delClient (Long id) {
        log.info("Deleting client with id: {}", id);
        ClientModel clientModel = findById(id);
        clientRepository.delete(clientModel);
        log.info("Client deleted with id: {}", id);
    }

}
