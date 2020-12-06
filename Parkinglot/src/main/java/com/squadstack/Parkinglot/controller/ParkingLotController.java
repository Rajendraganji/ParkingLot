package com.squadstack.Parkinglot.controller;

import com.squadstack.Parkinglot.domain.ParkingLot;
import com.squadstack.Parkinglot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;

public class ParkingLotController {

    private final ParkingLotService parkinglotservice;

    @Autowired
    public ParkingLotController(ParkingLotService parkinglotservice) {
        this.parkinglotservice = parkinglotservice;
    }

    static int ticketNumber = 1;

    public void processInputFile(String filePath) {
        try {
            File path = new File(filePath);
            filePath = path.getAbsolutePath();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(filePath));
            HashMap<Integer, String> slots = new HashMap<>();
            HashSet<String> vehicleNumbersParked=new HashSet<>();
            int totalSlots = 0;
            boolean areSlotsCreated = false;
            String line;

            label:
            while ((line = bufferedreader.readLine()) != null) {
                String[] contentOfLine = line.split(" ");
                String prefix = contentOfLine[0];

                try {

                    switch (prefix){

                        case "Create_parking_lot":
                            areSlotsCreated = true;
                            totalSlots = Integer.parseInt(contentOfLine[1]);
                            System.out.println("Created parking of " + totalSlots + " slots");
                            for (int i = 1; i <= totalSlots; i++) {
                                slots.put(i, "Available");
                            }
                            break;

                        case "Park":
                            if (!areSlotsCreated){
                                System.out.println("Slots are not created initially so terminating the code");
                                break label;
                            }
                            int availableSlotNumber = getSlotNumber(slots, totalSlots);
                            ParkingLot parkinglot = getParkingDetails(availableSlotNumber, contentOfLine, slots,vehicleNumbersParked);
                            if (parkinglot != null) {
                                parkinglotservice.saveParkingDetails(parkinglot);
                            }
                            break;

                        case "Slot_numbers_for_driver_of_age":
                            parkinglotservice.findSlotNumbersByDriverAge(Integer.parseInt(contentOfLine[1]));
                            break;

                        case "Slot_number_for_car_with_number":
                            parkinglotservice.findSlotNumbersByVehicleNumber(contentOfLine[1]);
                            break;

                        case "Leave":
                            if (!areSlotsCreated){
                                System.out.println("Slots are not created initially so terminating the code");
                                break label;
                            }
                            int slotNumber = Integer.parseInt(contentOfLine[1]);
                            if(slotNumber>totalSlots){
                                System.out.println("The slot "+slotNumber+" is not present and cannot be vacated");
                            }
                            else if (slots.get(slotNumber).equals("Available")) {
                                System.out.println("Slot " + slotNumber + " is already vacated");
                            }
                            else {
                                slots.put(slotNumber, "Available");
                                parkinglotservice.vacateSlot(slotNumber);
                            }
                            break;

                        case "Vehicle_registration_number_for_driver_of_age":
                            parkinglotservice.findVehicleNumbersByDriverAge(Integer.parseInt(contentOfLine[1]));
                            break;

                        default:
                            System.out.println("This command in a file is not in right format ");
                            break;
                    }
                }
                catch (Exception ex) {
                    System.out.println("This command is not in right format");
                }
            }
        }
        catch (Exception ex){
            System.out.println("File not exists or finename is incorrect");
        }
    }

    public static ParkingLot getParkingDetails(int availableSlotNumber,String[] contentOfLine,HashMap<Integer,String> slots,HashSet<String> vehicleNumbersParked){
        ParkingLot parkinglot = new ParkingLot();
        if(availableSlotNumber==0){
            System.out.println("All Slots are full and not available for parking");
        }
        else if(vehicleNumbersParked.contains(contentOfLine[1])){
            System.out.println("This Vehicle is already parked");
        }
        else {
            parkinglot.setTicketNumber(ticketNumber);
            parkinglot.setSlotNumber(availableSlotNumber);
            if(contentOfLine[1].length()==13) {
                parkinglot.setVehicleNumber(contentOfLine[1]);
            }
            else{
                System.out.println("Invalid vehicle number");
                return null;
            }
            parkinglot.setDriverAge(Integer.parseInt(contentOfLine[3]));
            parkinglot.setStatus("Parked");
            slots.put(availableSlotNumber,"Occupaid");
            vehicleNumbersParked.add(contentOfLine[1]);
            ticketNumber++;
            return parkinglot;
        }
        return null;
    }

    public static int getSlotNumber(HashMap<Integer,String> slots,int totalSlots){
        int slotNumber =0;
        for(int i=1;i<=totalSlots;i++)
        {
            if(slots.get(i).equals("Available")) {
                return i;
            }
        }
        return slotNumber;
    }
}
