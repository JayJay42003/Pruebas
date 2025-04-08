package com.practica1.base;

import com.practica1.model.*;
import com.practica1.model.DAO.CarCRUD;
import com.practica1.model.DAO.ConcessionaireCRUD;
import com.practica1.model.DAO.MotorcycleCRUD;
import com.practica1.model.DAO.VehicleCRUD;
import com.practica1.service.SvcConcessionaire;

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
            ConcessionaireCRUD concessionaireCRUD = new ConcessionaireCRUD(dbC);
            VehicleCRUD vehicleCRUD = new VehicleCRUD(dbC);
            MotorcycleCRUD motorcycleCRUD = new MotorcycleCRUD(dbC);
            CarCRUD carCRUD = new CarCRUD(dbC);

        /*concessionaireCRUD.createObject("conce1",1);
        concessionaireCRUD.createObject("conce2",15);
        motorcycleCRUD.createObject(new Motorcycle("5704GPN","KTM","125 Duke",LocalDate.of(2012,10,3),FuelType.GASOLINE.name(),125,1,1));
        motorcycleCRUD.createObject(new Motorcycle("5704GPO","KTM","125 Duke",LocalDate.of(2012,10,3),FuelType.GASOLINE.name(),125,0,2));
        motorcycleCRUD.createObject(new Motorcycle("5705BBB","KTM","125 Duke",LocalDate.of(2012,10,3),FuelType.GASOLINE.name(),125,0,1));
        carCRUD.createObject(new Car("5704GPO","Mercedes","CLA Coupé",LocalDate.of(2004,6,23), FuelType.HYBRID.name(), 4,1,1));
        carCRUD.createObject(new Car("5704GPN","Mercedes","CLA Coupé",LocalDate.of(2004,6,23), FuelType.HYBRID.name(), 4,2,0));
        carCRUD.createObject(new Car("5705AAA","Mercedes","CLA Coupé",LocalDate.of(2004,6,23), FuelType.HYBRID.name(), 4,1,0));
        */
            Car car = carCRUD.readObjectbyLicense("5704GPN");
            car.showInfo();
            concessionaireCRUD.readObject(car.getIdConcessionaire()).showInfo();

            carCRUD.deleteObjectbyLicense("5704GPN");
            carCRUD.readObjectbyLicense("5704GPN").showInfo();

            carCRUD.updateObjectbyLicense("5704GPO", new Car("5704GPO", "Fiat", "Punto", LocalDate.of(2002, 10, 15), FuelType.GASOLINE.name(), 4));

            List<Car> cars = carCRUD.readAll();
            for (Car c : cars) {
                c.showInfo();
            }


            carCRUD.readAllbyConcessionaire("conce1").forEach(Car::showInfo);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}