package controller;

import java.io.IOException;
import model.ModelPag04;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class Pag04Controller implements Initializable {

    private ModelPag04 modelPag04;
    @FXML
    private Label p1;
    @FXML
    private Label p4;
    @FXML
    private Label p3;
    @FXML
    private Label p5;
    @FXML
    private Label p2;
    
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    @FXML
    private Label f2;
    @FXML
    private Label f1;
    private double newTranslateX;
    private double newTranslateY;
    @FXML
    private Label espaco;
    @FXML
    private ListView<?> listaPalavras;

    public Pag04Controller() {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag04 = new ModelPag04(p1, p2, p3, p4, p5, f1, f2,espaco);
    }

    public void setUnidadeAtual(String unidadeAtual) {
        modelPag04.setUnidadeAtual(unidadeAtual);
        switch (unidadeAtual) {
            case "u01":

                break;
            default:
                break;
        }
    }

    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag04.pararAudio();
        modelPag04.proximaPagina(event);
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag04.pararAudio();
        modelPag04.paginaAnterior(event);
    }

    @FXML
    private void mousePressionado(MouseEvent event) {
        System.out.println("Mouse clicado");

        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
        orgTranslateX = ((Label) (event.getSource())).getTranslateX();
        orgTranslateY = ((Label) (event.getSource())).getTranslateY();

    }

    @FXML
    private void mouseArrastado(MouseEvent event) {
        //System.out.println("Mouse arrastado");
        double offsetX = event.getSceneX() - orgSceneX;
        double offsetY = event.getSceneY() - orgSceneY;
        newTranslateX = orgTranslateX + offsetX;
        newTranslateY = orgTranslateY + offsetY;

        ((Label) (event.getSource())).setTranslateX(newTranslateX);
        ((Label) (event.getSource())).setTranslateY(newTranslateY);
        verificarColisao(event);
    }

    @FXML
    private void mouseLiberado(MouseEvent event) {
        if ((verificarColisao(event))) {
            //se for a opcao correta
            if (modelPag04.verificarEscolhaSilaba(event)) {
                modelPag04.alterarLabelEspaco(event);
                
            } else {
                ((Label) (event.getSource())).setTranslateX(orgTranslateX);
                ((Label) (event.getSource())).setTranslateY(orgTranslateY);
            }

        } else {
            ((Label) (event.getSource())).setTranslateX(orgTranslateX);
            ((Label) (event.getSource())).setTranslateY(orgTranslateY);
        }
    }

    private boolean verificarColisao(MouseEvent evento) {
        if (((Label) (evento.getSource())).getBoundsInParent().intersects(espaco.getBoundsInParent())) {
            return true;
        } else {
        }

        return false;
    }

    public void tocarAudio() {
        modelPag04.tocarAudio();        
    }
    
    @FXML
    private void menuInicial(ActionEvent event) throws IOException{
        modelPag04.menuInicial(event);
    }

    @FXML
    private void mouseClicado(MouseEvent event) {
    }

}
