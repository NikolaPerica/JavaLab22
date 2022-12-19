package com.nikola.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikola.model.ElMeter;
import com.nikola.repository.ClientRepository;
import com.nikola.repository.ElMeterRepository;

@Service
public class ElMeterService {
    @Autowired
    ElMeterRepository devRepository;
    @Autowired
    ClientRepository clRepository;

    public ElMeter findDeviceByID(int deviceID) {
        return devRepository.findDeviceByID(deviceID);

    }
    public int addNewValueForDevice(int deviceID) {
        int newValue = devRepository.addNewValueForDevice(deviceID);
        clRepository.refreshClientDataWithDeviceData(deviceID, newValue);
        return newValue;
    }

    public ArrayList<ElMeter> getAllDevices() {
        return devRepository.getAllDevices();
    }
    public ElMeter registerNewDevice() {
        return devRepository.registerNewDevice();
    }

}
