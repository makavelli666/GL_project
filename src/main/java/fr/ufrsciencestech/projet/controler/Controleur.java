/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.controler;

import fr.ufrsciencestech.projet.view.*;
import fr.ufrsciencestech.projet.model.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controleur implements ActionListener {
    private Modele model;
    private VueGraphiqueListe vue;
    private Fruit currentFruit;
    private Panier p = new Panier(10);

    public Controleur(Modele model, VueGraphiqueListe vue){
        this.model = model;
        this.vue = vue;
        model.addPropertyChangeListener(vue);
        currentFruit = (Fruit) vue.getjComboBox().getSelectedItem();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){   //Invoked when an action occurs
        if (e.getActionCommand().equals("RAZP")){
            this.p=new Panier(getPanier().getContenanceMax());
            model.update(2);
            remplirCylindre(vue);
            vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
            vue.getAffiche().setText("0");
            vue.getAffichePrix().setText("Prix Total  :  0 €");
        }else if (e.getActionCommand().equals("AugmenterContenance")){
            this.p.setContenanceMax(this.p.getContenanceMax()+10);
            vue.getPanierProgressBar().setMaximum(this.p.getContenanceMax());
            remplirCylindre(vue);
            setPrixTotal(vue);
            vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
        }else if (((Component) e.getSource()).getName().equals("ComboBox")){
            currentFruit = (Fruit) vue.getjComboBox().getSelectedItem();
            System.out.println("**- CHANGEMENT DANS LA COMBOBOX Fruit Courant : " + currentFruit + " -**");
        }
        else if(((Component)e.getSource()).getName().equals("Plus")) {
            try {
                p.ajout(currentFruit);
                model.update(1);
                remplirCylindre(vue);
                setPrixTotal(vue);
                vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
            } catch (PanierPleinException ex) {
                afficherErreurPanierPlein(vue);
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(((Component)e.getSource()).getName().equals("Moins"))
            try {
                p.retrait();
                model.update(-1);
                remplirCylindre(vue);
                setPrixTotal(vue);
                vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
        } catch (PanierVideException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPanier(Panier pa){
        this.p = pa;
    }

    public Panier getPanier(){
        return this.p;
    }
    
    public void setcurrentFruit(Fruit c){
        this.currentFruit = c;
    }
    public Fruit getcurrentFruit(){
        return currentFruit;
    }
    public void setModele(Modele m)
    {
        this.model = m;
    }
    public void setVue(VueGraphiqueListe vg)
    {
        this.vue = vg;
    }
    public static JTextArea findJTextAreaByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (component instanceof JTextArea && component.getName() != null &&
                    component.getName().equals(name)) {
                return (JTextArea) component;
            } else if (component instanceof Container) {
                JTextArea textArea = findJTextAreaByName((Container) component, name);
                if (textArea != null) {
                    return textArea;
                }
            }
        }
        return null;
    }

    public void remplirCylindre(VueGraphiqueListe v) {
        // Définition des valeurs minimale et maximale
        v.getPanierProgressBar().setMinimum(0);
        v.getPanierProgressBar().setMaximum(getPanier().getContenanceMax()); // Utilisation de la capacité maximale du panier

        if (getPanier() != null) {
            int pourcentageRemplissage = (int) ((double) getPanier().getTaillePanier() / vue.getPanierProgressBar().getMaximum() * 100);

            vue.getPanierProgressBar().setValue(getPanier().getTaillePanier());
            vue.getPanierProgressBar().setString(pourcentageRemplissage + "%");
        } else {
            vue.getPanierProgressBar().setValue(0);
            vue.getPanierProgressBar().setString("0%");
        }
    }

    public void setPrixTotal(VueGraphiqueListe vue){
        double prixTotal = this.getPanier().getPrix();
        vue.getAffichePrix().setText("Prix Total : " + prixTotal + " €");
    }

    public void afficherErreurPanierPlein(VueGraphiqueListe vue) {
        PanierPlein panierPleinFrame = new PanierPlein("Le panier est plein !");
        panierPleinFrame.setVisible(true);
    }
}


