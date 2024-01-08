/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.view;

import fr.ufrsciencestech.projet.controler.Controleur;
import fr.ufrsciencestech.projet.model.Modele;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

public class VueConsole implements PropertyChangeListener, Observer{
    private String trace;

    private Modele model = new Modele();
    
    /**
     * @return the trace
     */
    public String getTrace() {
        return trace;
    }

    /**
     * @param trace the trace to set
     */
    public void setTrace(String trace) {
        this.trace = trace;
    }
    
    public VueConsole(){
        trace = "Valeur initiale : " + 0;
        System.out.println(trace);
    }
    
    public void update(Observable m, Object compteur){   //This method is called whenever the observed object is changed
      //  ((Modele) m).setCompteur((Integer)compteur);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        Modele m = (Modele) evt.getSource();
        
        trace = "Nouvelle valeur : " + m.getCompteur();
        System.out.println(trace);
        
        System.out.println("Variation of " + evt.getPropertyName());
	    System.out.println("\t(" + evt.getOldValue() +
							" -> " + evt.getNewValue() + ")");
	    System.out.println("Property in object " + evt.getSource());
    }

}
