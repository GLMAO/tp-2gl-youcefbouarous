package com.polytech.tp;

public class Responsable implements Observer {
    private String nom;

    public Responsable(String nom) {
        this.nom = nom;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification pour le responsable " + nom + " : " + message);
    }
    
    public String getNom() {
        return nom;
    }
}