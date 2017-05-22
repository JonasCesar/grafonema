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
import javafx.scene.image.ImageView;
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
    @FXML
    private Button u02;
    @FXML
    private Button u03;
    @FXML
    private ImageView imagemFundo;
    @FXML
    private Button u04;
    @FXML
    private Button u06;
    @FXML
    private Button u07;
    @FXML
    private Button u08;
    @FXML
    private Button u05;
    @FXML
    private Button u11;
    @FXML
    private Button u12;
    @FXML
    private Button u13;
    @FXML
    private Button u09;
    @FXML
    private Button u10;
    @FXML
    private Button u14;
    @FXML
    private Button u17;
    @FXML
    private Button u16;
    @FXML
    private Button u19;
    @FXML
    private Button u18;
    @FXML
    private Button u20;
    @FXML
    private Button u15;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuInicial = new ModelmenuInicial();
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
            case "u01":
                menuInicial.iniciar(event, 1);
                break;
            case "u02":
                System.out.println("Entrou aqui");
                menuInicial.iniciar(event, 2);
                break;
            case "u03":
                menuInicial.iniciar(event, 3);
                break;
            case "u04":
                menuInicial.iniciar(event, 4);
                break;
            case "u05":
                menuInicial.iniciar(event, 5);
                break;
            case "u06":
                menuInicial.iniciar(event, 6);
                break;
            case "u07":
                menuInicial.iniciar(event, 7);
                break;
            case "u08":
                menuInicial.iniciar(event, 8);
                break;
            case "u09":
                menuInicial.iniciar(event, 9);
                break;
            case "u10":
                menuInicial.iniciar(event, 10);
                break;
            case "u11":
                menuInicial.iniciar(event, 11);
                break;
            case "u12":
                menuInicial.iniciar(event, 12);
                break;
            case "u13":
                menuInicial.iniciar(event, 13);
                break;
            case "u14":
                menuInicial.iniciar(event, 14);
                break;
            case "u15":
                menuInicial.iniciar(event, 15);
                break;
            case "u16":
                menuInicial.iniciar(event, 16);
                break;
            case "u17":
                menuInicial.iniciar(event, 17);
                break;
            case "u18":
                menuInicial.iniciar(event, 18);
                break;
            case "u19":
                menuInicial.iniciar(event, 19);
                break;
            case "u20":
                menuInicial.iniciar(event, 20);
                break;
            case "u21":
                menuInicial.iniciar(event, 21);
                break;
            case "u22":
                menuInicial.iniciar(event, 22);
                break;
            case "u23":
                menuInicial.iniciar(event, 23);
                break;
            default:
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
}
