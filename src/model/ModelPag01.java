/**
 * Model da pagina 1
 */
package model;

import controller.Pag02Controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelPag01 {

    private int unidadeAtual;
    private String caminhoAudio;
    @FXML
    private ListView<String> listaPalavras;
    private File arquivo;
    private ModelClasseComum mCC;
    private Stage janela;
    @FXML
    private ImageView imagemTexto;
    private URL imagemURL;

    public ModelPag01(ImageView imgView, ListView listaPalavras) {
        this.unidadeAtual = 1;
        this.listaPalavras = listaPalavras;
        mCC = new ModelClasseComum(janela, this.listaPalavras);
        imagemTexto = imgView;

    }

    /**
     * Pega a unidade atual em execução
     *
     * @return string com o valor da unidade atual
     */
    public int getUnidadeAtual() {
        return this.unidadeAtual;
    }

    /**
     * Define a unidade em que o software se encontra
     *
     * @param unidadeAtual unidade atual da execução
     * @param tituloUnidade titulo a ser utilizado na unidade atual
     */
    public void setUnidadeAtual(int unidadeAtual, Label tituloUnidade) {
        System.out.println(" Unidade atual pag01 " + unidadeAtual);
        this.unidadeAtual = unidadeAtual;
    }

    //tituloUnidade.setText(tituloUnidade.getText() + " " + unidadeAtual.substring(1));
    /**
     * Toca o audio inicial da pagina
     */
    public void tocarAudio() {
        switch (getUnidadeAtual()) {
            case 1:
                caminhoAudio = "audios/u01/l1p1.mp3";
                break;
            case 2:
                caminhoAudio = "audios/u02/l2p1.mp3";
                break;
            case 3:
                caminhoAudio = "audios/u03/l3p1.mp3";     
                break;
            default:
                caminhoAudio = "audios/u"+unidadeAtual+"/l"+unidadeAtual+"p1.mp3";
                break;
        }
        mCC.play(caminhoAudio);

    }

    /**
     * Carrega a próxima página na tela
     *
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
     *
     * @param event disparado pelo método "menuInicial" do controller
     * @throws IOException
     */
    public void menuInicial(ActionEvent event) throws IOException {
        mCC.menuInicial(event);
    }

    /**
     * Executa o audio da palavra clicada
     *
     * @param palavraSelecionada string que representa a palavra selecionada
     */
    public void tocarAudioPalavraSelecionada(String palavraSelecionada) {
        mCC.tocarAudioPalavraSelecionada(palavraSelecionada);
    }

    /**
     * Carrega a interface do ABC
     *
     * @param event disparado pelo método ABCJanela do controller
     * @param pagina
     * @throws IOException
     */
    public void abrirABC(ActionEvent event, int pagina) throws IOException {
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirABC(event, pagina);
    }

    /**
     * Carrega a interface do manual do software
     *
     * @param event disparado pelo método
     * @param pagina pagina de onde o manual foi chamado
     * @throws IOException
     */
    public void abrirManual(ActionEvent event, int pagina) throws IOException {
        mCC.pararAudio();
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirManual(event, pagina);
    }

    /**
     * Define a imagem texto que será apresentada na imageView
     *
     * @param unidadeAtual
     */
    public void setImagemTexto(int unidadeAtual) {
        imagemURL = null;
        switch (unidadeAtual) {
            case 1:                
                imagemURL = getClass().getResource("imagens/licao01/imagemTexto.png");
                break;
            case 2:
                imagemURL = getClass().getResource("imagens/licao02/imagemTexto.png");
                break;
            case 3:
                imagemURL = getClass().getResource("imagens/licao03/imagemTexto.png");
                break;
            case 4:
                imagemURL = getClass().getResource("imagens/licao4/imagemTexto.png");
                break;
            default:
                imagemURL = getClass().getResource("imagens/licao"+unidadeAtual+"/imagemTexto.png");
                break;
        }
        imagemTexto.setImage(new Image(imagemURL.toString()));
    }
    public void atualizarListView(int unidade) {        
        mCC.atualizarListView(listaPalavras,unidade);
    }

}
