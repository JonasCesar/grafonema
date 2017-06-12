/*
 * Model da pagina 01
 */
package model;

import controller.Pag01Controller;
import controller.Pag06Controller;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
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
public class ModelPag07 {

    private Stage janela;
    private String caminhoAudio;
    private int unidadeAtual;
    private ModelClasseComum mCC;
    private final int pagina = 7;
    private ListView<String> listaPalavras;
    private URL imagemURL;
    private ImageView imagemTexto;
    private String tituloUnidades[]={"1: VOVÔ","2: POVO(OVO, UVA)","3: TATO",
            "4: UVA","5: VIVA", "6: LUVA", "7: LATA", "8: BEBÊ", "9: BOLA/DOCE",
            "10: BOCA", "11: BALA(Baba)", "12: HOJE", "13: PIPA", "14: FURO", 
            "15: FITA", "16: JOGOS", "17: ROXO", "18: GATO e RATO", "19: BONECA",
            "20: DEDOS","21: SINOS","22: RUA", "23: DUAS", "24: ESSA", "25: SETE",
            "26: MOTIVO","27: ACUMULAR","28: PIJAMA","29: ESPUMA"};
    public ModelPag07(ImageView imagemTexto) {
        this.imagemTexto = imagemTexto;
        this.unidadeAtual = 0;
        mCC = new ModelClasseComum(janela, listaPalavras);
    }

    /**
     * Carrega a próxima página na tela
     *
     * @param event disparado pelo método avancar do controller
     * @throws IOException
     */
    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag01.fxml"));
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag01Controller pg01Cont = fxmloader.<Pag01Controller>getController();
        pg01Cont.setUnidadeAtual(getUnidadeAtual() + 1);
        mCC.exibirCena(proximaCena, janela);
        pg01Cont.setImagemTexto();
        pg01Cont.tocarAudio();
    }

    /**
     * Define a unidade em que o software se encontra
     *
     * @param unidade
     * @param tituloUnidade
     */
    public void setUnidadeAtual(int unidade, Label tituloUnidade) {
        this.unidadeAtual = unidade;

        System.out.println(tituloUnidade + " " + unidade);
        tituloUnidade.setText(tituloUnidade.getText() + " "+tituloUnidades[unidade-1]);
    }

    /**
     * Executra o audio que deve ser executado nessa pagina
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
            case 4:
                caminhoAudio = "audios/u4/l4p1.mp3";
                break;
            default:
                caminhoAudio = "audios/u" + getUnidadeAtual() + "/l" + getUnidadeAtual() + "p1.mp3";
                break;                
        }
        mCC.play(caminhoAudio);
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
     * Carrega a página anterior
     *
     * @param event disparado pelo método voltar do controller
     * @throws IOException
     */
    public void paginaAnterior(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag06.fxml"));
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag06Controller pg06Cont = fxmloader.<Pag06Controller>getController();

        mCC.exibirCena(proximaCena, janela);
        pg06Cont.setUnidadeAtual(getUnidadeAtual());
        pg06Cont.tocarAudio();
    }

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

    public void atualizarListView() {
        mCC.atualizarListView(listaPalavras,getUnidadeAtual());
    }

    public void incrementarUnidade(int valor) {
        System.out.println("Unidade atual " + getUnidadeAtual());
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
            default:
                imagemURL = getClass().getResource("imagens/licao"+unidadeAtual+"/imagemTexto.png");
                break;                
                
        }
        imagemTexto.setImage(new Image(imagemURL.toString()));
    }

}
