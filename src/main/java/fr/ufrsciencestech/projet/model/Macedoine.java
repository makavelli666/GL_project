/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.model;

import java.util.ArrayList;

public class Macedoine implements Fruit{
    private double prix;
    private ArrayList<Fruit> fruits;
    
    public Macedoine() 
    {
        this.fruits = new ArrayList<Fruit>();
        this.prix = 0;
    }
    
    public Macedoine(Fruit f) 
    {
        this.fruits = new ArrayList<Fruit>();
        this.prix = 0;
        this.fruits.add(f);
    }
    
    public Macedoine(Fruit f1, Fruit f2) 
    {
        this.fruits = new ArrayList<Fruit>();
        this.prix = 0;
        this.fruits.add(f1);
        this.fruits.add(f2);
    }
    
    public void ajoute(Fruit f) 
    {
        this.fruits.add(f);
    }
        
    @Override
    public double getPrix(){
        prix=0.0;
        for(int i=0; i<fruits.size();i++){
            prix=prix+fruits.get(i).getPrix();
        }
	return prix;
    }

    @Override
    public void setPrix(double prix){
	this.prix=prix;
    }
    
    public ArrayList<Fruit> getFruits(){
	return fruits;
    }
 
    public void setFruits(ArrayList<Fruit> f){
	this.fruits=f;
    }

    @Override
    public String getOrigine(){
	return "";
    }
 
    @Override
    public void setOrigine(String origine){
    }

    @Override
    public String toString() {
    String Noms="";
    for (int i=0; i<fruits.size(); i++) {
        if(i>0){
            Noms+=", ";
        }
        Noms+=fruits.get(i).getClass().getSimpleName();
    }
    return "Macedoine de (" + Noms + ") a " + getPrix() + " €";
}

    @Override
    public boolean equals(Object o){  //predicat pour tester si 2 Fraises sont equivalentes
        if(o != null && getClass() == o.getClass()){
            Macedoine or = (Macedoine) o;
            return (prix == or.prix && fruits.equals(or.fruits));
        }
        return false;
    }

    @Override
    public boolean isSeedless() {  //predicat indiquant qu'un fruit a des pepins
        return false;
    }

}