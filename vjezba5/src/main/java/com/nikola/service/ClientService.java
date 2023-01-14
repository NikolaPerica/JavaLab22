package com.nikola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikola.model.Client;
import com.nikola.model.Usage;
import com.nikola.model.ElMeter;
import com.nikola.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	public List<Usage> getHistoryForClientsDevice(int clientID) {
		return clientRepository.getHistoryForClientsDevice(clientID);
	}

	public Optional<ElMeter> registerNewSmartDeviceForClient(int clientID) {
		return clientRepository.registerNewDeviceForClient(clientID);
	}

	public List<Client> getAllClients() {
		return clientRepository.getAllClients();
	}
}
