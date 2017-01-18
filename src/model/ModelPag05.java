/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Pag04Controller;
import controller.Pag06Controller;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelPag05 {
    private Stage janela;
    private String unidadeAtual;
    @FXML
    private Label p1;
    @FXML
    private Label p4;
    @FXML
    private Label p3;
    @FXML
    private Label p5;
    @FXML
    private Label p2;
    
    @FXML
    private Label f2;
    @FXML
    private Label f1;
    
    public ModelPag05(Label p1,Label p2, Label p3, Label p4, Label p5, Label f1, Label f2) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.f1 = f1;
        this.f2 = f2;
        this.unidadeAtual = "u00";
    }    

    public void setUnidadeAtual(String unidadeAtual) {
        this.unidadeAtual = unidadeAtual;
        switch(unidadeAtual){
            case "u01":
                p1.setText("VA");
                p2.setText("VE");
                p3.setText("VI");
                p4.setText("VO");
                p5.setText("VU");                
                f1.setText("POL");
                break;
            default:
                break;
        }
    }

    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag06.fxml"));        
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag06Controller pg06Cont = fxmloader.<Pag06Controller>getController();
        pg06Cont.setUnidadeAtual(getUnidadeAtual());
        
        Scene cena = new Scene(proximaCena, 900, 700);//tamanho
        janela.setTitle("Projeto 2");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface
    }

    public void paginaAnterior(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag04.fxml"));        
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag04Controller pg04Cont = fxmloader.<Pag04Controller>getController();
        
        Scene cena = new Scene(proximaCena, 900, 700);//tamanho
        janela.setTitle("Projeto 2");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface
        pg04Cont.setUnidadeAtual(getUnidadeAtual());
    }

    private String getUnidadeAtual() {
        return this.unidadeAtual;
    }

    public boolean verificarEscolhaSilaba(MouseEvent event) {
        String silabaEscolhida = ((Label)event.getSource()).getText();
        boolean opcaoCorreta = false;
        switch(getUnidadeAtual()){
            case "u01":
                if(silabaEscolhida.equals("VO")){
                    opcaoCorreta = true;
                }                
                break;
            default:
                break;
        }
        
        return opcaoCorreta;
    }
    
}
