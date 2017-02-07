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

    public String getUnidadeAtual() {
        return this.unidadeAtual;
    }
    /**
     * OBS: Enviar a atualização da unidade para todas as
     * classes que utilizarem
     * @param unidadeAtual 
     * @param tituloUnidade 
     */
    public void setUnidadeAtual(String unidadeAtual, Label tituloUnidade) {
        
        this.unidadeAtual = unidadeAtual;
        tituloUnidade.setText(tituloUnidade.getText()+" "+unidadeAtual.substring(1));
    }

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

    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag02.fxml"));
        
        
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag02Controller pg02Cont = fxmloader.<Pag02Controller>getController();
        pg02Cont.setUnidadeAtual(getUnidadeAtual());
        
        mCC.exibirCena(proximaCena, janela);
    }

    public void pararAudio() {
        mCC.pararAudio();        
    }

    public void menuInicial(ActionEvent event) throws IOException {
        mCC.menuInicial(event);
    }
    

    public void tocarAudioPalavraSelecionada(String palavraSelecionada) {
        mCC.tocarAudioPalavraSelecionada(palavraSelecionada);
    }

    public void abrirManual(ActionEvent event, int pagina) throws IOException {
        mCC.pararAudio();
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirManual(event, pagina);
    }
    
}
