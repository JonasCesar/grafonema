package model;

import controller.Pag03Controller;
import controller.Pag05Controller;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelPag04 {
    
    //String que armazena o valor da unidade atual
    private String unidadeAtual;
    //Janela ondo programa
    private Stage janela;
    
    //Labels utilizadas na pagina
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
    
    
    //String utilizada nos diretórios dos audios
    private String caminhoAudio;
    //arquivo de audio que deve ser criado
    private File arquivo;
    
    //label que terá seu conteúdo substituído
    private Label espaco;
    //classe com métodos com a mesma estrutura das outras classes
    private ModelClasseComum mCC;
    /**
     * Construtor da classe
     * Labels que são referenciadas do controlador:
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @param p5
     * @param f1
     * @param f2
     * @param espaco 
     */
    public ModelPag04(Label p1, Label p2, Label p3, Label p4, Label p5, Label f1, Label f2, Label espaco) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.f1 = f1;
        this.f2 = f2;
        this.espaco = espaco;
        mCC = new ModelClasseComum(janela);
    }
    /**
     * Define a unidade atual
     * @param unidadeAtual 
     */
    public void setUnidadeAtual(String unidadeAtual) {
        this.unidadeAtual = unidadeAtual;
        //define o conteúdo das labels da pag 4 
        switch (unidadeAtual) {
            case "u01":
                p1.setText("VA");
                p2.setText("VE");
                p3.setText("VI");
                p4.setText("VO");
                p5.setText("VU");
                f1.setText("ÁR");
                f2.setText("RE");
                break;
            default:
                break;
        }
    }
    /**
     * Retorna a unidade atual
     * @return 
     */
    public String getUnidadeAtual() {
        return this.unidadeAtual;
    }

    /**
     * Avança para a pagin 5
     * @param event
     * @throws IOException 
     */
    public void proximaPagina(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag05.fxml"));

        //cria a próxima cena     
        Parent proximaCena = (Parent) fxmloader.load();
        Pag05Controller pg05Cont = fxmloader.<Pag05Controller>getController();
        pg05Cont.setUnidadeAtual(getUnidadeAtual());

        mCC.exibirCena(proximaCena, janela);
        pg05Cont.tocarAudio();
    }

    /**
     * Retorna para a pagina 3
     * @param event botão "Voltar" clicado
     * @throws IOException 
     */
    public void paginaAnterior(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag03.fxml"));
        //cria a próxima cena     
        Parent proximaCena = (Parent) fxmloader.load();
        Pag03Controller pg03Cont = fxmloader.<Pag03Controller>getController();

        mCC.exibirCena(proximaCena, janela);
        pg03Cont.setUnidadeAtual(getUnidadeAtual());
        pg03Cont.audioInicial();
        pg03Cont.setImagens(getUnidadeAtual());
    }

    /**
     * Verifica se se a sílaba escolhida é a correta
     * @param event mouse liberado
     * @return true ou false
     */
    public boolean verificarEscolhaSilaba(MouseEvent event) {
        //converte conteudo do evento para string
        String silabaEscolhida = ((Label) event.getSource()).getText();
        boolean opcaoCorreta = false;
        //verifica a unidade atual
        switch (getUnidadeAtual()) {
            case "u01":
                if (silabaEscolhida.equals("VO")) {
                    opcaoCorreta = true;
                }
                break;
            default:
                break;
        }
        //retorna o valor booleano
        return opcaoCorreta;
    }
    /**
     *Audio executado automaticamente quando a interface é iniciada 
     */
    public void tocarAudio() {
        //verifica a unidade atual
        switch (getUnidadeAtual()) {
            case "u01":
                //diretório do audio
                caminhoAudio = "src/audios/u01/l1p4.mp3";
                break;
            default:
                break;
        }
        //executa o audio
        mCC.play(caminhoAudio);
    }

    public void pararAudio() {
        mCC.pararAudio();
    }
    /**
     * Altera o conteúdo da label espaço vazia
     * @param evento 
     */
    public void alterarLabelEspaco(MouseEvent evento) {
        //pega o conteúdo da label selecionada
        espaco.setText(((Label) evento.getSource()).getText());
        //esconde a label que havia sido selecionada
        ((Label) evento.getSource()).setVisible(false);
    }
    /**
     * Handle para o botão que retorna ao menu inicial
     * @param event evento disparado ao se clicar no botãos
     * @throws IOException 
     */
    public void menuInicial(ActionEvent event) throws IOException {
       mCC.menuInicial(event);
    }
    /**
     * Executa a palavra que foi selecionada na lista de palavras estudadas
     * @param palavraSelecionada string da palavra selecionada
     */
    public void tocarAudioPalavraSelecionada(String palavraSelecionada) {
        mCC.tocarAudioPalavraSelecionada(palavraSelecionada);
    }

    public void executarPalavra() {
        switch (getUnidadeAtual()) {
            case "u01":
                caminhoAudio = "src/audios/u01/arvore.mp3";
                break;
            default:               
                break;
        }
        mCC.play(caminhoAudio);
    }
}
