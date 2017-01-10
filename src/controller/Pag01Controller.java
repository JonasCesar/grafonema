package controller;
import model.ModelPag01;
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
public class Pag01Controller implements Initializable {

    private String unidadeAtual;
    
    private String palavraAtual;
   
    private Stage janela;
    
    private ModelPag01 modelPag01 = new ModelPag01();            
    

    public Pag01Controller() {
        unidadeAtual = "u00";
        
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void tocarAudio() {
        modelPag01.tocarAudio();        
    }

    public String getUnidadeAtual() {
        return modelPag01.getUnidadeAtual();
    }

    public void setUnidadeAtual(String unidade) {
        System.out.println(unidade);
        modelPag01.setUnidadeAtual(unidade);
    }

    @FXML
    private void avancar(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag02.fxml"));
        
        
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag02Controller pg02Cont = fxmloader.<Pag02Controller>getController();
        pg02Cont.setUnidadeAtual(getUnidadeAtual());
        
        Scene cena = new Scene(proximaCena, 900, 700);//tamanho
        janela.setTitle("Projeto 2");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface
    }

}
