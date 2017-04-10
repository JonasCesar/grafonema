package controller;

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
    @FXML
    private ImageView imagemFundo;

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
        modelSeqCenas = new Model_SequenciaCenas(imgView,imagemFundo);
        //imagemFundo.();
        imgView.toFront();

    }

    /**
     * Inicia as cenas
     */
    public void iniciarCena() {
        modelSeqCenas.iniciarCenas();
    }

    public void executarCenaInicial() {
        modelSeqCenas.executarCenaInicial();
    }

    /**
     * Define a fase atual do jogo
     *
     * @param faseAtual fase atualizada
     */
    public void setFaseAtual(int faseAtual) {
        modelSeqCenas.setFaseAtual(faseAtual);
    }

    /**
     * Esse método apenas chama uma imagem
     */
    public void executarCenaFimFase() {
        modelSeqCenas.executarCenaFimDaFase();
    }

    public void executarCenaMeioFase() {
        modelSeqCenas.executarCenaMeioFase();
    }

    public void executarCenaIntermediariaFase() {
        modelSeqCenas.executarCenaIntermediaria();

    }

    public void executarCenaInicioJogo() {
        ////throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getFaseAtual() {
        return modelSeqCenas.getFaseAtual();
    }

    public void definirImagemFundo() {
        modelSeqCenas.definirImagemFundo();
    }

}
