package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import model.ModelPag04;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
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
    private ListView<String> listaPalavras;
    ObservableList<String> items = FXCollections.observableArrayList();
    private ControllerClasseComum controlerComum;
    @FXML
    private Button som;
    @FXML
    private Button abc;
    @FXML
    private Button manual;
    @FXML
    private Button avancar;
    @FXML
    private Button voltar;
    @FXML
    private Button menuInicial;
    @FXML
    private Label palavrasEstudadas;
    
    private final int pagina = 4;
    
    public Pag04Controller() {
        listaPalavras = new ListView<String>();
        controlerComum = new ControllerClasseComum(listaPalavras);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag04 = new ModelPag04(p1, p2, p3, p4, p5, f1, f2, espaco);
    }

    public void setUnidadeAtual(String unidadeAtual) throws IOException {
        atualizarListaPalavras();
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
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
        orgTranslateX = ((Label) (event.getSource())).getTranslateX();
        orgTranslateY = ((Label) (event.getSource())).getTranslateY();

    }

    @FXML
    private void mouseArrastado(MouseEvent event) {
        double offsetX = event.getSceneX() - orgSceneX;
        double offsetY = event.getSceneY() - orgSceneY;
        newTranslateX = orgTranslateX + offsetX;
        newTranslateY = orgTranslateY + offsetY;
        ((Label) (event.getSource())).setTranslateX(newTranslateX);
        ((Label) (event.getSource())).setTranslateY(newTranslateY);
    }

    @FXML
    private void mouseLiberado(MouseEvent event) {
        if ((verificarColisao(event))) {
            //se for a opcao correta
            if (modelPag04.verificarEscolhaSilaba(event)) {
                modelPag04.alterarLabelEspaco(event);
                modelPag04.executarPalavra();

            } else {
                ((Label) (event.getSource())).setTranslateX(orgTranslateX);
                ((Label) (event.getSource())).setTranslateY(orgTranslateY);
                //modelPag04.tocarAudioAcerto(false);
            }

        } else {
            ((Label) (event.getSource())).setTranslateX(orgTranslateX);
            ((Label) (event.getSource())).setTranslateY(orgTranslateY);
        }
    }
    /**
     * Veriifica se a label solta é a label correta que deveria ter sido arrastada
     * @param evento o botão do mouse é solto
     * @return true ou false
     */
    private boolean verificarColisao(MouseEvent evento) {
        return ((Label) (evento.getSource())).getBoundsInParent().intersects(espaco.getBoundsInParent());
    }

    public void tocarAudio() {
        modelPag04.tocarAudio();
    }

    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelPag04.menuInicial(event);
        modelPag04.pararAudio();
    }

    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag04.tocarAudioPalavraSelecionada(palavraSelecionada);
    }
    
    private void atualizarListaPalavras() throws FileNotFoundException, IOException {
       controlerComum.atualizarListaPalavras(listaPalavras);
    }
    
    @FXML
    private void sombrearBotao(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        ((Button)((event)).getSource()).setEffect(sombras);
    }
    
    @FXML
    private void retirarSombraBotao(MouseEvent event) {        
        ((Button)((event)).getSource()).setEffect(null);
    }
    
    @FXML
    private void abrirManual(ActionEvent event) throws IOException {
        modelPag04.abrirManual(event, pagina);
    }
    
    @FXML
    private void abc(ActionEvent event) throws IOException {
        modelPag04.ABCJanela(event);
        modelPag04.pararAudio();
    }

}
