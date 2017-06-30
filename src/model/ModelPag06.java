/*
 * Model da página 6
 */
package model;

import controller.Pag05Controller;
import controller.Pag05aController;
import controller.Pag06aController;
import controller.Pag07Controller;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author shadows
 */
public class ModelPag06 {

    private int unidadeAtual;
    private Stage janela;
    private String caminhoAudio;

    private Label p1, p2;
    private ModelClasseComum mCC;

    @FXML
    private Text instrucao;
    private EventHandler<ActionEvent> primeiroAudio;
    private EventHandler<ActionEvent> segundoAudio;
    private ListView<String> listaPalavras;
    private String respostasCorretas[] = {" ", "VOVÔ", "POVO", "TATO", "UVA", "VIVA",
        "LUVA", "LATA", "BEBÊ", "BOLA", "BOCA", "BALA", "HOJE", "PIPA", "FURO", "FITA", "JOGOS",
        "ROXO", "GATO/RATO", "BONECA", "DOCES", "SINOS", "RUA", "DUAS", "ESSA", "SETE", "MOTIVOS", "ACUMULAR",
        "PIJAMA", "ESPUMA", "SOPRANDO", "PERNAMBUCO", "ÁRVORES", "FAMOSO", "ESCOLA", "LIXINHO", "MENINA",
        "MACARRÃO", "BICICLETA", "MENINO", "BRAVURA", "FELIZES", "FELICIDADE", "RÁPIDO",
        "JUJUBA", "PADARIAS", "MOLEZA", "CHEGA", "EXPLICAR", "COMPANHEIROS", "ESTRELINHAS",
        "RIQUEZA", "LEMBRANDO", "ALGODÃO", "PRINCESA", "PROFESSOR", "CRIANÇA", "CORDEL",
        "ATENÇÃO", "FLORESTAS", "TRANSFORMAR"
};

    private String listaFrases[] = {"\"O vovô é meu amigo\"", "\"O povo da festa está animado\"",
        "\"O tato serve para nos proteger\"", "\"Meu pai gosta de uva\"", "\"Viva a vida com amor\"",
        "\"Está frio, vou usar minha luva\"", "\"Comprei uma lata de tinta\"", "\"O bebê está dormindo\"",
        "\"Ganhei uma bola\"", "\"Estou com a boca cheia\"", "\"Comprei um pacote de bala\"", "\"Hoje é um grande dia\"",
        "\"A pipa do menino está no céu\"", "\"Fizeram um furo na parede\"", "\"A menina do laço de fita\"",
        "\"A família se reuniu para assistr aos jogos \"", "\"A minha cor predileta é roxo\"",
        "\"O gato e o rato são amigos\"", "\"Ganhei uma linda boneca\"", "\"Minha vovó faz doces deliciosos\"",
        "\"Os sinos da igreja estão tocando.\"", "\"As crianças estão brincando na rua\"", "\"A moça comprou duas sandálias\"",
        "\"Essa escola é muito divertida\"", "\"Vou completar sete anos\"", "\"Tenho motivos para sorrir.\"", "\"Não vale a pena acumular tarefas\"",
        "\"Coloquei o meu pijama quentinha para dormir\"", "\"Vou tomar um banho com muita espuma.\"", "\"O vento está soprando forte.\"",
        "\"Vou viajar para Pernambuco nas férias\"", "\"Gosto de subir em árvores\"", "\"Meu tio é famoso\"",
        "\"Não fui para a escola hoje\"", "\"Joguei o lixinho no lixo\"", "\"Aquela menina é linda\"",
        "\"Minha comida predileta é macarrão\"", "\"Ganhei uma bicicleta do meu pai\"", "\"Sou amiga daquele menino\"",
        "\"Tenho medo da bravura do mar\"", "\"Todos pareciam felizes\"", "\"Felicidade é se sentir amado.\"",
        "\"O avião é rápido\"", "\"Ana ama comer jujuba\"", "\"Meu avô frequenta duas padarias.\"",
        "\"Estou com moleza.\"", "\"Quando chega as férias eu fico muito feliz\"", "\"Eu vou te explicar tudo.\"",
        "\"Tenho muitos companheiros na escola.\"","\"Tem muita estrelinhas no céu\"", "\"A riqueza traz alegrias mas não a felicidade\"",
        "\"Estou lembrando do meu tempo de criança\"", "\"Amo algodão doce\"", "\"Me fantasiei de princesa no meu aniversário\"",
        "\"O professor é muito inteligente\"", "\"Ser criança é muito bom\"", "\"A literatura de cordel é maravilhosa\"",
        "\"Prestem muita atenção!\"", "\"Os homens estão desmatando as florestas\"", "\"Vamos transformar o mundo?"
};

    @FXML
    private Label p3;

    @FXML
    private TextField segundaResposta;

    public ModelPag06(Label p1, Label p2, Text instrucao1, ListView<String> listaPalavras, Label p3, TextField segundaResposta) {
        this.p1 = p1;
        this.p2 = p2;
        this.unidadeAtual = 0;
        this.listaPalavras = listaPalavras;
        mCC = new ModelClasseComum(janela, this.listaPalavras);
        this.instrucao = instrucao1;
        this.p3 = p3;
        this.segundaResposta = segundaResposta;
    }

    /**
     * Define a unidade em que o software se encontra
     *
     * @param unidade
     */
    public void setUnidadeAtual(int unidade) {
        this.unidadeAtual = unidade;
        switch (getUnidadeAtual()) {
            case 1:
                definirLabels();
                break;
            case 2:
                definirLabels();
                break;
            default:
                break;
        }

    }

    /**
     * Carrega a próxima página na tela
     *
     * @param event disparado pelo método avancar do controller
     * @throws IOException
     */
    public void proximaPagina(ActionEvent event) throws IOException {
        int u = getUnidadeAtual();
        if (u == 9 || u == 25 || u == 47) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag06a.fxml"));

            //cria a próxima cena chamando a inteface dos avatares        
            Parent proximaCena = (Parent) fxmloader.load();
            Pag06aController pg06aCont = fxmloader.<Pag06aController>getController();
            pg06aCont.setUnidadeAtual(getUnidadeAtual());
            pg06aCont.setInstrucao(getUnidadeAtual());
            pg06aCont.tocarAudio();
            mCC.exibirCena(proximaCena, janela);

        } else {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag07.fxml"));

            //cria a próxima cena chamando a inteface dos avatares        
            Parent proximaCena = (Parent) fxmloader.load();
            Pag07Controller pg07Cont = fxmloader.<Pag07Controller>getController();
            pg07Cont.setUnidadeAtual(getUnidadeAtual());           
            mCC.exibirCena(proximaCena, janela);
            pg07Cont.tocarAudio();
            pg07Cont.setImagemTexto(getUnidadeAtual());

        }

    }

    /**
     * Carrega a página anterior
     *
     * @param event disparado pelo método voltar do controller
     * @throws IOException
     */
    public void paginaAnterior(ActionEvent event) throws IOException {
        int u = getUnidadeAtual();
        if ( u == 3 || u == 47 || u == 51 || u == 41 || u == 32 || u == 31 || u == 25) {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag05a.fxml"));
            //cria a próxima cena chamando a inteface dos avatares        
            Parent proximaCena = (Parent) fxmloader.load();
            Pag05aController pg05aCont = fxmloader.<Pag05aController>getController();

            mCC.exibirCena(proximaCena, janela);
            pg05aCont.setUnidadeAtual(getUnidadeAtual());
            pg05aCont.tocarAudio();
            pg05aCont.setInstrucao(getUnidadeAtual());
        } else {
            janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
            FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag05.fxml"));
            //cria a próxima cena chamando a inteface dos avatares        
            Parent proximaCena = (Parent) fxmloader.load();
            Pag05Controller pg05Cont = fxmloader.<Pag05Controller>getController();

            mCC.exibirCena(proximaCena, janela);
            pg05Cont.setUnidadeAtual(getUnidadeAtual());
            pg05Cont.tocarAudio();
        }
    }

    /**
     * Pega a unidade atual em execução
     *
     * @return string com o valor da unidade atual
     */
    public int getUnidadeAtual() {
        return this.unidadeAtual;
    }

    /**
     * Verifica se a resposta digita pelo usuário é a resposta correta
     *
     * @param resposta string digitada pela pessoa
     * @return
     */
    public boolean verificarResposta(String resposta) {
        boolean respostaCorreta = false;
        respostaCorreta = resposta.toUpperCase().equals(respostasCorretas[getUnidadeAtual()]);
        return respostaCorreta;
    }

    /**
     * Executa o áudio da página
     */
    public void tocarAudio() {
        switch (getUnidadeAtual()) {
            case 1:
                caminhoAudio = "audios/u01/l1p6.mp3";
                break;
            case 2:
                caminhoAudio = "audios/u02/l2p6.mp3";
                break;
            case 3:
                caminhoAudio = "audios/u03/l3p6.mp3";
                break;
            default:
                caminhoAudio = "audios/u" + unidadeAtual + "/l" + unidadeAtual + "p6.mp3";
                break;
        }
        mCC.play(caminhoAudio);
    }

    /**
     * Define as labels que serão utilizadas na página baseado na unidade atual
     */
    public void definirLabels() {
        segundaResposta.setVisible(false);
        p3.setVisible(false);
        switch (getUnidadeAtual()) {
            case 1:
                p1.setText("O");
                p2.setText("É MEU AMIGO");
                break;
            case 2:
                p1.setText("O");
                p2.setText("DA FESTA ESTÁ ANIMADO");
                break;
            case 3:
                p1.setText("O");
                p2.setText("SERVE PARA NOS PROTEGER");
                break;
            case 4:
                p1.setText("MEU PAI GOSTA DE");
                break;
            case 5:
                p2.setText("A VIDA COM AMOR.");
                break;
            case 6:
                p1.setText("ESTÁ FRIO, VOU USAR MINHA");
                p2.setText(".");
                break;
            case 7:
                p1.setText("Comprei uma");
                p2.setText("de tinta.");
                break;
            case 8:
                p1.setText("O");
                p2.setText("está dormindo.");
                break;
            case 9:
                p1.setText("Ganhei uma");
                p2.setVisible(false);
                break;
            case 10:
                p1.setText("Estou com a");
                p2.setText("cheia.");
                break;
            case 11:
                p1.setText("Comprei um pacote de");
                p2.setVisible(false);
                break;
            case 12:
                p2.setText("é um grande dia.");
                break;
            case 13:
                p1.setText("A");
                p2.setText("do menino está no céu.");
                break;
            case 14:
                p1.setText("Fizeram um");
                p2.setText("na parede.");
                break;
            case 15:
                p1.setText("A menina do laço de");
                break;
            case 16:
                p1.setText("A família se reuniu para assistir aos");
                break;
            case 17:
                p1.setText("A minha cor predileta é");
                break;
            case 18:
                segundaResposta.setVisible(true);
                p3.setVisible(true);
                p1.setText("O");
                p2.setText("e o");
                p3.setText("são amigos.");
                break;
            case 19:
                p1.setText("Ganhei uma linda");
                p2.setVisible(false);
                break;
            case 20:
                p1.setText("Minha vovó faz");
                p2.setText("deliciosos.");
                break;
            case 21:
                p1.setText("Os");
                p2.setText("da igreja estão tocando.");
                break;
            case 22:
                p1.setText("As crianças estão brincando na");
                p2.setVisible(false);
                break;
            case 23:
                p1.setText("A moça comprou");
                p2.setText("sandálias.");
                break;
            case 24:
                p2.setText("escola é muita divertida.");
                break;
            case 25:
                p1.setText("Vou completar");
                p2.setText("anos.");
                break;
            case 26:
                p1.setText("Tenho");
                p2.setText("para sorrir.");
                break;
            case 27:
                p1.setText("Não vale a pena");
                p2.setText("tarefas.");
                break;
            case 28:
                p1.setText("Coloquei o meu");
                p2.setText("quentinho para dormir.");
                break;
            case 29:
                p1.setText("Vou tomar um banho com muita");
                break;
            case 30:
                p1.setText("O vento está");
                p2.setText("forte.");
                break;
            case 31:
                p1.setText("Vou viajar para");
                p2.setText("nas férias.");
                break;
            case 32:
                p1.setText("Gosto de subir em");
                p2.setText(".");
                break;
            case 33:
                p1.setText("Meu tio é");
                p2.setText(".");
                break;
            case 34:
                p1.setText("Não fui para a");
                p2.setText("hoje.");
                break;
            case 35:
                p1.setText("Joguei o");
                p2.setText("no lixo.");
                break;
            case 36:
                p1.setText("Aquela");
                p2.setText("é linda.");
                break;
            case 37:
                p1.setText("Minha comida predileta é");
                p2.setText(".");
                break;
            case 38:
                p1.setText("Ganhei uma");
                p2.setText("do meu pai.");
                break;
            case 39:
                p1.setText("Sou amiga daquele");
                p2.setText(".");
                break;
            case 40:
                p1.setText("Tenho medo da");
                p2.setText("do mar.");
                break;
            case 41:
                p1.setText("Todos pareciam");
                p2.setText(".");
                break;
            case 42:
                p2.setText("é se sentir amado.");
                break;
            case 43:
                p1.setText("O avião é");
                p2.setText(".");
                break;
            case 44:
                p1.setText("Ana ama comer");
                p2.setText(".");
                break;
            case 45:
                p1.setText("Meu avô frequenta duas");
                p2.setText(".");
                break;
            case 46:
                p1.setText("Estou com");
                p2.setText(".");
                break;
            case 47:
                p1.setText("Quando");
                p2.setText("as férias fico muito feliz.");
                break;
            case 48:
                p1.setText("Eu vou te");
                p2.setText("tudo.");
                break;
            case 49:
                p1.setText("Tenho muitos");
                p2.setText("na escola.");
                break;
            case 50:
                p1.setText("Tem muitas");
                p2.setText("no céu.");
                break;
            case 51:
                p1.setText("A");
                p2.setText("traz alegrias mas não a felicidade.");
                break;
            case 52:
                p1.setText("Estou");
                p2.setText("do meu tempo de criança.");
                break;
            case 53:
                p1.setText("Amo");
                p2.setText("doce.");
                break;
            case 54:
                p1.setText("Me fantasiei de");
                p2.setText("no meu aniversário.");
                break;
            case 55:
                p1.setText("O");
                p2.setText("é muito inteligente.");
                break;
            case 56:
                p1.setText("Ser");
                p2.setText("é muito bom.");
                break;
            case 57:
                p1.setText("A literatura de");
                p2.setText("é maravilhosa.");
                break;
            case 58:
                p1.setText("Prestem muita");
                p2.setText("!");
                break;
            case 59:
                p1.setText("Os homens estão desmatando as");
                p2.setText(".");
                break;
            case 60:
                p1.setText("Vamos");
                p2.setText("o mundo?");
                break;
            default:
                break;
        }
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

    public void executarAudioFrase() {
        switch (getUnidadeAtual()) {
            case 1:
                caminhoAudio = "audios/u01/frase.mp3";
                break;
            case 2:
                caminhoAudio = "audios/u02/frase.mp3";
                break;
            case 3:
                caminhoAudio = "audios/u03/frase.mp3";
                break;
            default:
                caminhoAudio = "audios/u" + unidadeAtual + "/frase.mp3";
                break;
        }
        mCC.play(caminhoAudio);
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

    /**
     * Abre o função "ABC" do programa
     *
     * @param event disparado quando o botão "ABC" é clicado
     * @param pagina pagina de onde a função "ABC" foi chamada
     * @throws IOException
     */
    public void abrirABC(ActionEvent event, int pagina) throws IOException {
        mCC.setUnidadeAtual(getUnidadeAtual());
        mCC.abrirABC(event, pagina, "");
    }

    //faz exibir a instrução da atividade atual na tela
    public void definirInstrucao(int unidadeAtual) {
        String instrucaoTexto = "";
        instrucao.setText("Digite a palavra que você aprendeu para formar a frase:\n " + listaFrases[unidadeAtual - 1]);
    }

    private void tocarAudioParabens() throws InterruptedException {
        Random indiceParabens = new Random();
        int numeroAudio = indiceParabens.nextInt(3);
        caminhoAudio = "audios/acerto/" + numeroAudio + ".mp3";
        mCC.play(caminhoAudio);

    }

    public void audioAcerto() {
        //evento que represanta a ação do acerto
        primeiroAudio = (ActionEvent event) -> {
            try {
                tocarAudioParabens();
            } catch (InterruptedException ex) {
                Logger.getLogger(ModelPag04.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        //evento que representa o audio a ser executado depois o
        segundoAudio = (ActionEvent event) -> {
            executarAudioFrase();
        };
        new Timeline(
                new KeyFrame(Duration.seconds(0), primeiroAudio),
                new KeyFrame(Duration.seconds(3), segundoAudio)).play();
    }

    public void audioErro() {
        Random indiceErro = new Random();
        int numeroAudio = indiceErro.nextInt(3);
        caminhoAudio = "audios/erro/" + numeroAudio + ".mp3";
        mCC.play(caminhoAudio);
    }

    public void atualizarListView() {
        mCC.atualizarListView(listaPalavras, getUnidadeAtual());
    }

    public boolean verificarResposta(String resposta, String segundaResposta) {
        boolean respostaCorreta = false;
        respostaCorreta = resposta.toUpperCase().equals("GATO") && (segundaResposta.toUpperCase().equals("RATO"));
        return respostaCorreta;
    }
}
