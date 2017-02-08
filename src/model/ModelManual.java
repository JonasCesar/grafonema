/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Pag01Controller;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelManual {

    private ModelClasseComum mCC;
    private Stage janela;
    private String unidadeAtual;
    

    public ModelManual() {
        mCC = new ModelClasseComum(janela);
        unidadeAtual = "u00";
    }

    public void menuInicial(ActionEvent event) throws IOException {
        mCC.menuInicial(event);
    }

    public void pararAudio() {
        mCC.pararAudio();
    }

    public void voltar(ActionEvent evento, int pagina) throws IOException {
        janela = (Stage) ((Button) evento.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = null;
        Pag01Controller pag01Cont;
        
        fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag0"+pagina+".fxml"));

        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        

        switch(pagina){
            case 1:
                pag01Cont = fxmloader.<Pag01Controller>getController();
                System.out.println("Aqui 1");
                mCC.exibirCena(proximaCena, janela);
                System.out.println("Aqui 2");
                System.out.println(getUnidadeAtual());
                pag01Cont.setUnidadeAtual(getUnidadeAtual());
                System.out.println("Aqui 3");
                pag01Cont.tocarAudio();
                System.out.println("Aqui 4");
                break;
        }
        

        
        
    }

    public void setUnidadeAtual(String unidadeAtual) {
        this.unidadeAtual = unidadeAtual;
    }

    public String getUnidadeAtual() {
        return unidadeAtual;
    }

}
