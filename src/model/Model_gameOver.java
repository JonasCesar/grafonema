/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import javafx.event.ActionEvent;

/**
 *
 * @author valdir
 */
public class Model_gameOver {
    private Model_Inicial modelInicial = new Model_Inicial();
    
    public Model_gameOver() {
    }

    public void reiniciarJogo(ActionEvent event) throws IOException {
        modelInicial.iniciar(event);
    }
    
}
