/*
 * Model da pagina 01
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

    /**
     * Carrega a próxima página na tela
     *
     * @param event disparado pelo método avancar do controller
     * @throws IOException
     */
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
    /**
     *Define a unidade em que o software se encontra
     */
    public void setUnidadeAtual(String unidade, Label tituloUnidade) {
        this.unidadeAtual = unidade;

        System.out.println(tituloUnidade + " " + unidade);
        tituloUnidade.setText(tituloUnidade.getText() + " " + unidadeAtual.substring(1));
    }
    /**
     * Executra o audio que deve ser executado nessa pagina
     */
    public void tocarAudio() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/l1p1.mp3";
                break;
            default:

                break;
        }

        mCC.play(caminhoAudio);
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
    /**
     * Salva as palavras estudadas
     * @param unidade
     * @throws IOException 
     */
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
        /**
         * O código abaixo verifica se a nova palavra a ser adicionada já existe no arquivo
         */
        File arquivoEscrita = new File("src/AudiosPalavrasEstudadas/texto.txt"); // se já existir, será sobreescrito
        FileWriter escreverArquivo = new FileWriter(arquivoEscrita, true);
        BufferedWriter bufferDeEscrita = new BufferedWriter(escreverArquivo);
        try {
            FileReader arquivoLeitura = new FileReader("src/AudiosPalavrasEstudadas/texto.txt");
            lerArq = new BufferedReader(arquivoLeitura);

            String linha = lerArq.readLine();
            if (linha == null) {//ocorre quando for
                bufferDeEscrita.write(novaPalavra);
                encontrado = true;
            } else {
                Scanner textoNoArquivo = new Scanner(arquivoEscrita);
                String palavra = textoNoArquivo.findWithinHorizon(novaPalavra, 0);
                boolean jaFoiAdicionada = palavra.length() != 0;
                if (!jaFoiAdicionada) {
                    bufferDeEscrita.write(novaPalavra);
                    bufferDeEscrita.flush();
                }
            }
            lerArq.close();
        } catch (Exception e) {
        }

        bufferDeEscrita.close();
    }
/**
     * Carrega a página anterior
     * @param event disparado pelo método voltar do controller
     * @throws IOException
     */
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

    public void abrirABC(ActionEvent event, int pagina) throws IOException {
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirABC(event, pagina);
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

}
