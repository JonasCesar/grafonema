package model;

import controller.Gui_JogoPrincipalController;
import java.io.File;
import java.io.IOException;
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
public class JogoPrincipal {

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
            eventoVoltar, eventoAcerto, eventoFimAcerto, reiniciarRelogio;
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

    public Jogador jogador = new Jogador();

    private Map<String, String> matrizVogais, matrizSilabasSimples,
            matrizSilabasSimplesB;

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

    public JogoPrincipal(Button b1, Button b2, Button b3, Button b4, Button b5,
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
        this.mostrandoCena = false;
        this.indicacaoPular = false;
        this.pularErro = false;
        this.tempo = tempo;
        nomeAudioAtual = "";

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

        if (jogador.getQntErros() + jogador.getAcertosTotal() == 15) {
            jogador.setQntErros(0);//restaura a quantidade de erros do jogador
            jogador.setQntPulos(-1); //restaura a quantidade de pulos disponível
            jogador.setBonus(false);//retira o bônus do jogador
            jogador.setAcertosPorFase(jogador.getFaseAtual(), jogador.getAcertosTotal());
            jogador.setFaseAtual(jogador.getFaseAtual() + 1);//atualiza a fase do jogador
            jogador.setAcertosTotal(0);
        }

        int i = 0;
        int proxValor = 0;
        ArrayList novasOpcoes = new ArrayList(); //recebe os índices para as novas opções do array correspondente à fase
        ArrayList indiceUtilizados = new ArrayList();//array que receberá os índices que já foram utilizados ????
        Random indice = new Random();//gerador de índices aleatorios
        switch (jogador.getFaseAtual()) {//verifica qual a fase em que o jogador se encontra
            case 1: //FASE 1
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
                i = 0;
                som = 0;
                if (!isGameOver()) {
                    som = gerarSomAleatorio();
                }
                indiceUtilizados.add(som);
                while (indiceUtilizados.size() <= 5) {

                    proxValor = indice.nextInt(81);

                    if (!indiceUtilizados.contains(proxValor)) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }

                preencherOpcoes(silabasSimplesB, som, novasOpcoes);
                break;
        }
    }

    /*função para preencher as opções na tela inserindo 
     em um local aleatório a opção correspondente ao áudio
     (USADA A PARTIR DA FASE DAS SÍLABAS)
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
                break;
            case 2:
                i = indiceAudio.nextInt(29);
                System.out.println("o som gerado foi o som: " + audioSilabasSimples[i]);
                tocarAudio(audioSilabasSimples[i]);
                y = i;
                break;
            case 3:
                i = indiceAudio.nextInt(80);
                System.out.println("o som gerado foi o som: " + audioSilabasSimplesB[i]);
                tocarAudio(audioSilabasSimplesB[i]);
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
    public Button opcaoCorreta(ActionEvent event) {
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
            default:
                break;
        }

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
                    Logger.getLogger(JogoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(Gui_JogoPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(cenaPrincipal, 900, 700);
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
                try {
                    //cenaPrincipal é definida como a classe com as sequencias de cenas
                    cenaPrincipal = FXMLLoader.load(getClass().getResource("/interfaces/Gui_SequenciaCenas.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(Gui_JogoPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(cenaPrincipal, 900, 700);
                window.setTitle("Grafonema");
                window.setScene(scene);
                window.show();
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
                    Logger.getLogger(JogoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        //animação que exibe as cenas e volta para a interface principal do jogo
        new Timeline(
                new KeyFrame(Duration.seconds(0), eventoCenas),
                new KeyFrame(Duration.seconds(5), eventoVoltar)).play();
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

            int i = 5;

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
                                    System.out.println("setou o i como 30");
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
                            System.out.println(i);
                            i = 5;
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
        eventoAcerto = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Button btemp = opcaoCorreta(event);
                (btemp).setText("X");
            }
        };

        //evento que representa a ação a ser feita depois da 
        //animação de acerto
        eventoFimAcerto = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    gerarOpcaoAleatoria();
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(Gui_JogoPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //indicacaoPular = true;
                setIndicacaoPular(true);
            }
        };
        new Timeline(
                new KeyFrame(Duration.seconds(0), eventoAcerto),
                new KeyFrame(Duration.seconds(1), eventoFimAcerto)).play();
    }

}
