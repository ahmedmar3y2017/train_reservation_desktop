/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbox;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import login.login;
import train_reservation.FXMLDocumentController;
import train_reservation.start;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class VboxController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vb;

    @FXML
    private ImageView image;

    @FXML
    private Text name;

    @FXML
    private JFXButton Login;

    @FXML
    private JFXButton register;

    @FXML
    private JFXButton logout;

    @FXML
    private JFXButton help;

    @FXML
    private JFXButton exite;

    @FXML
    void action(ActionEvent event) throws IOException {

        if (event.getSource() == Login) {

            login l = new login("login");

        }
        if (event.getSource() == register) {
            login l = new login("sign");

        }
        if (event.getSource() == help) {

        }
        if (event.getSource() == logout) {

            //            confirm_dialog c = new confirm_dialog("Exite program", "Are you sure to exite ?", "Ok / cancel");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("Are you sure to Logout ?");
            alert.setContentText("Ok / cancel");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                FXMLDocumentController.name = "";
                start.s.close();
                new start();
            } else {
                // ... user chose CANCEL or closed the dialog
            }

        }
        if (event.getSource() == exite) {
//            confirm_dialog c = new confirm_dialog("Exite program", "Are you sure to exite ?", "Ok / cancel");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exite program");
            alert.setHeaderText("Are you sure to exite ?");
            alert.setContentText("Ok / cancel");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.exit(0);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
//         System.exit(0);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        javafx.scene.image.Image im5 = new javafx.scene.image.Image(getClass().getResourceAsStream("user.png"));
        image.setImage(im5);

    }

    public void user(String nname) {

        name.setText(nname);
        Login.setDisable(true);
        register.setDisable(true);

    }

    public void admin(String nname) {
        name.setText(nname);
        Login.setDisable(true);
        register.setDisable(true);

    }

    public void cancel_logout() {

        logout.setDisable(true);

    }
}
