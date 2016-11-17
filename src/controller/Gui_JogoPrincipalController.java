/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import model.JogoPrincipal;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    @FXML
    private Label pontuacao;
    @FXML
    private ProgressBar lifeBar;

    private Stage window;
    boolean indicacaoPular;//indica que o jogador acionou o botão pular
    boolean pularErro;
    private EventHandler<ActionEvent> eventoVoltar;

    private Scene temp;
    private EventHandler<ActionEvent> eventoCenas;
    private EventHandler<ActionEvent> eventoAcerto, eventoFimAcerto;

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
        audio.setText(vogais[indiceVogal.nextInt(5)]);
        jogoPrincipal = new JogoPrincipal(btn_1, btn_2, btn_3, btn_4, btn_5, pular, audio, pontuacao, lifeBar);
        jogoPrincipal.iniciarMatrizAudiosVogal();

        eventoAcerto = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Button btemp = jogoPrincipal.opcaoCorreta(event);
                (btemp).setText("X");
            }
        };

        eventoFimAcerto = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    jogoPrincipal.gerarOpcaoAleatoria();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Gui_JogoPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Gui_JogoPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                indicacaoPular = true;
            }
        };

        jogoPrincipal.gerarSomAleatorio();

        Timer timer = new Timer();
        //criação da tarefa que vai executar durante 1 segundo
        timer.scheduleAtFixedRate(new TimerTask() {

            int i = 30;

            @Override
            public void run() {

                //Platform.runLater para alterar elementos da interface do javaFX
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {

                        /*condição que faz o contador de segundos 
                        continuar em 30 durante a exibição da cena 
                        */
                        Timer timer2 = new Timer();
                        timer2.scheduleAtFixedRate(new TimerTask() {

                            @Override
                            public void run() {
                                if (jogoPrincipal.getMostrandoCena()) {
                                    i = 30;
                                    System.out.println("setou o i como 30");
                                }
                            }
                        }, 0, 50);

                        tempo.setText("" + i);
                        i--;
                        if (i == -1) {
                            System.out.println("timer cacelado");

                            try {
                                jogoPrincipal.gerarOpcaoAleatoria();
                            } catch (InterruptedException | IOException ex) {
                                Logger.getLogger(Gui_JogoPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            jogoPrincipal.reduzirLifeBar();
                            jogoPrincipal.incrementarErro();
                            Button temp = jogoPrincipal.opcaoCorreta(null);
                            //se o jogador perdeu o jogo exibir a tela de game over
                            if (!jogoPrincipal.isGameOver()) {
                                //animação
                                jogoPrincipal.mostrarOpcaoCorreta(temp);
                                pularErro = true;
                            } else {
                                timer.cancel();
                                //animação
                                jogoPrincipal.mostraFimDeJogo(temp);

                            }
                        }
                        //se o jogador pulou ou errou voltar o tempo para 30 segundos
                        if ((indicacaoPular) || (pularErro)) {
                            System.out.println(i);
                            i = 30;
                            indicacaoPular = false;
                            pularErro = false;
                        }
                    }
                });
            }
        }, 0, 1000);
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
        indicacaoPular = true;
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

            //trocar a cor do botão
            jogoPrincipal.incrementarPontuacao();
            jogoPrincipal.incrementarAcerto();

            if (jogoPrincipal.jogador.getAcertosTotal() == 10) {
                jogoPrincipal.setMostrandoCena(true);//usado para setar como 30 o contador de segundos
                System.out.println("mostrando cena = true");
                jogoPrincipal.mostrarCenas();

            } else {
                new Timeline(
                        new KeyFrame(Duration.seconds(0), eventoAcerto),
                        new KeyFrame(Duration.seconds(5), eventoFimAcerto)).play();
            }

        } else {

            //reduzir barra de vidas
            jogoPrincipal.reduzirLifeBar();
            jogoPrincipal.incrementarErro();
            Button temp = jogoPrincipal.opcaoCorreta(event);
            //animação da opção correta
            jogoPrincipal.mostrarOpcaoCorreta(temp);

            indicacaoPular = true;
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
