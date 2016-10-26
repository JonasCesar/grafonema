/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author jonas
 */
public class JogoPrincipal {

    @FXML
    private Button btn_1;
    @FXML
    private Button btn_2;
    @FXML
    private Button btn_3;
    @FXML
    private Button btn_5;
    @FXML
    private Button btn_4;

    public JogoPrincipal(Button b1, Button b2, Button b3, Button b4, Button b5) {
        this.btn_1 = b1;
        this.btn_2 = b2;
        this.btn_3 = b3;
        this.btn_4 = b4;
        this.btn_5 = b5;

    }

    public void gerarNovasOpcoes() {
        btn_1.setText("OI");
    }

}
