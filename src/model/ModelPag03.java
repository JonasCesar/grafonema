/**
 * Model da página 3
 */
package model;

import controller.Pag02Controller;
import controller.Pag04Controller;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelPag03 {

    private String unidadeAtual, caminhoAudio;
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

    private File f1, f2, f3, f4, f5, f6;
    private Stage janela;
    private ModelClasseComum mCC;

    public ModelPag03(ImageView i1, ImageView i2, ImageView i3, ImageView i4, ImageView i5, ImageView i6) {
        this.img1 = i1;
        this.img2 = i2;
        this.img3 = i3;
        this.img4 = i4;
        this.img5 = i5;
        this.img6 = i6;

        unidadeAtual = "u00";
        caminhoAudio = "";
        mCC = new ModelClasseComum(janela);        
    }
/**
     *Define a unidade em que o software se encontra
     * @param unidadeAtual unidade atual da execução
     */
    public void setUnidadeAtual(String unidade) {
        this.unidadeAtual = unidade;
    }
    /**
     * Pega a unidade atual em execução
     * @return string com o valor da unidade atual
     */
    public String getUnidadeAtual() {
        return unidadeAtual;
    }
/**
     * Carrega a página anterior
     * @param event disparado pelo método voltar do controller
     * @throws IOException
     */
    public void paginaAnterior(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag02.fxml"));

        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag02Controller pag02Cont = fxmloader.<Pag02Controller>getController();

        mCC.exibirCena(proximaCena, janela);
        pag02Cont.setUnidadeAtual(getUnidadeAtual());

    }

    
    /**
     * Carrega a próxima página na tela
     * @param event disparado pelo método avancar do controller
     * @throws IOException 
     */
    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag04.fxml"));

        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag04Controller pg04Cont = fxmloader.<Pag04Controller>getController();
        pg04Cont.setUnidadeAtual(getUnidadeAtual());

        mCC.exibirCena(proximaCena, janela);
        pg04Cont.tocarAudio();

    }

    public void verificarImagem(MouseEvent event) throws MalformedURLException {
        String imgClicada = ((ImageView) event.getSource()).getId();
        String nomeImagem = imgClicada.substring(3, 4);

        switch (getUnidadeAtual()) {
            //acertos img1, img3, img5
            case "u01":
                if (imgClicada.equals("img1") || imgClicada.equals("img3") || imgClicada.equals("img5")) {
                    f1 = new File("src/imagens/licao01/" + nomeImagem + "c.jpg");
                    ((ImageView) event.getSource()).setImage(new Image(f1.toURI().toURL().toString()));
                } else {
                    f1 = new File("src/imagens/licao01/" + nomeImagem + "e.jpg");
                    ((ImageView) event.getSource()).setImage(new Image(f1.toURI().toURL().toString()));
                }
                break;
            default:
                break;
        }
    }

    public void tocarAudioInicial() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/l1p3.mp3";
                break;
            default:
                break;
        }

        mCC.play(caminhoAudio);
    }

    public void definirImagens(String unidadeAtual) throws MalformedURLException {
        String caminho = "";
        /**
         * Criar arquivos que chamam as imagens e pegar a URL desses arquivos
         */
        switch (unidadeAtual) {
            case "u01":
                f1 = new File("src/imagens/licao01/1.jpg");
                f2 = new File("src/imagens/licao01/2.jpg");
                f3 = new File("src/imagens/licao01/3.jpg");
                f4 = new File("src/imagens/licao01/4.jpg");
                f5 = new File("src/imagens/licao01/5.jpg");
                f6 = new File("src/imagens/licao01/6.jpg");

                break;
            default:
                break;
        }
        img1.setImage(new Image(f1.toURI().toURL().toString()));
        img2.setImage(new Image(f2.toURI().toURL().toString()));
        img3.setImage(new Image(f3.toURI().toURL().toString()));
        img4.setImage(new Image(f4.toURI().toURL().toString()));
        img5.setImage(new Image(f5.toURI().toURL().toString()));
        img6.setImage(new Image(f6.toURI().toURL().toString()));

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

    public void executarAudioImagem(MouseEvent event) {
        String idImagem = ((ImageView) event.getSource()).getId();
        String nomeImagem = idImagem.substring(3, 4);
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/" + nomeImagem + ".mp3";
                break;
        }
        mCC.play(caminhoAudio);
        System.out.println("Tocar " + caminhoAudio);
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
/**
     * Carrega a interface do ABC
     * @param event disparado pelo método ABCJanela do controller
     * @throws IOException 
     */
    

    public void abrirABC(ActionEvent event, int pagina) throws IOException {
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirABC(event, pagina);
    }
}
