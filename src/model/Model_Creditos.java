package model;

import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author jonas
 */
public class Model_Creditos {

    @FXML
    ImageView imagemCreditos;

    @FXML
    Button proximaImagem;
    @FXML
    Button imagemAnterior;

    private int numeroImagem;

    private String[] listaDiretoriosImagens = {"1/img1", "1/img2", "1/img3", "1/img4", "1/img5"};

    public Model_Creditos(ImageView imagemCreditos, Button proximaImagem, Button imagemAnterior) {
        this.imagemAnterior = imagemAnterior;
        this.imagemCreditos = imagemCreditos;
        this.proximaImagem = proximaImagem;
        this.numeroImagem = -1;
    }

    public void imagemAnterior(ActionEvent event) {
        if (numeroImagem > 0) {
            numeroImagem--;
            URL arquivoImg = getClass().getResource("Imagens/fase" + listaDiretoriosImagens[numeroImagem] + ".jpg");
            imagemCreditos.setImage(new Image(arquivoImg.toString()));
        }

    }

    public void proximaImagem(ActionEvent event) {
        if (numeroImagem < listaDiretoriosImagens.length-1) {
            numeroImagem++;
            URL arquivoImg = getClass().getResource("Imagens/fase" + listaDiretoriosImagens[numeroImagem] + ".jpg");
            imagemCreditos.setImage(new Image(arquivoImg.toString()));
        }

    }

}
