package model;

import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Iran Ribeiro
 */
public class Model_gameOver {

    private Model_Inicial modelInicial;
    private Stage janela;

    public Model_gameOver() {
        modelInicial = new Model_Inicial();
    }

    /**
     * Reinicia o jogo 
     * @param imgReiniciar disparado quando a imagem é clicada
     * @throws IOException 
     */
    public void reiniciarJogo(ImageView imgReiniciar) throws IOException {
        modelInicial.iniciar(imgReiniciar); //inicia o jogo novamente
    }
    /**
     *  Sai do jogo
     * @param imgSair 
     */
    public void sairJogo(ImageView imgSair) {
        modelInicial.sairDoJogo(imgSair);        
    }
}
