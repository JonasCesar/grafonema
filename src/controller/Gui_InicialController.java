package controller;

import model.Model_Inicial;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ModelJogoPrincipal;

/**
 * FXML Controller class
 *
 * @author jonas
 */
public class Gui_InicialController implements Initializable {    
    @FXML
    private Button iniciar;
    
    
    private Model_Inicial modelInical = new Model_Inicial();
    private ModelJogoPrincipal modelPrincipal = new ModelJogoPrincipal(iniciar, 
            iniciar, iniciar, iniciar, iniciar, iniciar, null, null, null, iniciar,
            null, null,iniciar, iniciar, iniciar, iniciar, iniciar);    
    
    @FXML
    private ImageView imagemFundo;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image(getClass().getResourceAsStream("play.png"));
        iniciar.setGraphic(new ImageView(image));
        imagemFundo.toBack();
        
    }
    /**
     * Começa o jogo
     * @param event botão de Começar
     * @throws IOException 
     */
    @FXML
    private void handleComecar(ActionEvent event) throws IOException {       
        modelInical.iniciar(event);        
    }

    @FXML
    private void sobreAction(ActionEvent event) throws IOException {
        modelInical.sobre(event);
    }

}
