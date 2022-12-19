package com.nikola.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikola.model.ElMeter;
import com.nikola.service.ElMeterService;

@RestController
@RequestMapping("/device")
public class ElMeterController {
    @Autowired
    private ElMeterService deviceService;


    @GetMapping
    public ResponseEntity<ArrayList<ElMeter>> getAllDevices() {
        ArrayList<ElMeter> devices = deviceService.getAllDevices();
        if (devices != null) {
            return new ResponseEntity<>(devices, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Integer> addNewValueForDevice(int deviceID) {
        Integer value = deviceService.addNewValueForDevice(deviceID);
        if (value != -1) {
            return new ResponseEntity<Integer>(value, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
