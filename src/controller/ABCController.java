/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import model.ModelABC;

/**
 * FXML Controller class
 *
 * @author jonas
 */
public class ABCController implements Initializable {

    private ModelABC modelABC;

    @FXML
    public ImageView imgLetra;

    public Image img;

    public ABCController() {
        //modelABC = new ModelABC(imgLetra, img);
        modelABC = new ModelABC();
    }

    public String botaoEscolhido = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void handleButton(ActionEvent event) throws IOException {
        String letraEscolhida = ((Button) event.getSource()).getText();
        System.out.println("botão escolhido = " + letraEscolhida);
        //modelABC.desenharLetra(letraEscolhida);
        desenharLetra(letraEscolhida);
        modelABC.tocarSomLetra(letraEscolhida);

    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {

    }

    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelABC.menuInicial(event);
    }

    @FXML
    private void passouRato(MouseEvent event) throws IOException {

        //modelABC.tocarAudio(event);
        modelABC.tocarAudioUnicoLetras(botaoEscolhido);
        System.out.println("passou no rato");
    }

    public void desenharLetra(String letra) {
        System.out.println("entrou no desenha letra");
        switch (letra) {

            case "A":
                img = new Image("/ABCimg/aaaa.jpg");
                botaoEscolhido = "A";
                break;
            case "B":
                img = new Image("/ABCimg/bbbb.jpg");
                botaoEscolhido = "B";
                break;
            case "C":
                img = new Image("/ABCimg/cccc.jpg");
                botaoEscolhido = "C";
                break;
            case "D":
                img = new Image("/ABCimg/dddd.jpg");
                botaoEscolhido = "D";
                break;
            case "E":
                img = new Image("/ABCimg/eeee.jpg");
                botaoEscolhido = "E";
                break;
            case "F":
                img = new Image("/ABCimg/ffff.jpg");
                botaoEscolhido = "F";
                break;
            case "G":
                img = new Image("/ABCimg/gggg.jpg");
                botaoEscolhido = "G";
                break;
            case "H":
                img = new Image("/ABCimg/hhhh.jpg");
                botaoEscolhido = "H";
                break;
            case "I":
                img = new Image("/ABCimg/iiii.jpg");
                botaoEscolhido = "I";
                break;
            case "J":
                img = new Image("/ABCimg/jjjj.jpg");
                botaoEscolhido = "J";
                break;
            case "K":
                img = new Image("/ABCimg/kkkk.jpg");
                botaoEscolhido = "K";
                break;
        }

        imgLetra.setImage(img);

    }
}
