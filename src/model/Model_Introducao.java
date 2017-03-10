package model;

import controller.Gui_SequenciaCenasController;
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
public class Model_Introducao {

    private Stage window;

    private String selecionado;
    
    public Model_Introducao() {
        
    }

    /**
     * Reconhece qual avatar foi selecionado pelo jogador
     *
     * @param event
     * @throws IOException
     */
    public void getGuiJogoPrincipal(ActionEvent event) throws IOException {
        //pega a cena em que o botão que disparou o evento estava
        window = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent proximaCena;
        
        proximaCena = FXMLLoader.load(getClass().getResource("/interfaces/Gui_JogoPrincipal.fxml"));
        
        //cria uma cena 
        Scene cena = new Scene(proximaCena, 1200, 700);
        window.setTitle("Grafonema");//título da cena
        window.setScene(cena);
        window.show();//exibe a cena
    }

    /**
     * Define o avatar selecionado
     *
     * @param valor string contendo "menina" ou "menino"
     */
    public void setSelecionado(String valor) {
        this.selecionado = valor;
    }

    /**
     * Retorna o avatar que foi selecionado
     *
     * @return a string correspondente ao avatar: "menino"/"menina"
     */
    public String getSelecionado() {
        return selecionado;
    }

    public void getCenaInicial(ActionEvent event) throws IOException {
        window = (Stage) ((Button) event.getSource()).getScene().getWindow();       
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Gui_SequenciaCenas.fxml"));
        Parent proximaCena = (Parent) fxmloader.load();        
        Gui_SequenciaCenasController sequenciaCenas = fxmloader.<Gui_SequenciaCenasController>getController();
        //cria uma cena 
        Scene cena = new Scene(proximaCena, 1200, 700);
        window.setTitle("Grafonema");//título da cena
        window.setScene(cena);
        window.show();//exibe a cena
        sequenciaCenas.executarCenaInicial();
        
    }
}
