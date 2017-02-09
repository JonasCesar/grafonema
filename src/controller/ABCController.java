/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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

    private int paginaTemporaria;

    public ABCController() {
        //modelABC = new ModelABC(imgLetra, img);
        modelABC = new ModelABC();
        paginaTemporaria = 0;
    }

    public String botaoEscolhido = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    /**
     * Reconhece qual botão (A-Z) foi clicado
     * @param event
     * @throws IOException 
     */
    @FXML
    public void handleButton(ActionEvent event) throws IOException {
        String letraEscolhida = ((Button) event.getSource()).getText();
        System.out.println("botão escolhido = " + letraEscolhida);
        //modelABC.desenharLetra(letraEscolhida);
        botaoEscolhido = letraEscolhida;
        desenharLetra(letraEscolhida);
        modelABC.pararAudio();
        modelABC.tocarSomLetra(letraEscolhida);

    }

    public void setUnidadeAtual(String unidadeAtual) {
        modelABC.setUnidadeAtual(unidadeAtual);
    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        System.out.println(" Temp" +paginaTemporaria);
        modelABC.voltar(event, paginaTemporaria);
    }

    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelABC.menuInicial(event);
    }
    /**
     * Reconhece quando o mouse é passado sobre a imagem da letra
     * @param event
     * @throws IOException 
     */
    @FXML
    private void passouRato(MouseEvent event) throws IOException {

        //modelABC.tocarAudio(event);
        modelABC.pararAudio();
        modelABC.tocarAudioUnicoLetras(botaoEscolhido);
        System.out.println("passou no rato");
    }
    /**
     * Exibe a imagem referente ao botão que foi clicado
     * @param letra letra representado
     */
    public void desenharLetra(String letra) {
        System.out.println("entrou no desenha letra");
        switch (letra) {

            case "A":
                img = new Image("/ABCimg/aaaa.jpg");                
                break;
            case "B":
                img = new Image("/ABCimg/bbbb.jpg");
                break;
            case "C":
                img = new Image("/ABCimg/cccc.jpg");
                break;
            case "D":
                img = new Image("/ABCimg/dddd.jpg");                
                break;
            case "E":
                img = new Image("/ABCimg/eeee.jpg");
                break;
            case "F":
                img = new Image("/ABCimg/ffff.jpg");
                break;
            case "G":
                img = new Image("/ABCimg/gggg.jpg");
                break;
            case "H":
                img = new Image("/ABCimg/hhhh.jpg");
                break;
            case "I":
                img = new Image("/ABCimg/iiii.jpg");
                break;
            case "J":
                img = new Image("/ABCimg/jjjj.jpg");
                break;
            case "K":
                img = new Image("/ABCimg/kkkk.jpg");
                break;
        }

        imgLetra.setImage(img);

    }

    public void setPaginaTemporaria(int pagina) {
        paginaTemporaria = pagina;
    }
}
