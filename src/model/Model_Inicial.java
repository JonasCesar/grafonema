package model;

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
import javafx.stage.WindowEvent;

/**
 * @author iran
 */
public class Model_Inicial {

    private Stage janela;

    public Model_Inicial() {
    }

    /**
     * Exibe a tela dos avatares
     *
     * @param event o usuário clica no botão iniciar
     * @throws IOException
     */
    public void iniciar(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        //cria a próxima cena chamando a inteface dos avatares
        Parent proximaCena = FXMLLoader.load(getClass().getResource("/interfaces/Gui_Introducao.fxml"));
        Scene cena = new Scene(proximaCena, 1200, 700);//tamanho
        janela.setTitle("Grafonema");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface

    }

    public void iniciar(ImageView imgView) throws IOException {
        janela = (Stage) ((ImageView) imgView).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        //cria a próxima cena chamando a inteface dos avatares
        Parent proximaCena = FXMLLoader.load(getClass().getResource("/interfaces/Gui_Introducao.fxml"));
        Scene cena = new Scene(proximaCena, 1200, 700);//tamanho
        janela.setTitle("Grafonema");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface
    }

    public void sairDoJogo(ImageView imgSair) {
        System.out.println("Entrou aqui");
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
