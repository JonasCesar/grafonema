/*
 * Model do controller "ManualController"
 */
package model;

import controller.Pag01Controller;
import controller.Pag02Controller;
import controller.Pag03Controller;
import controller.Pag04Controller;
import controller.Pag05Controller;
import controller.Pag06Controller;
import controller.Pag07Controller;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelManual {

    private ModelClasseComum mCC;
    private Stage janela;
    private int unidadeAtual;
    private ListView<String> listaPalavras;

    public ModelManual() {
        mCC = new ModelClasseComum(janela,listaPalavras);
        unidadeAtual = 0;
    }
    /**
     * Chama o menu inicial
     * @param event
     * @throws IOException 
     */
    public void menuInicial(ActionEvent event) throws IOException {
        mCC.menuInicial(event);
    }
    /**
     * Para a execução do áudio atual
     */
    public void pararAudio() {
        mCC.pararAudio();
    }
    /**
     * Volta para a pagina de onde o manual foi chamado
     * @param evento disparado quando o botão "Manual" é clicado
     * @param pagina pagina de onde o manual foi chamado
     * @throws IOException 
     */
    public void voltar(ActionEvent evento, int pagina) throws IOException {
        janela = (Stage) ((Button) evento.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = null;
        /**
         * Controllers das paginas
         */
        Pag01Controller pag01Cont;
        Pag02Controller pag02Cont;
        Pag03Controller pag03Cont;
        Pag04Controller pag04Cont;
        Pag05Controller pag05Cont;
        Pag06Controller pag06Cont;
        Pag07Controller pag07Cont;
        //carrega o loader do fxml referente à pagina que chamou o manual
        fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag0" + pagina + ".fxml"));

        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        //carrega a interface correta para cada página
        switch (pagina) {
            case 1:
                pag01Cont = fxmloader.<Pag01Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag01Cont.setUnidadeAtual(getUnidadeAtual());
                pag01Cont.tocarAudio();
                break;
            case 2:
                pag02Cont = fxmloader.<Pag02Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag02Cont.setUnidadeAtual(getUnidadeAtual());
                break;
            case 3:
                pag03Cont = fxmloader.<Pag03Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag03Cont.setUnidadeAtual(getUnidadeAtual());
                pag03Cont.audioInicial();
                pag03Cont.setImagens(getUnidadeAtual());
                break;
            case 4:
                pag04Cont = fxmloader.<Pag04Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag04Cont.setUnidadeAtual(getUnidadeAtual());
                pag04Cont.tocarAudio();
                break;
            case 5:
                pag05Cont = fxmloader.<Pag05Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag05Cont.setUnidadeAtual(getUnidadeAtual());
                pag05Cont.tocarAudio();
                break;
            case 6:
                pag06Cont = fxmloader.<Pag06Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag06Cont.setUnidadeAtual(getUnidadeAtual());
                pag06Cont.tocarAudio();
                break;
            case 7:
                pag07Cont = fxmloader.<Pag07Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag07Cont.setUnidadeAtual(getUnidadeAtual());
                break;
        }

    }
    /**
     * Define a unidade atual
     * @param unidadeAtual unidade atual
     */
    public void setUnidadeAtual(int unidadeAtual) {
        this.unidadeAtual = unidadeAtual;
    }
    /**
     * Retorna a unidade atual
     * @return string referente à unidade atual
     */
    public int getUnidadeAtual() {
        return unidadeAtual;
    }

}
