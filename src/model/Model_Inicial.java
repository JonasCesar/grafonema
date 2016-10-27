/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author iran
 */
public class Model_Inicial {
    
    private Stage window;
    public Model_Inicial() {
    }
    
    public void iniciar(ActionEvent event) throws IOException{
        window = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent cenaPrincipal = FXMLLoader.load(getClass().getResource("/interfaces/Gui_avatares.fxml"));
        Scene scene = new Scene(cenaPrincipal, 900, 700);
        window.setTitle("Grafonema");
        window.setScene(scene);
        window.show();

    }
}
