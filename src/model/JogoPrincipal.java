package model;

import controller.Gui_JogoPrincipalController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    private Button btn_5;
    @FXML
    private Button btn_4;
    @FXML
    private Button pular;
    @FXML
    private Label audio;

    private EventHandler<ActionEvent> eventoFinal;
    @FXML
    private Label pontuacao;

    @FXML
    private ProgressBar lifeBar;
    private String vogais[] = {"A", "E", "I", "O", "U"};
    private String silabas[] = {"BA", "BE", "BI", "BO", "BU"};

    //o nome dos arquivos das vogais
    private String audioVogais[] = {"letra_a", "letra_e", "letra_i", "letra_o", "letra_u"};
    private String audioSilabas[] = {"sil_ba", "sil_be", "sil_bi", "sil_bo", "sil_bu"};

    public Jogador jogador = new Jogador();

    private Map<String, String> matrizVogais;
    private Map<String, String> matrizSilabas;

    private Random indiceAudio;

    private Stage window;

    public JogoPrincipal(Button b1, Button b2, Button b3, Button b4, Button b5,
            Button pular, Label audio, Label pontuacao, ProgressBar lifeBar) {

        this.btn_1 = b1;
        this.btn_2 = b2;
        this.btn_3 = b3;
        this.btn_4 = b4;
        this.btn_5 = b5;
        this.pular = pular;
        this.audio = audio;
        this.lifeBar = lifeBar;
        this.pontuacao = pontuacao;
        this.indiceAudio = new Random();
        this.matrizVogais = new HashMap<String, String>();

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
            jogador.setFaseAtual(jogador.getFaseAtual() + 1);
        }

        int i = 0;
        int proxValor = 0;
        ArrayList novasOpcoes = new ArrayList(); //recebe os índices para as novas opções do array correspondente à fase
        ArrayList indiceUtilizados = new ArrayList();//array que receberá os índices que já foram utilizados
        Random indice = new Random();//gerador de índices aleatorios
        switch (jogador.getFaseAtual()) {//verifica qual a fase em que o jogador se encontra
            case 1: //se for na fase 1
                gerarSomAleatorio(); //gera um som aleatório                
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
            case 2:
                i = 0;
                gerarSomAleatorio();
                while (i < 5) {
                    proxValor = indice.nextInt(5);
                    if (!indiceUtilizados.contains(proxValor)) {//se o índice ainda não foi utilizado
                        novasOpcoes.add(proxValor);//adiciona o indice no array
                        indiceUtilizados.add(proxValor);//adiciona o indice utilizado vetor de utilizados
                        i++;
                    }
                }
                btn_1.setText(silabas[(int) novasOpcoes.get(0)]);
                btn_2.setText(silabas[(int) novasOpcoes.get(1)]);
                btn_3.setText(silabas[(int) novasOpcoes.get(2)]);
                btn_4.setText(silabas[(int) novasOpcoes.get(3)]);
                btn_5.setText(silabas[(int) novasOpcoes.get(4)]);
                break;
            default:
                break;
        }
    }

    /**
     * Gera o som aleatório que o jogador deverá relacionar com uma das opções
     */
    public void gerarSomAleatorio() {
        //objeto que será utilizado para gera um número aleatório
        Random indice = new Random();
        int i = 0;
        int fase = jogador.getFaseAtual();//pega a fase em que o jogador se encontra
        if (fase == 0) {//se for a primeira fase
            jogador.setFaseAtual(1);//define a fase atual como 1
        }
        //verifica qual a fase atual do jogador
        switch (jogador.getFaseAtual()) {
            case 1:
                i = indiceAudio.nextInt(5);//gera um índice entre 0 - 4 
                audio.setText(audioVogais[i]);//atualiza o áudio
                break;
            case 2:
                i = indiceAudio.nextInt(5);
                audio.setText(audioSilabas[i]);
                break;
            default:
                break;
        }

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
        boolean resultado = ((getKeyByValue(matrizVogais, opcaoEscolhida)).equals(audio.getText()));
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
        double valorAtualizado = valorAnterior - 0.2;
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
     * Inicia matriz de vogais
     */
    public void iniciarMatrizAudiosVogal() {
        matrizVogais.put("letra_a", "A");
        matrizVogais.put("letra_e", "E");
        matrizVogais.put("letra_i", "I");
        matrizVogais.put("letra_o", "O");
        matrizVogais.put("letra_u", "U");
    }

    public void iniciarMatrizAudioSilabas() {
        matrizSilabas.put("sil_ba", "BA");
        matrizSilabas.put("sil_be", "BE");
        matrizSilabas.put("sil_bi", "BI");
        matrizSilabas.put("sil_bo", "BO");
        matrizSilabas.put("sil_bu", "BU");
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
        //gera a nova pontuação somando 10 à pontuação anterior        
        if (jogador.getBonus()) {
            valorAcrescentar = 20;
        }

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
        //pega o valor da opção correta
        String opcaoCorreta = matrizVogais.get(audio.getText());
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
        if (jogador.getQntErros() == 5) {//se o jogador errou 5 vezes
            fimDeJogo = true;
        }
        return fimDeJogo;
    }

    public void mostrarOpcaoCorreta(Button temp) {
        eventoFinal = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    gerarOpcaoAleatoria();
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(Gui_JogoPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(temp.opacityProperty(), .1)),
                new KeyFrame(Duration.seconds(3), new KeyValue(temp.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(2), eventoFinal)).play();
    }
}
