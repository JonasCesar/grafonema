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
import model.ModelmenuInicialPag3;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class MenuInicialPag3Controller implements Initializable {


    private ModelmenuInicialPag3 menuInicialPag3;
    @FXML
    private ImageView imagemFundo;
    @FXML
    private Button u41;
    @FXML
    private Button u42;
    @FXML
    private Button u43;
    @FXML
    private Button u44;
    @FXML
    private Button u46;
    @FXML
    private Button u47;
    @FXML
    private Button u48;
    @FXML
    private Button u45;
    @FXML
    private Button u51;
    @FXML
    private Button u52;
    @FXML
    private Button u53;
    @FXML
    private Button u49;
    @FXML
    private Button u50;
    @FXML
    private Button u54;
    @FXML
    private Button u57;
    @FXML
    private Button u56;
    @FXML
    private Button u59;
    @FXML
    private Button u58;
    @FXML
    private Button u60;
    @FXML
    private Button u55;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuInicialPag3 = new ModelmenuInicialPag3();
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
            case "u41":
                System.out.println("Entrou aqui");
                menuInicialPag3.iniciar(event, 41);
                break;
            case "u42":
                menuInicialPag3.iniciar(event, 42);
                break;
            case "u43":
                menuInicialPag3.iniciar(event, 43);
                break;
            case "u44":
                menuInicialPag3.iniciar(event, 44);
                break;
            case "u45":
                menuInicialPag3.iniciar(event, 45);
                break;
            case "u46":
                menuInicialPag3.iniciar(event, 46);
                break;
            case "u47":
                menuInicialPag3.iniciar(event, 47);
                break;
            case "u48":
                menuInicialPag3.iniciar(event, 48);
                break;
            case "u49":
                menuInicialPag3.iniciar(event, 49);
                break;
            case "u50":
                menuInicialPag3.iniciar(event, 50);
                break;
            case "u51":
                menuInicialPag3.iniciar(event, 51);
                break;
            case "u52":
                menuInicialPag3.iniciar(event, 52);
                break;
            case "u53":
                menuInicialPag3.iniciar(event, 53);
                break;
            case "u54":
                menuInicialPag3.iniciar(event, 54);
                break;
            case "u55":
                menuInicialPag3.iniciar(event, 55);
                break;
            case "u56":
                menuInicialPag3.iniciar(event, 56);
                break;
            case "u57":
                menuInicialPag3.iniciar(event, 57);
                break;
            case "u58":
                menuInicialPag3.iniciar(event, 58);
                break;
            case "u59":
                menuInicialPag3.iniciar(event, 59);
                break;
            case "u60":
                menuInicialPag3.iniciar(event, 60);
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
        menuInicialPag3.voltarMenuInicial(event);
    }

    private void proximoMenu(ActionEvent event) throws IOException {
        menuInicialPag3.proximoMenu(event);
    }
}
