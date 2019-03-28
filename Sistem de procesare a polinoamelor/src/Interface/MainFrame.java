package Interface;

import Model.*;
import Controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame{

    public MainFrame(String titlu){
        super(titlu);

        JPanel panelU1;
        JPanel panelD1;
        JPanel panelU2;
        JPanel panelD2;
        JSplitPane panel1;
        JSplitPane panel2;
        JSplitPane panel3;

        JLabel labelPolinom1;
        JLabel labelPolinom2;
        JLabel labelVerPol1;
        JLabel labelVerPol2;
        JLabel labelRezultat;
        JLabel labelRezultat1;
        JLabel labelRezultat2;

        JTextField polinom1;
        JTextField polinom2;

        JButton butonVerPol1;
        JButton butonVerPol2;
        JButton butonAdunare;
        JButton butonScadere;
        JButton butonInmultire;
        JButton butonImpartire;
        JButton butonDerivarePol;
        JButton butonIntegrarePol;

        labelPolinom1 = new JLabel("Polinom1: ");
        labelPolinom2 = new JLabel("Polinom2: ");
        polinom1 = new JTextField(60);
        polinom2 = new JTextField(60);

        butonVerPol1 = new JButton("Afisare Polinom 1");
        labelVerPol1 = new JLabel("Afisare Polinom 1");

        butonVerPol2 = new JButton("Afisare Polinom 2");
        labelVerPol2 = new JLabel("Afisare Polinom 2");

        butonAdunare = new JButton("Adunare");
        butonScadere = new JButton("Scadere");
        butonInmultire = new JButton("Inmultire");
        butonImpartire = new JButton("Impartire");
        butonDerivarePol = new JButton("Derivare");
        butonIntegrarePol = new JButton("Integrare");

        labelRezultat = new JLabel("Operatia:");
        labelRezultat1 = new JLabel("Rezultat 1");
        labelRezultat2 = new JLabel("Rezultat 2");

        panelU1 = new JPanel();
        panelD1 = new JPanel();
        panelU2 = new JPanel();
        panelD2 = new JPanel();
        panel1 = new JSplitPane();
        panel2 = new JSplitPane();
        panel3 = new JSplitPane();

        panel1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panel2.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panel3.setOrientation(JSplitPane.VERTICAL_SPLIT);

        panel1.setDividerLocation(70);
        panel2.setDividerLocation(130);
        panel3.setDividerLocation(30);

        panelU1.setLayout(new FlowLayout());
        panelD1.setLayout(new BoxLayout(panelD1, BoxLayout.Y_AXIS));
        panelU2.setLayout(new GridLayout(1, 6));
        panelD2.setLayout(new FlowLayout());

        labelPolinom1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelU1.add(labelPolinom1);
        polinom1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelU1.add(polinom1);
        labelPolinom2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelU1.add(labelPolinom2);
        polinom2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelU1.add(polinom2);

        panelD1.add(Box.createRigidArea(new Dimension(0, 7)));
        butonVerPol1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelD1.add(butonVerPol1);
        panelD1.add(Box.createRigidArea(new Dimension(0, 7)));
        labelVerPol1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelD1.add(labelVerPol1);
        panelD1.add(Box.createRigidArea(new Dimension(0, 7)));
        butonVerPol2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelD1.add(butonVerPol2);
        panelD1.add(Box.createRigidArea(new Dimension(0, 7)));
        labelVerPol2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelD1.add(labelVerPol2);
        panelD1.add(Box.createRigidArea(new Dimension(0, 7)));

        panelU2.add(butonAdunare);
        panelU2.add(butonScadere);
        panelU2.add(butonInmultire);
        panelU2.add(butonImpartire);
        panelU2.add(butonDerivarePol);
        panelU2.add(butonIntegrarePol);

        panelD2.add(labelRezultat);
        panelD2.add(labelRezultat1);
        panelD2.add(labelRezultat2);

        panel1.setTopComponent(panelU1);
        panel2.setTopComponent(panelD1);
        panel3.setTopComponent(panelU2);
        panel3.setBottomComponent(panelD2);
        panel2.setBottomComponent(panel3);
        panel1.setBottomComponent(panel2);

        butonVerPol1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Monom> monoame;
                String string = polinom1.getText();
                monoame = OperatiiPolinoame.formarePolinom(string);
                Polinom P = new Polinom(monoame);
                labelVerPol1.setText(OperatiiPolinoame.parsarePolinom(P));
            }
        });

        butonVerPol2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Monom> monoame;
                String string = polinom2.getText();
                monoame = OperatiiPolinoame.formarePolinom(string);
                Polinom P = new Polinom(monoame);
                labelVerPol2.setText(OperatiiPolinoame.parsarePolinom(P));
            }
        });

        butonAdunare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Monom> monoame1 = new ArrayList<Monom>();
                List<Monom> monoame2 = new ArrayList<Monom>();
                String string1 = polinom1.getText();
                String string2 = polinom2.getText();
                monoame1 = OperatiiPolinoame.formarePolinom(string1);
                monoame2 = OperatiiPolinoame.formarePolinom(string2);
                Polinom P1 = new Polinom(monoame1);
                Polinom P2 = new Polinom(monoame2);
                Polinom rezultat = OperatiiPolinoame.adunarePolinoame(P1, P2);
                labelRezultat.setText("Adunare: ");
                labelRezultat1.setText(OperatiiPolinoame.parsarePolinom(rezultat));
                labelRezultat2.setText("");
            }
        });

        butonScadere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Monom> monoame1 = new ArrayList<Monom>();
                List<Monom> monoame2 = new ArrayList<Monom>();
                String string1 = polinom1.getText();
                String string2 = polinom2.getText();
                monoame1 = OperatiiPolinoame.formarePolinom(string1);
                monoame2 = OperatiiPolinoame.formarePolinom(string2);
                Polinom P1 = new Polinom(monoame1);
                Polinom P2 = new Polinom(monoame2);
                Polinom rezultat = OperatiiPolinoame.scaderePolinoame(P1, P2);
                labelRezultat.setText("Scadere: ");
                labelRezultat1.setText(OperatiiPolinoame.parsarePolinom(rezultat));
                labelRezultat2.setText("");
            }
        });

        butonInmultire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Monom> monoame1 = new ArrayList<Monom>();
                List<Monom> monoame2 = new ArrayList<Monom>();
                String string1 = polinom1.getText();
                String string2 = polinom2.getText();
                monoame1 = OperatiiPolinoame.formarePolinom(string1);
                monoame2 = OperatiiPolinoame.formarePolinom(string2);
                Polinom P1 = new Polinom(monoame1);
                Polinom P2 = new Polinom(monoame2);
                Polinom rezultat = OperatiiPolinoame.inmultirePolinoame(P1, P2);
                labelRezultat.setText("Inmultire: ");
                labelRezultat1.setText(OperatiiPolinoame.parsarePolinom(rezultat));
                labelRezultat2.setText("");
            }
        });

        butonImpartire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Monom> monoame1 = new ArrayList<Monom>();
                List<Monom> monoame2 = new ArrayList<Monom>();
                String string1 = polinom1.getText();
                String string2 = polinom2.getText();
                monoame1 = OperatiiPolinoame.formarePolinom(string1);
                monoame2 = OperatiiPolinoame.formarePolinom(string2);
                Polinom P1 = new Polinom(monoame1);
                Polinom P2 = new Polinom(monoame2);
                if(P2.monoame.size() == 0) {
                    labelRezultat.setText("Impartire: ");
                    labelRezultat1.setText("Imposibil");
                    labelRezultat2.setText("");
                }
                else
                {
                    Polinom cat = new Polinom();
                    Polinom rest = OperatiiPolinoame.impartirePolinoame(P1, P2, cat);
                    labelRezultat.setText("Impartire: ");
                    labelRezultat1.setText("Catul: " + OperatiiPolinoame.parsarePolinomCuCoefD(cat));
                    labelRezultat2.setText("Restul: " + OperatiiPolinoame.parsarePolinom(rest));
                }
            }
        });

        butonDerivarePol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Monom> monoame1 = new ArrayList<Monom>();
                List<Monom> monoame2 = new ArrayList<Monom>();
                String string1 = polinom1.getText();
                String string2 = polinom2.getText();
                monoame1 = OperatiiPolinoame.formarePolinom(string1);
                monoame2 = OperatiiPolinoame.formarePolinom(string2);
                Polinom P1 = new Polinom(monoame1);
                Polinom P2 = new Polinom(monoame2);
                OperatiiPolinoame.derivarePolinom(P1);
                OperatiiPolinoame.derivarePolinom(P2);
                labelRezultat.setText("Derivare: ");
                labelRezultat1.setText("Polinom 1: " + OperatiiPolinoame.parsarePolinom(P1));
                labelRezultat2.setText("    Polinom 2: " + OperatiiPolinoame.parsarePolinom(P2));
            }
        });

        butonIntegrarePol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Monom> monoame1 = new ArrayList<Monom>();
                List<Monom> monoame2 = new ArrayList<Monom>();
                String string1 = polinom1.getText();
                String string2 = polinom2.getText();
                monoame1 = OperatiiPolinoame.formarePolinom(string1);
                monoame2 = OperatiiPolinoame.formarePolinom(string2);
                Polinom P1 = new Polinom(monoame1);
                Polinom P2 = new Polinom(monoame2);
                OperatiiPolinoame.integrarePolinom(P1);
                OperatiiPolinoame.integrarePolinom(P2);
                labelRezultat.setText("Integrare: ");
                labelRezultat1.setText("Polinom 1: " + OperatiiPolinoame.parsarePolinomCuCoefD(P1));
                labelRezultat2.setText("    Polinom 2: " + OperatiiPolinoame.parsarePolinomCuCoefD(P2));
            }
        });

        this.add(panel1);
        setVisible(true);
    }
}
