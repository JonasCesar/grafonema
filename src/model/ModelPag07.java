/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.MenuInicialController;
import controller.Pag06Controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
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
public class ModelPag07 {

    private Stage janela;
    private String caminhoAudio;    
    private String unidadeAtual;
    private ModelClasseComum mCC;
    public ModelPag07() {
        this.unidadeAtual = "u00";
        mCC = new ModelClasseComum(janela);
    }

    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/menuInicial.fxml"));

        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        MenuInicialController menuInicialCont = fxmloader.<MenuInicialController>getController();
        //menuInicialCont.setUnidadeAtual(getUnidadeAtual());
        salvarPalavraEstudadas(getUnidadeAtual());
        mCC.exibirCena(proximaCena, janela);
    }

    public void setUnidadeAtual(String unidade, Label tituloUnidade) {
        this.unidadeAtual = unidade;
        
        System.out.println(tituloUnidade+" " + unidade);
        tituloUnidade.setText(tituloUnidade.getText() + " " + unidadeAtual.substring(1));
    }

    public void tocarAudio() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/l1p1.MP3";
                break;
            default:
                System.out.println("Não foi");
                break;
        }

        mCC.play(caminhoAudio);
    }

    public String getUnidadeAtual() {
        return this.unidadeAtual;

    }

    public void pararAudio() {
        mCC.pararAudio();
    }

    public void menuInicial(ActionEvent event) throws IOException {
        mCC.menuInicial(event);

    }

    public void tocarAudioPalavraSelecionada(String palavraSelecionada) {
        mCC.tocarAudioPalavraSelecionada(palavraSelecionada);
    }

    public void salvarPalavraEstudadas(String unidade) throws IOException {
        String novaPalavra = "";
        boolean encontrado = false;
        BufferedReader lerArq = null;
        switch (unidade) {
            case "u01":
                novaPalavra = "\nVOVÔ";
                break;
            default:
                break;
        }
        File arquivoEscrita = new File("src/AudiosPalavrasEstudadas/texto.txt"); // se já existir, será sobreescrito
        FileWriter fw = new FileWriter(arquivoEscrita, true);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            FileReader arquivoLeitura = new FileReader("src/AudiosPalavrasEstudadas/texto.txt");
            lerArq = new BufferedReader(arquivoLeitura);

            String linha = lerArq.readLine();
            if (linha == null) {//ocorre quando for
                System.out.println("Linha nula");
                bw.write(novaPalavra);
                encontrado = true;
            } else {
                Scanner wordFinder = new Scanner(arquivoEscrita);
                String palavra = wordFinder.findWithinHorizon(novaPalavra, 0);
                boolean jaFoiAdicionada = palavra.length() != 0;
                if (!jaFoiAdicionada) {
                    bw.write(novaPalavra);
                    bw.flush();
                }
            }
            lerArq.close();
        } catch (Exception e) {
        }

        bw.close();
    }

    public void paginaAnterior(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag06.fxml"));
        //cria a próxima cena chamando a inteface dos avatares        
        Parent proximaCena = (Parent) fxmloader.load();
        Pag06Controller pg06Cont = fxmloader.<Pag06Controller>getController();

        mCC.exibirCena(proximaCena, janela);
        pg06Cont.setUnidadeAtual(getUnidadeAtual());
        pg06Cont.tocarAudio();
    }

}
