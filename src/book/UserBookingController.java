package book;

import com.sun.javafx.binding.StringFormatter;
import database.database;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.swing.JOptionPane;
import login.mycontroller;

public class UserBookingController implements Initializable {

//    database data_base;
    Connection con;
    Statement st = null;
    ResultSet rs = null;

    ObservableList<Booking> data;

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private AnchorPane ss;
    @FXML
    TableView<Booking> table;

    @FXML
    TableColumn id;

    @FXML
    TableColumn price;

    @FXML
    TableColumn time;

    @FXML
    TableColumn source;

    @FXML
    TableColumn destination;

    @FXML
    TableColumn available;

    @FXML
    private Button book;

    @FXML
    private DatePicker date;
    static Connection conn;
    String phone;
    int user_id;
//    @FXML
//    private TextField userid;
    @FXML
    private ImageView image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ss.getStyleClass().add("ss");
        anchorpane.getStyleClass().add("ss");
        book.getStylesheets().add("button");
        javafx.scene.image.Image im5 = new javafx.scene.image.Image(getClass().getResourceAsStream("ee.gif"));
        image.setImage(im5);
        phone = mycontroller.getEemail();
        try {
            //        data_base = new database();
            con = database.connect();
            user_id = database.get_user_id(phone);
        } catch (SQLException ex) {
            Logger.getLogger(UserBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        data = FXCollections.observableArrayList();

        try {

            //retrieve data & store it in a list
//        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT trip.id , trip.price , trip.time, city.from , city.to ,trip.available_seat FROM trip join city on city.id=trip.cityid");
            while (rs.next()) {
                data.add(new Booking(rs.getInt(1), rs.getInt(2), rs.getTime(3), rs.getString(4), rs.getString(5), rs.getInt(6)));

            }

            //set the list into tableview
            table.setItems(data);

            //give priorities to TableColumns
            processColumns();
        } catch (SQLException ex) {
            Logger.getLogger(UserBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void processColumns() {
        id.setCellValueFactory(new PropertyValueFactory("id"));
        price.setCellValueFactory(new PropertyValueFactory("price"));
        time.setCellValueFactory(new PropertyValueFactory("time"));
        source.setCellValueFactory(new PropertyValueFactory("source"));
        destination.setCellValueFactory(new PropertyValueFactory("destination"));
        available.setCellValueFactory(new PropertyValueFactory("available_seat"));
    }

    @FXML
    void handleBookAction(ActionEvent event) throws SQLException {
        ObservableList<Booking> selectedrow;    //get selected row
        selectedrow = table.getSelectionModel().getSelectedItems();

        int row = table.getSelectionModel().getSelectedIndex();
        //get the row

        //if no row selected
        if (row == -1) {
//            JOptionPane.showMessageDialog(null, "please choose a record!");
            new dialogs.dialog(Alert.AlertType.INFORMATION, "Select", "please choose a record!");
        } else {

//            new date
            int trip_id = (int) id.getCellObservableValue(row).getValue();
            int ticket_price = (int) price.getCellObservableValue(row).getValue();

            // check available seat of trip ..............
            if (database.check_availableseat(trip_id)) {

                //                 check date before all
//                System.out.println(date.getValue().toString());
                if (date.getValue() != null) {
//                    System.out.println("Done");

                    if (database.check_price(user_id, ticket_price)) {
                        database.update_seats(con, trip_id);

                        // insert data into ticket table //
                        int trip_id_value = (int) id.getCellObservableValue(row).getValue();
                        int seat_number = (int) available.getCellObservableValue(row).getValue();
                        database.insert(con, user_id, trip_id_value, date.getValue(), seat_number);

                        //decreasing user charge money by ticket price //
                        database.update_charge(con, ticket_price, user_id);
                        // delete and update table from database again

                        if (table.getItems().removeAll(data)) {
                            for (int i = data.size() - 1; i >= 0; i--) {

                                data.remove(i);
                            }

                            try {

                                //retrieve data & store it in a list
//        try {
                                st = con.createStatement();
                                rs = st.executeQuery("SELECT trip.id , trip.price , trip.time, city.from , city.to ,trip.available_seat FROM trip join city on city.id=trip.cityid");
                                while (rs.next()) {
                                    data.add(new Booking(rs.getInt(1), rs.getInt(2), rs.getTime(3), rs.getString(4), rs.getString(5), rs.getInt(6)));

                                }

                                //set the list into tableview
                                table.setItems(data);

                                //give priorities to TableColumns
                                processColumns();
                            } catch (SQLException ex) {
                                Logger.getLogger(UserBookingController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    } else {
//                        JOptionPane.showMessageDialog(null, "Your charge Not Enought");
                        new dialogs.dialog(Alert.AlertType.ERROR, "Error", "Your charge Not Enought");

                    }
                } else {
//                    System.out.println("error date is null");
                    new dialogs.dialog(Alert.AlertType.ERROR, "Error", "error date is null");

                }

            } else {
//                JOptionPane.showMessageDialog(null, "Trip seats is completed");
                new dialogs.dialog(Alert.AlertType.ERROR, "Trip complete", "Trip seats is completed");

            }

        }
    }
}
