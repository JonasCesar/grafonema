/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import controller.Pag01Controller;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author shadows
 */
public class ModelPag01 {
    private String unidadeAtual;
    private String caminhoAudio;
    
    private File arquivo;

    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView = new MediaView();
    
    public ModelPag01() {
        this.unidadeAtual = "u00";
    }

    public String getUnidadeAtual() {
        return this.unidadeAtual;
    }
    /**
     * OBS: Enviar a atualização da unidade para todas as
     * classes que utilizarem
     * @param unidadeAtual 
     * @param classe 
     */
    public void setUnidadeAtual(String unidadeAtual) {
        
        this.unidadeAtual = unidadeAtual;        
    }

    public void tocarAudio() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/l1p1.MP3";
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
    
    
}
