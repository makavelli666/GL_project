/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.view;

import fr.ufrsciencestech.projet.controler.Controleur;
import fr.ufrsciencestech.projet.model.Modele;
import fr.ufrsciencestech.projet.model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;


public class VueGraphiqueListe extends JFrame implements VueG{
    private JButton inc,dec;
    private JLabel affiche,affichePrix;
    private static JPanel jPanel1,jPanel2;
    private JComboBox jComboBox;
    private static JTextArea jTextArea;
    private static JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JMenu menuFruit,menuPanier,menuItemType,menuJusMac,menuOrigine;
    private JMenuItem menuItem,menuItem2,menuItem3,menuItem4,menuItem5;
    private JMenuItem menuItemRAZp,menuItemRAZc,menuItemtype1,menuItemtype2,menuItemtype3,menuItemtype4;
    private int option=0;
    private double prixTotal = 0.0;
    private JProgressBar panierProgressBar;
    public VueGraphiqueListe() throws PanierPleinException {
        super("Panier Swing");

        FabriqueFruit fruit = new FabriqueFruit();
        Fruit orange = fruit.creerFruit("orange",0.99,"Espagne");
        Fruit ananas = fruit.creerFruit("ananas",2.0,"USA");
        Fruit banane = fruit.creerFruit("banane",1.0,"Inde");
        Fruit cerise = fruit.creerFruit("cerise",2.2,"France");
        Fruit fraise = fruit.creerFruit("fraise",1.99,"France");
        Fruit kiwi = fruit.creerFruit("kiwi",1.5,"Chine");

        Fruit ma = new Jus(orange,banane);
        Fruit[] fruits={orange,ananas,banane,cerise,fraise,kiwi,ma};
        DefaultComboBoxModel<Fruit> modelCombo = new DefaultComboBoxModel<>(fruits);

        this.jComboBox = new JComboBox<>(modelCombo);


        // Création du menu bar
        menuBar = new JMenuBar();
        menuPanier = new JMenu("Panier");
        menuFruit = new JMenu("Fruit");
        menuJusMac = new JMenu("Jus/Macedoinne");
        menuOrigine = new JMenu("Boycotte");


        menuItem = new JMenuItem("Ajouter Fruit");
        menuItem2 = new JMenuItem("Supprimer Fruit");

        menuItem3 = new JMenuItem("Ajouter Jus/Macedoine");
        menuItem4 = new JMenuItem("Supprimer Jus/Macedoine");

        menuItem5 = new JMenuItem("Boycotter un Pays");

        menuItemType = new JMenu("Modifier le type du Panier");

        menuItemRAZp = new JMenuItem("Reinitialiser le Panier");
        menuItemRAZc = new JMenuItem("Reinitialiser le Catalogue");

        menuItemtype1 = new JMenuItem("Panier à moins de 2€/fruit");
        menuItemtype2 = new JMenuItem("Fruits locaux (France)");
        menuItemtype3 = new JMenuItem("Fruit seulement");
        menuItemtype4 = new JMenuItem("Augmenter la contenance de +10");

        menuPanier.add(menuItemType);

        menuItemType.add(menuItemtype1);
        menuItemType.add(menuItemtype2);
        menuItemType.add(menuItemtype3);
        menuItemType.add(menuItemtype4);
        
        menuPanier.add(menuItemRAZp);
        menuPanier.add(menuItemRAZc);


        menuFruit.add(menuItem);
        menuFruit.add(menuItem2);

        menuJusMac.add(menuItem3);
        menuJusMac.add(menuItem4);

        menuOrigine.add(menuItem5);

        menuBar.add(menuPanier);
        menuBar.add(menuFruit);
        menuBar.add(menuJusMac);
        menuBar.add(menuOrigine);

        styliserMenuBar();

        setJMenuBar(menuBar);

        inc = new JButton("+");
        dec = new JButton("-");

        jPanel1 = new JPanel();
        jPanel2 = new JPanel();

        createAndShowGUI();

        // Ajoutez des marges autour des panneaux
        jPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        jTextArea = new JTextArea(18,25);
        scrollPane= new JScrollPane(jTextArea);


        affiche = new JLabel("Fruit(s) total dans le panier  :  0", JLabel.CENTER);
        affichePrix = new JLabel("Prix Total : 0 €", JLabel.CENTER);


        // Utilisez un layout plus flexible pour jPanel2
        jPanel2.setLayout(new BorderLayout());
        JPanel labelsPanel = new JPanel(new GridLayout(1, 2, 0, 5));

        labelsPanel.add(affiche);
        labelsPanel.add(affichePrix);


        jPanel2.add(labelsPanel, BorderLayout.SOUTH);
        jPanel2.add(scrollPane);



        // Utilisez un layout pour jPanel1 avec un espacement entre les composants
        jPanel1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));

        // Personnalisez les couleurs si nécessaire
        jPanel1.setBackground(Color.LIGHT_GRAY);
        jPanel2.setBackground(Color.white);


        this.jComboBox.setSelectedIndex(0);
        jPanel1.add(jComboBox);
        jPanel1.add(inc);
        jPanel1.add(dec);

        add(jPanel1, BorderLayout.NORTH);
        add(jPanel2, BorderLayout.CENTER);

        inc.setName("Plus");
        dec.setName("Moins");
        this.jComboBox.setName("ComboBox");
        affiche.setName("Affichage");
        jTextArea.setText("Liste des fruit(s) dans mon Panier :\n");
        menuItem.setName("menuItemAjoutFruit");


        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjoutFruit ajoutFruitDialog = new AjoutFruit(VueGraphiqueListe.this);
                if(option==1){Option1();}
                if(option==2){Option2();}
                if(option==3){Option3();}
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e0) {
                SupprimerFruit supprFruitDialog = new SupprimerFruit(VueGraphiqueListe.this);
            }
        });


        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjoutJusMacedoine AjoutJusMacedoineDialog = new AjoutJusMacedoine(VueGraphiqueListe.this);
                if(option==1){Option1();}
                if(option==2){Option2();}
                if(option==3){Option3();}
            }
        });

        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e0) {
                SupprimerJusMac supprJusMac = new SupprimerJusMac(VueGraphiqueListe.this);
            }
        });

        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e5) {
                BoycotterPays BoycottePaysDialog = new BoycotterPays(VueGraphiqueListe.this);
            }
        });


        menuItemRAZc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultComboBoxModel<Fruit> modelCombo = new DefaultComboBoxModel<>(fruits);
                getjComboBox().setModel(modelCombo);
            }
        });

        menuItemtype1.addActionListener(new ActionListener() {                  //Panier à moins de 2€/fruit
            @Override
            public void actionPerformed(ActionEvent e) {
                option=1;
                Option1();
            }
        });

        menuItemtype2.addActionListener(new ActionListener() {                   //Fruits locaux (France)
            @Override
            public void actionPerformed(ActionEvent e) {
                option=2;
                Option2();
            }
        });

        menuItemtype3.addActionListener(new ActionListener() {                      //Fruit seulement
            @Override
            public void actionPerformed(ActionEvent e) {
                option=3;
                Option3();
            }
        });

        this.setExtendedState(JFrame.NORMAL);  // Définit l'état initial
        this.pack();

        // Récupère les dimensions de l'écran
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();

        // Définit la taille de la fenêtre en fonction des dimensions de l'écran
        int largeur = (int) (dim.width * 0.5);
        int hauteur = (int) (dim.height * 0.6);
        this.setSize(largeur, hauteur);

        // Centre la fenêtre sur l'écran
        this.setLocation(dim.width/2-this.getWidth()/2, dim.height/2 - this.getWidth()/3);

        panierProgressBar = new JProgressBar(0, 20); // Changez 20 selon votre max
        panierProgressBar.setStringPainted(true);
        panierProgressBar.setValue(0);


        // Ajoutez la barre de progression à droite de la zone de texte
        jPanel2.add(panierProgressBar, BorderLayout.EAST);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

    public void addControleur(Controleur c){
        getInc().addActionListener(c);
        getDec().addActionListener(c);
        getjComboBox().addActionListener(c);
        getmenuItemRAZPanier().setActionCommand("RAZP");
        getmenuItemRAZPanier().addActionListener(c);
        getmenuItemtype4().setActionCommand("AugmenterContenance");
        getmenuItemtype4().addActionListener(c);
    }

    @Override
    public void update(Observable m, Object compteur) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("propertyChange method called!");
        if("value".equals(evt.getPropertyName())) {
            if (evt.getSource() instanceof Modele) {
                Modele m = (Modele) evt.getSource();
                m.setCompteur((Integer) evt.getNewValue());
                getAffiche().setText("Fruit(s) total dans le panier  :  " + m.getCompteur());
                getAffichePrix().setText("Prix Total  :  " + prixTotal + " €");
            }
        }
    }

    public JProgressBar getPanierProgressBar(){
        return this.panierProgressBar;
    }


    public void ajouterFruit(Fruit f) {
            DefaultComboBoxModel<Fruit> models = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
            models.addElement(f);
            repaint();
            revalidate();
    }

    public void supprimerFruit(Fruit elementSelectionne) {
        DefaultComboBoxModel<Fruit> models = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
        models.removeElement(elementSelectionne);
        repaint();
        revalidate();
    }


    public void boycotterPays(String elementSelectionne) {
        DefaultComboBoxModel<Fruit> models = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
        for (int i=0; i<models.getSize(); i++){
            if(models.getElementAt(i).getOrigine().toLowerCase().equals(elementSelectionne.toLowerCase())){
                models.removeElementAt(i);
                i--;
            }
        }
        if(models.getSize()>0)
            getjComboBox().setSelectedIndex(0);
        repaint();
        revalidate();
    }
    public void ajouterJusMacedoine(Fruit f) {
        DefaultComboBoxModel<Fruit> models = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
        models.addElement(f);
        repaint();
        revalidate();
    }

    public JMenuItem getjmenuItem() {
        return this.menuItem;
    }

    public JMenuItem getmenuItemRAZPanier() {
        return this.menuItemRAZp;
    }
    public JMenuItem getmenuItemtype4() {
        return this.menuItemtype4;
    }

    public JComboBox getjComboBox() {
        return this.jComboBox;
    }
    public JTextArea getjTextArea() {
        return this.jTextArea;
    }

    public void Option1() {
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
        DefaultComboBoxModel<Fruit> modeleListeFruit = new DefaultComboBoxModel<>();

        for (int i = 0; i < model.getSize(); i++) {
            if(model.getElementAt(i).getPrix()<2.0){
                modeleListeFruit.addElement(model.getElementAt(i));
            }
        }
        getjComboBox().setModel(modeleListeFruit);
        repaint();
        revalidate();
    }

    public void Option2() {
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
        DefaultComboBoxModel<Fruit> modeleListeFruit = new DefaultComboBoxModel<>();

        for (int i = 0; i < model.getSize(); i++) {
            if(model.getElementAt(i).getOrigine().toLowerCase().equals("france")){
                modeleListeFruit.addElement(model.getElementAt(i)); // Ajoutez le fruit au modèle de fruits uniques
            }
        }
        getjComboBox().setModel(modeleListeFruit);
        repaint();
        revalidate();
    }

    public void Option3() {
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) getjComboBox().getModel();
        DefaultComboBoxModel<Fruit> modeleListeFruit = new DefaultComboBoxModel<>();
        for (int i=0; i<model.getSize(); i++){
            if(!(model.getElementAt(i).getClass().getSimpleName().equals("Macedoine") || model.getElementAt(i).getClass().getSimpleName().equals("Jus"))){
                modeleListeFruit.addElement(model.getElementAt(i));
            }
        }
        getjComboBox().setModel(modeleListeFruit);
        repaint();
        revalidate();
    }

    /**
     * @return the inc
     */
    public JButton getInc() {
        return inc;
    }

    /**
     * @param inc the inc to set
     */
    public void setInc(JButton inc) {
        this.inc = inc;
    }

    /**
     * @return the dec
     */
    public JButton getDec() {
        return dec;
    }

    /**
     * @param dec the dec to set
     */
    public void setDec(JButton dec) {
        this.dec = dec;
    }

    /**
     * @return the affiche
     */
    public JLabel getAffiche() {
        return affiche;
    }

    public JLabel getAffichePrix() {
        return affichePrix;
    }


    /**
     * @param affiche the affiche to set
     */
    public void setAffiche(JLabel affiche) {
        this.affiche = affiche;
    }

    /********* menu bar *************/

    private void styliserMenuBar() {
        // Stylisation de la barre de menus
        menuBar.setBackground(new Color(0x462665)); // Couleur de fond
        menuBar.setForeground(Color.WHITE); // Couleur du texte

        // Stylisation des menus
        styliserMenu(menuPanier);
        styliserMenu(menuFruit);
        styliserMenu(menuJusMac);
        styliserMenu(menuOrigine);


        // Stylisation des items de menu
        styliserMenuItem(menuItem);
        styliserMenuItem(menuItem2);
        styliserMenuItem(menuItem3);
        styliserMenuItem(menuItem4);
        styliserMenuItem(menuItem5);



        // Stylisation des items de sous-menu
        styliserMenuItem(menuItemtype1);
        styliserMenuItem(menuItemtype2);
        styliserMenuItem(menuItemtype3);
        styliserMenuItem(menuItemtype4);
        styliserMenuItem(menuItemRAZp);
        styliserMenuItem(menuItemRAZc);
    }

    private void styliserMenu(JMenu menu) {
        // Ajoutez des styles au besoin
     //   menu.setForeground(Color.WHITE);
        menu.setBorderPainted(false);
    }

    private void styliserMenuItem(JMenuItem item) {
        // Ajoutez des styles au besoin
     //   item.setForeground(Color.WHITE);
        item.setBackground(new Color(0xFF9B9D9B, true));
        item.setBorderPainted(false);
    }

    private void createAndShowGUI() {
        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setOpaque(false);
        jTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
       // jTextArea.setForeground(Color.WHITE);

        // Set a more modern look for the JTextArea
        jTextArea.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        jTextArea.setBorder(BorderFactory.createEmptyBorder());

        // Create a JScrollPane and customize it
        scrollPane = new JScrollPane(jTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); // Hide vertical scrollbar

        // Add the JScrollPane to the existing JPanel
        jPanel2.add(scrollPane, BorderLayout.CENTER);

        // Set modern and dark look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        SwingUtilities.updateComponentTreeUI(this);

        this.add(jPanel2);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}

