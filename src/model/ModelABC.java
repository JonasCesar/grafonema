/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ABCController;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    public ImageView imgLetra;

    public Image img;

    //String botaoEscolhido = "";
    public ModelABC() {
        mCC = new ModelClasseComum(janela);

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

    public void pararAudio() {
        mCC.pararAudio();
    }

}
