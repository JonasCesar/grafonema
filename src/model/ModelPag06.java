/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Pag05Controller;
import controller.Pag07Controller;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelPag06 {
    private String unidadeAtual;
    private Stage janela;
    private File arquivo;
    private String caminhoAudio;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView = new MediaView();
    
    private Label p1, p2;

    public ModelPag06(Label p1, Label p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.unidadeAtual = "u00";
    }   

    public void setUnidadeAtual(String unidade) {
        this.unidadeAtual = unidade;
    }

    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag07.fxml"));
        
        
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag07Controller pg07Cont = fxmloader.<Pag07Controller>getController();
        System.out.println("Pag 6 "+getUnidadeAtual());
        pg07Cont.setUnidadeAtual(getUnidadeAtual());
        
        Scene cena = new Scene(proximaCena, 900, 700);//tamanho
        janela.setTitle("Projeto 2");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface
        pg07Cont.tocarAudio();
    }

    public void paginaAnterior(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag05.fxml"));        
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag05Controller pg05Cont = fxmloader.<Pag05Controller>getController();
        
        Scene cena = new Scene(proximaCena, 900, 700);//tamanho
        janela.setTitle("Projeto 2");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface
        pg05Cont.setUnidadeAtual(getUnidadeAtual());
        pg05Cont.tocarAudio();
    }

    public String getUnidadeAtual() {
        return this.unidadeAtual;
    }

    public boolean verificarResposta(String resposta) {
        boolean respostaCorreta = false;
        switch(getUnidadeAtual()){
            case "u01":
                if(resposta.toUpperCase().equals("VOVÔ")){
                    respostaCorreta = true;
                    
                }                
                break;
            default:
                break;
        }
        return respostaCorreta;
    }

    public void tocarAudio() {
    
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/l1p6.MP3";
                break;
            default:
                break;
        }

        //cria um objeto arquivo que recebe o nome do arquivo como parâmetro
        arquivo = new File(caminhoAudio);
        //pega todo do caminho referente ao objeto File criado
        caminhoAudio = arquivo.getAbsolutePath();
        //troca todas as barras invertidas duplas ('\\') por '/'
        caminhoAudio = caminhoAudio.replace("\\", "/");
        //cria um objeto Media que recebe o objeto 'arquivo' como parâmetro
        media = new Media(new File(caminhoAudio).toURI().toString());
        //cria um objeto mediaPlayer que permite qua uma media possa ser reproduzida
        mediaPlayer = new MediaPlayer(media);
        //toca o audio automaticamente
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    public void definirLabels() {
        switch(getUnidadeAtual()){
            case "u01":
                p1.setText("O");
                p2.setText("É MEU AMIGO");
                break;
            default:
                break;
        }
    }

    public void pararAudio() {
        mediaPlayer.stop();
    }
    
}
