package com.practica1.model;

import java.util.*;

public class Concessionaire {
    private ArrayList<Vehicle> vehiclesArrayList;
    private HashMap<String,Vehicle> vehiclesHashMap;
    private Set<String> brandSet;

    public Concessionaire() {
        this.vehiclesArrayList = new ArrayList<>();
        this.vehiclesHashMap = new HashMap<>();
        this.brandSet = new HashSet<>();
    }

    public ArrayList<Vehicle> getVehiclesArrayList() {
        return vehiclesArrayList;
    }

    public void addToVehicleArrayList(Vehicle vehicle) {
        this.vehiclesArrayList.add(vehicle);
    }

    public void removeFromVehicleArrayList(Vehicle vehicle){
        this.vehiclesArrayList.remove(vehicle);
    }

    public HashMap<String, Vehicle> getVehiclesHashMap() {
        return vehiclesHashMap;
    }

    public void addToVehiclesHashmap(String str,Vehicle vehicle) {
        this.vehiclesHashMap.put(str,vehicle);
    }

    public Vehicle removeFromVehicleHashmap(String licensePlate){
        return this.vehiclesHashMap.remove(licensePlate);
    }

    public Set<String> getBrandSet() {
        return brandSet;
    }

    public void addToBrandSet(String str) {
        this.brandSet.add(str);
    }

    public void removeFromBrandSet(String brand){
        this.brandSet.remove(brand);
    }
}
