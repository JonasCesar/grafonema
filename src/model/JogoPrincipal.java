package model;

import java.util.ArrayList;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author jonas
 */
public class JogoPrincipal {

    @FXML
    private Button btn_1;
    @FXML
    private Button btn_2;
    @FXML
    private Button btn_3;
    @FXML
    private Button btn_5;
    @FXML
    private Button btn_4;
    
    private String vogais[] = {"A", "E", "I", "O", "U"};

    public JogoPrincipal(Button b1, Button b2, Button b3, Button b4, Button b5) {

        this.btn_1 = b1;
        this.btn_2 = b2;
        this.btn_3 = b3;
        this.btn_4 = b4;
        this.btn_5 = b5;

    }

    /**
     * Gera as novas opções que serão exibidas para o jogador
     * 
     *OBS: OS VALORES GERADOS IRÃO VARIAR EM CADA FASE E DEVERÃO SER CORRESPONDENTES
     * AOS ARQUIVOS QUE ESTARÃO SENDO EXECUTADOS
     * @param fase fase em que o jogo se encontra
     */
    public void gerarNovasOpcoes(int fase) {
        if (fase == 0) {
            fase = 1;
        }
        ArrayList novasOpcoes = new ArrayList();
        ArrayList indiceUtilizados = new ArrayList();
        Random indice = new Random();
        switch (fase) {
            case 1:
                int i = 0;
                while(i<5) {
                    int proxValor = indice.nextInt(5);
                    if (!indiceUtilizados.contains(proxValor)) {
                        novasOpcoes.add(proxValor);
                        indiceUtilizados.add(proxValor);
                        i++;
                    }
                }                
                btn_1.setText(vogais[(int)novasOpcoes.get(0)]);
                btn_2.setText(vogais[(int)novasOpcoes.get(1)]);
                btn_3.setText(vogais[(int)novasOpcoes.get(2)]);
                btn_4.setText(vogais[(int)novasOpcoes.get(3)]);
                btn_5.setText(vogais[(int)novasOpcoes.get(4)]);
                break;
            case 2:
                break;
        }

    }

}
