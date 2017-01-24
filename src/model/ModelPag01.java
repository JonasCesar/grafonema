/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import controller.MenuInicialController;
import controller.Pag02Controller;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
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
public class ModelPag01 {
    private String unidadeAtual;
    private String caminhoAudio;
    
    private File arquivo;

    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView = new MediaView();
    private Stage janela;
    
    
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
    public void setUnidadeAtual(String unidadeAtual, Label tituloUnidade) {
        
        this.unidadeAtual = unidadeAtual;
        tituloUnidade.setText(tituloUnidade.getText()+" "+unidadeAtual.substring(1));
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
        play(caminhoAudio);

        
    }

    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag02.fxml"));
        
        
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag02Controller pg02Cont = fxmloader.<Pag02Controller>getController();
        pg02Cont.setUnidadeAtual(getUnidadeAtual());
        
        exibirCena(proximaCena);
    }

    public void pararAudio() {
        mediaPlayer.stop();        
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

    
    public void tocarAudioPalavraSelecionada(String palavraSelecionada) {
        switch(palavraSelecionada){
            case "VOVÔ":
                caminhoAudio = "src/audios/u01/l1p2a1.MP3";
                break;
            default:
                break;
        }
        play(caminhoAudio);
    }
    
    
}
