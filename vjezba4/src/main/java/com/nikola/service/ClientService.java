package com.nikola.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikola.model.Client;
import com.nikola.model.ElMeter;
import com.nikola.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    ClientRepository clRepository;

    public List<Integer> getHistoryForClientsDevice(int clientID){
        return clRepository.getHistoryForClientsDevice(clientID);
    }

    public ElMeter registerNewSmartDeviceForClient(int clientID) {
        return clRepository.registerNewDeviceForClient(clientID);
    }

    public List<Client> getAllClients() {
        return clRepository.getAllClients();
    }
}
