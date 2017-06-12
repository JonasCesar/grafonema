/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Pag01Controller;
import controller.Pag02Controller;
import controller.Pag02aController;
import controller.Pag03Controller;
import controller.Pag03aController;
import controller.Pag03bController;
import controller.Pag03cController;
import controller.Pag03dController;
import controller.Pag04Controller;
import controller.Pag04aController;
import controller.Pag04bController;
import controller.Pag05Controller;
import controller.Pag05aController;
import controller.Pag05bController;
import controller.Pag06Controller;
import controller.Pag06aController;
import controller.Pag07Controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author jonas
 */
public class ModelABC {

    private File imagemArquivo;

    private ModelClasseComum mCC;
    private Stage janela;
    private int unidadeAtual;

    @FXML
    public ImageView imgLetra;

    public Image img;

    //String botaoEscolhido = "";
    private ListView<String> listaPalavras;
    private URL imgMaiusculaForma;
    private URL imgMinusculaForma;
    private URL imgMaiusculaCursiva = null;
    private URL imgMinusculaCursiva = null;

    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;

    public ModelABC(ImageView img1, ImageView img2, ImageView img3, ImageView img4) {
        mCC = new ModelClasseComum(janela, listaPalavras);
        unidadeAtual = 0;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
    }

    public void menuInicial(ActionEvent event) throws IOException {
        mCC.menuInicial(event);
    }

    public void tocarAudio(MouseEvent event) throws IOException {
        mCC.play("ABCAudio/resposta_certa.mp3");
    }

    //função responsável por tocar o som das letras
    public void tocarAudioUnicoLetras(String letra) {
        switch (letra) {
            case "A":
                mCC.play("ABCAudioUnico/a.mp3");
                break;
            case "B":
                mCC.play("ABCAudioUnico/b.mp3");
                break;
//            case "C":
//                mCC.play("ABCAudio/resposta_certa.mp3");
//                break;
//            case "D":
//                mCC.play("ABCAudio/resposta_certa.mp3");
//                break;
//            case "E":
//                mCC.play("ABCAudio/resposta_certa.mp3");
//                break;
//            case "F":
//                mCC.play("ABCAudio/resposta_certa.mp3");
//                break;
        }
    }

    //função responsável por fazer um discurso sobre a letra
    public void tocarSomLetra(String letra) {
        mCC.play("audios/ABCAudioEPalavras/" + letra + ".mp3");
    }

    public void executarAudio(String letra) {

    }

    public void voltar(ActionEvent evento, int pagina, String subPagina) throws IOException {
        mCC.pararAudio();
        janela = (Stage) ((Button) evento.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = null;
        Pag01Controller pag01Cont;
        
        Pag02Controller pag02Cont;
        Pag02aController pag02aCont;
        
        Pag03Controller pag03Cont;
        Pag03aController pag03aCont;
        Pag03bController pag03bCont;
        Pag03cController pag03cCont;
        Pag03dController pag03dCont;        
        
        Pag04Controller pag04Cont;
        Pag04aController pag04aCont;
        Pag04bController pag04bCont;        
        
        Pag05Controller pag05Cont;
        Pag05aController pag05aCont;
        Pag05bController pag05bCont;
                
        Pag06Controller pag06Cont;
        Pag06aController pag06aCont;
                
        Pag07Controller pag07Cont;

        fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag0" + pagina+subPagina+ ".fxml"));

        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();

        switch (""+pagina+subPagina) {
            case "1":
                pag01Cont = fxmloader.<Pag01Controller>getController();                
                mCC.exibirCena(proximaCena, janela);                
                System.out.println(getUnidadeAtual());
                pag01Cont.setUnidadeAtual(getUnidadeAtual());                
                pag01Cont.tocarAudio();                
                break;
            case "2":
                pag02Cont = fxmloader.<Pag02Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag02Cont.setUnidadeAtual(getUnidadeAtual());
                break;
            case "2a":
                pag02aCont = fxmloader.<Pag02aController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag02aCont.setUnidadeAtual(getUnidadeAtual());
                break;
            case "3":
                pag03Cont = fxmloader.<Pag03Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag03Cont.setUnidadeAtual(getUnidadeAtual());
                pag03Cont.audioInicial();
                pag03Cont.setImagens(getUnidadeAtual());
                break;
            case "3a":
                pag03aCont = fxmloader.<Pag03aController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag03aCont.setUnidadeAtual(getUnidadeAtual());
                pag03aCont.audioInicial();
                pag03aCont.setImagens(getUnidadeAtual());
                break;
            case "3b":
                pag03bCont = fxmloader.<Pag03bController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag03bCont.setUnidadeAtual(getUnidadeAtual());
                pag03bCont.audioInicial();
                pag03bCont.setImagens(getUnidadeAtual());
                break;
            case "3c":
                pag03cCont = fxmloader.<Pag03cController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag03cCont.setUnidadeAtual(getUnidadeAtual());
                pag03cCont.audioInicial();
                pag03cCont.setImagens(getUnidadeAtual());
                break;
            case "3d":
                pag03dCont = fxmloader.<Pag03dController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag03dCont.setUnidadeAtual(getUnidadeAtual());
                pag03dCont.audioInicial();
                pag03dCont.setImagens(getUnidadeAtual());
                break;
            case "4":
                pag04Cont = fxmloader.<Pag04Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag04Cont.setUnidadeAtual(getUnidadeAtual());
                pag04Cont.tocarAudio();
                break;
            case "4a":
                pag04aCont = fxmloader.<Pag04aController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag04aCont.setUnidadeAtual(getUnidadeAtual());
                pag04aCont.tocarAudio();
                break;
            case "4b":
                pag04bCont = fxmloader.<Pag04bController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag04bCont.setUnidadeAtual(getUnidadeAtual());
                pag04bCont.tocarAudio();
                break;
            case "5":
                pag05Cont = fxmloader.<Pag05Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag05Cont.setUnidadeAtual(getUnidadeAtual());
                pag05Cont.tocarAudio();
                break;
            case "5a":
                pag05aCont = fxmloader.<Pag05aController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag05aCont.setUnidadeAtual(getUnidadeAtual());
                pag05aCont.tocarAudio();
                break;
            case "5b":
                pag05bCont = fxmloader.<Pag05bController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag05bCont.setUnidadeAtual(getUnidadeAtual());
                pag05bCont.tocarAudio();
                break;            
            case "6":
                pag06Cont = fxmloader.<Pag06Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag06Cont.setUnidadeAtual(getUnidadeAtual());
                pag06Cont.tocarAudio();
                break;
            case "6a":
                pag06aCont = fxmloader.<Pag06aController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag06aCont.setUnidadeAtual(getUnidadeAtual());
                pag06aCont.tocarAudio();
                break;
            case "7":
                pag07Cont = fxmloader.<Pag07Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag07Cont.setUnidadeAtual(getUnidadeAtual());
                break;

        }
    }

    public void setUnidadeAtual(int unidadeAtual) {
        this.unidadeAtual = unidadeAtual;
    }

    public int getUnidadeAtual() {
        return unidadeAtual;
    }

    public void pararAudio() {
        mCC.pararAudio();
    }

    public void desenharLetra(String letra) {
        System.out.println("LEtra " + letra);
        //imgMaiusculaForma = null;
        imgMaiusculaForma = getClass().getResource("imagens/ABCimg/maiuscula/" + letra + ".png");
        imgMinusculaForma = getClass().getResource("imagens/ABCimg/minuscula/" + letra + ".png");
        imgMaiusculaCursiva = getClass().getResource("imagens/ABCimg/maiuscula/cursiva/" + letra + ".png");
        imgMinusculaCursiva = getClass().getResource("imagens/ABCimg/minuscula/cursiva/" + letra + ".png");
        //System.out.println(imgMaiusculaForma.getPath());
        try {
            img1.setImage(new Image(imgMaiusculaForma.toString()));
            img2.setImage(new Image(imgMinusculaForma.toString()));
            img3.setImage(new Image(imgMaiusculaCursiva.toString()));
            img4.setImage(new Image(imgMinusculaCursiva.toString()));
        }catch(Exception e){
            
        }

    }

}
