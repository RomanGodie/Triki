/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triki;

import java.util.Locale;

/**
 *
 * @author Diego Molina
 */
public class LogicaTriki {

    //esta matriz es un equivalente logico de la matriz de botones de la interfaz
    private char[][] matrizXO;
    private char turnoXO;
    private int lasX, lasO;
    private Boolean ganador;

    public LogicaTriki() {
        turnoXO = 'x';
        ganador = false;
        matrizXO = new char[3][3];
    }

    public void evaluarXO(int posx, int posy) {
        matrizXO[posx][posy] = turnoXO;
        evaluarTresEnLinea();
    }

    public void evaluarTresEnLinea() {
        lasX = 0;
        lasO = 0;

        if (ganador.equals(false)) {
////////////////////for que evalua las columnas de la matriz///////////////////
            for (int i = 0; (i < matrizXO.length) && (lasX < 3) && (lasO < 3); i++) {
                lasX = 0;
                lasO = 0;
                for (int j = 0; j < matrizXO.length; j++) {
                    if (matrizXO[i][j] == 'x') {
                        lasX++;
                    } else if (matrizXO[i][j] == 'o') {
                        lasO++;
                    }
                }
            }
///////////////////////////////fin del for//////////////////////////////////////
//if que consulta si en alguna columna se encontraron los tres elementos repetidos
            if ((lasX == 3) || (lasO == 3)) {
                ganador = true;
            } else {
//////////////////////for que evalua las filas de la matriz/////////////////////
                lasX = 0;
                lasO = 0;
                for (int k = 0; (k < matrizXO.length) && (lasX < 3) && (lasO < 3); k++) {
                    lasX = 0;
                    lasO = 0;
                    for (int j = 0; j < matrizXO.length; j++) {
                        if (matrizXO[j][k] == 'x') {
                            lasX++;
                        } else if (matrizXO[j][k] == 'o') {
                            lasO++;
                        }
                    }
                }
///////////////////////////////fin del for//////////////////////////////////////
//if que consulta si en alguna fila se encontraron los tres elementos repetidos
                if (lasX == 3 || lasO == 3) {
                    ganador = true;
                } else {
////////////////do-while que evalua la diagonal de 0,0 a 2,2////////////////////
                    lasX = 0;
                    lasO = 0;
                    int i = 0;
                    int j = 0;
                    do {
                        if (matrizXO[i][j] == 'x') {
                            lasX++;
                        } else if (matrizXO[i][j] == 'o') {
                            lasO++;
                        }
                        i++;
                        j++;
                    } while (lasX < 3 && lasO < 3 && i < 3 && j < 3);
//////////////////////////fin del do-while//////////////////////////////////////
//if que consulta si en la anterior diagonal se encontraron los tres elementos repetidos
                    if (lasX == 3 || lasO == 3) {
                        ganador = true;
                    } else {
/////////////////do-while que evalua la diagonal de 2,0 a 0,2///////////////////
                        lasX = 0;
                        lasO = 0;
                        i = 2;
                        j = 0;
                        do {
                            if (matrizXO[i][j] == 'x') {
                                lasX++;
                            } else if (matrizXO[i][j] == 'o') {
                                lasO++;
                            }
                            i--;
                            j++;
                        } while (lasX < 3 && lasO < 3 && i < 3 && j < 3);
//////////////////////////fin del do-while//////////////////////////////////////
//if que consulta si en la anterior diagonal se encontraron los tres elementos repetidos
                        if (lasX == 3 || lasO == 3) {
                            ganador = true;
                        }
                    }
                }
            }
        }
    }

    public void cambiarTurnoXO() {
        if (turnoXO == 'x') {
            turnoXO = 'o';
        } else {
            turnoXO = 'x';
        }
    }

    public boolean getGanador() {
        return ganador;
    }

    public char getTurnoXO() {
        return turnoXO;
    }
}
