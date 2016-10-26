/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.JogoPrincipal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author jonas
 */
public class Gui_JogoPrincipalController implements Initializable {
    @FXML
    private Button btn_1;
    @FXML
    private Button btn_2;
    @FXML
    private Button btn_3;
    @FXML
    private Button btn_5;
    @FXML
    private Button btn_4;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
    }    

    @FXML
    private void handlePular(ActionEvent event) {
        JogoPrincipal jogoPrincipal = new JogoPrincipal(btn_1, btn_2, btn_3, btn_4, btn_5);
        jogoPrincipal.gerarNovasOpcoes();        
    }
    
}
