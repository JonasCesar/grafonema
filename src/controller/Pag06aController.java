package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import model.ModelPag06a;
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
import javafx.scene.control.TextField;
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
public class Pag06aController implements Initializable {
    @FXML
    private Label p1;
    @FXML
    private Label p2;
    @FXML
    private TextField resposta;
    private ModelPag06a modelPag06a;
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
    private Button confirmar;
    @FXML
    private Label palavrasEstudadas;
    
    private final int pagina = 6;
    
    @FXML
    private Text instrucao;
    @FXML
    private ImageView repetir;
    @FXML
    private Button atividades;
    
    public Pag06aController() {
        listaPalavras = new ListView<String>();        
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag06a = new ModelPag06a(p1,p2, instrucao,listaPalavras);
        Tooltip ouvirPalavras = new Tooltip("Clique em uma palavra para ouvir");
        listaPalavras.setTooltip(ouvirPalavras);
        abc.setTooltip(new Tooltip("Clique para ouvir os sons das letras"));
        atividades.setTooltip(new Tooltip("Clique para ver as atividades para imprimir"));
        manual.setTooltip(new Tooltip("Clique para ler o manual do programa "));
    }    
    /**
     * Define a unidade atual
     * @param unidade valor da unidade atual
     * @throws IOException 
     */
    public void setUnidadeAtual(int unidade) throws IOException {
        atualizarListView();//atualiza a lista de palavras estudadas
        modelPag06a.setUnidadeAtual(unidade);
        modelPag06a.definirLabels();
    }
    /**
     * Pega a unidade atual
     * @return string com o valor da unidade atual
     */
    public int getUnidadeAtual(){
        return modelPag06a.getUnidadeAtual();
    }
    /**
     * Avança para a proxima pagina
     * @param event
     * @throws IOException 
     */
    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag06a.pararAudio();
        modelPag06a.proximaPagina(event);
    }
    /**
     * Volta para a pagina anterior
     * @param event
     * @throws IOException 
     */
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag06a.pararAudio();
        modelPag06a.paginaAnterior(event);
    }    
    /**
     * Verifica se o texto digitado está correto
     * @param event 
     */
    @FXML
    private void verificarTexto(ActionEvent event) throws InterruptedException {
        modelPag06a.pararAudio();
        if(modelPag06a.verificarResposta(resposta.getText())){
            resposta.setText(resposta.getText().toUpperCase());
            resposta.setStyle("-fx-background-color: grey; -fx-font-weight: bold; fx-color: black;");
            resposta.setDisable(true);
            modelPag06a.audioAcerto();
        }else{
            modelPag06a.audioErro();
            resposta.setText("");
            
        }
    }

    /**
     * Leva o usuário para o menu inicial
     * @param event clique no botão "Menu Inicial"
     * @throws IOException 
     */
    @FXML
    private void menuInicial(ActionEvent event) throws IOException{
        modelPag06a.menuInicial(event);
        modelPag06a.pararAudio();
    }
    /**
     * Executa automaticamente o audio da pagina 
     */
    public void tocarAudio() {
        modelPag06a.tocarAudio();
        setInstrucao(modelPag06a.getUnidadeAtual());
    }
    /**
     * Trata o evento de quando o mouse é clicado na lista de palavras
     * @param event mouse é pressionado
     */
    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag06a.tocarAudioPalavraSelecionada(palavraSelecionada);
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
    /**
     * Executa novamente a frase que deve ser completada pelo usuário
     * @param event quando o botão for clicado
     */
    private void ouvirNovamente(ActionEvent event) {
        modelPag06a.executarAudioFrase();
    }
    /**
     * Abre o manual do software
     * @param event clique no botão
     * @throws IOException 
     */
    @FXML
    private void abrirManual(ActionEvent event) throws IOException {
        modelPag06a.abrirManual(event, pagina);
    }
    /**
     * Abre a função ABC do software
     * @param event clique no botão "ABC"
     * @throws IOException 
     */
    
    @FXML
    private void abrirABC(ActionEvent event) throws IOException {
        modelPag06a.abrirABC(event, pagina);
        modelPag06a.pararAudio();
    }
    
    //redireciona para o método definir instrução do model que irá imprimir a instrução na tela
    public void setInstrucao(int unidadeAtual) {
        modelPag06a.definirInstrucao(unidadeAtual);
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
        modelPag06a.pararAudio();
        modelPag06a.tocarAudio();
    }

    @FXML
    private void sugestaoAtividades(ActionEvent event) {
    }
    
    private void atualizarListView() {
        modelPag06a.atualizarListView();
    }
}
