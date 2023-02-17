package com.epf.rentmanager.model;

public class Vehicle {

    private long id;

    private String constructeur;

    private int nb_places;

    public Vehicle(long id, String constructeur, int nb_places) {
        this.id = id;
        this.constructeur = constructeur;
        this.nb_places = nb_places;
    }
    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    public int getNbr_places() {
        return nb_places;
    }

    public void setNbr_places(int nb_places) {
        this.nb_places = nb_places;
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
                ", constructeur='" + constructeur + '\'' +
                ", nb_places=" + nb_places +
                '}';
    }
}
