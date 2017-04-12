package controller;

import java.io.IOException;
import model.Model_gameOver;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author iran
 */
public class Gui_GameOverController implements Initializable {


    private Model_gameOver gameOver = new Model_gameOver();
    
    private Stage window;
    @FXML
    private ImageView imagemFundo;
    @FXML
    private ImageView imgReiniciar;
    @FXML
    private ImageView imgSair;
    @FXML
    private Label pontuacao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagemFundo.toBack();
        URL imageReiniciar = getClass().getResource("Imagens/Icones/reiniciar.png");
        URL imageSair = getClass().getResource("Imagens/Icones/sair.png");
       
        // TODO
    }
    /**
     * Realiza a função de reiniciar o jogo
     * @param event botão reiniciar
     * @throws IOException 
     */
    private void handleReiniciar(ActionEvent event) throws IOException {
        //gameOver.reiniciarJogo(event);
    }
    /**
     * Sai do jogo
     * @param event botão sair
     * @throws IOException 
     */
    private void handleSair(ActionEvent event) throws IOException {        
        window = (Stage) ((Button) event.getSource()).getScene().getWindow();        
        window.close();
        System.exit(0);
    }

    @FXML
    private void reiniciarJogo(MouseEvent event) throws IOException {
        gameOver.reiniciarJogo(imgReiniciar);
    }

    @FXML
    private void sairJogo(MouseEvent event) {
        gameOver.sairJogo(imgSair);
    }
    /**
     * Remove a sombra da imagem
     * @param event mouse posicionado sobre a imagem
     */
    @FXML
    private void sombrearImagem(MouseEvent event) {
        DropShadow sombra = new DropShadow();
        ((ImageView) event.getSource()).setEffect(sombra);
    }
    /**
     * Remove o efeito de sombra da imagem
     * @param event mouse retirado de cima da imagem
     */
    @FXML
    private void removerSombraImagem(MouseEvent event) {
        ((ImageView) event.getSource()).setEffect(null);
    }

    public void definirPontuacaoFinal(int pontuacaoTotal) {
        pontuacao.setText(""+pontuacaoTotal);
    }
}
