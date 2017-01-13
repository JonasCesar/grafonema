/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import model.ModelPag06;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.ModelPag05;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class Pag06Controller implements Initializable {
    @FXML
    private Label p1;
    @FXML
    private Label p2;
    @FXML
    private TextField resposta;
    private ModelPag06 modelPag06;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag06 = new ModelPag06();
    }    

    public void setUnidadeAtual(String unidade) {
        System.out.println(unidade);
        modelPag06.setUnidadeAtual(unidade);
        
    }

    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag06.proximaPagina(event);
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag06.paginaAnterior(event);
    }    
}
