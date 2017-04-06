package model;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
