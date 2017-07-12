/**
 * Model da página 3
 */
package model;

import controller.Pag03aController;
import controller.Pag03bController;
import controller.Pag03dController;
import controller.Pag04Controller;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelPag03c {

    private String caminhoAudio;
    private int unidadeAtual;
    private File arquivo;
    private Image i1, i2, i3, i4, i5, i6;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img6;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img4;

    @FXML
    private Text instrucao;

    private File f1, f2, f3, f4, f5, f6;
    private URL imgf1, imgf2, imgf3, imgf4, imgf5, imgf6;
    private Stage janela;
    private ModelClasseComum mCC;
    @FXML
    ListView<String> listaPalavras;
    //
    private String listaInstrucao[] = {"", "", "", "", "", "", "", "", "", "", "",
        "", "", "", "", "", "", "", "", "", "", "", "", "", "\"CES\"", "", "", "", "",
        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
        "", "", "", "", "", "", "", "", "", "", "", "\"NHA\""};

    public ModelPag03c(ImageView i1, ImageView i2, ImageView i3, ImageView i4, ImageView i5, ImageView i6, Text instrucao1,
            ListView<String> listaPalavras) {
        img1 = i1;
        img2 = i2;
        img3 = i3;
        img4 = i4;
        img5 = i5;
        img6 = i6;
        this.listaPalavras = listaPalavras;
        this.instrucao = instrucao1;

        unidadeAtual = 0;
        caminhoAudio = "";

        mCC = new ModelClasseComum(janela, this.listaPalavras);
    }

    /**
     * Define a unidade em que o software se encontra
     *
     * @param unidade
     */
    public void setUnidadeAtual(int unidade) {
        this.unidadeAtual = unidade;
    }

    /**
     * Pega a unidade atual em execução
     *
     * @return string com o valor da unidade atual
     */
    public int getUnidadeAtual() {
        return unidadeAtual;
    }

    /**
     * Carrega a página anterior
     *
     * @param event disparado pelo método voltar do controller
     * @throws IOException
     */
    public void paginaAnterior(ActionEvent event) throws IOException {
        int u = getUnidadeAtual();
        if (u == 51 || u == 25) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag03b.fxml"));
            //cria a próxima cena chamando a inteface dos avatares        
            Parent proximaCena = (Parent) fxmloader.load();
            Pag03bController pag03bCont = fxmloader.<Pag03bController>getController();
            mCC.exibirCena(proximaCena, janela);
            pag03bCont.setUnidadeAtual(getUnidadeAtual());
            pag03bCont.setUnidadeAtual(getUnidadeAtual());
            pag03bCont.audioInicial();
            pag03bCont.setImagens(getUnidadeAtual());
        } else {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag03a.fxml"));
            //cria a próxima cena chamando a inteface dos avatares        
            Parent proximaCena = (Parent) fxmloader.load();
            Pag03aController pag03aCont = fxmloader.<Pag03aController>getController();
            mCC.exibirCena(proximaCena, janela);
            pag03aCont.setUnidadeAtual(getUnidadeAtual());
            pag03aCont.setUnidadeAtual(getUnidadeAtual());
            pag03aCont.audioInicial();
            pag03aCont.setImagens(getUnidadeAtual());

        }
    }

    /**
     * Carrega a próxima página na tela
     *
     * @param event disparado pelo método avancar do controller
     * @throws IOException
     */
    public void proximaPagina(ActionEvent event) throws IOException {
        int u = getUnidadeAtual();
        if (u == 25) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag03d.fxml"));
            //cria a próxima cena chamando a inteface dos avatares        
            Parent proximaCena = (Parent) fxmloader.load();
            Pag03dController pg03dCont = fxmloader.<Pag03dController>getController();
            pg03dCont.setUnidadeAtual(getUnidadeAtual());
            pg03dCont.setInstrucao(unidadeAtual);
            pg03dCont.setImagens(unidadeAtual);
            mCC.exibirCena(proximaCena, janela);
            pg03dCont.audioInicial();

        } else {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag04.fxml"));
            //cria a próxima cena chamando a inteface dos avatares        
            Parent proximaCena = (Parent) fxmloader.load();
            Pag04Controller pg04Cont = fxmloader.<Pag04Controller>getController();
            pg04Cont.setUnidadeAtual(getUnidadeAtual());
            mCC.exibirCena(proximaCena, janela);
            pg04Cont.tocarAudio();
        }

    }

    /**
     * Verifica se a imagem clicada contem o fonema
     *
     * @param event disparado quando uma das 6 imagens é clicada
     */
    public void verificarImagem(MouseEvent event) {
        String imgClicada = ((ImageView) event.getSource()).getId();
        String nomeImagem = imgClicada.substring(3, 4);

        switch (getUnidadeAtual()) {
            //acertos img1, img3, img5
            case 25:
                if (imgClicada.equals("img5") || imgClicada.equals("img6")) {
                    imgf1 = getClass().getResource("imagens/licao" + unidadeAtual + "c/" + nomeImagem + "c.png");
                } else {
                    imgf1 = getClass().getResource("imagens/licao" + unidadeAtual + "c/" + nomeImagem + "e.png");
                }
                break;
            case 51:
                if (imgClicada.equals("img3") || imgClicada.equals("img5") || imgClicada.equals("img6")) {
                    imgf1 = getClass().getResource("imagens/licao" + unidadeAtual + "c/" + nomeImagem + "c.png");
                } else {
                    imgf1 = getClass().getResource("imagens/licao" + unidadeAtual + "c/" + nomeImagem + "e.png");
                }
                break;
            default:

                break;
        }
        ((ImageView) event.getSource()).setImage(new Image(imgf1.toString()));
    }

    /**
     * Executa o audio inicial da página
     */
    public void tocarAudioInicial() {
        switch (getUnidadeAtual()) {
            case 1:
                caminhoAudio = "audios/u01/l1p3.mp3";
                break;
            case 2:
                caminhoAudio = "audios/u02/l2p3.mp3";
                break;
            case 3:
                caminhoAudio = "audios/u03/l3p3a2.mp3";
            case 4:
                caminhoAudio = "audios/u4/l4p3a2.mp3";
                break;
            default:
                caminhoAudio = "audios/u" + getUnidadeAtual() + "c/l" + unidadeAtual + "p3.mp3";
                break;
        }

        mCC.play(caminhoAudio);
    }

    /**
     * Define as imagens da página
     *
     * @param unidadeAtual unidade atual em execução
     * @throws MalformedURLException
     */
    public void definirImagens(int unidadeAtual) throws MalformedURLException {
        String caminho = "";
        imgf1 = imgf2 = imgf3 = imgf4 = imgf5 = imgf6 = null;
        /**
         * Cria arquivos que chamam as imagens e pegar a URL desses arquivos
         */
        switch (unidadeAtual) {
            case 1:
                imgf1 = getClass().getResource("imagens/licao01/1.png");
                imgf2 = getClass().getResource("imagens/licao01/2.png");
                imgf3 = getClass().getResource("imagens/licao01/3.png");
                imgf4 = getClass().getResource("imagens/licao01/4.png");
                imgf5 = getClass().getResource("imagens/licao01/5.png");
                imgf6 = getClass().getResource("imagens/licao01/6.png");
                break;
            case 2:
                imgf1 = getClass().getResource("imagens/licao02/1.png");
                imgf2 = getClass().getResource("imagens/licao02/2.png");
                imgf3 = getClass().getResource("imagens/licao02/3.png");
                imgf4 = getClass().getResource("imagens/licao02/4.png");
                imgf5 = getClass().getResource("imagens/licao02/5.png");
                imgf6 = getClass().getResource("imagens/licao02/6.png");
                break;
            case 3:
                imgf1 = getClass().getResource("imagens/licao03a/1.png");
                imgf2 = getClass().getResource("imagens/licao03a/2.png");
                imgf3 = getClass().getResource("imagens/licao03a/3.png");
                imgf4 = getClass().getResource("imagens/licao03a/4.png");
                imgf5 = getClass().getResource("imagens/licao03a/5.png");
                imgf6 = getClass().getResource("imagens/licao03a/6.png");
                break;
            default:
                imgf1 = getClass().getResource("imagens/licao" + unidadeAtual + "c/1.png");
                imgf2 = getClass().getResource("imagens/licao" + unidadeAtual + "c/2.png");
                imgf3 = getClass().getResource("imagens/licao" + unidadeAtual + "c/3.png");
                imgf4 = getClass().getResource("imagens/licao" + unidadeAtual + "c/4.png");
                imgf5 = getClass().getResource("imagens/licao" + unidadeAtual + "c/5.png");
                imgf6 = getClass().getResource("imagens/licao" + unidadeAtual + "c/6.png");
                break;
        }
        /**
         * Seta as imagens nos imageViews
         */

        img1.setImage(new Image(imgf1.toString()));
        img2.setImage(new Image(imgf2.toString()));
        img3.setImage(new Image(imgf3.toString()));
        img4.setImage(new Image(imgf4.toString()));
        img5.setImage(new Image(imgf5.toString()));
        img6.setImage(new Image(imgf6.toString()));

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
     * Executa o áudio referente a cada imagem
     *
     * @param event mouse é passado por cima de uma das imagens
     */
    public void executarAudioImagem(MouseEvent event) {
        mCC.pararAudio();
        String idImagem = ((ImageView) event.getSource()).getId();
        String nomeImagem = idImagem.substring(3, 4);
        caminhoAudio = "audios/u" + unidadeAtual + "c/" + nomeImagem + ".mp3";
        mCC.play(caminhoAudio);
    }

    /**
     * Carrega a interface do manual do software
     *
     * @param event disparado pelo método
     * @param pagina pagina de onde o manual foi chamado
     * @throws IOException
     */
    public void abrirManual(ActionEvent event, int pagina, String subPagina) throws IOException {
        mCC.pararAudio();
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirManual(event, pagina, "c");
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
        mCC.abrirABC(event, pagina, "c");
    }

    //faz exibir a instrução da atividade atual na tela
    public void definirInstrucao(int unidadeAtual) throws MalformedURLException {
        String textoInstrucao = "";
        instrucao.setText("Clique nas imagens que tem o som " + listaInstrucao[unidadeAtual - 1]);

    }

    public void atualizarListView() {
        mCC.atualizarListView(listaPalavras, getUnidadeAtual());
    }

    public void sugestaoAtividade(ActionEvent event, int pagina) throws IOException {
        mCC.sugestaoAtividades(event, pagina,"c");
    }
}
