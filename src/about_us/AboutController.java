/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package about_us;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;




public class AboutController implements Initializable {

    @FXML
    private AnchorPane pane;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pane.getStyleClass().add("ss");
        System.out.println("about");

    }

}
