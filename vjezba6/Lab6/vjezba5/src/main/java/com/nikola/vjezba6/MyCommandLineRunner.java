package com.nikola.vjezba6;

import java.util.ArrayList;
import java.util.List;

import com.nikola.model.Usage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.nikola.model.Address;
import com.nikola.model.Client;
import com.nikola.model.ElMeter;
import com.nikola.repository.AddressRepository;
import com.nikola.repository.ClientRepository;
import com.nikola.repository.ElMeterRepository;


@Component
public class MyCommandLineRunner implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ElMeterRepository smartDeviceRepository;



	@Override
	public void run(String... args) throws Exception {

		clientRepository.deleteAll();
		addressRepository.deleteAll();
		smartDeviceRepository.deleteAll();


		Address address1 = new Address("Trg Britanac", "5", "Zagreb", "10000", "Hrvatska");
		Address address2 = new Address("Simiceva", "10", "Split", "21000", "Hrvatska");
		Address address3 = new Address("Rudera Boskovica", "5", "Split", "21000", "Hrvatska");
		Address address4 = new Address("Rudera Boskovica", "5", "Split", "21000", "Slovenia");
		addressRepository.save(address1);
		addressRepository.save(address2);
		addressRepository.save(address3);
		addressRepository.save(address4);

		Usage u1=new Usage(1,2022,45);
		Usage u2=new Usage(2,2022,55);
		Usage u11=new Usage(1,2022,125);
		Usage u22=new Usage(2,2022,505);


		ArrayList<Usage> usage = new ArrayList<>();
		usage.add(u1);
		usage.add(u2);
		ArrayList<Usage> usage2 = new ArrayList<>();
		usage2.add(u11);
		usage2.add(u22);
		ElMeter device1 = new ElMeter(usage);
		ElMeter device2 = new ElMeter(usage2);
		ElMeter device3 = new ElMeter();
		smartDeviceRepository.save(device1);
		smartDeviceRepository.save(device2);
		smartDeviceRepository.save(device3);

		ArrayList<ElMeter> allDevices = smartDeviceRepository.getAllDevices();


		clientRepository.save(new Client("Milan Karamarko", address1, allDevices.get(0)));
		clientRepository.save(new Client("Nikola Perica", address2, allDevices.get(1)));
		clientRepository.save(new Client("Petar Preradovic", address3, allDevices.get(2)));

	}

}
