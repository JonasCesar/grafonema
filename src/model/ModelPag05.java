/*
 * Model da página 5
 */
package model;

import controller.Pag03aController;
import controller.Pag04Controller;
import controller.Pag04bController;
import controller.Pag05aController;
import controller.Pag06Controller;
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
public class ModelPag05 {

    private Stage janela;
    private int unidadeAtual;
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
    private EventHandler<ActionEvent> primeiroAudio;
    private EventHandler<ActionEvent> segundoAudio;
    private URL imagemUrl;

    @FXML
    private ListView<String> listaPalavras;

    @FXML
    private Text instrucao;

    @FXML
    private Label f3;
    //13/PI
    private String silabaCorreta[] = {"VO","PO","TÁ","VA","VI","LU","LA","BE","BO",
    "CA","BA","JÉ","PI","RO","FI","GOS","XO","GA","NE","DOS",
    "NOZ","RU","DU","SA","SU","MO","CU","JÁ","PU","SO","PER",
    "AR","FA","LA","LI","ME","CAR","CI","NO","BRA","ZES","VO",//SÓ FIZ ATÉ AQUI
    "VO","VO","VO","VO","VO","VO","VO","VO","VO","VO","VO",
    "VO","VO","VO","VO","VO","VO","VO","VO","VO","VO","VO",
    "VO","VO","VO","VO","VO","VO","VO","VO","VO","VO","VO",};
    
    private String matrizSilabas[][] = {{"VA", "VE", "VI", "VO", "VU"}, {"PA", "PE", "PI", "PO", "PU"}, {"TÁ", "TE", "TI", "TO", "TU"},
        {"VA", "VE", "VI", "VO", "VU"}, {"VA", "VE", "VI", "VO", "VU"}, {"LA", "LE", "LI", "LO", "LU"}, {"LA", "LE", "LI", "LO", "LU"},
        {"BA", "BE", "BI", "BO", "BU"}, {"BA", "BE", "BI", "BO", "BU"}, {"CA", "CE", "CI", "CO", "CU"}, {"BA", "BE", "BI", "BO", "BU"},
        {"JÁ", "JÉ", "JI", "JO", "JU"}, {"PA", "PE", "PI", "PO", "PU"}, {"RA", "RE", "RI", "RO", "RU"}, {"FA", "FE", "FI", "FO", "FU"}, 
        {"GAS", "GES", "GIS", "GOS", "GUS"}, {"XA", "XE", "XI", "XO", "XU"}, {"GA", "GE", "GI", "GO", "GU"}, {"NA", "NE", "NI", "NO", "NU"},
        {"DAS", "DES", "DIS", "DOS", "DUS"}, {"NAZ", "NEZ", "NIZ", "NOZ", "NUZ"}, {"RA", "RE", "RI", "RO", "RU"}, {"DA", "DE", "DI", "DO", "DU"},
        {"SA", "SE", "SI", "SO", "SU"}, {"SA", "SE", "SI", "SO", "SU"}, {"MA", "ME", "MI", "MO", "MU"}, {"CA", "CE", "CI", "CO", "CU"},
        {"JÁ", "JÉ", "JI", "JO", "JU"}, {"PA", "PE", "PI", "PO", "PU"}, {"SA", "SE", "SI", "SO", "SU"}, {"PAR", "PER", "PIR", "POR", "PUR"},
        {"AR", "ER", "IR", "OR", "UR"}, {"FA", "FE", "FI", "FO", "FU"}, {"LA", "LE", "LI", "LO", "LU"}, {"LA", "LE", "LI", "LO", "LU"},
        {"MA", "ME", "MI", "MO", "MU"}, {"CAR", "CER", "CIR", "COR", "CUR"}, {"CA", "CE", "CI", "CO", "CU"}, {"NA", "NE", "NI", "NO", "NU"},
        {"BRA", "BRE", "BRI", "BRO", "BRU"}, {"ZAS", "ZES", "ZIS", "ZOS", "ZUS"}};

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
     * @param instrucao1
     * @param listaPalavras
     * @param f3
     *
     */
    public ModelPag05(Label p1, Label p2, Label p3, Label p4, Label p5, Label f1,
            Label f2, Label espaco, ImageView imagemAudio, AnchorPane janelaPrograma,
            Text instrucao1, ListView<String> listaPalavras, Label f3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.espaco = espaco;
        this.unidadeAtual = 0;
        this.listaPalavras = listaPalavras;
        mCC = new ModelClasseComum(janela, this.listaPalavras);
        this.imagemAudio = imagemAudio;
        this.janelaPrograma = janelaPrograma;
        this.instrucao = instrucao1;

    }

    /**
     * Define a unidade atual e as labels referentes a essa pagina
     *
     * @param unidadeAtual
     */
    public void setUnidadeAtual(int unidadeAtual) {
        this.unidadeAtual = unidadeAtual;
        double xf1, yf1, xespaco, yespaco, z = 0;
        URL imgUrl = null;
        
        p1.setText(matrizSilabas[unidadeAtual - 1][0]);
        p2.setText(matrizSilabas[unidadeAtual - 1][1]);
        p3.setText(matrizSilabas[unidadeAtual - 1][2]);
        p4.setText(matrizSilabas[unidadeAtual - 1][3]);
        p5.setText(matrizSilabas[unidadeAtual - 1][4]);

        switch (unidadeAtual) {
            case 1:
                f1.setText("POL");
                espaco.setText("");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao01/polvopb.png");
                imagemAudio.setImage(new Image(imgUrl.toString()));
                break;
            case 2:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("ÇO");
                f2.setVisible(false);
                f3.setVisible(false);
                espaco.setText("");
                imgUrl = getClass().getResource("imagens/licao02/poçopb.png");
                imagemAudio.setImage(new Image(imgUrl.toString()));
                break;
            case 3:
                f1.setId("pg5f1_3");//sílaba com 3 letras
                espaco.setId("pg5espaco");
                f1.setText("BUA");
                f2.setVisible(false);
                f3.setVisible(false);
                espaco.setText("");
                imgUrl = getClass().getResource("imagens/licao03/tabuapb.png");
                imagemAudio.setImage(new Image(imgUrl.toString()));
                break;
            case 4:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("GA");
                f2.setText("LU");
                f3.setText("ME");
                espaco.setText("");
                imgUrl = getClass().getResource("imagens/licao4a/vagalumepb.png");
                imagemAudio.setImage(new Image(imgUrl.toString()));
                break;
            case 5:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("O");
                f2.setText("LE");
                f3.setText("TA");
                espaco.setText("");
                imgUrl = getClass().getResource("imagens/licao5/violetapb.png");
                imagemAudio.setImage(new Image(imgUrl.toString()));
                break;
            case 6:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("NE");
                f2.setText("TA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao6/lunetapb.jpg");
                break;
            case 7:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("GO");
                f2.setText("A");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao7/lagoapb.png");
                break;
            case 8:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("BI");
                f2.setText("DA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao8/bebidapb.png");
                break;
            case 9:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("TÃO");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao9/botaopb.png");
                break;
            case 10:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("RA");
                f2.setText("COL");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao10/CA5pb.png");                
                break;
            case 11:
                
                f1.setText("A");
                f2.setText("CA");
                f3.setText("XI");
                imgUrl = getClass().getResource("imagens/licao11/BA5pb.png");
                break;
            case 12:
                f1.setText("PA");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao12/JÉ5pb.png");
                break;
            case 13:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("PO");
                f2.setText("CA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao13/PI5pb.png");                
                break;
            case 14:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("DO");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/RO5pb.png");
                break;
            case 15:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("GO");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/FI5pb.png");
                break;
            case 16:                
                f1.setText("PRE");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/GOS5pb.png");
                break;
            case 17:
                f1.setText("CAI");
                f2.setText("TE");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/XO5pb.png");
                break;
            case 18:
                f1.setVisible(false);
                f2.setText("TO");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/GA5pb.png");
                break;
            case 19:
                f1.setText("COR");
                f2.setText("TA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/NE5pb.png");
                break;
            case 20:
                f3.setId("pg5f3Esquerda");
                espaco.setId("pg5espacoDireita");
                f1.setText("CAL");
                f2.setText("ÇA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/DOS5pb.png");
                break;
            case 21:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("-MOS");
                f2.setText("CA");
                f3.setText("DA");
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/NOZ5pb.png");
                break;
            case 22:
                f1.setText("PE");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/RU5pb.png");
                break;
            case 23:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("ZEN");
                f2.setText("TOS");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/DU5pb.png");
                break;
            case 24:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("PO");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/SA5pb.png");
                break;
            case 25:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("JO");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/SU5pb.png");
                break;
            case 26:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("DE");
                f2.setText("LO");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/MO5pb.png");
                break;
            case 27:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("RU");
                f2.setText("RU");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/CU5pb.png");
                break;
            case 28:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("BU");
                f2.setText("TI");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/JA5pb.png");
                break;
            case 29:
                f1.setId("pg5f1");
                espaco.setId("pg5espaco");
                f1.setText("DIM");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao"+unidadeAtual+"/PU5pb.png");
                break;
        }
        imagemAudio.setImage(new Image(imgUrl.toString()));
    }

    /**
     * Carrega a próxima página na tela
     *
     * @param event disparado pelo método avancar do controller
     * @throws IOException
     */
    public void proximaPagina(ActionEvent event) throws IOException {
        int u = getUnidadeAtual();
        if (u == 3 || u == 9 || u == 12 || u == 13 || u == 18 || u == 25 || u == 26) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag05a.fxml"));
            //cria a próxima cena chamando a inteface dos avatares        
            Parent proximaCena = (Parent) fxmloader.load();
            Pag05aController pg05aCont = fxmloader.<Pag05aController>getController();
            pg05aCont.setUnidadeAtual(getUnidadeAtual());
            f1.setId("f1");
            espaco.setId("espaco");
            mCC.exibirCena(proximaCena, janela);
            pg05aCont.tocarAudio();
            pg05aCont.setInstrucao(getUnidadeAtual());
        } else {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag06.fxml"));
            //cria a próxima cena chamando a inteface dos avatares        
            Parent proximaCena = (Parent) fxmloader.load();
            Pag06Controller pg06Cont = fxmloader.<Pag06Controller>getController();
            pg06Cont.setUnidadeAtual(getUnidadeAtual());
            f1.setId("f1");
            espaco.setId("espaco");
            mCC.exibirCena(proximaCena, janela);
            pg06Cont.tocarAudio();
        }

    }

    /**
     * Carrega a página anterior
     *
     * @param event disparado pelo método voltar do controller
     * @throws IOException
     */
    public void paginaAnterior(ActionEvent event) throws IOException {
        int u = getUnidadeAtual();
        if (getUnidadeAtual() == 3) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag03a.fxml"));
            //cria a próxima cena
            Parent proximaCena = (Parent) fxmloader.load();
            Pag03aController pg03aCont = fxmloader.<Pag03aController>getController();

            mCC.exibirCena(proximaCena, janela);
            pg03aCont.setUnidadeAtual(getUnidadeAtual());
            pg03aCont.setImagens(getUnidadeAtual());
            pg03aCont.setInstrucao(getUnidadeAtual());
        } else if (u == 9) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag04b.fxml"));
            //cria a próxima cena
            Parent proximaCena = (Parent) fxmloader.load();
            Pag04bController pg04bCont = fxmloader.<Pag04bController>getController();

            mCC.exibirCena(proximaCena, janela);
            pg04bCont.setUnidadeAtual(getUnidadeAtual());

        } else if(u == 25){
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag04d.fxml"));
            //cria a próxima cena
            Parent proximaCena = (Parent) fxmloader.load();
            Pag04bController pg04bCont = fxmloader.<Pag04bController>getController();

            mCC.exibirCena(proximaCena, janela);
            pg04bCont.setUnidadeAtual(getUnidadeAtual());
        }
        else {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag04.fxml"));
            //cria a próxima cena
            Parent proximaCena = (Parent) fxmloader.load();
            Pag04Controller pg04Cont = fxmloader.<Pag04Controller>getController();

            mCC.exibirCena(proximaCena, janela);
            pg04Cont.setUnidadeAtual(getUnidadeAtual());
            pg04Cont.tocarAudio();
        }

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
     * Verifica se a palavra escolhida é a correta
     *
     * @param event mouse liberado
     * @return true ou false
     */
    public boolean verificarEscolhaSilaba(MouseEvent event) {
        String silabaEscolhida = ((Label) event.getSource()).getText();
        imagemUrl = null;
        boolean opcaoCorreta = false;
        switch (getUnidadeAtual()) {
            case 1:
                if (silabaEscolhida.equals("VO")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao01/polvocor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 2:
                if (silabaEscolhida.equals("PO")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao02/poçocor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 3:
                if (silabaEscolhida.equals("TÁ")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao03/tabuacor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 4:
                if (silabaEscolhida.equals("VA")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao4a/vagalumecor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 5:
                if (silabaEscolhida.equals("VI")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao5/violetacor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 6:
                if (silabaEscolhida.equals("LU")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao6/lunetacor.jpg");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 7:
                if (silabaEscolhida.equals("LA")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao7/lagoacor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 8:
                if (silabaEscolhida.equals("BE")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao8/bebidacor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 9:
                if (silabaEscolhida.equals("BO")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao9/botaocor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            default:
                String fonema = silabaCorreta[getUnidadeAtual()-1];
                if (silabaEscolhida.equals(fonema)) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao"+getUnidadeAtual()+"/"+fonema+"5cor.png");
                }
                break;
        }
        return opcaoCorreta;
    }

    /**
     * Executa o audio automatico quando a interface é iniciada
     */
    public void tocarAudio() {
        switch (getUnidadeAtual()) {
            case 1:
                caminhoAudio = "audios/u01/l1p5.mp3";
                break;
            case 2:
                caminhoAudio = "audios/u02/l2p5.mp3";
                break;
            case 3:
                caminhoAudio = "audios/u03/l3p5.mp3";
                break;
            default:
                caminhoAudio = "audios/u" + unidadeAtual + "/l" + unidadeAtual + "p5.mp3";
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
                caminhoAudio = "audios/u01/polvo.mp3";
                break;
            case 2:
                caminhoAudio = "audios/u02/poço.mp3";
                break;
            case 3:
                caminhoAudio = "audios/u03/palPag5.mp3";
                break;
            default:
                caminhoAudio = "audios/u" + unidadeAtual + "/palPg5.mp3";
                break;
        }
        mCC.play(caminhoAudio);
    }

    /**
     * Abre o ABC do software
     *
     * @param event botão "ABC" clicado
     * @param pagina pagina de onde o botão "ABC" foi clicado
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
     * Trata o evento em que o mouse é arrastado depois de ser pressionado
     *
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
     *
     * @param evento label é solta
     * @return true se sim, do contrário false
     */
    public boolean verificarColisao(MouseEvent evento) {
        boolean colidiu = (((Label) (evento.getSource())).getBoundsInParent().intersects(espaco.getBoundsInParent()));
        return colidiu;
    }

    /**
     * Trata o evento de quando o mouse é liberado
     *
     * @param event label é solta
     * @throws MalformedURLException
     */
    public void mouseLiberado(MouseEvent event) throws MalformedURLException {
        if ((verificarColisao(event))) {
            //se for a opcao correta
            if (verificarEscolhaSilaba(event)) {
                alterarLabelEspaco(event);
                audioAcerto();
            } else {
                ((Label) (event.getSource())).setTranslateX(orgTranslateX);
                ((Label) (event.getSource())).setTranslateY(orgTranslateY);
                audioErro();
            }

        } else {
            ((Label) (event.getSource())).setTranslateX(orgTranslateX);
            ((Label) (event.getSource())).setTranslateY(orgTranslateY);

        }
        janelaPrograma.setStyle("-fx-cursor: none;");
    }

    /**
     * Trata o evento de quando uma das labels é selecionada
     *
     * @param event label pressionada
     */
    public void mousePressionado(MouseEvent event) {
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
        orgTranslateX = ((Label) (event.getSource())).getTranslateX();
        orgTranslateY = ((Label) (event.getSource())).getTranslateY();
        janelaPrograma.setStyle("-fx-cursor: hand;");
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

    public void definirInstrucao(int unidadeAtual) {
        String textoInstrucao[] = {"\"POLVO\"", "\"POÇO\"", "\"TÁBUA\"", "\"VAGALUME\"", 
            "\"VIOLETA\"", "\"LUNETA\"", "\"LAGOA\"", "\"BEBIDA\"", "\"BOTÃO\"","\"CARACOL\"",
            "\"ABACAXI\"","\"PAJÉ\"","\"PIPOCA\"","\"RODO\"","\"FIGO\"","\"PREGOS\"","\"CAIXOTE\"","\"GATO\"",
            "\"CORNETA\"", "\"CALÇADOS\"", "\"BOVINOS\"", "\"PERU\"","\"DUZENTOS\"","\"SAPO\"","\"SUJO\"",
            "\"MODELO\"","\"CURURU\"","\"JABUTI\"","\"PUDIM\"","\"\""};
        instrucao.setText("Complete com a parte que está faltando: " + textoInstrucao[unidadeAtual - 1]);
    }

    public void atualizarListView() {
        mCC.atualizarListView(listaPalavras, getUnidadeAtual());
    }
}
