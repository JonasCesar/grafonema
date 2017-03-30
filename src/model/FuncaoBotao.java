package model;

/**
 *
 * @author jonas
 * Essa classe fará que os botões opções de grafemas fiquem inativos depois que
 * o usuário clica pela primeira vez
 */
public class FuncaoBotao {

    public static int clique; //variável que para controle da permissão de clique

    public FuncaoBotao() {

    }

    //método para indicar que o clique está liberado (clique = 1)
    public void setClique1() {
        this.clique = 1;
        System.out.println("clique setado como 1. Valor clique: " + clique);
    }
    
    //método para indicar que o clique não está liberado (clique = 2)
    public void setClique0() {
        this.clique = 0;
        System.out.println("clique setado como 0. Valor clique: " + clique);
    }

    //método que o valor da variável clique
    public int getClique() {
        return this.clique;
    }

}
