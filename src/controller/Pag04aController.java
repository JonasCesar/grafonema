/**
 * Controller da página 4
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import model.ModelPag04a;
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
public class Pag04aController implements Initializable {

    private ModelPag04a modelPag04a;
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

    private final int pagina = 4;
    
    @FXML
    ImageView repetir;
    
    @FXML
    ImageView imagemAudio;
    
    @FXML
    AnchorPane janelaPrograma;
    
    @FXML
    private Text instrucao;
    @FXML
    private Button atividades;
    @FXML
    private Label f3;

    public Pag04aController() {
        listaPalavras = new ListView<>();
             
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag04a = new ModelPag04a(p1, p2, p3, p4, p5, f1, f2, espaco, imagemAudio, janelaPrograma, instrucao, listaPalavras,f3);
        Tooltip ouvirPalavras = new Tooltip("Clique em uma palavra para ouvir");
        listaPalavras.setTooltip(ouvirPalavras);
        abc.setTooltip(new Tooltip("Clique para ouvir os sons das letras"));
        atividades.setTooltip(new Tooltip("Clique para ver as atividades para imprimir"));
        manual.setTooltip(new Tooltip("Clique para ler o manual do programa "));
    }
    /**
     * Define a unidade atual
     * @throws IOException 
     */
    public void setUnidadeAtual(int unidadeAtual) throws IOException {
        atualizarListView();
        modelPag04a.setUnidadeAtual(unidadeAtual);
    }
    /**
     * Avança para a proxima pagina
     * @param event
     * @throws IOException 
     */
    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag04a.pararAudio();
        modelPag04a.proximaPagina(event);
    }

    /**
     * Volta para a pagina anterior
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag04a.pararAudio();
        modelPag04a.paginaAnterior(event);
    }
    /**
     * Chama o método do model que trata o evento de quando o mouse é pressionado
     * @param event uma das labels contendo as sílabas é pressionada
     */
    @FXML
    private void mousePressionado(MouseEvent event) {
        modelPag04a.mousePressionado(event);
    }
    /**
     * Chama o método do model que trata o evento de quando a label pressionada é
     * arrastada pela tela
     * @param event mouse é arrastado pela tela
     */
    @FXML
    private void mouseArrastado(MouseEvent event) {
        modelPag04a.mouseArrastado(event);
        
    }
    /**
     * Chama o método do model que trata o evento de quando o mouse, que estava pressionado,
     * é solto
     * @param event mouse é liberado (label é solta)
     * @throws MalformedURLException 
     */
    @FXML
    private void mouseLiberado(MouseEvent event) throws MalformedURLException, InterruptedException {
        modelPag04a.mouseLiberado(event);        
    }

    public void tocarAudio() throws MalformedURLException {
        modelPag04a.tocarAudio();
        setInstrucao(modelPag04a.getUnidadeAtual());
    }

    /**
     * Leva o usuário para o menu inicial
     *
     * @param event clique no botão "Menu Inicial"
     * @throws IOException
     */
    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelPag04a.menuInicial(event);
        modelPag04a.pararAudio();
    }
    /**
     * Trata o evento de quando o mouse é clicado na lista de palavras
     * @param event mouse é pressionado
     */
    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag04a.tocarAudioPalavraSelecionada(palavraSelecionada);
    }
    /**
     * Atualiza a lista de palavras estudadas
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void atualizarListaPalavras() throws FileNotFoundException, IOException {
        //controlerComum.atualizarListaPalavras(listaPalavras);
    }
    /**
     * Realiza o efeito de sombrear o botão quando o mouse passar por cima dele
     * @param event movimentação do mouse sobre os componentes
     */
    @FXML
    private void sombrearBotao(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        ((Button) ((event)).getSource()).setEffect(sombras);
    }
    /**
     * Realiza o efeito de dessombrear o botão quando o mouse for retirado de cima dele
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
        modelPag04a.abrirManual(event, pagina);
    }

    /**
     * Abre a função ABC do software
     *
     * @param event clique no botão "ABC"
     * @throws IOException
     */
    @FXML
    private void abrirABC(ActionEvent event) throws IOException {
        modelPag04a.abrirABC(event, pagina);
        modelPag04a.pararAudio();
    }
    
    //redireciona para o método definir instrução do model que irá imprimir a instrução na tela
    public void setInstrucao(int unidadeAtual) throws MalformedURLException    {
        modelPag04a.definirInstrucao(unidadeAtual);
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
        modelPag04a.pararAudio();
        modelPag04a.tocarAudio();
    }

    @FXML
    private void sugestaoAtividades(ActionEvent event) {
    }
    
    private void atualizarListView() {
        modelPag04a.atualizarListView();
    }

}
