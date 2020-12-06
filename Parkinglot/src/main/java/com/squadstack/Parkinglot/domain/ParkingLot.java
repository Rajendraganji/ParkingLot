package com.squadstack.Parkinglot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParkingLot {
    @Id
    int ticketNumber;

    int slotNumber;

    @Column(length = 13)
    String vehicleNumber;

    int driverAge;

    String status;

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "ticketNumber=" + ticketNumber +
                ", slotNumber=" + slotNumber +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", driverAge=" + driverAge +
                ", status='" + status + '\'' +
                '}';
    }
}
