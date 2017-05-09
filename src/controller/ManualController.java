/*
 * Controller da classe manual
 */
package controller;

import java.net.URL;
import javafx.fxml.FXML;
import model.ModelManual;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.DropShadow;

/**
 * FXML Controller class
 *
 * @author shadows
 */
public class ManualController implements Initializable {

    private ModelManual modelManual;
    private String unidadeAtual;
    private ActionEvent evento;
    private int paginaTemporaria;

    public ManualController() {
        modelManual = new ModelManual();
        evento = null;
        paginaTemporaria = 0;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Realiza o efeito de dessombrear o botão quando o mouse for retirado de
     * cima dele
     *
     * @param event movimentação do mouse para fora do botão
     */
    @FXML
    private void retirarSombraBotao(MouseEvent event) {
        ((Button) ((event)).getSource()).setEffect(null);
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
     * Leva o usuário para o menu inicial
     *
     * @param event clique no botão "Menu Inicial"
     * @throws IOException
     */
    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelManual.menuInicial(event);
        modelManual.pararAudio();
    }

    /**
     * Define a unidade atual
     *
     * @param unidadeAtual
     */
    public void setUnidadeAtual(int unidadeAtual) {
        modelManual.setUnidadeAtual(unidadeAtual);
    }

    /**
     * Retorna o valor da unidade atual
     *
     * @return string da unidade atual
     */
    public int getUnidadeAtual() {
        return modelManual.getUnidadeAtual();
    }

    /**
     * Volta para a pagina anterior
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        modelManual.voltar(event, paginaTemporaria);
    }
    /**
     * Armazena temporariamente o valor da unidade atual
     * @param pagina 
     */
    public void setPaginaTemporaria(int pagina) {
        paginaTemporaria = pagina;
    }
}
