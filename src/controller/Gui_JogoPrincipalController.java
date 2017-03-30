/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URISyntaxException;
import model.ModelJogoPrincipal;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import model.FuncaoBotao;

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
    private Button pular;

    @FXML
    private Label tempo;

    @FXML
    private Label pontuacao;

    private ModelJogoPrincipal modelJogoPrincipal;
    @FXML
    private ProgressBar lifeBar;

    private Stage window;
    boolean indicacaoPular, pularErro;//indica que o jogador acionou o botão pular
    @FXML
    private ImageView imagemFundo;
    @FXML
    private Button ouvirAudio;

    public Gui_JogoPrincipalController() {

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagemFundo.toBack();
        String vogais[] = {"letra_a", "letra_e", "letra_i", "letra_o", "letra_u"};
        Random indiceVogal = new Random();
        try {
            modelJogoPrincipal = new ModelJogoPrincipal(btn_1, btn_2, btn_3, btn_4, btn_5, pular, pontuacao, lifeBar, tempo, ouvirAudio);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gui_JogoPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        modelJogoPrincipal.iniciarMatrizAudiosVogal();//inicia a matriz de audios de vogais
        modelJogoPrincipal.iniciarMatrizAudioSilabas();
        modelJogoPrincipal.iniciarMatrizSilabasSimplesB();
        modelJogoPrincipal.iniciarMatrizSilabasComplexas2();
        modelJogoPrincipal.iniciarMatrizSilabasComplexas3();
        modelJogoPrincipal.iniciarMatrizPalavrasSimples();
        modelJogoPrincipal.iniciarMatrizSilabasComplexas();
    }

    FuncaoBotao funcao = new FuncaoBotao();

    public void iniciarJogo() {

        modelJogoPrincipal.gerarSomAleatorio();//gerar um som aleatorio
        modelJogoPrincipal.iniciarTimer();//inicia o relógio

        btn_1.setStyle("-fx-font-size: 30px; \n-fx-pref-width: 80px;\n -fx-pref-height: 80px;");
        btn_2.setStyle("-fx-font-size: 30px; \n -fx-pref-width: 80px;\n -fx-pref-height: 80px;");
        btn_3.setStyle("-fx-font-size: 30px; \n -fx-pref-width: 80px;\n -fx-pref-height: 80px;");
        btn_4.setStyle("-fx-font-size: 30px; \n -fx-pref-width: 80px;\n -fx-pref-height: 80px;");
        btn_5.setStyle("-fx-font-size: 30px; \n -fx-pref-width: 80px;\n -fx-pref-height: 80px;");

    }

    /**
     * Trata o evento de quando o botão "Pular" for pressionado
     *
     * @param event
     */
    @FXML
    private void handlePular(ActionEvent event) throws InterruptedException, IOException, ClassNotFoundException, URISyntaxException, LineUnavailableException, UnsupportedAudioFileException {
        int qntPulosAtual = modelJogoPrincipal.jogador.getQntPulos();
        //se o jogador já pulou 3 vezes
        //(pois a quantidade de pulos é iniciada com 0
        if (qntPulosAtual == 2) {
            //desabilita o botão de pular
            modelJogoPrincipal.desabilitarPulo();
        } else {
            //gera uma opção aleatória
            modelJogoPrincipal.gerarOpcaoAleatoria();
            modelJogoPrincipal.jogador.setQntPulos(qntPulosAtual);//incrementa quantidade de pulos do jogador
        }
        //seta indicacaoPular como true
        modelJogoPrincipal.setIndicacaoPular(true);
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

        //se o clique está permitido realizar as tarefas
        if (funcao.getClique() == 1) {

            funcao.setClique0();//após o primeiro clique, bloquar os botões
            if (modelJogoPrincipal.verificarRelacaoGaFonema(event)) {
                //((Button) event.getSource()).setDisable(true);

                //MUDAR A APARENCIA DO BOTAO EM CASO DE ACERTO
                modelJogoPrincipal.incrementarPontuacao();//incrementa a pontuação do jogador
                modelJogoPrincipal.incrementarAcerto();//incrementar o acerto

                if (modelJogoPrincipal.jogador.getAcertosTotal() == 10) {
                    modelJogoPrincipal.setMostrandoCena(true);//usado para setar como 30 o contador de segundos
                    System.out.println("mostrando cena = true");
                    modelJogoPrincipal.mostrarCenas();//mostra as cenas depois que o jogador acerta 10 vezes

//
                } else {
                    //mostra a animação de acerto
                    modelJogoPrincipal.mostrarAnimacaoAcerto();
                }

            } else {
                //reduzir barra de vidas
                modelJogoPrincipal.mostrarAnimacaoErro(event);
                modelJogoPrincipal.reduzirLifeBar();
                modelJogoPrincipal.incrementarErro();//incrementa a quantidade de erro do jogador
                Button temp = modelJogoPrincipal.opcaoCorreta(event);

                //mostrar a animaçao de erro
                //animação da opção correta            
                if (modelJogoPrincipal.isGameOver()) {//se for o fim do jogo
                    temp = modelJogoPrincipal.opcaoCorreta(event);
                    //animação do fim de jogo
                    modelJogoPrincipal.mostraFimDeJogo(temp);
                } else {
                    //animação da opção correta
                    modelJogoPrincipal.mostrarOpcaoCorreta(temp);
                }
            }
        } else {

            System.out.println("NÃO FAZ NADA" + " valor cliq: " + funcao.getClique());
        }


    }

    /**
     * Executa novamente o áudio
     *
     * @param event botão ouvirAudio
     */
    @FXML
    private void handleOuvirAudio(ActionEvent event) throws ClassNotFoundException, URISyntaxException, LineUnavailableException, UnsupportedAudioFileException, IOException {
        String audio = modelJogoPrincipal.getAudioAtual();
        modelJogoPrincipal.tocarAudio(audio);
    }
}
