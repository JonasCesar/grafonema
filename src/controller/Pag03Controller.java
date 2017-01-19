package controller;

import java.io.IOException;
import java.net.MalformedURLException;
import model.ModelPag03;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class Pag03Controller implements Initializable {

    
    
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
    
    private ModelPag03 modelPag03;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag03 = new ModelPag03(img1,img2,img3,img4,img5,img6);
    }    
    public void audioInicial(){
        modelPag03.tocarAudioInicial();
    }

    public void setUnidadeAtual(String unidade) {
        modelPag03.setUnidadeAtual(unidade);
    }

    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag03.proximaPagina(event);        
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag03.paginaAnterior(event);
    }

    public void setImagens(String unidadeAtual) throws MalformedURLException {
        modelPag03.definirImagens(unidadeAtual);
    }

    @FXML
    private void verificarImagem(MouseEvent event) {
        modelPag03.verificarImagem(event);
    }
    
}
