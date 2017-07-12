/*
 * Model do controller "ManualController"
 */
package model;

import controller.Pag01Controller;
import controller.Pag02Controller;
import controller.Pag02aController;
import controller.Pag02bController;
import controller.Pag02cController;
import controller.Pag03Controller;
import controller.Pag03aController;
import controller.Pag03bController;
import controller.Pag03cController;
import controller.Pag03dController;
import controller.Pag04Controller;
import controller.Pag04aController;
import controller.Pag04bController;
import controller.Pag05Controller;
import controller.Pag05aController;
import controller.Pag05bController;
import controller.Pag06Controller;
import controller.Pag06aController;
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
public class ModelAtividades {

    private ModelClasseComum mCC;
    private Stage janela;
    private int unidadeAtual;
    private ListView<String> listaPalavras;

    public ModelAtividades() {
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
    public void voltar(ActionEvent evento, int pagina, String subPagina) throws IOException {
        janela = (Stage) ((Button) evento.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = null;
        Pag01Controller pag01Cont;

        Pag02Controller pag02Cont;
        Pag02aController pag02aCont;
        Pag02bController pag02bCont;
        Pag02cController pag02cCont;

        Pag03Controller pag03Cont;
        Pag03aController pag03aCont;
        Pag03bController pag03bCont;
        Pag03cController pag03cCont;
        Pag03dController pag03dCont;

        Pag04Controller pag04Cont;
        Pag04aController pag04aCont;
        Pag04bController pag04bCont;

        Pag05Controller pag05Cont;
        Pag05aController pag05aCont;
        Pag05bController pag05bCont;

        Pag06Controller pag06Cont;
        Pag06aController pag06aCont;

        Pag07Controller pag07Cont;

        fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag0" + pagina + subPagina + ".fxml"));

        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        System.out.println(""+pagina+subPagina);
        switch ("" + pagina + subPagina) {
            case "1":
                pag01Cont = fxmloader.<Pag01Controller>getController();                
                pag01Cont.setUnidadeAtual(getUnidadeAtual());
                pag01Cont.tocarAudio();
                pag01Cont.setImagemTexto();
                mCC.exibirCena(proximaCena, janela);
                break;
            case "2":
                pag02Cont = fxmloader.<Pag02Controller>getController();                
                pag02Cont.setUnidadeAtual(getUnidadeAtual());
                mCC.exibirCena(proximaCena, janela);
                break;
            case "2a":
                pag02aCont = fxmloader.<Pag02aController>getController();                
                pag02aCont.setUnidadeAtual(getUnidadeAtual());
                mCC.exibirCena(proximaCena, janela);
                break;
            case "2b":
                pag02bCont = fxmloader.<Pag02bController>getController();                
                pag02bCont.setUnidadeAtual(getUnidadeAtual());
                mCC.exibirCena(proximaCena, janela);
                break;
            case "2c":
                pag02cCont = fxmloader.<Pag02cController>getController();                
                pag02cCont.setUnidadeAtual(getUnidadeAtual());
                mCC.exibirCena(proximaCena, janela);
                break;
            case "3":
                pag03Cont = fxmloader.<Pag03Controller>getController();                
                pag03Cont.setUnidadeAtual(getUnidadeAtual());
                pag03Cont.audioInicial();
                pag03Cont.setImagens(getUnidadeAtual());
                mCC.exibirCena(proximaCena, janela);
                break;
            case "3a":
                pag03aCont = fxmloader.<Pag03aController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag03aCont.setUnidadeAtual(getUnidadeAtual());
                pag03aCont.audioInicial();
                pag03aCont.setImagens(getUnidadeAtual());
                break;
            case "3b":
                pag03bCont = fxmloader.<Pag03bController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag03bCont.setUnidadeAtual(getUnidadeAtual());
                pag03bCont.audioInicial();
                pag03bCont.setImagens(getUnidadeAtual());
                break;
            case "3c":
                pag03cCont = fxmloader.<Pag03cController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag03cCont.setUnidadeAtual(getUnidadeAtual());
                pag03cCont.audioInicial();
                pag03cCont.setImagens(getUnidadeAtual());
                break;
            case "3d":
                pag03dCont = fxmloader.<Pag03dController>getController();
                mCC.exibirCena(proximaCena, janela);
                pag03dCont.setUnidadeAtual(getUnidadeAtual());
                pag03dCont.audioInicial();
                pag03dCont.setImagens(getUnidadeAtual());
                break;
            case "4":
                pag04Cont = fxmloader.<Pag04Controller>getController();
                mCC.exibirCena(proximaCena, janela);
                pag04Cont.setUnidadeAtual(getUnidadeAtual());
                pag04Cont.setInstrucao(getUnidadeAtual());
                pag04Cont.tocarAudio();                
                break;
            case "4a":
                pag04aCont = fxmloader.<Pag04aController>getController();                
                pag04aCont.setUnidadeAtual(getUnidadeAtual());
                pag04aCont.setInstrucao(getUnidadeAtual());
                pag04aCont.tocarAudio();
                mCC.exibirCena(proximaCena, janela);
                break;
            case "4b":
                pag04bCont = fxmloader.<Pag04bController>getController();                
                pag04bCont.setUnidadeAtual(getUnidadeAtual());
                pag04bCont.setInstrucao(getUnidadeAtual());
                pag04bCont.tocarAudio();
                mCC.exibirCena(proximaCena, janela);
                break;
            case "5":
                pag05Cont = fxmloader.<Pag05Controller>getController();                
                pag05Cont.setUnidadeAtual(getUnidadeAtual());
                pag05Cont.setInstrucao(unidadeAtual);
                pag05Cont.tocarAudio();
                mCC.exibirCena(proximaCena, janela);
                break;
            case "5a":
                pag05aCont = fxmloader.<Pag05aController>getController();                
                pag05aCont.setUnidadeAtual(getUnidadeAtual());
                pag05aCont.setInstrucao(getUnidadeAtual());
                pag05aCont.tocarAudio();
                mCC.exibirCena(proximaCena, janela);
                break;
            case "5b":
                pag05bCont = fxmloader.<Pag05bController>getController();                
                pag05bCont.setUnidadeAtual(getUnidadeAtual());                
                pag05bCont.tocarAudio();
                pag05bCont.setInstrucao(getUnidadeAtual());
                mCC.exibirCena(proximaCena, janela);
                break;
            case "6":
                pag06Cont = fxmloader.<Pag06Controller>getController();                
                pag06Cont.setUnidadeAtual(getUnidadeAtual());
                pag06Cont.tocarAudio();
                pag06Cont.setInstrucao(getUnidadeAtual());
                mCC.exibirCena(proximaCena, janela);
                break;
            case "6a":
                pag06aCont = fxmloader.<Pag06aController>getController();                
                pag06aCont.setUnidadeAtual(getUnidadeAtual());
                pag06aCont.setInstrucao(getUnidadeAtual());
                pag06aCont.tocarAudio();
                mCC.exibirCena(proximaCena, janela);
                break;
            case "7":
                pag07Cont = fxmloader.<Pag07Controller>getController();                
                pag07Cont.setUnidadeAtual(getUnidadeAtual());
                pag07Cont.setImagemTexto(getUnidadeAtual());
                mCC.exibirCena(proximaCena, janela);
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
