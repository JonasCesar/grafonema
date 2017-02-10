package model;

import controller.Pag02Controller;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelPag01 {

    private String unidadeAtual;
    private String caminhoAudio;

    private File arquivo;
    private ModelClasseComum mCC;
    private Stage janela;

    public ModelPag01() {
        this.unidadeAtual = "u00";
        mCC = new ModelClasseComum(janela);
    }
    /**
     * Pega a unidade atual em execução
     * @return string com o valor da unidade atual
     */
    public String getUnidadeAtual() {
        return this.unidadeAtual;
    }

    /**
     *Define a unidade em que o software se encontra
     * @param unidadeAtual unidade atual da execução
     * @param tituloUnidade titulo a ser utilizado na unidade atual
     */
    public void setUnidadeAtual(String unidadeAtual, Label tituloUnidade) {

        this.unidadeAtual = unidadeAtual;
        tituloUnidade.setText(tituloUnidade.getText() + " " + unidadeAtual.substring(1));
    }
    /**
     * Toca o audio inicial da pagina
     */
    public void tocarAudio() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/l1p1.mp3";
                break;
            default:
                break;
        }
        mCC.play(caminhoAudio);

    }
    /**
     * Carrega a próxima página na tela
     * @param event disparado pelo método avancar do controller
     * @throws IOException 
     */
    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag02.fxml"));

        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag02Controller pg02Cont = fxmloader.<Pag02Controller>getController();
        pg02Cont.setUnidadeAtual(getUnidadeAtual());

        mCC.exibirCena(proximaCena, janela);
    }
    /**
     * Para o audio em execução
     */
    public void pararAudio() {
        mCC.pararAudio();
    }
    /**
     * Carrega o menu inicial
     * @param event disparado pelo método "menuInicial" do controller
     * @throws IOException 
     */
    public void menuInicial(ActionEvent event) throws IOException {
        mCC.menuInicial(event);
    }
    /**
     * Executa o audio da palavra clicada
     * @param palavraSelecionada string que representa a palavra selecionada
     */
    public void tocarAudioPalavraSelecionada(String palavraSelecionada) {
        mCC.tocarAudioPalavraSelecionada(palavraSelecionada);
    }
    /**
     * Carrega a interface do ABC
     * @param event disparado pelo método ABCJanela do controller
     * @throws IOException 
     */
    public void abrirABC(ActionEvent event, int pagina) throws IOException {
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirABC(event, pagina);
    }
    
    
    /**
     * Carrega a interface do manual do software
     * @param event disparado pelo método
     * @param pagina pagina de onde o manual foi chamado
     * @throws IOException 
     */
    public void abrirManual(ActionEvent event, int pagina) throws IOException {
        mCC.pararAudio();
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirManual(event, pagina);
    }
    
}
