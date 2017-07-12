package principal;

import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author shadows
 */
public class Principal extends Application {

    @FXML
    private Stage janela;

    @Override
    public void start(Stage janela) throws Exception {
        this.janela = janela;
        Parent cenaInicial = FXMLLoader.load(getClass().getResource("/interfaces/menuInicial.fxml"));
        Scene cena = new Scene(cenaInicial, 1200, 700);
        janela.setTitle("EscreLer");
        janela.setScene(cena);
        janela.show();
        janela.setResizable(false);
        //função para encerrar todos os processos quando o usuário clicar no "X"
        janela.setOnCloseRequest((WindowEvent event) -> {
            Alert confirmacaoSaida = new Alert(Alert.AlertType.CONFIRMATION,
                    "Você tem certeza que quer sair do programa?");
            Button exitButton = (Button) confirmacaoSaida.getDialogPane().lookupButton(ButtonType.OK);
            exitButton.setText("Sair");
            confirmacaoSaida.setHeaderText("Confirmação de Saída");
            confirmacaoSaida.initModality(Modality.APPLICATION_MODAL);
            confirmacaoSaida.initOwner(janela);
            
            Optional<ButtonType> resposta = confirmacaoSaida.showAndWait();
            if (!ButtonType.OK.equals(resposta.get())) {
                event.consume();
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
