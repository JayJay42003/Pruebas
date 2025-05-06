package com.practica1.controller;

import com.practica1.model.*;
import com.practica1.service.dao.CarDao;
import com.practica1.service.dao.MotorcycleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {
    DatabaseConnection databaseConnection;
    @Autowired
    CarDao carDao;
    @Autowired
    MotorcycleDao motorcycleDao;

    public VehicleController() {
        this.databaseConnection = new DatabaseConnection();
        this.carDao = new CarDao(databaseConnection);
        this.motorcycleDao = new MotorcycleDao(databaseConnection);
    }

    //Haced un getAll, get por id, filter(que busque solo por los atributos que vienen en el body), insert, update y delete

    @GetMapping("/vehicles/cars")
    public ResponseEntity<?> getAllCars() {
        ArrayList<Car> arr = new ArrayList<>(carDao.readAll());
        if (arr.isEmpty()) {
            return ResponseEntity.badRequest().body("This arraylist is empty");
        } else {
            return ResponseEntity.ok(arr);
        }
    }

    @GetMapping("/vehicles/motorcycles")
    public ResponseEntity<?> getAllMotorcycles() {
        ArrayList<Motorcycle> arr = new ArrayList<>(motorcycleDao.readAll());
        if (arr.isEmpty()) {
            return ResponseEntity.badRequest().body("This arraylist is empty");
        } else {
            return ResponseEntity.ok(arr);
        }
    }

    @GetMapping("/vehicles/car")
    public ResponseEntity<?> getCarById(@RequestBody String body) {
        try {
            return ResponseEntity.ok(carDao.readObject(Integer.parseInt(body)));
        } catch (NumberFormatException numberFormatException) {
            System.err.println(numberFormatException.getMessage());
            return ResponseEntity.badRequest().body(numberFormatException.getMessage());
        }
    }

    @GetMapping("/vehicles/motorcycle")
    public ResponseEntity<?> getMotorcycleById(@RequestBody String body) {
        try {
            return ResponseEntity.ok(motorcycleDao.readObject(Integer.parseInt(body)));
        } catch (NumberFormatException numberFormatException) {
            System.err.println(numberFormatException.getMessage());
            return ResponseEntity.badRequest().body(numberFormatException.getMessage());
        }
    }

    @GetMapping("/vehicles/filter/car")
    public ResponseEntity<?> filterCars(@RequestBody Car car) {
        List<Car> cars = carDao.filter(car);
        if (cars.isEmpty()) {
            return ResponseEntity.badRequest().body("No se ha devuelto ningún coche");
        } else {
            return ResponseEntity.ok(cars);
        }
    }

    @GetMapping("/vehicles/filter/motorcycle")
    public ResponseEntity<?> filterMotorCycles(@RequestBody Motorcycle motorcycle) {
        List<Motorcycle> motorcycles = motorcycleDao.filter(motorcycle);
        if (motorcycles.isEmpty()) {
            return ResponseEntity.badRequest().body("No se ha devuelto ningún coche");
        } else {
            return ResponseEntity.ok(motorcycles);
        }
    }

    @PostMapping("/vehicles/insert/car")
    public ResponseEntity<?> insertVehicleCar(@RequestBody Car car) {
        try {
            String result = carDao.createObject(car);

            if (result.isEmpty()) {
                return ResponseEntity
                        .badRequest().body("Error al insertar el coche. No se devolvió resultado.");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Datos inválidos: " + e.getMessage());
        }
    }

    @PostMapping("/vehicles/insert/motorcycle")
    public ResponseEntity<?> insertVehicleMotorCycle(@RequestBody Motorcycle motorcycle) {
        try {
            String result = motorcycleDao.createObject(motorcycle);

            if (result.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error al insertar el coche. No se devolvió resultado.");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(result);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Datos inválidos: " + e.getMessage());

        }
    }

    @PutMapping("/vehicles/update/car")
    public ResponseEntity<?> updateCar(@RequestBody Car vehicle) {
        if (vehicle.getIdVehicle() == 0) {
            return ResponseEntity.badRequest().body("No se ha dado un id de vehiculo");
        } else {
            String r = carDao.updateObject(vehicle);
            if (r.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(r);
            }

        }
    }

    @PutMapping("/vehicles/update/motorcycle")
    public ResponseEntity<?> updateMotorCycle(@RequestBody Motorcycle vehicle) {
        if (vehicle.getIdVehicle() == 0) {
            return ResponseEntity.badRequest().body("No se ha dado un id de vehiculo");
        } else {
            String r = motorcycleDao.updateObject(vehicle);
            if (r.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(r);
            }

        }
    }

    @DeleteMapping("/vehicles/delete/car")
    public ResponseEntity<?> deleteCar(@RequestBody Car vehicle) {
        try {
            String r = carDao.deleteObject(vehicle.getIdVehicle());
            if (r.isEmpty()) {
                return ResponseEntity.badRequest().body("No se ha borrado ningun coche");
            } else {
                return ResponseEntity.ok(r);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Datos inválidos: " + e.getMessage());
        }
    }

    @DeleteMapping("/vehicles/delete/motorcycle")
    public ResponseEntity<?> deleteMotorcycle(@RequestBody Motorcycle vehicle) {
        try {
            String r = motorcycleDao.deleteObject(vehicle.getIdVehicle());
            if (r.isEmpty()) {
                return ResponseEntity.badRequest().body("No se ha borrado ninguna moto");
            } else {
                return ResponseEntity.ok(r);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Datos inválidos: " + e.getMessage());
        }
    }


}