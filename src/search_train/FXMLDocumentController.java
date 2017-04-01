/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search_train;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import database.database;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.swing.JOptionPane;
import static search_train.DateTimePicker.DefaultFormat;

/**
 *
 * @author MuHamd Gomaa
 */
public class FXMLDocumentController implements Initializable {
    
    


    @FXML
    private AnchorPane ss;

    @FXML
    private Label label;

    @FXML
    private JFXComboBox<String> compo_to;

    @FXML
    private JFXComboBox<String> compo_from;

    @FXML
    private Button btn_sesarch;

    @FXML
    private Label l2;

    @FXML
    private Label l1;

    @FXML
    private ImageView ima1;

    @FXML
    private Label avl1;
    @FXML
    private Label avl;

    @FXML
    private Label details;
    @FXML
    private ListView<Time> listv;
    @FXML
    private AnchorPane a;
    @FXML
    private ImageView ima2;

    @FXML
    private Label l21;

    ObservableList<Time> ll = FXCollections.observableArrayList();


    public static DateTimePicker dd = new DateTimePicker();

//    database data = null;

    public static String compf = "caairo";
    public static String compto = "aswan";
    public static String date = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//compf = "caairo";
//compto="aswan";
        // date = dd.getEditor().getText();
        // compf=compo_from.getSelectionModel().getSelectedItem();
        // System.out.println("fsfdffss"+compf);

        avl.setVisible(false);

        avl1.setVisible(false);
        details.setDisable(true);
        details.setVisible(false);
        //    a.getChildren().add(dd);
        ima2.setVisible(false);
        listv.setVisible(false);

        // String date_time= dd.getEditor().getText();
//          System.out.println(ss);
//
//
        ss.getStyleClass().add("ss");
        label.getStyleClass().add("ff");
        compo_to.getStyleClass().add("ff");
        compo_from.getStyleClass().add("ff");
        avl1.getStyleClass().add("ff");
        btn_sesarch.getStyleClass().add("sds");
        details.getStyleClass().add("ff");
        dd.getStyleClass().add("ff");
        ima1.getStyleClass().add("ff");
        l1.getStyleClass().add("ff");

        try {
            //        database data = null;
            database.connect();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
//            data = new database();

            ResultSet rr = database.select_from_to();
            while (rr.next()) {
                compo_from.getItems().addAll(rr.getString("from"));

                compo_to.getItems().addAll(rr.getString("to"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        javafx.scene.image.Image im1 = new javafx.scene.image.Image(getClass().getResourceAsStream("ssss.png"));
        ima1.setImage(im1);
        javafx.scene.image.Image im2 = new javafx.scene.image.Image(getClass().getResourceAsStream("clo.png"));
        ima2.setImage(im2);

    }

    @FXML
    public void mouse_event(MouseEvent ee) throws SQLException, ClassNotFoundException {
//        data = new database();
        int id = 0;
        Time time = null;
        Double price = 0.0;
        int available_seat = 0;
        int train_id = 0;
        int totalseat = 0;

        if (ee.getSource() == btn_sesarch) {
            compf = compo_from.getSelectionModel().getSelectedItem();
            compto = compo_to.getSelectionModel().getSelectedItem();
            date = dd.getEditor().getText();

//              String arr[] = {date};
//        String arr2[] = {" please enter the date of trip "};
            String message = "";

            //System.out.println(compf);
            // Result rrr = JUnitCore.runClasses(testnullvalue.class);
            //  if (rrr.wasSuccessful()) {
            if (compo_from.getSelectionModel().getSelectedItem() == null || compo_to.getSelectionModel().getSelectedItem() == null) {

                if (compo_from.getSelectionModel().getSelectedItem() == null) {

                    message += "please select from";
                }
                if (compo_to.getSelectionModel().getSelectedItem() == null) {

                    message += "please select to";
                }

                new dialogs.dialog(Alert.AlertType.ERROR, " attention", message);
            } else {

                ResultSet rs = database.select_trip(compo_from, compo_to);
                for (int i = ll.size() - 1; i >= 0; i--) {
                    ll.remove(i);
                }
                boolean ff = false;
                while (rs.next()) {

                    time = rs.getTime(1);

                    ll.add(time);
                    ff = true;
                }

                if (ff) {

                    listv.setItems(ll);

                    details.setDisable(false);
                    // details.setVisible(true);
                    ima2.setVisible(true);
                    listv.setVisible(true);
                    avl.setVisible(true);
                    //new dialogs.dialog(Alert.AlertType.CONFIRMATION, " confirm", "please enter correct data");

                } else {
                    new dialogs.dialog(Alert.AlertType.INFORMATION, " information", "there is no train at this time");

                    details.setDisable(true);
                    // details.setVisible(true);
                    ima2.setVisible(false);
                    listv.setVisible(false);
                    avl.setVisible(false);

                }

//            } else {
//new dialogs.dialog(Alert.AlertType.ERROR, " attention", "please enter correct data");
//            }
            }
        }

        if (ee.getSource() == listv) {

            ObservableList<Time> times;
            times = listv.getSelectionModel().getSelectedItems();

            // ResultSet rr =database.show_specific_date(dd);
            for (Time select : times) {

                System.out.println(select);

                ResultSet rr = database.show_specific_trip(select);
                while (rr.next()) {

                    id = rr.getInt(1);
                    System.out.println(id);
                    price = rr.getDouble(2);
                    time = rr.getTime(3);
                    available_seat = rr.getInt(4);
                    train_id = rr.getInt(5);
                    totalseat = rr.getInt(6);

                    details.setText(" id : " + id + " \n price is :" + price + "\n will leave at :" + time + " \n the avalailable seat : " + available_seat + "\n train id :" + train_id + " \n totalseat" + totalseat);
                    // details.setDisable(false);
                    // details.setVisible(true);
                    // ima2.setVisible(true);
                    listv.setVisible(true);
                    details.setDisable(false);
                    details.setVisible(true);
                    avl1.setVisible(true);

                }

            }
//                        
//                        
        }

    }
}
