/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ABCController;
import controller.Pag01Controller;
import controller.Pag02Controller;
import controller.Pag03Controller;
import controller.Pag04Controller;
import controller.Pag05Controller;
import controller.Pag06Controller;
import controller.Pag07Controller;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
    private String unidadeAtual;

    @FXML
    public ImageView imgLetra;

    public Image img;

    //String botaoEscolhido = "";
    public ModelABC() {
        mCC = new ModelClasseComum(janela);
        unidadeAtual = "u00";

    }

    public void menuInicial(ActionEvent event) throws IOException {
        mCC.menuInicial(event);
    }

    public void tocarAudio(MouseEvent event) throws IOException {
        mCC.play("src/ABCAudio/resposta_certa.mp3");
    }

    //função responsável por tocar o som das letras
    public void tocarAudioUnicoLetras(String letra) {
        switch (letra) {
            case "A":
                mCC.play("src/ABCAudioUnico/a.mp3");
                break;
            case "B":
                mCC.play("src/ABCAudioUnico/b.mp3");
                break;
//            case "C":
//                mCC.play("src/ABCAudio/resposta_certa.mp3");
//                break;
//            case "D":
//                mCC.play("src/ABCAudio/resposta_certa.mp3");
//                break;
//            case "E":
//                mCC.play("src/ABCAudio/resposta_certa.mp3");
//                break;
//            case "F":
//                mCC.play("src/ABCAudio/resposta_certa.mp3");
//                break;
        }
    }

    //função responsável por fazer um discurso sobre a letra
    public void tocarSomLetra(String letra) {
        switch (letra) {
            case "A":
                mCC.play("src/ABCAudioEPalavras/a.MP3");
                break;
            case "B":
                mCC.play("src/ABCAudioEPalavras/b.MP3");
                break;
//            case "C":
//                mCC.play("src/ABCAudio/resposta_certa.mp3");
//                break;
//            case "D":
//                mCC.play("src/ABCAudio/resposta_certa.mp3");
//                break;
//            case "E":
//                mCC.play("src/ABCAudio/resposta_certa.mp3");
//                break;
//            case "F":
//                mCC.play("src/ABCAudio/resposta_certa.mp3");
//                break;
        }
    }

//    public void desenharLetra(String letra) throws MalformedURLException {
//        System.out.println("entrou no desenha letra");
//        switch (letra) {
//            case "A":
//                System.out.println("entrou no case a");
//                imagemArquivo = new File("src/ABCAudio/aaaa.png");
//                String caminho = imagemArquivo.toURI().toURL().toString();
//                System.out.println("caminho coisado " + caminho);
//                img = new Image(imagemArquivo.toURI().toURL().toString());
//                imgLetra.setImage(img);
//                System.out.println("setou o imgviu");
//                botaoEscolhido = "A";
//
//                break;
//            case "B":
//                img = new Image("src/ABCimg/bbbb.png");
//                imgLetra.setImage(img);
//                botaoEscolhido = "B";
//                break;
//        }
//    }
    public void executarAudio(String letra) {

    }
    
    public void voltar(ActionEvent evento, int pagina) throws IOException{
        mCC.pararAudio();
        janela = (Stage) ((Button) evento.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = null;
        Pag01Controller pag01Cont;
        Pag02Controller pag02Cont;
        Pag03Controller pag03Cont;
        Pag04Controller pag04Cont;
        Pag05Controller pag05Cont;
        Pag06Controller pag06Cont;
        Pag07Controller pag07Cont;

        fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag0" + pagina + ".fxml"));

        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();

        switch (pagina) {
            case 1:
                pag01Cont = fxmloader.<Pag01Controller>getController();
                System.out.println("Aqui 2 abc");
                mCC.exibirCena(proximaCena, janela);
                System.out.println("Aqui 3 abc");
                System.out.println(getUnidadeAtual());
                pag01Cont.setUnidadeAtual(getUnidadeAtual());
                System.out.println("Aqui 4 abc");
                pag01Cont.tocarAudio();
                System.out.println("Aqui 6 abc");
                break;
            case 2:
                pag02Cont = fxmloader.<Pag02Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag02Cont.setUnidadeAtual(getUnidadeAtual());
                break;
            case 3:
                pag03Cont = fxmloader.<Pag03Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag03Cont.setUnidadeAtual(getUnidadeAtual());
                pag03Cont.audioInicial();
                pag03Cont.setImagens(getUnidadeAtual());
                break;
            case 4:
                pag04Cont = fxmloader.<Pag04Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag04Cont.setUnidadeAtual(getUnidadeAtual());
                pag04Cont.tocarAudio();
                break;
            case 5:
                pag05Cont = fxmloader.<Pag05Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag05Cont.setUnidadeAtual(getUnidadeAtual());
                pag05Cont.tocarAudio();
                break;
            case 6:
                pag06Cont = fxmloader.<Pag06Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag06Cont.setUnidadeAtual(getUnidadeAtual());
                pag06Cont.tocarAudio();
                break;
            case 7:
                pag07Cont = fxmloader.<Pag07Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag07Cont.setUnidadeAtual(getUnidadeAtual());
                break;
                
        }
    }

    public void setUnidadeAtual(String unidadeAtual) {
        this.unidadeAtual = unidadeAtual;
    }

    public String getUnidadeAtual() {
        return unidadeAtual;
    }

    public void pararAudio() {
        mCC.pararAudio();
    }

}
