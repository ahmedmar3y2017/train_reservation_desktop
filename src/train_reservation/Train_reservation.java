/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train_reservation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import socket.multipleClients;

/**
 *
 * @author ahmed
 */
public class Train_reservation extends Application {

    public static Stage s = new Stage();

    @Override
    public void start(Stage stage) throws Exception {

        s = stage;

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("train_reservation/style.css");

        s.setScene(scene);
        s.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    Connection conn = database.database.connect();
//                    delete from ticket where cast(`ticket`.`date` as date) < curdate()
                    boolean b = database.database.delete_trip();
                    System.out.println(b + "Done");

                } catch (SQLException ex) {
                    Logger.getLogger(Train_reservation.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        Runtime r = Runtime.getRuntime();
        r.addShutdownHook(new Thread(new Runnable() {

            @Override
            public void run() {

                t1.start();
                try {
                    t1.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Train_reservation.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }));

    }

}
