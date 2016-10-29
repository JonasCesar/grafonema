/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.JogoPrincipal;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author jonas
 */
public class Gui_JogoPrincipalController implements Initializable {

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
    
    @FXML
    private Label tempo;
    
    

    private JogoPrincipal jogoPrincipal;
    @FXML
    private Button pular;
    @FXML
    private Label audio;


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String vogais[] = {"letra_a", "letra_e","letra_i","letra_o","letra_u"};
        Random indiceVogal = new Random();
        audio.setText(vogais[indiceVogal.nextInt(5)]);
        jogoPrincipal = new JogoPrincipal(btn_1, btn_2, btn_3, btn_4, btn_5, pular,audio);
        jogoPrincipal.iniciarMatrizAudiosVogal();
    }

    @FXML
    private void handlePular(ActionEvent event) {
        int qntPulosAtual = jogoPrincipal.jogador.getQntPulos();
        if (qntPulosAtual == 2) {
            jogoPrincipal.desabilitarPulo();
        } else {
            jogoPrincipal.gerarOpcaoAleatoria();
            jogoPrincipal.jogador.setQntPulos(qntPulosAtual);
        }
    }

    @FXML
    private void handleBotoes(ActionEvent event) {
        if(jogoPrincipal.verificarRelacaoGaFonema(event)){
            //trocar a cor do botão
            ((Button)event.getSource()).setDisable(true);
        }
    }

}
