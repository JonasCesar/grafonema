/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 *
 * @author iran
 */
public class ModelCreditos {
    
    private ModelClasseComum mCC;
    private Stage janela;
    @FXML
    private ListView<String> listaPalavras;
    public ModelCreditos() {       
        mCC = new ModelClasseComum(janela, listaPalavras);
    }   

    public void menuInicial(ActionEvent event) throws IOException {
        mCC.menuInicial(event);
    }

    public void pararAudio() {
        mCC.pararAudio();
    }
    
}
