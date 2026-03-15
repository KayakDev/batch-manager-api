package com.kayak.batchManager.ManagerControl.Client.Controller;

import com.kayak.batchManager.ManagerControl.Client.Entity.ClientModel;
import com.kayak.batchManager.ManagerControl.Client.Service.ClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientModel saveClient(@RequestBody ClientModel clientModel) {
        return clientService.createClient(clientModel);
    }

    @GetMapping
    public List<ClientModel> listAll () {
        return clientService.listAllClient();
    }

    @GetMapping("/{id}")
    public ClientModel findByid(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PutMapping("/{id}")
    public ClientModel updateClient(@PathVariable Long id, @RequestBody ClientModel clientModel) {
        return clientService.uptadeClient(id, clientModel);
    }
    @DeleteMapping("/{id}")
    public void delClient(@PathVariable Long id) {
        clientService.delClient(id);
    }

}
