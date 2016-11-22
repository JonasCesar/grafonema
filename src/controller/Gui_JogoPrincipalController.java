/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import model.JogoPrincipal;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jonas
 */
public class Gui_JogoPrincipalController implements Initializable {

    @FXML
    private Button btn_1, btn_2, btn_3, btn_5, btn_4, pular;
    @FXML
    private Label tempo, pontuacao;

    private JogoPrincipal jogoPrincipal;
    @FXML
    private ProgressBar lifeBar;

    private Stage window;
    boolean indicacaoPular, pularErro ;//indica que o jogador acionou o botão pular

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String vogais[] = {"letra_a", "letra_e", "letra_i", "letra_o", "letra_u"};
        Random indiceVogal = new Random();
        jogoPrincipal = new JogoPrincipal(btn_1, btn_2, btn_3, btn_4, btn_5, pular, pontuacao, lifeBar, tempo);
        jogoPrincipal.iniciarMatrizAudiosVogal();        
        jogoPrincipal.gerarSomAleatorio();
        jogoPrincipal.iniciarTimer();        
    }

    /**
     * Trata o evento de quando o botão "Pular" for pressionado
     *
     * @param event
     */
    @FXML
    private void handlePular(ActionEvent event) throws InterruptedException, IOException {
        int qntPulosAtual = jogoPrincipal.jogador.getQntPulos();
        //se o jogador já pulou 3 vezes
        //(pois a quantidade de pulos é iniciada com 0
        if (qntPulosAtual == 2) {
            //desabilita o botão de pular
            jogoPrincipal.desabilitarPulo();
        } else {
            jogoPrincipal.gerarOpcaoAleatoria();
            jogoPrincipal.jogador.setQntPulos(qntPulosAtual);
        }
        jogoPrincipal.setIndicacaoPular(true);
    }

    /**
     * Trata os eventos de quando um dos botões correspondentes às respostas são
     * pressionados
     *
     * @param event
     * @throws InterruptedException
     */
    @FXML
    //método referente aos botões de opção
    private void handleBotoes(ActionEvent event) throws InterruptedException, IOException {
        //Se a opção escolhida está certa
        if (jogoPrincipal.verificarRelacaoGaFonema(event)) {
            jogoPrincipal.incrementarPontuacao();
            jogoPrincipal.incrementarAcerto();

            if (jogoPrincipal.jogador.getAcertosTotal() == 10) {
                jogoPrincipal.setMostrandoCena(true);//usado para setar como 30 o contador de segundos
                System.out.println("mostrando cena = true");
                jogoPrincipal.mostrarCenas();

            } else {
                jogoPrincipal.mostrarAnimacaoAcerto();               
            }

        } else {
            //reduzir barra de vidas
            jogoPrincipal.reduzirLifeBar();
            jogoPrincipal.incrementarErro();
            Button temp = jogoPrincipal.opcaoCorreta(event);
            //animação da opção correta
            jogoPrincipal.mostrarOpcaoCorreta(temp);
            jogoPrincipal.setIndicacaoPular(true);
            //indicacaoPular = true;
            
            if (jogoPrincipal.isGameOver()) {
                temp = jogoPrincipal.opcaoCorreta(event);

                //animação do fim de jogo
                jogoPrincipal.mostraFimDeJogo(temp);
            }
        }
        //colocar aqui se acertos for igual a 10 mostrar a cena da fase que passou
    }

    @FXML
    private void handleOuvirAudio(ActionEvent event) {
        String audio = jogoPrincipal.getAudioAtual();
        jogoPrincipal.tocarAudio(audio);
    }
}
