package com.epf.rentmanager.model;

import java.time.LocalDate;


public class Reservation {
    private long id;

    private Client client;
    private Vehicle vehicle;
    private LocalDate start;
    private LocalDate end;

    public Reservation(long id, Client client, Vehicle vehicle, LocalDate start, LocalDate end) {

        this.id = id;
        this.client = client;
        this.vehicle = vehicle;
        this.start = start;
        this.end= end;

    }

    public Reservation(Client client, Vehicle vehicle, LocalDate start, LocalDate end) {

        client = client;
        vehicle = vehicle;
        start = start;
        end= end;
    }

    public long getId() {
        return this.id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setVehicleId(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client=" + client +
                ", vehicle=" + vehicle +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}