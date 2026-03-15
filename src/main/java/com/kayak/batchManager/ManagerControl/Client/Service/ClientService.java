package com.kayak.batchManager.ManagerControl.Client.Service;

import com.kayak.batchManager.ManagerControl.Client.Entity.ClientModel;
import com.kayak.batchManager.ManagerControl.Client.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientModel createClient(ClientModel clientModel){
        Optional<ClientModel> clientExist = clientRepository.findByEmail(clientModel.getEmail());

        if (clientExist.isPresent()) {
            throw new RuntimeException("Já existe um cliente cadastrado com esse email");
        }
        return clientRepository.save(clientModel);
    }

    public List<ClientModel> listAllClient() {
        return clientRepository.findAll();
    }

    public ClientModel findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("Cliente não encontrado")));
    }

    public ClientModel uptadeClient(Long id, ClientModel clientUpdated) {
        ClientModel clientExist = findById(id);

        clientExist.setName(clientUpdated.getName());
        clientExist.setPhone(clientUpdated.getPhone());
        clientExist.setEmail(clientUpdated.getEmail());

        return clientRepository.save(clientExist);
    }

    public void delClient (Long id) {
        ClientModel clientModel = findById(id);
        clientRepository.delete(clientModel);
    }

}
