/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    
    public void atualizarListaPalavras(ListView listaPalavras) throws FileNotFoundException, IOException {
        this.listaPalavras = listaPalavras;
        BufferedReader lerArq = null;
        try {
            FileReader arquivo = new FileReader("src/AudiosPalavrasEstudadas/texto.txt");
            lerArq = new BufferedReader(arquivo);
            
            String linha = lerArq.readLine();
            if(linha.length()<1){
                linha = lerArq.readLine();
            }
            while (linha != null) {
                items.add(linha);
                linha = lerArq.readLine();
            }
            listaPalavras.setItems(items);
            lerArq.close();
        } catch (Exception e) {
            System.out.println("Provavelmente é a primeira unidade");
        }
    }    
}
