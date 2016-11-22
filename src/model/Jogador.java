package model;

import java.util.ArrayList;

/**
 *
 * @author jonas
 */
public class Jogador {
    private int pontuacaoTotal, tempoJogoTotal, avatarEscolhido, qntVida,
            faseAtual, qntPulos, qntErros, acertosTotal,
            tempoParaResponderUmaRodada, mediaDeTempoParaResposta,
            pontuacaoJogoMemoriaTotal;
    
    private ArrayList pontuacaoPorFase, acertosPorFase, pontuacaoJogoMemoriaFase, 
            acertosJogosMemoriaTotal;
    private boolean estaComBonus;
    
    /**
     * Construtor da classe Jogador
     */
    public Jogador() {
        this.faseAtual = 1;
        this.qntPulos = 0;
        this.pontuacaoTotal = 0;
        this.acertosTotal = 0;
        this.qntErros = 0;
        this.estaComBonus = false;
        this.acertosPorFase = new ArrayList();
        
    }
    /**
     * Retorna pontuação total do jogador
     * @return pontuacaoTotal
     */
    public int getPontuacaoTotal() {
        return pontuacaoTotal;
    }
    /**
     * Define a potuação do jogador
     * @param pontuacaoTotal valor a ser definido como a pontuação do jogador
     */
    public void setPontuacaoTotal(int pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }
    /**
     * Retorna a pontuação obtidade em cada fase
     * @return pontuacaoPorFase
     */
    public ArrayList getPontuacaoPorFase() {
        return pontuacaoPorFase;
    }
    
    /**
     * Define atualiza a pontuação total nas fases do jogador
     * @param pontuacaoPorFase 
     */
    public void setPontuacaoPorFase(ArrayList pontuacaoPorFase) {
        this.pontuacaoPorFase = pontuacaoPorFase;
    }
    
    /**
     * Pega o tempo total do jogo
     * @return tempoJogoTotal
     */
    public int getTempoJogoTotal() {
        return tempoJogoTotal;
    }
    /**
     * Define o tempo total do jogo
     * @param tempoJogoTotal o tempo total gasto no jogo
     */
    public void setTempoJogoTotal(int tempoJogoTotal) {
        this.tempoJogoTotal = tempoJogoTotal;
    }
    /**
     * Retorna o avatar escolhido pelo usuário
     * @return avatarEscolhido
     */
    public int getAvatarEscolhido() {
        return avatarEscolhido;
    }
    
    /**
     * Define o avatar escolhido
     * @param avatarEscolhido avatar escolhido pelo usuário
     */
    public void setAvatarEscolhido(int avatarEscolhido) {
        this.avatarEscolhido = avatarEscolhido;
    }
    
    /**
     * Retorna a quantidade de vida do jogador
     * @return qntVida
     */
    public int getQntVida() {
        return qntVida;
    }
    
    /**
     * Define a quantidade de vida do jogador
     * @param qntVida quantidade de vida do jogador
     */
    public void setQntVida(int qntVida) {
        this.qntVida = qntVida;
    }
    /**
     * Retorna a quantidade de pulos do jogador
     * @return qntPulos
     */
    public int getQntPulos() {
        return qntPulos;
    }
    /**
     * Define a quantidade de pulos do jogador
     * @param qntPulos quantidade de pulos realizado pelo jogador
     */
    public void setQntPulos(int qntPulos) {
        this.qntPulos = qntPulos+1;
    }
    /**
     * Retorna a quantidade de erros do jogador
     * @return qntErros
     */
    public int getQntErros() {
        return qntErros;
    }
    /**
     * Define a quantidade de erros do jogador
     * @param qntErros quantidade de erros do jogador
     */
    public void setQntErros(int qntErros) {
        this.qntErros = qntErros;
    }
    /**
     * Retorna a quantidade de acertos totais do jogador
     * @return acertosTotal
     */
    public int getAcertosTotal() {
        return acertosTotal;
    }
    /**
     * Define a quantidade de acertos totais do jogador
     * @param acertosTotal acertos totais do jogador
     */
    public void setAcertosTotal(int acertosTotal) {
        this.acertosTotal = acertosTotal;
    }
    /**
     * Retorna a quantidade de acertos totais por fase do jogador
     * @return acertosPorFase
     */
    public ArrayList getAcertosPorFase() {
        return acertosPorFase;
    }
    /**
     * Define os acertos por fase do jogador
     * 
     * @param valor acertos
     * @param fase fase correspondente aos acertos
     */
    public void setAcertosPorFase(int fase, int valor) {
        int posicao = fase-1;
        this.acertosPorFase.add(posicao, valor);
    }
    /**
     * Retorna o tempo para responder uma jogada
     * @return tempoParaResponderUmaRodada
     */
    public int getTempoParaResponderUmaRodada() {
        return tempoParaResponderUmaRodada;
    }
    /**
     * Define o tempo para responder a uma jogada
     * @param tempoParaResponderUmaRodada tempo do jogador para responder uma rodada
     */
    public void setTempoParaResponderUmaRodada(int tempoParaResponderUmaRodada) {
        this.tempoParaResponderUmaRodada = tempoParaResponderUmaRodada;
    }
    /**
     * Retorna a media de tempo para resposta
     * @return retorna mediaDeTempoParaResposta
     */
    public int getMediaDeTempoParaResposta() {
        return mediaDeTempoParaResposta;
    }
    /**
     * Define a media de tempo para resposta
     * @param mediaDeTempoParaResposta  média de tempo para a resposta de um jogador
     */
    public void setMediaDeTempoParaResposta(int mediaDeTempoParaResposta) {
        this.mediaDeTempoParaResposta = mediaDeTempoParaResposta;
    }
    /**
     * Retorna a pontuação do jogo da memória
     * @return pontuacaoJogoMemoriaTotal
     */
    public int getPontuacaoJogoMemoriaTotal() {
        return pontuacaoJogoMemoriaTotal;
    }
    /**
     * Define a pontuação do jogo da memória
     * @param pontuacaoJogoMemoriaTotal pontuação total do jogo da memória
     */
    public void setPontuacaoJogoMemoriaTotal(int pontuacaoJogoMemoriaTotal) {
        this.pontuacaoJogoMemoriaTotal = pontuacaoJogoMemoriaTotal;
    }
    /**
     * Retorna a pontuação do jogo da memória por fase
     * @return pontuacaoJogoMemoriaFase
     */
    public ArrayList getPontuacaoJogoMemoriaFase() {
        return pontuacaoJogoMemoriaFase;
    }
    /**
     * Define a pontuação por fase do jogo da memória
     * @param pontuacaoJogoMemoriaFase pontuação do jogo da memória por fase
     */
    public void setPontuacaoJogoMemoriaFase(ArrayList pontuacaoJogoMemoriaFase) {
        this.pontuacaoJogoMemoriaFase = pontuacaoJogoMemoriaFase;
    }
    /**
     * Retorna os acertos totais do jogo de memória
     * @return acertosJogosMemoriaTotal
     */
    public ArrayList getAcertosJogosMemoriaTotal() {
        return acertosJogosMemoriaTotal;
    }
    /**
     * Define os acertos totais do jogo da memória
     * @param acertosJogosMemoriaTotal acertos totais do jogo da memória
     */
    public void setAcertosJogosMemoriaTotal(ArrayList acertosJogosMemoriaTotal) {
        this.acertosJogosMemoriaTotal = acertosJogosMemoriaTotal;
    }
    /**
     * Retorna a fase atual
     * @return faseAtual
     */
    public int getFaseAtual() {
        return faseAtual;
    }
    /**
     * Retorna a fase atual
     * @param faseAtual fase atual do jogador
     */
    public void setFaseAtual(int faseAtual) {
        this.faseAtual = faseAtual;
    }    
    /**
     * Seta o valor de estaComBonus
     * @param b true ou false
     */
    void setBonus(boolean b) {
        estaComBonus = b;
    }
    /**
     * Retorna se o jogador esta com bonus ou não
     * @return estaComBonus
     */
    public boolean getBonus(){
        return estaComBonus;
    }
}
