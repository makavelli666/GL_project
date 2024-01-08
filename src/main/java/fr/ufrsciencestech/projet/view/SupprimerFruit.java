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

public class SupprimerFruit extends JDialog {
    private Fruit fruit;
    private VueGraphiqueListe parent;
    private JComboBox<Fruit> jcb;

    public SupprimerFruit(final VueGraphiqueListe p) {
        super(p, "Supprimer un fruit", true);
        this.parent = p;

        initUI();
        initLayout();

        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initUI() {
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) parent.getjComboBox().getModel();
        
        DefaultComboBoxModel<Fruit> modeleListeFruit = new DefaultComboBoxModel<>();
        for (int i=0; i<model.getSize(); i++){
            if(!(model.getElementAt(i).getClass().getSimpleName().equals("Macedoine") || model.getElementAt(i).getClass().getSimpleName().equals("Jus"))){
                modeleListeFruit .addElement(model.getElementAt(i));
            }
        }
        
        this.jcb = new JComboBox<>(modeleListeFruit);
    }

    private void initLayout() {
        setLayout(new BorderLayout());

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        comboBoxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        comboBoxPanel.add(jcb);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton supprimerButton = new JButton("Supprimer");
        JButton annulerButton = new JButton("Annuler");

        supprimerButton.setToolTipText("");
        annulerButton.setToolTipText("");

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerFruit();
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annulerSuppression();
            }
        });

        buttonPanel.add(supprimerButton);
        buttonPanel.add(annulerButton);

        add(comboBoxPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void supprimerFruit() {
        Fruit elementSelectionne = (Fruit) jcb.getSelectedItem();
        if (!(elementSelectionne instanceof Macedoine || elementSelectionne instanceof Jus)) {
            parent.supprimerFruit(elementSelectionne);
            dispose();
        } else {
            // Afficher un message d'erreur ou prendre une autre action si nécessaire
            System.out.println("L'élément sélectionné n'est pas un Fruit.");
        }
    }

    private void annulerSuppression() {
        System.out.println("Annulé:");
        dispose();
    }
/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new SupprimerFruit(null);
        });
    }

 */
}