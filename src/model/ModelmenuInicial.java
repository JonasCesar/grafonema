/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import controller.Pag01Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelmenuInicial {
    
    private Stage janela;
    private String unidade;
    private ModelPag01 modelPag01;
    
    public ModelmenuInicial() {
        
        
    }
    
    public void iniciar(ActionEvent event, String unidade) throws IOException{
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag01.fxml"));
        
        //cria a próxima cena chamando a inteface dos avatares
        Parent proximaCena = (Parent) fxmloader.load();
        Pag01Controller pg01Cont = fxmloader.<Pag01Controller>getController();
        pg01Cont.setUnidadeAtual(unidade);        
        Scene cena = new Scene(proximaCena, 900, 700);//tamanho
        janela.setTitle("Projeto 2");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface
        pg01Cont.tocarAudio();
        
    }
}
