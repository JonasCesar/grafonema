/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class Gui_avataresController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage window;

    private String selecionado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void avatarMeninoSelecionado(ActionEvent event) {
        selecionado = "menino";
    }

    @FXML
    private void avatarMeninaSelecinado(ActionEvent event) {
        selecionado = "menina";
    }

    @FXML
    private void handleAvancar(ActionEvent event) throws IOException {
        window = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent cenaPrincipal;
        if (selecionado.equals("menino")) {
            System.out.println("Menino Selecionado");
            cenaPrincipal = FXMLLoader.load(getClass().getResource("/interfaces/Gui_JogoPrincipal.fxml"));
        } else {
            System.out.println("Menina selecionada");
            cenaPrincipal = FXMLLoader.load(getClass().getResource("/interfaces/Gui_JogoPrincipal.fxml"));
        }

        Scene scene = new Scene(cenaPrincipal, 900, 700);
        window.setTitle("Grafonema");
        window.setScene(scene);
        window.show();
    }

}
