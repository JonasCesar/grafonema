package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import model.ModelPag01;
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
public class Pag01Controller implements Initializable {

    private String unidadeAtual;

    private ModelPag01 modelPag01;
    @FXML
    private Label tituloUnidade;
    @FXML
    private ListView<String> listaPalavras;
    ObservableList<String> items = FXCollections.observableArrayList();

    public Pag01Controller() {
        unidadeAtual = "u00";
        modelPag01 = new ModelPag01();
        listaPalavras = new ListView<String>();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void tocarAudio() {

        
        listaPalavras.setItems(items);
        modelPag01.tocarAudio();
    }

    public String getUnidadeAtual() {
        return modelPag01.getUnidadeAtual();
    }

    public void setUnidadeAtual(String unidade) throws FileNotFoundException, IOException {
        atualizarListaPalavras();

        modelPag01.setUnidadeAtual(unidade, tituloUnidade);
    }

    @FXML
    private void avancar(ActionEvent event) throws IOException {
        modelPag01.pararAudio();
        modelPag01.proximaPagina(event);
    }

    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelPag01.menuInicial(event);
        modelPag01.pararAudio();
    }

    @FXML
    private void mouseClicado(MouseEvent event) {
        String palavraSelecionada = listaPalavras.getSelectionModel().getSelectedItem();
        modelPag01.tocarAudioPalavraSelecionada(palavraSelecionada);
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
