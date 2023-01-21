package com.nikola.vjezba6.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.nikola.vjezba6.model.Client;
import com.nikola.vjezba6.model.Usage;
import com.nikola.vjezba6.model.ElMeter;
import com.nikola.vjezba6.validation.ErrorResponse;


@Controller
public class ViewController {

	private static String urlClients = "http://localhost:8080/clients";
	private static String urlDevices = "http://localhost:8080/smartdevices";
	private static String urlAddReading = "http://localhost:8080/smartdevices/";

	@GetMapping("/devices")
	public String viewAllReadings(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		Client[] clients = restTemplate.getForObject(urlClients, Client[].class);
		List<Client> onlyWithReadings = new ArrayList<>();
		for (Client object : clients) {
			if (object.getSmartDevice().getHistory().size() > 0) {
				onlyWithReadings.add(object);
			}
		}
		model.addAttribute("devices", onlyWithReadings);
		return "devices";
	}

	@GetMapping("/clients")
	public String viewClients(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		Object[] clients = restTemplate.getForObject(urlClients, Object[].class);
		model.addAttribute("clients", clients);
		return "clients";
	}

	@GetMapping("/addReading")
	public String addReading(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		ElMeter[] devices = restTemplate.getForObject(urlDevices, ElMeter[].class);
		model.addAttribute("devices", devices);

		String title = "Add reading for device";
		model.addAttribute("title", title);

		Usage newReading = new Usage();
		model.addAttribute("readingInfo", newReading);

		return "addReading";
	}

	@PostMapping("/addReading")
	public String createReading(Model model, @Valid @ModelAttribute("readingInfo") Usage readingInfo,
								BindingResult result) {
		if (result.hasErrors()) {
			RestTemplate restTemplate = new RestTemplate();
			ElMeter[] devices = restTemplate.getForObject(urlDevices, ElMeter[].class);
			model.addAttribute("devices", devices);

			String title = "Add reading for device";
			model.addAttribute("title", title);
			model.addAttribute("readingInfo", readingInfo);
			return "addReading";
		}
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity(urlAddReading + readingInfo.getDeviceID(), readingInfo,
				null);

		return "redirect:/devices";
	}

	@GetMapping(value = "/")
	public String index() {
		return "home";
	}

}
