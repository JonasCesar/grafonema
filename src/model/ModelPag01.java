/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import controller.Pag01Controller;

/**
 *
 * @author shadows
 */
public class ModelPag01 {
    private String unidadeAtual;
    private Pag01Controller pg01;
    
    public ModelPag01() {
    }

    public String getUnidadeAtual() {
        return unidadeAtual;
    }
    /**
     * OBS: Enviar a atualização da unidade para todas as
     * classes que utilizarem
     * @param unidadeAtual 
     * @param classe 
     */
    public void setUnidadeAtual(String unidadeAtual, Object controller) {
        this.unidadeAtual = unidadeAtual;
        pg01 = (Pag01Controller) controller;
        pg01.setUnidadeAtual(unidadeAtual);
        
    }

    void tocarAudio(Object controller) {
        pg01 = (Pag01Controller) controller;
        pg01.tocarAudio();
    }
    
    
}
