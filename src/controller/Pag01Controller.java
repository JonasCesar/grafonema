package controller;

import model.ModelPag01;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

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

    public Pag01Controller() {
        unidadeAtual = "u00";
        modelPag01 = new ModelPag01();
        listaPalavras = new ListView<String>();
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
        
        ObservableList<String> items = FXCollections.observableArrayList(
                "VOVÔ", "Double", "Suite", "Family App");
        listaPalavras.setItems(items);
        modelPag01.tocarAudio();
    }

    public String getUnidadeAtual() {
        return modelPag01.getUnidadeAtual();
    }

    public void setUnidadeAtual(String unidade) {
       
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

}
