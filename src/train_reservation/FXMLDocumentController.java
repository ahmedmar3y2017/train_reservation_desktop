/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train_reservation;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import vbox.VboxController;

/**
 *
 * @author ahmed
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private StackPane start;

    @FXML
    private AnchorPane home;

    @FXML
    private ImageView image1;

    @FXML
    private Label label1;

    @FXML
    private AnchorPane search;

    @FXML
    private ImageView image2;

    @FXML
    private Label label2;

    @FXML
    private AnchorPane profile;

    @FXML
    private ImageView image4;

    @FXML
    private Label label3;

    @FXML
    private AnchorPane about_us;

    @FXML
    private ImageView image6;

    @FXML
    private Label label5;

    @FXML
    private AnchorPane book;

    @FXML
    private ImageView image3;

    @FXML
    private Label exam_label;

    @FXML
    private AnchorPane admin;

    @FXML
    private ImageView image5;

    @FXML
    private Label label4;
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    public static String name = "";
    public static String phone = "";

    @FXML
    void mouse_Event(MouseEvent event) throws IOException {

        if (event.getSource() == home) {

            Node p = FXMLLoader.load(getClass().getResource("/home/start.fxml"));
            start.getChildren().clear();
            start.getChildren().add(p);

        }
        if (event.getSource() == search) {

            Node p = FXMLLoader.load(getClass().getResource("/search_train/FXMLDocument.fxml"));
            start.getChildren().clear();
            start.getChildren().add(p);

        }
        if (event.getSource() == book) {

            Node p = FXMLLoader.load(getClass().getResource("/book/FXML.fxml"));
            start.getChildren().clear();
            start.getChildren().add(p);

        }
        if (event.getSource() == profile) {

            Node p = FXMLLoader.load(getClass().getResource("/profile/FXMLDocument.fxml"));
            start.getChildren().clear();
            start.getChildren().add(p);

        }
        if (event.getSource() == admin) {

            Node p = FXMLLoader.load(getClass().getResource("/admin/admin.fxml"));
            start.getChildren().clear();
            start.getChildren().add(p);

        }
        if (event.getSource() == about_us) {

            Node p = FXMLLoader.load(getClass().getResource("/about_us/about.fxml"));
            start.getChildren().clear();
            start.getChildren().add(p);

        }

//           if (event.getSource() == ) {
//
//            Node p = FXMLLoader.load(getClass().getResource("/search/search.fxml"));
//            start.getChildren().clear();
//            start.getChildren().add(p);
//
//        }
//      
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        javafx.scene.image.Image im1 = new javafx.scene.image.Image(getClass().getResourceAsStream("hhome.png"));
        image1.setImage(im1);
        javafx.scene.image.Image im2 = new javafx.scene.image.Image(getClass().getResourceAsStream("search.png"));
        image2.setImage(im2);
        javafx.scene.image.Image im3 = new javafx.scene.image.Image(getClass().getResourceAsStream("book.png"));
        image3.setImage(im3);
        javafx.scene.image.Image im4 = new javafx.scene.image.Image(getClass().getResourceAsStream("profile.png"));
        image4.setImage(im4);
        javafx.scene.image.Image im5 = new javafx.scene.image.Image(getClass().getResourceAsStream("admin.png"));
        image5.setImage(im5);
        javafx.scene.image.Image im6 = new javafx.scene.image.Image(getClass().getResourceAsStream("about.png"));
        image6.setImage(im6);

        home.getStyleClass().add("home");
        label1.getStyleClass().add("label1");

        search.getStyleClass().add("home");
        label2.getStyleClass().add("label1");

        profile.getStyleClass().add("home");
        label3.getStyleClass().add("label1");

        admin.getStyleClass().add("home");
        label4.getStyleClass().add("label1");

        about_us.getStyleClass().add("home");
        label5.getStyleClass().add("label1");

        book.getStyleClass().add("home");
        exam_label.getStyleClass().add("label1");
        try {
            Node ppp = FXMLLoader.load(getClass().getResource("/home/start.fxml"));
//            pane.getChildren().clear();
            start.getChildren().add(ppp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
//
            FXMLLoader load = new FXMLLoader();

            Parent v = load.load(getClass().getResource("/vbox/vbox.fxml").openStream());
            VboxController user = (VboxController) load.getController();
            v.getStylesheets().add("vbox/vbox.css");
            if (!name.equals("")) {
                if (name.equals("user")) {
                    user.user(name);

                } else {
                    user.admin(name);

                }
            } else {
                user.cancel_logout();
            }

            drawer.setSidePane(v);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        HamburgerBackArrowBasicTransition hab2 = new HamburgerBackArrowBasicTransition(hamburger);
        hamburger.getStyleClass().add("home");
        hab2.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            hab2.setRate(hab2.getRate() * -1);

            hab2.play();
            if (drawer.isShown()) {
                drawer.close();
            } else {

                drawer.open();

            }

        });

        profile.setDisable(true);
        book.setDisable(true);
        admin.setDisable(true);

    }

    void user_privilage() {
        book.setDisable(false);
        profile.setDisable(false);
    }

    void admin_privilage() {
        admin.setDisable(false);
    }

}
