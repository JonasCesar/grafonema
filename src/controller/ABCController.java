/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.ModelABC;

/**
 * FXML Controller class
 *
 * @author jonas
 */
public class ABCController implements Initializable {

    private ModelABC modelABC;

    //public ImageView img1;


    private int paginaTemporaria;
    private ImageView img1;
    @FXML
    private Button a;
    @FXML
    private Button b;
    @FXML
    private Button c;
    @FXML
    private Button d;
    @FXML
    private Button j;
    @FXML
    private Button i;
    @FXML
    private Button h;
    @FXML
    private Button g;
    @FXML
    private Button f;
    @FXML
    private Button e;
    @FXML
    private Button n;
    @FXML
    private Button m;
    @FXML
    private Button l;
    @FXML
    private Button k;
    @FXML
    private Button x;
    @FXML
    private Button w;
    @FXML
    private Button v;
    @FXML
    private Button u;
    @FXML
    private Button t;
    @FXML
    private Button s;
    @FXML
    private Button r;
    @FXML
    private Button q;
    @FXML
    private Button p;
    @FXML
    private Button o;
    @FXML
    private Button z;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private String subPagina;
    @FXML
    private AnchorPane janelaPrograma;
    private Label cursivaM;
    @FXML
    private Label cursivaMinuscula;
    @FXML
    private Label cursivaMaiuscula;
    @FXML
    private Label formaMinuscula;
    @FXML
    private Label formaMaiuscula;

    public ABCController() {
        //modelABC = new ModelABC(imgLetra, img);
        
        paginaTemporaria = 0;
        subPagina = "";
    }

    public String botaoEscolhido = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modelABC = new ModelABC(img1, img2, img3, img4, janelaPrograma, formaMaiuscula, formaMinuscula, cursivaMaiuscula, cursivaMinuscula);
        Tooltip verLetra = new Tooltip("Clique em um botão para ver a letra");
        Tooltip.install(img1, verLetra);
        Tooltip.install(img2, verLetra);
        Tooltip.install(img3, verLetra);
        Tooltip.install(img4, verLetra);
    }

    /**
     * Reconhece qual botão (A-Z) foi clicado
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleButton(ActionEvent event) throws IOException {
        String letraEscolhida = ((Button) event.getSource()).getId();
        botaoEscolhido = letraEscolhida;
        desenharLetra(letraEscolhida);
        //modelABC.pararAudio();
    }

    public void setUnidadeAtual(int unidadeAtual) {
        modelABC.setUnidadeAtual(unidadeAtual);
    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {        
        modelABC.voltar(event, paginaTemporaria,subPagina);
    }

    @FXML
    private void menuInicial(ActionEvent event) throws IOException {
        modelABC.menuInicial(event);
    }

    /**
     * Reconhece quando o mouse é passado sobre a imagem da letra
     *
     * @param event
     * @throws IOException
     */
    private void passouRato(MouseEvent event) throws IOException {
        modelABC.passouRato(event);              
    }
    
    private void dessombrearImagem(MouseEvent event) throws IOException {        
        
       modelABC.dessombrearImagem(event);
    }

    /**
     * Exibe a imagem referente ao botão que foi clicado
     *
     * @param letra letra representado
     */
    public void desenharLetra(String letra) {
        modelABC.desenharLetra(letra);   
    }

    public void setPaginaTemporaria(int pagina, String subPagina) {
        this.subPagina = subPagina;
        paginaTemporaria = pagina;
    }

    private void tocarLetra(MouseEvent event) {        
        modelABC.tocarSomLetra(botaoEscolhido);
    }

    @FXML
    private void retirarMouseESombra(MouseEvent event) {
        modelABC.retirarMouseESombra(event);
    }

    @FXML
    private void adicionarMouseEsombra(MouseEvent event) {
        modelABC.adicionarMouseESombra(event);
    }
}
