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
import javafx.scene.effect.DropShadow;
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

    //public ImageView img1;


    private int paginaTemporaria;
    @FXML
    private ImageView img1;
    @FXML
    private Button a;
    @FXML
    private Button b;
    @FXML
    private Button c;
    @FXML
    private Button d;
    @FXML
    private Button j;
    @FXML
    private Button i;
    @FXML
    private Button h;
    @FXML
    private Button g;
    @FXML
    private Button f;
    @FXML
    private Button e;
    @FXML
    private Button n;
    @FXML
    private Button m;
    @FXML
    private Button l;
    @FXML
    private Button k;
    @FXML
    private Button x;
    @FXML
    private Button w;
    @FXML
    private Button v;
    @FXML
    private Button u;
    @FXML
    private Button t;
    @FXML
    private Button s;
    @FXML
    private Button r;
    @FXML
    private Button q;
    @FXML
    private Button p;
    @FXML
    private Button o;
    @FXML
    private Button z;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;

    public ABCController() {
        //modelABC = new ModelABC(imgLetra, img);
        
        paginaTemporaria = 0;
    }

    public String botaoEscolhido = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modelABC = new ModelABC(img1, img2, img3, img4);
    }

    /**
     * Reconhece qual botão (A-Z) foi clicado
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleButton(ActionEvent event) throws IOException {
        String letraEscolhida = ((Button) event.getSource()).getId();
        System.out.println("botão escolhido = " + letraEscolhida);
        //modelABC.desenharLetra(letraEscolhida);
        botaoEscolhido = letraEscolhida;
        desenharLetra(letraEscolhida);
        //modelABC.pararAudio();
    }

    public void setUnidadeAtual(int unidadeAtual) {
        modelABC.setUnidadeAtual(unidadeAtual);
    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        System.out.println(" Temp" + paginaTemporaria);
        modelABC.voltar(event, paginaTemporaria);
    }

    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelABC.menuInicial(event);
    }

    /**
     * Reconhece quando o mouse é passado sobre a imagem da letra
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void passouRato(MouseEvent event) throws IOException {
        DropShadow sombras = new DropShadow();
        ((ImageView) ((event)).getSource()).setEffect(sombras);
        
        /**
        //modelABC.tocarAudio(event);
        modelABC.pararAudio();
        modelABC.tocarAudioUnicoLetras(botaoEscolhido);
        System.out.println("passou no rato");
        * **/
    }
    
    @FXML
    private void dessombrearImagem(MouseEvent event) throws IOException {        
        ((ImageView) ((event)).getSource()).setEffect(null);        
        /**
        //modelABC.tocarAudio(event);
        modelABC.pararAudio();
        modelABC.tocarAudioUnicoLetras(botaoEscolhido);
        System.out.println("passou no rato");
        * **/
    }

    /**
     * Exibe a imagem referente ao botão que foi clicado
     *
     * @param letra letra representado
     */
    public void desenharLetra(String letra) {
        modelABC.desenharLetra(letra);
        
       // img1.setImage(new Image(imgMaiusculaForma.toString()));
        //img2.setImage(new Image(imgMinusculaForma.toString()));
        //img3.setImage(new Image(imgMaiusculaCursiva.toString()));
        //img4.setImage(new Image(imgMinusculaCursiva.toString()));
        //imagemTexto.setImage(new Image(imagemURL.toString()));
    }

    public void setPaginaTemporaria(int pagina) {
        paginaTemporaria = pagina;
    }

    @FXML
    private void tocarLetra(MouseEvent event) {        
        modelABC.tocarSomLetra(botaoEscolhido);
    }
}
