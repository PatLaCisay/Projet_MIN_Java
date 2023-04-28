package com.epf.rentmanager.model;

import java.util.Objects;

public class Vehicle {

    private long id;

    private String constructor;

    private int seats;

    private String model;

    public Vehicle(long id, String constructor, int seats, String model) {
        this.id = id;
        this.constructor = constructor;
        this.seats = seats;
        this.model = model;
    }

    public Vehicle(String constructor, int seats, String model) {
        this.constructor = constructor;
        this.seats = seats;
        this.model = model;
    }

    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Vehicle() {
    }

    public long getId(long id) {
        return this.id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", constructor='" + constructor + '\'' +
                ", seats=" + seats +
                '}';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
