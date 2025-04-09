package com.practica1.model;

public class VehicleClass {
    private int idVehicle;
    private String vehicleType;

    public VehicleClass(int idVehicle, String vehicleType) {
        this.idVehicle = idVehicle;
        this.vehicleType = vehicleType;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getIdVehicle() {
        return idVehicle;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}
