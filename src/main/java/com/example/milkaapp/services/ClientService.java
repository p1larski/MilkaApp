package com.example.milkaapp.services;

import com.example.milkaapp.models.Client;
import com.example.milkaapp.models.Day;
import com.example.milkaapp.models.modelsDto.ClientDto;
import com.example.milkaapp.models.modelsDto.DayDto;
import com.example.milkaapp.repositories.ClientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements Converter<ClientDto, Client> {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client convert(ClientDto source) {
        Client client = new Client();
        client.setName(source.getName());
        client.setLastname(source.getLastname());
        client.setPhoneNumber(source.getPhoneNumber());
        client.setAdress(source.getAdress());
        return client;
    }
    public Client addClient (ClientDto clientDto){
        return clientRepository.save(convert(clientDto));
    }
}
