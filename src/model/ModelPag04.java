/**
 * Model da página 4
 */
package model;

import controller.Pag03Controller;
import controller.Pag03aController;
import controller.Pag04aController;
import controller.Pag05Controller;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author shadows
 */
public class ModelPag04 {

    //inteiro que armazena o valor da unidade atual
    private int unidadeAtual;
    //Janela ondo programa
    private Stage janela;

    //Labels utilizadas na pagina
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
    private Text instrucao;

    //String utilizada nos diretórios dos audios
    private String caminhoAudio;
    //arquivo de audio que deve ser criado
    private File arquivo;

    //label que terá seu conteúdo substituído
    private Label espaco;
    //classe com métodos com a mesma estrutura das outras classes
    private ModelClasseComum mCC;

    private ImageView imagemAudio;

    private File imagem;

    private AnchorPane janelaPrograma;
    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;
    private double newTranslateX;
    private double newTranslateY;
    private EventHandler<ActionEvent> primeiroAudio;
    private EventHandler<ActionEvent> segundoAudio;
    private URL imagemUrl;
    
    @FXML
    private ListView<String> listaPalavras;
    
    private String textoInstrucao[] ={"\"ÁRVORE\"","\"PIPOCA\"","\"BOTA\"","\"URUBU\"","\"VINHO\"","\"LUTA\""};
    
    /**
     * Construtor da classe Labels que são referenciadas do controlador:
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
     * @param instrucao1
     * @param listaPalavras
     */
    public ModelPag04(Label p1, Label p2, Label p3, Label p4, Label p5, Label f1,
            Label f2, Label espaco, ImageView imagemAudio, AnchorPane janelaPrograma, Text instrucao1, ListView<String> listaPalavras) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.f1 = f1;
        this.f2 = f2;
        this.espaco = espaco;
        this.instrucao = instrucao1;
        this.listaPalavras = listaPalavras;
        mCC = new ModelClasseComum(janela, this.listaPalavras);
        this.imagemAudio = imagemAudio;
        this.janelaPrograma = janelaPrograma;
        this.instrucao = instrucao1;
    }

    /**
     * Define a unidade em que o software se encontra
     *
     * @param unidadeAtual unidade atual da execução
     */
    public void setUnidadeAtual(int unidadeAtual) {
        this.unidadeAtual = unidadeAtual;
        //define o conteúdo das labels da pag 4
        URL imgUrl = null;
        String matrizSilabas[][] = {{"VA","VE","VI","VO","VU"},{"PA","PE","PI","PO","PU"},{"TA","TE","TI","TO","TU"},
            {"A","E","I","O","U"},{"VA","VE","VI","VO","VU"},{"LA","LE","LI","LO","LU"}};
        p1.setText(matrizSilabas[unidadeAtual-1][0]);
        p2.setText(matrizSilabas[unidadeAtual-1][1]);
        p3.setText(matrizSilabas[unidadeAtual-1][2]);
        p4.setText(matrizSilabas[unidadeAtual-1][3]);
        p5.setText(matrizSilabas[unidadeAtual-1][4]);
        switch (unidadeAtual) {
            case 1:                
                f1.setText("ÁR");
                f2.setText("RE");
                imgUrl = getClass().getResource("imagens/licao01/arvorepb.png");
                break;
            case 2:                
                f1.setText("PI");
                f2.setText("CA");
                imgUrl = getClass().getResource("imagens/licao02/poçopb.png");
                break;
            case 3:                
                f1.setText("BO");
                f2.setText("");
                imgUrl = getClass().getResource("imagens/licao03/botapb.png");
                break;
            case 4:               
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("RU");
                f2.setText("BU");
                imgUrl = getClass().getResource("imagens/licao4/urubupb.png");
                break;
            case 5:                
                f1.setText("");
                f2.setText("NHO");
                imgUrl = getClass().getResource("imagens/licao5/vinhopb.png");
                break;
            case 6:
                f1.setText("");
                f2.setText("VA");
                imgUrl = getClass().getResource("imagens/licao5/luvacor.png");
                break;
                
        }
        imagemAudio.setImage(new Image(imgUrl.toString()));
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
     * Carrega a próxima página na tela
     *
     * @param event disparado pelo método avancar do controller
     * @throws IOException
     */
    public void proximaPagina(ActionEvent event) throws IOException {
        if (getUnidadeAtual() == 3) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag04a.fxml"));

            //cria a próxima cena     
            Parent proximaCena = (Parent) fxmloader.load();
            Pag04aController pg04aCont = fxmloader.<Pag04aController>getController();
            pg04aCont.setUnidadeAtual(getUnidadeAtual());
            pg04aCont.setInstrucao(getUnidadeAtual());
            pg04aCont.tocarAudio();
            mCC.exibirCena(proximaCena, janela);
        } else {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag05.fxml"));

            //cria a próxima cena     
            Parent proximaCena = (Parent) fxmloader.load();
            Pag05Controller pg05Cont = fxmloader.<Pag05Controller>getController();
            pg05Cont.setUnidadeAtual(getUnidadeAtual());

            mCC.exibirCena(proximaCena, janela);
            pg05Cont.tocarAudio();
        }

    }

    /**
     * Carrega a página anterior
     *
     * @param event disparado pelo método voltar do controller
     * @throws IOException
     */
    public void paginaAnterior(ActionEvent event) throws IOException {
        if (getUnidadeAtual() == 3 || getUnidadeAtual() == 4) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag03a.fxml"));
            //cria a próxima cena     
            Parent proximaCena = (Parent) fxmloader.load();
            Pag03aController pg03aCont = fxmloader.<Pag03aController>getController();

            mCC.exibirCena(proximaCena, janela);
            pg03aCont.setUnidadeAtual(getUnidadeAtual());
            pg03aCont.audioInicial();
            pg03aCont.setImagens(getUnidadeAtual());
        } else {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag03.fxml"));
            //cria a próxima cena     
            Parent proximaCena = (Parent) fxmloader.load();
            Pag03Controller pg03Cont = fxmloader.<Pag03Controller>getController();

            mCC.exibirCena(proximaCena, janela);
            pg03Cont.setUnidadeAtual(getUnidadeAtual());
            pg03Cont.audioInicial();
            pg03Cont.setImagens(getUnidadeAtual());
        }

    }

    /**
     * Verifica se se a sílaba escolhida é a correta
     *
     * @param event mouse liberado
     * @return true ou false
     * @throws java.net.MalformedURLException
     */
    public boolean verificarEscolhaSilaba(MouseEvent event) throws MalformedURLException {
        //converte conteudo do evento para string
        String silabaEscolhida = ((Label) event.getSource()).getText();
        boolean opcaoCorreta = false;
        imagemUrl = null;
        //verifica a unidade atual
        switch (getUnidadeAtual()) {
            case 1:
                if (silabaEscolhida.equals("VO")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao01/arvorecor.png");                    
                }
                break;
            case 2:
                if (silabaEscolhida.equals("PO")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao02/pipocacor.png");                    
                }
                break;
            case 3:
                if (silabaEscolhida.equals("TA")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao03/botacor.png");                    
                }                
                break;
            case 4:
                if (silabaEscolhida.equals("U")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao4/urubucor.png");                   
                }
                break;
            case 5:
                if (silabaEscolhida.equals("VI")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao5/vinhocor.png");                    
                }
                break;
            case 6:
                if (silabaEscolhida.equals("LU")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao6/luvacor.png");                    
                }
                break;
            default:
                break;
        }
        imagemAudio.setImage(new Image(imagemUrl.toString()));
        if (opcaoCorreta) {
            p1.setDisable(true);
            p2.setDisable(true);
            p3.setDisable(true);
            p4.setDisable(true);
            p5.setDisable(true);
        }

        //retorna o valor booleano
        return opcaoCorreta;
    }

    /**
     * Audio executado automaticamente quando a interface é iniciada
     */
    public void tocarAudio() {
        //verifica a unidade atual
        switch (getUnidadeAtual()) {
            case 1:
                //diretório do audio
                caminhoAudio = "audios/u01/l1p4.mp3";
                break;
            case 2:
                caminhoAudio = "audios/u02/l2p4.mp3";
                break;
            case 3:
                caminhoAudio = "audios/u03/l3p4.mp3";
                break;
            default:
                caminhoAudio = "audios/u" + unidadeAtual + "/l" + unidadeAtual + "p4.mp3";
                break;
        }
        //executa o audio
        mCC.play(caminhoAudio);
    }

    /**
     * Para o audio em execução
     */
    public void pararAudio() {
        mCC.pararAudio();
    }

    /**
     * Altera o conteúdo da label espaço vazia
     *
     * @param evento
     */
    public void alterarLabelEspaco(MouseEvent evento) {
        //pega o conteúdo da label selecionada
        espaco.setText(((Label) evento.getSource()).getText());
        //esconde a label que havia sido selecionada
        ((Label) evento.getSource()).setVisible(false);
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
     * Executa a palavra completada
     */
    public void executarPalavra() {
        switch (getUnidadeAtual()) {
            case 1:
                caminhoAudio = "audios/u01/arvore.mp3";
                break;
            case 2:
                caminhoAudio = "audios/u02/pipoca.mp3";
                break;
            case 3:
                caminhoAudio = "audios/u03/palpg4.mp3";
                break;
            default:
                caminhoAudio = "audios/u" + unidadeAtual + "/palPag4.mp3";
                break;
        }
        mCC.play(caminhoAudio);
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
     * Carrega a interface do ABC
     *
     * @param event disparado pelo método ABCJanela do controller
     * @throws IOException
     */
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
     * Trata o evento de quando o mouse é pressionado
     *
     * @param event disparado quando uma das labels contendo as sílabas é
     * pressionada
     */
    public void mousePressionado(MouseEvent event) {
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
        orgTranslateX = ((Label) (event.getSource())).getTranslateX();
        orgTranslateY = ((Label) (event.getSource())).getTranslateY();
        janelaPrograma.setStyle("-fx-cursor: hand;");
    }

    /**
     * Trata o evento de quando a label pressionada é arrastada pela tela
     *
     * @param event disparado quando mouse é arrastado pela tela
     */
    public void mouseArrastado(MouseEvent event) {
        double offsetX = event.getSceneX() - orgSceneX;
        double offsetY = event.getSceneY() - orgSceneY;
        newTranslateX = orgTranslateX + offsetX;
        newTranslateY = orgTranslateY + offsetY;
        ((Label) (event.getSource())).setTranslateX(newTranslateX);
        ((Label) (event.getSource())).setTranslateY(newTranslateY);
        janelaPrograma.setStyle("-fx-cursor: move;");
    }

    /**
     * Trata o evento de quando o mouse, que estava pressionado, é solto
     *
     * @param event mouse é liberado (label é solta)
     * @throws MalformedURLException
     * @throws java.lang.InterruptedException
     */
    public void mouseLiberado(MouseEvent event) throws InterruptedException, MalformedURLException {
        if ((verificarColisao(event))) {
            //se for a opcao correta
            if (verificarEscolhaSilaba(event)) {
                alterarLabelEspaco(event);
                audioAcerto();

            } else {
                ((Label) (event.getSource())).setTranslateX(orgTranslateX);
                ((Label) (event.getSource())).setTranslateY(orgTranslateY);
                audioErro();
                //modelPag04.tocarAudioAcerto(false);
            }

        } else {
            ((Label) (event.getSource())).setTranslateX(orgTranslateX);
            ((Label) (event.getSource())).setTranslateY(orgTranslateY);
        }
        janelaPrograma.setStyle("-fx-cursor: none");
    }

    /**
     * Verifica se a label solta é a label correta que deveria ter sido
     * arrastada
     *
     * @param evento o botão do mouse é solto
     * @return true ou false
     */
    private boolean verificarColisao(MouseEvent evento) {
        return ((Label) (evento.getSource())).getBoundsInParent().intersects(espaco.getBoundsInParent());
    }

    private void tocarAudioParabens() throws InterruptedException {
        Random indiceParabens = new Random();
        int numeroAudio = indiceParabens.nextInt(3);
        caminhoAudio = "audios/acerto/" + numeroAudio + ".mp3";
        mCC.play(caminhoAudio);

    }

    public void audioAcerto() {

        //evento que represanta a ação do acerto
        primeiroAudio = (ActionEvent event) -> {
            try {
                tocarAudioParabens();
            } catch (InterruptedException ex) {
                Logger.getLogger(ModelPag04.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        //evento que representa o audio a ser executado depois o
        segundoAudio = (ActionEvent event) -> {
            executarPalavra();
        };
        new Timeline(
                new KeyFrame(Duration.seconds(0), primeiroAudio),
                new KeyFrame(Duration.seconds(3), segundoAudio)).play();
    }

    private void audioErro() {
        Random indiceErro = new Random();
        int numeroAudio = indiceErro.nextInt(3);
        caminhoAudio = "audios/erro/" + numeroAudio + ".mp3";
        mCC.play(caminhoAudio);
    }

    //faz exibir a instrução da atividade atual na tela
    public void definirInstrucao(int unidadeAtual) {        
        instrucao.setText("“Complete com a parte que está faltando: "+textoInstrucao[unidadeAtual-1]);        
    }
    public void atualizarListView() {
        mCC.atualizarListView(listaPalavras,getUnidadeAtual());
    }
}
