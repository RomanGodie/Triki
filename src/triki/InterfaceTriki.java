/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triki;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Molina
 */
public class InterfaceTriki extends JFrame implements ActionListener {

    private Container contenedor;
    private JPanel JPTablero;
    private JButton[][] Botones = new JButton[3][3];
    private JButton JBNuevo, JBCancelar;
    private LogicaTriki nuevaLogica;

    public InterfaceTriki() {
        crearVentana();
        nuevaLogica = new LogicaTriki();
    }

    public void crearVentana() {

        setTitle("Triki Tres en Linea");
        setSize(300, 300);
        contenedor = getContentPane();
        contenedor.setLayout(null);

        JPTablero = new JPanel(new GridLayout(3, 3));
        JPTablero.setBounds(45, 10, 210, 210);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Botones[i][j] = new JButton();
                Botones[i][j].addActionListener(this);
                JPTablero.add(Botones[i][j]);
            }
        }

        add(JPTablero);

        JBNuevo = new JButton("Nuevo");
        JBNuevo.setBounds(25, 230, 100, 20);
        JBNuevo.addActionListener(this);
        add(JBNuevo);

        JBCancelar = new JButton("Cancelar");
        JBCancelar.setBounds(175, 230, 100, 20);
        JBCancelar.addActionListener(this);
        add(JBCancelar);

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actualizarTablero(int posx, int posy) {

        Icon equis = new ImageIcon(getClass().getResource("/imagenes/X.png"));
        Icon lao = new ImageIcon(getClass().getResource("/imagenes/O.png"));
        if (nuevaLogica.getTurnoXO() == 'x') {
            Botones[posx][posy].setIcon(equis);
            Botones[posx][posy].setEnabled(false);
            nuevaLogica.evaluarXO(posx, posy);
        } else if (nuevaLogica.getTurnoXO() == 'o') {
            Botones[posx][posy].setIcon(lao);
            Botones[posx][posy].setEnabled(false);
            nuevaLogica.evaluarXO(posx, posy);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == JBNuevo) {
            InterfaceTriki nuevaInterface = new InterfaceTriki();
            this.dispose();
        } else if (ae.getSource() == JBCancelar) {
            this.dispose();
        } else {
            for (int i = 0; i < Botones.length; i++) {
                for (int j = 0; j < Botones.length; j++) {
                    if (ae.getSource() == Botones[i][j]) {
                        actualizarTablero(i, j);
                        if (nuevaLogica.getGanador() == true) {
                            JOptionPane.showMessageDialog(null, "GANASTE JUGADOR " 
                                    + Character.toUpperCase(nuevaLogica.getTurnoXO()));
                            for (int k = 0; k < 3; k++) {
                                for (int l = 0; l < 3; l++) {
                                    Botones[k][l].setEnabled(false);
                                }
                            }
                        } else {
                            nuevaLogica.cambiarTurnoXO();
                        }
                    }
                }
            }
        }
    }
}
