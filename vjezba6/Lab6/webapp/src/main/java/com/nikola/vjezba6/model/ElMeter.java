package com.nikola.vjezba6.model;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;



public class ElMeter {
private static AtomicInteger ID_GENERATOR = new AtomicInteger(100);
	
	private int smartDeviceID;
	private List<Usage> history;
	//private Map<Pair<Integer,Integer>, Integer> history; //par mjesec,godina, a value je vrijednost ocitanja
	
	public ElMeter() {
		super();
		this.smartDeviceID = ID_GENERATOR.getAndIncrement();
		//this.history = new HashMap<Pair<Integer,Integer>, Integer>();
		this.history = new ArrayList<Usage>();
	}
	
	public ElMeter(int smartDeviceID) {
		super();
		this.smartDeviceID = smartDeviceID;
		//this.history = new HashMap<Pair<Integer,Integer>, Integer>();
		this.history = new ArrayList<Usage>();
	}
	
	public ElMeter(ArrayList<Usage> history) {
		super();
		this.smartDeviceID = ID_GENERATOR.getAndIncrement();
		this.history = history;
	}
	public ElMeter(int smartDeviceID, int value, ArrayList<Usage> history) {
		super();
		this.smartDeviceID = smartDeviceID;
		this.history = history;
	}
	public int getSmartDeviceID() {
		return smartDeviceID;
	}
	public void setSmartDeviceID(int smartDeviceID) {
		this.smartDeviceID = smartDeviceID;
	}

	public List<Usage> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<Usage> history) {
		this.history = history;
	}

}