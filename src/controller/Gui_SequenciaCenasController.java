package controller;

import java.net.MalformedURLException;
import model.Model_SequenciaCenas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author jonas
 */
public class Gui_SequenciaCenasController implements Initializable {
    @FXML    
    private ImageView imgView;
    private Model_SequenciaCenas modelSeqCenas;
    private int unidadeAtual;

    public Gui_SequenciaCenasController() {
       unidadeAtual = 0;
    }   
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         modelSeqCenas = new Model_SequenciaCenas(imgView);
            
    }
    
    public void iniciarCena() throws MalformedURLException{
        modelSeqCenas.iniciarCenas();
    }
    
    public void executarCenaInicial(){
        modelSeqCenas.executarCenaInicial();
    }

    public void setFaseAtual(int faseAtual) {
        modelSeqCenas.setFaseAtual(faseAtual);
    }
    
    public void executarCenaFimFase() throws MalformedURLException{
        modelSeqCenas.executarCenaFimDaFase();
    }
    
}
