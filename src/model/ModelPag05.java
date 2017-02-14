/*
 * Model da página 5
 */
package model;

import controller.Pag04Controller;
import controller.Pag06Controller;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelPag05 {

    private Stage janela;
    private String unidadeAtual;
    //Declaração das labels utilizadas
    @FXML
    private Label p1;
    @FXML
    private Label p4;
    @FXML
    private Label p3;
    @FXML
    private Label p5;
    @FXML
    private Label p2;

    @FXML
    private Label f2;
    @FXML
    private Label f1;

    @FXML
    private Label espaco;
    //diretório do arquivo de audio
    private String caminhoAudio;
    private File arquivo;
    //classe com métodos com a mesma estrutura das outras classes
    private ModelClasseComum mCC;
    private File imagem;
    private ImageView imagemAudio;
    private AnchorPane janelaPrograma;
    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;
    private double newTranslateX;
    private double newTranslateY;

    /**
     * Construtor da classe Labels utilzadas nas paginas:
     *
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @param p5
     * @param f1
     * @param f2
     * @param espaco
     * @param imagemAudio
     * @param janelaPrograma
     */
    public ModelPag05(Label p1, Label p2, Label p3, Label p4, Label p5, Label f1, Label f2, Label espaco, ImageView imagemAudio, AnchorPane janelaPrograma) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.f1 = f1;
        this.f2 = f2;
        this.espaco = espaco;
        this.unidadeAtual = "u00";
        mCC = new ModelClasseComum(janela);
        this.imagemAudio = imagemAudio;
        this.janelaPrograma = janelaPrograma;
    }

    /**
     * Define a unidade atual e as labels referentes a essa pagina
     * @param unidadeAtual 
     */
    public void setUnidadeAtual(String unidadeAtual) throws MalformedURLException {
        this.unidadeAtual = unidadeAtual;
        switch (unidadeAtual) {
            case "u01":
                p1.setText("VA");
                p2.setText("VE");
                p3.setText("VI");
                p4.setText("VO");
                p5.setText("VU");
                f1.setText("POL");
                espaco.setText("");
                imagem = new File("src/imagens/licao01/polvopb.jpg");
                imagemAudio.setImage(new Image(imagem.toURI().toURL().toString()));
                break;
            default:
                break;
        }
    }

    /**
     * Carrega a próxima página na tela
     * @param event disparado pelo método avancar do controller
     * @throws IOException 
     */
    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag06.fxml"));
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag06Controller pg06Cont = fxmloader.<Pag06Controller>getController();
        pg06Cont.setUnidadeAtual(getUnidadeAtual());

        mCC.exibirCena(proximaCena, janela);
        pg06Cont.tocarAudio();
    }

    /**
     * Carrega a página anterior
     * @param event disparado pelo método voltar do controller
     * @throws IOException
     */
    public void paginaAnterior(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag04.fxml"));
        //cria a próxima cena
        Parent proximaCena = (Parent) fxmloader.load();
        Pag04Controller pg04Cont = fxmloader.<Pag04Controller>getController();

        mCC.exibirCena(proximaCena, janela);
        pg04Cont.setUnidadeAtual(getUnidadeAtual());
        pg04Cont.tocarAudio();
    }
   /**
     * Pega a unidade atual em execução
     * @return string com o valor da unidade atual
     */
    private String getUnidadeAtual() {
        return this.unidadeAtual;
    }

    /**
     * Verifica se a palavra escolhida é a correta
     *
     * @param event mouse liberado
     * @return true ou false
     */
    public boolean verificarEscolhaSilaba(MouseEvent event) throws MalformedURLException {
        String silabaEscolhida = ((Label) event.getSource()).getText();
        boolean opcaoCorreta = false;
        switch (getUnidadeAtual()) {
            case "u01":
                if (silabaEscolhida.equals("VO")) {
                    opcaoCorreta = true;
                    imagem = new File("src/imagens/licao01/polvocor.jpg");
                    imagemAudio.setImage(new Image(imagem.toURI().toURL().toString()));
                }
                break;
            default:
                break;
        }

        return opcaoCorreta;
    }

    /**
     * Executa o audio automatico quando a interface é iniciada
     */
    public void tocarAudio() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/l1p5.mp3";
                break;
            default:

                break;
        }
        mCC.play(caminhoAudio);
    }

    /**
     * Para o audio em execução
     */
    public void pararAudio() {
        mCC.pararAudio();
    }

    /**
     * Altera o valor do espaço
     *
     * @param evento
     */
    public void alterarLabelEspaco(MouseEvent evento) {

        espaco.setText(((Label) evento.getSource()).getText());
        ((Label) evento.getSource()).setVisible(false);
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
     * Executa a palavra completada
     */
    public void executarPalavra() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/polvo.mp3";
                break;
            default:
                break;
        }
        mCC.play(caminhoAudio);
    }
    /**
     * Abre o ABC do software
     * @param event botão "ABC" clicado
     * @param pagina pagina de onde o botão "ABC" foi clicado
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
    /**
     * Trata o evento em que o mouse é arrastado depois de ser pressionado
     * @param event mouse arrastado pela janela
     */
    
    public void mouseArrastado(MouseEvent event) {
        double offsetX = event.getSceneX() - orgSceneX;
        double offsetY = event.getSceneY() - orgSceneY;
        newTranslateX = orgTranslateX + offsetX;
        newTranslateY = orgTranslateY + offsetY;

        ((Label) (event.getSource())).setTranslateX(newTranslateX);
        ((Label) (event.getSource())).setTranslateY(newTranslateY);
        verificarColisao(event);
        janelaPrograma.setStyle("-fx-cursor: move;");
    }
    /**
     * Verifica se a label solta dentro do espaço em branco
     * @param evento label é solta
     * @return true se sim, do contrário false
     */
    
    public boolean verificarColisao(MouseEvent evento) {
        boolean colidiu = (((Label) (evento.getSource())).getBoundsInParent().intersects(espaco.getBoundsInParent()));
        return colidiu;
    }
    /**
     * Trata o evento de quando o mouse é liberado
     * @param event label é solta
     * @throws MalformedURLException 
     */
    public void mouseLiberado(MouseEvent event) throws MalformedURLException {
        if ((verificarColisao(event))) {
            //se for a opcao correta
            if (verificarEscolhaSilaba(event)) {
                alterarLabelEspaco(event);
                executarPalavra();
            } else {
                ((Label) (event.getSource())).setTranslateX(orgTranslateX);
                ((Label) (event.getSource())).setTranslateY(orgTranslateY);
                //modelPag05.tocarAudioAcerto(false);
            }

        } else {
            ((Label) (event.getSource())).setTranslateX(orgTranslateX);
            ((Label) (event.getSource())).setTranslateY(orgTranslateY);
        }
        janelaPrograma.setStyle("-fx-cursor: none;");
    }
    /**
     * Trata o evento de quando uma das labels é selecionada
     * @param event label pressionada
     */
    public void mousePressionado(MouseEvent event) {
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
        orgTranslateX = ((Label) (event.getSource())).getTranslateX();
        orgTranslateY = ((Label) (event.getSource())).getTranslateY();
        janelaPrograma.setStyle("-fx-cursor: hand;");
    }
}
