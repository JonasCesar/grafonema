package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import model.ModelPag02;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    
    public Pag02Controller() {
        unidadeAtual = "u00";
        listaPalavras = new ListView<String>();
        
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag02.pararAudio();
        modelPag02.proximaPagina(event);
    }

    @FXML
    private void audio1(ActionEvent event) {
        modelPag02.pararAudio();
        modelPag02.tocarAudio1();
    }

    @FXML
    private void audio2(ActionEvent event) {
        modelPag02.pararAudio();
        modelPag02.tocarAudio2();
    }

    @FXML
    private void audio3(ActionEvent event) {
        modelPag02.pararAudio();
        modelPag02.tocarAudio3();
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelPag02.pararAudio();
        modelPag02.paginaAnterior(event);        
    }
    
    @FXML
    private void menuInicial(ActionEvent event) throws IOException{
        modelPag02.menuInicial(event);
        modelPag02.pararAudio();
    }

    public String getUnidadeAtual() {
        return modelPag02.getUnidadeAtual();
    }

    public void setUnidadeAtual(String unidade) throws IOException {
        atualizarListaPalavras();
        switch(unidade){
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
        BufferedReader lerArq = null;
        try {
            FileReader arquivo = new FileReader("src/AudiosPalavrasEstudadas/texto.txt");
            lerArq = new BufferedReader(arquivo);
            
            String linha = lerArq.readLine();
            if(linha.length()<1){
                linha = lerArq.readLine();
            }
            while (linha != null) {
                System.out.printf("%s\n", linha);
                items.add(linha);
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            listaPalavras.setItems(items);
            lerArq.close();
        } catch (Exception e) {
            System.out.println("Provavelmente é a primeira unidade");
        }

    }
}