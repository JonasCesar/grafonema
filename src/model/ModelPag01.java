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
    private String tituloUnidades[]={"1: VOVÔ","2: POVO(OVO, UVA)","3: TATO",
            "4: UVA","5: VIVA", "6: LUVA", "7: LATA", "8: BEBÊ", "9: BOLA/DOCE",
            "10: BOCA", "11: BALA(Baba)", "12: HOJE", "13: PIPA", "14: FURO", 
            "15: FITA", "16: JOGOS", "17: ROXO", "18: GATO e RATO", "19: BONECA",
            "20: DEDOS","21: SINOS","22: RUA", "23: DUAS", "24: ESSA", "25: SETE",
            "26: MOTIVO","27: ACUMULAR","28: PIJAMA","29: ESPUMA", "30: SOPRANDO",
            "31: PERBAMBUCO","32: ÁRVORE","33: FAMOSO","34: ESCOLA","35: LIXINHO",
            "36: MENINA","37: MACARRÃO","38: BICICLETA", "39: MENINO","40: BRAVURA",
            "41: FELIZES","42: FELICIDADE","43: RÁPIDO","44: JUJUBA","45: PADARIAS",
            "46: MOLEZA","47: CHEGA/TOQUES","48: EXPLICAR","49: COMPANHEIROS",
            "50: ESTRELINHA", "51: ÁGUA/QUE/RIQUEZA/TAMANHA", "52: LEMBRANDO",
            "53: ALGODÃO", "54: PRINCESA", "55: PROFESSOR", "56: CRIANÇA", "57: CORDEL",
            "58: ATENÇÃO", "59: FLORESTA", "60: TRANSFORMA"
};

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
        tituloUnidade.setText(tituloUnidade.getText()+" "+tituloUnidades[unidadeAtual-1]);
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
        mCC.abrirABC(event, pagina,"");
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
