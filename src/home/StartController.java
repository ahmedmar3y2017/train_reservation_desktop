/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class StartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView image;
    @FXML
    private AnchorPane ss;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        javafx.scene.image.Image im1 = new javafx.scene.image.Image(getClass().getResourceAsStream("pp.jpg"));
//        image.setImage(im1);

        ss.getStyleClass().add("ss");
    }

}
