package com.nikola.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import com.nikola.model.Client;
import com.nikola.model.ElMeter;
import com.nikola.repository.ClientRepository;
import com.nikola.repository.ElMeterRepository;
import com.nikola.sorter.ElMeterSorter;

@Service
public class ElMeterService {
	@Autowired
	ElMeterService deviceRepository;
	@Autowired
	ClientRepository clientRepository;

	public ElMeter findDeviceByID(int deviceID) {
		return deviceRepository.findDeviceByID(deviceID);
	}

	public int addNewValueForDevice(int deviceID, int month, int year) {
		int newValue = deviceRepository.addNewValueForDevice(deviceID, month, year);

		if (newValue != -1)
			clientRepository.refreshClientDataWithDeviceData(deviceID, month, year, newValue);

		return newValue;
	}

	public ArrayList<ElMeter> getAllDevices() {
		return deviceRepository.getAllDevices();
	}

	public ElMeter registerNewDevice() {
		return deviceRepository.registerNewDevice();
	}

	public ElMeter updateValueForDevice(int deviceID, int month, int year, int value) {
		ElMeter device = deviceRepository.updateValueForDevice(deviceID, month, year, value);
		if (device != null) {
			clientRepository.refreshClientDataWithDeviceData(deviceID, month, year, value);
		}
		return device;
	}

	public String deleteValueForDevice(int deviceID, int month, int year) {
		String deleted = deviceRepository.deleteValueForDevice(deviceID, month, year);
		if (deleted != null)
			clientRepository.refreshClientDataWithDeviceDataDeleted(deviceID, month, year);
		return deleted;
	}


	public String getValueForDeviceForMonthAndYear(Integer deviceID, Integer month, Integer year) {
		return deviceRepository.getValueForDeviceForMonthAndYear(deviceID, month, year);
	}

	public String getValueForDeviceForWholeYear(Integer deviceID, Integer year) {
		return deviceRepository.getValueForDeviceForWholeYear(deviceID, year);
	}

	public String getSumForYear(Integer deviceID, Integer year) {
		return deviceRepository.getSumForYear(deviceID, year);
	}


	public ArrayList<ElMeter> getAllDevicesPaginated(Integer page, Integer limit) {
		ArrayList<ElMeter> list = getAllDevices();
		if (page != null) {
			ArrayList<ElMeter> paginated = getPaginatedSubList(list, page, limit);
			return paginated;
		} else
			return list;
	}

	public ArrayList<ElMeter> getAllDevicesSortedAndPaginated(Integer page, Integer limit, String sortBy, String sortDir) {
		ArrayList<ElMeter> sorted = getSortedList(getAllDevicesPaginated(page, limit), sortBy, sortDir);
		return sorted;
	}

	
	
	private ArrayList<ElMeter> getSortedList(ArrayList<ElMeter> list, String sortBy, String sortDir) {

		if (sortBy.equalsIgnoreCase("ID")) {
			if (sortDir.equalsIgnoreCase("asc"))
				return ElMeterSorter.getSortedSmartDevicesByIDAsc(list);
			else
				return ElMeterSorter.getSortedSmartDevicesByIDDesc(list);
		} else {
			if (sortDir.equalsIgnoreCase("asc"))
				return ElMeterSorter.getSortedSmartDevicesByHistorySizeAsc(list);
			else
				return ElMeterSorter.getSortedSmartDevicesByHistorySizeDesc(list);
		}

	}

	private ArrayList<ElMeter> getPaginatedSubList(ArrayList<ElMeter> list, Integer page, Integer limit) {
		int totalLimit = list.size();
		int indexFrom = (page - 1) * limit;
		int indexTo = indexFrom + limit;
		if (indexFrom <= totalLimit) {
			if (indexTo > totalLimit) {
				indexTo = totalLimit;
			}
			return new ArrayList<ElMeter>(list.subList(indexFrom, indexTo));
		}
		return new ArrayList<ElMeter>();
	}

	public Integer addNewValueForDevice(Integer deviceID, int value,
			int month, int year) {
		int newValue = deviceRepository.addNewValueForDevice(deviceID, value, month, year);

		if (newValue != -1)
			clientRepository.refreshClientDataWithDeviceData(deviceID, month, year, newValue);
		return newValue;
	}

}
