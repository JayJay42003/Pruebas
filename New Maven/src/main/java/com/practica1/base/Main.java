package com.practica1.base;

import com.practica1.model.*;
import com.practica1.model.common.FuelType;
import com.practica1.service.dao.CarDao;
import com.practica1.service.dao.ConcessionaireDao;
import com.practica1.service.dao.MotorcycleDao;
import com.practica1.service.dao.VehicleDao;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //PRC-17-----------------------
        try {
            DatabaseConnection dbC = new DatabaseConnection();
            dbC.initializeConnection();
            //Primera inyección de vehiculos.
            //dbC.insertInfo();
            ConcessionaireDao concessionaireDAO = new ConcessionaireDao(dbC);
            VehicleDao vehicleDAO = new VehicleDao(dbC);
            MotorcycleDao motorcycleDAO = new MotorcycleDao(dbC);
            CarDao carDAO = new CarDao(dbC);

            //Crear concesionarios
            concessionaireDAO.createObject("conce1", 1);
            concessionaireDAO.createObject("conce2", 15);

            //Crear vehiculos
            motorcycleDAO.createObject(new Motorcycle(1, 1, "5704GPO", "Yamaha", "MT-07", LocalDate.of(2019, 3, 20), FuelType.GASOLINE.name(), 689));
            motorcycleDAO.createObject(new Motorcycle(2, 2, "5704GPN", "Honda", "CBR500R", LocalDate.of(2021, 7, 12), FuelType.DIESEL.name(), 471));
            motorcycleDAO.createObject(new Motorcycle(3, 1, "3333CCC", "Kawasaki", "Ninja 400", LocalDate.of(2020, 5, 30), FuelType.GASOLINE.name(), 399));
            carDAO.createObject(new Car(1, 1, "5704GPN", "Toyota", "Corolla", LocalDate.of(2020, 1, 15), FuelType.GASOLINE.name(), 4));
            carDAO.createObject(new Car(2, 2, "5704GPO", "Tesla", "Model 3", LocalDate.of(2022, 6, 10), FuelType.ELECTRIC.name(), 4));
            carDAO.createObject(new Car(3, 2, "9101GHI", "Volkswagen", "Golf", LocalDate.of(2018, 9, 5), FuelType.DIESEL.name(), 5));

            //Leer coche por matricula y su concesionario
            Car car = carDAO.readObjectbyLicense("5704GPN");
            car.showInfo();
            concessionaireDAO.readObject(car.getIdConcessionaire()).showInfo();

            //Borrar vehiculo por matricula y comprobar si existe
            carDAO.deleteObjectbyLicense("5704GPN");
            carDAO.readObjectbyLicense("5704GPN").showInfo();

            //Actualizar vehiculo por matricula
            carDAO.updateObjectbyLicense("5704GPO", new Car("5704GPO", "Fiat", "Punto", LocalDate.of(2002, 10, 15), FuelType.GASOLINE.name(), 4));

            //Mostrar todos los coches
            List<Car> cars = carDAO.readAll();
            for (Car c : cars) {
                c.showInfo();
            }

            //Mostrar todos los coches por concesionario
            carDAO.readAllbyConcessionaire("conce1").forEach(Car::showInfo);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}