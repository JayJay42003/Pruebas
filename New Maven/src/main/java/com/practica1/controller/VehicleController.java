package com.practica1.controller;

import com.practica1.model.*;
import com.practica1.model.common.FuelType;
import com.practica1.service.dao.CarDao;
import com.practica1.service.dao.MotorcycleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController{
    DatabaseConnection databaseConnection;
    @Autowired
    CarDao carDao;
    @Autowired
    MotorcycleDao motorcycleDao;

    public VehicleController() {
        this.databaseConnection = new DatabaseConnection();
        this.carDao=new CarDao(databaseConnection);
        this.motorcycleDao=new MotorcycleDao(databaseConnection);
    }

    //Haced un getAll, get por id, filter(que busque solo por los atributos que vienen en el body), insert, update y delete

    @GetMapping("/vehicles/cars")
    public List<Vehicle> getAllCars(){
        return new ArrayList<>(carDao.readAll());
    }

    @GetMapping("/vehicles/motorcycles")
    public List<Vehicle> getAllMotorcycles(){
        return new ArrayList<>(motorcycleDao.readAll());
    }

    @GetMapping("/vehicles/car")
    public Vehicle getCarById(@RequestBody String body){
        return carDao.readObject(Integer.parseInt(body));
    }

    @GetMapping("/vehicles/motorcycle")
    public Vehicle getMotorcycleById(@RequestBody String body){
        return motorcycleDao.readObject(Integer.parseInt(body));
    }

    @GetMapping("/vehicles/filter/car")
    public List<Car> filterCars(@RequestBody Car car) {
        return carDao.filter(car);
    }

    @GetMapping("/vehicles/filter/motorcycle")
    public List<Motorcycle> filterMotorCycles(@RequestBody Motorcycle motorcycle) {
        return motorcycleDao.filter(motorcycle);
    }

    @PostMapping("/vehicles/insert/car")
    public String insertVehicleCar(@RequestBody Car car){
        return carDao.createObject(car);
    }

    @PostMapping("/vehicles/insert/motorcycle")
    public String insertVehicleMotorCycle(@RequestBody Motorcycle motorcycle){
        return motorcycleDao.createObject(motorcycle);
    }

    @PutMapping("/vehicles/update/car")
    public String updateCar(@RequestBody Car vehicle){
        return carDao.updateObject(vehicle);
    }

    @PutMapping("/vehicles/update/motorcycle")
    public String updateMotorCycle(@RequestBody Motorcycle vehicle){
        return motorcycleDao.updateObject(vehicle);
    }

    @DeleteMapping("/vehicles/delete")
    public String deleteVehicle(@RequestBody Vehicle vehicle){
        if(vehicle instanceof Car){
            return carDao.deleteObject(vehicle.getIdVehicle());
        }else if(vehicle instanceof Motorcycle){
            return motorcycleDao.deleteObject(vehicle.getIdVehicle());
        }
        return "Tipo de vehículo desconocido: " + vehicle;
    }
}