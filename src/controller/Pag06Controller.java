package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import model.ModelPag06;
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
public class Pag06Controller implements Initializable {
    @FXML
    private Label p1;
    @FXML
    private Label p2;
    @FXML
    private TextField resposta;
    private ModelPag06 modelPag06;
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
    private Button confirmar;
    @FXML
    private Label palavrasEstudadas;
    
    private final int pagina = 6;
    
    @FXML
    private Text instrucao;
    @FXML
    private ImageView repetir;
    
    public Pag06Controller() {
        listaPalavras = new ListView<String>();
        controlerComum = new ControllerClasseComum(listaPalavras);
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag06 = new ModelPag06(p1,p2, instrucao);
        Tooltip ouvirPalavras = new Tooltip("Clique em uma palavra para ouvir");
        listaPalavras.setTooltip(ouvirPalavras);
    }    
    /**
     * Define a unidade atual
     * @param unidade valor da unidade atual
     * @throws IOException 
     */
    public void setUnidadeAtual(String unidade) throws IOException {
        atualizarListaPalavras();//atualiza a lista de palavras estudadas
        modelPag06.setUnidadeAtual(unidade);
        switch(getUnidadeAtual()){
            case "u01":
                modelPag06.definirLabels();
                break;
            default:
                break;
        }  
    }
    /**
     * Pega a unidade atual
     * @return string com o valor da unidade atual
     */
    public String getUnidadeAtual(){
        return modelPag06.getUnidadeAtual();
    }
    /**
     * Avança para a proxima pagina
     * @param event
     * @throws IOException 
     */
    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag06.pararAudio();
        modelPag06.proximaPagina(event);
    }
    /**
     * Volta para a pagina anterior
     * @param event
     * @throws IOException 
     */
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag06.pararAudio();
        modelPag06.paginaAnterior(event);
    }    
    /**
     * Verifica se o texto digitado está correto
     * @param event 
     */
    @FXML
    private void verificarTexto(ActionEvent event) throws InterruptedException {
        modelPag06.pararAudio();
        if(modelPag06.verificarResposta(resposta.getText())){
            resposta.setText(resposta.getText().toUpperCase());
            resposta.setStyle("-fx-background-color: grey; -fx-font-weight: bold; fx-color: black;");
            resposta.setDisable(true);
            modelPag06.audioAcerto();
        }else{
            modelPag06.audioErro();
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
        modelPag06.menuInicial(event);
        modelPag06.pararAudio();
    }
    /**
     * Executa automaticamente o audio da pagina 
     */
    public void tocarAudio() throws MalformedURLException {
        modelPag06.tocarAudio();
        setInstrucao(modelPag06.getUnidadeAtual());
    }
    /**
     * Trata o evento de quando o mouse é clicado na lista de palavras
     * @param event mouse é pressionado
     */
    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag06.tocarAudioPalavraSelecionada(palavraSelecionada);
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
    /**
     * Executa novamente a frase que deve ser completada pelo usuário
     * @param event quando o botão for clicado
     */
    private void ouvirNovamente(ActionEvent event) {
        modelPag06.executarAudioFrase();
    }
    /**
     * Abre o manual do software
     * @param event clique no botão
     * @throws IOException 
     */
    private void abrirManual(ActionEvent event) throws IOException {
        modelPag06.abrirManual(event, pagina);
    }
    /**
     * Abre a função ABC do software
     * @param event clique no botão "ABC"
     * @throws IOException 
     */
    
    @FXML
    private void abrirABC(ActionEvent event) throws IOException {
        modelPag06.abrirABC(event, pagina);
        modelPag06.pararAudio();
    }
    
    //redireciona para o método definir instrução do model que irá imprimir a instrução na tela
    public void setInstrucao(String unidadeAtual) throws MalformedURLException {
        modelPag06.definirInstrucao(unidadeAtual);
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
        modelPag06.pararAudio();
        modelPag06.tocarAudio();
    }
}
