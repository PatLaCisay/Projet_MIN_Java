package com.epf.rentmanager.model;

public class Vehicule {

    private final long id;

    private String constructeur;

    private int nb_places;

    public Vehicule(long id, String constructeur, int nb_places) {
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


}
