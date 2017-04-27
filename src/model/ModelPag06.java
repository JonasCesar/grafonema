/*
 * Model da página 6
 */
package model;

import controller.Pag05Controller;
import controller.Pag07Controller;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author shadows
 */
public class ModelPag06 {

    private String unidadeAtual;
    private Stage janela;
    private String caminhoAudio;

    private Label p1, p2;
    private ModelClasseComum mCC;
    
    @FXML
    private Text instrucao;
    private EventHandler<ActionEvent> primeiroAudio;
    private EventHandler<ActionEvent> segundoAudio;

    public ModelPag06(Label p1, Label p2, Text instrucao1) {
        this.p1 = p1;
        this.p2 = p2;
        this.unidadeAtual = "u00";
        mCC = new ModelClasseComum(janela);
        this.instrucao = instrucao1;
    }

    /**
     * Define a unidade em que o software se encontra
     * @param unidade
     */
    public void setUnidadeAtual(String unidade) {
        this.unidadeAtual = unidade;
    }

    /**
     * Carrega a próxima página na tela
     *
     * @param event disparado pelo método avancar do controller
     * @throws IOException
     */
    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag07.fxml"));

        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag07Controller pg07Cont = fxmloader.<Pag07Controller>getController();
        System.out.println("Pag 6 " + getUnidadeAtual());
        pg07Cont.setUnidadeAtual(getUnidadeAtual());

        mCC.exibirCena(proximaCena, janela);
        pg07Cont.tocarAudio();
    }

    /**
     * Carrega a página anterior
     *
     * @param event disparado pelo método voltar do controller
     * @throws IOException
     */
    public void paginaAnterior(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag05.fxml"));
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag05Controller pg05Cont = fxmloader.<Pag05Controller>getController();

        mCC.exibirCena(proximaCena, janela);
        pg05Cont.setUnidadeAtual(getUnidadeAtual());
        pg05Cont.tocarAudio();
    }

    /**
     * Pega a unidade atual em execução
     *
     * @return string com o valor da unidade atual
     */
    public String getUnidadeAtual() {
        return this.unidadeAtual;
    }

    /**
     * Verifica se a resposta digita pelo usuário é a resposta correta
     *
     * @param resposta string digitada pela pessoa
     * @return
     */
    public boolean verificarResposta(String resposta) throws InterruptedException {
        boolean respostaCorreta = false;
        switch (getUnidadeAtual()) {
            case "u01":
                if (resposta.toUpperCase().equals("VOVÔ")) {
                    respostaCorreta = true;                    
                }
                break;
            default:
                break;
        }
        return respostaCorreta;
    }
    /**
     * Executa o áudio da página
     */
    public void tocarAudio() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "audios/u01/l1p6.mp3";
                break;
            default:
                break;
        }
        mCC.play(caminhoAudio);
    }
    /**
     * Define as labels que serão utilizadas na página baseado na unidade atual
     */
    public void definirLabels() {
        switch (getUnidadeAtual()) {
            case "u01":
                p1.setText("O");
                p2.setText("É MEU AMIGO");
                break;
            default:
                break;
        }
    }

    /**
     * Para o audio em execução
     */
    public void pararAudio() {
        mCC.pararAudio();
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

//    áudios de zoeira só pra teste    
//    public void tocarAudioAcerto(boolean acerto) {
//        caminhoAudio = "src/audios/u01/resposta_certa.mp3";
//        if (acerto) {
//            mCC.play(caminhoAudio);
//        } else {
//            caminhoAudio = "src/audios/u01/errou.mp3";
//            mCC.play(caminhoAudio);
//        }
//    }

    public void executarAudioFrase() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "audios/u01/frase.mp3";
                break;
            default:
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
    public void abrirManual(ActionEvent event, int pagina) throws IOException {
        mCC.pararAudio();
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirManual(event, pagina);
    }
    /**
     * Abre o função "ABC" do programa
     * @param event disparado quando o botão "ABC" é clicado
     * @param pagina pagina de onde a função "ABC" foi chamada
     * @throws IOException 
     */
    public void abrirABC(ActionEvent event, int pagina) throws IOException {
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirABC(event, pagina);
    }
    
    //faz exibir a instrução da atividade atual na tela
    public void definirInstrucao(String unidadeAtual) throws MalformedURLException {

        switch (unidadeAtual) {

            case "u01":
                instrucao.setText("Digite a palavra que você aprendeu para formar a frase:\n \"o vovô é meu amigo\"");
            break;
        }

    }
    
    private void tocarAudioParabens() throws InterruptedException {
        Random indiceParabens = new Random();
        int numeroAudio = indiceParabens.nextInt(3);
        caminhoAudio = "audios/acerto/"+numeroAudio+".mp3";
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
               executarAudioFrase();
            }
        };
        new Timeline(
                new KeyFrame(Duration.seconds(0), primeiroAudio),
                new KeyFrame(Duration.seconds(3), segundoAudio)).play();
    }

    public void audioErro() {
        Random indiceErro = new Random();
        int numeroAudio = indiceErro.nextInt(3);
        caminhoAudio = "audios/erro/"+numeroAudio+".mp3";
        mCC.play(caminhoAudio);
    }

}
