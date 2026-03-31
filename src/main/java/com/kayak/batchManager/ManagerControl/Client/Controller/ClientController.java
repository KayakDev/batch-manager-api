package com.kayak.batchManager.ManagerControl.Client.Controller;

import com.kayak.batchManager.ManagerControl.Client.Dto.ClientDTO;
import com.kayak.batchManager.ManagerControl.Client.Entity.ClientModel;
import com.kayak.batchManager.ManagerControl.Client.Service.ClientService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;
    private ModelMapper modelMapper;

    public ClientController(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ClientDTO saveClient(@RequestBody @Valid ClientDTO clientDTO) {
        ClientModel clientModel = modelMapper.map(clientDTO, ClientModel.class);
        ClientModel savedClient = clientService.createClient(clientModel);
        return modelMapper.map(savedClient, ClientDTO.class);
    }

    @GetMapping
    public List<ClientDTO> listAll() {
        return clientService.listAllClient().stream()
                .map(client -> modelMapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        ClientModel client = clientService.findById(id);
        return modelMapper.map(client, ClientDTO.class);
    }

    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody @Valid ClientDTO clientDTO) {
        ClientModel clientModel = modelMapper.map(clientDTO, ClientModel.class);
        ClientModel updatedClient = clientService.uptadeClient(id, clientModel);
        return modelMapper.map(updatedClient, ClientDTO.class);
    }

    @DeleteMapping("/{id}")
    public void delClient(@PathVariable Long id) {
        clientService.delClient(id);
    }

}
