/**
 * Model da página 4
 */
package model;

import controller.Pag03Controller;
import controller.Pag03aController;
import controller.Pag03bController;
import controller.Pag03cController;
import controller.Pag03dController;
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
    private Label f3;

    @FXML
    private ListView<String> listaPalavras;
    //41
    private String textoInstrucao[] = {"\"ÁRVORE\"", "\"PIPOCA\"", "\"BOTA\"", "\"URUBU\"",
        "\"VINHO\"", "\"LUVA\"", "\"LAÇO\"", "\"CABELO\"", "\"BONECA\"", "\"CARANGUEIJO\"",
        "\"BAÚ\"", "\"HOMEM\"", "\"PIRANHA\"", "\"FUBÁ\"", "\"FILHO\"", "\"JOANINHA\"", "\"LIXO\"",
        "\"GAFANHOTO\"", "\"CORNETA\"", "\"DEGRAU\"", "\"MÚSICA\"", "\"RUGAS\"", "\"ASNO\"",
        "\"ESFERA\"", "\"SETA\"", "\"ÁRVORE\"", "\"CURATIVO\"", "\"JACARÉ\"", "\"PUXAR\"",
        "\"SOLA\"", "\"PERNIL\"", "\"ARBUSTO\"", "\"FADA\"", "\"LARANJA\"", "\"LIMA\"",
        "\"MEDO\"", "\"CARTA\"", "\"BICO\"", "\"NOVE\"", "\"BRAVO\"", "\"RAPAZES\"", "\"DAMASCO\"",
        "\"PIPA\"", "\"CAJU\"", "\"PALHAÇO\"", "\"BULE\"", "\"GAVIÃO\"", "\"EXPORTAR\"", "\"COMPRAS\"",
        "\"TREZENTOS\"", "\"GUARANÁ\"", "\"LEMBRANÇAS\"", "\"ALFACE\"", "\"PRÍNCIPE\"", "\"PRÓTESE\"",
        "\"CRIMINOSO\"", "\"CORRER\"", "\"TEMPORAL\"", "\"FLOCOS\"", "\"MARMITA\""};

    private String silabaCorreta[] = {"VO", "PO", "TA", "U", "VI", "LU", "LA", "BE", "BO",
        "CA", "BA", "HO", "PI", "FU", "FI", "JO", "XO", "GA", "NE", "DE", "SI", "RU", "AS", "ES",
        "SE", "VO", "CU", "JA", "PU", "SO", "PER", "AR", "FA", "LA", "LI", "ME", "CAR", "BI", "NO", "BRA",
        "ZES", "DA", "PI", "JU", "PA", "LE", "GA", "EX", "COM", "TRE", "GUA", "LEM", "AL", "PRÍN", "PRÓ", "CRI",
        "COR", "TEM", "FLO", "MAR"};

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
     * @param f3
     */
    public ModelPag04(Label p1, Label p2, Label p3, Label p4, Label p5, Label f1,
            Label f2, Label espaco, ImageView imagemAudio, AnchorPane janelaPrograma,
            Text instrucao1, ListView<String> listaPalavras, Label f3) {
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
        this.f3 = f3;
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
        String matrizSilabas[][] = {{"VA", "VE", "VI", "VO", "VU"}, {"PA", "PE", "PI", "PO", "PU"}, {"TA", "TE", "TI", "TO", "TU"},
        {"A", "E", "I", "O", "U"}, {"VA", "VE", "VI", "VO", "VU"}, {"LA", "LE", "LI", "LO", "LU"}, {"LA", "LE", "LI", "LO", "LU"},
        {"BA", "BE", "BI", "BO", "BU"}, {"BA", "BE", "BI", "BO", "BU"}, {"CA", "CE", "CI", "CO", "CU"}, {"BA", "BE", "BI", "BO", "BU"},
        {"HA", "HE", "HI", "HO", "HU"}, {"PA", "PE", "PI", "PO", "PU"}, {"FA", "FE", "FI", "FO", "FU"}, {"FA", "FE", "FI", "FO", "FU"},
        {"JA", "JE", "JI", "JO", "JU"}, {"XA", "XE", "XI", "XO", "XU"}, {"GA", "GE", "GI", "GO", "GU"}, {"NA", "NE", "NI", "NO", "NU"},
        {"DA", "DE", "DI", "DO", "DU"}, {"SA", "SE", "SI", "SO", "SU"}, {"RA", "RE", "RI", "RO", "RU"}, {"AS", "ES", "IS", "OS", "US"},
        {"AS", "ES", "IS", "OS", "US"}, {"SA", "SE", "SI", "SO", "SU"}, {"VA", "VE", "VI", "VO", "VU"}, {"CA", "CE", "CI", "CO", "CU"},
        {"JA", "JE", "JI", "JO", "JU"}, {"PA", "PE", "PI", "PO", "PU"}, {"SA", "SE", "SI", "SO", "SU"}, {"PAR", "PER", "PIR", "POR", "PUR"},
        {"AR", "ER", "IR", "OR", "UR"}, {"FA", "FE", "FI", "FO", "FU"}, {"LA", "LE", "LI", "LO", "LU"}, {"LA", "LE", "LI", "LO", "LU"},
        {"MA", "ME", "MI", "MO", "MU"}, {"CAR", "CER", "CIR", "COR", "CUR"}, {"BA", "BE", "BI", "BO", "BU"}, {"NA", "NE", "NI", "NO", "NU"},
        {"BRA", "BRE", "BRI", "BRO", "BRU"}, {"ZAS", "ZES", "ZIS", "ZOS", "ZUS"}, {"DA", "DE", "DI", "DO", "DU"}, {"PA", "PE", "PI", "PO", "PU"},
        {"JA", "JE", "JI", "JO", "JU"}, {"PA", "PE", "PI", "PO", "PU"}, {"LA", "LE", "LI", "LO", "LU"}, {"GA", "GE", "GI", "GO", "GU"}, {"AX", "EX", "IX", "OX", "UX"}, {"CAM", "CEM", "CIM", "COM", "CUM"},
        {"TRA", "TRE", "TRI", "TRO", "TRU"}, {"GUA", "GUE", "GUI", "GUO", ""}, {"LAM", "LEM", "LIM", "LOM", "LUM"}, {"AL", "EL", "IL", "OL", "UL"},
        {"PRAN", "PREN", "PRÍN", "PRON", "PRUN"}, {"PRA", "PRE", "PRI", "PRÓ", "PRU"}, {"CRA", "CRE", "CRI", "CRO", "CRU"}, {"CAR", "CER", "CIR", "COR", "CUR"},
        {"TAM", "TEM", "TIM", "TOM", "TUM"}, {"FLA", "FLE", "FLI", "FLO", "FLU"}, {"MAR", "MER", "MIR", "MOR", "MUR"}};
        p1.setText(matrizSilabas[unidadeAtual - 1][0]);
        p2.setText(matrizSilabas[unidadeAtual - 1][1]);
        p3.setText(matrizSilabas[unidadeAtual - 1][2]);
        p4.setText(matrizSilabas[unidadeAtual - 1][3]);
        p5.setText(matrizSilabas[unidadeAtual - 1][4]);
        switch (unidadeAtual) {
            case 1:
                espaco.setText("- -");
                f1.setText("ÁR");
                f2.setText("RE");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao01/arvorepb.png");
                break;
            case 2:
                espaco.setText("- -");
                f1.setText("PI");
                f2.setText("CA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao02/pipocapb.png");
                break;
            case 3:
                espaco.setText("- -");
                f1.setText("BO");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao03/botapb.png");
                break;
            case 4:
                espaco.setText("- -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("RU");
                f2.setText("BU");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao4/urubupb.png");
                break;
            case 5:
                espaco.setText("- -");
                f1.setVisible(false);
                f2.setText("NHO");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao5/vinhopb.png");
                break;
            case 6:
                espaco.setText("- -");
                f1.setVisible(false);
                f2.setText("VA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao6/luvapb.png");
                break;
            case 7:
                espaco.setText("- -");
                f1.setVisible(false);
                f2.setText("ÇO");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao7/lacopb.png");
                break;
            case 8:
                espaco.setText("- -");
                f1.setText("CA");
                f2.setText("LO");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao8/cabelopb.png");
                break;
            case 9:
                espaco.setText("- -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("NE");
                f2.setText("CA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao9/bonecapb.png");
                break;
            case 10:
                espaco.setText("- -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("RAN");
                f2.setText("GUEI");
                f3.setText("JO");
                imgUrl = getClass().getResource("imagens/licao10/CA4pb.png");
                break;
            case 11:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("Ú");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao11/BA4pb.png");
                break;
            case 12:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("MEM");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao12/HO4pb.png");
                break;
            case 13:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("RA");
                f2.setText("NHA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao13/PI4pb.png");
                break;
            case 14:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("BÁ");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao14/FU4pb.png");
                break;
            case 15:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("LHO");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao15/FI4pb.png");
                break;
            case 16:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("A");
                f2.setText("NI");
                f3.setText("NHA");
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/JO4pb.png");
                break;
            case 17:
                espaco.setText("- -");
                f1.setText("LI");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/XO4pb.png");
                break;
            case 18:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("FA");
                f2.setText("NHO");
                f3.setText("TO");
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/GA4pb.png");
                break;
            case 19:
                espaco.setText("- -");
                f1.setText("JA");
                f2.setText("LA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/NE4pb.png");
                break;
            case 20:
                espaco.setText("- -");
                f1.setId("pg4f1_4");
                espaco.setId("pg4espaco");
                f1.setText("GRAU");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/DE4pb.png");
                break;
            case 21:
                espaco.setText("- -");
                f1.setText("MÚ");
                f2.setText("CA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/SI4pb.png");
                break;
            case 22:
                espaco.setText("- -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("GAS");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/RU4pb.png");
                break;
            case 23:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("NO");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/AS4pb.png");
                break;
            case 24:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("PE");
                f2.setText("RA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/ES4pb.png");
                break;
            case 25:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("TA");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/SE4pb.png");
                break;
            case 26:
                espaco.setText("- -");
                f1.setText("ÁR");
                f2.setText("RE");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/VO4pb.png");
                break;
            case 27:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("RA");
                f2.setText("TI");
                f3.setText("VO");
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/CU4pb.png");
                break;
            case 28:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("CA");
                f2.setText("RÉ");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/JA4pb.png");
                break;
            case 29:
                espaco.setText("- -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("XAR");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/PU4pb.png");
                break;
            case 30:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("LA");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/SO4pb.png");
                break;
            case 31:
                espaco.setText("- -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("NIL");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/PER4pb.png");
                break;
            case 32:
                espaco.setText("- -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("BUS");
                f2.setText("TO");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/AR4pb.png");
                break;
            case 33:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("DA");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/FA4pb.png");
                break;
            case 34:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("RAN");
                f2.setText("JA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/LA4pb.png");
                break;
            case 35:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("MA");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/LI4pb.png");
                break;
            case 36:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("DO");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/ME4pb.png");
                break;
            case 37:
                espaco.setText("- - -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("TA");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/CAR4pb.png");
                break;
            case 38:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("CO");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/BI4pb.png");
                break;
            case 39:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("VE");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/NO4pb.png");
                break;
            case 40:
                espaco.setText("- - -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("VO");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/BRA4pb.png");
                break;
            case 41:
                espaco.setText("- - -");
                f2.setId("pg4f2Esquerda_3");
                espaco.setId("pg4espacoDireita_3");
                f1.setText("RA");
                f2.setText("PA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/ZES4pb.png");
                break;
            case 42:
                espaco.setText("- -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("MAS");
                f2.setText("CO");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/DA4pb.png");
                break;
            case 43:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("PA");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/PI4pb.png");
                break;
            case 44:
                espaco.setText("- -");
                f1.setText("CA");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/JU4pb.png");
                break;
            case 45:
                espaco.setText("- -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("LHA");
                f2.setText("ÇO");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/PA4pb.png");
                break;
            case 46:
                espaco.setText("- -");
                f1.setText("BU");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/LE4pb.png");
                break;
            case 47:
                espaco.setText("- -");
                f1.setId("pg4f1");
                espaco.setId("pg4espaco");
                f1.setText("VI");
                f2.setText("ÃO");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/GA4pb.png");
                break;
            case 48:
                espaco.setText("- -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("POR");
                f2.setText("TAR");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/EX4pb.png");
                break;
            case 49:
                espaco.setText("- - -");
                f1.setId("pg4f1_4");
                espaco.setId("pg4espaco");
                f1.setText("PRAS");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/COM4pb.png");
                break;
            case 50:
                espaco.setText("- - -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("ZEN");
                f2.setText("TOS");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/TRE4pb.png");
                break;
            case 51:
                espaco.setText("- - -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("RA");
                f2.setText("NÁ");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/GUA4pb.png");
                break;
            case 52:
                espaco.setText("- - -");
                f1.setId("pg4f1_5");
                f2.setId("pg4f2_3");
                espaco.setId("pg4espaco");
                f1.setText("BRAN");
                f2.setText("ÇAS");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/LEM4pb.png");
                break;
            case 53:
                espaco.setText("- -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("FA");
                f2.setText("CE");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/AL4pb.png");
                break;
            case 54:
                espaco.setText("- - - -");
                f1.setId("pg4f1_4");
                espaco.setId("pg4espaco");
                f1.setText("CI");
                f2.setText("PE");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/PRÍN4pb.png");
                break;
            case 55:
                espaco.setText("- - -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("TE");
                f2.setText("SE");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/PRÓ4pb.png");
                break;
            case 56:
                espaco.setText("- - -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("MI");
                f2.setText("NO");
                f3.setText("SO");
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/CRI4pb.png");
                break;
            case 57:
                espaco.setText("- - -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("RER");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/COR4pb.png");
                break;
            case 58:
                espaco.setText("- - -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("PO");
                f2.setText("RAL");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/TEM4pb.png");
                break;
            case 59:
                espaco.setText("- - -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("COS");
                f2.setVisible(false);
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/FLO4pb.png");
                break;
            case 60:
                espaco.setText("- - -");
                f1.setId("pg4f1_3");
                espaco.setId("pg4espaco");
                f1.setText("MI");
                f2.setText("TA");
                f3.setVisible(false);
                imgUrl = getClass().getResource("imagens/licao" + unidadeAtual + "/MAR4pb.png");
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
        int u = getUnidadeAtual();
        if (u == 3 || u == 9 || u == 13 || u == 18 || u == 25 || u == 31 || u == 32
                || u == 41 || u == 45 || u == 50 || u == 51 || u == 53) {
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
        int u = getUnidadeAtual();
        if (u == 3 || u == 4 || u == 20 || u == 21 || u == 23 || u == 24 || u == 26
                || u == 32 || u == 38 || u == 41 || u == 45 || u == 47 || u == 49
                || u == 50 || u == 52 || u == 53 || u == 55 || u == 56 || u == 58
                || u == 59) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag03a.fxml"));
            //cria a próxima cena     
            Parent proximaCena = (Parent) fxmloader.load();
            Pag03aController pg03aCont = fxmloader.<Pag03aController>getController();

            mCC.exibirCena(proximaCena, janela);
            pg03aCont.setUnidadeAtual(getUnidadeAtual());
            pg03aCont.audioInicial();
            pg03aCont.setImagens(getUnidadeAtual());
        } else if (u == 25) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag03d.fxml"));
            //cria a próxima cena     
            Parent proximaCena = (Parent) fxmloader.load();
            Pag03dController pg03dCont = fxmloader.<Pag03dController>getController();

            mCC.exibirCena(proximaCena, janela);
            pg03dCont.setUnidadeAtual(getUnidadeAtual());
            pg03dCont.audioInicial();
            pg03dCont.setImagens(getUnidadeAtual());
        } else if (u == 31 || u == 60) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag03b.fxml"));
            //cria a próxima cena     
            Parent proximaCena = (Parent) fxmloader.load();
            Pag03bController pg03bCont = fxmloader.<Pag03bController>getController();
            mCC.exibirCena(proximaCena, janela);
            pg03bCont.setUnidadeAtual(getUnidadeAtual());
            pg03bCont.audioInicial();
            pg03bCont.setImagens(getUnidadeAtual());
        } else if (u == 51) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag03c.fxml"));
            //cria a próxima cena     
            Parent proximaCena = (Parent) fxmloader.load();
            Pag03cController pg03cCont = fxmloader.<Pag03cController>getController();
            mCC.exibirCena(proximaCena, janela);
            pg03cCont.setUnidadeAtual(getUnidadeAtual());
            pg03cCont.audioInicial();
            pg03cCont.setImagens(getUnidadeAtual());
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
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 2:
                if (silabaEscolhida.equals("PO")) {
                    System.out.println("Po");
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao02/pipocacor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 3:
                if (silabaEscolhida.equals("TA")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao03/botacor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 4:
                if (silabaEscolhida.equals("U")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao4/urubucor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 5:
                if (silabaEscolhida.equals("VI")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao5/vinhocor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 6:
                if (silabaEscolhida.equals("LU")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao6/luvacor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 7:
                if (silabaEscolhida.equals("LA")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao7/lacocor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 8:
                if (silabaEscolhida.equals("BE")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao8/cabelocor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            case 9:
                if (silabaEscolhida.equals("BO")) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao9/bonecacor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;
            default:
                String fonema = silabaCorreta[getUnidadeAtual() - 1];
                if (silabaEscolhida.equals(fonema)) {
                    opcaoCorreta = true;
                    imagemUrl = getClass().getResource("imagens/licao" + getUnidadeAtual() + "/" + fonema + "4cor.png");
                    imagemAudio.setImage(new Image(imagemUrl.toString()));
                }
                break;

        }
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
                caminhoAudio = "audios/u" + unidadeAtual + "/palPg4.mp3";
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
    public void abrirManual(ActionEvent event, int pagina, String subPagina) throws IOException {
        mCC.pararAudio();
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirManual(event, pagina, subPagina);
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
        mCC.abrirABC(event, pagina, "");
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
        instrucao.setText("Complete com a parte que está faltando: " + textoInstrucao[unidadeAtual - 1]);
    }

    public void atualizarListView() {
        mCC.atualizarListView(listaPalavras, getUnidadeAtual());
    }

    public void sugestaoAtividade(ActionEvent event, int pagina) throws IOException {
        mCC.sugestaoAtividades(event, pagina,"");
    }
}
