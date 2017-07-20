/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import model.Model_Creditos;

/**
 * FXML Controller class
 *
 * @author jonas
 */
public class Gui_Creditos implements Initializable {
    @FXML
    private ImageView imagemCreditos;
    @FXML
    private Button imagemAnterior;
    @FXML
    private Button proximaImagem;
    
    private Model_Creditos modelCreditos;

    public Gui_Creditos() {
        
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelCreditos = new Model_Creditos(imagemCreditos,proximaImagem, imagemAnterior);
    }    

    @FXML
    private void imagemAnterior(ActionEvent event) {
        modelCreditos.imagemAnterior(event);
    }

    @FXML
    private void proximaImagem(ActionEvent event) {
        modelCreditos.proximaImagem(event);
    }
    
}
