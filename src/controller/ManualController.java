/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import model.ModelManual;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class ManualController implements Initializable {
    
    private ModelManual modelManual;
    private String unidadeAtual;
    private ActionEvent evento;
    private int paginaTemporaria;

    public ManualController() {
        modelManual = new ModelManual();
        evento = null;
        paginaTemporaria = 0;
    }

    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retirarSombraBotao(MouseEvent event) {
        ((Button)((event)).getSource()).setEffect(null);
    }

    @FXML
    private void sombrearBotao(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        ((Button)((event)).getSource()).setEffect(sombras);
    }

    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelManual.menuInicial(event);
        modelManual.pararAudio();
    }

    public void setUnidadeAtual(String unidadeAtual) {
        modelManual.setUnidadeAtual(unidadeAtual);
    }
    
    public String getUnidadeAtual(){
        return modelManual.getUnidadeAtual();
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelManual.voltar(event, paginaTemporaria);
    }

    public void setPaginaTemporaria(int pagina) {
        paginaTemporaria = pagina;
    }
}
