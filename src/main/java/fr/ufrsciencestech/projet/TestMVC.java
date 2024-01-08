/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet;

import fr.ufrsciencestech.projet.controler.Controleur;
import fr.ufrsciencestech.projet.view.*;
import fr.ufrsciencestech.projet.model.*;

//utilise pour springIoC :
import javax.swing.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Observable;


public class TestMVC {
    private VueG vueg;      //pour pouvoir changer de vue si on le souhaite
    private Controleur controleur;  //pour pouvoir changer de controleur si on le souhaite

    /**
     * @return the vueg
     */
    public VueG getVueg() {
        return vueg;
    }
    /**
     * @param vueg the vueg to set
     */
    public void setVueg(VueG vueg) {
        this.vueg = vueg;
    }

    /**
     * @return the controleur
     */
    public Controleur getControleur() {
        return controleur;
    }

    /**
     * @param controleur the controleur to set
     */
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public static void main(String[] args) throws PanierPleinException {
        //TestMVC test = new TestMVC();    //sans utiliser SpringIoC

        //La meme chose mais avec SpringIoC :
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        TestMVC test = (TestMVC)context.getBean("MVC");  //SpringIoC
        test.setControleur( (Controleur)context.getBean("Controleur") );  //SpringIoC


        test.setVueg( (VueG)context.getBean("Vue") );   //SpringIoC

        test.getControleur().setModele((Modele)context.getBean("Modele"));

        test.getVueg().addControleur(test.getControleur());
    }
}
