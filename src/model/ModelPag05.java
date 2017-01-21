/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.MenuInicialController;
import controller.Pag04Controller;
import controller.Pag06Controller;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelPag05 {
    private Stage janela;
    private String unidadeAtual;
    @FXML
    private Label p1;
    @FXML
    private Label p4;
    @FXML
    private Label p3;
    @FXML
    private Label p5;
    @FXML
    private Label p2;
    
    @FXML
    private Label f2;
    @FXML
    private Label f1;
    
    @FXML
    private Label espaco;
    
    private String caminhoAudio;
    private File arquivo;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView = new MediaView();
    
    public ModelPag05(Label p1,Label p2, Label p3, Label p4, Label p5, Label f1, Label f2, Label espaco) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.f1 = f1;
        this.f2 = f2;
        this.espaco = espaco;
        this.unidadeAtual = "u00";
    }    

    public void setUnidadeAtual(String unidadeAtual) {
        this.unidadeAtual = unidadeAtual;
        switch(unidadeAtual){
            case "u01":
                p1.setText("VA");
                p2.setText("VE");
                p3.setText("VI");
                p4.setText("VO");
                p5.setText("VU");                
                f1.setText("POL");
                espaco.setText("");
                break;
            default:
                break;
        }
    }

    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag06.fxml"));        
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag06Controller pg06Cont = fxmloader.<Pag06Controller>getController();
        pg06Cont.setUnidadeAtual(getUnidadeAtual());
        
        Scene cena = new Scene(proximaCena, 900, 700);//tamanho
        janela.setTitle("Projeto 2");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface
        pg06Cont.tocarAudio();
    }

    public void paginaAnterior(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag04.fxml"));        
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag04Controller pg04Cont = fxmloader.<Pag04Controller>getController();
        
        exibirCena(proximaCena);
        pg04Cont.setUnidadeAtual(getUnidadeAtual());
        pg04Cont.tocarAudio();
    }

    private String getUnidadeAtual() {
        return this.unidadeAtual;
    }

    public boolean verificarEscolhaSilaba(MouseEvent event) {
        String silabaEscolhida = ((Label)event.getSource()).getText();
        boolean opcaoCorreta = false;
        switch(getUnidadeAtual()){
            case "u01":
                if(silabaEscolhida.equals("VO")){
                    opcaoCorreta = true;
                }                
                break;
            default:
                break;
        }
        
        return opcaoCorreta;
    }

    public void tocarAudio() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/l1p5.MP3";
                break;
            default:
                System.out.println("Não foi");
                break;
        }
        play(caminhoAudio);
    }

    public void pararAudio() {
        mediaPlayer.stop();
    }

    public void alterarLabelEspaco(MouseEvent evento) {
        
        espaco.setText(((Label)evento.getSource()).getText());
        ((Label)evento.getSource()).setVisible(false);
    }

   public void menuInicial(ActionEvent event) throws IOException {
         janela = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/menuInicial.fxml"));
        
        Parent proximaCena = (Parent) fxmloader.load();
        MenuInicialController miController = fxmloader.<MenuInicialController>getController();
        
        exibirCena(proximaCena);
    }
   
   public void exibirCena(Parent proximaCena) {
        Scene cena = new Scene(proximaCena, 900, 700);
        janela.setTitle("Menu Inicial");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface  
    }
   
   public void play(String caminhoAudio){
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
    
}
