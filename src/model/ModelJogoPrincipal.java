package model;

import controller.Gui_SequenciaCenasController;
import controller.Gui_JogoPrincipalController;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    private ProgressBar lifeBar;
    private String vogais[] = {"A", "E", "I", "O", "U"};
    private String silabasSimples[] = {
        "AD", "AL", "AM", "AN", "AR", "AS", "AZ", "ÇÃO", "ÇÕES", "EL", "EM",
        "EN", "ER", "ES", "IL", "IM", "IN", "IR", "IS", "OL", "OM", "ON", "OR",
        "OS", "UL", "UM", "UN", "UR", "US"};
    private String silabasSimplesB[] = {
        "BA", "BE", "BI", "BO", "BU", "CA", "CE", "CI", "CO", "CU", "DA", "DE",
        "DI", "DO", "DU", "FA", "FE", "FI", "FO", "FU", "GA", "GE", "GI", "GO",
        "GU", "JA", "JE", "JI", "JO", "JU", "LA", "LE", "LI", "LO", "LU", "MA",
        "ME", "MI", "MO", "MU", "NA", "NE", "NI", "NO", "NU", "PA", "PE", "PI",
        "PO", "PU", "RA", "RE", "RI", "RO", "RU", "SA", "SE", "SI", "SO", "SU",
        "TA", "TE", "TI", "TO", "TU", "VA", "VE", "VI", "VO", "VU", "XA", "XE",
        "XI", "XO", "XU", "ZA", "ZE", "ZI", "ZO", "ZU"};
    private String silabasComplexas2[] = {
        "BLA", "BLE", "BLI", "BLO", "BLU", "BRA", "BRE", "BRI", "BRO", "BRU",
        "CLA", "CLE", "CLI", "CLO", "CLU", "CRA", "CRE", "CRI", "CRO", "CRU",
        "DLA", "DLE", "DLI", "DLO", "DLU", "DRA", "DRE", "DRI", "DRO", "DRU",
        "FLA", "FLE", "FLI", "FLO", "FLU", "FRA", "FRE", "FRI", "FRO", "FRU",
        "GLA", "GLE", "GLI", "GLO", "GLU", "GRA", "GRE", "GRI", "GRO", "GRU",
        "PLA", "PLE", "PLI", "PLO", "PLU", "PRA", "PRE", "PRI", "PRO", "PRU",
        "TI", "TLA", "TLE", "TLI", "TLO", "TLU", "TRA", "TRE", "TRI", "TRO",
        "TRU", "VLA", "VLE", "VLI", "VLO", "VLU", "VRA", "VRE", "VRI", "VRO",
        "VRU"
    };

    private String silabasComplexas[] = {
        "BAL", "BAM", "BAN", "BÃO", "BAR", "BAS", "BAZ", "BEL", "BEM", "BEN",
        "BER", "BES", "BEZ", "BIL", "BIM", "BIN", "BIR", "BIS", "BIZ", "BOL",
        "BOM", "BON", "BOR", "BOS", "BOZ", "BUL", "BUM", "BUN", "BUR", "BUS",
        "BUZ", "CÃES", "CAL", "CAM", "CAN", "CÃO", "CAR", "CAS", "CAZ", "CEL",
        "CEM", "CEN", "CER", "CES", "CEZ", "CHAL", "CHAM", "CHAN", "CHÃO",
        "CHAR", "CHAS", "CHEI", "CHEM", "CHE", "CHEN", "CHER", "CHES", "CHIL",
        "CHIM", "CHIN", "CHIR", "CHIS", "CHOL", "CHOM", "CHON", "CHOR", "CHUL",
        "CHUM", "CHUN", "CHUR", "CIM", "CIN", "CIR", "CIS", "ÇÕES", "COL", "COM",
        "CON", "COR", "COS", "COZ", "CUL", "CUM", "CUN", "CUR", "CUS", "CUZ", "DÃES",
        "DAL", "DAM", "DAN", "DÃO", "DAR", "DAS", "DAZ", "DEL", "DEM", "DEN", "DER",
        "DES", "DEZ", "DIL", "DIM", "DIN", "DIR", "DIS", "DIZ", "DOL", "DOM", "DON",
        "DOR", "DOS", "DOZ", "DUL", "DUM", "DUN", "DUR", "DUS", "DUZ", "FAL", "FAM",
        "FAN", "FAR", "FAS", "FAZ", "FEL", "FEM", "FEN", "FER", "FES", "FEZ",
        "FIL", "FIM", "FIN", "FIR", "FIS", "FIZ", "FOL", "FOM", "FON", "FOR", "FOS",
        "FOZ", "FUI", "FUL", "FUM", "FUN", "FUR", "FUS", "FUZ", "GAL", "GAM", "GAN",
        "GÃO", "GAR", "GEL", "GEM", "GEN", "GER", "GES", "GIL", "GIM", "GIN",
        "GIR", "GIS", "GIZ", "GOL", "GOM", "GON", "GOR", "GUA", "GUL", "GUM",
        "GUN", "GUR", "IS", "JAL", "JAM", "JAN", "JÃO", "JAR", "JAS", "JAZ", "JEM",
        "JEN", "JER", "JES", "JIL", "JIM", "JIN", "JIR", "JOL", "JOM", "JON", "JOR",
        "JOS", "JOZ", "JUL", "JUM", "JUN", "JUR", "JUS", "JUZ", "LAL", "LAM", "LAN",
        "LÃO", "LAR", "LAS", "LAZ", "LEM", "LEN", "LER", "LES", "LEZ", "LIM", "LIN",
        "LIR", "LIS", "LIZ", "LOM", "LON", "LOR", "LOS", "LOZ", "LUM", "LUN", "LUR",
        "LUS", "LUZ", "MAL", "MAM", "MAN", "MÃO", "MÃOS", "MAR", "MAS", "MAZ", "MEL",
        "MEM", "MEN", "MER", "MES", "MEZ", "MIL", "MIM", "MIN", "MIR", "MIS", "MIZ",
        "MOL", "MOM", "MON", "MOR", "MOS", "MOZ", "MUL", "MUM", "MUN", "MUR", "MUS",
        "MUZ", "NAL", "NAM", "NAN", "NÃO", "NÃOS", "NAR", "NAS", "NAU", "NAZ", "NEL",
        "NEM", "NEN", "NER", "NES", "NEZ", "NIL", "NIM", "NIN", "NIR", "NIS", "NIZ",
        "NOL", "NOM", "NON", "NOR", "NOS", "NOZ", "NUL", "NUM", "NUN", "NUR", "NUS",
        "NUZ", "PÃES", "PAL", "PAM", "PAN", "PÃO", "PAR", "PAS", "PAZ", "PEC", "PEL",
        "PEM", "PEN", "PER", "PES", "PEZ", "PIL", "PIM", "PIN", "PIR", "PIS", "PIZ",
        "POL", "POM", "PON", "POR", "POS", "POZ", "PUL", "PUM", "PUN", "PUR", "PUS",
        "PUZ", "QUAL", "QUÃO", "RÃES", "RAL", "RAM", "RAN", "RÃO", "RAR", "RAS", "RAZ", "REL"
    };

    private String[] silabasComplexas3 = {
        "BLAM", "BLAN", "BLAS", "BLEM", "BLEN", "BLES", "BLIM", "BLIN", "BLIS", "BLOM",
        "BLON", "BLOS", "BLUM", "BLUN", "BONS", "BRAM", "BRAN", "BRAS", "BREM", "BREN",
        "BRES", "BRIM", "BRIS", "BROM", "BRON", "BROS", "BRUM", "BRUS", "CHA", "CHÃO",
        "CHAS", "CHER", "CHES", "CHIR", "CHIS", "CHOR", "CHOS", "CHUR", "CHUS", "CLAM",
        "CLAN", "CLAS", "CLEM", "CLEN", "CLES", "CLIM", "CLIN", "CLIS", "CLOM", "CLON",
        "CLOS", "CLUM", "CLUN", "CLUS", "COM", "CON", "CRAM", "CRAN", "CRAS", "CREM",
        "CREN", "CRES", "CRIM", "CRIN", "CRIS", "CROM", "CRON", "CROS", "CRUM", "CRUN",
        "CRUS", "DLAS", "DLES", "DLIS", "DLOS", "DLUS", "DRAM", "DRAN", "DRAS", "DREM",
        "DREN", "DRES", "DRIM", "DRIN", "DRIS", "DROM", "DRON", "DROS", "DRUM", "DRUN",
        "DRUS", "FAN", "FLAM", "FLAN", "FLAS", "FLEM", "FLEN", "FLES", "FLIM", "FLIN",
        "FLIS", "FLOM", "FLON", "FLOS", "FLUM", "FLUN", "FLUS", "FRAM", "FRAN", "FRAS",
        "FREM", "FREN", "FRES", "FRIM", "FRIN", "FRIS", "FROM", "FRON", "FROS", "FRUM",
        "FRUS", "GLAM", "GLAN", "GLAS", "GLEM", "GLEN", "GLES", "GLIM", "GLIN", "GLIS",
        "GLOM", "GLON", "GLOS", "GLUM", "GLUN", "GLUS", "GOM", "GON", "GRAM", "GRAN",
        "GRAS", "GREM", "GREN", "GRES", "GRIM", "GRIN", "GRIS", "GROM", "GRON", "GROS",
        "GRUM", "GRUN", "GRUS", "GUAM", "GUAN", "GUAR", "GUEM", "GUE", "GUEN", "GUER",
        "GUIM", "GUI", "GUIN", "GUIR", "GUO", "GUON", "LHEU", "PERS", "PLAM", "PLAN",
        "PLAS", "PLEM", "PLEN", "PLES", "PLIM", "PLIN", "PLIS", "PLOM", "PLON", "PLOS",
        "PLUM", "PLUS", "PRAM", "PRAN", "PRAS", "PREM", "PREN", "PRES", "PRIM", "PRIN",
        "PRIS", "PROM", "PRON", "PROS", "PRUM", "PRUS", "PSI", "QUA", "QUAN", "QUÃO",
        "QUAR", "QUEM", "QUE", "QUEN", "QUER", "QUIM", "QUI", "QUIN", "QUIR", "QUO",
        "TLAM", "TLAN", "TLAS", "TLEM", "TLEN", "TLES", "TLIM", "TLIN", "TLIS", "TLOM",
        "TLON", "TLOS", "TLUM", "TLUS", "TRAN", "TRAS", "TREM", "TREN", "TRES", "TRIM",
        "TRIS", "TROM", "TRON", "TROS", "TRUM", "TRUN", "TRUS", "VLAS", "VLEM", "VLEN",
        "VLES", "VLIM", "VLIS", "VLOM", "VLON", "VLOS", "VLUM", "VLUN", "VLUS", "VRAM",
        "VRAN", "VRAS", "VREM", "VREN", "VRES", "VRIM", "VRIN", "VRIS", "VROM", "VRON",
        "VROS", "VRUM", "VRUN", "VRUS"
    };

    private String frasesSimples[] = {
        "A BONECA É BONITA", "A BRUXA É FEIA", "A CASA É AZUL", "ALINE GOSTA DE MAÇÃ",
        "A MELANCIA É GOSTOSA", "A NEVE É BRANCA", "A PORTA ESTÁ ABERTA", "A TV ESTÁ LIGADA",
        "BETO GOSTA DE CORRER", "BIA ESTÁ ESTUDANDO", "BIBI COMPROU UMA BOLSA", "EU ADORO LER",
        "EU AMO VOCÊ", "HOJE VAI CHOVER", "HUGO JOGA FUTEBOL", "JOANA CAIU DA CAMA", "JOÃO USA BONÉ",
        "JOSÉ É QUERIDO", "MAGALI ADORA COMER", "MALU ADORA NOVELAS", "MAMÃE FEZ UM BOLO",
        "MARIA ESTÁ NA ESCOLA", "MÁRIO USA GRAVATA", "MEU CABELO É PRETO", "MEU VESTIDO É ROSA",
        "MINHA MÃE É LINDA", "O BEBÊ ESTÁ CHORANDO", "O CARRO É VERMELHO", "O CAVALO É MARRON",
        "O DIA ESTÁ NUBLADO", "O ELEFANTE É GRANDE", "O GATO PULOU DA ÁRVORE", "O LIVRO É PEQUENO",
        "O MACACO É SAPECA", "O PATO ESTÁ ANDANDO", "O PINTINHO CISCA NO CHÃO", "O RATO É DENTUÇO",
        "O REI É BONDOSO", "O SAPATO É AMARELO", "O SAPO ESTÁ NO LAGO", "O TELEFONE ESTÁ TOCANDO",
        "O URSO É PELUDO", "PAPAI CHEGOU EM CASA"};

    private String audiosFrasesSimples[] = {
        "a_boneca_eh_bonita", "a_bruxa_eh_feia", "a_casa_eh_azul",
        "aline_gosta_de_maca", "a_melancia_eh_gostosa", "a_neve_eh_branca",
        "a_porta_esta_aberta", "a_tv_esta_ligada", "beto_gosta_de_correr",
        "bia_esta_estudando", "bibi_comprou_uma_bolsa", "eu_adoro_ler",
        "eu_amo_vc", "hoje_vai_chover", "hugo_joga_futebol", "joana_caiu_da_cama",
        "joao_usa_bone", "josé_eh_querido", "magali_adora_comer", "malu_adora_novelas",
        "mamae_fez_um_bolo", "maria_está_na_escola", "mario_usa_gravata", "meu_cabelo_eh_preto",
        "meu_vestido_eh_rosa", "minha_mae_eh_linda", "o_bebe_esta_chorando", "o_carro_eh_vermelho",
        "o_cavalo_eh_marrom", "o_dia_esta_nublado", "o_elefante_eh_grande", "o_gato_pulou_da_arvore",
        "o_livro_eh_pequeno", "o_macaco_eh_sapeca", "o_pato_esta_andando", "o_pintinho_cisca_no_chao",
        "o_rato_eh_dentuco", "o_rei_eh_bondoso", "o_sapato_eh_amarelo", "o_sapo_esta_no_lago",
        "o_telefone_esta_tocando", "o_urso_eh_peludo", "papai_chegou_em_casa"
    };

    private String palavrasSimples[] = {
        "AMARELO", "AMO", "ANEL", "APITO", "ARVORE", "BALA", "BANANA", "BATATA",
        "BATE", "BIRUTA", "BOCA", "BOLA", "BOLO", "BONECA", "BONÉ",
        "BONITO", "BULE", "CABELO", "CAFÉ", "CAMA", "CAMELO", "CAMISA", "CAPACETE",
        "CASACO", "CASA", "CAVALO", "CEREJA", "CINTO", "COLA", "COMO", "COPO",
        "CORUJA", "DADO", "DEDO", "ESCOLA", "ESCOVA", "FACA", "FADA", "FOCA",
        "FOGO", "GALO", "GATO", "GELADO", "GELO", "JACARE", "JANELA",
        "LAPA", "LATA", "LEITE", "LIXO", "LUA", "LUTA", "LUVA", "MACACO", "MALA",
        "MENINA", "MENINO", "MESA", "MOEDA", "MUSICA", "NOVELA", "PAREDE", "PATO",
        "PELADO", "PÉ", "PENA", "PERA", "PETECA", "PIANO", "PIPA", "PIPOCA", "PIRULITO",
        "PORTA", "RATO", "REI", "RICO", "ROBO", "ROSA", "RUA", "SÁBADO", "SAPATO",
        "SAPO", "SINO", "TAPETE", "TATU", "TELEFONE", "TETO", "TIJOLO", "TOMATE",
        "URRO", "UVA", "VACA", "VELA", "XICARA"
    };

    //o nome dos arquivos das vogais
    private final String audioVogais[] = {"vogal-A", "vogal-E", "vogal-I", "vogal-O", "vogal-U"};

    private final String audioSilabasSimples[] = {
        "ad", "al", "am", "an", "ar", "as", "az", "ção", "ções", "el", "em",
        "en", "er", "es", "il", "im", "in", "ir", "is", "ol", "om", "on", "or",
        "os", "ul", "um", "un", "ur", "us"};

    private final String audioSilabasSimplesB[] = {
        "ba", "be", "bi", "bo", "bu", "ca",
        "ce", "ci", "co", "cu", "da", "de", "di", "do", "du", "fa", "fe", "fi",
        "fo", "fu", "ga", "ge", "gi", "go", "gu", "ja", "je", "ji", "jo", "ju",
        "la", "le", "li", "lo", "lu", "ma", "me", "mi", "mo", "mu", "na", "ne",
        "ni", "no", "nu", "pa", "pe", "pi", "po", "pu", "ra", "re", "ri", "ro",
        "ru", "sa", "se", "si", "so", "su", "ta", "te", "ti", "to", "tu", "va",
        "ve", "vi", "vo", "vu", "xa", "xe", "xi", "xo", "xu", "za", "ze", "zi",
        "zo", "zu"};

    private final String audiosSilabasComplexas[] = {
        "bal", "bam", "ban", "bão", "bar", "bas", "baz", "bel", "bem", "ben",
        "ber", "bes", "bez", "bil", "bim", "bin", "bir", "bis", "biz", "bol",
        "bom", "bon", "bor", "bos", "boz", "bul", "bum", "bun", "bur", "bus",
        "buz", "cães", "cal", "cam", "can", "cão", "car", "cas", "caz", "cel",
        "cem", "cen", "cer", "ces", "cez", "chal", "cham", "chan", "chão",
        "char", "chas", "chei", "chem", "che", "chen", "cher", "ches", "chil",
        "chim", "chin", "chir", "chis", "chol", "chom", "chon", "chor", "chul",
        "chum", "chun", "chur", "cim", "cin", "cir", "cis", "ções", "col", "com",
        "con", "cor", "cos", "coz", "cul", "cum", "cun", "cur", "cus", "cuz", "dães",
        "dal", "dam", "dan", "dão", "dar", "das", "daz", "del", "dem", "den", "der",
        "des", "dez", "dil", "dim", "din", "dir", "dis", "diz", "dol", "dom", "don",
        "dor", "dos", "doz", "dul", "dum", "dun", "dur", "dus", "duz", "fal", "fam",
        "fan", "far", "fas", "faz", "fel", "fem", "fen", "fer", "fes", "fez",
        "fil", "fim", "fin", "fir", "fis", "fiz", "fol", "fom", "fon", "for", "fos",
        "foz", "fui", "ful", "fum", "fun", "fur", "fus", "fuz", "gal", "gam", "gan",
        "gão", "gar", "gel", "gem", "gen", "ger", "ges", "gil", "gim", "gin",
        "gir", "gis", "giz", "gol", "gom", "gon", "gor", "gua", "gul", "gum",
        "gun", "gur", "is", "jal", "jam", "jan", "jão", "jar", "jas", "jaz", "jem",
        "jen", "jer", "jes", "jil", "jim", "jin", "jir", "jol", "jom", "jon", "jor",
        "jos", "joz", "jul", "jum", "jun", "jur", "jus", "juz", "lal", "lam", "lan",
        "lão", "lar", "las", "laz", "lem", "len", "ler", "les", "lez", "lim", "lin",
        "lir", "lis", "liz", "lom", "lon", "lor", "los", "loz", "lum", "lun", "lur",
        "lus", "luz", "mal", "mam", "man", "mão", "mãos", "mar", "mas", "maz", "mel",
        "mem", "men", "mer", "mes", "mez", "mil", "mim", "min", "mir", "mis", "miz",
        "mol", "mom", "mon", "mor", "mos", "moz", "mul", "mum", "mun", "mur", "mus",
        "muz", "nal", "nam", "nan", "não", "nãos", "nar", "nas", "nau", "naz", "nel",
        "nem", "nen", "ner", "nes", "nez", "nil", "nim", "nin", "nir", "nis", "niz",
        "nol", "nom", "non", "nor", "nos", "noz", "nul", "num", "nun", "nur", "nus",
        "nuz", "pães", "pal", "pam", "pan", "pão", "par", "pas", "paz", "pec", "pel",
        "pem", "pen", "per", "pes", "pez", "pil", "pim", "pin", "pir", "pis", "piz",
        "pol", "pom", "pon", "por", "pos", "poz", "pul", "pum", "pun", "pur", "pus",
        "puz", "qual", "quão", "rães", "ral", "ram", "ran", "rão", "rar", "ras", "raz", "rel"
    };

    private final String audiosSilabasComplexas2[] = {
        "bla", "ble", "bli", "blo", "blu", "bra", "bre", "bri", "bro", "bru",
        "cla", "cle", "cli", "clo", "clu", "cra", "cre", "cri", "cro", "cru",
        "dla", "dle", "dli", "dlo", "dlu", "dra", "dre", "dri", "dro", "dru",
        "fla", "fle", "fli", "flo", "flu", "fra", "fre", "fri", "fro", "fru",
        "gla", "gle", "gli", "glo", "glu", "gra", "gre", "gri", "gro", "gru",
        "pla", "ple", "pli", "plo", "plu", "pra", "pre", "pri", "pro", "pru",
        "ti", "tla", "tle", "tli", "tlo", "tlu", "tra", "tre", "tri", "tro",
        "tru", "vla", "vle", "vli", "vlo", "vlu", "vra", "vre", "vri", "vro",
        "vru"};

    private final String audiosPalavrasSimples[] = {
        "amarelo", "amo", "anel", "apito", "arvore", "bala", "banana", "batata",
        "bate", "biruta", "boca", "bola", "bolo", "boneca", "boné",
        "bonito", "bule", "cabelo", "café", "cama", "camelo", "camisa", "capacete",
        "casaco", "casa", "cavalo", "cereja", "cinto", "cola", "como", "copo",
        "coruja", "dado", "dedo", "escola", "escova", "faca", "fada", "foca",
        "fogo", "galo", "gato", "gelado", "gelo", "jacare", "janela", "lapa",
        "lata", "leite", "lixo", "lua", "luta", "luva", "macaco", "mala",
        "menina", "menino", "mesa", "moeda", "musica", "novela", "parede", "pato",
        "pelado", "pé", "pena", "pera", "peteca", "piano", "pipa", "pipoca",
        "pirulito", "porta", "rato", "rei", "rico", "robo", "rosa", "rua",
        "sábado", "sapato", "sapo", "sino", "tapete", "tatu", "telefone", "teto",
        "tijolo", "tomate", "urro", "uva", "vaca", "vela", "xicara"
    };

    private final String audiosSilabasComplexas3[] = {
        "blam", "blan", "blas", "blem", "blen", "bles", "blim", "blin", "blis", "blom",
        "blon", "blos", "blum", "blun", "bons", "bram", "bran", "bras", "brem", "bren",
        "bres", "brim", "bris", "brom", "bron", "bros", "brum", "brus", "cha", "chão",
        "chas", "cher", "ches", "chir", "chis", "chor", "chos", "chur", "chus", "clam",
        "clan", "clas", "clem", "clen", "cles", "clim", "clin", "clis", "clom", "clon",
        "clos", "clum", "clun", "clus", "com", "con", "cram", "cran", "cras", "crem",
        "cren", "cres", "crim", "crin", "cris", "crom", "cron", "cros", "crum", "crun",
        "crus", "dlas", "dles", "dlis", "dlos", "dlus", "dram", "dran", "dras", "drem",
        "dren", "dres", "drim", "drin", "dris", "drom", "dron", "dros", "drum", "drun",
        "drus", "fan", "flam", "flan", "flas", "flem", "flen", "fles", "flim", "flin",
        "flis", "flom", "flon", "flos", "flum", "flun", "flus", "fram", "fran", "fras",
        "frem", "fren", "fres", "frim", "frin", "fris", "from", "fron", "fros", "frum",
        "frus", "glam", "glan", "glas", "glem", "glen", "gles", "glim", "glin", "glis",
        "glom", "glon", "glos", "glum", "glun", "glus", "gom", "gon", "gram", "gran",
        "gras", "grem", "gren", "gres", "grim", "grin", "gris", "grom", "gron", "gros",
        "grum", "grun", "grus", "guam", "guan", "guar", "guem", "gue", "guen", "guer",
        "guim", "gui", "guin", "guir", "guo", "guon", "lheu", "pers", "plam", "plan",
        "plas", "plem", "plen", "ples", "plim", "plin", "plis", "plom", "plon", "plos",
        "plum", "plus", "pram", "pran", "pras", "prem", "pren", "pres", "prim", "prin",
        "pris", "prom", "pron", "pros", "prum", "prus", "psi", "qua", "quan", "quão",
        "quar", "quem", "que", "quen", "quer", "quim", "qui", "quin", "quir", "quo",
        "tlam", "tlan", "tlas", "tlem", "tlen", "tles", "tlim", "tlin", "tlis", "tlom",
        "tlon", "tlos", "tlum", "tlus", "tran", "tras", "trem", "tren", "tres", "trim",
        "tris", "trom", "tron", "tros", "trum", "trun", "trus", "vlas", "vlem", "vlen",
        "vles", "vlim", "vlis", "vlom", "vlon", "vlos", "vlum", "vlun", "vlus", "vram",
        "vran", "vras", "vrem", "vren", "vres", "vrim", "vrin", "vris", "vrom", "vron",
        "vros", "vrum", "vrun", "vrus"};

    public Jogador jogador = new Jogador();

    private final Map<String, String> matrizVogais, matrizSilabasSimples,
            matrizSilabasSimplesB, matrizSilabasComplexas2, matrizPalavrasSimples,
            matrizSilabasComplexas, matrizSilabasComplexas3, matrizFrasesSimples;

    private Random indiceAudio;

    private Stage window;
    private String caminhoAudio, nomeAudioAtual;
    private File arquivo;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView = new MediaView();

    private boolean mostrandoCena, indicacaoPular, pularErro;

    private Scene cenaTemporaria;

    private Button correto;

    private Timer timer;

    Gui_SequenciaCenasController sequenciaCenas = new Gui_SequenciaCenasController();

    public ModelJogoPrincipal(Button b1, Button b2, Button b3, Button b4, Button b5,
            Button pular, Label pontuacao, ProgressBar lifeBar, Label tempo) {

        this.btn_1 = b1;
        this.btn_2 = b2;
        this.btn_3 = b3;
        this.btn_4 = b4;
        this.btn_5 = b5;
        this.pular = pular;
        this.lifeBar = lifeBar;
        this.pontuacao = pontuacao;
        this.indiceAudio = new Random();
        this.matrizVogais = new HashMap<String, String>();
        this.matrizSilabasSimples = new HashMap<String, String>();
        this.matrizSilabasSimplesB = new HashMap<String, String>();
        this.matrizSilabasComplexas2 = new HashMap<String, String>();
        this.matrizSilabasComplexas3 = new HashMap<String, String>();
        this.matrizSilabasComplexas = new HashMap<String, String>();
        this.matrizPalavrasSimples = new HashMap<String, String>();
        this.matrizFrasesSimples = new HashMap<String, String>();

        this.mostrandoCena = false;
        this.indicacaoPular = false;
        this.pularErro = false;
        this.tempo = tempo;
        nomeAudioAtual = "";
        window = null;
        cenaTemporaria = null;

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

        //se o jogador acertar pelo menos 10 vezes
        if (jogador.getAcertosTotal() == 10) {
            jogador.setBonus(true);
        }

        if (jogador.getQntErros() + jogador.getAcertosTotal() == 11) {
            jogador.setQntErros(0);//restaura a quantidade de erros do jogador
            jogador.setQntPulos(-1); //restaura a quantidade de pulos disponível
            jogador.setBonus(false);//retira o bônus do jogador
            jogador.setAcertosPorFase(jogador.getFaseAtual(), jogador.getAcertosTotal());
            mostrarCenaFinalFase();
//            jogador.setFaseAtual(faseAtualTemp);//atualiza a fase do jogador
            jogador.setAcertosTotal(0);
            System.out.print("Voltou da cena ");
            //          gerarOpcaoAudio();
            //chamar a cena de inicio da proxima fase
            /**
             * OBS: VAI SER DIFERENTE SE FOR NA ÚLTIMA FASE
             */

        } else {
            System.out.println("final");
            gerarOpcaoAudio();
        }       
    }

    /*função para preencher as opções na tela inserindo 
     em um local aleatório a opção correspondente ao áudio
     (USADA A PARTIR DA FASE DAS SÍLABAS)
     */
    public void preencherOpcoes(String categoria[], int s, ArrayList no) {

        System.out.println("Categoria " + categoria.length);
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
        System.out.println("faseAtual " + jogador.getFaseAtual());
        switch (jogador.getFaseAtual()) {
            case 1:
                i = indiceAudio.nextInt(5);//gera um índice entre 0 - 4 
                System.out.println("fase 1: o som gerado foi o som: " + audioSilabasSimples[i]);
                tocarAudio(audioVogais[i]);
                break;
            case 2:
                i = indiceAudio.nextInt(29);
                System.out.println("fase 2: o som gerado foi o som: " + audioSilabasSimples[i]);
                tocarAudio(audioSilabasSimples[i]);
                y = i;
                break;
            case 3:
                i = indiceAudio.nextInt(80);
                System.out.println("fase 3: o som gerado foi o som: " + audioSilabasSimplesB[i]);
                tocarAudio(audioSilabasSimplesB[i]);
                y = i;
                break;
            case 4:
                i = indiceAudio.nextInt(93);
                System.out.println("fase 4: o som gerado foi o som: " + palavrasSimples[i]);
                tocarAudio(audiosPalavrasSimples[i]);

                y = i;
                break;
            case 5:
                i = indiceAudio.nextInt(111);
                System.out.println("fase 5: o som gerado foi o som: " + audiosSilabasComplexas[i]);
                tocarAudio(audiosSilabasComplexas[i]);
                y = i;
                break;
            case 6:
                i = indiceAudio.nextInt(80);
                System.out.println("fase 6: o som gerado foi o som: " + audiosSilabasComplexas2[i]);
                tocarAudio(audiosSilabasComplexas2[i]);
                y = i;
                break;
            case 7:
                i = indiceAudio.nextInt(266);
                System.out.println("fase 7: o som gerado foi o som: " + audiosSilabasComplexas3[i]);
                tocarAudio(audiosSilabasComplexas3[i]);
                y = i;
                break;
            default:
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
                break;
            case 2:
                resultado = ((getKeyByValue(matrizSilabasSimples, opcaoEscolhida)).equals(getAudioAtual()));
                break;
            case 3:
                resultado = ((getKeyByValue(matrizSilabasSimplesB, opcaoEscolhida)).equals(getAudioAtual()));
                break;
            case 4:
                System.out.println("FASE QUATRO!!!");
                resultado = ((getKeyByValue(matrizPalavrasSimples, opcaoEscolhida)).equals(getAudioAtual()));
                break;
            case 5:
                System.out.println("FASE CINCO!!!");
                resultado = ((getKeyByValue(matrizSilabasComplexas, opcaoEscolhida)).equals(getAudioAtual()));
                break;
            case 6:
                System.out.println("FASE SEIS!!!");
                resultado = ((getKeyByValue(matrizSilabasComplexas2, opcaoEscolhida)).equals(getAudioAtual()));
                break;
            case 7:
                System.out.println("FASE SETE");
                resultado = ((getKeyByValue(matrizSilabasComplexas3, opcaoEscolhida)).equals(getAudioAtual()));
                break;
            case 9:
                resultado = ((getKeyByValue(matrizFrasesSimples, opcaoEscolhida)).equals(getAudioAtual()));
                break;
            default:
                break;
        }

        return resultado;
    }

    public void atualizarRelogioJogo() {

    }

    public void selecionarProxRodada(int fase) {

    }

    public void refazerFase() {

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
     *
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

        matrizSilabasSimples.put("ad", "AD");
        matrizSilabasSimples.put("al", "AL");
        matrizSilabasSimples.put("am", "AM");
        matrizSilabasSimples.put("an", "AN");
        matrizSilabasSimples.put("ar", "AR");
        matrizSilabasSimples.put("as", "AS");
        matrizSilabasSimples.put("az", "AZ");
        matrizSilabasSimples.put("ção", "ÇÃO");
        matrizSilabasSimples.put("ções", "ÇÕES");
        matrizSilabasSimples.put("el", "EL");
        matrizSilabasSimples.put("em", "EM");
        matrizSilabasSimples.put("en", "EN");
        matrizSilabasSimples.put("er", "ER");
        matrizSilabasSimples.put("es", "ES");
        matrizSilabasSimples.put("il", "IL");
        matrizSilabasSimples.put("im", "IM");
        matrizSilabasSimples.put("in", "IN");
        matrizSilabasSimples.put("ir", "IR");
        matrizSilabasSimples.put("is", "IS");
        matrizSilabasSimples.put("ol", "OL");
        matrizSilabasSimples.put("om", "OM");
        matrizSilabasSimples.put("on", "ON");
        matrizSilabasSimples.put("or", "OR");
        matrizSilabasSimples.put("os", "OS");
        matrizSilabasSimples.put("ul", "UL");
        matrizSilabasSimples.put("um", "UM");
        matrizSilabasSimples.put("un", "UN");
        matrizSilabasSimples.put("ur", "UR");
        matrizSilabasSimples.put("us", "US");

    }

    public void iniciarMatrizSilabasSimplesB() {
        matrizSilabasSimplesB.put("ba", "BA");
        matrizSilabasSimplesB.put("be", "BE");
        matrizSilabasSimplesB.put("bi", "BI");
        matrizSilabasSimplesB.put("bo", "BO");
        matrizSilabasSimplesB.put("bu", "BU");
        matrizSilabasSimplesB.put("ca", "CA");
        matrizSilabasSimplesB.put("ce", "CE");
        matrizSilabasSimplesB.put("ci", "CI");
        matrizSilabasSimplesB.put("co", "CO");
        matrizSilabasSimplesB.put("cu", "CU");
        matrizSilabasSimplesB.put("da", "DA");
        matrizSilabasSimplesB.put("de", "DE");
        matrizSilabasSimplesB.put("di", "DI");
        matrizSilabasSimplesB.put("do", "DO");
        matrizSilabasSimplesB.put("du", "DU");
        matrizSilabasSimplesB.put("fa", "FA");
        matrizSilabasSimplesB.put("fe", "FE");
        matrizSilabasSimplesB.put("fi", "FI");
        matrizSilabasSimplesB.put("fo", "FO");
        matrizSilabasSimplesB.put("fu", "FU");
        matrizSilabasSimplesB.put("ga", "GA");
        matrizSilabasSimplesB.put("ge", "GE");
        matrizSilabasSimplesB.put("gi", "GI");
        matrizSilabasSimplesB.put("go", "GO");
        matrizSilabasSimplesB.put("gu", "GU");
        matrizSilabasSimplesB.put("ja", "JA");
        matrizSilabasSimplesB.put("je", "JE");
        matrizSilabasSimplesB.put("ji", "JI");
        matrizSilabasSimplesB.put("jo", "JO");
        matrizSilabasSimplesB.put("ju", "JU");
        matrizSilabasSimplesB.put("la", "LA");
        matrizSilabasSimplesB.put("le", "LE");
        matrizSilabasSimplesB.put("li", "LI");
        matrizSilabasSimplesB.put("lo", "LO");
        matrizSilabasSimplesB.put("lu", "LU");
        matrizSilabasSimplesB.put("ma", "MA");
        matrizSilabasSimplesB.put("me", "ME");
        matrizSilabasSimplesB.put("mi", "MI");
        matrizSilabasSimplesB.put("mo", "MO");
        matrizSilabasSimplesB.put("mu", "MU");
        matrizSilabasSimplesB.put("na", "NA");
        matrizSilabasSimplesB.put("ne", "NE");
        matrizSilabasSimplesB.put("ni", "NI");
        matrizSilabasSimplesB.put("no", "NO");
        matrizSilabasSimplesB.put("nu", "NU");
        matrizSilabasSimplesB.put("pa", "PA");
        matrizSilabasSimplesB.put("pe", "PE");
        matrizSilabasSimplesB.put("pi", "PI");
        matrizSilabasSimplesB.put("po", "PO");
        matrizSilabasSimplesB.put("pu", "PU");
        matrizSilabasSimplesB.put("ra", "RA");
        matrizSilabasSimplesB.put("re", "RE");
        matrizSilabasSimplesB.put("ri", "RI");
        matrizSilabasSimplesB.put("ro", "RO");
        matrizSilabasSimplesB.put("ru", "RU");
        matrizSilabasSimplesB.put("sa", "SA");
        matrizSilabasSimplesB.put("se", "SE");
        matrizSilabasSimplesB.put("si", "SI");
        matrizSilabasSimplesB.put("so", "SO");
        matrizSilabasSimplesB.put("su", "SU");
        matrizSilabasSimplesB.put("ta", "TA");
        matrizSilabasSimplesB.put("te", "TE");
        matrizSilabasSimplesB.put("ti", "TI");
        matrizSilabasSimplesB.put("to", "TO");
        matrizSilabasSimplesB.put("tu", "TU");
        matrizSilabasSimplesB.put("va", "VA");
        matrizSilabasSimplesB.put("ve", "VE");
        matrizSilabasSimplesB.put("vi", "VI");
        matrizSilabasSimplesB.put("vo", "VO");
        matrizSilabasSimplesB.put("vu", "VU");
        matrizSilabasSimplesB.put("xa", "XA");
        matrizSilabasSimplesB.put("xe", "XE");
        matrizSilabasSimplesB.put("xi", "XI");
        matrizSilabasSimplesB.put("xo", "XO");
        matrizSilabasSimplesB.put("xu", "XU");
        matrizSilabasSimplesB.put("za", "ZA");
        matrizSilabasSimplesB.put("ze", "ZE");
        matrizSilabasSimplesB.put("zi", "ZI");
        matrizSilabasSimplesB.put("zo", "ZO");
        matrizSilabasSimplesB.put("zu", "ZU");
    }

    public void iniciarMatrizSilabasComplexas2() {
        matrizSilabasComplexas2.put("bla", "BLA");
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
        matrizSilabasComplexas2.put("dla", "DLA");
        matrizSilabasComplexas2.put("dle", "DLE");
        matrizSilabasComplexas2.put("dli", "DLI");
        matrizSilabasComplexas2.put("dlo", "DLO");
        matrizSilabasComplexas2.put("dlu", "DLU");
        matrizSilabasComplexas2.put("dra", "DRA");
        matrizSilabasComplexas2.put("dre", "DRE");
        matrizSilabasComplexas2.put("dri", "DRI");
        matrizSilabasComplexas2.put("dro", "DRO");
        matrizSilabasComplexas2.put("dru", "DRU");
        matrizSilabasComplexas2.put("fla", "FLA");
        matrizSilabasComplexas2.put("fle", "FLE");
        matrizSilabasComplexas2.put("fli", "FLI");
        matrizSilabasComplexas2.put("flo", "FLO");
        matrizSilabasComplexas2.put("flu", "FLU");
        matrizSilabasComplexas2.put("fra", "FRA");
        matrizSilabasComplexas2.put("fre", "FRE");
        matrizSilabasComplexas2.put("fri", "FRI");
        matrizSilabasComplexas2.put("fro", "FRO");
        matrizSilabasComplexas2.put("fru", "FRU");
        matrizSilabasComplexas2.put("gla", "GLA");
        matrizSilabasComplexas2.put("gle", "GLE");
        matrizSilabasComplexas2.put("gli", "GLI");
        matrizSilabasComplexas2.put("glo", "GLO");
        matrizSilabasComplexas2.put("glu", "GLU");
        matrizSilabasComplexas2.put("gra", "GRA");
        matrizSilabasComplexas2.put("gre", "GRE");
        matrizSilabasComplexas2.put("gri", "GRI");
        matrizSilabasComplexas2.put("gro", "GRO");
        matrizSilabasComplexas2.put("gru", "GRU");
        matrizSilabasComplexas2.put("pla", "PLA");
        matrizSilabasComplexas2.put("ple", "PLE");
        matrizSilabasComplexas2.put("pli", "PLI");
        matrizSilabasComplexas2.put("plo", "PLO");
        matrizSilabasComplexas2.put("plu", "PLU");
        matrizSilabasComplexas2.put("pra", "PRA");
        matrizSilabasComplexas2.put("pre", "PRE");
        matrizSilabasComplexas2.put("pri", "PRI");
        matrizSilabasComplexas2.put("pro", "PRO");
        matrizSilabasComplexas2.put("pru", "PRU");
        matrizSilabasComplexas2.put("ti", "TI");
        matrizSilabasComplexas2.put("tla", "TLA");
        matrizSilabasComplexas2.put("tle", "TLE");
        matrizSilabasComplexas2.put("tli", "TLI");
        matrizSilabasComplexas2.put("tlo", "TLO");
        matrizSilabasComplexas2.put("tlu", "TLU");
        matrizSilabasComplexas2.put("tra", "TRA");
        matrizSilabasComplexas2.put("tre", "TRE");
        matrizSilabasComplexas2.put("tri", "TRI");
        matrizSilabasComplexas2.put("tro", "TRO");
        matrizSilabasComplexas2.put("tru", "TRU");
        matrizSilabasComplexas2.put("vla", "VLA");
        matrizSilabasComplexas2.put("vle", "VLE");
        matrizSilabasComplexas2.put("vli", "VLI");
        matrizSilabasComplexas2.put("vlo", "VLO");
        matrizSilabasComplexas2.put("vlu", "VLU");
        matrizSilabasComplexas2.put("vra", "VRA");
        matrizSilabasComplexas2.put("vre", "VRE");
        matrizSilabasComplexas2.put("vri", "VRI");
        matrizSilabasComplexas2.put("vro", "VRO");
        matrizSilabasComplexas2.put("vru", "VRU");
    }

    public void iniciarMatrizPalavrasSimples() {
        matrizPalavrasSimples.put("amarelo", "AMARELO");
        matrizPalavrasSimples.put("amo", "AMO");
        matrizPalavrasSimples.put("anel", "ANEL");
        matrizPalavrasSimples.put("apito", "APITO");
        matrizPalavrasSimples.put("arvore", "ARVORE");
        matrizPalavrasSimples.put("bala", "BALA");
        matrizPalavrasSimples.put("banana", "BANANA");
        matrizPalavrasSimples.put("batata", "BATATA");
        matrizPalavrasSimples.put("bate", "BATE");
        matrizPalavrasSimples.put("biruta", "BIRUTA");
        matrizPalavrasSimples.put("boca", "BOCA");
        matrizPalavrasSimples.put("bola", "BOLA");
        matrizPalavrasSimples.put("bolo", "BOLO");
        matrizPalavrasSimples.put("boneca", "BONECA");
        matrizPalavrasSimples.put("boné", "BONÉ");
        matrizPalavrasSimples.put("bonito", "BONITO");
        matrizPalavrasSimples.put("bule", "BULE");
        matrizPalavrasSimples.put("cabelo", "CABELO");
        matrizPalavrasSimples.put("café", "CAFÉ");
        matrizPalavrasSimples.put("cama", "CAMA");
        matrizPalavrasSimples.put("camelo", "CAMELO");
        matrizPalavrasSimples.put("camisa", "CAMISA");
        matrizPalavrasSimples.put("capacete", "CAPACETE");
        matrizPalavrasSimples.put("casaco", "CASACO");
        matrizPalavrasSimples.put("casa", "CASA");
        matrizPalavrasSimples.put("cavalo", "CAVALO");
        matrizPalavrasSimples.put("cereja", "CEREJA");
        matrizPalavrasSimples.put("cinto", "CINTO");
        matrizPalavrasSimples.put("cola", "COLA");
        matrizPalavrasSimples.put("como", "COMO");
        matrizPalavrasSimples.put("copo", "COPO");
        matrizPalavrasSimples.put("coruja", "CORUJA");
        matrizPalavrasSimples.put("dado", "DADO");
        matrizPalavrasSimples.put("dedo", "DEDO");
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
        matrizPalavrasSimples.put("jacare", "JACARE");
        matrizPalavrasSimples.put("janela", "JANELA");
        matrizPalavrasSimples.put("lapa", "LAPA");
        matrizPalavrasSimples.put("lata", "LATA");
        matrizPalavrasSimples.put("leite", "LEITE");
        matrizPalavrasSimples.put("lixo", "LIXO");
        matrizPalavrasSimples.put("lua", "LUA");
        matrizPalavrasSimples.put("luta", "LUTA");
        matrizPalavrasSimples.put("luva", "LUVA");
        matrizPalavrasSimples.put("macaco", "MACACO");
        matrizPalavrasSimples.put("mala", "MALA");
        matrizPalavrasSimples.put("menina", "MENINA");
        matrizPalavrasSimples.put("menino", "MENINO");
        matrizPalavrasSimples.put("mesa", "MESA");
        matrizPalavrasSimples.put("moeda", "MOEDA");
        matrizPalavrasSimples.put("musica", "MUSICA");
        matrizPalavrasSimples.put("novela", "NOVELA");
        matrizPalavrasSimples.put("parede", "PAREDE");
        matrizPalavrasSimples.put("pato", "PATO");
        matrizPalavrasSimples.put("pelado", "PELADO");
        matrizPalavrasSimples.put("pé", "PÉ");
        matrizPalavrasSimples.put("pena", "PENA");
        matrizPalavrasSimples.put("pera", "PERA");
        matrizPalavrasSimples.put("peteca", "PETECA");
        matrizPalavrasSimples.put("piano", "PIANO");
        matrizPalavrasSimples.put("pipa", "PIPA");
        matrizPalavrasSimples.put("pipoca", "PIPOCA");
        matrizPalavrasSimples.put("pirulito", "PIRULITO");
        matrizPalavrasSimples.put("porta", "PORTA");
        matrizPalavrasSimples.put("rato", "RATO");
        matrizPalavrasSimples.put("rei", "REI");
        matrizPalavrasSimples.put("rico", "RICO");
        matrizPalavrasSimples.put("robo", "ROBO");
        matrizPalavrasSimples.put("rosa", "ROSA");
        matrizPalavrasSimples.put("rua", "RUA");
        matrizPalavrasSimples.put("sábado", "SÁBADO");
        matrizPalavrasSimples.put("sapato", "SAPATO");
        matrizPalavrasSimples.put("sapo", "SAPO");
        matrizPalavrasSimples.put("sino", "SINO");
        matrizPalavrasSimples.put("tapete", "TAPETE");
        matrizPalavrasSimples.put("tatu", "TATU");
        matrizPalavrasSimples.put("telefone", "TELEFONE");
        matrizPalavrasSimples.put("teto", "TETO");
        matrizPalavrasSimples.put("tijolo", "TIJOLO");
        matrizPalavrasSimples.put("tomate", "TOMATE");
        matrizPalavrasSimples.put("urro", "URRO");
        matrizPalavrasSimples.put("uva", "UVA");
        matrizPalavrasSimples.put("vaca", "VACA");
        matrizPalavrasSimples.put("vela", "VELA");
        matrizPalavrasSimples.put("xicara", "XICARA");

    }

    /**
     *
     */
    public void iniciarMatrizSilabasComplexas() {
        matrizSilabasComplexas.put("bal", "BAL");
        matrizSilabasComplexas.put("bam", "BAM");
        matrizSilabasComplexas.put("ban", "BAN");
        matrizSilabasComplexas.put("bão", "BÃO");
        matrizSilabasComplexas.put("bar", "BAR");
        matrizSilabasComplexas.put("bas", "BAS");
        matrizSilabasComplexas.put("baz", "BAZ");
        matrizSilabasComplexas.put("bel", "BEL");
        matrizSilabasComplexas.put("bem", "BEM");
        matrizSilabasComplexas.put("ben", "BEN");
        matrizSilabasComplexas.put("ber", "BER");
        matrizSilabasComplexas.put("bes", "BES");
        matrizSilabasComplexas.put("bez", "BEZ");
        matrizSilabasComplexas.put("bil", "BIL");
        matrizSilabasComplexas.put("bim", "BIM");
        matrizSilabasComplexas.put("bin", "BIN");
        matrizSilabasComplexas.put("bir", "BIR");
        matrizSilabasComplexas.put("bis", "BIS");
        matrizSilabasComplexas.put("biz", "BIZ");
        matrizSilabasComplexas.put("bol", "BOL");
        matrizSilabasComplexas.put("bom", "BOM");
        matrizSilabasComplexas.put("bon", "BON");
        matrizSilabasComplexas.put("bor", "BOR");
        matrizSilabasComplexas.put("bos", "BOS");
        matrizSilabasComplexas.put("boz", "BOZ");
        matrizSilabasComplexas.put("bul", "BUL");
        matrizSilabasComplexas.put("bum", "BUM");
        matrizSilabasComplexas.put("bun", "BUN");
        matrizSilabasComplexas.put("bur", "BUR");
        matrizSilabasComplexas.put("bus", "BUS");
        matrizSilabasComplexas.put("buz", "BUZ");
        matrizSilabasComplexas.put("cães", "CÃES");
        matrizSilabasComplexas.put("cal", "CAL");
        matrizSilabasComplexas.put("cam", "CAM");
        matrizSilabasComplexas.put("can", "CAN");
        matrizSilabasComplexas.put("cão", "CÃO");
        matrizSilabasComplexas.put("car", "CAR");
        matrizSilabasComplexas.put("cas", "CAS");
        matrizSilabasComplexas.put("caz", "CAZ");
        matrizSilabasComplexas.put("cel", "CEL");
        matrizSilabasComplexas.put("cem", "CEM");
        matrizSilabasComplexas.put("cen", "CEN");
        matrizSilabasComplexas.put("cer", "CER");
        matrizSilabasComplexas.put("ces", "CES");
        matrizSilabasComplexas.put("cez", "CEZ");
        matrizSilabasComplexas.put("chal", "CHAL");
        matrizSilabasComplexas.put("cham", "CHAM");
        matrizSilabasComplexas.put("chan", "CHAN");
        matrizSilabasComplexas.put("chão", "CHÃO");
        matrizSilabasComplexas.put("char", "CHAR");
        matrizSilabasComplexas.put("chas", "CHAS");
        matrizSilabasComplexas.put("chei", "CHEI");
        matrizSilabasComplexas.put("chem", "CHEM");
        matrizSilabasComplexas.put("che", "CHE");
        matrizSilabasComplexas.put("chen", "CHEN");
        matrizSilabasComplexas.put("cher", "CHER");
        matrizSilabasComplexas.put("ches", "CHES");
        matrizSilabasComplexas.put("chil", "CHIL");
        matrizSilabasComplexas.put("chim", "CHIM");
        matrizSilabasComplexas.put("chin", "CHIN");
        matrizSilabasComplexas.put("chir", "CHIR");
        matrizSilabasComplexas.put("chis", "CHIS");
        matrizSilabasComplexas.put("chol", "CHOL");
        matrizSilabasComplexas.put("chom", "CHOM");
        matrizSilabasComplexas.put("chon", "CHON");
        matrizSilabasComplexas.put("chor", "CHOR");
        matrizSilabasComplexas.put("chul", "CHUL");
        matrizSilabasComplexas.put("chum", "CHUM");
        matrizSilabasComplexas.put("chun", "CHUN");
        matrizSilabasComplexas.put("chur", "CHUR");
        matrizSilabasComplexas.put("cim", "CIM");
        matrizSilabasComplexas.put("cin", "CIN");
        matrizSilabasComplexas.put("cir", "CIR");
        matrizSilabasComplexas.put("cis", "CIS");
        matrizSilabasComplexas.put("ções", "ÇÕES");
        matrizSilabasComplexas.put("col", "COL");
        matrizSilabasComplexas.put("com", "COM");
        matrizSilabasComplexas.put("con", "CON");
        matrizSilabasComplexas.put("cor", "COR");
        matrizSilabasComplexas.put("cos", "COS");
        matrizSilabasComplexas.put("coz", "COZ");
        matrizSilabasComplexas.put("cul", "CUL");
        matrizSilabasComplexas.put("cum", "CUM");
        matrizSilabasComplexas.put("cun", "CUN");
        matrizSilabasComplexas.put("cur", "CUR");
        matrizSilabasComplexas.put("cus", "CUS");
        matrizSilabasComplexas.put("cuz", "CUZ");
        matrizSilabasComplexas.put("dães", "DÃES");
        matrizSilabasComplexas.put("dal", "DAL");
        matrizSilabasComplexas.put("dam", "DAM");
        matrizSilabasComplexas.put("dan", "DAN");
        matrizSilabasComplexas.put("dão", "DÃO");
        matrizSilabasComplexas.put("dar", "DAR");
        matrizSilabasComplexas.put("das", "DAS");
        matrizSilabasComplexas.put("daz", "DAZ");
        matrizSilabasComplexas.put("del", "DEL");
        matrizSilabasComplexas.put("dem", "DEM");
        matrizSilabasComplexas.put("den", "DEN");
        matrizSilabasComplexas.put("der", "DER");
        matrizSilabasComplexas.put("des", "DES");
        matrizSilabasComplexas.put("dez", "DEZ");
        matrizSilabasComplexas.put("dil", "DIL");
        matrizSilabasComplexas.put("dim", "DIM");
        matrizSilabasComplexas.put("din", "DIN");
        matrizSilabasComplexas.put("dir", "DIR");
        matrizSilabasComplexas.put("dis", "DIS");
        matrizSilabasComplexas.put("diz", "DIZ");
        matrizSilabasComplexas.put("dol", "DOL");
        matrizSilabasComplexas.put("dom", "DOM");
        matrizSilabasComplexas.put("don", "DON");
        matrizSilabasComplexas.put("dor", "DOR");
        matrizSilabasComplexas.put("dos", "DOS");
        matrizSilabasComplexas.put("doz", "DOZ");
        matrizSilabasComplexas.put("dul", "DUL");
        matrizSilabasComplexas.put("dum", "DUM");
        matrizSilabasComplexas.put("dun", "DUN");
        matrizSilabasComplexas.put("dur", "DUR");
        matrizSilabasComplexas.put("dus", "DUS");
        matrizSilabasComplexas.put("duz", "DUZ");
        matrizSilabasComplexas.put("fal", "FAL");
        matrizSilabasComplexas.put("fam", "FAM");
        matrizSilabasComplexas.put("fan", "FAN");
        matrizSilabasComplexas.put("far", "FAR");
        matrizSilabasComplexas.put("fas", "FAS");
        matrizSilabasComplexas.put("faz", "FAZ");
        matrizSilabasComplexas.put("fel", "FEL");
        matrizSilabasComplexas.put("fem", "FEM");
        matrizSilabasComplexas.put("fen", "FEN");
        matrizSilabasComplexas.put("fer", "FER");
        matrizSilabasComplexas.put("fes", "FES");
        matrizSilabasComplexas.put("fez", "FEZ");
        matrizSilabasComplexas.put("fil", "FIL");
        matrizSilabasComplexas.put("fim", "FIM");
        matrizSilabasComplexas.put("fin", "FIN");
        matrizSilabasComplexas.put("fir", "FIR");
        matrizSilabasComplexas.put("fis", "FIS");
        matrizSilabasComplexas.put("fiz", "FIZ");
        matrizSilabasComplexas.put("fol", "FOL");
        matrizSilabasComplexas.put("fom", "FOM");
        matrizSilabasComplexas.put("fon", "FON");
        matrizSilabasComplexas.put("for", "FOR");
        matrizSilabasComplexas.put("fos", "FOS");
        matrizSilabasComplexas.put("foz", "FOZ");
        matrizSilabasComplexas.put("fui", "FUI");
        matrizSilabasComplexas.put("ful", "FUL");
        matrizSilabasComplexas.put("fum", "FUM");
        matrizSilabasComplexas.put("fun", "FUN");
        matrizSilabasComplexas.put("fur", "FUR");
        matrizSilabasComplexas.put("fus", "FUS");
        matrizSilabasComplexas.put("fuz", "FUZ");
        matrizSilabasComplexas.put("gal", "GAL");
        matrizSilabasComplexas.put("gam", "GAM");
        matrizSilabasComplexas.put("gan", "GAN");
        matrizSilabasComplexas.put("gão", "GÃO");
        matrizSilabasComplexas.put("gar", "GAR");
        matrizSilabasComplexas.put("gel", "GEL");
        matrizSilabasComplexas.put("gem", "GEM");
        matrizSilabasComplexas.put("gen", "GEN");
        matrizSilabasComplexas.put("ger", "GER");
        matrizSilabasComplexas.put("ges", "GES");
        matrizSilabasComplexas.put("gil", "GIL");
        matrizSilabasComplexas.put("gim", "GIM");
        matrizSilabasComplexas.put("gin", "GIN");
        matrizSilabasComplexas.put("gir", "GIR");
        matrizSilabasComplexas.put("gis", "GIS");
        matrizSilabasComplexas.put("giz", "GIZ");
        matrizSilabasComplexas.put("gol", "GOL");
        matrizSilabasComplexas.put("gom", "GOM");
        matrizSilabasComplexas.put("gon", "GON");
        matrizSilabasComplexas.put("gor", "GOR");
        matrizSilabasComplexas.put("gua", "GUA");
        matrizSilabasComplexas.put("gul", "GUL");
        matrizSilabasComplexas.put("gum", "GUM");
        matrizSilabasComplexas.put("gun", "GUN");
        matrizSilabasComplexas.put("gur", "GUR");
        matrizSilabasComplexas.put("is", "IS");
        matrizSilabasComplexas.put("jal", "JAL");
        matrizSilabasComplexas.put("jam", "JAM");
        matrizSilabasComplexas.put("jan", "JAN");
        matrizSilabasComplexas.put("jão", "JÃO");
        matrizSilabasComplexas.put("jar", "JAR");
        matrizSilabasComplexas.put("jas", "JAS");
        matrizSilabasComplexas.put("jaz", "JAZ");
        matrizSilabasComplexas.put("jem", "JEM");
        matrizSilabasComplexas.put("jen", "JEN");
        matrizSilabasComplexas.put("jer", "JER");
        matrizSilabasComplexas.put("jes", "JES");
        matrizSilabasComplexas.put("jil", "JIL");
        matrizSilabasComplexas.put("jim", "JIM");
        matrizSilabasComplexas.put("jin", "JIN");
        matrizSilabasComplexas.put("jir", "JIR");
        matrizSilabasComplexas.put("jol", "JOL");
        matrizSilabasComplexas.put("jom", "JOM");
        matrizSilabasComplexas.put("jon", "JON");
        matrizSilabasComplexas.put("jor", "JOR");
        matrizSilabasComplexas.put("jos", "JOS");
        matrizSilabasComplexas.put("joz", "JOZ");
        matrizSilabasComplexas.put("jul", "JUL");
        matrizSilabasComplexas.put("jum", "JUM");
        matrizSilabasComplexas.put("jun", "JUN");
        matrizSilabasComplexas.put("jur", "JUR");
        matrizSilabasComplexas.put("jus", "JUS");
        matrizSilabasComplexas.put("juz", "JUZ");
        matrizSilabasComplexas.put("lal", "LAL");
        matrizSilabasComplexas.put("lam", "LAM");
        matrizSilabasComplexas.put("lan", "LAN");
        matrizSilabasComplexas.put("lão", "LÃO");
        matrizSilabasComplexas.put("lar", "LAR");
        matrizSilabasComplexas.put("las", "LAS");
        matrizSilabasComplexas.put("laz", "LAZ");
        matrizSilabasComplexas.put("lem", "LEM");
        matrizSilabasComplexas.put("len", "LEN");
        matrizSilabasComplexas.put("ler", "LER");
        matrizSilabasComplexas.put("les", "LES");
        matrizSilabasComplexas.put("lez", "LEZ");
        matrizSilabasComplexas.put("lim", "LIM");
        matrizSilabasComplexas.put("lin", "LIN");
        matrizSilabasComplexas.put("lir", "LIR");
        matrizSilabasComplexas.put("lis", "LIS");
        matrizSilabasComplexas.put("liz", "LIZ");
        matrizSilabasComplexas.put("lom", "LOM");
        matrizSilabasComplexas.put("lon", "LON");
        matrizSilabasComplexas.put("lor", "LOR");
        matrizSilabasComplexas.put("los", "LOS");
        matrizSilabasComplexas.put("loz", "LOZ");
        matrizSilabasComplexas.put("lum", "LUM");
        matrizSilabasComplexas.put("lun", "LUN");
        matrizSilabasComplexas.put("lur", "LUR");
        matrizSilabasComplexas.put("lus", "LUS");
        matrizSilabasComplexas.put("luz", "LUZ");
        matrizSilabasComplexas.put("mal", "MAL");
        matrizSilabasComplexas.put("mam", "MAM");
        matrizSilabasComplexas.put("man", "MAN");
        matrizSilabasComplexas.put("mão", "MÃO");
        matrizSilabasComplexas.put("mãos", "MÃOS");
        matrizSilabasComplexas.put("mar", "MAR");
        matrizSilabasComplexas.put("mas", "MAS");
        matrizSilabasComplexas.put("maz", "MAZ");
        matrizSilabasComplexas.put("mel", "MEL");
        matrizSilabasComplexas.put("mem", "MEM");
        matrizSilabasComplexas.put("men", "MEN");
        matrizSilabasComplexas.put("mer", "MER");
        matrizSilabasComplexas.put("mes", "MES");
        matrizSilabasComplexas.put("mez", "MEZ");
        matrizSilabasComplexas.put("mil", "MIL");
        matrizSilabasComplexas.put("mim", "MIM");
        matrizSilabasComplexas.put("min", "MIN");
        matrizSilabasComplexas.put("mir", "MIR");
        matrizSilabasComplexas.put("mis", "MIS");
        matrizSilabasComplexas.put("miz", "MIZ");
        matrizSilabasComplexas.put("mol", "MOL");
        matrizSilabasComplexas.put("mom", "MOM");
        matrizSilabasComplexas.put("mon", "MON");
        matrizSilabasComplexas.put("mor", "MOR");
        matrizSilabasComplexas.put("mos", "MOS");
        matrizSilabasComplexas.put("moz", "MOZ");
        matrizSilabasComplexas.put("mul", "MUL");
        matrizSilabasComplexas.put("mum", "MUM");
        matrizSilabasComplexas.put("mun", "MUN");
        matrizSilabasComplexas.put("mur", "MUR");
        matrizSilabasComplexas.put("mus", "MUS");
        matrizSilabasComplexas.put("muz", "MUZ");
        matrizSilabasComplexas.put("nal", "NAL");
        matrizSilabasComplexas.put("nam", "NAM");
        matrizSilabasComplexas.put("nan", "NAN");
        matrizSilabasComplexas.put("não", "NÃO");
        matrizSilabasComplexas.put("nãos", "NÃOS");
        matrizSilabasComplexas.put("nar", "NAR");
        matrizSilabasComplexas.put("nas", "NAS");
        matrizSilabasComplexas.put("nau", "NAU");
        matrizSilabasComplexas.put("naz", "NAZ");
        matrizSilabasComplexas.put("nel", "NEL");
        matrizSilabasComplexas.put("nem", "NEM");
        matrizSilabasComplexas.put("nen", "NEN");
        matrizSilabasComplexas.put("ner", "NER");
        matrizSilabasComplexas.put("nes", "NES");
        matrizSilabasComplexas.put("nez", "NEZ");
        matrizSilabasComplexas.put("nil", "NIL");
        matrizSilabasComplexas.put("nim", "NIM");
        matrizSilabasComplexas.put("nin", "NIN");
        matrizSilabasComplexas.put("nir", "NIR");
        matrizSilabasComplexas.put("nis", "NIS");
        matrizSilabasComplexas.put("niz", "NIZ");
        matrizSilabasComplexas.put("nol", "NOL");
        matrizSilabasComplexas.put("nom", "NOM");
        matrizSilabasComplexas.put("non", "NON");
        matrizSilabasComplexas.put("nor", "NOR");
        matrizSilabasComplexas.put("nos", "NOS");
        matrizSilabasComplexas.put("noz", "NOZ");
        matrizSilabasComplexas.put("nul", "NUL");
        matrizSilabasComplexas.put("num", "NUM");
        matrizSilabasComplexas.put("nun", "NUN");
        matrizSilabasComplexas.put("nur", "NUR");
        matrizSilabasComplexas.put("nus", "NUS");
        matrizSilabasComplexas.put("nuz", "NUZ");
        matrizSilabasComplexas.put("pães", "PÃES");
        matrizSilabasComplexas.put("pal", "PAL");
        matrizSilabasComplexas.put("pam", "PAM");
        matrizSilabasComplexas.put("pan", "PAN");
        matrizSilabasComplexas.put("pão", "PÃO");
        matrizSilabasComplexas.put("par", "PAR");
        matrizSilabasComplexas.put("pas", "PAS");
        matrizSilabasComplexas.put("paz", "PAZ");
        matrizSilabasComplexas.put("pec", "PEC");
        matrizSilabasComplexas.put("pel", "PEL");
        matrizSilabasComplexas.put("pem", "PEM");
        matrizSilabasComplexas.put("pen", "PEN");
        matrizSilabasComplexas.put("per", "PER");
        matrizSilabasComplexas.put("pes", "PES");
        matrizSilabasComplexas.put("pez", "PEZ");
        matrizSilabasComplexas.put("pil", "PIL");
        matrizSilabasComplexas.put("pim", "PIM");
        matrizSilabasComplexas.put("pin", "PIN");
        matrizSilabasComplexas.put("pir", "PIR");
        matrizSilabasComplexas.put("pis", "PIS");
        matrizSilabasComplexas.put("piz", "PIZ");
        matrizSilabasComplexas.put("pol", "POL");
        matrizSilabasComplexas.put("pom", "POM");
        matrizSilabasComplexas.put("pon", "PON");
        matrizSilabasComplexas.put("por", "POR");
        matrizSilabasComplexas.put("pos", "POS");
        matrizSilabasComplexas.put("poz", "POZ");
        matrizSilabasComplexas.put("pul", "PUL");
        matrizSilabasComplexas.put("pum", "PUM");
        matrizSilabasComplexas.put("pun", "PUN");
        matrizSilabasComplexas.put("pur", "PUR");
        matrizSilabasComplexas.put("pus", "PUS");
        matrizSilabasComplexas.put("puz", "PUZ");
        matrizSilabasComplexas.put("qual", "QUAL");
        matrizSilabasComplexas.put("quão", "QUÃO");
        matrizSilabasComplexas.put("rães", "RÃES");
        matrizSilabasComplexas.put("ral", "RAL");
        matrizSilabasComplexas.put("ram", "RAM");
        matrizSilabasComplexas.put("ran", "RAN");
        matrizSilabasComplexas.put("rão", "RÃO");
        matrizSilabasComplexas.put("rar", "RAR");
        matrizSilabasComplexas.put("ras", "RAS");
        matrizSilabasComplexas.put("raz", "RAZ");
        matrizSilabasComplexas.put("rel", "REL");
    }

    public void iniciarMatrizSilabasComplexas3() {
        matrizSilabasComplexas3.put("blam", "BLAM");
        matrizSilabasComplexas3.put("blan", "BLAN");
        matrizSilabasComplexas3.put("blas", "BLAS");
        matrizSilabasComplexas3.put("blem", "BLEM");
        matrizSilabasComplexas3.put("blen", "BLEN");
        matrizSilabasComplexas3.put("bles", "BLES");
        matrizSilabasComplexas3.put("blim", "BLIM");
        matrizSilabasComplexas3.put("blin", "BLIN");
        matrizSilabasComplexas3.put("blis", "BLIS");
        matrizSilabasComplexas3.put("blom", "BLOM");
        matrizSilabasComplexas3.put("blon", "BLON");
        matrizSilabasComplexas3.put("blos", "BLOS");
        matrizSilabasComplexas3.put("blum", "BLUM");
        matrizSilabasComplexas3.put("blun", "BLUN");
        matrizSilabasComplexas3.put("bons", "BONS");
        matrizSilabasComplexas3.put("bram", "BRAM");
        matrizSilabasComplexas3.put("bran", "BRAN");
        matrizSilabasComplexas3.put("bras", "BRAS");
        matrizSilabasComplexas3.put("brem", "BREM");
        matrizSilabasComplexas3.put("bren", "BREN");
        matrizSilabasComplexas3.put("bres", "BRES");
        matrizSilabasComplexas3.put("brim", "BRIM");
        matrizSilabasComplexas3.put("bris", "BRIS");
        matrizSilabasComplexas3.put("brom", "BROM");
        matrizSilabasComplexas3.put("bron", "BRON");
        matrizSilabasComplexas3.put("bros", "BROS");
        matrizSilabasComplexas3.put("brum", "BRUM");
        matrizSilabasComplexas3.put("brus", "BRUS");
        matrizSilabasComplexas3.put("cha", "CHA");
        matrizSilabasComplexas3.put("chão", "CHÃO");
        matrizSilabasComplexas3.put("chas", "CHAS");
        matrizSilabasComplexas3.put("cher", "CHER");
        matrizSilabasComplexas3.put("ches", "CHES");
        matrizSilabasComplexas3.put("chir", "CHIR");
        matrizSilabasComplexas3.put("chis", "CHIS");
        matrizSilabasComplexas3.put("chor", "CHOR");
        matrizSilabasComplexas3.put("chos", "CHOS");
        matrizSilabasComplexas3.put("chur", "CHUR");
        matrizSilabasComplexas3.put("chus", "CHUS");
        matrizSilabasComplexas3.put("clam", "CLAM");
        matrizSilabasComplexas3.put("clan", "CLAN");
        matrizSilabasComplexas3.put("clas", "CLAS");
        matrizSilabasComplexas3.put("clem", "CLEM");
        matrizSilabasComplexas3.put("clen", "CLEN");
        matrizSilabasComplexas3.put("cles", "CLES");
        matrizSilabasComplexas3.put("clim", "CLIM");
        matrizSilabasComplexas3.put("clin", "CLIN");
        matrizSilabasComplexas3.put("clis", "CLIS");
        matrizSilabasComplexas3.put("clom", "CLOM");
        matrizSilabasComplexas3.put("clon", "CLON");
        matrizSilabasComplexas3.put("clos", "CLOS");
        matrizSilabasComplexas3.put("clum", "CLUM");
        matrizSilabasComplexas3.put("clun", "CLUN");
        matrizSilabasComplexas3.put("clus", "CLUS");
        matrizSilabasComplexas3.put("com", "COM");
        matrizSilabasComplexas3.put("con", "CON");
        matrizSilabasComplexas3.put("cram", "CRAM");
        matrizSilabasComplexas3.put("cran", "CRAN");
        matrizSilabasComplexas3.put("cras", "CRAS");
        matrizSilabasComplexas3.put("crem", "CREM");
        matrizSilabasComplexas3.put("cren", "CREN");
        matrizSilabasComplexas3.put("cres", "CRES");
        matrizSilabasComplexas3.put("crim", "CRIM");
        matrizSilabasComplexas3.put("crin", "CRIN");
        matrizSilabasComplexas3.put("cris", "CRIS");
        matrizSilabasComplexas3.put("crom", "CROM");
        matrizSilabasComplexas3.put("cron", "CRON");
        matrizSilabasComplexas3.put("cros", "CROS");
        matrizSilabasComplexas3.put("crum", "CRUM");
        matrizSilabasComplexas3.put("crun", "CRUN");
        matrizSilabasComplexas3.put("crus", "CRUS");
        matrizSilabasComplexas3.put("dlas", "DLAS");
        matrizSilabasComplexas3.put("dles", "DLES");
        matrizSilabasComplexas3.put("dlis", "DLIS");
        matrizSilabasComplexas3.put("dlos", "DLOS");
        matrizSilabasComplexas3.put("dlus", "DLUS");
        matrizSilabasComplexas3.put("dram", "DRAM");
        matrizSilabasComplexas3.put("dran", "DRAN");
        matrizSilabasComplexas3.put("dras", "DRAS");
        matrizSilabasComplexas3.put("drem", "DREM");
        matrizSilabasComplexas3.put("dren", "DREN");
        matrizSilabasComplexas3.put("dres", "DRES");
        matrizSilabasComplexas3.put("drim", "DRIM");
        matrizSilabasComplexas3.put("drin", "DRIN");
        matrizSilabasComplexas3.put("dris", "DRIS");
        matrizSilabasComplexas3.put("drom", "DROM");
        matrizSilabasComplexas3.put("dron", "DRON");
        matrizSilabasComplexas3.put("dros", "DROS");
        matrizSilabasComplexas3.put("drum", "DRUM");
        matrizSilabasComplexas3.put("drun", "DRUN");
        matrizSilabasComplexas3.put("drus", "DRUS");
        matrizSilabasComplexas3.put("fan", "FAN");
        matrizSilabasComplexas3.put("flam", "FLAM");
        matrizSilabasComplexas3.put("flan", "FLAN");
        matrizSilabasComplexas3.put("flas", "FLAS");
        matrizSilabasComplexas3.put("flem", "FLEM");
        matrizSilabasComplexas3.put("flen", "FLEN");
        matrizSilabasComplexas3.put("fles", "FLES");
        matrizSilabasComplexas3.put("flim", "FLIM");
        matrizSilabasComplexas3.put("flin", "FLIN");
        matrizSilabasComplexas3.put("flis", "FLIS");
        matrizSilabasComplexas3.put("flom", "FLOM");
        matrizSilabasComplexas3.put("flon", "FLON");
        matrizSilabasComplexas3.put("flos", "FLOS");
        matrizSilabasComplexas3.put("flum", "FLUM");
        matrizSilabasComplexas3.put("flun", "FLUN");
        matrizSilabasComplexas3.put("flus", "FLUS");
        matrizSilabasComplexas3.put("fram", "FRAM");
        matrizSilabasComplexas3.put("fran", "FRAN");
        matrizSilabasComplexas3.put("fras", "FRAS");
        matrizSilabasComplexas3.put("frem", "FREM");
        matrizSilabasComplexas3.put("fren", "FREN");
        matrizSilabasComplexas3.put("fres", "FRES");
        matrizSilabasComplexas3.put("frim", "FRIM");
        matrizSilabasComplexas3.put("frin", "FRIN");
        matrizSilabasComplexas3.put("fris", "FRIS");
        matrizSilabasComplexas3.put("from", "FROM");
        matrizSilabasComplexas3.put("fron", "FRON");
        matrizSilabasComplexas3.put("fros", "FROS");
        matrizSilabasComplexas3.put("frum", "FRUM");
        matrizSilabasComplexas3.put("frus", "FRUS");
        matrizSilabasComplexas3.put("glam", "GLAM");
        matrizSilabasComplexas3.put("glan", "GLAN");
        matrizSilabasComplexas3.put("glas", "GLAS");
        matrizSilabasComplexas3.put("glem", "GLEM");
        matrizSilabasComplexas3.put("glen", "GLEN");
        matrizSilabasComplexas3.put("gles", "GLES");
        matrizSilabasComplexas3.put("glim", "GLIM");
        matrizSilabasComplexas3.put("glin", "GLIN");
        matrizSilabasComplexas3.put("glis", "GLIS");
        matrizSilabasComplexas3.put("glom", "GLOM");
        matrizSilabasComplexas3.put("glon", "GLON");
        matrizSilabasComplexas3.put("glos", "GLOS");
        matrizSilabasComplexas3.put("glum", "GLUM");
        matrizSilabasComplexas3.put("glun", "GLUN");
        matrizSilabasComplexas3.put("glus", "GLUS");
        matrizSilabasComplexas3.put("gom", "GOM");
        matrizSilabasComplexas3.put("gon", "GON");
        matrizSilabasComplexas3.put("gram", "GRAM");
        matrizSilabasComplexas3.put("gran", "GRAN");
        matrizSilabasComplexas3.put("gras", "GRAS");
        matrizSilabasComplexas3.put("grem", "GREM");
        matrizSilabasComplexas3.put("gren", "GREN");
        matrizSilabasComplexas3.put("gres", "GRES");
        matrizSilabasComplexas3.put("grim", "GRIM");
        matrizSilabasComplexas3.put("grin", "GRIN");
        matrizSilabasComplexas3.put("gris", "GRIS");
        matrizSilabasComplexas3.put("grom", "GROM");
        matrizSilabasComplexas3.put("gron", "GRON");
        matrizSilabasComplexas3.put("gros", "GROS");
        matrizSilabasComplexas3.put("grum", "GRUM");
        matrizSilabasComplexas3.put("grun", "GRUN");
        matrizSilabasComplexas3.put("grus", "GRUS");
        matrizSilabasComplexas3.put("guam", "GUAM");
        matrizSilabasComplexas3.put("guan", "GUAN");
        matrizSilabasComplexas3.put("guar", "GUAR");
        matrizSilabasComplexas3.put("guem", "GUEM");
        matrizSilabasComplexas3.put("gue", "GUE");
        matrizSilabasComplexas3.put("guen", "GUEN");
        matrizSilabasComplexas3.put("guer", "GUER");
        matrizSilabasComplexas3.put("guim", "GUIM");
        matrizSilabasComplexas3.put("gui", "GUI");
        matrizSilabasComplexas3.put("guin", "GUIN");
        matrizSilabasComplexas3.put("guir", "GUIR");
        matrizSilabasComplexas3.put("guo", "GUO");
        matrizSilabasComplexas3.put("guon", "GUON");
        matrizSilabasComplexas3.put("lheu", "LHEU");
        matrizSilabasComplexas3.put("pers", "PERS");
        matrizSilabasComplexas3.put("plam", "PLAM");
        matrizSilabasComplexas3.put("plan", "PLAN");
        matrizSilabasComplexas3.put("plas", "PLAS");
        matrizSilabasComplexas3.put("plem", "PLEM");
        matrizSilabasComplexas3.put("plen", "PLEN");
        matrizSilabasComplexas3.put("ples", "PLES");
        matrizSilabasComplexas3.put("plim", "PLIM");
        matrizSilabasComplexas3.put("plin", "PLIN");
        matrizSilabasComplexas3.put("plis", "PLIS");
        matrizSilabasComplexas3.put("plom", "PLOM");
        matrizSilabasComplexas3.put("plon", "PLON");
        matrizSilabasComplexas3.put("plos", "PLOS");
        matrizSilabasComplexas3.put("plum", "PLUM");
        matrizSilabasComplexas3.put("plus", "PLUS");
        matrizSilabasComplexas3.put("pram", "PRAM");
        matrizSilabasComplexas3.put("pran", "PRAN");
        matrizSilabasComplexas3.put("pras", "PRAS");
        matrizSilabasComplexas3.put("prem", "PREM");
        matrizSilabasComplexas3.put("pren", "PREN");
        matrizSilabasComplexas3.put("pres", "PRES");
        matrizSilabasComplexas3.put("prim", "PRIM");
        matrizSilabasComplexas3.put("prin", "PRIN");
        matrizSilabasComplexas3.put("pris", "PRIS");
        matrizSilabasComplexas3.put("prom", "PROM");
        matrizSilabasComplexas3.put("pron", "PRON");
        matrizSilabasComplexas3.put("pros", "PROS");
        matrizSilabasComplexas3.put("prum", "PRUM");
        matrizSilabasComplexas3.put("prus", "PRUS");
        matrizSilabasComplexas3.put("psi", "PSI");
        matrizSilabasComplexas3.put("qua", "QUA");
        matrizSilabasComplexas3.put("quan", "QUAN");
        matrizSilabasComplexas3.put("quão", "QUÃO");
        matrizSilabasComplexas3.put("quar", "QUAR");
        matrizSilabasComplexas3.put("quem", "QUEM");
        matrizSilabasComplexas3.put("que", "QUE");
        matrizSilabasComplexas3.put("quen", "QUEN");
        matrizSilabasComplexas3.put("quer", "QUER");
        matrizSilabasComplexas3.put("quim", "QUIM");
        matrizSilabasComplexas3.put("qui", "QUI");
        matrizSilabasComplexas3.put("quin", "QUIN");
        matrizSilabasComplexas3.put("quir", "QUIR");
        matrizSilabasComplexas3.put("quo", "QUO");
        matrizSilabasComplexas3.put("tlam", "TLAM");
        matrizSilabasComplexas3.put("tlan", "TLAN");
        matrizSilabasComplexas3.put("tlas", "TLAS");
        matrizSilabasComplexas3.put("tlem", "TLEM");
        matrizSilabasComplexas3.put("tlen", "TLEN");
        matrizSilabasComplexas3.put("tles", "TLES");
        matrizSilabasComplexas3.put("tlim", "TLIM");
        matrizSilabasComplexas3.put("tlin", "TLIN");
        matrizSilabasComplexas3.put("tlis", "TLIS");
        matrizSilabasComplexas3.put("tlom", "TLOM");
        matrizSilabasComplexas3.put("tlon", "TLON");
        matrizSilabasComplexas3.put("tlos", "TLOS");
        matrizSilabasComplexas3.put("tlum", "TLUM");
        matrizSilabasComplexas3.put("tlus", "TLUS");
        matrizSilabasComplexas3.put("tran", "TRAN");
        matrizSilabasComplexas3.put("tras", "TRAS");
        matrizSilabasComplexas3.put("trem", "TREM");
        matrizSilabasComplexas3.put("tren", "TREN");
        matrizSilabasComplexas3.put("tres", "TRES");
        matrizSilabasComplexas3.put("trim", "TRIM");
        matrizSilabasComplexas3.put("tris", "TRIS");
        matrizSilabasComplexas3.put("trom", "TROM");
        matrizSilabasComplexas3.put("tron", "TRON");
        matrizSilabasComplexas3.put("tros", "TROS");
        matrizSilabasComplexas3.put("trum", "TRUM");
        matrizSilabasComplexas3.put("trun", "TRUM");
        matrizSilabasComplexas3.put("trus", "TRUS");
        matrizSilabasComplexas3.put("vlas", "VLAS");
        matrizSilabasComplexas3.put("vlem", "VLEM");
        matrizSilabasComplexas3.put("vlen", "VLEN");
        matrizSilabasComplexas3.put("vles", "VLES");
        matrizSilabasComplexas3.put("vlim", "VLIM");
        matrizSilabasComplexas3.put("vlis", "VLIS");
        matrizSilabasComplexas3.put("vlom", "VLOM");
        matrizSilabasComplexas3.put("vlon", "VLON");
        matrizSilabasComplexas3.put("vlos", "VLOS");
        matrizSilabasComplexas3.put("vlum", "VLUM");
        matrizSilabasComplexas3.put("vlun", "VLUN");
        matrizSilabasComplexas3.put("vlus", "VLUS");
        matrizSilabasComplexas3.put("vram", "VRAM");
        matrizSilabasComplexas3.put("vran", "VRAN");
        matrizSilabasComplexas3.put("vras", "VRAS");
        matrizSilabasComplexas3.put("vrem", "VREM");
        matrizSilabasComplexas3.put("vren", "VREN");
        matrizSilabasComplexas3.put("vres", "VRES");
        matrizSilabasComplexas3.put("vrim", "VRIM");
        matrizSilabasComplexas3.put("vrin", "VRIN");
        matrizSilabasComplexas3.put("vris", "VRIS");
        matrizSilabasComplexas3.put("vrom", "VROM");
        matrizSilabasComplexas3.put("vron", "VRON");
        matrizSilabasComplexas3.put("vros", "VROS");
        matrizSilabasComplexas3.put("vrum", "VRUM");
        matrizSilabasComplexas3.put("vrun", "VRUN");
        matrizSilabasComplexas3.put("vrus", "VRUS");
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
        int valorAcrescentar = 10;
        //pega a pontuação no label da pontuação
        int pontuacaoAnterior = Integer.parseInt(pontuacao.getText());

        if (jogador.getBonus()) {
            valorAcrescentar = 20;
        }
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
        System.out.println("Acertos " + jogador.getAcertosTotal());
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
                opcaoCorreta = matrizSilabasSimplesB.get(nomeAudioAtual);
                break;
            case 4:
                opcaoCorreta = matrizPalavrasSimples.get(nomeAudioAtual);
                break;
            case 5:
                opcaoCorreta = matrizSilabasComplexas.get(nomeAudioAtual);
                break;
            case 6:
                opcaoCorreta = matrizSilabasComplexas2.get(nomeAudioAtual);
                break;
            case 7:
                opcaoCorreta = matrizSilabasComplexas3.get(nomeAudioAtual);
                break;
            default:
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
        gerarProximaRodada = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    gerarOpcaoAleatoria();

                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(ModelJogoPrincipal.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        //evento que reinicia seta o valor boleano que indica que o relógio deve ser reiniciado
        reiniciarRelogio = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                setIndicacaoPular(true);
            }
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
        eventoGameOver = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window = (Stage) btn_1.getScene().getWindow();
                Parent cenaPrincipal = null;
                try {
                    cenaPrincipal = FXMLLoader.load(getClass().getResource("/interfaces/Gui_GameOver.fxml"));

                } catch (IOException ex) {
                    Logger.getLogger(Gui_JogoPrincipalController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(cenaPrincipal, 1200, 700);
                window.setTitle("Grafonema");
                window.setScene(scene);
                window.show();
            }

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
        setNomeAudioAtual(n);//define o nome atual do áudio que está sendo utilizado
        switch (jogador.getFaseAtual()) {//pega a fase atual do jogador
            case 1:
                caminhoAudio = "src/audios_vogais/" + n + ".mp3";
                break;
            case 2:
                caminhoAudio = "src/audios_silabas_simples/" + n + ".mp3";
                break;
            case 3:
                caminhoAudio = "src/audios_silabas_simplesB/" + n + ".mp3";
                break;
            case 4:
                caminhoAudio = "src/audios_palavras_simples/" + n + ".mp3";
                break;
            case 5:
                caminhoAudio = "src/audios_silabas_complexas/" + n + ".mp3";
                break;
            case 6:
                caminhoAudio = "src/audios_silabas_complexas2/" + n + ".mp3";
                break;
            case 7:
                caminhoAudio = "src/audios_silabas_complexas3/" + n + ".mp3";
                break;
            default:
                break;
        }
        //cria um objeto arquivo que recebe o nome do arquivo como parâmetro
        arquivo = new File(caminhoAudio);
        //pega todo do caminho referente ao objeto File criado
        caminhoAudio = arquivo.getAbsolutePath();
        //troca todas as barras invertidas duplas ('\\') por '/'
        caminhoAudio = caminhoAudio.replace("\\", "/");
        //cria um objeto Media que recebe o objeto 'arquivo' como parâmetro
        media = new Media(new File(caminhoAudio).toURI().toString());
        //cria um objeto mediaPlayer que permite qua uma media possa ser reproduzida
        mediaPlayer = new MediaPlayer(media);
        //toca o audio automaticamente
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);

    }

    /**
     * Define o áudio atual
     *
     * @param n nome do áudio
     */
    private void setNomeAudioAtual(String n) {
        nomeAudioAtual = n;
    }

    /**
     * Retorna o nome do áudio atual
     *
     * @return string contendo o nome áudio atual
     */
    public String getAudioAtual() {
        return nomeAudioAtual;
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
     * Mostra as cenas após o jogador acertar 10 vezes
     *
     * @throws InterruptedException
     * @throws IOException
     */
    public void mostrarCenas() throws InterruptedException, IOException {

        //evento responsável por exibir as cenas de progresso na história
        eventoCenas = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //armazena a cena em que o botão 'btn_1' se encontra atualmente
                window = (Stage) btn_1.getScene().getWindow();
                Parent cenaPrincipal = null;
                //armeza a cena do botão 'btn_1' em uma variável temporária
                cenaTemporaria = btn_1.getScene();

                FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Gui_SequenciaCenas.fxml"));
                Parent proximaCena = null;
                try {
                    proximaCena = (Parent) fxmloader.load();
                } catch (IOException ex) {
                    Logger.getLogger(ModelJogoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                Gui_SequenciaCenasController sequenciaCenas = fxmloader.<Gui_SequenciaCenasController>getController();
                //cria uma cena 
                Scene cena = new Scene(proximaCena, 1200, 700);
                window.setTitle("Grafonema");//título da cena
                window.setScene(cena);
                window.show();//exibe a cena
                try {
                    sequenciaCenas.setFaseAtual(jogador.getFaseAtual());
                    sequenciaCenas.iniciarCena();
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ModelJogoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        //evento para voltar para o jogo pós exibição da cena
        eventoVoltar = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setTitle("Grafonema");
                window.setScene(cenaTemporaria);
                //mostrandoCena = false;
                setMostrandoCena(false);
                //eventoAcerto.handle(null);
                Button btemp = opcaoCorreta(null);
                (btemp).setText("X");

                window.show();
                try {
                    gerarOpcaoAleatoria();

                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(ModelJogoPrincipal.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        //animação que exibe as cenas e volta para a interface principal do jogo
        new Timeline(
                new KeyFrame(Duration.seconds(0), eventoCenas),
                new KeyFrame(Duration.seconds(10), eventoVoltar)).play();
        System.out.println("Opção aleatoria gerada");

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
        Timer timer = new Timer();
        //criação da tarefa que vai executar durante 1 segundo
        timer.scheduleAtFixedRate(new TimerTask() {

            int i = 30;

            @Override
            public void run() {

                //Platform.runLater para alterar elementos da interface do javaFX
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {

                        /*condição que faz o contador de segundos 
                         continuar em 30 durante a exibição da cena 
                         */
                        Timer timer2 = new Timer();
                        timer2.scheduleAtFixedRate(new TimerTask() {

                            @Override
                            public void run() {
                                if (getMostrandoCena()) {
                                    i = 30;
                                    //System.out.println("setou o i como 30");
                                }
                                if (getIndicacaoPular()) {
                                    i = i + 0;
                                }

                            }
                        }, 0, 50);

                        tempo.setText("" + i);
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

                    }
                });
            }
        }, 0, 1000);
    }

    /**
     * Mostra a animação de acerto
     */
    public void mostrarAnimacaoAcerto() {
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
                };
                break;

        }

        //evento que representa a ação a ser feita depois da 
        //animação de acerto
        eventoFimAcerto = (ActionEvent event) -> {
            try {
                gerarOpcaoAleatoria();

            } catch (InterruptedException | IOException ex) {
                Logger.getLogger(Gui_JogoPrincipalController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            //indicacaoPular = true;
            setIndicacaoPular(true);
        };

        new Timeline(
                new KeyFrame(Duration.seconds(0), eventoAcerto),
                new KeyFrame(Duration.seconds(1), eventoCorOriginal),
                new KeyFrame(Duration.seconds(2), eventoFimAcerto)).play();

    }

    //ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    public void mostrarAnimacaoErro(ActionEvent ev) {
        //evento que represanta a ação do acerto

        switch (jogador.getFaseAtual()) {

            case 1:

                eventoErro = (ActionEvent event) -> {
                    //MUDA A COR DO BOTÃO
                    Button btemp = (Button) ev.getSource();
                    System.out.println("OLHA AÍ EU EXIBINDO O ERRO OOHHH");
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "    linear-gradient(#ff0000, #ff0000),\n"
                            + "    linear-gradient(#ff0000, #7B68EE),\n"
                            + "    linear-gradient(#483D8B, #ff0000),\n"
                            + "    linear-gradient(from 0% 0% to 15% 50%, rgba(230,230,230,0.9), rgba(255,255,255,0));"
                            + "    -fx-font-size: 35px;\n"
                            + "    -fx-pref-width: 88px;\n"
                            + "    -fx-pref-height: 80px;\n");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = (Button) ev.getSource();
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "    linear-gradient(#ffd65b, #e68400),\n"
                            + "    linear-gradient(#ffef84, #f2ba44),\n"
                            + "    linear-gradient(#ffea6a, #efaa22),\n"
                            + "    linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                            + "    linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
                            + "    -fx-background-radius: 30;\n"
                            + "    -fx-background-insets: 0,1,2,3,0;\n"
                            + "    -fx-text-fill: #654b00;\n"
                            + "    -fx-font-size: 35px;\n"
                            + "    -fx-pref-width: 88px;\n"
                            + "    -fx-pref-height: 80px;\n"
                    //                    //+ "    -fx-font-weight: bold;\n"
                    //                    + "    -fx-padding: 10 20 10 20;"
                    );
                };

                break;

            case 2:
                eventoErro = (ActionEvent event) -> {
                    //MUDA A COR DO BOTÃO
                    Button btemp = (Button) ev.getSource();
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "    linear-gradient(#ff0000, #ff0000),\n"
                            + "    linear-gradient(#ff0000, #7B68EE),\n"
                            + "    linear-gradient(#483D8B, #ff0000),\n"
                            + "    linear-gradient(from 0% 0% to 15% 50%, rgba(230,230,230,0.9), rgba(255,255,255,0));"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 100px;\n"
                            + "    -fx-pref-height: 70px;\n");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = (Button) ev.getSource();
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "    linear-gradient(#ffd65b, #e68400),\n"
                            + "    linear-gradient(#ffef84, #f2ba44),\n"
                            + "    linear-gradient(#ffea6a, #efaa22),\n"
                            + "    linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                            + "    linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
                            + "    -fx-background-radius: 30;\n"
                            + "    -fx-background-insets: 0,1,2,3,0;\n"
                            + "    -fx-text-fill: #654b00;\n"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 100px;\n"
                            + "    -fx-pref-height: 70px;\n"
                    //                    //+ "    -fx-font-weight: bold;\n"
                    //                    + "    -fx-padding: 10 20 10 20;"
                    );
                };

                break;

            case 3:

                eventoErro = (ActionEvent event) -> {
                    //MUDA A COR DO BOTÃO
                    Button btemp = (Button) ev.getSource();
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "    linear-gradient(#ff0000, #ff0000),\n"
                            + "    linear-gradient(#ff0000, #7B68EE),\n"
                            + "    linear-gradient(#483D8B, #ff0000),\n"
                            + "    linear-gradient(from 0% 0% to 15% 50%, rgba(230,230,230,0.9), rgba(255,255,255,0));"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 100px;\n"
                            + "    -fx-pref-height: 70px;\n");
                };

                eventoCorOriginal = (ActionEvent event) -> {
                    Button btemp = (Button) ev.getSource();
                    //(btemp).setText("X");
                    btemp.setStyle("-fx-background-color: \n"
                            + "    linear-gradient(#ffd65b, #e68400),\n"
                            + "    linear-gradient(#ffef84, #f2ba44),\n"
                            + "    linear-gradient(#ffea6a, #efaa22),\n"
                            + "    linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                            + "    linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n"
                            + "    -fx-background-radius: 30;\n"
                            + "    -fx-background-insets: 0,1,2,3,0;\n"
                            + "    -fx-text-fill: #654b00;\n"
                            + "    -fx-font-size: 17px;\n"
                            + "    -fx-pref-width: 100px;\n"
                            + "    -fx-pref-height: 70px;\n"
                    //                    //+ "    -fx-font-weight: bold;\n"
                    //                    + "    -fx-padding: 10 20 10 20;"
                    );
                };

                break;

        };

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
        eventoCenas = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //armazena a cena em que o botão 'btn_1' se encontra atualmente
                window = (Stage) btn_1.getScene().getWindow();
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
                    Logger.getLogger(ModelJogoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                sequenciaCenas = fxmloader.<Gui_SequenciaCenasController>getController();
                //cria uma cena 
                Scene cena = new Scene(proximaCena, 1200, 700);
                window.setTitle("Grafonema");//título da cena
                window.setScene(cena);
                window.show();//exibe a cena
                sequenciaCenas.setFaseAtual(jogador.getFaseAtual());
                try {
                    //funcionando
                    sequenciaCenas.executarCenaFimFase();
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ModelJogoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        //evento que mostra a cena do início da fase
        eventoInicioFase = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                sequenciaCenas.executarCenaMeioFase();
            }
        };

        eventoVoltar = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setTitle("Grafonema");
                window.setScene(cenaTemporaria);
                //mostrandoCena = false;
                setMostrandoCena(false);
                //eventoAcerto.handle(null);
                Button btemp = opcaoCorreta(null);
                window.show();
                jogador.setFaseAtual(jogador.getFaseAtual() + 1);
                gerarOpcaoAudio();

            }
        };

        new Timeline(
                new KeyFrame(Duration.seconds(0), eventoCenas),
                new KeyFrame(Duration.seconds(15), eventoInicioFase),
                new KeyFrame(Duration.seconds(25), eventoVoltar)).play();

    }

    public void mostrarCenaInicialFase() {
        //evento responsável por exibir as cenas de progresso na história
        eventoCenas = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println(" public void mostrarCenaInicialFase() {");
                //armazena a cena em que o botão 'btn_1' se encontra atualmente
                window = (Stage) btn_1.getScene().getWindow();
                Parent cenaPrincipal = null;
                //armeza a cena do botão 'btn_1' em uma variável temporária
                cenaTemporaria = btn_1.getScene();

                FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Gui_SequenciaCenas.fxml"));
                Parent proximaCena = null;
                try {
                    proximaCena = (Parent) fxmloader.load();
                } catch (IOException ex) {
                    Logger.getLogger(ModelJogoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                Gui_SequenciaCenasController sequenciaCenas = fxmloader.<Gui_SequenciaCenasController>getController();
                //cria uma cena 
                Scene cena = new Scene(proximaCena, 1200, 700);
                window.setTitle("Grafonema");//título da cena
                window.setScene(cena);
                window.show();//exibe a cena
                sequenciaCenas.setFaseAtual(jogador.getFaseAtual());

                sequenciaCenas.executarCenaInicial();

            }
        };

        //evento para voltar para o jogo pós exibição da cena
        eventoVoltar = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                System.out.println("evntVoltar mostrarCenaInicialFase");
                window.setTitle("Grafonema");
                window.setScene(cenaTemporaria);
                //mostrandoCena = false;
                setMostrandoCena(false);
                //eventoAcerto.handle(null);
                //Button btemp = opcaoCorreta(null);

                window.show();
                try {
                    gerarOpcaoAleatoria();
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(ModelJogoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        /**
         * OBSERVAÇÃO:
         *
         *
         * OS TEMPOS ENTRE O PRIMEIRO KEYFRAME E O SEGUNDO DEVE SER COMPATÍVEL
         * COM O TEMPO DENTRO DO EVENTO "eventoCenas"
         */
        new Timeline(
                new KeyFrame(Duration.seconds(0), eventoCenas),
                new KeyFrame(Duration.seconds(10), eventoVoltar)).play();

    }

    private void gerarOpcaoAudio() {
        System.out.println("Chamou audio aqui");
        int i = 0;
        int proxValor = 0;
        ArrayList novasOpcoes = new ArrayList(); //recebe os índices para as novas opções do array correspondente à fase
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

                    if (!indiceUtilizados.contains(proxValor)) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }

                preencherOpcoes(silabasSimples, som, novasOpcoes);

                break;
                
            case 3:
                
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

                    proxValor = indice.nextInt(80);

                    if (!indiceUtilizados.contains(proxValor)) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }

                preencherOpcoes(silabasSimplesB, som, novasOpcoes);
                break;

            case 4:
                i = 0;
                som = 0;
                if (!isGameOver()) {
                    som = gerarSomAleatorio();
                }
                indiceUtilizados.add(som);
                while (indiceUtilizados.size() <= 5) {

                    proxValor = indice.nextInt(93);
                    if (!indiceUtilizados.contains(proxValor)) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }
                preencherOpcoes(palavrasSimples, som, novasOpcoes);

                break;
            case 5:
                i = 0;
                som = 0;
                if (!isGameOver()) {
                    som = gerarSomAleatorio();
                }
                indiceUtilizados.add(som);
                while (indiceUtilizados.size() <= 5) {

                    proxValor = indice.nextInt(110);
                    if (!indiceUtilizados.contains(proxValor)) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }

                preencherOpcoes(silabasComplexas, som, novasOpcoes);
                break;
            case 6:
                i = 0;
                som = 0;
                if (!isGameOver()) {
                    som = gerarSomAleatorio();
                }
                indiceUtilizados.add(som);
                while (indiceUtilizados.size() <= 5) {

                    proxValor = indice.nextInt(80);
                    if (!indiceUtilizados.contains(proxValor)) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }
                preencherOpcoes(silabasComplexas2, som, novasOpcoes);
                break;

            case 7:
                i = 0;
                som = 0;
                if (!isGameOver()) {
                    som = gerarSomAleatorio();
                }
                indiceUtilizados.add(som);
                while (indiceUtilizados.size() <= 5) {

                    proxValor = indice.nextInt(264);
                    if (!indiceUtilizados.contains(proxValor)) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }
                preencherOpcoes(silabasComplexas3, som, novasOpcoes);
                break;
            case 9:
                i = 0;
                som = 0;
                if (!isGameOver()) {
                    som = gerarSomAleatorio();
                }
                indiceUtilizados.add(som);
                while (indiceUtilizados.size() <= 5) {

                    proxValor = indice.nextInt(50);
                    if (!indiceUtilizados.contains(proxValor)) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }
                preencherOpcoes(frasesSimples, som, novasOpcoes);
                break;

        }
    }

}
