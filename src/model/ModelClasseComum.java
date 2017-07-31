/**
 * Model do controller "ControllerClasseComum"
 */
package model;

import controller.ABCController;
import controller.ManualController;
import controller.MenuInicialController;
import controller.atividadesController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelClasseComum {

    private File arquivo;
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView = new MediaView();
    private Stage janela;
    private String caminhoAudio;
    private int unidadeAtual;
    private ActionEvent eventoTemporario;
    public static ObservableList<String> items = FXCollections.observableArrayList();

    public String listaPalavrasEstudadas[] = {"VOVÔ", "POVO", "TATO", "UVA", "VIVA", "LUVA", "LATA",
        "BEBÊ", "BOLA", "DOCE", "BOCA", "BALA", "HOJE", "PIPA", "FURO", "FITA", "JOGOS", "ROXO", "GATO", "RATO", "BONECA",
        "DEDOS", "SINOS", "RUA", "DUAS", "ESSA", "SETE", "SUCESSO", "MOTIVO", "ACUMULAR", "PIJAMA", "ESPUMA",
        "SOPRANDO", "PERNAMBUCO", "ÁRVORE", "FAMOSO", "ESCOLA", "LIXINHO", "MENINA", "MACARRÃO", "BICICLETA",
        "MENINO", "BRAVURA", "FELIZES", "FELICIDADE", "RÁPIDO", "JUJUBA", "PADARIAS", "MOLEZA", "CHEGA", "TOQUES",
        "EXPLICAR", "COMPANHEIROS", "ESTRELINHAS", "ÁGUA", "QUE", "RIQUEZA", "TAMANHA", "LEMBRANDO",
        "ALGODÃO", "PRINCESA", "PROFESSOR", "CRIANÇA", "CORDEL", "ATENÇÃO", "FLORESTA", "TRANSFORMAR"
    };

    @FXML
    private ListView<String> listaPalavras;

    public ModelClasseComum(Stage janela, ListView<String> listaPalavras) {
        this.janela = janela;
        eventoTemporario = null;
        unidadeAtual = 0;
        this.listaPalavras = listaPalavras;
    }

    /**
     * Executa um arquivo de audio
     *
     * @param caminhoAudio caminho do arquivo que deve ser executado
     */
    public void play(String caminhoAudio) {
        URL file = getClass().getResource(caminhoAudio);
        media = new Media(file.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    /**
     * Parar o audio em execução atualmente
     */
    public void pararAudio() {
        try {
            mediaPlayer.stop();
        } catch (Exception e) {

        }
    }

    /**
     * Carrega uma cena
     *
     * @param proximaCena a cena a ser carregada
     * @param janela janela onde será carregada a cena
     */
    public void exibirCena(Parent proximaCena, Stage janela) {
        this.janela = janela;
        Scene cena = new Scene(proximaCena, 1200, 700);
        janela.setTitle("Mundo da Leitura e da Escrita");//título da cena

        janela.setScene(cena);
        janela.show();//exibe a interface  
    }

    /**
     * Executa o audio da palavra selecionada na listView
     *
     * @param palavraSelecionada
     */
    public void tocarAudioPalavraSelecionada(String palavraSelecionada) {
        pararAudio();
        caminhoAudio = "audios/AudiosPalavrasEstudadas/" + palavraSelecionada + ".mp3";
        play(caminhoAudio);
    }

    /**
     * Carrega o menu inicial do jogo
     *
     * @param event disparado pelo método "menuInicial" presente em todos os
     * controllers
     * @throws IOException
     */
    public void menuInicial(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/menuInicial.fxml"));

        Parent proximaCena = (Parent) fxmloader.load();
        MenuInicialController miController = fxmloader.<MenuInicialController>getController();

        exibirCena(proximaCena, janela);
    }

    /**
     * Carrega a janela do ABC
     *
     * @param event disparado pelo método abrirABC presente em todos os
     * controllers
     * @param pagina pagina de onde o método foi chamado
     * @param subPagina
     * @throws IOException
     */
    public void abrirABC(ActionEvent event, int pagina, String subPagina) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/ABC.fxml"));
        Parent proximaCena = (Parent) fxmloader.load();
        ABCController ABCCont = fxmloader.<ABCController>getController();
        exibirCena(proximaCena, janela);
        ABCCont.setUnidadeAtual(getUnidadeAtual());
        ABCCont.setPaginaTemporaria(pagina, subPagina);

    }

    /**
     * Abre o manual do software
     *
     * @param event disparado pelo método abrirManual presente em todas as
     * classes
     * @param pagina pagina de onde o método foi chamado
     * @throws IOException
     */
    void abrirManual(ActionEvent event, int pagina, String subPagina) throws IOException {

        janela = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/manual.fxml"));
        Parent proximaCena = (Parent) fxmloader.load();
        ManualController manualController = fxmloader.<ManualController>getController();
        exibirCena(proximaCena, janela);
        manualController.setUnidadeAtual(getUnidadeAtual());
        manualController.setPaginaTemporaria(pagina, subPagina);
    }

    /**
     * Define a unidade atual que será necessária em algumas funções
     *
     * @param unidadeAtual unidade em que o usuário se encontra
     */
    public void setUnidadeAtual(int unidadeAtual) {
        this.unidadeAtual = unidadeAtual;
    }

    /**
     * Retorna a unidade atual
     *
     * @return
     */
    public int getUnidadeAtual() {
        return this.unidadeAtual;
    }

    void atualizarListaPalavras(ListView<String> listaPalavras) {
        //listaPalavras.setItems(items);
    }

    public void atualizarListView(ListView<String> listaPalavras, int unidade) {
        this.listaPalavras = listaPalavras;
        int unidadeTemporaria = unidade;
        try {
            if (unidadeTemporaria > 1) {
                if (unidadeTemporaria >= 26 && unidadeTemporaria < 48) {
                    unidadeTemporaria = unidadeTemporaria + 1;
                } else if (unidadeTemporaria >= 48 && unidadeTemporaria < 52) {
                    unidadeTemporaria = unidadeTemporaria + 4;
                } else if (unidadeTemporaria >= 52 && unidadeTemporaria < 60) {
                    unidadeTemporaria = unidadeTemporaria + 7;
                } else if (unidadeTemporaria == 60) {
                    unidadeTemporaria = 61;
                }
                else{
                    unidadeTemporaria = unidade;
                }
                if (!items.isEmpty()) {//se já houver elementos na lista de palavras
                    System.out.println("Apagou todos os itens");
                    items.remove(0, items.size());//remove todos os elementos
                }
                for (int i = 0; i < unidadeTemporaria - 1; i++) {
                    items.add(listaPalavrasEstudadas[i]);
                }
            }
            listaPalavras.setItems(items);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
        }
    }

    public void sugestaoAtividades(ActionEvent event, int pagina, String subPagina) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/atividades.fxml"));
        Parent proximaCena = (Parent) fxmloader.load();
        atividadesController atividadesController = fxmloader.<atividadesController>getController();
        exibirCena(proximaCena, janela);
        atividadesController.setUnidadeAtual(getUnidadeAtual());
        atividadesController.setPaginaTemporaria(pagina, subPagina);
    }
}
