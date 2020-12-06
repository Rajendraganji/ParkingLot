package com.squadstack.Parkinglot.service;

import com.squadstack.Parkinglot.domain.ParkingLot;

public interface ParkingLotService {
    void saveParkingDetails(ParkingLot parking);

    void findVehicleNumbersByDriverAge(int age);

    void findSlotNumbersByDriverAge(int age);

    void findSlotNumbersByVehicleNumber(String vehicleno);

    void vacateSlot(int slotNumber);
}
