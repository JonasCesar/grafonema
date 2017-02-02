package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

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
    private Button menuInicial;
    @FXML
    private Button confirmar;
    @FXML
    private Label palavrasEstudadas;
    public Pag06Controller() {
        listaPalavras = new ListView<String>();
        controlerComum = new ControllerClasseComum(listaPalavras);
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag06 = new ModelPag06(p1,p2);
    }    
    /**
     * Define a unidade atual
     * @param unidade
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
     * Retorna a unidade atual
     * @return 
     */
    public String getUnidadeAtual(){
        return modelPag06.getUnidadeAtual();
    }
    /**
     * Avança para a pagina 6
     * @param event
     * @throws IOException 
     */
    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag06.pararAudio();
        modelPag06.proximaPagina(event);
    }
    /**
     * Volta para a pag 5
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
    private void verificarTexto(ActionEvent event) {
        modelPag06.pararAudio();
        if(modelPag06.verificarResposta(resposta.getText())){
            resposta.setText(resposta.getText().toUpperCase());
            resposta.setStyle("-fx-background-color: green;");
            resposta.setDisable(true);
            modelPag06.executarAudioFrase();
        }else{
            resposta.setText("");
        }
    }

    /**
     * Handle para o menu inicial
     * @param event
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
    public void tocarAudio() {
        modelPag06.tocarAudio();
    }
    /**
     * Handle para quando o mouse for clicado
     * @param event 
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
     * handle para quando o mouse estiver em cima de um botão
     * @param event 
     */
    @FXML
    private void sombrearBotao(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        ((Button)((event)).getSource()).setEffect(sombras);
    }
    /**
     * Adiciona o efeito de desombreamento no botão
     * @param event 
     */
    @FXML
    private void retirarSombraBotao(MouseEvent event) {        
        ((Button)((event)).getSource()).setEffect(null);
    }
    
}
