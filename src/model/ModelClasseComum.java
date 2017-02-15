/**
 * Model do controller "ControllerClasseComum"
 */
package model;

import controller.ABCController;
import controller.ManualController;
import controller.MenuInicialController;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private String unidadeAtual;
    private ActionEvent eventoTemporario;

    public ModelClasseComum(Stage janela) {
        this.janela = janela;
        eventoTemporario = null;
        unidadeAtual = "u00";
    }
    /**
     * Executa um arquivo de audio
     * @param caminhoAudio caminho do arquivo que deve ser executado
     */
    public void play(String caminhoAudio) {
        //cria um objeto arquivo que recebe o nome do arquivo como parâmetro
        arquivo = new File(caminhoAudio);
        //pega todo do caminho referente ao objeto File criado
        caminhoAudio = arquivo.getAbsolutePath();
        //troca todas as barras invertidas duplas ('\\') por '/'
        caminhoAudio = caminhoAudio.replace("\\", "/");
        //cria um objeto Media que recebe o objeto 'arquivo' como parâmetro
        media = new Media(new File(caminhoAudio).toURI().toString());
        //cria um objeto mediaPlayer que permite qua uma media possa ser reproduzida
        mediaPlayer = new MediaPlayer(media);
        //toca o audio automaticamente
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
     * @param proximaCena a cena a ser carregada
     * @param janela janela onde será carregada a cena
     */
    public void exibirCena(Parent proximaCena, Stage janela) {
        this.janela = janela;
        Scene cena = new Scene(proximaCena, 950, 700);
        janela.setTitle("Projeto 2");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface  
        System.out.println("exibiu a cena certinho");
    }
    /**
     * Executa o audio da palavra selecionada na listView
     * @param palavraSelecionada 
     */
    public void tocarAudioPalavraSelecionada(String palavraSelecionada) {
        pararAudio();
        switch(palavraSelecionada){
            case "VOVÔ":
                caminhoAudio = "src/AudiosPalavrasEstudadas/vovo.mp3";
                break;
            default:
                break;
        }
        play(caminhoAudio);
    }
    /**
     * Carrega o menu inicial do jogo
     * @param event disparado pelo método "menuInicial" presente em todos os controllers
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
     * @param event disparado pelo método abrirABC presente em todos os controllers
     * @param pagina pagina de onde o método foi chamado
     * @throws IOException 
     */
    public void abrirABC(ActionEvent event, int pagina) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/ABC.fxml"));
        Parent proximaCena = (Parent) fxmloader.load();
        ABCController ABCCont = fxmloader.<ABCController>getController();
        exibirCena(proximaCena, janela);
        
        System.out.println(getUnidadeAtual());
        ABCCont.setUnidadeAtual(getUnidadeAtual());
        ABCCont.setPaginaTemporaria(pagina);

    }
    /**
     * Abre o manual do software
     * @param event disparado pelo método abrirManual presente em todas as classes
     * @param pagina pagina de onde o método foi chamado
     * @throws IOException 
     */
    void abrirManual(ActionEvent event, int pagina) throws IOException {
        
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/manual.fxml"));
        Parent proximaCena = (Parent) fxmloader.load();
        ManualController manualController = fxmloader.<ManualController>getController();        
        exibirCena(proximaCena, janela);
        manualController.setUnidadeAtual(getUnidadeAtual());
        manualController.setPaginaTemporaria(pagina);
    }
    /**
     * Define a unidade atual que será necessária em algumas funções
     * @param unidadeAtual unidade em que o usuário se encontra
     */
    public void setUnidadeAtual(String unidadeAtual){
        
        this.unidadeAtual = unidadeAtual;
    }
    /**
     * Retorna a unidade atual
     * @return 
     */
    public String getUnidadeAtual(){
        return this.unidadeAtual;
    }
}
