/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.view;
import fr.ufrsciencestech.projet.view.VueGraphiqueListe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.ufrsciencestech.projet.model.Fruit;


public class BoycotterPays extends JDialog {
    private VueGraphiqueListe parent;
    private JList<String> jcb;
    private JList<Fruit> fruitList;

    public BoycotterPays(final VueGraphiqueListe p) {
        super(p, "Boycotter un pays", true);
        this.parent = p;

        setLayout(new BorderLayout());

        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) this.parent.getjComboBox().getModel();
        DefaultComboBoxModel<String> modeleFruitsUniques = new DefaultComboBoxModel<>();
        DefaultListModel<Fruit> modeleListeFruit = new DefaultListModel<>();
        List<String> originesDejaAjoutees = new ArrayList<>();

        for (int i = 0; i < model.getSize(); i++) {
            Fruit fruit = model.getElementAt(i);
            String origine = fruit.getOrigine();
            if (!originesDejaAjoutees.contains(origine)) {
                modeleFruitsUniques.addElement(fruit.getOrigine());
                originesDejaAjoutees.add(origine);
            }
        }

        this.jcb = new JList<>(modeleFruitsUniques);
        jcb.setSelectedIndex(0);

        String selectedOrigine = jcb.getSelectedValue();
        for (int i = 0; i < model.getSize(); i++) {
            Fruit fruit = model.getElementAt(i);
            if (selectedOrigine.equals(fruit.getOrigine())) {
                modeleListeFruit.addElement(fruit);
            }
        }

        this.fruitList = new JList<>(modeleListeFruit);
        JScrollPane scrollPane = new JScrollPane(fruitList);
        fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(jcb, BorderLayout.WEST);
        this.add(scrollPane, BorderLayout.CENTER);

        JButton boycotterButton = new JButton("Boycotter");
        boycotterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                String selectedOrigine = jcb.getSelectedValue();
                parent.boycotterPays(selectedOrigine);
                dispose(); // Ferme la fenêtre de dialogue
            }
        });

        jcb.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedOrigine = jcb.getSelectedValue();

                    modeleListeFruit.removeAllElements();

                    for (int i = 0; i < model.getSize(); i++) {
                        Fruit fruit = model.getElementAt(i);
                        if (selectedOrigine.equals(fruit.getOrigine())) {
                            modeleListeFruit.addElement(fruit);
                        }
                    }
                }
            }
        });

        JButton annulerButton = new JButton("Annuler");
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                System.out.println("Annulé:");
                dispose(); // Ferme la fenêtre de dialogue sans créer de fruit
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(boycotterButton);
        buttonPanel.add(annulerButton);

        add(buttonPanel, BorderLayout.SOUTH);
        setSize(450, 400);
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
