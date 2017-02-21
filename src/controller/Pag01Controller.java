/**
 * Controller da página 01
 */
package controller;

import java.io.FileNotFoundException;
import model.ModelPag01;
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
public class Pag01Controller implements Initializable {

    private String unidadeAtual;

    private ModelPag01 modelPag01;
    @FXML
    private Label tituloUnidade;
    @FXML
    private ListView<String> listaPalavras;
    ObservableList<String> items = FXCollections.observableArrayList();
    private ControllerClasseComum controlerComum;
    @FXML
    private Button abc;
    @FXML
    private Button manual;
    @FXML
    private Button avancar;
    @FXML
    private Label palavrasEstudadas;
    
    private final int pagina = 1;
    @FXML
    private ImageView iconeOuvir;
    @FXML
    private Button atividades;

    public Pag01Controller() {
        unidadeAtual = "u00";
        modelPag01 = new ModelPag01();
        listaPalavras = new ListView<String>();
        controlerComum = new ControllerClasseComum(listaPalavras);
        iconeOuvir = new ImageView();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tooltip ouvirPalavras = new Tooltip("Clique em uma palavra para ouvir");
        listaPalavras.setTooltip(ouvirPalavras);
        abc.setTooltip(new Tooltip("Clique para ouvir os sons das letras"));
        atividades.setTooltip(new Tooltip("Clique para ver as atividades para imprimir"));
        manual.setTooltip(new Tooltip("Clique para ler o manual do programa "));
        
        
    }
    /**
     * Toca o audio que será iniciado quando a classe for iniciada
     */
    public void tocarAudio() {        
        modelPag01.tocarAudio();
    }
    /**
     * Retorna o valor da unidade atual
     * @return 
     */
    public String getUnidadeAtual() {
        return modelPag01.getUnidadeAtual();
    }
    /**
     * Define a unidade atual
     * @param unidade valor da unidade atual
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void setUnidadeAtual(String unidade) throws FileNotFoundException, IOException {
        atualizarListaPalavras();
        modelPag01.setUnidadeAtual(unidade, tituloUnidade);
    }
    /**
     * Avança para a próxima pagina
     * @param event
     * @throws IOException 
     */
    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag01.pararAudio();
        modelPag01.proximaPagina(event);
    }
    /**
     * Leva o usuário para o menu inicial
     * @param event clique no botão "Menu Inicial"
     * @throws IOException 
     */
    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelPag01.menuInicial(event);
        modelPag01.pararAudio(); //para o áudio que está sendo executado
    }
    /**
     * Trata o evento de quando o mouse é clicado na lista de palavras
     * @param event mouse é pressionado
     */
    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag01.tocarAudioPalavraSelecionada(palavraSelecionada);
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
     * @param event clique no botão
     * @throws IOException 
     */
    @FXML
    private void abrirManual(ActionEvent event) throws IOException {
        modelPag01.abrirManual(event, pagina);
    }
    /**
     * Abre a função ABC do software
     * @param event clique no botão "ABC"
     * @throws IOException 
     */
    @FXML
    private void abrirABC(ActionEvent event) throws IOException {
        modelPag01.abrirABC(event, pagina);
        modelPag01.pararAudio();
        System.out.println("abriu o abc da pagina 1");
    }

    @FXML
    private void dicaOuvirPalavras(MouseEvent event) {
       // System.out.println("Vovo");
        Tooltip ouvirPalavras = new Tooltip("Clique em uma palavra para ouvir");
        
        listaPalavras.setTooltip(ouvirPalavras);
    }

    @FXML
    private void sugestaoAtividades(ActionEvent event) {
    }
}
