package controller;

import java.io.IOException;
import java.net.URISyntaxException;
import model.ModelJogoPrincipal;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.FuncaoBotao;

/**
 * FXML Controller class
 *
 * @author jonas
 */
public class Gui_JogoPrincipalController implements Initializable {

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

    @FXML
    private Label tempo;

    @FXML
    private Label pontuacao;

    private ModelJogoPrincipal modelJogoPrincipal;
    @FXML
    private ProgressBar lifeBar;

    private Stage window;
    boolean indicacaoPular, pularErro;//indica que o jogador acionou o botão pular
    @FXML
    private ImageView imagemFundo;
    @FXML
    private Button ouvirAudio;

    private int faseAtual;
    @FXML
    private AnchorPane achorPane;
    @FXML
    private Label numFase;
    @FXML
    private ImageView imgReiniciar;

    public Gui_JogoPrincipalController() {

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagemFundo.toBack();
        String vogais[] = {"letra_a", "letra_e", "letra_i", "letra_o", "letra_u"};
        Random indiceVogal = new Random();
        modelJogoPrincipal = new ModelJogoPrincipal(btn_1, btn_2, btn_3, btn_4, btn_5, pular,
                pontuacao, lifeBar, tempo, ouvirAudio,
                imagemFundo, numFase);
        modelJogoPrincipal.iniciarMatrizAudiosVogal();//inicia a matriz de audios de vogais
    }

    FuncaoBotao funcao = new FuncaoBotao();

    public void iniciarJogo() {
        System.out.println("INICIOU JOGO");

        modelJogoPrincipal.gerarSomAleatorio();//gerar um som aleatorio
        modelJogoPrincipal.iniciarTimer();//inicia o relógio

        btn_1.setStyle("-fx-font-size: 30px; \n-fx-pref-width: 80px;\n -fx-pref-height: 80px;");
        btn_2.setStyle("-fx-font-size: 30px; \n -fx-pref-width: 80px;\n -fx-pref-height: 80px;");
        btn_3.setStyle("-fx-font-size: 30px; \n -fx-pref-width: 80px;\n -fx-pref-height: 80px;");
        btn_4.setStyle("-fx-font-size: 30px; \n -fx-pref-width: 80px;\n -fx-pref-height: 80px;");
        btn_5.setStyle("-fx-font-size: 30px; \n -fx-pref-width: 80px;\n -fx-pref-height: 80px;");

        funcao.setClique1();
        numFase.setText("Fase: 1/7");
    }

    /**
     * Trata o evento de quando o botão "Pular" for pressionado
     *
     * @param event
     */
    @FXML
    private void handlePular(ActionEvent event) throws InterruptedException, IOException, ClassNotFoundException, URISyntaxException {

        int qntPulosAtual = modelJogoPrincipal.jogador.getQntPulos();

            //EH ESSE O CERTO
        //gera uma opção aleatória
        modelJogoPrincipal.gerarOpcaoAleatoria();
        modelJogoPrincipal.jogador.setQntPulos(qntPulosAtual);//incrementa quantidade de pulos do jogador
        System.out.println("quantidade de pulos: " + qntPulosAtual);
        if (qntPulosAtual == 2) {
            modelJogoPrincipal.desabilitarPulo();
        }
//        }
        //seta indicacaoPular como true
        modelJogoPrincipal.setIndicacaoPular(true);
    }

    /**
     * Trata os eventos de quando um dos botões correspondentes às respostas são
     * pressionados
     *
     * @param event
     * @throws InterruptedException
     */
    @FXML
    //método referente aos botões de opção

    private void handleBotoes(ActionEvent event) throws InterruptedException, IOException {

        //se o clique está permitido realizar as tarefas
        if (funcao.getClique() == 1) {

            funcao.setClique0();//após o primeiro clique, bloquar os botões
            if (modelJogoPrincipal.verificarRelacaoGaFonema(event)) {
                //((Button) event.getSource()).setDisable(true);

                //MUDAR A APARENCIA DO BOTAO EM CASO DE ACERTO
                modelJogoPrincipal.incrementarPontuacao();//incrementa a pontuação do jogador
                modelJogoPrincipal.incrementarAcerto();//incrementar o acerto
                modelJogoPrincipal.mostrarAnimacaoAcerto();
            } else {
                //reduzir barra de vidas
                modelJogoPrincipal.mostrarAnimacaoErro(event);
                modelJogoPrincipal.reduzirLifeBar();
                modelJogoPrincipal.incrementarErro();//incrementa a quantidade de erro do jogador
                Button temp = modelJogoPrincipal.opcaoCorreta(event);

                //mostrar a animaçao de erro
                //animação da opção correta            
                if (modelJogoPrincipal.isGameOver()) {//se for o fim do jogo
                    temp = modelJogoPrincipal.opcaoCorreta(event);
                    //animação do fim de jogo
                    modelJogoPrincipal.mostraFimDeJogo(temp);
                } else {
                    //animação da opção correta
                    modelJogoPrincipal.mostrarOpcaoCorreta(temp);
                }
            }
        } else {

            System.out.println("NÃO FAZ NADA" + " valor cliq: " + funcao.getClique());
        }

    }

    /**
     * Executa novamente o áudio
     *
     * @param event botão ouvirAudio
     */
    @FXML
    private void handleOuvirAudio(ActionEvent event) throws ClassNotFoundException, URISyntaxException, IOException {
        String audio = modelJogoPrincipal.getAudioAtual();
        modelJogoPrincipal.tocarAudio(audio);
    }

    @FXML
    private void removerSombraBotao(MouseEvent event) {
        DropShadow sombras = new DropShadow();
        ((Button) event.getSource()).setEffect(sombras);
    }

    @FXML
    private void sombrearBotao(MouseEvent event) {
        ((Button) event.getSource()).setEffect(null);
    }

    public void definirImagemFundo() {
        modelJogoPrincipal.definirImagemFundo();
    }

    public void setFaseAtual(int faseAtual) {
        modelJogoPrincipal.setFaseAtual(faseAtual);
    }

    public int getFaseAtual() {
        return modelJogoPrincipal.getFaseAtual();
    }

    @FXML
    private void removerSombraImagem(MouseEvent event) {
        ((ImageView) event.getSource()).setEffect(null);
    }

    @FXML
    private void sombrearImagem(MouseEvent event) {
        DropShadow sombra = new DropShadow();
        ((ImageView) event.getSource()).setEffect(sombra);
    }

    @FXML
    private void reiniciarJogo(MouseEvent event) throws IOException {
        modelJogoPrincipal.reiniciarJogo(imgReiniciar);
    }

}
