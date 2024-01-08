package fr.ufrsciencestech.projet.view;
import fr.ufrsciencestech.projet.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AjoutJusMacedoine extends JDialog {
    private VueGraphiqueListe parent;
    private JList<Fruit> fruitList;
    private JRadioButton macedoineRadioButton;
    private JRadioButton jusRadioButton;

    public AjoutJusMacedoine(final VueGraphiqueListe p) {
        super(p, "Ajouter Jus/Macedoine", true);
        this.parent = p;

        initUI();
        initLayout();

        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initUI() {
        macedoineRadioButton = new JRadioButton("Macédoine");
        jusRadioButton = new JRadioButton("Jus");

        ButtonGroup group = new ButtonGroup();
        macedoineRadioButton.setSelected(true);
        group.add(macedoineRadioButton);
        group.add(jusRadioButton);

        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) parent.getjComboBox().getModel();
        DefaultListModel<Fruit> listModel = new DefaultListModel<>();

        for (int i = 0; i < model.getSize(); i++) {
            if (!(model.getElementAt(i) instanceof Macedoine) && !(model.getElementAt(i) instanceof Jus)) {
                listModel.addElement(model.getElementAt(i));
            }
        }
        fruitList = new JList<>(listModel);
        fruitList.setSelectedIndex(0);
        fruitList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    private void initLayout() {
        setLayout(new BorderLayout());

        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        radioButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        radioButtonPanel.add(macedoineRadioButton);
        radioButtonPanel.add(jusRadioButton);

        JScrollPane scrollPane = new JScrollPane(fruitList);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton creerButton = new JButton("Créer");
        JButton annulerButton = new JButton("Annuler");

        creerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creerJusMacedoine();
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annulerAjout();
            }
        });

        buttonPanel.add(creerButton);
        buttonPanel.add(annulerButton);

        add(radioButtonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void creerJusMacedoine() {
        List<Fruit> selectedFruits = fruitList.getSelectedValuesList();
        if (macedoineRadioButton.isSelected()) {
            Macedoine macedoine = new Macedoine();
            for (Fruit fruit : selectedFruits) {
                macedoine.ajoute(fruit);
            }
            parent.ajouterJusMacedoine(macedoine);
        } else if (jusRadioButton.isSelected()) {
            Jus jus = new Jus();
            for (Fruit fruit : selectedFruits) {
                jus.ajoute(fruit);
            }
            parent.ajouterJusMacedoine(jus);
        }
        dispose();
    }

    private void annulerAjout() {
        dispose();
    }
}

/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AjoutJusMacedoine extends JDialog{
    private VueGraphiqueListe parent;
    private JList<Fruit> jcb;
    private JRadioButton macedoineRadioButton;
    private JRadioButton jusRadioButton;
    private JPanel jp; 
    
    
    public AjoutJusMacedoine(final VueGraphiqueListe p) {
        super(p, "Ajouter Jus/Macedoine", true);
        this.parent = p;
        
        setLayout(new GridLayout(2, 2));
        
        macedoineRadioButton = new JRadioButton("Macédoine");
        jusRadioButton = new JRadioButton("Jus");
        JPanel jp= new JPanel();
        ButtonGroup group = new ButtonGroup();
        macedoineRadioButton.setSelected(true);
        group.add(macedoineRadioButton);
        group.add(jusRadioButton);
        jp.add(macedoineRadioButton);
        jp.add(jusRadioButton);
        this.add(jp);
        
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) this.parent.getjComboBox().getModel();
        DefaultListModel<Fruit> listModel = new DefaultListModel<>();
        
        for (int i=0; i<model.getSize(); i++){
            if(!(model.getElementAt(i).getClass().getSimpleName().equals("Macedoine") || model.getElementAt(i).getClass().getSimpleName().equals("Jus"))){
                listModel.addElement(model.getElementAt(i));
            }
        }
        this.jcb = new JList<>(listModel);
        jcb.setSelectedIndex(0);
        jcb.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.add(jcb);
        
        JButton CreerButton = new JButton("Créer");
        CreerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                List<Fruit> selectedFruits = jcb.getSelectedValuesList();
                //System.out.println("Fruit a supprimeé:"+elementSelectionne.toString());
                if(macedoineRadioButton.isSelected()) {
                    Macedoine ma=new Macedoine();
                    for (Fruit fruit : selectedFruits) {
                       ma.ajoute(fruit);
                    }
                    parent.ajouterJusMacedoine((Fruit)ma);
                }else if (jusRadioButton.isSelected()){
                    Jus jus=new Jus();
                    for (Fruit fruit : selectedFruits) {
                       jus.ajoute(fruit);
                    }
                    parent.ajouterJusMacedoine((Fruit)jus);
                }
                dispose(); // Ferme la fenêtre de dialogue
            }
        });

        JButton annulerButton = new JButton("Annuler");
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                System.out.println("Annulé");
                dispose(); // Ferme la fenêtre de dialogue sans créer de fruit
            }
        });

        add(CreerButton);
        add(annulerButton);
        pack();                // Redimmentionnage automatique
        setSize(400, 400);
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getWidth()/2, dim.height/2 - this.getWidth()/2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
*/