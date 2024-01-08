package fr.ufrsciencestech.projet.model;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.beans.PropertyChangeSupport;
import java.util.Observable;

public class Panier extends Observable{
    private ArrayList<Fruit> fruits  = new ArrayList<Fruit>();  //attribut pour stocker les fruits
    private int contenanceMax=10;        //nb maximum d'oranges que peut contenir le panier
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private Modele modele;
    
    //groupe 1
     public Panier(){  //initialise un panier vide ayant une contenance maximale de 20 
       this.fruits = new ArrayList<Fruit>();
       this.contenanceMax = contenanceMax;
    }
        
    public Panier(int contMax){  //initialise un panier vide ayant une certaine contenance maximale (precisee en parametre)
       this.fruits = new ArrayList<Fruit>();
       this.contenanceMax = contMax;
    }

    @Override
    public String toString() { // affichage de ce qui est contenu dans le panier : liste des fruits presents
        String res = "";
        String newLine = System.getProperty("line.separator");
        for (int i = 0; i < fruits.size(); i++) {
            res += fruits.get(i).toString() + newLine;
        }
        return res;
    }
    
    //groupe 2
    public ArrayList<Fruit> getFruits() {  //accesseur du premier attribut
       return fruits;
    }

    public void setFruits(ArrayList<Fruit> fruits) { //modificateur du premier attribut
      this.fruits = fruits;
    }

    public int getTaillePanier(){  //accesseur retournant la taille allouee pour l'attibut fruits
        return fruits.size();
    }
    
    public int getContenanceMax(){  //accesseur du second attribut
	return contenanceMax;
    }
    
    public void setContenanceMax(int x){  //setteur du second attribut
        this.contenanceMax=x;
    }
        
    //groupe 3
    public Fruit getFruit(int i){  //accesseur retournant le fruit contenu dans le panier a l'emplacement n°i ou null s'il n'y a rien a cet emplacement
	return fruits.get(i);
    }
    
    public void setFruit(int i, Fruit f){  //modificateur du fruit contenu dans le panier a l'emplacement n°i par f (s'il y a bien deja un fruit a cet emplacement, ne rien faire sinon)
        if(fruits.get(i) == null) {
            fruits.set(i, f);
        }
    }
    
    public boolean estVide(){  //predicat indiquant que le panier est vide
	return fruits.isEmpty();
    }
    
    public boolean estPlein(){  //predicat indiquant que le panier est plein
	return fruits.size() == contenanceMax;
    }

    //groupe 4

 
    public void ajout() throws PanierPleinException {
        if (fruits.size() < contenanceMax) {
            // Ajouter un fruit fictif (vous pourriez ajouter un fruit spécifique ici)
            Fruit fruitAjoute = new Orange();  // Assurez-vous de remplacer Fruit() par le type de fruit que vous souhaitez ajouter
            fruits.add(fruitAjoute);
        } else {
            throw new PanierPleinException();
        }
}


    public void ajout(Fruit o) throws PanierPleinException{  //ajoute le fruit o a la fin du panier si celui-ci n'est pas plein
        if (fruits.size() < contenanceMax) {
        fruits.add(o);
        }
       else {
            throw new PanierPleinException();
        }
    }

    //groupe 5
    public void retrait() throws PanierVideException{ //retire le dernier fruit du panier si celui-ci n'est pas vide
        if(!fruits.isEmpty()){
            int removedIndex = fruits.size() - 1;
            fruits.remove(removedIndex);
        } else{
            System.out.println("le panier est vide");
        } 
    }

    
    //groupe 6
    public double getPrix() {
        double prixTotal = 0;
        for (Fruit fruit : fruits) {
            prixTotal += fruit.getPrix(); // Assurez-vous que la classe Fruit a une méthode getPrix()
        }
        return prixTotal;
    }

    
    //groupe 7
    public void boycotteOrigine(String origine) {
        ArrayList<Fruit> fruitsRetenus = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (!fruit.getOrigine().equalsIgnoreCase(origine)) {
                fruitsRetenus.add(fruit);
            }
        }
        fruits = fruitsRetenus;
    }
    //groupe 8    
    @Override
    public boolean equals(Object o){  ///predicat pour tester si 2 paniers sont equivalents : s'ils contiennent exactement les memes fruits
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panier panier = (Panier) o;
        return Objects.equals(fruits, panier.fruits);
    }
}


