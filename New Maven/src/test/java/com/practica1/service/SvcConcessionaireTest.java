package com.practica1.service;

import com.practica1.model.*;
import com.practica1.model.common.FuelType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class SvcConcessionaireTest {

    @Mock
    private Concessionaire concessionaireTest;
    @InjectMocks
    SvcConcessionaire svcConcessionaireTest;
    @Mock
    private Car carTest;
    @Mock
    private HashMap<String,Vehicle> hasM;


    @Test
    @DisplayName("Test para añadir vehiculo")
    void addVehicle() {
        //when
        svcConcessionaireTest.addVehicle(carTest);
        //then
        Mockito.verify(concessionaireTest).addToVehicleArrayList(carTest);
        Mockito.verify(concessionaireTest).addToBrandSet(carTest.getBrand());
        Mockito.verify(concessionaireTest).addToVehiclesHashmap(carTest);
    }

    @Test
    @DisplayName("Test para encontrar vehiculo por matricula")
    void findVehicleByLicensePlate() {
        String license="22345HGG";
        BDDMockito.given(concessionaireTest.getVehiclesHashMap()).willReturn(hasM);

        //when
        svcConcessionaireTest.findVehicleByLicensePlate(license);
        //then
        Mockito.verify(hasM).get(license);
    }

    @Test
    void getAllLicensePlates() {
        //when
        svcConcessionaireTest.getAllLicensePlates();
        //then
        Mockito.verify(concessionaireTest).getBrandSet();
    }

    @Test
    void filterByTypeCar() {
        Concessionaire concessionaire=new Concessionaire();
        SvcConcessionaire svcConcessionaire1=new SvcConcessionaire(concessionaire);


        Car car1 = new Car("5704GPN", "Toyota", "Corolla", LocalDate.of(2020, 1, 15), FuelType.GASOLINE.name(), 4);
        Car car2 = new Car("5704GPO", "Tesla", "Model 3", LocalDate.of(2022, 6, 10), FuelType.ELECTRIC.name(), 4);

        concessionaire.addToVehicleArrayList(car1);
        concessionaire.addToVehicleArrayList(car2);

        List<Vehicle> result = svcConcessionaire1.filterByType(Car.class);

        assertEquals(2, result.size());
        assertTrue(result.contains(car1));
        assertTrue(result.contains(car2));
    }

    @Test
    void filterByTypeMotorcycle() {
        Concessionaire concessionaire=new Concessionaire();
        SvcConcessionaire svcConcessionaire1=new SvcConcessionaire(concessionaire);

        Vehicle moto1 = new Motorcycle("5704GPN", "Honda", "CBR500R", LocalDate.of(2021, 7, 12), FuelType.DIESEL.name(), 471);
        Vehicle moto2 = new Motorcycle("3333CCC", "Kawasaki", "Ninja 400", LocalDate.of(2020, 5, 30), FuelType.GASOLINE.name(), 399);

        concessionaire.addToVehicleArrayList(moto1);
        concessionaire.addToVehicleArrayList(moto2);

        List<Vehicle> result = svcConcessionaire1.filterByType(Motorcycle.class);

        assertEquals(2, result.size());
        assertTrue(result.contains(moto1));
        assertTrue(result.contains(moto2));
    }

    @Test
    void deleteByLicensePlate() {
        Concessionaire concessionaire=new Concessionaire();
        SvcConcessionaire svcConcessionaire1=new SvcConcessionaire(concessionaire);

        Vehicle moto1 = new Motorcycle("5704GPN", "Honda", "CBR500R", LocalDate.of(2021, 7, 12), FuelType.DIESEL.name(), 471);
        Vehicle moto2 = new Motorcycle("3333CCC", "Kawasaki", "Ninja 400", LocalDate.of(2020, 5, 30), FuelType.GASOLINE.name(), 399);

        svcConcessionaire1.addVehicle(moto1);
        svcConcessionaire1.addVehicle(moto2);
        //when
        svcConcessionaire1.deleteByLicensePlate(moto1.getLicensePlate());
        //then
        assertFalse(concessionaire.getVehiclesHashMap().containsKey(moto1.getLicensePlate()));
        assertFalse(concessionaire.getBrandSet().contains(moto1.getBrand()));
        assertFalse(concessionaire.getVehiclesArrayList().contains(moto1));
    }
}