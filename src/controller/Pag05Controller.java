package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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

    private final int pagina = 5;

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
    private Button voltar;
    @FXML
    private Label palavrasEstudadas;

    @FXML
    private Text instrucao;
    
    @FXML
    ImageView imagemAudio;
    
    @FXML
    AnchorPane janelaPrograma;

    @FXML
    ImageView repetir;
    @FXML
    private Button atividades;
    @FXML
    private Label f3;
    
    public Pag05Controller() {
        listaPalavras = new ListView<>();        
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag05 = new ModelPag05(p1, p2, p3, p4, p5, f1, f2,espaco,
                imagemAudio, janelaPrograma, instrucao, listaPalavras,f3);
        Tooltip ouvirPalavras = new Tooltip("Clique em uma palavra para ouvir");
        listaPalavras.setTooltip(ouvirPalavras);
        abc.setTooltip(new Tooltip("Clique para ouvir os sons das letras"));
        atividades.setTooltip(new Tooltip("Clique para ver as atividades para imprimir"));
        manual.setTooltip(new Tooltip("Clique para ler o manual do programa "));
    }    
    /**
     * Define a unidade atual
     * @param unidadeAtual
     * @throws IOException 
     */
    public void setUnidadeAtual(int unidadeAtual) throws IOException {
        atualizarListView();
        modelPag05.setUnidadeAtual(unidadeAtual);
    }

    /**
     * Avança para a proxima pagina
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag05.pararAudio();
        modelPag05.proximaPagina(event);
    }

    /**
     * Volta para a pagina anterior
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag05.pararAudio();
        modelPag05.paginaAnterior(event);
    }
    /**
     * Chama o método do model que trata o evento de quando o mouse é liberado
     * @param event mouse liberado
     * @throws MalformedURLException 
     */
    @FXML
    private void mouseLiberado(MouseEvent event) throws MalformedURLException {
        modelPag05.mouseLiberado(event);
        
    }
    /**
     * Trata o evento de quando o mouse é arrastado
     * @param event mouse arrastado
     */
    @FXML
    private void mouseArrastado(MouseEvent event) {
        modelPag05.mouseArrastado(event);        
    }
    /**
     * Trata o evento de quando o mouse é pressionado
     * @param event mouse pressionado
     */
    @FXML
    private void mousePressionado(MouseEvent event) {
        modelPag05.mousePressionado(event);        
    }
    /**
     * toca o audio da pag 05
     */
    public void tocarAudio() throws MalformedURLException {
        modelPag05.tocarAudio();
        setInstrucao(modelPag05.getUnidadeAtual());
    }

    /**
     * Leva o usuário para o menu inicial
     *
     * @param event clique no botão "Menu Inicial"
     * @throws IOException
     */
    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelPag05.menuInicial(event);
        modelPag05.pararAudio();
    }

    /**
     * Trata o evento de quando o mouse é clicado na lista de palavras
     *
     * @param event mouse é pressionado
     */
    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag05.tocarAudioPalavraSelecionada(palavraSelecionada);
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
        modelPag05.abrirManual(event, pagina);
    }

    /**
     * Abre a função ABC do software
     *
     * @param event clique no botão "ABC"
     * @throws IOException
     */

    @FXML
    private void abrirABC(ActionEvent event) throws IOException {
        modelPag05.abrirABC(event, pagina);
        modelPag05.pararAudio();
    }
    /**
     * Retira a sombra do icone de "replay"
     * @param event mouse passado por cima do icone
     */
    @FXML
    private void dessombrearImagem(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        repetir.setEffect(null);
    }
    /**
     * Adiciona uma sobra ao icone de "Replay"
     * @param event 
     */
    @FXML
    private void sombrearImagem(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        repetir.setEffect(sombras);
    }
    /**
     * Executa o áudio da classe novamente
     * @param event 
     */
    @FXML
    private void replayAudio(MouseEvent event) {
        modelPag05.pararAudio();
        modelPag05.tocarAudio();
    }
    
    public void setInstrucao(int unidadeAtual)    {
        modelPag05.definirInstrucao(unidadeAtual);
    }

    @FXML
    private void sugestaoAtividades(ActionEvent event) {
    }
    
    private void atualizarListView() {
        modelPag05.atualizarListView();
    }
}
