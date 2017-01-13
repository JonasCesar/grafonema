/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import model.ModelPag05;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class Pag05Controller implements Initializable {
    @FXML
    private Label p1;
    @FXML
    private Label p4;
    @FXML
    private Label p3;
    @FXML
    private Label p5;
    @FXML
    private Label p2;

    private ModelPag05 modelPag05;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag05 = new ModelPag05();
    }    

    public void setUnidadeAtual(String unidadeAtual) {
        modelPag05.setUnidadeAtual(unidadeAtual);
        switch(unidadeAtual){
            case "u01":
                p1.setText("VA");
                p2.setText("VE");
                p3.setText("VI");
                p4.setText("VO");
                p5.setText("VU");                
                break;
            default:
                break;
        }
    }

    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag05.proximaPagina(event);
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag05.paginaAnterior(event);
    }
    
}
