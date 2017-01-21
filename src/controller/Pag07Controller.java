package controller;

import java.io.IOException;
import model.ModelPag07;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class Pag07Controller implements Initializable {
    @FXML
    private Label tituloUnidade;
    
    private ModelPag07 modelPag07;

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
    
    public void setUnidadeAtual(String unidadeAtual) {
        modelPag07.setUnidadeAtual(unidadeAtual, tituloUnidade);
    }

    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag07.pararAudio();
        modelPag07.proximaPagina(event);
    }
    
    
    @FXML
    private void menuInicial(ActionEvent event) throws IOException{
        modelPag07.menuInicial(event);
    }
}
