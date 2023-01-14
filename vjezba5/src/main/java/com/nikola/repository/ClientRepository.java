package com.nikola.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import com.nikola.model.Client;
import com.nikola.model.Usage;
import com.nikola.model.ElMeter;
import com.nikola.service.ElMeterService;

@Repository
public class ClientRepository {

	@Autowired
	private ElMeterService deviceService;

	private static final String filePath = "client.json";
	private static Gson gson;
	private static ArrayList<Client> allClients;

	public ClientRepository() {
		gson = new GsonBuilder().setPrettyPrinting().create();
		allClients = new ArrayList<>();

		File file = new File(filePath);
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		loadAll();
	}

	public void loadAll() {

		if (allClients.isEmpty()) {
			try {
				Client[] array = (gson.fromJson(new FileReader(filePath), Client[].class));
				if (array != null) {
					for (Client Client : array) {
						allClients.add(Client);
					}
				}
			} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	public void deleteAll() {
		allClients.clear();
	}

	public void save(Client client) {
		for (Client existingClietns : allClients) {
			if (existingClietns.equals(client)) {
				System.out.println("Vec je neko na toj adresi!!!!!!");
				return;
			}
		}
		allClients.add(client);

		try (FileWriter writer = new FileWriter(filePath)) {
			gson.toJson(allClients, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Usage> getHistoryForClientsDevice(int clientID) {
		for (Client client : allClients) {
			if (client.getClientID() == clientID) {
				if (client.getSmartDevice() != null)
					return client.getSmartDevice().getHistory();
			}
		}
		return List.of();
	}

	public Optional<ElMeter> registerNewDeviceForClient(int clientID) {
		ElMeter smartDevice = null;

		for (Client client : allClients) {
			if (client.getClientID() == clientID) {
				if (client.getSmartDevice() == null) {
					smartDevice = deviceService.registerNewDevice();
					client.setSmartDevice(smartDevice);
				} else {
					System.out.println("Klijent vec ima uredaj");
					return Optional.empty();
				}
			}
		}

		try (FileWriter writer = new FileWriter(filePath)) {
			gson.toJson(allClients, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Optional.of(smartDevice);
	}

	public List<Client> getAllClients() {
		return allClients;
	}

	// when new value is generated so clients.json is refreshed too
	public void refreshClientDataWithDeviceData(int deviceID, int month, int year, int newValue) {
		for (Client client : allClients) {
			if (client.getSmartDevice().getSmartDeviceID() == deviceID) {

				try (FileWriter writer = new FileWriter(filePath)) {
					gson.toJson(allClients, writer);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public boolean refreshClientDataWithDeviceDataDeleted(int deviceID, int month, int year) {
		try (FileWriter writer = new FileWriter(filePath)) {
			gson.toJson(allClients, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
