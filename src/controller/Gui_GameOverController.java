/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import model.Model_gameOver;
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
 * @author iran
 */
public class Gui_GameOverController implements Initializable {

    @FXML
    private Button reiniciar;
    @FXML
    private Button sair;

    private Model_gameOver gameOver = new Model_gameOver();
    
    private Stage window;

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void handleReiniciar(ActionEvent event) throws IOException {
        gameOver.reiniciarJogo(event);
    }
    @FXML
    private void handleSair(ActionEvent event) throws IOException {        
        window = (Stage) ((Button) event.getSource()).getScene().getWindow();        
        window.close();
    }
}
