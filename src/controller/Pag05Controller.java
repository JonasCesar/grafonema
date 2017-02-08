package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import model.ModelPag05;
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
    
    private final int pagina = 5;

    public Pag05Controller() {
        listaPalavras = new ListView<String>();
        controlerComum = new ControllerClasseComum(listaPalavras);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag05 = new ModelPag05(p1, p2, p3, p4, p5, f1, f2, espaco);
    }

    public void setUnidadeAtual(String unidadeAtual) throws IOException {
        atualizarListaPalavras();
        modelPag05.setUnidadeAtual(unidadeAtual);
        switch (unidadeAtual) {
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
                modelPag05.executarPalavra();
            } else {
                ((Label) (event.getSource())).setTranslateX(orgTranslateX);
                ((Label) (event.getSource())).setTranslateY(orgTranslateY);
                //modelPag05.tocarAudioAcerto(false);
            }

        } else {
            ((Label) (event.getSource())).setTranslateX(orgTranslateX);
            ((Label) (event.getSource())).setTranslateY(orgTranslateY);
        }
    }

    @FXML
    private void mouseArrastado(MouseEvent event) {
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
        boolean colidiu = (((Label) (evento.getSource())).getBoundsInParent().intersects(espaco.getBoundsInParent()));
        return colidiu;
    }

    public void tocarAudio() {
        modelPag05.tocarAudio();
    }

    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelPag05.menuInicial(event);
        modelPag05.pararAudio();
    }

    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag05.tocarAudioPalavraSelecionada(palavraSelecionada);
    }

    private void atualizarListaPalavras() throws FileNotFoundException, IOException {
        controlerComum.atualizarListaPalavras(listaPalavras);

    }

    @FXML
    private void sombrearBotao(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        ((Button) ((event)).getSource()).setEffect(sombras);
    }

    @FXML
    private void retirarSombraBotao(MouseEvent event) {
        ((Button) ((event)).getSource()).setEffect(null);
    }

    @FXML
    private void abrirABC(ActionEvent event) throws IOException {
        modelPag05.abrirABC(event, pagina);
        modelPag05.pararAudio();
    }
}
