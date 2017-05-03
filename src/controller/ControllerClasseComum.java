/*
 * Classe com alguma métodos e atributos compartilhados por mais de uma classe
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 *
 * @author shadows
 */
public class ControllerClasseComum {

    ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    private ListView<String> listaPalavras;

    public ControllerClasseComum(ListView listaPalavras) {
        this.listaPalavras = listaPalavras;

    }

    /**
     * Atualiza a lista de palavras que foram estudadas
     *
     * @param listaPalavras listView que deverá ser alterada
     * @throws FileNotFoundException
     * @throws IOException
     */

    public void atualizarListaPalavras(ListView listaPalavras) throws FileNotFoundException, IOException {
//        this.listaPalavras = listaPalavras;
//        BufferedReader lerArq = null;
//        //tenta criar e ler o arquivo texto
//        try {
//            FileReader arquivo = new FileReader("src/AudiosPalavrasEstudadas/texto.txt");
//            lerArq = new BufferedReader(arquivo);
//
//            String linha = lerArq.readLine();
//            if (linha.length() < 1) {
//                linha = lerArq.readLine();
//            }
//            while (linha != null) {
//                items.add(linha);
//                linha = lerArq.readLine();
//            }
//            listaPalavras.setItems(items);
//            lerArq.close();
//        } catch (Exception e) {
//
//        }
//    }
    }
}
