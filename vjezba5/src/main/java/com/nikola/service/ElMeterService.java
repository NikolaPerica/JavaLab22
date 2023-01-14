package com.nikola.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikola.model.Usage;
import com.nikola.model.ElMeter;
import com.nikola.repository.ClientRepository;
import com.nikola.repository.ElMeterRepository;

@Service
public class ElMeterService {

	@Autowired
	private ElMeterRepository deviceRepository;

	@Autowired
	private ClientRepository clientRepository;

	public Optional<ElMeter> findDeviceByID(int deviceID) {
		return deviceRepository.findDeviceByID(deviceID);

	}

	public Optional<Integer> addNewValueForDevice(int deviceID, int month, int year) {
		Optional<Integer> newValue = deviceRepository.addNewValueForDevice(deviceID, month, year);

		newValue.ifPresent(newId -> {
			clientRepository.refreshClientDataWithDeviceData(deviceID, month, year, newId);
		});

		return newValue;
	}

	public ArrayList<ElMeter> getAllDevices() {
		return deviceRepository.getAllDevices();
	}

	public ElMeter registerNewDevice() {
		return deviceRepository.registerNewDevice();
	}

	public Optional<ElMeter> updateValueForDevice(int deviceID, int month, int year, int value) {
		Optional<ElMeter> device = deviceRepository.updateValueForDevice(deviceID, month, year, value);
		if (device.isPresent()) {
			clientRepository.refreshClientDataWithDeviceData(deviceID, month, year, value);

		}

		return device;
	}

	public Optional<String> deleteValueForDevice(int deviceID, Usage usage) {
		Optional<String> deleted = deviceRepository.deleteValueForDevice(deviceID, usage.getMonth(),
				usage.getYear());
		if (deleted.isPresent()) {
			clientRepository.refreshClientDataWithDeviceDataDeleted(deviceID, usage.getMonth(), usage.getYear());

		}

		return deleted;
	}

	public List<Map<String, Object>> getAllValueForDevice(Integer deviceID) {
		return deviceRepository.getAllValueForDevice(deviceID);
	}

	public Map<String, Object> getValueForDeviceMonthAndYear(Integer deviceID, Integer month, Integer year) {
		return deviceRepository.getValueForDeviceMonthAndYear(deviceID, month, year);
	}

	public Map<String, Object> getValueForDeviceWholeYear(Integer deviceID, Integer year) {
		return deviceRepository.getValueForDeviceForYear(deviceID, year);
	}

	public Map<String, Object> getYearSum(Integer deviceID, Integer year) {
		return deviceRepository.getYearSum(deviceID, year);
	}

}
