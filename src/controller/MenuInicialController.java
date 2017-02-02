/*
 * Controller da classe menu inicial
 */
package controller;

import java.io.IOException;
import model.ModelmenuInicial;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class MenuInicialController implements Initializable {
    @FXML
    private Button u01;
    
    private ModelmenuInicial menuInicial; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuInicial = new ModelmenuInicial();       
    }    
    /**
     * Inicia as lições referentes à unidade que foi selecionada pelo usuário
     * @param event botão clicado
     * @throws IOException 
     */
    @FXML
    private void iniciarUnidade(ActionEvent event) throws IOException {
        String unidade = "";
        unidade = ((Button)(event.getSource())).getId();
        //verifica qual botão foi clicado
        switch(unidade){
            case "u01":
                menuInicial.iniciar(event, unidade);
                break;
            default:
                break;
        }
    }

    public void setUnidadeAtual(String unidadeAtual) {
        
    }
    /**
     * Realiza o efeito de sombrear o botão quando o mouse passar por cima dele
     * @param event movimentação do mouse sobre os componentes
     */
    @FXML
    private void sombrearBotao(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        ((Button)((event)).getSource()).setEffect(sombras);
    }
    /**
     * Realiza o efeito de dessombrear o botão quando o mouse for retirado de cima dele
     * @param event movimentação do mouse em cima do botão
     */
    @FXML
    private void retirarSombraBotao(MouseEvent event) {        
        ((Button)((event)).getSource()).setEffect(null);
    }
}
