/*
 * Controller da classe menu inicial
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.ModelmenuInicialPag2;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class MenuInicialPag2Controller implements Initializable {


    private ModelmenuInicialPag2 menuInicialPag2;
    @FXML
    private ImageView imagemFundo;
    @FXML
    private Button u21;
    @FXML
    private Button u22;
    @FXML
    private Button u23;
    @FXML
    private Button u24;
    @FXML
    private Button u26;
    @FXML
    private Button u27;
    @FXML
    private Button u28;
    @FXML
    private Button u25;
    @FXML
    private Button u31;
    @FXML
    private Button u32;
    @FXML
    private Button u33;
    @FXML
    private Button u29;
    @FXML
    private Button u30;
    @FXML
    private Button u34;
    @FXML
    private Button u37;
    @FXML
    private Button u36;
    @FXML
    private Button u39;
    @FXML
    private Button u38;
    @FXML
    private Button u40;
    @FXML
    private Button u35;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuInicialPag2 = new ModelmenuInicialPag2();
    }

    /**
     * Inicia as lições referentes à unidade que foi selecionada pelo usuário
     *
     * @param event botão clicado
     * @throws IOException
     */
    @FXML
    private void iniciarUnidade(ActionEvent event) throws IOException {
        String unidade = "";
        unidade = ((Button) (event.getSource())).getId();
        //verifica qual botão foi clicado
        switch (unidade) {
            case "u21":
                System.out.println("Entrou aqui");
                menuInicialPag2.iniciar(event, 21);
                break;
            case "u22":
                menuInicialPag2.iniciar(event, 22);
                break;
            case "u23":
                menuInicialPag2.iniciar(event, 23);
                break;
            case "u24":
                menuInicialPag2.iniciar(event, 24);
                break;
            case "u25":
                menuInicialPag2.iniciar(event, 25);
                break;
            case "u26":
                menuInicialPag2.iniciar(event, 26);
                break;
            case "u27":
                menuInicialPag2.iniciar(event, 27);
                break;
            case "u28":
                menuInicialPag2.iniciar(event, 28);
                break;
            case "u29":
                menuInicialPag2.iniciar(event, 29);
                break;
            case "u30":
                menuInicialPag2.iniciar(event, 30);
                break;
            case "u31":
                menuInicialPag2.iniciar(event, 31);
                break;
            case "u32":
                menuInicialPag2.iniciar(event, 32);
                break;
            case "u33":
                menuInicialPag2.iniciar(event, 33);
                break;
            case "u34":
                menuInicialPag2.iniciar(event, 34);
                break;
            case "u35":
                menuInicialPag2.iniciar(event, 35);
                break;
            case "u36":
                menuInicialPag2.iniciar(event, 36);
                break;
            case "u37":
                menuInicialPag2.iniciar(event, 37);
                break;
            case "u38":
                menuInicialPag2.iniciar(event, 38);
                break;
            case "u39":
                menuInicialPag2.iniciar(event, 39);
                break;
            case "u40":
                menuInicialPag2.iniciar(event, 40);
                break;
            
        }
    }

    public void setUnidadeAtual(String unidadeAtual) {

    }

    /**
     * Realiza o efeito de sombrear o botão quando o mouse passar por cima dele
     *
     * @param event movimentação do mouse sobre os componentes
     */
    @FXML
    private void sombrearBotao(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        ((Button) ((event)).getSource()).setEffect(sombras);
    }

    /**
     * Realiza o efeito de dessombrear o botão quando o mouse for retirado de
     * cima dele
     *
     * @param event movimentação do mouse em cima do botão
     */
    @FXML
    private void retirarSombraBotao(MouseEvent event) {
        ((Button) ((event)).getSource()).setEffect(null);
    }

    @FXML
    private void voltarMenuAnterior(ActionEvent event) throws IOException {
        menuInicialPag2.voltarMenuInicial(event);
    }

    @FXML
    private void proximoMenu(ActionEvent event) throws IOException {
        menuInicialPag2.proximoMenu(event);
    }

    @FXML
    private void abrirCreditos(ActionEvent event) {
        menuInicialPag2.abrirCreditos(event);
    }
}
