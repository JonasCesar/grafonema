/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.net.MalformedURLException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author shadows
 */
public class Model_SequenciaCenas {
    @FXML
    private ImageView imgView;
    private File arquivoImagem;
    public Model_SequenciaCenas(ImageView imagem) {
        imgView = imagem;
    }

    public void iniciarCenas() throws MalformedURLException {
        arquivoImagem = new File("src/controller/vinho.jpg");        
        imgView.setImage(new Image(arquivoImagem.toURI().toURL().toString()));
    }
    
    
    
}
