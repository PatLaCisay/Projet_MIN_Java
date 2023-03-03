package com.epf.rentmanager.model;

public class Vehicle {

    private long id;

    private String constructor;

    private int seats;

    public Vehicle(long id, String constructor, int seats) {
        this.id = id;
        this.constructor = constructor;
        this.seats = seats;
    }
    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }

    public int getNbr_places() {
        return seats;
    }

    public void setNbr_places(int seats) {
        this.seats = seats;
    }

    public Vehicle() {
    }

    public long getId(long id) {
        return this.id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", constructor='" + constructor + '\'' +
                ", seats=" + seats +
                '}';
    }
}
