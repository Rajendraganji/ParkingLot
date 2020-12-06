package com.squadstack.Parkinglot.service.Impl;

import com.squadstack.Parkinglot.domain.ParkingLot;
import com.squadstack.Parkinglot.repository.ParkingLotRepository;
import com.squadstack.Parkinglot.service.ParkingLotService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotRepository parkinglotrepository;

    public ParkingLotServiceImpl(ParkingLotRepository parkinglotrepository) {
        this.parkinglotrepository = parkinglotrepository;
    }

    @Override
    public void saveParkingDetails(ParkingLot parkinglot) {
        System.out.println("Car with vehicle registration number "+parkinglot.getVehicleNumber()+" has been parked at slot number "+parkinglot.getSlotNumber());
        parkinglotrepository.save(parkinglot);
    }

    @Override
    public void findVehicleNumbersByDriverAge(int driverAge) {
        List<ParkingLot> listOfRecords=parkinglotrepository.findByDriverAge(driverAge);
        if(listOfRecords.size()!=0){
            int i=0;
            for(i=0;i<listOfRecords.size()-1;i++){
                System.out.print(listOfRecords.get(i).getVehicleNumber() + ",");
            }
            System.out.print(listOfRecords.get(i).getVehicleNumber());
            System.out.println();
        }
        else{
            System.out.println("No vehicle is parked by drivers with age "+driverAge);
        }
    }

    @Override
    public void findSlotNumbersByDriverAge(int driverAge) {
        List<ParkingLot> listOfRecords=parkinglotrepository.findByDriverAge(driverAge);
        if(listOfRecords.size()!=0){
            int i=0;
            for(i=0;i<listOfRecords.size()-1;i++){
                System.out.print(listOfRecords.get(i).getSlotNumber() + ",");
            }
            System.out.print(listOfRecords.get(i).getSlotNumber());
            System.out.println();
        }
        else {
            System.out.println("Currently there are no vehicles parked with driver's age "+driverAge);
        }
    }

    @Override
    public void findSlotNumbersByVehicleNumber(String vehicleNumber) {
        List<ParkingLot> listOfRecords=parkinglotrepository.findByVehicleNumber(vehicleNumber);
        if(listOfRecords.size()!=0){
            System.out.println(listOfRecords.get(0).getSlotNumber());
        }
        else {
            System.out.println("Currently there are no vehicles parked with registration number "+vehicleNumber);
        }
    }

    @Override
    public void vacateSlot(int slotNumber) {
        List<ParkingLot> listOfRecords=parkinglotrepository.findBySlotNumber(slotNumber);
        if(listOfRecords.size()!=0) {
            ParkingLot parkinglot=listOfRecords.get(0);
            System.out.println("Slot number " + slotNumber + " vacated, the car with vehicle registration number " + parkinglot.getVehicleNumber() + " left the space, the driver of the car was of age " + parkinglot.getDriverAge());
            parkinglotrepository.delete(parkinglot);
        }
        else {
            System.out.println("Slot is already vacant");
        }
    }
}
