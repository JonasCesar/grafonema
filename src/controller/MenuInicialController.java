/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import model.ModelmenuInicial;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class MenuInicialController implements Initializable {
    @FXML
    private Button u01;
    
    private ModelmenuInicial menuInicial;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuInicial = new ModelmenuInicial();
    }    

    @FXML
    private void iniciarUnidade(ActionEvent event) throws IOException {
        String unidade = "";
        unidade = ((Button)(event.getSource())).getId();
        switch(unidade){
            case "u01":
                menuInicial.iniciar(event, unidade);
                break;
            default:
                break;
        }
    }

    public void setUnidadeAtual(String unidadeAtual) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
