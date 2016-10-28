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
public class Model_avatares {
    
    private Stage window;
    
    private String selecionado;
    
    public Model_avatares() {
               
    }
    
    //
    public void getGuiJogoPrincipal(ActionEvent event) throws IOException{
        window = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent cenaPrincipal;
        if (getSelecionado().equals("menino")) {
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
    
    public void setSelecionado(String valor){
        this.selecionado = valor;
    }    
    public String getSelecionado(){
        return selecionado;
    }
    
    
}
