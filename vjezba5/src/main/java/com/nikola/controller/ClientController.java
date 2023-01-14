package com.nikola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikola.model.Client;
import com.nikola.model.Usage;
import com.nikola.model.ElMeter;
import com.nikola.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@PostMapping("/add")
	public ResponseEntity<ElMeter> registerNewSmartDeviceForClient(Integer clientID) {
		return clientService.registerNewSmartDeviceForClient(clientID).map(ResponseEntity::ok)
				.orElse(ResponseEntity.badRequest().build());
	}

	@GetMapping("/{clientId}")
	public ResponseEntity<List<Usage>> getDeviceHistory(@PathVariable(value = "clientId") Integer clientId) {
		return ResponseEntity.ok(clientService.getHistoryForClientsDevice(clientId));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Client>> getAllClients() {
		return ResponseEntity.ok(clientService.getAllClients());
	}
}
