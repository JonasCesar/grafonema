package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

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

    @FXML
    private Label pontuacao;

    @FXML
    private ProgressBar lifeBar;
    private String vogais[] = {"A", "E", "I", "O", "U"};

    //o nome dos arquivos das vogais
    private String audioVogais[] = {"letra_a", "letra_e", "letra_i", "letra_o", "letra_u"};

    public Jogador jogador = new Jogador();

    private Map<String, String> matrizVogais;

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
            System.out.println("Fase bônus");
            jogador.setFaseAtual(jogador.getFaseAtual() + 1);
        }
        //quando for o inicio do jogo
        if (jogador.getFaseAtual() == 0) {
            jogador.setFaseAtual(1); //altera a fase do jogador para atual
        }
        
        ArrayList novasOpcoes = new ArrayList(); //recebe os índices para as novas opções do array correspondente à fase
        ArrayList indiceUtilizados = new ArrayList();//array que receberá os índices que já foram utilizados
        Random indice = new Random();//gerador de índices aleatorios
        switch (jogador.getFaseAtual()) {//verifica qual a fase em que o jogador se encontra
            case 1: //se for na fase 1
                gerarSomAleatorio(); //gera um som aleatório
                int i = 0;
                //loop que gera os índices e os adiciona no array novasOpcoes
                while (i < 5) {
                    int proxValor = indice.nextInt(5);
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
                btn_1.setText("bone");
                btn_2.setText("que");
                btn_3.setText("bone");
                btn_4.setText("que");
                btn_5.setText("cena");
                break;
            default:
                break;
        }
    }
    /**
     * Gera um som aleatório
     */
    public void gerarSomAleatorio() {
        Random indice = new Random();
        int fase = jogador.getFaseAtual();
        if (fase == 0) {
            jogador.setFaseAtual(1);
        }

        switch (jogador.getFaseAtual()) {
            case 1:
                int i = indiceAudio.nextInt(5);
                audio.setText(audioVogais[i]);
                break;
            case 2:
                break;
            default:
                break;
        }

    }

    public boolean verificarRelacaoGaFonema(ActionEvent event) {
        String opcaoEscolhida = (((Button) event.getSource()).getText());
        System.out.println(opcaoEscolhida);
        System.out.println(getKeyByValue(matrizVogais, opcaoEscolhida));
        return ((getKeyByValue(matrizVogais, opcaoEscolhida)).equals(audio.getText()));
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
        double valorAnterior = lifeBar.getProgress();
        double valorAtualizado = valorAnterior - 0.2;
        System.out.println(valorAnterior);
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
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Incrementa, em valores de 10, a quantidade de pontos do jagador
     */
    public void incrementarPontuacao() {
        int pontuacaoAnterior = Integer.parseInt(pontuacao.getText());
        int ptAnterior = jogador.getPontuacaoTotal();
        int novaPontuacao = pontuacaoAnterior + 10;
        jogador.setPontuacaoTotal(novaPontuacao);
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
     * Habilita todos os botões
     */
    public void habilitarBotoes() {
        btn_1.setDisable(false);
        btn_2.setDisable(false);
        btn_3.setDisable(false);
        btn_4.setDisable(false);
        btn_5.setDisable(false);

    }

    /**
     * OBS: O BOTÃO SELECIONADO AQUI DEVERÁ MUDAR DE COR OU TER OUTRA FORMA DE
     * DESTAQUE QUE MOSTRE QUE A OPÇÃO ESTÁ ERRADA
     *
     * @param event
     * @return
     */
    public Button opcaoCorreta(ActionEvent event) {
        String opcaoEscolhida = (((Button) event.getSource()).getText());
        Button temporario = null;
        String opcaoCorreta = matrizVogais.get(audio.getText());
        System.out.println(opcaoCorreta);
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
        jogador.setQntErros(jogador.getQntErros() + 1);
        System.out.println("Erros " + jogador.getQntErros());
    }
    
    /**
     * Verifica se é o fim do jogo para o jogador
     * @return true se sim, do contrário false
     */
    public boolean isGameOver() {
        boolean fimDeJogo = false;
        if (jogador.getQntErros() == 5) {
            fimDeJogo = true;
        }
        return fimDeJogo;
    }
}
