package controller;

import java.io.FileNotFoundException;
import model.ModelPag01;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class Pag01Controller implements Initializable {

    private String unidadeAtual;

    private ModelPag01 modelPag01;
    @FXML
    private Label tituloUnidade;
    @FXML
    private ListView<String> listaPalavras;
    ObservableList<String> items = FXCollections.observableArrayList();
    private ControllerClasseComum controlerComum;
    @FXML
    private Button som;
    @FXML
    private Button abc;
    @FXML
    private Button manual;
    @FXML
    private Button avancar;
    public Pag01Controller() {
        unidadeAtual = "u00";
        modelPag01 = new ModelPag01();
        listaPalavras = new ListView<String>();
        controlerComum = new ControllerClasseComum(listaPalavras);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }

    public void tocarAudio() {
        modelPag01.tocarAudio();
    }

    public String getUnidadeAtual() {
        return modelPag01.getUnidadeAtual();
    }

    public void setUnidadeAtual(String unidade) throws FileNotFoundException, IOException {
        atualizarListaPalavras();
        modelPag01.setUnidadeAtual(unidade, tituloUnidade);
    }

    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag01.pararAudio();
        modelPag01.proximaPagina(event);
    }

    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelPag01.menuInicial(event);
        modelPag01.pararAudio();
    }

    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag01.tocarAudioPalavraSelecionada(palavraSelecionada);
    }

    private void atualizarListaPalavras() throws FileNotFoundException, IOException {
        controlerComum.atualizarListaPalavras(listaPalavras);
    }

    @FXML
    private void sombrearBotao(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        ((Button)((event)).getSource()).setEffect(sombras);
    }
    
    @FXML
    private void retirarSombraBotao(MouseEvent event) {        
        ((Button)((event)).getSource()).setEffect(null);
    }
    
    

}
