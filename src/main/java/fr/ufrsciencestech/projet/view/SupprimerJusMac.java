package fr.ufrsciencestech.projet.view;
import fr.ufrsciencestech.projet.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupprimerJusMac extends JDialog {
    private VueGraphiqueListe parent;
    private JComboBox<Fruit> jcb;

    public SupprimerJusMac(final VueGraphiqueListe p) {
        super(p, "Supprimer Jus/Macedoine", true);
        this.parent = p;

        setLayout(new BorderLayout());

        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) this.parent.getjComboBox().getModel();
        
        DefaultComboBoxModel<Fruit> modeleListeJusMacedoine = new DefaultComboBoxModel<>();
        for (int i=0; i<model.getSize(); i++){
            if(model.getElementAt(i).getClass().getSimpleName().equals("Macedoine") || model.getElementAt(i).getClass().getSimpleName().equals("Jus")){
                modeleListeJusMacedoine .addElement(model.getElementAt(i));
            }
        }
        
        this.jcb = new JComboBox<>(modeleListeJusMacedoine);
        add(jcb, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton supprimerButton = new JButton("Supprimer");
        JButton annulerButton = new JButton("Annuler");

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                Fruit elementSelectionne = (Fruit) jcb.getSelectedItem();
                if (elementSelectionne instanceof Macedoine || elementSelectionne instanceof Jus) {
                    parent.supprimerFruit(elementSelectionne);
                    dispose(); // Ferme la fenêtre de dialogue
                } else {
                    // Afficher un message d'erreur ou prendre une autre action si nécessaire
                    System.out.println("L'élément sélectionné n'est ni une Macedoine ni un Jus.");
                }
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                System.out.println("Annulé:");
                dispose(); // Ferme la fenêtre de dialogue sans créer de fruit
            }
        });

        buttonPanel.add(supprimerButton);
        buttonPanel.add(annulerButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();                // Redimmentionnage automatique
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
