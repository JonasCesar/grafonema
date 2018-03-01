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
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    boolean indicacaoPular, pularErro;//indica que o jogador acionou o botão pular
    @FXML
    private ImageView imagemFundo;
    @FXML
    private Button ouvirAudio;
    @FXML
    private AnchorPane achorPane;
    @FXML
    private Label numFase;
    @FXML
    private ImageView imgReiniciar;
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
                imagemFundo, numFase, DicaBotao0,DicaBotao1,DicaBotao2,DicaBotao3,DicaBotao4);
        modelJogoPrincipal.iniciarMatrizAudiosVogal();//inicia a matriz de audios de vogais
        Tooltip.install(imgReiniciar, new Tooltip("Clique para reiniciar o jogo"));
        Image image = new Image(getClass().getResourceAsStream("som32.png"));
        DicaBotao0.setGraphic(new ImageView(image));
        DicaBotao1.setGraphic(new ImageView(image));
        DicaBotao2.setGraphic(new ImageView(image));
        DicaBotao3.setGraphic(new ImageView(image));
        DicaBotao4.setGraphic(new ImageView(image));        
        
    }

    FuncaoBotao funcao = new FuncaoBotao();

    public void iniciarJogo() {
        
        
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
                modelJogoPrincipal.mostrarAnimacaoAcerto(event);
            } else {
                //reduzir barra de vidas
                modelJogoPrincipal.mostrarAnimacaoErro(event);
                modelJogoPrincipal.reduzirLifeBar();
                modelJogoPrincipal.incrementarErro();//incrementa a quantidade de erro do jogador
                Button temp = modelJogoPrincipal.opcaoCorreta(event);
                if (modelJogoPrincipal.jogador.getBarraVida() == 3) {
                    lifeBar.setId("lifeBarOrange");
                } else if (modelJogoPrincipal.jogador.getBarraVida() == 5) {
                    lifeBar.setId("lifeBarRed");
                }

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
        }

    }

    /**
     * Executa novamente o áudio
     *
     * @param event botão ouvirAudio
     */
    @FXML
    public void handleOuvirAudio(ActionEvent event) {
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

    @FXML
    private void tocarDica(ActionEvent event) throws InterruptedException {
        modelJogoPrincipal.tocarDica(event);
    }

    public void gerarOpcaoAleatoria() throws InterruptedException, IOException {
        modelJogoPrincipal.gerarOpcaoAleatoria();                
    }

    public void pararRelogio() {
        modelJogoPrincipal.pararRelogio();
    }
    
    public void iniciarRelogio(){
        modelJogoPrincipal.iniciarTimer();
    }

    public void setMostrandoCena(boolean b) {
       modelJogoPrincipal.setMostrandoCena(b);
    }
    
    public int getPontuacao(){
        return modelJogoPrincipal.getPontuacao();
    }

    public void setPontuacao(int pontuacao) {
        modelJogoPrincipal.setPontuacao(pontuacao);
    }

    public void setLabelFase(String string) {
        modelJogoPrincipal.setLabelFase(string);
    }

}
