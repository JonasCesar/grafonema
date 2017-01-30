/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author shadows
 */
public class Principal extends Application {
    @FXML
    private Stage janela;
    
    @Override
    public void start(Stage janela) throws Exception {
        this.janela = janela;
        Parent cenaInicial = FXMLLoader.load(getClass().getResource("/interfaces/menuInicial.fxml"));        
        Scene cena = new Scene(cenaInicial, 950,700);        
        janela.setTitle("Projeto 2");
        janela.setScene(cena);
        janela.show();
        
        //função para encerrar todos os processos quando o usuário clicar no "X"
        janela.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {

                Platform.exit();
                System.exit(0);
            }
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
