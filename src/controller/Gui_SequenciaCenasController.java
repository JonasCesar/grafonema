package controller;

import model.Model_SequenciaCenas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author jonas
 */
public class Gui_SequenciaCenasController implements Initializable {

    @FXML
    private ImageView imgView;
    private Model_SequenciaCenas modelSequenciaCenas;
    private int unidadeAtual;
    private ImageView imagemFundo;
    @FXML
    private Button skipIntro;

    public Gui_SequenciaCenasController() {
        unidadeAtual = 0;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelSequenciaCenas = new Model_SequenciaCenas(imgView,imagemFundo,skipIntro);        
        //imgView.toFront();

    }

    /**
     * Inicia as cenas
     */
    public void iniciarCena() {
        modelSequenciaCenas.iniciarCenas();
    }

    public void executarCenaInicialFase() {
        modelSequenciaCenas.executarCenaInicialFase();
    }

    /**
     * Define a fase atual do jogo
     *
     * @param faseAtual fase atualizada
     */
    public void setFaseAtual(int faseAtual) {
        modelSequenciaCenas.setFaseAtual(faseAtual);
    }

    /**
     * Esse método apenas chama uma imagem
     */
    public void executarCenaFimFase() {
        modelSequenciaCenas.executarCenaFimDaFase();
    }

    public void executarCenaMeioFase() {
        modelSequenciaCenas.executarCenaMeioFase();
    }

    public void executarCenaIntermediariaFase() {
        modelSequenciaCenas.executarCenaIntermediaria();

    }

    public void executarCenaInicioJogo() {
        ////throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getFaseAtual() {
        return modelSequenciaCenas.getFaseAtual();
    }

    public void definirImagemFundo() {
        modelSequenciaCenas.definirImagemFundo();
    }
    
    public void executarCenaInicial(){
        modelSequenciaCenas.executarCenaInicial();
    }

    public Double getTempoFase(int fase) {
        return modelSequenciaCenas.getTempoFase(fase);
    }

    @FXML
    private void handlePular(ActionEvent event) {        
        modelSequenciaCenas.chamarCenaPulo();
        
    }

}
