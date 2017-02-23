package controller;

import model.Model_Introducao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class Gui_Introducao implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage window;

    private String selecionado;

    private Model_Introducao modelAvatares = new Model_Introducao();
    
    @FXML
    private Button avancar;
    
    //@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Evento disparado quando o botão avançar é selecionado
     * @param event botão avançar
     * @throws IOException 
     */
    @FXML
    private void handleAvancar(ActionEvent event) throws IOException {
        modelAvatares.getGuiJogoPrincipal(event);
    }

}
