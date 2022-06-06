package com.example.milkaapp.controllers;

import com.example.milkaapp.models.Client;
import com.example.milkaapp.models.modelsDto.ClientDto;
import com.example.milkaapp.services.ClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client")
    public Client newClient (@RequestBody ClientDto clientDto){
        return clientService.addClient(clientDto);
    }

}
