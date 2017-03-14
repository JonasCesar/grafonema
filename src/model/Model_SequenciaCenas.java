/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Gui_JogoPrincipalController;
import controller.Gui_SequenciaCenasController;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author shadows
 */
public class Model_SequenciaCenas {

    @FXML
    private ImageView imgView;
    private File arquivoImagem;
    private EventHandler<ActionEvent> c1, c2, c3, c4, c5;
    private TextArea textoAudio;
    private String caminhoAudio;
    private File arquivo;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView = new MediaView();
    private Stage window;
    private int faseAtual;
    String caminho1, caminho2, caminho3, caminho4, caminho5 = "";
    private File arquivoImagemPB;
    int faseTempAtual = 0;

    public Model_SequenciaCenas(ImageView imagem) {
        imgView = imagem;
        faseAtual = 1;
        faseTempAtual = 0;
    }

    public void iniciarCenas() throws MalformedURLException {
        caminho1 = caminho2 = caminho3 = caminho4 = caminho5 = "";
        switch (getFaseAtual()) {
            case 1:
                System.out.println("Unidade 1");
                caminho1 = "fase1/img1";
                caminho2 = "fase1/img2";
                caminho3 = "fase1/img3";
                caminho4 = "fase1/img4";
                caminho5 = "fase1/img5";
                break;
            case 2:
                System.out.println("Unidade 2");
                caminho1 = "fase2/img1";
                caminho2 = "fase2/img2";
                caminho3 = "fase2/img3";
                caminho4 = "fase2/img4";
                caminho5 = "fase2/img5";
                break;
            case 3:
                System.out.println("Unidade 3");
                caminho1 = "fase3/img1";
                caminho2 = "fase3/img2";
                caminho3 = "fase3/img3";
                caminho4 = "fase3/img4";
                caminho5 = "fase3/img5";
                break;
            case 4:
                caminho1 = "fase4/img1";
                caminho2 = "fase4/img2";
                caminho3 = "fase4/img3";
                caminho4 = "fase4/img4";
                caminho5 = "fase4/img5";
                break;
            case 5:
                caminho1 = "fase5/img1";
                caminho2 = "fase5/img2";
                caminho3 = "fase5/img3";
                caminho4 = "fase5/img4";
                caminho5 = "fase5/img5";
                break;
            case 6:
                caminho1 = "fase5/img1";
                caminho2 = "fase5/img2";
                caminho3 = "fase5/img3";
                caminho4 = "fase5/img4";
                caminho5 = "fase5/img5";
                break;
            case 7:
                caminho1 = "fase5/img1";
                caminho2 = "fase5/img2";
                caminho3 = "fase5/img3";
                caminho4 = "fase5/img4";
                caminho5 = "fase5/img5";
                break;
        }

        //evento que represanta a primeira cena do acerto
        c1 = (ActionEvent event) -> {
            try {
                arquivoImagem = new File("src/Imagens/" + caminho1 + ".png");
                imgView.setImage(new Image(arquivoImagem.toURI().toURL().toString()));
                caminhoAudio = "src/audiosEfeitosSonoros/espada.mp3";
                tocarAudio(caminhoAudio);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Model_SequenciaCenas.class.getName()).log(Level.SEVERE, null, ex);
            }

        };

        //evento que representa a ação a ser feita depois da 
        //animação de acerto
        c2 = (ActionEvent event) -> {
            System.out.println("Segundo");
            caminhoAudio = "src/audiosEfeitosSonoros/espada2.mp3";
            tocarAudio(caminhoAudio);
            arquivoImagem = new File("src/Imagens/" + caminho2 + ".png");

            try {
                imgView.setImage(new Image(arquivoImagem.toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(Model_SequenciaCenas.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        c3 = (ActionEvent event) -> {
            System.out.println("Terceiro");
            caminhoAudio = "src/audiosEfeitosSonoros/espada2.mp3";
            tocarAudio(caminhoAudio);
            arquivoImagem = new File("src/Imagens/" + caminho3 + ".png");
            try {
                imgView.setImage(new Image(arquivoImagem.toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(Model_SequenciaCenas.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        c4 = (ActionEvent event) -> {
            System.out.println("quarto");
            caminhoAudio = "src/audiosEfeitosSonoros/espada.mp3";
            tocarAudio(caminhoAudio);
            arquivoImagem = new File("src/Imagens/" + caminho4 + ".png");
            try {
                imgView.setImage(new Image(arquivoImagem.toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(Model_SequenciaCenas.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        c5 = (ActionEvent event) -> {
            System.out.println("Quinto");
            caminhoAudio = "src/audiosEfeitosSonoros/espada.mp3";
            tocarAudio(caminhoAudio);
            arquivoImagem = new File("src/Imagens/" + caminho5 + ".png");
            try {
                imgView.setImage(new Image(arquivoImagem.toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                Logger.getLogger(Model_SequenciaCenas.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        /**
         * c6 = (ActionEvent event) -> { System.out.println("Quinto");
         * arquivoImagem = new File("src/controller/g2.jpg"); try {
         * imgView.setImage(new
         * Image(arquivoImagem.toURI().toURL().toString())); } catch
         * (MalformedURLException ex) {
         * Logger.getLogger(Model_SequenciaCenas.class.getName()).log(Level.SEVERE,
         * null, ex); } };*
         */

        new Timeline(
                new KeyFrame(Duration.seconds(0), c1),
                new KeyFrame(Duration.seconds(2), c2),
                new KeyFrame(Duration.seconds(4), c3),
                new KeyFrame(Duration.seconds(6), c4),
                new KeyFrame(Duration.seconds(8), c5)).play();

    }

    public void executarCenaInicial() {
        caminho1 = caminho2 = caminho3 = "";

        switch (getFaseAtual()) {
            case 1:
                //System.out.println("SDs");
                caminho1 = "fase1/fe";
                caminho2 = "fase1/fepb";
                caminho3 = "audios_vogais/frase1";
                break;
            case 2:
                System.out.println("Entrou aqui");
                caminho1 = "fase2/inicioFase2";
                caminho2 = "fase2/inicioFase2pb";
                caminho3 = "audios_vogais/frase1";
                break;
            case 3:
                caminho1 = "fase3/inicioFase3";
                caminho2 = "fase3/inicioFase3pb";
                caminho3 = "audios_vogais/frase1";
                break;
            default:
                break;
        }

        c1 = (ActionEvent event) -> {
            try {
                arquivoImagem = new File("src/Imagens/" + caminho1 + ".jpg");
                imgView.setImage(new Image(arquivoImagem.toURI().toURL().toString()));
                caminhoAudio = "src/" + caminho3 + ".mp3";
                tocarAudio(caminhoAudio);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Model_SequenciaCenas.class.getName()).log(Level.SEVERE, null, ex);
            }

        };

        c2 = (ActionEvent event) -> {
            try {
                arquivoImagemPB = new File("src/Imagens/" + caminho2 + ".jpg");
                imgView.setImage(new Image(arquivoImagemPB.toURI().toURL().toString()));

            } catch (MalformedURLException ex) {
                Logger.getLogger(Model_SequenciaCenas.class.getName()).log(Level.SEVERE, null, ex);
            }

        };

        c3 = (ActionEvent event) -> {
            window = (Stage) imgView.getScene().getWindow();
            Parent cenaPrincipal = null;

            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Gui_JogoPrincipal.fxml"));
            try {
                cenaPrincipal = (Parent) fxmloader.load();
            } catch (IOException ex) {
                Logger.getLogger(Model_SequenciaCenas.class.getName()).log(Level.SEVERE, null, ex);
            }
            Gui_JogoPrincipalController jogoPrincipalController = fxmloader.<Gui_JogoPrincipalController>getController();
            jogoPrincipalController.iniciarJogo();

            Scene scene = new Scene(cenaPrincipal, 1200, 700);
            window.setTitle("Grafonema");
            window.setScene(scene);
            window.show();

        };
        new Timeline(
                new KeyFrame(Duration.seconds(0), c1),
                new KeyFrame(Duration.seconds(10), c2),
                new KeyFrame(Duration.seconds(20), c3)).play();

    }

    public void executarCenaFimDaFase() throws MalformedURLException {
        int valorRetorno = 0;
        //System.out.println("ExecutarCenaFimDaFase");
        caminho1 = caminho2 = caminho3 = "";
        System.out.println(getFaseAtual());
        switch (getFaseAtual()) {
            case 1:
                caminho1 = "fase1/fim";
                caminho3 = "src/audios_vogais/frase1.mp3";
                setFaseAtual(2);
                break;
            case 2:
                caminho1 = "fase2/fim";
                caminho3 = "src/audios_vogais/frase1.mp3";
                setFaseAtual(3);
                break;
            case 3:
                caminho1 = "fase3/fim";
                caminho3 = "src/audios_vogais/frase1.mp3";
                setFaseAtual(3);
                break;
            case 4:
                caminho1 = "fase4/fim";
                caminho3 = "src/audios_vogais/frase1.mp3";
                setFaseAtual(3);
                break;
            case 5:
                caminho1 = "fase5/fim";
                caminho3 = "src/audios_vogais/frase1.mp3";
                setFaseAtual(3);
                break;
            case 6:
                caminho1 = "fase6/fim";
                caminho3 = "src/audios_vogais/frase1.mp3";
                setFaseAtual(3);
                break;
            case 7:
                caminho1 = "fase2/fim";
                caminho3 = "src/audios_vogais/frase1.mp3";
                setFaseAtual(3);
                break;
            default:
                break;

        }
        arquivoImagem = new File("src/Imagens/" + caminho1 + ".jpg");
        imgView.setImage(new Image(arquivoImagem.toURI().toURL().toString()));
        tocarAudio(caminho3);

    }

    public void executarCenaMeioFase(){
        caminho1 = caminho2 = caminho3 = "";

        switch (getFaseAtual()) {
            case 1:
                //System.out.println("SDs");
                caminho1 = "fase1/fe";
                caminho2 = "fase1/fepb";
                caminho3 = "audios_vogais/frase1";
                break;
            case 2:
                System.out.println("Entrou aqui");
                caminho1 = "fase2/inicioFase2";
                caminho2 = "fase2/inicioFase2pb";
                caminho3 = "audios_vogais/frase1";
                break;
            case 3:
                caminho1 = "fase3/inicioFase03";
                caminho2 = "fase3/inicioFase03pb";
                caminho3 = "audios_vogais/frase1";
                break;
            case 4:
                caminho1 = "fase4/inicioFase04";
                caminho2 = "fase4/inicioFase04pb";
                caminho3 = "audios_vogais/frase1";                
                break;
            case 5:
                caminho1 = "fase5/inicioFase05";
                caminho2 = "fase5/inicioFase05pb";
                caminho3 = "audios_vogais/frase1";
                break;
            case 6:
                caminho1 = "fase6/inicioFase06";
                caminho2 = "fase6/inicioFase06pb";
                caminho3 = "audios_vogais/frase1";
                break;
            case 7:
                caminho1 = "fase7/inicioFase07";
                caminho2 = "fase7/inicioFase07pb";
                caminho3 = "audios_vogais/frase1";
        }

        c1 = (ActionEvent event) -> {
            try {
                arquivoImagem = new File("src/Imagens/" + caminho1 + ".jpg");
                imgView.setImage(new Image(arquivoImagem.toURI().toURL().toString()));
                caminhoAudio = "src/" + caminho3 + ".mp3";
                tocarAudio(caminhoAudio);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Model_SequenciaCenas.class.getName()).log(Level.SEVERE, null, ex);
            }

        };

        c2 = (ActionEvent event) -> {
            try {
                arquivoImagemPB = new File("src/Imagens/" + caminho2 + ".jpg");
                imgView.setImage(new Image(arquivoImagemPB.toURI().toURL().toString()));

            } catch (MalformedURLException ex) {
                Logger.getLogger(Model_SequenciaCenas.class.getName()).log(Level.SEVERE, null, ex);
            }

        };

        
        new Timeline(
                new KeyFrame(Duration.seconds(0), c1),
                new KeyFrame(Duration.seconds(10), c2)).play();
    }
    
    public void tocarAudio(String caminhoAudio) {
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

    public void setFaseAtual(int faseAtual) {
        this.faseAtual = faseAtual;
    }

    public int getFaseAtual() {
        return faseAtual;
    }

}
