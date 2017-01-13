package controller;
import model.ModelPag01;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    

    public Pag01Controller() {
        unidadeAtual = "u00";
        modelPag01 = new ModelPag01();
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

    public void setUnidadeAtual(String unidade) {
        System.out.println(unidade);
        modelPag01.setUnidadeAtual(unidade, tituloUnidade);
    }

    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag01.proximaPagina(event);        
        
    }

}
