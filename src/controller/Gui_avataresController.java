package controller;


import model.Model_avatares;
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
public class Gui_avataresController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage window;

    private String selecionado;

    private Model_avatares modelAvatares = new Model_avatares();
    
    @FXML
    private Button avancar;
    
    //@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        avancar.setDisable(true);
    }
    
    /**
     * Evento disparado quando o avatar que representa o menino é selecionado
     * @param event botao menino
     */
    @FXML
    private void avatarMeninoSelecionado(ActionEvent event) {
        modelAvatares.setSelecionado("menino");
        avancar.setDisable(false);

    }
    /**
     * Evento disparado quando o avatar que representa a menina é selecionado
     * @param event botão menina
     */
    @FXML
    private void avatarMeninaSelecinado(ActionEvent event) {
        modelAvatares.setSelecionado("menina");
        avancar.setDisable(false);
    }
    /**
     * Evento disparado quando o botão avançar é selecionado
     * @param event botão avançar
     * @throws IOException 
     */
    @FXML
    private void handleAvancar(ActionEvent event) throws IOException {
        modelAvatares.getCenaInicial(event);
        ///modelAvatares.getGuiJogoPrincipal(event);
    }

}
