/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class Jogador {
    private int pontuacaoTotal;
    private ArrayList pontuacaoPorFase;
    private int tempoJogoTotal;
    private int avatarEscolhido;
    private int qntVida;
    private int qntPulos;
    private int qntErros;
    private int acertosTotal;
    private int acertosPorFase;
    private int tempoParaResponderUmaRodada;
    private int mediaDeTempoParaResposta;
    private int pontuacaoJogoMemoriaTotal;
    private ArrayList pontuacaoJogoMemoriaFase;
    private ArrayList acertosJogosMemoriaTotal;
    private int faseAtual;

    public Jogador() {
        this.faseAtual = 0;
        this.qntPulos = 0;
        this.pontuacaoTotal = 0;
        this.acertosTotal = 0;
        this.qntErros = 0;
    }

    public int getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public void setPontuacaoTotal(int pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }

    public ArrayList getPontuacaoPorFase() {
        return pontuacaoPorFase;
    }

    public void setPontuacaoPorFase(ArrayList pontuacaoPorFase) {
        this.pontuacaoPorFase = pontuacaoPorFase;
    }

    public int getTempoJogoTotal() {
        return tempoJogoTotal;
    }

    public void setTempoJogoTotal(int tempoJogoTotal) {
        this.tempoJogoTotal = tempoJogoTotal;
    }

    public int getAvatarEscolhido() {
        return avatarEscolhido;
    }

    public void setAvatarEscolhido(int avatarEscolhido) {
        this.avatarEscolhido = avatarEscolhido;
    }

    public int getQntVida() {
        return qntVida;
    }

    public void setQntVida(int qntVida) {
        this.qntVida = qntVida;
    }

    public int getQntPulos() {
        return qntPulos;
    }

    public void setQntPulos(int qntPulos) {
        this.qntPulos = qntPulos+1;
    }

    public int getQntErros() {
        return qntErros;
    }

    public void setQntErros(int qntErros) {
        this.qntErros = qntErros;
    }

    public int getAcertosTotal() {
        return acertosTotal;
    }

    public void setAcertosTotal(int acertosTotal) {
        this.acertosTotal = acertosTotal;
    }

    public int getAcertosPorFase() {
        return acertosPorFase;
    }

    public void setAcertosPorFase(int acertosPorFase) {
        this.acertosPorFase = acertosPorFase;
    }

    public int getTempoParaResponderUmaRodada() {
        return tempoParaResponderUmaRodada;
    }

    public void setTempoParaResponderUmaRodada(int tempoParaResponderUmaRodada) {
        this.tempoParaResponderUmaRodada = tempoParaResponderUmaRodada;
    }

    public int getMediaDeTempoParaResposta() {
        return mediaDeTempoParaResposta;
    }

    public void setMediaDeTempoParaResposta(int mediaDeTempoParaResposta) {
        this.mediaDeTempoParaResposta = mediaDeTempoParaResposta;
    }

    public int getPontuacaoJogoMemoriaTotal() {
        return pontuacaoJogoMemoriaTotal;
    }

    public void setPontuacaoJogoMemoriaTotal(int pontuacaoJogoMemoriaTotal) {
        this.pontuacaoJogoMemoriaTotal = pontuacaoJogoMemoriaTotal;
    }

    public ArrayList getPontuacaoJogoMemoriaFase() {
        return pontuacaoJogoMemoriaFase;
    }

    public void setPontuacaoJogoMemoriaFase(ArrayList pontuacaoJogoMemoriaFase) {
        this.pontuacaoJogoMemoriaFase = pontuacaoJogoMemoriaFase;
    }

    public ArrayList getAcertosJogosMemoriaTotal() {
        return acertosJogosMemoriaTotal;
    }

    public void setAcertosJogosMemoriaTotal(ArrayList acertosJogosMemoriaTotal) {
        this.acertosJogosMemoriaTotal = acertosJogosMemoriaTotal;
    }

    public int getFaseAtual() {
        return faseAtual;
    }

    public void setFaseAtual(int faseAtual) {
        this.faseAtual = faseAtual;
    }
    
    
    
}
