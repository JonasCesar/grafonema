/**
 * Controller da pagina 02
 */
package controller;

import java.io.FileNotFoundException;
import model.ModelPag02a;
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
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class Pag02aController implements Initializable {

    @FXML
    private Label palavraAtual;

    private ModelPag02a modelPag02a;

    private String unidadeAtual;
    @FXML
    private ListView<String> listaPalavras;

    ObservableList<String> items = FXCollections.observableArrayList();

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
    private Label palavrasEstudadas;

    private final int pagina = 2;
    @FXML
    private Button atividades;
    @FXML
    private ImageView imgAudio1;

    public Pag02aController() {
        unidadeAtual = "u00";
        listaPalavras = new ListView<>();

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag02a = new ModelPag02a(listaPalavras, palavraAtual);
        Tooltip ouvirPalavras = new Tooltip("Clique em uma palavra para ouvir");
        listaPalavras.setTooltip(ouvirPalavras);
        abc.setTooltip(new Tooltip("Clique para ouvir os sons das letras"));
        atividades.setTooltip(new Tooltip("Clique para ver as atividades para imprimir"));
        manual.setTooltip(new Tooltip("Clique para ler o manual do programa "));
        audio1.setTooltip(new Tooltip("Clique para ouvir o áudio 1"));
        audio2.setTooltip(new Tooltip("Clique para ouvir o áudio 2"));
        audio3.setTooltip(new Tooltip("Clique para ouvir o áudio 3"));
    }

    /**
     * Avança para a proxima pagina
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag02a.pararAudio();
        modelPag02a.proximaPagina(event);
    }

    /**
     * Executa o audio 1
     *
     * @param event clique no botão do audio 1
     */
    @FXML
    private void audio1(ActionEvent event) {
        String verde = "-fx-background-color: \n"
                + "        linear-gradient(#97ff5b, #54e600),\n"
                + "        linear-gradient(#b1ff83, #83f143),\n"
                + "        linear-gradient(#a0ff69, #6def22),\n"
                + "        linear-gradient(#57ff6a 0%, #02f80e 50%, #12ee0a 100%),\n"
                + "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));";
        modelPag02a.pararAudio();
        modelPag02a.tocarAudio1();
        ((Button) event.getSource()).setStyle(verde);
    }

    /**
     * Executa o audio 2
     *
     * @param event clique no botão do audio 2
     */
    @FXML
    private void audio2(ActionEvent event) {
        String verde = "-fx-background-color: \n"
                + "        linear-gradient(#97ff5b, #54e600),\n"
                + "        linear-gradient(#b1ff83, #83f143),\n"
                + "        linear-gradient(#a0ff69, #6def22),\n"
                + "        linear-gradient(#57ff6a 0%, #02f80e 50%, #12ee0a 100%),\n"
                + "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));";
        modelPag02a.pararAudio();
        modelPag02a.tocarAudio2();
        ((Button) event.getSource()).setStyle(verde);
    }

    /**
     * Executa o audio 3
     *
     * @param event clique no botão do audio 3
     */
    @FXML
    private void audio3(ActionEvent event) {
        String verde = "-fx-background-color: \n"
                + "        linear-gradient(#97ff5b, #54e600),\n"
                + "        linear-gradient(#b1ff83, #83f143),\n"
                + "        linear-gradient(#a0ff69, #6def22),\n"
                + "        linear-gradient(#57ff6a 0%, #02f80e 50%, #12ee0a 100%),\n"
                + "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));";
        modelPag02a.pararAudio();
        modelPag02a.tocarAudio3();
        ((Button) event.getSource()).setStyle(verde);
    }

    /**
     * Volta para a pagina anterior
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag02a.pararAudio();
        modelPag02a.paginaAnterior(event);
    }

    /**
     * Leva o usuario para o menu inicial
     *
     * @param event clique no botao "Menu Inicial"
     * @throws IOException
     */
    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelPag02a.menuInicial(event);
        modelPag02a.pararAudio();
    }

    /**
     * Pega a unidade atual
     *
     * @return string com o valor da unidade atual
     */
    public int getUnidadeAtual() {
        return modelPag02a.getUnidadeAtual();
    }

    /**
     * Define a unidade atual
     *
     * @param unidade valor da unidade atual
     * @throws IOException
     */
    public void setUnidadeAtual(int unidade) throws IOException {
        atualizarListView();        
        modelPag02a.setUnidadeAtual(unidade);
    }

    /**
     * Trata o evento de quando o mouse é clicado na lista de palavras
     *
     * @param event mouse é pressionado
     */
    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag02a.tocarAudioPalavraSelecionada(palavraSelecionada);
    }

    /**
     * Atualiza a lista de palavras estudadas
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void atualizarListaPalavras() throws FileNotFoundException, IOException {
        //controlerComum.atualizarListaPalavras(listaPalavras);
    }

    /**
     * Realiza o efeito de sombrear o botão quando o mouse passar por cima dele
     *
     * @param event movimentação do mouse sobre os componentes
     */
    @FXML
    private void sombrearBotao(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        ((Button) ((event)).getSource()).setEffect(sombras);
    }

    /**
     * Realiza o efeito de dessombrear o botão quando o mouse for retirado de
     * cima dele
     *
     * @param event movimentação do mouse para fora do botão
     */
    @FXML
    private void retirarSombraBotao(MouseEvent event) {
        ((Button) ((event)).getSource()).setEffect(null);
    }

    /**
     * Abre o manual do software
     *
     * @param event clique no botão
     * @throws IOException
     */
    @FXML
    private void abrirManual(ActionEvent event) throws IOException {
        modelPag02a.abrirManual(event, pagina,"a");
    }

    /**
     * Abre a função ABC do software
     *
     * @param event clique no botão "ABC"
     * @throws IOException
     */
    @FXML
    private void abrirABC(ActionEvent event) throws IOException {
        modelPag02a.abrirABC(event, pagina);
        modelPag02a.pararAudio();
    }

    @FXML
    private void sugestaoAtividades(ActionEvent event) throws IOException {
        modelPag02a.sugestaoAtividade(event, pagina);
    }

    private void atualizarListView() {
        modelPag02a.atualizarListView();
    }
}
