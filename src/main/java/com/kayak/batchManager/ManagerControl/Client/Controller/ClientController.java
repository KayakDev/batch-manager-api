package com.kayak.batchManager.ManagerControl.Client.Controller;

import com.kayak.batchManager.ManagerControl.Client.Dto.ClientDTO;
import com.kayak.batchManager.ManagerControl.Client.Entity.ClientModel;
import com.kayak.batchManager.ManagerControl.Client.Mapper.ClientMapper;
import com.kayak.batchManager.ManagerControl.Client.Service.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;
    private ClientMapper clientMapper;

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    public ClientDTO saveClient(@RequestBody @Valid ClientDTO clientDTO) {
        ClientModel clientModel = clientMapper.toEntity(clientDTO);
        ClientModel savedClient = clientService.createClient(clientModel);
        return clientMapper.toDTO(savedClient);
    }

    @GetMapping
    public Page<ClientDTO> listAll(Pageable pageable) {
        return clientService.listAllClient(pageable).map(clientMapper::toDTO);
    }

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        ClientModel client = clientService.findById(id);
        return clientMapper.toDTO(client);
    }

    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody @Valid ClientDTO clientDTO) {
        ClientModel clientModel = clientMapper.toEntity(clientDTO);
        ClientModel updatedClient = clientService.uptadeClient(id, clientModel);
        return clientMapper.toDTO(updatedClient);
    }

    @DeleteMapping("/{id}")
    public void delClient(@PathVariable Long id) {
        clientService.delClient(id);
    }

}
