package controller;

import java.io.IOException;
import model.ModelPag05;
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
public class Pag05Controller implements Initializable {
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

    private ModelPag05 modelPag05;
    @FXML
    private Label espaco;
    @FXML
    private Label f1;
    @FXML
    private Label f2;
    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double newTranslateX;
    private double orgTranslateY;
    private double newTranslateY;
    @FXML
    private ListView<?> listaPalavras;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag05 = new ModelPag05(p1, p2, p3, p4, p5, f1, f2,espaco);
    }    

    public void setUnidadeAtual(String unidadeAtual) {
        modelPag05.setUnidadeAtual(unidadeAtual);
        switch(unidadeAtual){
            case "u01":
                p1.setText("VA");
                p2.setText("VE");
                p3.setText("VI");
                p4.setText("VO");
                p5.setText("VU");                
                break;
            default:
                break;
        }
    }

    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag05.pararAudio();
        modelPag05.proximaPagina(event);
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag05.pararAudio();
        modelPag05.paginaAnterior(event);
    }

    @FXML
    private void mouseLiberado(MouseEvent event) {
         if ((verificarColisao(event))) {
            //se for a opcao correta
            if (modelPag05.verificarEscolhaSilaba(event)) {
                modelPag05.alterarLabelEspaco(event);
                ((Label) (event.getSource())).setTranslateX(orgTranslateX);
                ((Label) (event.getSource())).setTranslateY(orgTranslateY);
            } else {
                ((Label) (event.getSource())).setTranslateX(orgTranslateX);
                ((Label) (event.getSource())).setTranslateY(orgTranslateY);
            }

        } else {
            ((Label) (event.getSource())).setTranslateX(orgTranslateX);
            ((Label) (event.getSource())).setTranslateY(orgTranslateY);
        }
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
    private void mousePressionado(MouseEvent event) {
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
        orgTranslateX = ((Label) (event.getSource())).getTranslateX();
        orgTranslateY = ((Label) (event.getSource())).getTranslateY();
    }

    private boolean verificarColisao(MouseEvent evento) {
        if (((Label) (evento.getSource())).getBoundsInParent().intersects(espaco.getBoundsInParent())) {
            return true;
        } else {
            
        }
        return false;       
    }

    public void tocarAudio() {
        modelPag05.tocarAudio();
    }
    
    @FXML
    private void menuInicial(ActionEvent event) throws IOException{
        modelPag05.menuInicial(event);
    }

    @FXML
    private void mouseClicado(MouseEvent event) {
    }
    
}
