package model;

import java.io.IOException;
import javafx.event.ActionEvent;

/**
 *
 * @author valdir
 */
public class Model_gameOver {
    private Model_Inicial modelInicial;
    
    public Model_gameOver() {
        modelInicial = new Model_Inicial();
    }
    /**
     * Reinicia o jogo
     * @param event disparado quando o botão de "Reiniciar" é pressionado
     * @throws IOException 
     */
    public void reiniciarJogo(ActionEvent event) throws IOException {
        modelInicial.iniciar(event); //inicia o jogo novamente
    }
    
}
