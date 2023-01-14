package com.nikola.vjezba4;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nikola.controller.ClientController;
import com.nikola.controller.ElMeterController;
import com.nikola.model.Address;
import com.nikola.model.Client;
import com.nikola.model.ElMeter;
import com.nikola.repository.AddressRepository;
import com.nikola.repository.ClientRepository;
import com.nikola.repository.ElMeterRepository;
import com.nikola.service.ClientService;
import com.nikola.service.ElMeterService;

@SpringBootTest
class Vjezba4ApplicationTests {

	@Autowired
    private ElMeterController smartDeviceController;
    @Autowired
    private ElMeterService smartDeviceService;
    @Autowired
    private ClientController clientController;
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ElMeterRepository deviceRepository;
    
    private Address address;
    private Client client;
    private ElMeter smartDevice;
    
    @BeforeEach
    public void setUp() {
    	address = new Address("streetName","streetNum", "city", "zipCode", "country");
    	client = new Client("clientData", address);
    	smartDevice = new ElMeter();
    	client.setSmartDevice(smartDevice);
    }
    @AfterEach
    public void tearDown() {
    	addressRepository.deleteAll();
    	clientRepository.deleteAll();
    	deviceRepository.deleteAll();
        address = null;
        client = null;
        smartDevice = null;
    }
    
	@Test
	void contextLoads() {
		assertThat(smartDeviceController).isNotNull();
		assertThat(smartDeviceService).isNotNull();
		assertThat(clientController).isNotNull();
		assertThat(clientService).isNotNull();
	}
	//client tests
	@Test
	void clientControllerGetNoDeviceHistory() {
		clientRepository.save(client);
		assertThat( clientController.getDeviceHistory(client.getClientID()) ).asList().isEmpty();
	}
	
	@Test
	void clientControllerAddNewClientDevice() {
		clientRepository.save(client);
		clientService.registerNewSmartDeviceForClient(client.getClientID());		
		assertThat( client.getSmartDevice() ).isNotNull();
	}
	
	//smart device tests

	@Test
	void smartDeviceControllergetAll() {
		deviceRepository.save(smartDevice);
		assertThat(smartDeviceController.getAllDevices()).isNotNull();
	}
}
