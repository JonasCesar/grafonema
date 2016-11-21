/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafonema;
import java.io.IOException;
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
 * @author jonas
 */
public class Main extends Application {
    @FXML
    private Stage janela;
    
    @Override
    public void start(Stage janela) throws IOException {
        this.janela = janela;
        Parent cenaInicial = FXMLLoader.load(getClass().getResource("/interfaces/Gui_Inicial.fxml"));        
        Scene scene = new Scene(cenaInicial, 900, 700);        
        janela.setTitle("Grafonema");
        janela.setScene(scene);
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
    
    @FXML
    public Stage stageMain(){
        return janela;
    }
    
}
