/**
 * Model da página 4
 */

package model;

import controller.Pag03Controller;
import controller.Pag05Controller;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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

    //String que armazena o valor da unidade atual
    private String unidadeAtual;
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
     */
    public ModelPag04(Label p1, Label p2, Label p3, Label p4, Label p5, Label f1,
            Label f2, Label espaco, ImageView imagemAudio, AnchorPane janelaPrograma, Text instrucao1) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.f1 = f1;
        this.f2 = f2;
        this.espaco = espaco;
        this.instrucao = instrucao1;
        mCC = new ModelClasseComum(janela);
        this.imagemAudio = imagemAudio;
        this.janelaPrograma = janelaPrograma;
        this.instrucao = instrucao1;
    }

    /**
     *Define a unidade em que o software se encontra
     * @param unidadeAtual unidade atual da execução
     */
    public void setUnidadeAtual(String unidadeAtual) throws MalformedURLException {
        this.unidadeAtual = unidadeAtual;
        //define o conteúdo das labels da pag 4
         
        switch (unidadeAtual) {
            case "u01":
                p1.setText("VA");
                p2.setText("VE");
                p3.setText("VI");
                p4.setText("VO");
                p5.setText("VU");
                f1.setText("ÁR");
                f2.setText("RE");
                imagem = new File("src/imagens/licao01/arvorepb.jpg");
                break;
            default:
                break;
        }
        imagemAudio.setImage(new Image(imagem.toURI().toURL().toString()));
    }

    /**
     * Pega a unidade atual em execução
     * @return string com o valor da unidade atual
     */
    public String getUnidadeAtual() {
        return this.unidadeAtual;
    }

    /**
     * Carrega a próxima página na tela
     * @param event disparado pelo método avancar do controller
     * @throws IOException 
     */
    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag05.fxml"));

        //cria a próxima cena     
        Parent proximaCena = (Parent) fxmloader.load();
        Pag05Controller pg05Cont = fxmloader.<Pag05Controller>getController();
        pg05Cont.setUnidadeAtual(getUnidadeAtual());

        mCC.exibirCena(proximaCena, janela);
        pg05Cont.tocarAudio();
    }

    /**
     * Carrega a página anterior
     * @param event disparado pelo método voltar do controller
     * @throws IOException
     */
    public void paginaAnterior(ActionEvent event) throws IOException {
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
        //verifica a unidade atual
        switch (getUnidadeAtual()) {
            case "u01":
                if (silabaEscolhida.equals("VO")) {
                    opcaoCorreta = true;
                    imagem = new File("src/imagens/licao01/arvorecor.jpg");
                    imagemAudio.setImage(new Image(imagem.toURI().toURL().toString()));
                }
                break;
            default:
                break;
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
            case "u01":
                //diretório do audio
                caminhoAudio = "src/audios/u01/l1p4.mp3";
                break;
            default:
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
                caminhoAudio = "src/audios/u01/arvore.mp3";
                break;
            default:
                break;
        }
        mCC.play(caminhoAudio);
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
    /**
     * Trata o evento de quando o mouse é pressionado
     * @param event disparado quando uma das labels contendo as sílabas é pressionada
     */
    public void mousePressionado(MouseEvent event) {
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
        orgTranslateX = ((Label) (event.getSource())).getTranslateX();
        orgTranslateY = ((Label) (event.getSource())).getTranslateY();
        janelaPrograma.setStyle("-fx-cursor: hand;");
    }
    /**
     * Trata o evento de quando a label pressionada é
     * arrastada pela tela
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
     * @param event mouse é liberado (label é solta)
     * @throws MalformedURLException 
     * @throws java.lang.InterruptedException 
     */
    public void mouseLiberado(MouseEvent event) throws MalformedURLException, InterruptedException {
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
        caminhoAudio = "src/audios/acerto/"+numeroAudio+".mp3";
        mCC.play(caminhoAudio);
        
    }
    
    public void audioAcerto(){
        
        //evento que represanta a ação do acerto
        primeiroAudio = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    tocarAudioParabens();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ModelPag04.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        //evento que representa o audio a ser executado depois o
        segundoAudio = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               executarPalavra();
            }
        };
        new Timeline(
                new KeyFrame(Duration.seconds(0), primeiroAudio),
                new KeyFrame(Duration.seconds(3), segundoAudio)).play();
    }

    private void audioErro() {
        Random indiceErro = new Random();
        int numeroAudio = indiceErro.nextInt(3);
        caminhoAudio = "src/audios/erro/"+numeroAudio+".mp3";
        mCC.play(caminhoAudio);
    }
    
    //faz exibir a instrução da atividade atual na tela
    public void definirInstrucao(String unidadeAtual) throws MalformedURLException {

        switch (unidadeAtual) {

            case "u01":
                instrucao.setText("“Complete com a parte que está faltando: \"ÁRVORE\"");
            break;
        }

    }
}
