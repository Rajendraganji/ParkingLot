package com.squadstack.Parkinglot.repository;

import com.squadstack.Parkinglot.domain.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {

    List<ParkingLot> findByDriverAge(int driverAge);

    List<ParkingLot> findByVehicleNumber(String vehicleNumber);

    List<ParkingLot> findBySlotNumber(int slotNumber);

}
