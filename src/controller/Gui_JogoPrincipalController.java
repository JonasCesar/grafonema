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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Button btn_1;
    @FXML
    private Button btn_2;
    @FXML
    private Button btn_3;
    @FXML
    private Button btn_4;
    @FXML
    private Button btn_5;
    @FXML
    private Button btn_6;
    @FXML
    private Button pular;
            
    @FXML
    private Label tempo;
    
    @FXML
    private Label pontuacao;

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
        jogoPrincipal.iniciarMatrizAudiosVogal();//inicia a matriz de audios de vogais
        jogoPrincipal.iniciarMatrizAudioSilabas();
        jogoPrincipal.iniciarMatrizSilabasSimplesB();
        jogoPrincipal.inicarMatrizSilabasComplexas2();
        jogoPrincipal.iniciarMatrizSilabasComplexas3();
        jogoPrincipal.iniciarMatrizPalavrasSimples();
        jogoPrincipal.iniciarMatrizSilabasComplexas();
        jogoPrincipal.gerarSomAleatorio();//gerar um som aleatorio
        jogoPrincipal.iniciarTimer();//inicia o relógio
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
            //gera uma opção aleatória
            jogoPrincipal.gerarOpcaoAleatoria();
            jogoPrincipal.jogador.setQntPulos(qntPulosAtual);//incrementa quantidade de pulos do jogador
        }
        //seta indicacaoPular como true
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
            jogoPrincipal.incrementarPontuacao();//incrementa a pontuação do jogador
            jogoPrincipal.incrementarAcerto();//incrementar o acerto

            if (jogoPrincipal.jogador.getAcertosTotal() == 10) {
                jogoPrincipal.setMostrandoCena(true);//usado para setar como 30 o contador de segundos
                System.out.println("mostrando cena = true");
                jogoPrincipal.mostrarCenas();//mostra as cenas depois que o jogador acerta 10 vezes

            } else {
                //mostra a animação de acerto
                jogoPrincipal.mostrarAnimacaoAcerto();               
            }

        } else {
            //reduzir barra de vidas
            jogoPrincipal.reduzirLifeBar();
            jogoPrincipal.incrementarErro();//incrementa a quantidade de erro do jogador
            Button temp = jogoPrincipal.opcaoCorreta(event);
            
            //animação da opção correta            
            if (jogoPrincipal.isGameOver()) {//se for o fim do jogo
                temp = jogoPrincipal.opcaoCorreta(event);
                //animação do fim de jogo
                jogoPrincipal.mostraFimDeJogo(temp);
            }else{
                //animação da opção correta
                jogoPrincipal.mostrarOpcaoCorreta(temp);
            }
        }
    }

    /**
     * Executa novamente o áudio
     * @param event botão ouvirAudio
     */
    @FXML
    private void handleOuvirAudio(ActionEvent event) {
        String audio = jogoPrincipal.getAudioAtual();
        jogoPrincipal.tocarAudio(audio);//chama o método que toca o áudio
    }
}
