package model;

import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author valdir
 */
public class Model_gameOver {

    private Model_Inicial modelInicial;
    private Stage janela;

    public Model_gameOver() {
        modelInicial = new Model_Inicial();
    }

    /**
     * Reinicia o jogo
     *
     * @param event disparado quando o botão de "Reiniciar" é pressionado
     * @throws IOException
     */
    public void reiniciarJogo(MouseEvent event) throws IOException {
        //modelInicial.iniciar(event); //inicia o jogo novamente
    }

    public void reiniciarJogo(ImageView imgReiniciar) throws IOException {
        modelInicial.iniciar(imgReiniciar); //inicia o jogo novamente
    }

    public void sairJogo(ImageView imgSair) {
        modelInicial.sairDoJogo(imgSair);        
    }
}
