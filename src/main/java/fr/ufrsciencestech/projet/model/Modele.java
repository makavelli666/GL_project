/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.model;

import fr.ufrsciencestech.projet.view.VueConsole;
import fr.ufrsciencestech.projet.view.VueG;
import fr.ufrsciencestech.projet.view.VueGraphiqueListe;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observer;

public class Modele extends Observable{
    private int compteur;   //compteur toujours positif
    PropertyChangeSupport support;

    public Modele(){
        support = new  PropertyChangeSupport(this);
        compteur = 0;
    }

    public void update(int incr) {
        if(incr==2){
            this.compteur=0;
        }else{
            int old = this.compteur;
            compteur += incr;
            if(compteur<0){
                compteur=0;
            }
            System.out.println("compteur = "+compteur);
            support.firePropertyChange("value",old,this.compteur);
            setChanged();
            notifyObservers(getCompteur());
        }
    }
    /**
     * @return the compteur
     */
    public int getCompteur() {
        return compteur;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void setCompteur(int newCounter) {
        int old = this.compteur;
        this.compteur = newCounter;
        if(this.compteur<0){
            this.compteur=0;
        }
        support.firePropertyChange("value", old, newCounter);
        setChanged();
        notifyObservers(getCompteur());
    }
}

