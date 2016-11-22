package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author jonas
 */
public class Gui_SequenciaCenasController implements Initializable {
    @FXML
    
    private ImageView imgView;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Image img = new Image("/controller/vinho.jpg");        
        imgView.setImage(img);
    }    
    
}
