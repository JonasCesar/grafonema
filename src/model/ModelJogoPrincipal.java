package model;

import controller.Gui_FimJogoController;
import controller.Gui_GameOverController;
import controller.Gui_SequenciaCenasController;
import controller.Gui_JogoPrincipalController;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author jonas
 */
public class ModelJogoPrincipal {

    @FXML
    private Button btn_1;
    @FXML
    private Button btn_2;
    @FXML
    private Button btn_3;
    @FXML
    private Button btn_4;
    @FXML
    private Button btn_5;
    @FXML
    private Button pular;

    private EventHandler<ActionEvent> gerarProximaRodada, eventoGameOver, eventoCenas,
            eventoVoltar, eventoAcerto, eventoFimAcerto, eventoCorOriginal, eventoErro,
            reiniciarRelogio, eventoInicioFase;
    @FXML
    private Label pontuacao;

    @FXML
    private Label tempo;

    @FXML
    private Label numFase;

    private URL url;
    @FXML
    private ProgressBar lifeBar;
    private final String vogais[] = {"A", "E", "I", "O", "U"};
    private final String silabasSimples[] = {
        "AL", "AM", "AN", "AR", "AS", "BA", "BE", "BI", "BO", "BU", "CA",
        "ÇÃO", "CE", "CI", "ÇÕES", "CU", "DA", "DI", "DO",
        "EM", "EN", "ER", "ES", "FA", "FE", "FI",
        "FO", "FU", "GA", "GE", "GI", "GO", "GU", "IM", "IN", "IR", "IS", "JAN", "JE", "JI", "JO", "JU",
        "LA", "LE", "LI", "LO", "MA", "ME", "MI", "MO", "MU", "NA", "NE",
        "NI", "NO", "NU", "OM", "ON", "OR", "OS",
        "PA", "PE", "PI", "PO", "PU", "RA", "RE", "RI", "RO",
        "RU", "SA", "SE", "SI", "SO", "SU", "TA", "TE", "TI", "TO", "TU", "UM", "UR",
        "VA", "VE", "VI", "VO", "VU", "XA", "XE", "XI", "XO", "ZA", "ZE",
        "ZI"};

    private final String silabasComplexas2[] = {
        "BLE", "BLI", "BLO", "BLU", "BRA", "BRE", "BRI", "BRO", "BRU",
        "CLA", "CLE", "CLI", "CLO", "CLU", "CRA", "CRE", "CRI", "CRO",
        "CRU", "DRA", "DRE", "DRI", "DRO", "DRU", "FLA", "FLE", "FLO",
        "FLU", "FRA", "FRE", "FRI", "FRO", "FRU", "GLA", "GLO", "GRA",
        "GRE", "GRI", "GRO", "GRU", "PLA", "PLO", "PLU", "PRA", "PRE",
        "PRI", "PRO", "PRU", "TLE", "TLO", "TRA", "TRE", "TRI", "TRO",
        "TRU", "VRE", "VRO",};

    private final String silabasComplexas[] = {
        "BAL", "BAM", "BAN", "BÃO", "BAR", "BAS", "BEL", "BEM", "BER", "BES",
        "BIL", "BIN", "BIR", "BIS", "BOL", "BOM", "BOR", "BOS", "BUM", "BUR",
        "BUS", "CÃES", "CAL", "CAM", "CAN", "CÃO", "CAS", "CEL", "CEN", "CER",
        "CES", "CHE", "CIN", "CIR", "CIS", "ÇÕES", "COL", "COM", "CON", "COR",
        "COS", "CUL", "CUM", "CUR", "CUS", "CUZ", "DAN", "DÃO", "DAR", "DAS",
        "DEN", "DER", "DES", "DIM", "DIS", "DON", "DOR", "DOS", "DUM", "FAL",
        "FAN", "FAR", "FEL", "FEN", "FER", "FES", "FIL", "FIM", "FIR", "FOL",
        "FON", "FOR", "FOS", "FUN", "FUS", "GAL", "GAM", "GAN", "GÃO", "GAR",
        "GEL", "GEM", "GEN", "GER", "GIL", "GIN", "GIR", "GOL", "GON", "GOR",
        "GUA", "JAN", "JÃO", "JAR", "JAS", "JOR", "JUN", "JUS", "LAM", "LAN",
        "LÃO", "LAR", "LAS", "LEM", "LEN", "LER", "LIM", "LIN", "LOM", "LON",
        "LOS", "LUS", "LUZ", "MAL", "MAN", "MÃO", "MÃOS", "MAR", "MEL", "MEM",
        "MEN", "MER", "MÊS", "MIN", "MIR", "MIS", "MOL", "MON", "MOR", "MOS",
        "MUL", "MUN", "MUS", "NAL", "NÃO", "NAS", "NEL", "NER", "NIL", "NIR",
        "NOZ", "PÃES", "PAL", "PAN", "PÃO", "PAR", "PAS", "PAZ", "PEL", "PEN",
        "PER", "PES", "PIM", "PIN", "PIS", "POL", "POM", "PON", "POR", "POS",
        "PUL", "PUM", "RAM", "RAN", "RAS", "REL", "REN", "RES", "RIO", "RIR",
        "RIS", "ROM", "RON", "ROS", "ROU", "SAL", "SAM", "SAN", "SÃO", "SAR",
        "SEL", "SEM", "SEN", "SER", "SIM", "SIS", "SOL", "SOM", "SON", "SOR",
        "SUN", "SUR", "SUS", "TAL", "TAM", "TAN", "TÃO", "TAR", "TAS", "TEL",
        "TEM", "TEN", "TER", "TES", "TIL", "TIM", "TIN", "TIR", "TOL", "TOM",
        "TON", "TOR", "TUM", "TUR", "VAL", "VAM", "VÃO", "VAR", "VAS", "VEL",
        "VEM", "VEN", "VER", "VES", "VIN", "VIR", "VIS", "VON", "VUL", "XAM",
        "XÃO", "XAR", "XER", "XIS", "ZAL", "ZÃO", "ZAR", "ZER", "ZES", "ZIN",
        "ZOL", "ZOM", "ZUL", "ZUM"
    };

    private final String[] silabasComplexas3 = {
        "BRA", "BRAS", "BRES", "BRIN", "BRON", "BROS", "BRUS", "CHÃO",
        "CLAS", "CLOS", "CRES", "CRIS", "CROS", "CRUS", "GUAM", "GUAR",
        "LHEU", "PLAS", "PLES", "PRAN", "PRAS", "PREN", "PRES", "PRIN",
        "PRON", "TLAS", "TRAS", "TRES", "TRIS", "TROS"
    };

    private String palavrasSimples[] = {
        "AMARELO", "APITO", "ÁRVORE", "BALA", "BANANA", "BATATA",
        "BIRUTA", "BOCA", "BOLA", "BOLITA", "BOLO", "BONÉ", "BONECA",
        "BONITO", "BOTO", "BULA", "BULE", "CABELO", "CAFÉ", "CAMA", "CAMELO", "CAMISA", "CAPACETE",
        "CASA", "CASACO", "CAVALO", "CEREJA", "COLA", "COPO",
        "CORUJA", "DADO", "DEDO", "DURO", "ESCOLA", "ESCOVA", "FACA", "FADA", "FOCA",
        "FOGO", "GALO", "GATO", "GELADO", "GELO", "JACA", "JACARÉ", "JANELA",
        "LATA", "LEITE", "LIXO", "LUA", "LUTA", "LUVA", "MACACO", "MALA",
        "MENINO", "MESA", "MOEDA", "MÚSICA", "NEVE", "NOVELA", "OVO", "PAREDE", "PATO",
        "PÉ", "PELADO", "PENA", "PERA", "PETECA", "PIANO", "PIPA", "PIPOCA", "PIRULITO",
        "PULO", "RATO", "REI", "RICO", "ROBÔ", "RODA", "ROSA", "RUA", "SÁBADO", "SAPATO",
        "SAPO", "SINO", "SOFÁ", "SUCO", "TAPETE", "TATU", "TELEFONE", "TETO", "TIJOLO", "TOMATE",
        "URSO", "UVA", "VACA", "VAGALUME", "VELA", "XÍCARA", "XIXI"
    };

    private String palavrasComplexas[] = {
        "ABELHA", "ABERTO", "ALEGRIA", "ANIMAL", "ANTENA", "APONTADOR",
        "ARANHA", "ARROZ", "AZUL", "BANHA", "BARCO", "BATOM", "BÍBLIA", "BIBLIOTECA",
        "BICICLETA", "BISCOITO", "BLOCO", "BLUSA", "BOLHA", "BORBOLETA", "BRAÇO",
        "BRASIL", "BRINCO", "BRINQUEDO", "BRONCA", "BRUXA", "CACHORRO", "CADERNO",
        "CALÇA", "CARRO", "CARROÇA", "CENOURA", "CHINELO", "CHOCOLATE", "COBERTOR",
        "COBRA", "COMPUTADOR", "ENGRAÇADO", "ESPINHO", "FAROL", "FECHADO", "FICHA",
        "FILHO", "FLAUTA", "FLOR", "FLORESTA", "FOGUETE", "FOLHA", "FRALDA", "FRUTA",
        "GALINHA", "GIRASSOL", "GRAVATA", "GRAVETO", "GUITARRA", "IMPRESSORA", "JARDIM",
        "JEGUE", "JOELHO", "LÂMPADA", "LARANJA", "LEÃO", "LIMÃO", "LÍNGUA", "LINHA",
        "LIVRO", "MALVADO", "MAMÃO", "MARAVILHA", "MINHOCA", "MOCHILA", "NARIZ",
        "NINHO", "NOIVO", "NUBLADO", "OLHO", "PALHAÇO", "PASSAGEM", "PASSARINHO",
        "PEIXE", "PILHA", "PINGUIM", "PLACA", "PLANTA", "PRAÇA", "PRATO", "PRINCESA",
        "PRÍNCIPE", "PULSEIRA", "RÉGUA", "RETRATO", "SEGREDO", "SOL", "SONHO",
        "TARTARUGA", "TELEFONE", "TERRA", "TIGRE", "TORNOZELO", "VIDRO"
    };

    //o nome dos arquivos das vogais
    private final String audioVogais[] = {"vogal-A", "vogal-E", "vogal-I", "vogal-O", "vogal-U"};

    private final String audioSilabasSimples[] = {
        "al", "am", "an", "ar", "as", "ba", "be", "bi", "bo", "bu", "ca",
        "ção", "ce", "ci", "ções", "cu", "da", "di", "do",
        "em", "en", "er", "es", "fa", "fe", "fi", "fo", "fu", "ga", "ge", "gi",
        "go", "gu", "im", "in", "ir", "is", "jan", "je", "ji", "jo", "ju",
        "la", "le", "li", "lo", "ma", "me", "mi", "mo", "mu", "na", "ne",
        "ni", "no", "nu", "om", "on", "or", "os",
        "pa", "pe", "pi", "po", "pu", "ra", "re", "ri", "ro",
        "ru", "sa", "se", "si", "so", "su", "ta", "te", "ti", "to", "tu",
        "um", "ur", "va", "ve", "vi", "vo", "vu", "xa", "xe", "xi", "xo", "za", "ze", "zi"};

    private final String audiosSilabasComplexas[] = {
        "bal", "bam", "ban", "bão", "bar", "bas", "bel", "bem", "ber", "bes",
        "bil", "bin", "bir", "bis", "bol", "bom", "bor", "bos", "bum", "bur",
        "bus", "cães", "cal", "cam", "can", "cão", "cas", "cel", "cen", "cer",
        "ces", "che", "cin", "cir", "cis", "ções", "col", "com", "con", "cor",
        "cos", "cul", "cum", "cur", "cus", "cuz", "dan", "dão", "dar", "das",
        "den", "der", "des", "dim", "dis", "don", "dor", "dos", "dum", "fal",
        "fan", "far", "fel", "fen", "fer", "fes", "fil", "fim", "fir", "fol",
        "fon", "for", "fos", "fun", "fus", "gal", "gam", "gan", "gão", "gar",
        "gel", "gem", "gen", "ger", "gil", "gin", "gir", "gol", "gon", "gor",
        "gua", "jan", "jão", "jar", "jas", "jor", "jun", "jus", "lam", "lan",
        "lão", "lar", "las", "lem", "len", "ler", "lim", "lin", "lom", "lon",
        "los", "lus", "luz", "mal", "man", "mão", "mãos", "mar", "mel", "mem",
        "men", "mer", "mês", "min", "mir", "mis", "mol", "mon", "mor", "mos",
        "mul", "mun", "mus", "nal", "não", "nas", "nel", "ner", "nil", "nir",
        "noz", "pães", "pal", "pan", "pão", "par", "pas", "paz", "pel", "pen",
        "per", "pes", "pim", "pin", "pis", "pol", "pom", "pon", "por", "pos",
        "pul", "pum", "ram", "ran", "ras", "rel", "ren", "res", "rio", "rir",
        "ris", "rom", "ron", "ros", "rou", "sal", "sam", "san", "são", "sar",
        "sel", "sem", "sen", "ser", "sim", "sis", "sol", "som", "son", "sor",
        "sun", "sur", "sus", "tal", "tam", "tan", "tão", "tar", "tas", "tel",
        "tem", "ten", "ter", "tes", "til", "tim", "tin", "tir", "tol", "tom",
        "ton", "tor", "tum", "tur", "val", "vam", "vão", "var", "vas", "vel",
        "vem", "ven", "ver", "ves", "vin", "vir", "vis", "von", "vul", "xam",
        "xão", "xar", "xer", "xis", "zal", "zão", "zar", "zer", "zes", "zin",
        "zol", "zom", "zul", "zum"
    };

    private final String audiosSilabasComplexas2[] = {
        "ble", "bli", "blo", "blu", "bra", "bre", "bri", "bro", "bru",
        "cla", "cle", "cli", "clo", "clu", "cra", "cre", "cri", "cro",
        "cru", "dra", "dre", "dri", "dro", "dru", "fla", "fle", "flo",
        "flu", "fra", "fre", "fri", "fro", "fru", "gla", "glo", "gra",
        "gre", "gri", "gro", "gru", "pla", "plo", "plu", "pra", "pre",
        "pri", "pro", "pru", "tle", "tlo", "tra", "tre", "tri", "tro",
        "tru", "vre", "vro"};

    private final String audiosPalavrasSimples[] = {
        "amarelo", "apito", "árvore", "bala", "banana", "batata",
        "biruta", "boca", "bola", "bolita", "bolo", "boné", "boneca",
        "bonito", "boto", "bula", "bule", "cabelo", "café", "cama", "camelo", "camisa", "capacete",
        "casa", "casaco", "cavalo", "cereja", "cola", "copo",
        "coruja", "dado", "dedo", "duro", "escola", "escova", "faca", "fada", "foca",
        "fogo", "galo", "gato", "gelado", "gelo", "jaca", "jacaré", "janela",
        "lata", "leite", "lixo", "lua", "luta", "luva", "macaco", "mala",
        "menino", "mesa", "moeda", "música", "neve", "novela", "ovo", "parede", "pato",
        "pé", "pelado", "pena", "pera", "peteca", "piano", "pipa", "pipoca", "pirulito",
        "pulo", "rato", "rei", "rico", "robô", "roda", "rosa", "rua", "sábado", "sapato",
        "sapo", "sino", "sofá", "suco", "tapete", "tatu", "telefone", "teto", "tijolo", "tomate",
        "urso", "uva", "vaca", "vagalume", "vela", "xícara", "xixi"
    };

    private final String audiosSilabasComplexas3[] = {
        "bra", "bras", "bres", "brin", "bron", "bros", "brus", "chão",
        "clas", "clos", "cres", "cris", "cros", "crus", "guam", "guar",
        "lheu", "plas", "ples", "pran", "pras", "pren", "pres", "prin",
        "pron", "tlas", "tras", "tres", "tris", "tros"};

    private final String audiosPalavrasComplexas[] = {
        "abelha", "aberto", "alegria", "animal", "antena", "apontador",
        "aranha", "arroz", "azul", "banha", "barco", "batom", "bíblia", "biblioteca",
        "bicicleta", "biscoito", "bloco", "blusa", "bolha", "borboleta", "braço",
        "brasil", "brinco", "brinquedo", "bronca", "bruxa", "cachorro", "caderno",
        "calça", "carro", "carroça", "cenoura", "chinelo", "chocolate", "cobertor",
        "cobra", "computador", "engraçado", "espinho", "farol", "fechado", "ficha",
        "filho", "flauta", "flor", "floresta", "foguete", "folha", "fralda", "fruta",
        "galinha", "girassol", "gravata", "graveto", "guitarra", "impressora", "jardim",
        "jegue", "joelho", "lâmpada", "laranja", "leão", "limão", "língua", "linha",
        "livro", "malvado", "mamão", "maravilha", "minhoca", "mochila", "nariz",
        "ninho", "noivo", "nublado", "olho", "palhaço", "passagem", "passarinho",
        "peixe", "pilha", "pinguim", "placa", "planta", "praça", "prato", "princesa",
        "príncipe", "pulseira", "régua", "retrato", "segredo", "sol", "sonho",
        "tartaruga", "telefone", "terra", "tigre", "tornozelo", "vidro"
    };

    public Jogador jogador = new Jogador();

    private final Map<String, String> matrizVogais, matrizSilabasSimples, matrizSilabasComplexas2, matrizPalavrasSimples,
            matrizSilabasComplexas, matrizSilabasComplexas3, matrizPalavrasComplexas;

    private Random indiceAudio;

    private Stage janela;
    private String caminhoAudio, nomeAudioAtual;
    private File arquivo;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView = new MediaView();

    private boolean mostrandoCena, indicacaoPular, pularErro, mostrandoDica;

    private Scene cenaTemporaria;

    private Button correto;

    private Timer timer;

    int numClique2;

    Gui_FimJogoController fimJogo = new Gui_FimJogoController();
    Gui_SequenciaCenasController sequenciaCenas = new Gui_SequenciaCenasController();

    Gui_JogoPrincipalController jogoPrincipal = new Gui_JogoPrincipalController();

    FuncaoBotao funcao = new FuncaoBotao();

    private ImageView imagemFundo;

    private Model_Inicial modelInicial;
    Double tempoFase;
    private ArrayList novasOpcoes;

    @FXML
    private Button DicaBotao1;
    @FXML
    private Button DicaBotao2;
    @FXML
    private Button DicaBotao3;
    @FXML
    private Button DicaBotao4;

    @FXML
    private Button DicaBotao0;

    public ModelJogoPrincipal(Button b1, Button b2, Button b3, Button b4, Button b5,
            Button pular, Label pontuacao, ProgressBar lifeBar, Label tempo, Button ouvirAudio,
            ImageView imagemFundo, Label numFase, Button DicaBotao0, Button DicaBotao1, Button DicaBotao2,
            Button DicaBotao3, Button DicaBotao4) {

        this.btn_1 = b1;
        this.btn_2 = b2;
        this.btn_3 = b3;
        this.btn_4 = b4;
        this.btn_5 = b5;
        this.pular = pular;
        this.lifeBar = lifeBar;
        this.pontuacao = pontuacao;
        this.indiceAudio = new Random();
        this.matrizVogais = new HashMap<>();
        this.matrizSilabasSimples = new HashMap<>();
        //this.matrizSilabasSimplesB = new HashMap<>();
        this.matrizSilabasComplexas2 = new HashMap<>();
        this.matrizSilabasComplexas3 = new HashMap<>();
        this.matrizSilabasComplexas = new HashMap<>();
        this.matrizPalavrasSimples = new HashMap<>();
        this.matrizPalavrasComplexas = new HashMap<>();
        this.mostrandoCena = false;
        this.indicacaoPular = false;
        this.pularErro = false;
        this.tempo = tempo;
        nomeAudioAtual = "";
        janela = null;
        cenaTemporaria = null;
        url = null;
        this.imagemFundo = imagemFundo;
        this.numFase = numFase;
        modelInicial = new Model_Inicial();
        tempoFase = 0.0;
        this.DicaBotao0 = DicaBotao0;
        this.DicaBotao1 = DicaBotao1;
        this.DicaBotao2 = DicaBotao2;
        this.DicaBotao3 = DicaBotao3;
        this.DicaBotao4 = DicaBotao4;
        this.mostrandoDica = false;

    }

    /**
     * Gera as novas opções que serão exibidas para o jogador
     *
     * OBS: OS VALORES GERADOS IRÃO VARIAR EM CADA FASE E DEVERÃO SER
     * CORRESPONDENTES AOS ARQUIVOS DE ÁUDIO QUE ESTARÃO SENDO EXECUTADOS
     *
     * @throws java.lang.InterruptedException
     * @throws java.io.IOException
     */
    public void gerarOpcaoAleatoria() throws InterruptedException, IOException {
        int rodadas = 15;
        //int rodadas = 2;

        if (jogador.getFaseAtual() != 1 && jogador.getFaseAtual() != 3
                && jogador.getFaseAtual() != 7) {
            mostrarBotoesDicas();
        } else {
            ocultarBotoesDicas();
        }

        if (jogador.getFaseAtual() > 1) {
            rodadas = 20;
        }

        System.out.println("Nova opção aleatória fase " + getFaseAtual());
        System.out.println("qnt erros  " + jogador.getQntErros());
        //se o jogador acertar pelo menos 10 vezes
        if (jogador.getAcertosTotal() == 10) {
            jogador.setBonus(true);
        }

        if (jogador.getQntErros() + jogador.getAcertosTotal() == rodadas) {

            //apartir da fase 1 o número de rodadas aumenta para 20
            //if (pular.isDisable()) {
            //  pular.setDisable(false);
            //}
            jogador.setQntErros(0);//restaura a quantidade de erros do jogador
            lifeBar.setProgress(1.0);
            lifeBar.setId("lifeBar");
            jogador.setQntPulos(-1); //restaura a quantidade de pulos disponível
            jogador.setBonus(false);//retira o bônus do jogador
            if (getFaseAtual() != 7) {
                mostrarCenas();
                jogador.setAcertosTotal(0);
            } else {
                mostrarCenaFinal(jogador.getPontuacaoTotal());
            }

            /**
             * OBS: VAI SER DIFERENTE SE FOR NA ÚLTIMA FASE
             */
        } else {
            gerarOpcaoAudio();
        }
    }

    /**
     * função para preencher as opções na tela inserindo em um local aleatório a
     * opção correspondente ao áudio
     */
    public void preencherOpcoes(String categoria[], int s, ArrayList no) {

        Random ind = new Random();
        int valor = ind.nextInt(5);
        if (valor == 0) {

            btn_1.setText(categoria[s]);
            btn_2.setText(categoria[(int) no.get(1)]);
            btn_3.setText(categoria[(int) no.get(2)]);
            btn_4.setText(categoria[(int) no.get(3)]);
            btn_5.setText(categoria[(int) no.get(4)]);

        } else if (valor == 1) {

            btn_1.setText(categoria[(int) no.get(0)]);
            btn_2.setText(categoria[s]);
            btn_3.setText(categoria[(int) no.get(2)]);
            btn_4.setText(categoria[(int) no.get(3)]);
            btn_5.setText(categoria[(int) no.get(4)]);

        } else if (valor == 2) {

            btn_1.setText(categoria[(int) no.get(0)]);
            btn_2.setText(categoria[(int) no.get(1)]);
            btn_3.setText(categoria[s]);
            btn_4.setText(categoria[(int) no.get(3)]);
            btn_5.setText(categoria[(int) no.get(4)]);

        } else if (valor == 3) {

            btn_1.setText(categoria[(int) no.get(0)]);
            btn_2.setText(categoria[(int) no.get(1)]);
            btn_3.setText(categoria[(int) no.get(2)]);
            btn_4.setText(categoria[s]);
            btn_5.setText(categoria[(int) no.get(4)]);

        } else if (valor == 4) {

            btn_1.setText(categoria[(int) no.get(0)]);
            btn_2.setText(categoria[(int) no.get(1)]);
            btn_3.setText(categoria[(int) no.get(2)]);
            btn_4.setText(categoria[(int) no.get(3)]);
            btn_5.setText(categoria[s]);

        }
    }

    /**
     * Gera o som aleatório que o jogador deverá relacionar com uma das opções
     *
     * @return a posicao do array referente ao áudio que deve ser reproduzido
     */
    public int gerarSomAleatorio() {
        //objeto que será utilizado para gera um número aleatório

        int i = 0;
        int y = 0;
        //verifica qual a fase atual do jogador
        switch (jogador.getFaseAtual()) {
            case 1:
                i = indiceAudio.nextInt(5);//gera um índice entre 0 - 4
                tocarAudio(audioVogais[i]);
                //tocarAudio.teste();
                break;
            case 2:

                iniciarMatrizAudioSilabas();
                i = indiceAudio.nextInt(93);
                tocarAudio(audioSilabasSimples[i]);
                y = i;
                break;
            case 3:
                matrizSilabasSimples.clear();
                iniciarMatrizPalavrasSimples();
                i = indiceAudio.nextInt(99);
                tocarAudio(audiosPalavrasSimples[i]);
                y = i;
                break;
            case 4:
                matrizPalavrasSimples.clear();
                iniciarMatrizSilabasComplexas();
                i = indiceAudio.nextInt(245);
                tocarAudio(audiosSilabasComplexas[i]);
                y = i;
                break;
            case 5:
                matrizSilabasComplexas.clear();
                iniciarMatrizSilabasComplexas2();
                i = indiceAudio.nextInt(57);
                tocarAudio(audiosSilabasComplexas2[i]);
                y = i;
                break;
            case 6:
                matrizSilabasComplexas2.clear();
                iniciarMatrizSilabasComplexas3();
                i = indiceAudio.nextInt(30);
                tocarAudio(audiosSilabasComplexas3[i]);
                y = i;
                break;
            case 7:
                matrizSilabasComplexas2.clear();
                iniciarMatrizPalavrasComplexas();
                i = indiceAudio.nextInt(100);
                tocarAudio(audiosPalavrasComplexas[i]);
                y = i;
                break;
        }
        return y;

    }

    /**
     * Verifica se a opção escolhida pelo jogador é a correta
     *
     * @param event evento disparado quando o jogador escolhe um dos 5 botões
     * @return true se a opção escolhida foi a correta e false caso contrário
     */
    public boolean verificarRelacaoGaFonema(ActionEvent event) {
        String opcaoEscolhida = (((Button) event.getSource()).getText());//pega a opção escolhida pelo usuário
        //compara o valor que o usuário escolheu com o valor correspondente ao áudio
        boolean resultado = false;
        switch (jogador.getFaseAtual()) {
            case 1:

                resultado = ((getKeyByValue(matrizVogais, opcaoEscolhida)).equals(getAudioAtual()));
                System.out.println("Resultado " + resultado);
                break;
            case 2:
                resultado = ((getKeyByValue(matrizSilabasSimples, opcaoEscolhida)).equals(getAudioAtual()));
                break;
            case 3:
                resultado = ((getKeyByValue(matrizPalavrasSimples, opcaoEscolhida)).equals(getAudioAtual()));
                break;
            case 4:
                resultado = ((getKeyByValue(matrizSilabasComplexas, opcaoEscolhida)).equals(getAudioAtual()));
                break;
            case 5:
                resultado = ((getKeyByValue(matrizSilabasComplexas2, opcaoEscolhida)).equals(getAudioAtual()));
                break;
            case 6:
                resultado = ((getKeyByValue(matrizSilabasComplexas3, opcaoEscolhida)).equals(getAudioAtual()));
                break;
            case 7:
                resultado = ((getKeyByValue(matrizPalavrasComplexas, opcaoEscolhida)).equals(getAudioAtual()));
                break;
        }

        return resultado;
    }

    /**
     * Reduz a barra de life de acordo com os erros do jogador
     */
    public void reduzirLifeBar() {
        //pega o valor anterior da barra de vida
        double valorAnterior = lifeBar.getProgress();
        //reduz o valor da barra de vida em 0.2 de um total inicial de 1.0
        double valorAtualizado = valorAnterior - 0.1666;
        //atualiza a barra de vida
        lifeBar.setProgress(valorAtualizado);
    }
    
   

    /**
     * Desabilita a função pular
     */
    public void desabilitarPulo() {
        pular.setDisable(true);
    }

    /**
     * Inicia a matriz de vogais 'vogal-A' é o nome do arquivo de audio
     * referente à vogal A 'A' é o valor que aparecerá nos botões
     */
    public void iniciarMatrizAudiosVogal() {

        matrizVogais.put("vogal-A", "A");
        matrizVogais.put("vogal-E", "E");
        matrizVogais.put("vogal-I", "I");
        matrizVogais.put("vogal-O", "O");
        matrizVogais.put("vogal-U", "U");
    }

    /**
     * Inicia a matriz de sílabas
     */
    public void iniciarMatrizAudioSilabas() {

        matrizSilabasSimples.put("al", "AL");
        matrizSilabasSimples.put("am", "AM");
        matrizSilabasSimples.put("an", "AN");
        matrizSilabasSimples.put("ar", "AR");
        matrizSilabasSimples.put("as", "AS");
        matrizSilabasSimples.put("ba", "BA");
        matrizSilabasSimples.put("be", "BE");
        matrizSilabasSimples.put("bi", "BI");
        matrizSilabasSimples.put("bo", "BO");
        matrizSilabasSimples.put("bu", "BU");
        matrizSilabasSimples.put("ca", "CA");
        matrizSilabasSimples.put("ção", "ÇÃO");
        matrizSilabasSimples.put("ce", "CE");
        matrizSilabasSimples.put("ci", "CI");
        matrizSilabasSimples.put("ções", "ÇÕES");
        matrizSilabasSimples.put("cu", "CU");
        matrizSilabasSimples.put("da", "DA");
        matrizSilabasSimples.put("di", "DI");
        matrizSilabasSimples.put("do", "DO");
        matrizSilabasSimples.put("em", "EM");
        matrizSilabasSimples.put("en", "EN");
        matrizSilabasSimples.put("er", "ER");
        matrizSilabasSimples.put("es", "ES");

        matrizSilabasSimples.put("fa", "FA");
        matrizSilabasSimples.put("fe", "FE");
        matrizSilabasSimples.put("fi", "FI");
        matrizSilabasSimples.put("fo", "FO");
        matrizSilabasSimples.put("fu", "FU");

        matrizSilabasSimples.put("ga", "GA");
        matrizSilabasSimples.put("ge", "GE");
        matrizSilabasSimples.put("gi", "GI");
        matrizSilabasSimples.put("go", "GO");
        matrizSilabasSimples.put("gu", "GU");

        matrizSilabasSimples.put("im", "IM");
        matrizSilabasSimples.put("in", "IN");
        matrizSilabasSimples.put("ir", "IR");
        matrizSilabasSimples.put("is", "IS");

        matrizSilabasSimples.put("jan", "JAN");
        matrizSilabasSimples.put("je", "JE");
        matrizSilabasSimples.put("ji", "JI");
        matrizSilabasSimples.put("jo", "JO");
        matrizSilabasSimples.put("ju", "JU");
        matrizSilabasSimples.put("la", "LA");
        matrizSilabasSimples.put("le", "LE");
        matrizSilabasSimples.put("li", "LI");
        matrizSilabasSimples.put("lo", "LO");

        matrizSilabasSimples.put("ma", "MA");
        matrizSilabasSimples.put("me", "ME");
        matrizSilabasSimples.put("mi", "MI");
        matrizSilabasSimples.put("mo", "MO");
        matrizSilabasSimples.put("mu", "MU");
        matrizSilabasSimples.put("na", "NA");
        matrizSilabasSimples.put("ne", "NE");
        matrizSilabasSimples.put("ni", "NI");
        matrizSilabasSimples.put("no", "NO");
        matrizSilabasSimples.put("nu", "NU");

        matrizSilabasSimples.put("om", "OM");
        matrizSilabasSimples.put("on", "ON");
        matrizSilabasSimples.put("or", "OR");
        matrizSilabasSimples.put("os", "OS");

        matrizSilabasSimples.put("pa", "PA");
        matrizSilabasSimples.put("pe", "PE");
        matrizSilabasSimples.put("pi", "PI");
        matrizSilabasSimples.put("po", "PO");
        matrizSilabasSimples.put("pu", "PU");

        matrizSilabasSimples.put("ra", "RA");
        matrizSilabasSimples.put("re", "RE");
        matrizSilabasSimples.put("ri", "RI");
        matrizSilabasSimples.put("ro", "RO");
        matrizSilabasSimples.put("ru", "RU");

        matrizSilabasSimples.put("sa", "SA");
        matrizSilabasSimples.put("se", "SE");
        matrizSilabasSimples.put("si", "SI");
        matrizSilabasSimples.put("so", "SO");
        matrizSilabasSimples.put("su", "SU");

        matrizSilabasSimples.put("ta", "TA");
        matrizSilabasSimples.put("te", "TE");
        matrizSilabasSimples.put("ti", "TI");
        matrizSilabasSimples.put("to", "TO");
        matrizSilabasSimples.put("tu", "TU");

        matrizSilabasSimples.put("um", "UM");
        matrizSilabasSimples.put("ur", "UR");

        matrizSilabasSimples.put("va", "VA");
        matrizSilabasSimples.put("ve", "VE");
        matrizSilabasSimples.put("vi", "VI");
        matrizSilabasSimples.put("vo", "VO");
        matrizSilabasSimples.put("vu", "VU");

        matrizSilabasSimples.put("xa", "XA");
        matrizSilabasSimples.put("xe", "XE");
        matrizSilabasSimples.put("xi", "XI");
        matrizSilabasSimples.put("xo", "XO");

        matrizSilabasSimples.put("za", "ZA");
        matrizSilabasSimples.put("ze", "ZE");
        matrizSilabasSimples.put("zi", "ZI");
        matrizSilabasSimples.put("zo", "ZO");

    }

    /**
     * Inicia a matriz de silibas complexas 2
     */
    public void iniciarMatrizSilabasComplexas2() {
        matrizSilabasComplexas2.put("ble", "BLE");
        matrizSilabasComplexas2.put("bli", "BLI");
        matrizSilabasComplexas2.put("blo", "BLO");
        matrizSilabasComplexas2.put("blu", "BLU");
        matrizSilabasComplexas2.put("bra", "BRA");
        matrizSilabasComplexas2.put("bre", "BRE");
        matrizSilabasComplexas2.put("bri", "BRI");
        matrizSilabasComplexas2.put("bro", "BRO");
        matrizSilabasComplexas2.put("bru", "BRU");
        matrizSilabasComplexas2.put("cla", "CLA");
        matrizSilabasComplexas2.put("cle", "CLE");
        matrizSilabasComplexas2.put("cli", "CLI");
        matrizSilabasComplexas2.put("clo", "CLO");
        matrizSilabasComplexas2.put("clu", "CLU");
        matrizSilabasComplexas2.put("cra", "CRA");
        matrizSilabasComplexas2.put("cre", "CRE");
        matrizSilabasComplexas2.put("cri", "CRI");
        matrizSilabasComplexas2.put("cro", "CRO");
        matrizSilabasComplexas2.put("cru", "CRU");

        matrizSilabasComplexas2.put("dra", "DRA");
        matrizSilabasComplexas2.put("dre", "DRE");
        matrizSilabasComplexas2.put("dri", "DRI");
        matrizSilabasComplexas2.put("dro", "DRO");
        matrizSilabasComplexas2.put("dru", "DRU");
        matrizSilabasComplexas2.put("fla", "FLA");
        matrizSilabasComplexas2.put("fle", "FLE");

        matrizSilabasComplexas2.put("flo", "FLO");
        matrizSilabasComplexas2.put("flu", "FLU");
        matrizSilabasComplexas2.put("fra", "FRA");
        matrizSilabasComplexas2.put("fre", "FRE");
        matrizSilabasComplexas2.put("fri", "FRI");
        matrizSilabasComplexas2.put("fro", "FRO");
        matrizSilabasComplexas2.put("fru", "FRU");
        matrizSilabasComplexas2.put("gla", "GLA");
        matrizSilabasComplexas2.put("glo", "GLO");

        matrizSilabasComplexas2.put("gra", "GRA");
        matrizSilabasComplexas2.put("gre", "GRE");
        matrizSilabasComplexas2.put("gri", "GRI");
        matrizSilabasComplexas2.put("gro", "GRO");
        matrizSilabasComplexas2.put("gru", "GRU");
        matrizSilabasComplexas2.put("pla", "PLA");
        matrizSilabasComplexas2.put("plo", "PLO");
        matrizSilabasComplexas2.put("plu", "PLU");
        matrizSilabasComplexas2.put("pra", "PRA");
        matrizSilabasComplexas2.put("pre", "PRE");
        matrizSilabasComplexas2.put("pri", "PRI");
        matrizSilabasComplexas2.put("pro", "PRO");
        matrizSilabasComplexas2.put("pru", "PRU");
        matrizSilabasComplexas2.put("tle", "TLE");

        matrizSilabasComplexas2.put("tlo", "TLO");

        matrizSilabasComplexas2.put("tra", "TRA");
        matrizSilabasComplexas2.put("tre", "TRE");
        matrizSilabasComplexas2.put("tri", "TRI");
        matrizSilabasComplexas2.put("tro", "TRO");
        matrizSilabasComplexas2.put("tru", "TRU");
        matrizSilabasComplexas2.put("vre", "VRE");

        matrizSilabasComplexas2.put("vro", "VRO");
        matrizSilabasComplexas2.put("vru", "VRU");
    }

    /**
     * inicia a matriz de Palavras Simples
     */
    public void iniciarMatrizPalavrasSimples() {
        matrizPalavrasSimples.put("amarelo", "AMARELO");
        matrizPalavrasSimples.put("apito", "APITO");
        matrizPalavrasSimples.put("árvore", "ÁRVORE");

        matrizPalavrasSimples.put("bala", "BALA");
        matrizPalavrasSimples.put("banana", "BANANA");
        matrizPalavrasSimples.put("batata", "BATATA");
        matrizPalavrasSimples.put("biruta", "BIRUTA");
        matrizPalavrasSimples.put("boca", "BOCA");
        matrizPalavrasSimples.put("bola", "BOLA");
        matrizPalavrasSimples.put("bolita", "BOLITA");
        matrizPalavrasSimples.put("bolo", "BOLO");
        matrizPalavrasSimples.put("boné", "BONÉ");
        matrizPalavrasSimples.put("boneca", "BONECA");
        matrizPalavrasSimples.put("bonito", "BONITO");
        matrizPalavrasSimples.put("boto", "BOTO");
        matrizPalavrasSimples.put("bula", "BULA");
        matrizPalavrasSimples.put("bule", "BULE");

        matrizPalavrasSimples.put("cabelo", "CABELO");
        matrizPalavrasSimples.put("café", "CAFÉ");
        matrizPalavrasSimples.put("cama", "CAMA");
        matrizPalavrasSimples.put("camelo", "CAMELO");
        matrizPalavrasSimples.put("camisa", "CAMISA");
        matrizPalavrasSimples.put("capacete", "CAPACETE");
        matrizPalavrasSimples.put("casa", "CASA");
        matrizPalavrasSimples.put("casaco", "CASACO");
        matrizPalavrasSimples.put("cavalo", "CAVALO");
        matrizPalavrasSimples.put("cereja", "CEREJA");
        matrizPalavrasSimples.put("cola", "COLA");
        matrizPalavrasSimples.put("copo", "COPO");
        matrizPalavrasSimples.put("coruja", "CORUJA");

        matrizPalavrasSimples.put("dado", "DADO");
        matrizPalavrasSimples.put("dedo", "DEDO");
        matrizPalavrasSimples.put("duro", "DURO");

        matrizPalavrasSimples.put("escola", "ESCOLA");
        matrizPalavrasSimples.put("escova", "ESCOVA");

        matrizPalavrasSimples.put("faca", "FACA");
        matrizPalavrasSimples.put("fada", "FADA");
        matrizPalavrasSimples.put("foca", "FOCA");
        matrizPalavrasSimples.put("fogo", "FOGO");

        matrizPalavrasSimples.put("galo", "GALO");
        matrizPalavrasSimples.put("gato", "GATO");
        matrizPalavrasSimples.put("gelado", "GELADO");
        matrizPalavrasSimples.put("gelo", "GELO");

        matrizPalavrasSimples.put("jaca", "JACA");
        matrizPalavrasSimples.put("jacaré", "JACARÉ");
        matrizPalavrasSimples.put("janela", "JANELA");

        matrizPalavrasSimples.put("lata", "LATA");
        matrizPalavrasSimples.put("leite", "LEITE");
        matrizPalavrasSimples.put("lixo", "LIXO");
        matrizPalavrasSimples.put("lua", "LUA");
        matrizPalavrasSimples.put("luta", "LUTA");
        matrizPalavrasSimples.put("luva", "LUVA");

        matrizPalavrasSimples.put("macaco", "MACACO");
        matrizPalavrasSimples.put("mala", "MALA");
        matrizPalavrasSimples.put("menino", "MENINO");
        matrizPalavrasSimples.put("mesa", "MESA");
        matrizPalavrasSimples.put("moeda", "MOEDA");
        matrizPalavrasSimples.put("música", "MÚSICA");

        matrizPalavrasSimples.put("neve", "NEVE");
        matrizPalavrasSimples.put("novela", "NOVELA");

        matrizPalavrasSimples.put("ovo", "OVO");

        matrizPalavrasSimples.put("parede", "PAREDE");
        matrizPalavrasSimples.put("pato", "PATO");
        matrizPalavrasSimples.put("pé", "PÉ");
        matrizPalavrasSimples.put("pelado", "PELADO");
        matrizPalavrasSimples.put("pena", "PENA");
        matrizPalavrasSimples.put("pera", "PERA");
        matrizPalavrasSimples.put("peteca", "PETECA");
        matrizPalavrasSimples.put("piano", "PIANO");
        matrizPalavrasSimples.put("pipa", "PIPA");
        matrizPalavrasSimples.put("pipoca", "PIPOCA");
        matrizPalavrasSimples.put("pirulito", "PIRULITO");
        matrizPalavrasSimples.put("pulo", "PULO");

        matrizPalavrasSimples.put("rato", "RATO");
        matrizPalavrasSimples.put("rei", "REI");
        matrizPalavrasSimples.put("rico", "RICO");
        matrizPalavrasSimples.put("robô", "ROBÔ");
        matrizPalavrasSimples.put("roda", "RODA");
        matrizPalavrasSimples.put("rosa", "ROSA");
        matrizPalavrasSimples.put("rua", "RUA");

        matrizPalavrasSimples.put("sábado", "SÁBADO");
        matrizPalavrasSimples.put("sapato", "SAPATO");
        matrizPalavrasSimples.put("sapo", "SAPO");
        matrizPalavrasSimples.put("sino", "SINO");
        matrizPalavrasSimples.put("sofá", "SOFÁ");
        matrizPalavrasSimples.put("suco", "SUCO");

        matrizPalavrasSimples.put("tapete", "TAPETE");
        matrizPalavrasSimples.put("tatu", "TATU");
        matrizPalavrasSimples.put("telefone", "TELEFONE");
        matrizPalavrasSimples.put("teto", "TETO");
        matrizPalavrasSimples.put("tijolo", "TIJOLO");
        matrizPalavrasSimples.put("tomate", "TOMATE");

        matrizPalavrasSimples.put("urso", "URSO");
        matrizPalavrasSimples.put("uva", "UVA");

        matrizPalavrasSimples.put("vaca", "VACA");
        matrizPalavrasSimples.put("vagalume", "VAGALUME");
        matrizPalavrasSimples.put("vela", "VELA");

        matrizPalavrasSimples.put("xícara", "XÍCARA");
        matrizPalavrasSimples.put("xixi", "XIXI");

    }

    /**
     * Inicia a matriz de silabas complexas
     */
    public void iniciarMatrizSilabasComplexas() {
        matrizSilabasComplexas.put("bal", "BAL");
        matrizSilabasComplexas.put("bam", "BAM");
        matrizSilabasComplexas.put("ban", "BAN");
        matrizSilabasComplexas.put("bão", "BÃO");
        matrizSilabasComplexas.put("bar", "BAR");
        matrizSilabasComplexas.put("bas", "BAS");
        matrizSilabasComplexas.put("bel", "BEL");
        matrizSilabasComplexas.put("bem", "BEM");
        matrizSilabasComplexas.put("ber", "BER");
        matrizSilabasComplexas.put("bes", "BES");
        matrizSilabasComplexas.put("bil", "BIL");
        matrizSilabasComplexas.put("bin", "BIN");
        matrizSilabasComplexas.put("bir", "BIR");
        matrizSilabasComplexas.put("bis", "BIS");
        matrizSilabasComplexas.put("bol", "BOL");
        matrizSilabasComplexas.put("bom", "BOM");
        matrizSilabasComplexas.put("bor", "BOR");
        matrizSilabasComplexas.put("bos", "BOS");
        matrizSilabasComplexas.put("bum", "BUM");
        matrizSilabasComplexas.put("bur", "BUR");
        matrizSilabasComplexas.put("bus", "BUS");
        matrizSilabasComplexas.put("cães", "CÃES");
        matrizSilabasComplexas.put("cal", "CAL");
        matrizSilabasComplexas.put("cam", "CAM");
        matrizSilabasComplexas.put("can", "CAN");
        matrizSilabasComplexas.put("cão", "CÃO");
        matrizSilabasComplexas.put("cas", "CAS");
        matrizSilabasComplexas.put("cel", "CEL");
        matrizSilabasComplexas.put("cen", "CEN");
        matrizSilabasComplexas.put("cer", "CER");
        matrizSilabasComplexas.put("ces", "CES");
        matrizSilabasComplexas.put("che", "CHE");
        matrizSilabasComplexas.put("cin", "CIN");
        matrizSilabasComplexas.put("cir", "CIR");
        matrizSilabasComplexas.put("cis", "CIS");
        matrizSilabasComplexas.put("ções", "ÇÕES");
        matrizSilabasComplexas.put("col", "COL");
        matrizSilabasComplexas.put("com", "COM");
        matrizSilabasComplexas.put("con", "CON");
        matrizSilabasComplexas.put("cor", "COR");
        matrizSilabasComplexas.put("cos", "COS");
        matrizSilabasComplexas.put("cul", "CUL");
        matrizSilabasComplexas.put("cum", "CUM");
        matrizSilabasComplexas.put("cur", "CUR");
        matrizSilabasComplexas.put("cus", "CUS");
        matrizSilabasComplexas.put("cuz", "CUZ");
        matrizSilabasComplexas.put("dan", "DAN");
        matrizSilabasComplexas.put("dão", "DÃO");
        matrizSilabasComplexas.put("dar", "DAR");
        matrizSilabasComplexas.put("das", "DAS");
        matrizSilabasComplexas.put("den", "DEN");
        matrizSilabasComplexas.put("der", "DER");
        matrizSilabasComplexas.put("des", "DES");
        matrizSilabasComplexas.put("dim", "DIM");
        matrizSilabasComplexas.put("dis", "DIS");
        matrizSilabasComplexas.put("don", "DON");
        matrizSilabasComplexas.put("dor", "DOR");
        matrizSilabasComplexas.put("dos", "DOS");
        matrizSilabasComplexas.put("dum", "DUM");
        matrizSilabasComplexas.put("fal", "FAL");
        matrizSilabasComplexas.put("fan", "FAN");
        matrizSilabasComplexas.put("far", "FAR");
        matrizSilabasComplexas.put("fel", "FEL");
        matrizSilabasComplexas.put("fen", "FEN");
        matrizSilabasComplexas.put("fer", "FER");
        matrizSilabasComplexas.put("fes", "FES");

        matrizSilabasComplexas.put("fil", "FIL");

        matrizSilabasComplexas.put("fim", "FIM");
        matrizSilabasComplexas.put("fir", "FIR");
        matrizSilabasComplexas.put("fol", "FOL");

        matrizSilabasComplexas.put("fon", "FON");
        matrizSilabasComplexas.put("for", "FOR");
        matrizSilabasComplexas.put("fos", "FOS");
        matrizSilabasComplexas.put("fun", "FUN");

        matrizSilabasComplexas.put("fus", "FUS");

        matrizSilabasComplexas.put("gal", "GAL");
        matrizSilabasComplexas.put("gam", "GAM");
        matrizSilabasComplexas.put("gan", "GAN");
        matrizSilabasComplexas.put("gão", "GÃO");
        matrizSilabasComplexas.put("gar", "GAR");
        matrizSilabasComplexas.put("gel", "GEL");
        matrizSilabasComplexas.put("gem", "GEM");
        matrizSilabasComplexas.put("gen", "GEN");
        matrizSilabasComplexas.put("ger", "GER");

        matrizSilabasComplexas.put("gil", "GIL");
        matrizSilabasComplexas.put("gim", "GIM");
        matrizSilabasComplexas.put("gin", "GIN");
        matrizSilabasComplexas.put("gir", "GIR");

        matrizSilabasComplexas.put("gol", "GOL");
        matrizSilabasComplexas.put("gom", "GOM");
        matrizSilabasComplexas.put("gon", "GON");
        matrizSilabasComplexas.put("gor", "GOR");
        matrizSilabasComplexas.put("gua", "GUA");

        matrizSilabasComplexas.put("jan", "JAN");
        matrizSilabasComplexas.put("jão", "JÃO");
        matrizSilabasComplexas.put("jar", "JAR");
        matrizSilabasComplexas.put("jas", "JAS");
        matrizSilabasComplexas.put("jor", "JOR");

        matrizSilabasComplexas.put("jun", "JUN");

        matrizSilabasComplexas.put("jus", "JUS");

        matrizSilabasComplexas.put("lam", "LAM");
        matrizSilabasComplexas.put("lan", "LAN");
        matrizSilabasComplexas.put("lão", "LÃO");
        matrizSilabasComplexas.put("lar", "LAR");
        matrizSilabasComplexas.put("las", "LAS");

        matrizSilabasComplexas.put("lem", "LEM");
        matrizSilabasComplexas.put("len", "LEN");
        matrizSilabasComplexas.put("ler", "LER");

        matrizSilabasComplexas.put("lim", "LIM");
        matrizSilabasComplexas.put("lin", "LIN");

        matrizSilabasComplexas.put("lom", "LOM");
        matrizSilabasComplexas.put("lon", "LON");

        matrizSilabasComplexas.put("los", "LOS");

        matrizSilabasComplexas.put("lus", "LUS");
        matrizSilabasComplexas.put("luz", "LUZ");
        matrizSilabasComplexas.put("mal", "MAL");

        matrizSilabasComplexas.put("man", "MAN");
        matrizSilabasComplexas.put("mão", "MÃO");
        matrizSilabasComplexas.put("mãos", "MÃOS");
        matrizSilabasComplexas.put("mar", "MAR");

        matrizSilabasComplexas.put("mel", "MEL");
        matrizSilabasComplexas.put("mem", "MEM");
        matrizSilabasComplexas.put("men", "MEN");
        matrizSilabasComplexas.put("mer", "MER");
        matrizSilabasComplexas.put("mês", "MÊS");

        matrizSilabasComplexas.put("min", "MIN");
        matrizSilabasComplexas.put("mir", "MIR");
        matrizSilabasComplexas.put("mis", "MIS");

        matrizSilabasComplexas.put("mol", "MOL");

        matrizSilabasComplexas.put("mon", "MON");
        matrizSilabasComplexas.put("mor", "MOR");
        matrizSilabasComplexas.put("mos", "MOS");

        matrizSilabasComplexas.put("mul", "MUL");

        matrizSilabasComplexas.put("mun", "MUN");
        matrizSilabasComplexas.put("mur", "MUR");
        matrizSilabasComplexas.put("mus", "MUS");

        matrizSilabasComplexas.put("nal", "NAL");

        matrizSilabasComplexas.put("não", "NÃO");

        matrizSilabasComplexas.put("nas", "NAS");
        matrizSilabasComplexas.put("nel", "NEL");

        matrizSilabasComplexas.put("ner", "NER");

        matrizSilabasComplexas.put("nil", "NIL");
        matrizSilabasComplexas.put("nir", "NIR");

        matrizSilabasComplexas.put("noz", "NOZ");

        matrizSilabasComplexas.put("pães", "PÃES");
        matrizSilabasComplexas.put("pal", "PAL");

        matrizSilabasComplexas.put("pan", "PAN");
        matrizSilabasComplexas.put("pão", "PÃO");
        matrizSilabasComplexas.put("par", "PAR");
        matrizSilabasComplexas.put("pas", "PAS");
        matrizSilabasComplexas.put("paz", "PAZ");

        matrizSilabasComplexas.put("pel", "PEL");

        matrizSilabasComplexas.put("pen", "PEN");
        matrizSilabasComplexas.put("per", "PER");
        matrizSilabasComplexas.put("pes", "PES");
        matrizSilabasComplexas.put("pim", "PIM");

        matrizSilabasComplexas.put("pis", "PIS");

        matrizSilabasComplexas.put("pol", "POL");
        matrizSilabasComplexas.put("pom", "POM");
        matrizSilabasComplexas.put("pon", "PON");
        matrizSilabasComplexas.put("por", "POR");
        matrizSilabasComplexas.put("pos", "POS");

        matrizSilabasComplexas.put("pul", "PUL");
        matrizSilabasComplexas.put("pum", "PUM");

        matrizSilabasComplexas.put("ram", "RAM");
        matrizSilabasComplexas.put("ran", "RAN");
        matrizSilabasComplexas.put("ras", "RAS");

        matrizSilabasComplexas.put("rel", "REL");
        matrizSilabasComplexas.put("ren", "RES");
        matrizSilabasComplexas.put("rio", "RIO");
        matrizSilabasComplexas.put("rir", "RIR");
        matrizSilabasComplexas.put("ris", "RIS");
        matrizSilabasComplexas.put("rom", "ROM");
        matrizSilabasComplexas.put("ron", "RON");
        matrizSilabasComplexas.put("ros", "ROS");
        matrizSilabasComplexas.put("rou", "ROU");
        matrizSilabasComplexas.put("sal", "SAL");
        matrizSilabasComplexas.put("sam", "SAM");
        matrizSilabasComplexas.put("san", "SAN");
        matrizSilabasComplexas.put("são", "SÃO");
        matrizSilabasComplexas.put("sar", "SAR");
        matrizSilabasComplexas.put("sel", "SEL");
        matrizSilabasComplexas.put("sem", "SEM");
        matrizSilabasComplexas.put("sen", "SEN");
        matrizSilabasComplexas.put("ser", "SER");
        matrizSilabasComplexas.put("sim", "SIM");
        matrizSilabasComplexas.put("sis", "SIS");
        matrizSilabasComplexas.put("sol", "SOL");
        matrizSilabasComplexas.put("som", "SOM");
        matrizSilabasComplexas.put("son", "SON");
        matrizSilabasComplexas.put("sor", "SOR");
        matrizSilabasComplexas.put("sun", "SUN");
        matrizSilabasComplexas.put("sur", "SUR");
        matrizSilabasComplexas.put("sus", "SUS");
        matrizSilabasComplexas.put("tal", "TAL");
        matrizSilabasComplexas.put("tam", "TAM");
        matrizSilabasComplexas.put("tan", "TAN");
        matrizSilabasComplexas.put("tão", "TÃO");
        matrizSilabasComplexas.put("tar", "TAR");
        matrizSilabasComplexas.put("tas", "TAS");
        matrizSilabasComplexas.put("tel", "TEL");
        matrizSilabasComplexas.put("tem", "TEM");
        matrizSilabasComplexas.put("ten", "TEN");
        matrizSilabasComplexas.put("ter", "TER");
        matrizSilabasComplexas.put("tes", "TES");
        matrizSilabasComplexas.put("til", "TIL");
        matrizSilabasComplexas.put("tim", "TIM");
        matrizSilabasComplexas.put("tin", "TIN");
        matrizSilabasComplexas.put("tir", "TIR");
        matrizSilabasComplexas.put("tol", "TOL");
        matrizSilabasComplexas.put("tom", "TOM");
        matrizSilabasComplexas.put("ton", "TON");
        matrizSilabasComplexas.put("tor", "TOR");
        matrizSilabasComplexas.put("tum", "TUM");
        matrizSilabasComplexas.put("tur", "TUR");
        matrizSilabasComplexas.put("val", "VAL");
        matrizSilabasComplexas.put("vam", "VAM");
        matrizSilabasComplexas.put("vão", "VÃO");
        matrizSilabasComplexas.put("var", "VAR");
        matrizSilabasComplexas.put("vas", "VAS");
        matrizSilabasComplexas.put("vel", "VEL");
        matrizSilabasComplexas.put("vem", "VEM");
        matrizSilabasComplexas.put("ven", "VEN");
        matrizSilabasComplexas.put("ver", "VER");
        matrizSilabasComplexas.put("ves", "VES");
        matrizSilabasComplexas.put("vin", "VIN");
        matrizSilabasComplexas.put("vir", "VIR");
        matrizSilabasComplexas.put("vis", "VIS");
        matrizSilabasComplexas.put("von", "VON");
        matrizSilabasComplexas.put("vul", "VUL");
        matrizSilabasComplexas.put("xam", "XAM");
        matrizSilabasComplexas.put("xão", "XÃO");
        matrizSilabasComplexas.put("xar", "XAR");
        matrizSilabasComplexas.put("xer", "XER");
        matrizSilabasComplexas.put("xis", "XIS");
        matrizSilabasComplexas.put("zal", "ZAL");
        matrizSilabasComplexas.put("zão", "ZÃO");
        matrizSilabasComplexas.put("zar", "ZAR");
        matrizSilabasComplexas.put("zer", "ZER");
        matrizSilabasComplexas.put("zes", "ZES");
        matrizSilabasComplexas.put("zin", "ZIN");
        matrizSilabasComplexas.put("zol", "ZOL");
        matrizSilabasComplexas.put("zom", "ZOM");
        matrizSilabasComplexas.put("zul", "ZUL");
        matrizSilabasComplexas.put("zum", "ZUM");

    }

    /**
     * Inicia a matriz de silabas complexas 3
     */
    public void iniciarMatrizSilabasComplexas3() {
        matrizSilabasComplexas3.put("bra", "BRA");
        matrizSilabasComplexas3.put("bras", "BRAS");
        matrizSilabasComplexas3.put("bres", "BRES");
        matrizSilabasComplexas3.put("brin", "BRIN");
        matrizSilabasComplexas3.put("bron", "BRON");
        matrizSilabasComplexas3.put("bros", "BROS");
        matrizSilabasComplexas3.put("brus", "BRUS");
        matrizSilabasComplexas3.put("chão", "CHÃO");
        matrizSilabasComplexas3.put("clas", "CLAS");
        matrizSilabasComplexas3.put("clos", "CLOS");
        matrizSilabasComplexas3.put("cres", "CRES");
        matrizSilabasComplexas3.put("cris", "CRIS");
        matrizSilabasComplexas3.put("cros", "CROS");
        matrizSilabasComplexas3.put("crus", "CRUS");
        matrizSilabasComplexas3.put("guam", "GUAM");
        matrizSilabasComplexas3.put("guar", "GUAR");
        matrizSilabasComplexas3.put("lheu", "LHEU");
        matrizSilabasComplexas3.put("plas", "PLAS");
        matrizSilabasComplexas3.put("ples", "PLES");
        matrizSilabasComplexas3.put("pran", "PRAN");
        matrizSilabasComplexas3.put("pras", "PRAS");
        matrizSilabasComplexas3.put("pren", "PREN");
        matrizSilabasComplexas3.put("pres", "PRES");
        matrizSilabasComplexas3.put("prin", "PRIN");
        matrizSilabasComplexas3.put("pron", "PRON");
        matrizSilabasComplexas3.put("tlas", "TLAS");
        matrizSilabasComplexas3.put("tras", "TRAS");
        matrizSilabasComplexas3.put("tres", "TRES");
        matrizSilabasComplexas3.put("tris", "TRIS");
        matrizSilabasComplexas3.put("tros", "TROS");
    }

    public void iniciarMatrizPalavrasComplexas() {
        matrizPalavrasComplexas.put("abelha", "ABELHA");
        matrizPalavrasComplexas.put("aberto", "ABERTO");
        matrizPalavrasComplexas.put("alegria", "ALEGRIA");
        matrizPalavrasComplexas.put("animal", "ANIMAL");
        matrizPalavrasComplexas.put("antena", "ANTENA");
        matrizPalavrasComplexas.put("apontador", "APONTADOR");
        matrizPalavrasComplexas.put("aranha", "ARANHA");
        matrizPalavrasComplexas.put("arroz", "ARROZ");
        matrizPalavrasComplexas.put("azul", "AZUL");
        matrizPalavrasComplexas.put("banha", "BANHA");
        matrizPalavrasComplexas.put("barco", "BARCO");
        matrizPalavrasComplexas.put("batom", "BATOM");
        matrizPalavrasComplexas.put("bíblia", "BÍBLIA");
        matrizPalavrasComplexas.put("biblioteca", "BIBLIOTECA");
        matrizPalavrasComplexas.put("bicicleta", "BICICLETA");
        matrizPalavrasComplexas.put("biscoito", "BISCOITO");
        matrizPalavrasComplexas.put("bloco", "BLOCO");
        matrizPalavrasComplexas.put("blusa", "BLUSA");
        matrizPalavrasComplexas.put("bolha", "BOLHA");
        matrizPalavrasComplexas.put("borboleta", "BORBOLETA");
        matrizPalavrasComplexas.put("braço", "BRAÇO");
        matrizPalavrasComplexas.put("brasil", "BRASIL");
        matrizPalavrasComplexas.put("brinco", "BRINCO");
        matrizPalavrasComplexas.put("brinquedo", "BRINQUEDO");
        matrizPalavrasComplexas.put("bronca", "BRONCA");
        matrizPalavrasComplexas.put("bruxa", "BRUXA");
        matrizPalavrasComplexas.put("cachorro", "CACHORRO");
        matrizPalavrasComplexas.put("caderno", "CADERNO");
        matrizPalavrasComplexas.put("calça", "CALÇA");
        matrizPalavrasComplexas.put("carro", "CARRO");
        matrizPalavrasComplexas.put("carroça", "CARROÇA");
        matrizPalavrasComplexas.put("cenoura", "CENOURA");
        matrizPalavrasComplexas.put("chinelo", "CHINELO");
        matrizPalavrasComplexas.put("chocolate", "CHOCOLATE");
        matrizPalavrasComplexas.put("cobertor", "COBERTOR");
        matrizPalavrasComplexas.put("cobra", "COBRA");
        matrizPalavrasComplexas.put("computador", "COMPUTADOR");
        matrizPalavrasComplexas.put("engraçado", "ENGRAÇADO");
        matrizPalavrasComplexas.put("espinho", "ESPINHO");
        matrizPalavrasComplexas.put("farol", "FAROL");
        matrizPalavrasComplexas.put("fechado", "FECHADO");
        matrizPalavrasComplexas.put("ficha", "FICHA");
        matrizPalavrasComplexas.put("filho", "FILHO");
        matrizPalavrasComplexas.put("flauta", "FLAUTA");
        matrizPalavrasComplexas.put("flor", "FLOR");
        matrizPalavrasComplexas.put("floresta", "FLORESTA");
        matrizPalavrasComplexas.put("foguete", "FOGUETE");
        matrizPalavrasComplexas.put("folha", "FOLHA");
        matrizPalavrasComplexas.put("fralda", "FRALDA");
        matrizPalavrasComplexas.put("fruta", "FRUTA");
        matrizPalavrasComplexas.put("galinha", "GALINHA");
        matrizPalavrasComplexas.put("girassol", "GIRASSOL.");
        matrizPalavrasComplexas.put("gravata", "GRAVATA");
        matrizPalavrasComplexas.put("graveto", "GRAVETO");
        matrizPalavrasComplexas.put("guitarra", "GUITARRA");
        matrizPalavrasComplexas.put("impressora", "IMPRESSORA");
        matrizPalavrasComplexas.put("jardim", "JARDIM");
        matrizPalavrasComplexas.put("jegue", "JEGUE");
        matrizPalavrasComplexas.put("joelho", "JOELHO");
        matrizPalavrasComplexas.put("lâmpada", "LÂMPADA");
        matrizPalavrasComplexas.put("laranja", "LARANJA");
        matrizPalavrasComplexas.put("leão", "LEÃO");
        matrizPalavrasComplexas.put("limão", "LIMÃO");
        matrizPalavrasComplexas.put("língua", "LÍNGUA");
        matrizPalavrasComplexas.put("linha", "LINHA");
        matrizPalavrasComplexas.put("livro", "LIVRO");
        matrizPalavrasComplexas.put("malvado", "MALVADO");
        matrizPalavrasComplexas.put("mamão", "MAMÃO");
        matrizPalavrasComplexas.put("maravilha", "MARAVILHA");
        matrizPalavrasComplexas.put("minhoca", "MINHOCA");
        matrizPalavrasComplexas.put("mochila", "MOCHILA");
        matrizPalavrasComplexas.put("nariz", "NARIZ");
        matrizPalavrasComplexas.put("ninho", "NINHO");
        matrizPalavrasComplexas.put("noivo", "NOIVO");
        matrizPalavrasComplexas.put("nublado", "NUBLADO");
        matrizPalavrasComplexas.put("olho", "OLHO");
        matrizPalavrasComplexas.put("palhaço", "PALHAÇO");
        matrizPalavrasComplexas.put("passagem", "PASSAGEM");
        matrizPalavrasComplexas.put("passarinho", "PASSARINHO");
        matrizPalavrasComplexas.put("peixe", "PEIXE");
        matrizPalavrasComplexas.put("pilha", "PILHA");
        matrizPalavrasComplexas.put("pinguim", "PINGUIM");
        matrizPalavrasComplexas.put("placa", "PLACA");
        matrizPalavrasComplexas.put("planta", "PLANTA");
        matrizPalavrasComplexas.put("praça", "PRAÇA");
        matrizPalavrasComplexas.put("prato", "PRATO");
        matrizPalavrasComplexas.put("princesa", "PRINCESA");
        matrizPalavrasComplexas.put("príncipe", "PRÍNCIPE");
        matrizPalavrasComplexas.put("pulseira", "PULSEIRA");
        matrizPalavrasComplexas.put("régua", "RÉGUA");
        matrizPalavrasComplexas.put("retrato", "RETRATO");
        matrizPalavrasComplexas.put("segredo", "SEGREDO");
        matrizPalavrasComplexas.put("sol", "SOL");
        matrizPalavrasComplexas.put("sonho", "SONHO");
        matrizPalavrasComplexas.put("tartaruga", "TARTARUGA");
        matrizPalavrasComplexas.put("telefone", "TELEFONE");
        matrizPalavrasComplexas.put("terra", "TERRA");
        matrizPalavrasComplexas.put("tigre", "TIGRE");
        matrizPalavrasComplexas.put("tornozelo", "TORNOZELO");
        matrizPalavrasComplexas.put("vidro", "VIDRO");

    }

    /**
     * Retorna a chave da HashMap correspondente ao valor que é passado como
     * parâmetro
     *
     * @param <T>
     * @param <E>
     * @param map HasMap que na qual desaja-se pesquisar a chave
     * @param value valor que terá uma chave relacionada na hashMap
     * @return a chave correspondente ao valor pesquisado (null caso a chave não
     * exista)
     */
    //função que recebe o valor associado à chave e retorna a chave
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {//mapeia as chaves e valores em um pseudo-array
            if (Objects.equals(value, entry.getValue())) {//verifica se o valor passado como parâmetro é igual ao valor atual da entrada
                return entry.getKey(); //retorna a entrada que for igual
            }
        }
        return null;
    }

    /**
     * Incrementa, em valores de 10, a quantidade de pontos do jagador
     */
    public void incrementarPontuacao() {
        //valor que será acrescentado à pontuação do jogador
        int valorAcrescentar = 5;
        //pega a pontuação no label da pontuação
        int pontuacaoAnterior = Integer.parseInt(pontuacao.getText());

        //gera a nova pontuação somando o valor que deve ser acrescentado à
        //pontuação anterior
        int novaPontuacao = pontuacaoAnterior + valorAcrescentar;
        //atualiza a pontuação do jogador
        jogador.setPontuacaoTotal(novaPontuacao);
        //atualiza o label
        pontuacao.setText("" + novaPontuacao);
    }

    /**
     * Incrementa a quantidade de acertos do jogador
     */
    public void incrementarAcerto() {
        setIndicacaoPular(true);
        jogador.setAcertosTotal(jogador.getAcertosTotal() + 1);

    }

    /**
     * OBS: O BOTÃO SELECIONADO AQUI DEVERÁ MUDAR DE COR OU TER OUTRA FORMA DE
     * DESTAQUE QUE MOSTRE QUE A OPÇÃO ESTÁ ERRADA
     *
     * @param event
     * @return
     */
    public Button opcaoCorreta(ActionEvent event) throws NullPointerException {
        Button temporario = null;
        String opcaoCorreta = "";
        //pega o valor da opção correta
        switch (jogador.getFaseAtual()) {
            case 1:
                opcaoCorreta = matrizVogais.get(nomeAudioAtual);
                break;
            case 2:
                opcaoCorreta = matrizSilabasSimples.get(nomeAudioAtual);
                break;
            case 3:
                opcaoCorreta = matrizPalavrasSimples.get(nomeAudioAtual);
                break;
            case 4:
                opcaoCorreta = matrizSilabasComplexas.get(nomeAudioAtual);
                break;
            case 5:
                opcaoCorreta = matrizSilabasComplexas2.get(nomeAudioAtual);
                break;
            case 6:
                opcaoCorreta = matrizSilabasComplexas3.get(nomeAudioAtual);
                break;
            case 7:
                opcaoCorreta = matrizPalavrasComplexas.get(nomeAudioAtual);
                break;
        }

        try {
            //verifica quais dos botões é a opção correta
            if (opcaoCorreta.equals(btn_1.getText())) {
                temporario = btn_1;
            } else if (opcaoCorreta.equals(btn_2.getText())) {
                temporario = btn_2;
            } else if (opcaoCorreta.equals(btn_3.getText())) {
                temporario = btn_3;
            } else if (opcaoCorreta.equals(btn_4.getText())) {
                temporario = btn_4;
            } else if (opcaoCorreta.equals(btn_5.getText())) {
                temporario = btn_5;
            }
        } catch (NullPointerException e) {

        }

        return temporario;
    }

    /**
     * Aumenta em uma unidade a quantidade de erros do jogador
     */
    public void incrementarErro() {
        //incrementa a quantidade de erros do jogador
        jogador.setQntErros(jogador.getQntErros() + 1);
        jogador.setBarraVida(jogador.getBarraVida() + 1);
    }

    /**
     * Verifica se é o fim do jogo para o jogador
     *
     * @return true se sim, do contrário false
     */
    public boolean isGameOver() {
        boolean fimDeJogo = false;
        if (jogador.getQntErros() == 6) {//se o jogador errou 5 vezes
            fimDeJogo = true;
        }
        return fimDeJogo;
    }

    /**
     * Exibe uma animação mostrando qual seria a opção que deveria ter sido
     * escolhida pelo jogador
     *
     * @param temp opção correta
     */
    public void mostrarOpcaoCorreta(Button temp) {
        //evento final que realiza uma chamada ao método que gera uma opcao aleatoria
        gerarProximaRodada = (ActionEvent arg0) -> {
            try {
                gerarOpcaoAleatoria();

            } catch (InterruptedException | IOException ex) {
                Logger.getLogger(ModelJogoPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        };
        //evento que reinicia seta o valor boleano que indica que o relógio deve ser reiniciado
        reiniciarRelogio = (ActionEvent event) -> {
            setIndicacaoPular(true);
            funcao.setClique1();
        };
        //animação
        new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(temp.opacityProperty(), .1)),
                new KeyFrame(Duration.seconds(1), new KeyValue(temp.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(1), gerarProximaRodada),
                new KeyFrame(Duration.seconds(1), reiniciarRelogio)).play();

    }

    /**
     * Exibe uma animação mostrando qual a opção que deveria ter sido escolhida
     * pelo jogador e altera a interface para a interface do gameOver
     *
     * @param temp a opção correta
     */
    public void mostraFimDeJogo(Button temp) {

        //evento responsável por exibir a janela de GAME OVER 
        eventoGameOver = (ActionEvent event) -> {
            janela = (Stage) btn_1.getScene().getWindow();
            Parent cenaPrincipal = null;
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Gui_GameOver.fxml"));
            try {
                cenaPrincipal = (Parent) fxmloader.load();

            } catch (IOException ex) {
                Logger.getLogger(Model_SequenciaCenas.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            Gui_GameOverController gameOver = fxmloader.<Gui_GameOverController>getController();
            gameOver.definirPontuacaoFinal(jogador.getPontuacaoTotal());

            Scene scene = new Scene(cenaPrincipal, 1200, 700);
            janela.setTitle("Legere");
            janela.setScene(scene);
            janela.setResizable(false);
            janela.show();
        };
        new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(temp.opacityProperty(), .1)),
                new KeyFrame(Duration.seconds(1), new KeyValue(temp.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(1), eventoGameOver)).play();
    }

    /**
     * Executa o audio referente à rodada atual
     *
     * @param n nome do arquivo sem a extensão
     */
    public void tocarAudio(String n) {
        System.out.println("Nome " + n);
        setNomeAudioAtual(n);//define o nome atual do áudio que está sendo utilizado
        switch (jogador.getFaseAtual()) {//pega a fase atual do jogador
            case 1:
                caminhoAudio = "audios_vogais/" + n + ".mp3";
                break;
            case 2:
                caminhoAudio = "audios_silabas_simples/" + n + ".mp3";
                break;
            case 3:
                caminhoAudio = "audios_palavras_simples/" + n + ".mp3";
                break;
            case 4:
                caminhoAudio = "audios_silabas_complexas/" + n + ".mp3";
                break;
            case 5:
                caminhoAudio = "audios_silabas_complexas2/" + n + ".mp3";
                break;
            case 6:
                caminhoAudio = "audios_silabas_complexas3/" + n + ".mp3";
                break;
            case 7:
                caminhoAudio = "audios_palavras_complexas/" + n + ".mp3";
                break;
        }
        URL file = getClass().getResource(caminhoAudio);
        media = new Media(file.toString());
        mediaPlayer = new MediaPlayer(media);        
        mediaPlayer.play();
        System.out.println("status tocar " + mediaPlayer.getStatus() + "  " + mediaPlayer.getStatus().equals(Status.PLAYING));
        System.out.println((long) mediaPlayer.getTotalDuration().toMillis());

    }

    /**
     * Define o áudio atual
     *
     * @param n nome do áudio
     */
    public void setNomeAudioAtual(String n) {
        this.nomeAudioAtual = n;
    }

    /**
     * Retorna o nome do áudio atual
     *
     * @return string contendo o nome áudio atual
     */
    public String getAudioAtual() {
        return this.nomeAudioAtual;
    }

    /**
     * Define se o jogo está mostrando a cena referente aos 10 acertos
     *
     * @param valor true ou false
     */
    public void setMostrandoCena(boolean valor) {
        this.mostrandoCena = valor;
    }

    /**
     * Retorna se a cena referente aos 10 acertos está sendo exibida
     *
     * @return true se sim. false caso contrário
     */
    public boolean getMostrandoCena() {
        return mostrandoCena;
    }

    /**
     * Mostra as cenas após o jogador acertar 15 vezes
     *
     * @throws InterruptedException
     * @throws IOException
     */
    public void mostrarCenas() throws InterruptedException, IOException {

        //evento responsável por exibir as cenas de progresso na história
        eventoCenas = (ActionEvent event) -> {
            //armazena a cena em que o botão 'btn_1' se encontra atualmente
            janela = (Stage) btn_1.getScene().getWindow();
            //armeza a cena do botão 'btn_1' em uma variável temporária
            cenaTemporaria = btn_1.getScene();
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Gui_SequenciaCenas.fxml"));
            Parent proximaCena = null;
            try {
                proximaCena = (Parent) fxmloader.load();

            } catch (IOException ex) {
                Logger.getLogger(ModelJogoPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            sequenciaCenas = fxmloader.<Gui_SequenciaCenasController>getController();
            //cria uma cena
            Scene cena = new Scene(proximaCena, 1200, 700);
            janela.setTitle("Legere");//título da cena
            janela.setScene(cena);
            janela.setResizable(false);
            janela.show();//exibe a cena
            sequenciaCenas.setFaseAtual(jogador.getFaseAtual());
            System.out.println("PONTUACAO " + jogador.getPontuacaoTotal());
            sequenciaCenas.setPontuacao(jogador.getPontuacaoTotal());
            sequenciaCenas.definirImagemFundo();
            sequenciaCenas.iniciarCena();

        };

        //evento para voltar para o jogo pós exibição da cena
        eventoVoltar = (ActionEvent event) -> {
            try {
                System.out.println("Pulaintro " + sequenciaCenas.getPulandoIntro());
            } catch (InterruptedException ex) {
                Logger.getLogger(ModelJogoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ModelJogoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            jogador.setFaseAtual(jogador.getFaseAtual() + 1);
            setMostrandoCena(true);
            janela.setResizable(false);
            numFase.setText("Fase: " + jogador.getFaseAtual() + "/7");
        };
        eventoFimAcerto = (ActionEvent event) -> {
            mostrarCenaInicialFase(janela);
        };
        //animação que exibe as cenas e volta para a interface principal do jogo
        new Timeline(
                new KeyFrame(Duration.seconds(0), eventoCenas),
                new KeyFrame(Duration.seconds(12), eventoVoltar),
                new KeyFrame(Duration.seconds(12), eventoFimAcerto)).play();

    }

    /**
     * Define o valor da variável indicacaoPular
     *
     * @param valor true ou false
     */
    public void setIndicacaoPular(boolean valor) {
        this.indicacaoPular = valor;
    }

    /**
     * Define o valor da variável pularErro
     *
     * @param valor true ou false
     */
    public void setPularErro(boolean valor) {
        this.pularErro = valor;
    }

    /**
     * Retorna o valor de indicacaoPular
     *
     * @return indicacaoPular
     */
    public boolean getIndicacaoPular() {
        return indicacaoPular;
    }

    /**
     * Retorna o valor da variável pularErro
     *
     * @return true ou false
     */
    public boolean getPularErro() {
        return pularErro;
    }

    /**
     * Inicia o timer
     */
    public void iniciarTimer() {
        timer = new Timer();
        //criação da tarefa que vai executar durante 1 segundo
        timer.scheduleAtFixedRate(new TimerTask() {

            int i = 30;

            @Override
            public void run() {

                //Platform.runLater para alterar elementos da interface do javaFX
                Platform.runLater(() -> {
                    /*condição que faz o contador de segundos
                     continuar em 30 durante a exibição da cena
                     */
                    Timer timer2 = new Timer();
                    timer2.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (getMostrandoCena()) {
                                i = 30;
                            }

                            if (getIndicacaoPular()) {
                                i = i + 0;
                            }

                        }
                    }, 0, 50);

                    if (i >= 10) {
                        tempo.setText(i + "s");
                    } else {
                        tempo.setText(" " + i + "s");
                    }

                    if (getMostrandoDica()) {
                        i = i + 1;
                    }

                    i--;

                    if (i == -1) {
                        correto = opcaoCorreta(null);
                        //se o jogador perdeu o jogo exibir a tela de game over
                        if (!isGameOver()) {
                            System.out.println("timer cacelado");

                            reduzirLifeBar();
                            incrementarErro();
                            mostrarOpcaoCorreta(correto);//animação
                            setPularErro(true);
                        } else {
                            System.out.println("Entrou aqui essa bosta");

                            //animação
                        }
                    }
                    //se o jogador pulou ou errou voltar o tempo para 30 segundos
                    if ((getIndicacaoPular()) || (getPularErro())) {
                        //System.out.println(i);
                        i = 30;
                        //indicacaoPular = false;
                        setIndicacaoPular(false);
                        setPularErro(false);
                        //pularErro = false;
                        if (isGameOver()) {
                            mostraFimDeJogo(correto);
                            timer.cancel();
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    /**
     * Mostra a animação de acerto
     *
     * @param ev
     */
    public void mostrarAnimacaoAcerto(ActionEvent ev) {
        //evento que represanta a ação do acerto
        //MUDA A COR DO BOTÃO

        switch (jogador.getFaseAtual()) {
            case 1:
                eventoAcerto = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "    linear-gradient(#97ff5b, #54e600),\n"
                            + "    linear-gradient(#b1ff83, #83f143),\n"
                            + "    linear-gradient(#a0ff69, #6def22),\n"
                            + "    linear-gradient(#57ff6a 0%, #02f80e 50%, #12ee0a 100%),\n"
                            + "    linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
                            + "    -fx-font-size: 35px;\n"
                            + "    -fx-pref-height: 88px;\n"
                            + "    -fx-pref-width: 96px;\n");

                };
                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "     linear-gradient(#ffd65b, #e68400)\n"
                            + "     linear-gradient(#ffef84, #f2ba44),\n"
                            + "     linear-gradient(#ffea6a, #efaa22),\n"
                            + "     linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                            + "     linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
                            + "    -fx-background-radius: 30;\n"
                            + "    -fx-background-insets: 0,1,2,3,0;\n"
                            + "    -fx-text-fill: #654b00;\n"
                            + "    -fx-font-weight: bold;\n"
                            + "    -fx-padding: 10 20 10 20;"
                            + "    -fx-font-size: 35px;\n"
                            + "    -fx-pref-height: 80px;\n"
                            + "    -fx-pref-width: 88px;\n"
                    );
                    //ao terminar a animação desbloquear os botões
                    funcao.setClique1();
                };
                break;

            case 2:
                eventoAcerto = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "    linear-gradient(#97ff5b, #54e600),\n"
                            + "    linear-gradient(#b1ff83, #83f143),\n"
                            + "    linear-gradient(#a0ff69, #6def22),\n"
                            + "    linear-gradient(#57ff6a 0%, #02f80e 50%, #12ee0a 100%),\n"
                            + "    linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 108px;\n"
                            + "    -fx-pref-height: 78px;\n");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "     linear-gradient(#ffd65b, #e68400)\n"
                            + "     linear-gradient(#ffef84, #f2ba44),\n"
                            + "     linear-gradient(#ffea6a, #efaa22),\n"
                            + "     linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                            + "     linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
                            + "    -fx-background-radius: 30;\n"
                            + "    -fx-background-insets: 0,1,2,3,0;\n"
                            + "    -fx-text-fill: #654b00;\n"
                            + "    -fx-font-weight: bold;\n"
                            + "    -fx-padding: 10 20 10 20;"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 100px;\n"
                            + "    -fx-pref-height: 70px;\n"
                    );
                    //ao terminar a animação desbloquear os botões
                    funcao.setClique1();
                };
                break;

            case 3:

                eventoAcerto = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "    linear-gradient(#97ff5b, #54e600),\n"
                            + "    linear-gradient(#b1ff83, #83f143),\n"
                            + "    linear-gradient(#a0ff69, #6def22),\n"
                            + "    linear-gradient(#57ff6a 0%, #02f80e 50%, #12ee0a 100%),\n"
                            + "    linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 158px;\n"
                            + "    -fx-pref-height: 78px;\n");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "     linear-gradient(#ffd65b, #e68400)\n"
                            + "     linear-gradient(#ffef84, #f2ba44),\n"
                            + "     linear-gradient(#ffea6a, #efaa22),\n"
                            + "     linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                            + "     linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
                            + "    -fx-background-radius: 30;\n"
                            + "    -fx-background-insets: 0,1,2,3,0;\n"
                            + "    -fx-text-fill: #654b00;\n"
                            + "    -fx-font-weight: bold;\n"
                            + "    -fx-padding: 10 20 10 20;"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 150px;\n"
                            + "    -fx-pref-height: 70px;\n"
                    );
                    //ao terminar a animação desbloquear os botões
                    funcao.setClique1();
                };
                break;

            case 4:
                eventoAcerto = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "    linear-gradient(#97ff5b, #54e600),\n"
                            + "    linear-gradient(#b1ff83, #83f143),\n"
                            + "    linear-gradient(#a0ff69, #6def22),\n"
                            + "    linear-gradient(#57ff6a 0%, #02f80e 50%, #12ee0a 100%),\n"
                            + "    linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 108px;\n"
                            + "    -fx-pref-height: 78px;\n");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "     linear-gradient(#ffd65b, #e68400)\n"
                            + "     linear-gradient(#ffef84, #f2ba44),\n"
                            + "     linear-gradient(#ffea6a, #efaa22),\n"
                            + "     linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                            + "     linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
                            + "    -fx-background-radius: 30;\n"
                            + "    -fx-background-insets: 0,1,2,3,0;\n"
                            + "    -fx-text-fill: #654b00;\n"
                            + "    -fx-font-weight: bold;\n"
                            + "    -fx-padding: 10 20 10 20;"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 100px;\n"
                            + "    -fx-pref-height: 70px;\n"
                    );
                    //ao terminar a animação desbloquear os botões
                    funcao.setClique1();
                };
                break;
            case 5:
                eventoAcerto = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "    linear-gradient(#97ff5b, #54e600),\n"
                            + "    linear-gradient(#b1ff83, #83f143),\n"
                            + "    linear-gradient(#a0ff69, #6def22),\n"
                            + "    linear-gradient(#57ff6a 0%, #02f80e 50%, #12ee0a 100%),\n"
                            + "    linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 108px;\n"
                            + "    -fx-pref-height: 78px;\n");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "     linear-gradient(#ffd65b, #e68400)\n"
                            + "     linear-gradient(#ffef84, #f2ba44),\n"
                            + "     linear-gradient(#ffea6a, #efaa22),\n"
                            + "     linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                            + "     linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
                            + "    -fx-background-radius: 30;\n"
                            + "    -fx-background-insets: 0,1,2,3,0;\n"
                            + "    -fx-text-fill: #654b00;\n"
                            + "    -fx-font-weight: bold;\n"
                            + "    -fx-padding: 10 20 10 20;"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 100px;\n"
                            + "    -fx-pref-height: 70px;\n"
                    );
                    //ao terminar a animação desbloquear os botões
                    funcao.setClique1();
                };
                break;
            default:
                eventoAcerto = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "    linear-gradient(#97ff5b, #54e600),\n"
                            + "    linear-gradient(#b1ff83, #83f143),\n"
                            + "    linear-gradient(#a0ff69, #6def22),\n"
                            + "    linear-gradient(#57ff6a 0%, #02f80e 50%, #12ee0a 100%),\n"
                            + "    linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 108px;\n"
                            + "    -fx-pref-height: 78px;\n");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "     linear-gradient(#ffd65b, #e68400)\n"
                            + "     linear-gradient(#ffef84, #f2ba44),\n"
                            + "     linear-gradient(#ffea6a, #efaa22),\n"
                            + "     linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                            + "     linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
                            + "    -fx-background-radius: 30;\n"
                            + "    -fx-background-insets: 0,1,2,3,0;\n"
                            + "    -fx-text-fill: #654b00;\n"
                            + "    -fx-font-weight: bold;\n"
                            + "    -fx-padding: 10 20 10 20;"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 100px;\n"
                            + "    -fx-pref-height: 70px;\n"
                    );
                    //ao terminar a animação desbloquear os botões
                    funcao.setClique1();
                };
                break;

            case 7:

                eventoAcerto = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "    linear-gradient(#97ff5b, #54e600),\n"
                            + "    linear-gradient(#b1ff83, #83f143),\n"
                            + "    linear-gradient(#a0ff69, #6def22),\n"
                            + "    linear-gradient(#57ff6a 0%, #02f80e 50%, #12ee0a 100%),\n"
                            + "    linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 175px;\n"
                            + "    -fx-pref-height: 78px;\n");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = opcaoCorreta(event);
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "     linear-gradient(#ffd65b, #e68400)\n"
                            + "     linear-gradient(#ffef84, #f2ba44),\n"
                            + "     linear-gradient(#ffea6a, #efaa22),\n"
                            + "     linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                            + "     linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
                            + "    -fx-background-radius: 30;\n"
                            + "    -fx-background-insets: 0,1,2,3,0;\n"
                            + "    -fx-text-fill: #654b00;\n"
                            + "    -fx-font-weight: bold;\n"
                            + "    -fx-padding: 10 20 10 20;"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 150px;\n"
                            + "    -fx-pref-height: 70px;\n"
                    );
                    //ao terminar a animação desbloquear os botões
                    funcao.setClique1();
                };
                break;

        }
        //evento que representa a ação a ser feita depois da 
        //animação de acerto
        eventoFimAcerto = (ActionEvent event) -> {
            setIndicacaoPular(true);
            try {
                gerarOpcaoAleatoria();

            } catch (InterruptedException | IOException ex) {
                Logger.getLogger(Gui_JogoPrincipalController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        };

        new Timeline(
                new KeyFrame(Duration.seconds(0), eventoAcerto),
                new KeyFrame(Duration.seconds(1), eventoCorOriginal),
                new KeyFrame(Duration.millis(1300), eventoFimAcerto)).play();

    }

    /**
     * Exibe a animação que deve acontecer quando o jogador errar
     *
     * @param ev
     */
    public void mostrarAnimacaoErro(ActionEvent ev) {
        switch (jogador.getFaseAtual()) {
            case 1:
                eventoErro = (ActionEvent event) -> {
                    //MUDA A COR DO BOTÃO
                    Button btemp = (Button) ev.getSource();
                    btemp.setId("btemp_fe1");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = (Button) ev.getSource();
                    btemp.setId("btemp_fe11");
                    funcao.setClique1();
                };

                break;

            case 2:
                eventoErro = (ActionEvent event) -> {
                    //MUDA A COR DO BOTÃO
                    Button btemp = (Button) ev.getSource();
                    btemp.setId("btemp_fe2");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = (Button) ev.getSource();
                    btemp.setId("btemp_fe21");
                    //ao terminar a animação desbloquear os botões
                    funcao.setClique1();
                };

                break;

            case 3:

                eventoErro = (ActionEvent event) -> {
                    //MUDA A COR DO BOTÃO
                    Button btemp = (Button) ev.getSource();
                    //(btemp).setText("X");
                    btemp.setId("btemp_fe2");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = (Button) ev.getSource();
                    //(btemp).setText("X");
                    btemp.setId("btemp_fe21");
                    //ao terminar a animação desbloquear os botões
                    funcao.setClique1();
                };
                break;

            case 4:
                eventoErro = (ActionEvent event) -> {
                    //MUDA A COR DO BOTÃO
                    Button btemp = (Button) ev.getSource();
                    btemp.setId("btemp_fe4");
                };
                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = (Button) ev.getSource();
                    btemp.setId("btemp_fe41");
                    //ao terminar a animação desbloquear os botões
                    funcao.setClique1();
                };
                break;

            case 5:
                eventoErro = (ActionEvent event) -> {
                    //MUDA A COR DO BOTÃO
                    Button btemp = (Button) ev.getSource();
                    btemp.setId("btemp_fe2");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = (Button) ev.getSource();
                    btemp.setId("btemp_fe21");
                    //ao terminar a animação desbloquear os botões
                    funcao.setClique1();
                };
                break;
            case 6:
                eventoErro = (ActionEvent event) -> {
                    //MUDA A COR DO BOTÃO
                    Button btemp = (Button) ev.getSource();
                    btemp.setId("btemp_fe2");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = (Button) ev.getSource();
                    btemp.setId("btemp_fe21");
                    //ao terminar a animação desbloquear os botões
                    funcao.setClique1();
                };
                break;
            case 7:
                eventoErro = (ActionEvent event) -> {
                    //MUDA A COR DO BOTÃO
                    Button btemp = (Button) ev.getSource();
                    btemp.setId("btemp_fe2");
                };
                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = (Button) ev.getSource();
                    btemp.setId("btemp_fe21");
                    //ao terminar a animação desbloquear os botões
                    funcao.setClique1();
                };
                break;
        }

        new Timeline(
                new KeyFrame(Duration.seconds(0), eventoErro),
                new KeyFrame(Duration.seconds(1), eventoCorOriginal)).play();

    }

    /**
     * Mostra a cena final depois que o jogador terminou a fase
     *
     * @throws MalformedURLException
     */
    public void mostrarCenaFinalFase() throws MalformedURLException {
        //evento responsável por exibir as cenas de progresso na história
        eventoCenas = (ActionEvent event) -> {
            System.out.println("Mostrando cena final fase");
            //armazena a cena em que o botão 'btn_1' se encontra atualmente
            janela = (Stage) btn_1.getScene().getWindow();
            Parent cenaPrincipal = null;
            //armeza a cena do botão 'btn_1' em uma variável temporária
            cenaTemporaria = btn_1.getScene();
            //mostrandoCena = false;
            setMostrandoCena(true);

            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Gui_SequenciaCenas.fxml"));
            Parent proximaCena = null;
            try {
                proximaCena = (Parent) fxmloader.load();

            } catch (IOException ex) {
                Logger.getLogger(ModelJogoPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            sequenciaCenas = fxmloader.<Gui_SequenciaCenasController>getController();
            //cria uma cena
            Scene cena = new Scene(proximaCena, 1200, 700);
            janela.setTitle("Legere");//título da cena
            janela.setScene(cena);
            janela.setResizable(false);
            janela.show();//exibe a cena
            sequenciaCenas.setFaseAtual(jogador.getFaseAtual());
            sequenciaCenas.definirImagemFundo();
            //funcionando
            sequenciaCenas.executarCenaFimFase();

        };
        //evento que mostra a cena do início da fase
        eventoInicioFase = (ActionEvent event) -> {
            sequenciaCenas.executarCenaMeioFase();
        };

        eventoVoltar = (ActionEvent event) -> {
            janela.setTitle("Legere");
            janela.setScene(cenaTemporaria);
            //mostrandoCena = false;
            setMostrandoCena(false);
            //eventoAcerto.handle(null);
            Button btemp = opcaoCorreta(null);
            janela.show();
            jogador.setFaseAtual(jogador.getFaseAtual() + 1);
            try {
                gerarOpcaoAudio();

            } catch (IOException ex) {
                Logger.getLogger(ModelJogoPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        };

        new Timeline(
                new KeyFrame(Duration.seconds(0), eventoCenas),
                new KeyFrame(Duration.seconds(15), eventoInicioFase),
                new KeyFrame(Duration.seconds(25), eventoVoltar)).play();

    }

    /**
     * Mostra a cena inicial antes de cada fase
     *
     * @param jan
     */
    public void mostrarCenaInicialFase(Stage jan) {
        tempoFase = sequenciaCenas.getTempoFase(jogador.getFaseAtual());
        //evento responsável por exibir as cenas de progresso na história
        eventoCenas = (ActionEvent event) -> {
            //armazena a cena em que o botão 'btn_1' se encontra atualmente
            this.janela = jan;
            Parent cenaPrincipal = null;
            //armeza a cena do botão 'btn_1' em uma variável temporária
            cenaTemporaria = btn_1.getScene();
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Gui_SequenciaCenas.fxml"));
            Parent proximaCena = null;
            try {
                proximaCena = (Parent) fxmloader.load();

            } catch (IOException ex) {
                Logger.getLogger(ModelJogoPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            sequenciaCenas = fxmloader.<Gui_SequenciaCenasController>getController();
            //cria uma cena
            Scene cena = new Scene(proximaCena, 1200, 700);
            janela.setTitle("Legere");//título da cena
            janela.setScene(cena);
            janela.setResizable(false);
            janela.show();//exibe a cena
            sequenciaCenas.setFaseAtual(jogador.getFaseAtual());
            sequenciaCenas.setPontuacao(jogador.getPontuacaoTotal());
            sequenciaCenas.definirImagemFundo();
            sequenciaCenas.executarCenaIntermediariaFase();
        };

        //evento para voltar para o jogo pós exibição da cena
        eventoVoltar = (ActionEvent event) -> {
            try {
                if (!sequenciaCenas.getPulandoIntro()) {
                    Parent cenaPrincipal = null;
                    FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Gui_JogoPrincipal.fxml"));
                    try {
                        cenaPrincipal = (Parent) fxmloader.load();

                    } catch (IOException ex) {
                        Logger.getLogger(Model_SequenciaCenas.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                    jogoPrincipal = fxmloader.<Gui_JogoPrincipalController>getController();
                    jogoPrincipal.setFaseAtual(getFaseAtual());
                    jogoPrincipal.definirImagemFundo();
                    janela.setTitle("Legere");
                    janela.setScene(cenaTemporaria);
                    setMostrandoCena(false);
                    janela.setResizable(false);
                    definirImagemFundo();
                    janela.show();
                } else {

                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ModelJogoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ModelJogoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        };
        eventoFimAcerto = (ActionEvent event) -> {
            try {
                if (!sequenciaCenas.getPulandoIntro()) {
                    gerarOpcaoAleatoria();
                }

            } catch (InterruptedException | IOException ex) {
                Logger.getLogger(ModelJogoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        };

        //se for add alguma coisa antes do inicio do jogo é só aumentar o tamanho do tempo
        new Timeline(
                new KeyFrame(Duration.seconds(0), eventoCenas),
                new KeyFrame(Duration.seconds(tempoFase), eventoVoltar),
                new KeyFrame(Duration.seconds(tempoFase + 1), eventoFimAcerto)).play();

    }

    /**
     * Gera um novo áudio
     *
     * @throws IOException
     */
    private void gerarOpcaoAudio() throws IOException {
        int i = 0;
        int proxValor = 0;
        novasOpcoes = new ArrayList(); //recebe os índices para as novas opções do array correspondente à fase
        ArrayList indiceUtilizados = new ArrayList();//array que receberá os índices que já foram utilizados ????
        Random indice = new Random();//gerador de índices aleatorios
        switch (jogador.getFaseAtual()) {//verifica qual a fase em que o jogador se encontra
            case 1: //FASE 1
                btn_1.setStyle("-fx-font-size: 35px; \n-fx-pref-width: 88px;\n -fx-pref-height: 80px;");
                btn_2.setStyle("-fx-font-size: 35px; \n -fx-pref-width: 88px;\n -fx-pref-height: 80px;");
                btn_3.setStyle("-fx-font-size: 35px; \n -fx-pref-width: 88px;\n -fx-pref-height: 80px;");
                btn_4.setStyle("-fx-font-size: 35px; \n -fx-pref-width: 88px;\n -fx-pref-height: 80px;");
                btn_5.setStyle("-fx-font-size: 35px; \n -fx-pref-width: 88px;\n -fx-pref-height: 80px;");
                if (!isGameOver()) {
                    gerarSomAleatorio(); //gera um som aleatório                
                }
                //loop que gera os índices e os adiciona no array novasOpcoes
                while (i < 5) {
                    proxValor = indice.nextInt(5);
                    if (!indiceUtilizados.contains(proxValor)) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }

                //Altera o valor dos botões
                btn_1.setText(vogais[(int) novasOpcoes.get(0)]);
                btn_2.setText(vogais[(int) novasOpcoes.get(1)]);
                btn_3.setText(vogais[(int) novasOpcoes.get(2)]);
                btn_4.setText(vogais[(int) novasOpcoes.get(3)]);
                btn_5.setText(vogais[(int) novasOpcoes.get(4)]);

                break;

            case 2://FASE DOIS
                btn_1.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px;  \n -fx-pref-height: 70px;");
                btn_2.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");
                btn_3.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");
                btn_4.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");
                btn_5.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");

                i = 0;
                int som = 0;
                if (!isGameOver()) {
                    som = gerarSomAleatorio();
                }
                indiceUtilizados.add(som);
                while (indiceUtilizados.size() <= 5) {

                    proxValor = indice.nextInt(29);

                    if (!indiceUtilizados.contains(proxValor) && (!possuiSemelhante(indiceUtilizados, proxValor))) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }

                preencherOpcoes(silabasSimples, som, novasOpcoes);

                break;

            case 3:

                btn_1.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 150px;  \n -fx-pref-height: 70px;");
                btn_2.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 150px; \n -fx-pref-height: 70px;");
                btn_3.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 150px; \n -fx-pref-height: 70px;");
                btn_4.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 150px; \n -fx-pref-height: 70px;");
                btn_5.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 150px; \n -fx-pref-height: 70px;");

                i = 0;
                som = 0;
                if (!isGameOver()) {
                    som = gerarSomAleatorio();
                }
                indiceUtilizados.add(som);
                while (indiceUtilizados.size() <= 5) {

                    proxValor = indice.nextInt(80);

                    if (!indiceUtilizados.contains(proxValor) && (!possuiSemelhante(indiceUtilizados, proxValor))) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }

                preencherOpcoes(palavrasSimples, som, novasOpcoes);
                break;

            case 4:

                btn_1.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px;  \n -fx-pref-height: 70px;");
                btn_2.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");
                btn_3.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");
                btn_4.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");
                btn_5.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");

                i = 0;
                som = 0;
                if (!isGameOver()) {
                    som = gerarSomAleatorio();
                }
                indiceUtilizados.add(som);
                while (indiceUtilizados.size() <= 5) {

                    proxValor = indice.nextInt(93);
                    if (!indiceUtilizados.contains(proxValor) && (!possuiSemelhante(indiceUtilizados, proxValor))) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }
                preencherOpcoes(silabasComplexas, som, novasOpcoes);

                break;
            case 5:

                btn_1.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px;  \n -fx-pref-height: 70px;");
                btn_2.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");
                btn_3.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");
                btn_4.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");
                btn_5.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");

                i = 0;
                som = 0;
                if (!isGameOver()) {
                    som = gerarSomAleatorio();
                }
                indiceUtilizados.add(som);
                while (indiceUtilizados.size() <= 5) {

                    proxValor = indice.nextInt(57);
                    if (!indiceUtilizados.contains(proxValor) && (!possuiSemelhante(indiceUtilizados, proxValor))) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }

                preencherOpcoes(silabasComplexas2, som, novasOpcoes);
                break;

            case 6:
                
                btn_1.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px;  \n -fx-pref-height: 70px;");
                btn_2.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");
                btn_3.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");
                btn_4.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");
                btn_5.setStyle("-fx-font-size: 17px; \n -fx-pref-width: 100px; \n -fx-pref-height: 70px;");
                
                i = 0;
                som = 0;
                if (!isGameOver()) {
                    som = gerarSomAleatorio();
                }
                indiceUtilizados.add(som);
                while (indiceUtilizados.size() <= 5) {

                    proxValor = indice.nextInt(30);
                    if (!indiceUtilizados.contains(proxValor) && (!possuiSemelhante(indiceUtilizados, proxValor))) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }
                preencherOpcoes(silabasComplexas3, som, novasOpcoes);
                break;

            case 7:

                btn_1.setStyle("-fx-font-size: 15px; \n -fx-pref-width: 170px;  \n -fx-pref-height: 70px;");
                btn_2.setStyle("-fx-font-size: 15px; \n -fx-pref-width: 170px; \n -fx-pref-height: 70px;");
                btn_3.setStyle("-fx-font-size: 15px; \n -fx-pref-width: 170px; \n -fx-pref-height: 70px;");
                btn_4.setStyle("-fx-font-size: 15px; \n -fx-pref-width: 170px; \n -fx-pref-height: 70px;");
                btn_5.setStyle("-fx-font-size: 15px; \n -fx-pref-width: 170px; \n -fx-pref-height: 70px;");

                i = 0;
                som = 0;
                if (!isGameOver()) {
                    som = gerarSomAleatorio();
                }
                indiceUtilizados.add(som);
                while (indiceUtilizados.size() <= 5) {

                    proxValor = indice.nextInt(100);
                    if (!indiceUtilizados.contains(proxValor) && (!possuiSemelhante(indiceUtilizados, proxValor))) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }
                preencherOpcoes(palavrasComplexas, som, novasOpcoes);
                break;
        }
    }

    /**
     * Verifica se o próximo valor das novas opções possui algum fonema
     * semelhante
     *
     * @param indicesUtilizados lista com os valores utilizados
     * @param novaOpcao opção candidata a ser inserida na lista
     * @return
     */
    public boolean possuiSemelhante(ArrayList indicesUtilizados, int novaOpcao) {
        int iterador = 0;
        boolean retorno = false;
        if (!indicesUtilizados.isEmpty()) {
            for (iterador = 0; iterador < indicesUtilizados.size(); iterador++) {
                int valor = (int) indicesUtilizados.get(iterador);
                if (((valor - novaOpcao) == 1) || (novaOpcao - valor) == 1) {
                    retorno = true;
                }
            }
        }
        return retorno;
    }

    /**
     * Define a imagem de fundo de cada fase
     */
    public void definirImagemFundo() {
        URL arqImg = null;
        System.out.println(jogador.getFaseAtual());
        switch (jogador.getFaseAtual()) {
            case 1:
                arqImg = getClass().getResource("Imagens/Gerais/fundo_fase1.jpg");
                break;
            case 2:
                arqImg = getClass().getResource("Imagens/Gerais/fundo_fase2.jpg");
                break;
            case 3:
                arqImg = getClass().getResource("Imagens/Gerais/fundo_fase3.jpg");
                break;
            case 4:
                arqImg = getClass().getResource("Imagens/Gerais/fundo_fase4.jpg");
                break;
            case 5:
                arqImg = getClass().getResource("Imagens/Gerais/fundo_fase5.jpg");
                break;
            case 6:
                arqImg = getClass().getResource("Imagens/Gerais/fundo_fase6.jpg");
                break;
            case 7:
                arqImg = getClass().getResource("Imagens/Gerais/fundo_fase7.jpg");
                break;
        }
        imagemFundo.setImage(new Image(arqImg.toString()));
    }

    /**
     * Define a fase atual
     *
     * @param faseAtual
     */
    public void setFaseAtual(int faseAtual) {
        jogador.setFaseAtual(faseAtual);
    }

    /**
     * Retorna o valor da fase atual
     *
     * @return
     */
    public int getFaseAtual() {
        return jogador.getFaseAtual();
    }

    /**
     * Reinicia o jogo
     *
     * @param imgReiniciar imageView de onde ocorreu o click
     * @throws IOException
     */
    public void reiniciarJogo(ImageView imgReiniciar) throws IOException {
        timer.cancel();
        modelInicial.iniciar(imgReiniciar);
    }

    public void mostrarCenaFinal(int pontuacaoTotal) throws IOException {
        janela = (Stage) btn_1.getScene().getWindow();
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Gui_SequenciaCenas.fxml"));
        Parent proximaCena = (Parent) fxmloader.load();
        Gui_SequenciaCenasController sequenciaCenas = fxmloader.<Gui_SequenciaCenasController>getController();
        //cria uma cena 
        Scene cena = new Scene(proximaCena, 1200, 700);
        janela.setTitle("Legere");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a cena
        //sequenciaCenas.executarCenaInicioJogo();
        sequenciaCenas.definirImagemFundo();
        timer.cancel();
        sequenciaCenas.executarCenaFinal(pontuacaoTotal);
    }

    public void voltarCenaJogo(Stage janela) {
        Parent cenaPrincipal = null;
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Gui_JogoPrincipal.fxml"));
        try {
            cenaPrincipal = (Parent) fxmloader.load();

        } catch (IOException ex) {
            Logger.getLogger(Model_SequenciaCenas.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        jogoPrincipal = fxmloader.<Gui_JogoPrincipalController>getController();
        System.out.println("Evento voltar fasetual: " + getFaseAtual());
        jogoPrincipal.setFaseAtual(getFaseAtual());
        jogoPrincipal.definirImagemFundo();
        janela.setTitle("Legere");
        janela.setScene(cenaTemporaria);
        setMostrandoCena(false);
        janela.setResizable(false);
        definirImagemFundo();
        janela.show();
    }

    public void tocarDica(ActionEvent botao) throws InterruptedException {
        String caminhoAudioDica = "";
        String dica = "";
        String somAtual = getAudioAtual(); //string para armazenar o último fonema que foi tocado
        char charNumeroBotao = (((Button) botao.getSource()).getId()).charAt(9);
        int numeroBotao = Integer.parseInt("" + charNumeroBotao);
        //System.out.println(numeroBotao);
        switch (numeroBotao) {
            case 0:
                dica = btn_1.getText().toLowerCase();
                System.out.println("Dica " + dica);
                break;
            case 1:
                dica = btn_2.getText().toLowerCase();
                break;
            case 2:
                dica = btn_3.getText().toLowerCase();
                break;
            case 3:
                dica = btn_4.getText().toLowerCase();
                break;
            case 4:
                dica = btn_5.getText().toLowerCase();
                break;
        }
        caminhoAudioDica = "a_dicas/" + dica;
        tocarAudioDica(caminhoAudioDica);
        //setNomeAudioAtual(somAtual);       

    }

    private void mostrarBotoesDicas() {
        DicaBotao0.setVisible(true);
        DicaBotao1.setVisible(true);
        DicaBotao2.setVisible(true);
        DicaBotao3.setVisible(true);
        DicaBotao4.setVisible(true);
    }

    private void ocultarBotoesDicas() {
        DicaBotao0.setVisible(false);
        DicaBotao1.setVisible(false);
        DicaBotao2.setVisible(false);
        DicaBotao3.setVisible(false);
        DicaBotao4.setVisible(false);
    }

    public void tocarAudioDica(String n) {
        System.out.println("Nome " + n);
        switch (jogador.getFaseAtual()) {//pega a fase atual do jogador
            case 1:
                caminhoAudio = "audios_vogais/" + n + ".mp3";
                break;
            case 2:
                caminhoAudio = "audios_silabas_simples/" + n + ".mp3";
                break;
            case 3:
                caminhoAudio = "audios_palavras_simples/" + n + ".mp3";
                break;
            case 4:
                caminhoAudio = "audios_silabas_complexas/" + n + ".mp3";
                break;
            case 5:
                caminhoAudio = "audios_silabas_complexas2/" + n + ".mp3";
                break;
            case 6:
                caminhoAudio = "audios_silabas_complexas3/" + n + ".mp3";
                break;
            case 7:
                caminhoAudio = "audios_palavras_complexas/" + n + ".mp3";
                break;
        }
        URL file = getClass().getResource(caminhoAudio);
        media = new Media(file.toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.play();
                setMostrandoDica(true);
            }

        });

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                setMostrandoDica(false);
            }
        });
    }

    public void setMostrandoDica(boolean valor) {
        this.mostrandoDica = valor;
    }

    public boolean getMostrandoDica() {
        return this.mostrandoDica;
    }

    public void pararRelogio() {
        timer.cancel();
    }

    public void setLabelFase(String string) {
        numFase.setText(string);
    }

    public void setPontuacao(int pontos) {
        jogador.setPontuacaoTotal(pontos);
        pontuacao.setText("" + pontos);
    }

    public int getPontuacao() {
        return jogador.getPontuacaoTotal();
    }
}
