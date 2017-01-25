package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import model.ModelPag07;
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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class Pag07Controller implements Initializable {
    @FXML
    private Label tituloUnidade;
    
    private ModelPag07 modelPag07;
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
    private Button menuInicial;
    public Pag07Controller() {
        listaPalavras = new ListView<String>();
        controlerComum = new ControllerClasseComum(listaPalavras);
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag07 = new ModelPag07();        
    }    
    
    public void tocarAudio() {
        modelPag07.tocarAudio();        
    }
    
     public String getUnidadeAtual() {
        return modelPag07.getUnidadeAtual();
    }
    
    public void setUnidadeAtual(String unidadeAtual) throws IOException {
        atualizarListaPalavras();
        modelPag07.setUnidadeAtual(unidadeAtual, tituloUnidade);
    }

    private void avancar(ActionEvent event) throws IOException {
        modelPag07.pararAudio();
        modelPag07.proximaPagina(event);
    }
    
    private void voltar(ActionEvent event) throws IOException {
        modelPag07.pararAudio();
        modelPag07.paginaAnterior(event);
    }
    
    @FXML
    private void menuInicial(ActionEvent event) throws IOException{
        modelPag07.menuInicial(event);
        modelPag07.pararAudio();
    }

    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag07.tocarAudioPalavraSelecionada(palavraSelecionada);
    }
    
    private void atualizarListaPalavras() throws FileNotFoundException, IOException {
        controlerComum.atualizarListaPalavras(listaPalavras);
    }
}
