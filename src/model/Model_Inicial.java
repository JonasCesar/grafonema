package model;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *@author iran
 */
public class Model_Inicial {    
    private Stage window;
    public Model_Inicial() {
    }
    /**
     * Exibe a tela dos avatares
     * 
     * @param event o usuário clica no botão iniciar
     * @throws IOException 
     */
    public void iniciar(ActionEvent event) throws IOException{
        window = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        //cria a próxima cena chamando a inteface dos avatares
        Parent proximaCena = FXMLLoader.load(getClass().getResource("/interfaces/Gui_Introducao.fxml"));
        Scene cena = new Scene(proximaCena, 900, 700);//tamanho
        window.setTitle("Grafonema");//título da cena
        window.setScene(cena);
        window.show();//exibe a interface

    }
}
