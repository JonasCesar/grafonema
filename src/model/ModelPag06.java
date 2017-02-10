/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Pag05Controller;
import controller.Pag07Controller;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

    public ModelPag06(Label p1, Label p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.unidadeAtual = "u00";
        mCC = new ModelClasseComum(janela);
    }
    /**
     *Define a unidade em que o software se encontra
     */
    public void setUnidadeAtual(String unidade) {
        this.unidadeAtual = unidade;
    }
    /**
     * Carrega a próxima página na tela
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
     * @return string com o valor da unidade atual
     */
    public String getUnidadeAtual() {
        return this.unidadeAtual;
    }
/**
 * Verifica se a resposta 
 * @param resposta string digitada pela pessoa
 * @return 
 */
    public boolean verificarResposta(String resposta) {
        boolean respostaCorreta = false;
        switch (getUnidadeAtual()) {
            case "u01":
                if (resposta.toUpperCase().equals("VOVÔ")) {
                    respostaCorreta = true;
                    tocarAudioAcerto(respostaCorreta);
                } else {
                    tocarAudioAcerto(false);
                }
                break;
            default:
                break;
        }
        return respostaCorreta;
    }

    public void tocarAudio() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/l1p6.mp3";
                break;
            default:
                break;
        }
        mCC.play(caminhoAudio);
    }

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
     * @param event disparado pelo método "menuInicial" do controller
     * @throws IOException 
     */
    public void menuInicial(ActionEvent event) throws IOException {
        mCC.menuInicial(event);
    }
/**
     * Executa o audio da palavra clicada
     * @param palavraSelecionada string que representa a palavra selecionada
     */
   public void tocarAudioPalavraSelecionada(String palavraSelecionada) {
        mCC.tocarAudioPalavraSelecionada(palavraSelecionada);
    }

    public void tocarAudioAcerto(boolean acerto) {
        caminhoAudio = "src/audios/u01/resposta_certa.mp3";
        if (acerto) {
            mCC.play(caminhoAudio);
        } else {
            caminhoAudio = "src/audios/u01/errou.mp3";
            mCC.play(caminhoAudio);
        }
    }

    public void executarAudioFrase() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/frase.mp3";
                break;
            default:
                break;
        }
        mCC.play(caminhoAudio);
    }
/**
     * Carrega a interface do manual do software
     * @param event disparado pelo método
     * @param pagina pagina de onde o manual foi chamado
     * @throws IOException 
     */
    public void abrirManual(ActionEvent event, int pagina) throws IOException {
        mCC.pararAudio();
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirManual(event, pagina);
    }

    public void abrirABC(ActionEvent event, int pagina) throws IOException {
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirABC(event, pagina);
    }

}
