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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
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
    @FXML
    private FadeTransition ft;

    private EventHandler<ActionEvent> eventoFinal;

    private EventHandler<ActionEvent> eventoGameOver;

    private Stage window;

    boolean indicacaoPular;
    boolean pularErro;

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
        eventoFinal = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    jogoPrincipal.gerarOpcaoAleatoria();
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(Gui_JogoPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        eventoGameOver = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window = (Stage) btn_1.getScene().getWindow();
                Parent cenaPrincipal = null;
                try {
                    cenaPrincipal = FXMLLoader.load(getClass().getResource("/interfaces/Gui_GameOver.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(Gui_JogoPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(cenaPrincipal, 900, 700);
                window.setTitle("Grafonema");
                window.setScene(scene);
                window.show();
            }

        };

        Timer timer = new Timer();
        //criação da tarefa que vai executar durante 1 segunda
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 30;

            @Override
            public void run() {

                //Platform.runLater para alterar elementos da interface do javaFX
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        tempo.setText("" + i);
                        i--;
                        if (i == 0) {
                            System.out.println("timer cacelado");

                            try {
                                jogoPrincipal.gerarOpcaoAleatoria();
                            } catch (InterruptedException | IOException ex) {
                                Logger.getLogger(Gui_JogoPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            jogoPrincipal.reduzirLifeBar();
                            jogoPrincipal.incrementarErro();
                            Button temp = jogoPrincipal.opcaoCorreta(null);
                            if (!jogoPrincipal.isGameOver()) {
                                
                                new Timeline(
                                        new KeyFrame(Duration.seconds(0), new KeyValue(temp.opacityProperty(), .1)),
                                        new KeyFrame(Duration.seconds(3), new KeyValue(temp.opacityProperty(), 1)),
                                        new KeyFrame(Duration.seconds(2), eventoFinal)).play();
                                pularErro = true;
                            } else {
                                timer.cancel();
                                new Timeline(
                                        new KeyFrame(Duration.seconds(0), new KeyValue(temp.opacityProperty(), .1)),
                                        new KeyFrame(Duration.seconds(3), new KeyValue(temp.opacityProperty(), 1)),
                                        new KeyFrame(Duration.seconds(2), eventoGameOver)).play();
                            }
                        }
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
    private void handleBotoes(ActionEvent event) throws InterruptedException, IOException {
        //Se a opção escolhida está certa
        if (jogoPrincipal.verificarRelacaoGaFonema(event)) {
            //trocar a cor do botão
            jogoPrincipal.incrementarPontuacao();
            jogoPrincipal.incrementarAcerto();
            jogoPrincipal.gerarOpcaoAleatoria();
            indicacaoPular = true;
        } else {

            //reduzir barra de vidas
            jogoPrincipal.reduzirLifeBar();
            jogoPrincipal.incrementarErro();
            Button temp = jogoPrincipal.opcaoCorreta(event);
            new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(temp.opacityProperty(), .1)),
                    new KeyFrame(Duration.seconds(3), new KeyValue(temp.opacityProperty(), 1)),
                    new KeyFrame(Duration.seconds(2), eventoFinal)).play();
            indicacaoPular = true;
            if (jogoPrincipal.isGameOver()) {
                temp = jogoPrincipal.opcaoCorreta(event);
                new Timeline(
                        new KeyFrame(Duration.seconds(0), new KeyValue(temp.opacityProperty(), .1)),
                        new KeyFrame(Duration.seconds(3), new KeyValue(temp.opacityProperty(), 1)),
                        new KeyFrame(Duration.seconds(2), eventoGameOver)).play();
            }

        }
    }
}
