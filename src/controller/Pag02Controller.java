/**
 * Controller da pagina 02
 */
package controller;

import java.io.FileNotFoundException;
import model.ModelPag02;
import java.io.IOException;
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
public class Pag02Controller implements Initializable {

    @FXML
    private Label palavraAtual;

    private ModelPag02 modelPag02 = new ModelPag02();

    private String unidadeAtual;
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
    private Button audio1;
    @FXML
    private Button audio2;
    @FXML
    private Button audio3;
    @FXML
    private Button voltar;
    @FXML
    private Button menuInicial;
    @FXML
    private Label palavrasEstudadas;
    @FXML
    private Label tituloUnidade;

    private final int pagina = 2;

    public Pag02Controller() {
        unidadeAtual = "u00";
        listaPalavras = new ListView<String>();
        controlerComum = new ControllerClasseComum(listaPalavras);
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

    /**
     * Avança para a proxima pagina
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag02.pararAudio();
        modelPag02.proximaPagina(event);
    }

    /**
     * Executa o audio 1
     *
     * @param event clique no botão do audio 1
     */
    @FXML
    private void audio1(ActionEvent event) {
        modelPag02.pararAudio();
        modelPag02.tocarAudio1();
    }

    /**
     * Executa o audio 2
     *
     * @param event clique no botão do audio 2
     */
    @FXML
    private void audio2(ActionEvent event) {
        modelPag02.pararAudio();
        modelPag02.tocarAudio2();
    }

    /**
     * Executa o audio 3
     *
     * @param event clique no botão do audio 3
     */
    @FXML
    private void audio3(ActionEvent event) {
        modelPag02.pararAudio();
        modelPag02.tocarAudio3();
    }

    /**
     * Volta para a pagina anterior
     *
     * @param event
     * @throws IOException
     */

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag02.pararAudio();
        modelPag02.paginaAnterior(event);
    }

    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelPag02.menuInicial(event);
        modelPag02.pararAudio();
    }

    public String getUnidadeAtual() {
        return modelPag02.getUnidadeAtual();
    }

    public void setUnidadeAtual(String unidade) throws IOException {
        atualizarListaPalavras();
        switch (unidade) {
            case "u01":
                palavraAtual.setText("VOVÔ");
                break;
            default:
                break;
        }
        modelPag02.setUnidadeAtual(unidade);
    }

    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag02.tocarAudioPalavraSelecionada(palavraSelecionada);
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
    private void abrirManual(ActionEvent event) throws IOException {
        modelPag02.abrirManual(event, pagina);
    }

    @FXML
    private void abrirABC(ActionEvent event) throws IOException {
        modelPag02.abrirABC(event, pagina);
        modelPag02.pararAudio();
    }
}
