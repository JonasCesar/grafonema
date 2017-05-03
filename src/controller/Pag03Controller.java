package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import model.ModelPag03;
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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class Pag03Controller implements Initializable {

    
    
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img6;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img4;
    
    private ModelPag03 modelPag03;
    @FXML
    private ListView<String> listaPalavras;
    private ControllerClasseComum controlerComum;
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
    
    private final int pagina = 3;
    @FXML
    private Button atividades;
    
    public Pag03Controller() {
        listaPalavras = new ListView<String>();
        controlerComum = new ControllerClasseComum(listaPalavras);
    }
       
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag03 = new ModelPag03(img1,img2,img3,img4,img5,img6,instrucao);
        Tooltip ouvirPalavras = new Tooltip("Clique em uma palavra para ouvir");
        listaPalavras.setTooltip(ouvirPalavras);
        abc.setTooltip(new Tooltip("Clique para ouvir os sons das letras"));
        atividades.setTooltip(new Tooltip("Clique para ver as atividades para imprimir"));
        manual.setTooltip(new Tooltip("Clique para ler o manual do programa "));
    }    
    public void audioInicial() throws MalformedURLException{
        modelPag03.tocarAudioInicial();
        setInstrucao(modelPag03.getUnidadeAtual());
    }
    /**
     * Define a unidade atual
     * @param unidade valor da unidade atual
     * @throws IOException 
     */
    public void setUnidadeAtual(String unidade) throws IOException {
        atualizarListaPalavras();
        modelPag03.setUnidadeAtual(unidade);
    }
    /**
     * Avança para a proxima pagina
     * @param event
     * @throws IOException 
     */
    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag03.pararAudio();
        modelPag03.proximaPagina(event);    
        
    }
    /**
     * Volta para a pagina anterior
     * @param event
     * @throws IOException 
     */
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag03.pararAudio();
        modelPag03.paginaAnterior(event);
    }

    //redireciona para o método definir instrução do model que irá imprimir a instrução na tela
    public void setInstrucao(String unidadeAtual) throws MalformedURLException    {
        modelPag03.definirInstrucao(unidadeAtual);
    }
    
    public void setImagens(String unidadeAtual) throws MalformedURLException {
        modelPag03.definirImagens(unidadeAtual);
    }

    @FXML
    private void verificarImagem(MouseEvent event) throws MalformedURLException {
        modelPag03.verificarImagem(event);
    }
    /**
     * Leva o usuário para o menu inicial
     * @param event clique no botão "Menu Inicial"
     * @throws IOException 
     */
    @FXML
    private void menuInicial(ActionEvent event) throws IOException{
        modelPag03.menuInicial(event);
        modelPag03.pararAudio();
    }
    /**
     * Trata o evento de quando o mouse é clicado na lista de palavras
     * @param event mouse é pressionado
     */
    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag03.tocarAudioPalavraSelecionada(palavraSelecionada);
    }
    /**
     * Atualiza a lista de palavras estudadas
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void atualizarListaPalavras() throws FileNotFoundException, IOException {
        controlerComum.atualizarListaPalavras(listaPalavras);

    }
    /**
     * Realiza o efeito de sombrear o botão quando o mouse passar por cima dele
     * @param event movimentação do mouse sobre os componentes
     */
    @FXML
    private void sombrearBotao(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        ((Button)((event)).getSource()).setEffect(sombras);
    }
    /**
     * Realiza o efeito de dessombrear o botão quando o mouse for retirado de cima dele
     * @param event movimentação do mouse para fora do botão
     */
    @FXML
    private void retirarSombraBotao(MouseEvent event) {        
        ((Button)((event)).getSource()).setEffect(null);
    }

    @FXML
    private void executarAudioImagem(MouseEvent event) {
        modelPag03.executarAudioImagem(event);
        
    }
    /**
     * Abre o manual do software
     * @param event clique no botão
     * @throws IOException 
     */
    private void abrirManual(ActionEvent event) throws IOException {
        modelPag03.abrirManual(event, pagina);
    }
    /**
     * Abre a função ABC do software
     * @param event clique no botão "ABC"
     * @throws IOException 
     */
    @FXML
    private void abrirABC(ActionEvent event) throws IOException {
        modelPag03.abrirABC(event, pagina);
        modelPag03.pararAudio();
    }

    @FXML
    private void sugestaoAtividades(ActionEvent event) {
    }
}
