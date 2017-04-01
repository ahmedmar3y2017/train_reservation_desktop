package profile;

import database.database;
import dialogs.dialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import login.mycontroller;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<data> table;

    @FXML
    private Button cancel;

    @FXML
    private TableColumn<data, Integer> userid;
    @FXML
    private TableColumn<data, Integer> tripid;
    @FXML
    private TableColumn<data, String> from;

    @FXML
    private TableColumn<data, String> to;

    @FXML
    private TableColumn<data, String> date;

    @FXML
    private TableColumn<data, String> time;

    @FXML
    private TableColumn<data, Integer> seat_number;

    @FXML
    private TableColumn<data, Integer> price;
    @FXML
    private ComboBox edit;
    @FXML
    private AnchorPane ss;
    @FXML
    private StackPane stack;
    ObservableList<data> list = FXCollections.observableArrayList();

    @FXML
    void action(ActionEvent event) {

        if (!table.getSelectionModel().isEmpty()) {

            data d = table.getSelectionModel().getSelectedItem();

            try {
                boolean f = database.delete_reservation(d.getUserid(), d.getTripid(), d.getDate(), d.getSeat_number());
                if (f == true) {
                    if (table.getItems().removeAll(list)) {
                        for (int i = list.size() - 1; i >= 0; i--) {

                            list.remove(i);
                        }

                        List<data> ll = database.retu();

                        for (data p : ll) {
                            int getUserid = p.getUserid();
                            int getTripid = p.getTripid();

                            String getFrom = p.getFrom();
                            String getTo = p.getTo();
                            String getDate = p.getDate();
                            String getTime = p.getTime();
                            int getSeat_number = p.getSeat_number();
                            System.out.println(getSeat_number);

                            int getPrice = p.getPrice();

                            list.add(new data(getUserid, getTripid, getFrom, getTo, getDate, getTime, getSeat_number, getPrice));
                            table.setItems(list);
                        }

                    }
                } else {
//                    System.out.println("Error");
                    new dialogs.dialog(Alert.AlertType.ERROR, "error", "Delete Error");

                }

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
//            System.out.println("Error");
            new dialogs.dialog(Alert.AlertType.ERROR, "error", "Select Trip From table");

        }

    }
    String phone;
    int id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//table.setDisable(true);
//        cancel.getStyleClass().add
        ss.getStyleClass().add("ss");

        cancel.getStylesheets().add("button");
        phone = mycontroller.getEemail();
        try {
            id = database.get_user_id(phone);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList l = FXCollections.observableArrayList("edit phone", "edit password");
        System.out.println(l);
        edit.setItems(l);
        edit.setValue("edit phone");

        VBox v1 = new VBox();

        TextField t1 = new TextField();
        t1.setPromptText("Old Phone");
        t1.setFont(new Font(20));
        TextField t2 = new TextField();
        t2.setPromptText("New Phone");
        t2.setFont(new Font(20));

        Button b = new Button("Edit Phone");
        b.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String old_phoone = t1.getText();
                String new_phoone = t2.getText();
                //
                if (old_phoone.length() == 0 || new_phoone.length() == 0) {

                    if (old_phoone.length() == 0) {

                        new dialogs.dialog(Alert.AlertType.WARNING, "Error", "Enter Old Phone");
                    } else {
                        new dialogs.dialog(Alert.AlertType.WARNING, "Error", "Enter New Phone");

                    }

                } else {

                    if (database.return_phone(id).equals(old_phoone)) {
                        boolean b = database.update_phone(new_phoone, id);
//                        JOptionPane.showMessageDialog(null, "phone updated successfully");
                        new dialogs.dialog(Alert.AlertType.CONFIRMATION, "Done", "phone updated successfully");
                    } else {
//                        JOptionPane.showMessageDialog(null, "Please Enter Your Right Phone");
                        new dialogs.dialog(Alert.AlertType.ERROR, "Error", "Please Enter Your Right Phone");

                    }
                }
            }
        });

        v1.getChildren().addAll(t1, t2, b);
        stack.getChildren().clear();
        stack.getChildren().add(v1);

//        System.out.println(mycontroller.getEemail());
        try {
            Connection conn = database.connect();
            List<data> ll = database.retu();

            for (data p : ll) {
                int getUserid = p.getUserid();
                int getTripid = p.getTripid();

                String getFrom = p.getFrom();
                String getTo = p.getTo();
                String getDate = p.getDate();
                String getTime = p.getTime();
                int getSeat_number = p.getSeat_number();
                System.out.println(getSeat_number);

                int getPrice = p.getPrice();

                list.add(new data(getUserid, getTripid, getFrom, getTo, getDate, getTime, getSeat_number, getPrice));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        userid.setCellValueFactory(new PropertyValueFactory<data, Integer>("userid"));
        tripid.setCellValueFactory(new PropertyValueFactory<data, Integer>("tripid"));

        from.setCellValueFactory(new PropertyValueFactory<data, String>("from"));

        to.setCellValueFactory(new PropertyValueFactory<data, String>("to"));

        date.setCellValueFactory(new PropertyValueFactory<data, String>("date"));
        time.setCellValueFactory(new PropertyValueFactory<data, String>("time"));

        seat_number.setCellValueFactory(new PropertyValueFactory<data, Integer>("seat_number"));
        price.setCellValueFactory(new PropertyValueFactory<data, Integer>("price"));

        table.getItems().addAll(list);

    }

    @FXML
    void edition(ActionEvent event) {

        String editphone = edit.getSelectionModel().getSelectedItem().toString();

        if (editphone.equals("edit phone")) {

            VBox v1 = new VBox();

            TextField t1 = new TextField();
            t1.setPromptText("Old Phone");
            t1.setFont(new Font(20));
            TextField t2 = new TextField();
            t2.setPromptText("New Phone");
            t2.setFont(new Font(20));

            Button b = new Button("Edit Phone");
            b.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    String old_phoone = t1.getText();
                    String new_phoone = t2.getText();
                    //
                    if (old_phoone.length() == 0 || new_phoone.length() == 0) {

                        if (old_phoone.length() == 0) {

                            new dialogs.dialog(Alert.AlertType.WARNING, "Error", "Enter Old Phone");
                        } else {
                            new dialogs.dialog(Alert.AlertType.WARNING, "Error", "Enter New Phone");

                        }

                    } else {

                        if (database.return_phone(id).equals(old_phoone)) {
                            boolean b = database.update_phone(new_phoone, id);
//                        JOptionPane.showMessageDialog(null, "phone updated successfully");
                            new dialogs.dialog(Alert.AlertType.CONFIRMATION, "Done", "phone updated successfully");
                        } else {
//                        JOptionPane.showMessageDialog(null, "Please Enter Your Right Phone");
                            new dialogs.dialog(Alert.AlertType.ERROR, "Error", "Please Enter Your Right Phone");

                        }
                    }
                }
            });

            v1.getChildren().addAll(t1, t2, b);
            stack.getChildren().clear();
            stack.getChildren().add(v1);

        } else if (editphone.equals("edit password")) {
            VBox v1 = new VBox();

            TextField t1 = new TextField();
            t1.setPromptText("Old Pass");
            t1.setFont(new Font(20));
            PasswordField t2 = new PasswordField();
            t2.setPromptText("New Pass");
            t2.setFont(new Font(20));
            PasswordField t3 = new PasswordField();
            t3.setPromptText("Confirm");
            t3.setFont(new Font(20));

            Button b = new Button("Edit password");
            b.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    String old_pass = t1.getText();
                    String new_pass = t2.getText();
                    String Confirm = t3.getText();

                    if (old_pass.length() == 0 || new_pass.length() == 0 || Confirm.length() == 0) {

                        if (old_pass.length() == 0) {
                            new dialogs.dialog(Alert.AlertType.WARNING, "Error", "Enter  old password ");
                            return;
                        } else if (new_pass.length() == 0) {
                            new dialogs.dialog(Alert.AlertType.WARNING, "Error", "Enter  New password ");
                            return;

                        } else if (Confirm.length() == 0) {
                            new dialogs.dialog(Alert.AlertType.WARNING, "Error", "Enter  Confirm password ");
                            return;

                        }

                    } else {
                        if (!new_pass.equals(Confirm)) {

                            new dialog(Alert.AlertType.WARNING, "Error", "Two Passwords Done't Match");
                        } else {
//------------ check ----
                            if (database.return_password(id).equals(old_pass)) {
                                boolean b = database.update_password(new_pass, id);
//                                JOptionPane.showMessageDialog(null, "password updated successfully");
                                new dialog(Alert.AlertType.INFORMATION, "Done", "password updated successfully");

                            } else {
                                new dialog(Alert.AlertType.INFORMATION, "Done", "Error in your password ....");

                            }

                        }

                    }

                }
            });

            v1.getChildren().addAll(t1, t2, t3, b);
            stack.getChildren().clear();
            stack.getChildren().add(v1);

        }
    }

}
