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
    
    /**
     * Reconhece qual avatar foi selecionado pelo jogador
     * 
     * @param event
     * @throws IOException 
     */
    public void getGuiJogoPrincipal(ActionEvent event) throws IOException{
        //pega a cena em que o botão que disparou o evento estava
        window = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent proximaCena;
        if (getSelecionado().equals("menino")) {//verifica se o avatar selecionado é menino
            proximaCena = FXMLLoader.load(getClass().getResource("/interfaces/Gui_JogoPrincipal.fxml"));
        } else {//caso contrário é menina
            proximaCena = FXMLLoader.load(getClass().getResource("/interfaces/Gui_JogoPrincipal.fxml"));
        }
        //cria uma cena 
        Scene cena = new Scene(proximaCena, 900, 700);
        window.setTitle("Grafonema");//título da cena
        window.setScene(cena);
        window.show();//exibe a cena
    }
    /**
     * Define o avatar selecionado
     * @param valor string contendo "menina" ou "menino"
     */
    public void setSelecionado(String valor){
        this.selecionado = valor;
    }
    /**
     * Retorna o avatar que foi selecionado
     * @return a string correspondente ao avatar: "menino"/"menina"
     */
    public String getSelecionado(){
        return selecionado;
    }    
}
