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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private Label palavrasEstudadas;
    
    private final int pagina = 3;
    public Pag03Controller() {
        listaPalavras = new ListView<String>();
        controlerComum = new ControllerClasseComum(listaPalavras);
    }
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modelPag03 = new ModelPag03(img1,img2,img3,img4,img5,img6);
    }    
    public void audioInicial(){
        modelPag03.tocarAudioInicial();
    }

    public void setUnidadeAtual(String unidade) throws IOException {
        atualizarListaPalavras();
        modelPag03.setUnidadeAtual(unidade);
    }

    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag03.pararAudio();
        modelPag03.proximaPagina(event);        
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag03.pararAudio();
        modelPag03.paginaAnterior(event);
    }

    public void setImagens(String unidadeAtual) throws MalformedURLException {
        modelPag03.definirImagens(unidadeAtual);
    }

    @FXML
    private void verificarImagem(MouseEvent event) throws MalformedURLException {
        modelPag03.verificarImagem(event);
    }
    
    @FXML
    private void menuInicial(ActionEvent event) throws IOException{
        modelPag03.menuInicial(event);
        modelPag03.pararAudio();
    }

    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag03.tocarAudioPalavraSelecionada(palavraSelecionada);
    }
    private void atualizarListaPalavras() throws FileNotFoundException, IOException {
        controlerComum.atualizarListaPalavras(listaPalavras);

    }
    
    @FXML
    private void sombrearBotao(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        ((Button)((event)).getSource()).setEffect(sombras);
    }
    
    @FXML
    private void retirarSombraBotao(MouseEvent event) {        
        ((Button)((event)).getSource()).setEffect(null);
    }

    @FXML
    private void executarAudioImagem(MouseEvent event) {
        modelPag03.executarAudioImagem(event);
        
    }
    
    @FXML
    private void abrirManual(ActionEvent event) throws IOException {
        modelPag03.abrirManual(event, pagina);
    }
    
    @FXML
    private void abrirABC(ActionEvent event) throws IOException {
        modelPag03.abrirABC(event, pagina);
        modelPag03.pararAudio();
    }
}
