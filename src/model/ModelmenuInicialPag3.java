/**
 * Model do menu inicial
 */

package model;

import java.io.IOException;
import controller.Pag01Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author shadows
 */
public class ModelmenuInicialPag3 {
    
    private Stage janela;
    private String unidade;
    private ModelPag01 modelPag01;    
    public ModelmenuInicialPag3() {
        
        
    }
    /**
     * Inicia a primeira pagina do software
     * @param event disparado quando uma das unidades é escolhida
     * @param unidade unidade escolhida
     * @throws IOException 
     */
    public void iniciar(ActionEvent event, int unidade) throws IOException{
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("/interfaces/pag01.fxml"));        
        //cria a próxima cena   
        Parent proximaCena = (Parent) fxmloader.load();
        Pag01Controller pg01Cont = fxmloader.<Pag01Controller>getController();
        pg01Cont.setUnidadeAtual(unidade);        
        Scene cena = new Scene(proximaCena, 1200, 700);//tamanho
        janela.setTitle("Projeto 2");//título da cena
        janela.setScene(cena);
        janela.show();//exibe a interface
        pg01Cont.setImagemTexto();
        pg01Cont.tocarAudio();
        
        
    }

    public void voltarMenuInicial(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        Parent cenaInicial = FXMLLoader.load(getClass().getResource("/interfaces/menuInicialPag2.fxml"));
        Scene cena = new Scene(cenaInicial, 1200, 700);
        janela.setTitle("Projeto 2");
        janela.setScene(cena);
        janela.show();    
    }

    public void proximoMenu(ActionEvent event) throws IOException {
        janela = (Stage) ((Button) event.getSource()).getScene().getWindow(); //pega a cena em que o botão que gerou o evento estava
        Parent cenaInicial = FXMLLoader.load(getClass().getResource("/interfaces/menuInicialPag3.fxml"));
        Scene cena = new Scene(cenaInicial, 1200, 700);
        janela.setTitle("Projeto 2");
        janela.setScene(cena);
        janela.show();
    }
}
