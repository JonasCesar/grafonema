/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Model_avatares;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        avancar.setDisable(true);
    }
    
    
    @FXML
    private void avatarMeninoSelecionado(ActionEvent event) {
        modelAvatares.setSelecionado("menino");
        avancar.setDisable(false);

    }

    @FXML
    private void avatarMeninaSelecinado(ActionEvent event) {
        modelAvatares.setSelecionado("menina");
        avancar.setDisable(false);
    }

    @FXML
    private void handleAvancar(ActionEvent event) throws IOException {
        modelAvatares.getGuiJogoPrincipal(event);
    }

}
