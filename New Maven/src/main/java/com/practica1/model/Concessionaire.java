package com.practica1.model;

import java.util.*;

public class Concessionaire {
    private int id;
    private String name;
    private int numVehicles;
    private ArrayList<Vehicle> vehiclesArrayList;
    private HashMap<String, Vehicle> vehiclesHashMap;
    private Set<String> brandSet;

    public Concessionaire(int id, String name, int numVehicles) {
        this.vehiclesArrayList = new ArrayList<>();
        this.vehiclesHashMap = new HashMap<>();
        this.brandSet = new HashSet<>();
        this.id = id;
        this.name = name;
        this.numVehicles = numVehicles;
    }

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

    public void removeFromVehicleArrayList(Vehicle vehicle) {
        this.vehiclesArrayList.remove(vehicle);
    }

    public HashMap<String, Vehicle> getVehiclesHashMap() {
        return vehiclesHashMap;
    }

    public void addToVehiclesHashmap(Vehicle vehicle) {
        this.vehiclesHashMap.put(vehicle.getLicensePlate(), vehicle);
    }

    public Vehicle removeFromVehicleHashmap(String licensePlate) {
        return this.vehiclesHashMap.remove(licensePlate);
    }

    public Set<String> getBrandSet() {
        return brandSet;
    }

    public void addToBrandSet(String str) {
        this.brandSet.add(str);
    }

    public void removeFromBrandSet(String brand) {
        this.brandSet.remove(brand);
    }

    @Override
    public String toString() {
        return "Concessionaire{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numVehicles=" + numVehicles +
                '}';
    }

    public void showInfo() {
        try {
            System.out.println(this);
        } catch (NullPointerException nullP) {
            System.out.println("El objeto es nulo");
        }
    }
}
