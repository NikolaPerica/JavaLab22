package com.nikola.vjezba5;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
	private ClientRepository clRepository;
	@Autowired
	private AddressRepository addrRepository;
	@Autowired
	private ElMeterRepository sdRepository;
	
	
	@Override
	public void run(String... args) throws Exception {


		Address a1 = new Address("Trg Britanac", "5", "Zagreb", "10000", "Hrvatska");
		Address a2 = new Address("Simiceva", "10", "Split", "21000", "Hrvatska");
		Address a3 = new Address("Rudera Boskovica", "5", "Split", "21000", "Hrvatska");
		Address a4 = new Address("Rudera Boskovica", "5", "Split", "21000", "Slovenia");
		addrRepository.save(a1);
		addrRepository.save(a2);
		addrRepository.save(a3);
		addrRepository.save(a4);
		addrRepository.save(a1);

		sdRepository.save(new ElMeter());
		sdRepository.save(new ElMeter());
		sdRepository.save(new ElMeter());
		ArrayList<ElMeter> allDevices = sdRepository.getAllDevices();

		Client k1=new Client("Milan Karamarko", a1, allDevices.get(0));
		Client k2=new Client("Nikola Perica", a2);
		Client k3=new Client("Petar Preradovic", a3, allDevices.get(1));
		Client k4=new Client("Marin Ivanov", a3, allDevices.get(1));
		k3.setSmartDevice(allDevices.get(2));
		clRepository.save(k1);
		clRepository.save(k2);
		clRepository.save(k3);
		clRepository.save(k4);

		System.out.println(sdRepository.getYearSum(k1.getSmartDevice().getSmartDeviceID(), 2022));
	}

}
