/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.view;
import fr.ufrsciencestech.projet.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjoutFruit extends JDialog {
    private JTextField nomField, prixField, paysField;
    private JButton confirmerButton, annulerButton;

    private VueGraphiqueListe parent;

    public AjoutFruit(final VueGraphiqueListe parent) {
        super(parent, "Ajouter un fruit", true);
        this.parent = parent;

        initUI();
        initLayout();

        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initUI() {
        nomField = new JTextField(10);
        prixField = new JTextField(10);
        paysField = new JTextField(10);

        confirmerButton = new JButton("Confirmer");
        annulerButton = new JButton("Annuler");

        confirmerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterFruit();
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annulerAjout();
            }
        });
    }

    private void initLayout() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Nom du fruit:"));
        inputPanel.add(nomField);

        inputPanel.add(new JLabel("Prix du fruit:"));
        inputPanel.add(prixField);

        inputPanel.add(new JLabel("Pays de provenance:"));
        inputPanel.add(paysField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(confirmerButton);
        buttonPanel.add(annulerButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void ajouterFruit() {
        try {
            String nom = nomField.getText();
            double prix = Double.parseDouble(prixField.getText());
            String pays = paysField.getText();

            Fruit fruit = createFruit(nom, prix, pays);
            parent.ajouterFruit(fruit);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Le prix doit être un nombre valide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Fruit createFruit(String nom, double prix, String pays) {
        switch (nom.toLowerCase()) {
            case "orange":
                return new Orange(prix, pays);
            case "ananas":
                return new Ananas(prix, pays);
            case "banane":
                return new Banane(prix, pays);
            case "cerise":
                return new Cerise(prix, pays);
            case "kiwi":
                return new Kiwi(prix, pays);
            case "fraise":
                return new Fraise(prix, pays);
            default:
                throw new IllegalArgumentException("Fruit non disponible");
        }
    }

    private void annulerAjout() {
        dispose();
    }
}
