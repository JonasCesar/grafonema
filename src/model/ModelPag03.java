/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Pag02Controller;
import controller.Pag04Controller;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelPag03 {

    private String unidadeAtual, caminhoAudio;
    private File arquivo;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView = new MediaView();
    private Image i1, i2, i3, i4, i5, i6;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img6;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img4;
    
    private File f1, f2, f3, f4, f5, f6;
    private Stage janela;

    public ModelPag03(ImageView i1, ImageView i2, ImageView i3, ImageView i4, ImageView i5, ImageView i6) {
        this.img1 = i1;
        this.img2 = i2;
        this.img3 = i3;
        this.img4 = i4;
        this.img5 = i5;
        this.img6 = i6;
       
        unidadeAtual = "u00";
        caminhoAudio = "";
    }

    public void setUnidadeAtual(String unidade) {
        this.unidadeAtual = unidade;
    }

    public String getUnidadeAtual() {
        return unidadeAtual;
    }

    public void paginaAnterior(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag02.fxml"));
        
        
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag02Controller pag02Cont = fxmloader.<Pag02Controller>getController();
        
        Scene cena = new Scene(proximaCena, 900, 700);//tamanho
        janela.setTitle("Projeto 2");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface
        pag02Cont.setUnidadeAtual(getUnidadeAtual());
    }

    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag04.fxml"));
        
        
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag04Controller pg04Cont = fxmloader.<Pag04Controller>getController();
        pg04Cont.setUnidadeAtual(getUnidadeAtual());
        
        Scene cena = new Scene(proximaCena, 900, 700);//tamanho
        janela.setTitle("Projeto 2");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface

    }

    public void verificarImagem(MouseEvent event) {
        String imgClicada = ((ImageView)event.getSource()).getId();
        
        switch (getUnidadeAtual()) {
            //acertos img1, img3, img5
            case "u01":
                if(imgClicada.equals("img1") || imgClicada.equals("img3") || imgClicada.equals("img5")){
                    System.out.println("Coco");
                    ((ImageView)event.getSource()).setDisable(true);
                }else{
                    
                }
                   
                
                break;
            default:
                break;
        }
    }

    public void tocarAudioInicial() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/l1p3.MP3";
                break;
            default:
                System.out.println("Não foi");
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

    public void definirImagens(String unidadeAtual) throws MalformedURLException {
        String caminho = "";
        /**
         * Criar arquivos que chamam as imagens e pegar a URL desses arquivos
         */
        switch(unidadeAtual){
            case "u01": 
                f1= new File("src/imagens/licao01/1.png");
                f2= new File("src/imagens/licao01/2.png");
                f3= new File("src/imagens/licao01/3.png");
                f4= new File("src/imagens/licao01/4.png");
                f5= new File("src/imagens/licao01/5.png");
                f6= new File("src/imagens/licao01/6.png");
                
                /**
                i2 = new Image(getClass().getResourceAsStream("src/imagens/licao01/2.png"));
                i3 = new Image(getClass().getResourceAsStream("src/imagens/licao01/3.png"));
                i4 = new Image(getClass().getResourceAsStream("src/imagens/licao01/4.png"));
                i5 = new Image(getClass().getResourceAsStream("src/imagens/licao01/5.png"));
                i6 = new Image(getClass().getResourceAsStream("src/imagens/licao01/6.png"));
                * */
                
                
                break;
            default:
                System.out.println("Coco");
                break;
        }
        img1.setImage(new Image(f1.toURI().toURL().toString()));
        img2.setImage(new Image(f2.toURI().toURL().toString()));
        img3.setImage(new Image(f3.toURI().toURL().toString()));
        img4.setImage(new Image(f4.toURI().toURL().toString()));
        img5.setImage(new Image(f5.toURI().toURL().toString()));
        img6.setImage(new Image(f6.toURI().toURL().toString()));
        
        
    }

}
