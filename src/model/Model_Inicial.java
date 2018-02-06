package model;

import controller.Gui_SequenciaCenasController;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author iran
 */
public class Model_Inicial {

    private Stage janela;

    public Model_Inicial() {
    }

    /**
     * Inicia o jogo
     *
     * @param event o usuário clica no botão iniciar
     * @throws IOException
     */
    public void iniciar(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow();       
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Gui_SequenciaCenas.fxml"));
        Parent proximaCena = (Parent) fxmloader.load();        
        Gui_SequenciaCenasController sequenciaCenas = fxmloader.<Gui_SequenciaCenasController>getController();
        //cria uma cena 
        Scene cena = new Scene(proximaCena, 1200, 700);
        janela.setTitle("Legere");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a cena
        //sequenciaCenas.executarCenaInicioJogo();
        sequenciaCenas.definirImagemFundo();
        sequenciaCenas.executarCenaInicialFase();
    }
    
    //Abre a janela "Sobre o jogo"
    public void sobre(ActionEvent event) throws IOException {
        
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/interfaces/Gui_Sobre.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Sobre o Jogo");
            } catch (IOException e) {
            e.printStackTrace();
        }
        

    }

    /**
     * Método utilizado para iniciar o jogo na interface de Game Over
     * @param imgView imageView de onde o o clic veio
     * @throws IOException 
     */
    public void iniciar(ImageView imgView) throws IOException {
        janela = (Stage) ((ImageView) imgView).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/Gui_SequenciaCenas.fxml"));
        Parent proximaCena = (Parent) fxmloader.load();        
        Gui_SequenciaCenasController sequenciaCenas = fxmloader.<Gui_SequenciaCenasController>getController();
        //cria uma cena 
        Scene cena = new Scene(proximaCena, 1200, 700);
        janela.setTitle("Grafonema");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a cena
        //sequenciaCenas.executarCenaInicioJogo();
        sequenciaCenas.executarCenaInicialFase();
    }
    /**
     * Fecha o jogo
     * @param imgSair 
     */
    public void sairDoJogo(ImageView imgSair) {
        System.out.println("Entrou sair do jogo");
        janela = (Stage) ((ImageView) imgSair).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        //função para encerrar todos os processos quando o usuário clicar no "X"

        Alert confirmacaoSaida = new Alert(Alert.AlertType.CONFIRMATION,
                "Você tem certeza que quer sair do jogo?");
        Button botaoSair = (Button) confirmacaoSaida.getDialogPane().lookupButton(ButtonType.OK);
        botaoSair.setText("Sair");
        confirmacaoSaida.setHeaderText("Confirmação de Saída");
        confirmacaoSaida.initModality(Modality.APPLICATION_MODAL);
        confirmacaoSaida.initOwner(janela);

        Optional<ButtonType> resposta = confirmacaoSaida.showAndWait();
        if (ButtonType.OK.equals(resposta.get())) {
            janela.close();
            System.exit(0);
        }
    }
}
