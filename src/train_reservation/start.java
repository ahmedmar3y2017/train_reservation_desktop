/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train_reservation;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static train_reservation.Train_reservation.s;

/**
 *
 * @author ahmed
 */
public class start {

    public static Stage s = new Stage();

    public start() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("train_reservation/style.css");

        s.setScene(scene);
        s.setResizable(false);
        s.show();
    }

    public start(String type, String name, String phone) throws IOException {
        FXMLDocumentController.name = name;
        FXMLDocumentController.phone = phone;
//        eee = email;
//        Stage s = new Stage();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml.fxml"));
        FXMLLoader load = new FXMLLoader();
        Parent root = load.load(getClass().getResource("FXMLDocument.fxml").openStream());
        FXMLDocumentController user = (FXMLDocumentController) load.getController();

        if (type.equals("user")) {
            user.user_privilage();

//            System.out.println("Done1++++++++++++++++++++++++++++++++++++++");
        } else if (type.equals("admin")) {
            System.out.println("Admin");
            user.admin_privilage();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("train_reservation/style.css");

        s.setTitle("Welcome");
        s.setResizable(false);
        s.setScene(scene);
        s.show();

    }
}
