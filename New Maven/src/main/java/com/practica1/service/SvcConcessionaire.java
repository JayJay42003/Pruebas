package com.practica1.service;

import com.practica1.model.*;
import com.practica1.model.common.brandSetEmptyException;
import com.practica1.model.common.licenseAlreadyAddedException;
import com.practica1.model.common.licenseNotAddedException;
import com.practica1.model.common.typeDontExistsException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SvcConcessionaire extends Concessionaire{
    private Concessionaire concessionaire;

    public SvcConcessionaire(Concessionaire concessionaire) {
        this.concessionaire = concessionaire;
    }

    //Añade el vehículo en las 3 colecciones diferentes de ArrayList, HashMap y Set
    public  void addVehicle(Vehicle vehicle) {
        try {
            if (concessionaire.getVehiclesHashMap().containsKey(vehicle.getLicensePlate())) {
                throw new licenseAlreadyAddedException(vehicle.getLicensePlate());
            }
            concessionaire.addToVehicleArrayList(vehicle);
            concessionaire.addToVehiclesHashmap(vehicle.getLicensePlate(), vehicle);
            concessionaire.addToBrandSet(vehicle.getBrand());

            System.out.println("Vehiculo añadido al concesionario");

        } catch (licenseAlreadyAddedException e) {
            System.err.println(e.getMessage());
        }
    }

    //Busca un vehículo por su matricula
    public  Vehicle findVehicleByLicensePlate(String licensePlate) {
        try {
            if (!concessionaire.getVehiclesHashMap().containsKey(licensePlate)) {
                throw new licenseNotAddedException(licensePlate);
            }
        } catch (licenseNotAddedException e) {
            System.err.println(e.getMessage());
            return null;
        }

        return concessionaire.getVehiclesHashMap().get(licensePlate);
    }

    //Da una lista sin duplicados de las matrículas, sin acceder directamente a la misma
    public  Set<String> getAllLicensePlates() {
        try {
            if (concessionaire.getBrandSet().isEmpty()) {
                throw new brandSetEmptyException();
            }
        } catch (brandSetEmptyException b) {
            System.err.println(b.getMessage());
            return null;
        }
        return new HashSet<>(concessionaire.getBrandSet());
    }

    //Busca un conjunto de vehiculos de la clase dada
    public  List<Vehicle> filterByType(Class<?> tipo) {
        try {
            if (!tipo.isInstance(Vehicle.class)) {
                throw new typeDontExistsException(tipo);
            }
        } catch (typeDontExistsException t) {
            System.err.println(t.getMessage());
            return null;
        }


        List<Vehicle> vehiclesReturned = new ArrayList<>();

        for (Vehicle v : concessionaire.getVehiclesArrayList()) {
            if (tipo.equals(Motorcycle.class) && v instanceof Motorcycle) {
                vehiclesReturned.add(v);
            } else if (tipo.equals(Car.class) && v instanceof Car) {
                vehiclesReturned.add(v);
            }
        }

        return vehiclesReturned;
    }

    //Borra las instancias del vehículo que tiene esa matrícula
    public  void deleteByLicensePlate(String licensePlate) {
        try {
            if (!concessionaire.getVehiclesHashMap().containsKey(licensePlate)) {
                throw new licenseNotAddedException(licensePlate);
            }

            Vehicle v = concessionaire.removeFromVehicleHashmap(licensePlate);
            concessionaire.removeFromVehicleArrayList(v);
            concessionaire.removeFromBrandSet(v.getLicensePlate());

        } catch (licenseNotAddedException e) {
            System.err.println(e.getMessage());
        }
    }
}
