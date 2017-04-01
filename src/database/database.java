/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ComboBox;
import javax.swing.JOptionPane;
import profile.data;

/**
 *
 * @author ahmed
 */
public class database {

    static Connection conn;
    static Statement statement;
    static ResultSet resultSet;
    static PreparedStatement p;
    static String username = "root";
    static String password = "root";
    static String url = "jdbc:mysql://localhost:3306/train";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException ex) {

            ex.printStackTrace();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static List<data> retu() throws SQLException {

        ArrayList<data> list = new ArrayList<>();

        statement = conn.createStatement();

        resultSet = statement.executeQuery("select * from mydata where userid=1");
       // System.out.println("قبل");
        while (resultSet.next()) {
         //   System.out.println("فيه");
            int userid = resultSet.getInt("userid");
            System.out.println(userid);
            int tripid = resultSet.getInt("tripid");

            String from = resultSet.getString("from");
            String to = resultSet.getString("to");
            String date = resultSet.getDate("date").toString();
            String time = resultSet.getTime("time").toString();
            int seat_number = resultSet.getInt("seat_number");
            int price = (int) resultSet.getDouble("price");

            data p = new data(userid, tripid, from, to, date, time, seat_number, price);
//            System.out.println(tripid + "    ++++++++++++++++++++");
            list.add(p);

        }

        return list;

    }

    public static boolean delete_reservation(int userid, int tripid, String date, int seat_number) throws SQLException {

        boolean f = false;

        statement = connect().createStatement();
        statement.executeUpdate("delete from ticket where userid=" + userid + " and tripid=" + tripid + " and date='" + date + "' and seat_number=" + seat_number + "");
        f = true;

        return f;

    }

    public static boolean check_admin_login(String phone, String pass1) throws SQLException {
        boolean f = false;
        statement = conn.createStatement();
        resultSet = statement.executeQuery("select `phone` , `pass` from `admin` where `phone`='" + phone + "' and `pass`='" + pass1 + "' ");
        if (resultSet.next()) {
            f = true;

        }

        return f;
    }

    public static String get_admin_name(String phone) throws SQLException {

        String name = "";
        statement = conn.createStatement();
        resultSet = statement.executeQuery("select `name` from `admin` where `phone`='" + phone + "'");
        if (resultSet.next()) {

            name = resultSet.getString("name");
        }

        return name;

    }

    public static boolean check_user_login(String phone, String pass1) throws SQLException {

        boolean f = false;
        statement = conn.createStatement();
        resultSet = statement.executeQuery("select `phone` , `password` from `user` where `phone`='" + phone + "' and `password`='" + pass1 + "' ");
        if (resultSet.next()) {
            f = true;

        }

        return f;
    }

    public static String get_name(String phone) throws SQLException {

        String name = "";
        statement = conn.createStatement();
        resultSet = statement.executeQuery("select `name` from `user` where `phone`='" + phone + "'");
        if (resultSet.next()) {

            name = resultSet.getString("name");
        }

        return name;
    }

    public static boolean delete_ticket(int id) throws SQLException {
        boolean f = false;
        statement = conn.createStatement();
        statement.execute("delete from `ticket` where id=" + id + " and date");
        f = true;
        return f;

    }

    public static int get_user_id(String phone) throws SQLException {
        int id = 0;
        statement = conn.createStatement();
        resultSet = statement.executeQuery("select id from user where phone='" + phone + "'");
        if (resultSet.next()) {

            id = resultSet.getInt("id");
        }

        return id;
    }

    ///--------------------------------------- booking -------------------------------------
    public static void update_seats(Connection connection, int id) {
        try {
            p = connection.prepareStatement("UPDATE trip SET available_seat = available_seat-1 WHERE id= " + id + "");
            p.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    public static boolean update_charge(Connection connection, int price, int id) {
        try {
            p = connection.prepareStatement("UPDATE user SET charge_money = charge_money-" + price + " WHERE id= " + id + "");
            p.executeUpdate();
        } catch (SQLException ex) {

        }

        return true;
    }

    public static boolean insert(Connection connection, int user_id, int trip_id, LocalDate date, int seat_number) {
        try {
            p = connection.prepareStatement("insert into ticket values (" + user_id + "," + trip_id + ",'" + date + "'," + seat_number + ")");
            p.executeUpdate();
        } catch (SQLException ex) {

        }

        return true;

    }

    public static boolean check_price(int id, double ticket_price) throws SQLException {

        boolean f = false;

        p = conn.prepareStatement("select charge_money from user where id=? ");
        p.setInt(1, id);
        resultSet = p.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getDouble("charge_money") > ticket_price) {
                f = true;
            }

        }

        return f;
    }

    public static boolean check_availableseat(int trip_id) throws SQLException {

        p = conn.prepareStatement("select available_seat from trip where id=? ");
        p.setInt(1, trip_id);
        resultSet = p.executeQuery();
        if (resultSet.next()) {

            if (resultSet.getInt("available_seat") != 0) {

                return true;
            }

        }
        return false;
    }

    public static Object return_phone(int i) {
        String pas_return = "";
        try {
            p = conn.prepareStatement("SELECT phone FROM train.user where id = " + i + "");
            resultSet = p.executeQuery();

            while (resultSet.next()) {
                pas_return = resultSet.getString("phone");
            }
        } catch (SQLException ex) {
        }
        return pas_return;
    }

    public static boolean update_phone(String new_phoone, int i) {
        try {
            p = conn.prepareStatement("update user set phone = '" + new_phoone + "' where id = " + i + "");
            p.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public static String return_password(int id) {
        String pas_return = "";
        try {
            p = conn.prepareStatement("SELECT password FROM train.user where id = " + id + "");
            resultSet = p.executeQuery();

            while (resultSet.next()) {
                pas_return = resultSet.getString("password");
            }
        } catch (SQLException ex) {
        }
        return pas_return;
    }

    public static boolean update_password(String new_pass, int id) {
        try {
            p = conn.prepareStatement("update user set password = '" + new_pass + "' where id = " + id + "");
            p.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return true;
    }

    public static boolean insert_user(String name, String phone, String pass) throws SQLException {

        p = conn.prepareStatement("insert into user (name,phone,password) values (?,?,?) ");
        p.setString(1, name);
        p.setString(2, phone);
        p.setString(3, pass);

        p.executeUpdate();

        return true;

    }

    public static boolean check_user_phone(String phone) throws SQLException {
        boolean f = false;

        statement = conn.createStatement();
        resultSet = statement.executeQuery("select * from `user` where `phone`='" + phone + "'");
        if (resultSet.next()) {
            f = true;
        }

        return f;

    }

    //--------------------------------------------------- search  ----------------------------------
    public static ResultSet select_trip(ComboBox<String> from, ComboBox<String> to) throws SQLException {

        //ArrayList<trip> list = new ArrayList<>();
        try {
            p = conn.prepareStatement("select trip.time from trip JOIN city JOIN train ON city.id=trip.cityid and trip.trainid=train.id and city.from= ?  and city.to= ? ");

            p.setString(1, from.getSelectionModel().getSelectedItem());
            p.setString(2, to.getSelectionModel().getSelectedItem());
          //  prm.setString(3, dd.getEditor().getText());

            resultSet = p.executeQuery();

            return resultSet;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
    static boolean ch = false;

    public static boolean select_trip_validation(String from, String to) throws SQLException {
        try {
            p = conn.prepareStatement("select trip.time from trip JOIN city JOIN train ON city.id=trip.cityid and trip.trainid=train.id and city.from= ?  and city.to= ?");

            p.setString(1, from);
            p.setString(2, to);
//           prm.setString(3, date);

            resultSet = p.executeQuery();

            while (resultSet.next()) {

                ch = true;
                System.out.println("ew");
                break;

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return ch;

    }

    public static boolean check(ComboBox<String> from, ComboBox<String> to) throws SQLException {

        boolean f = false;
        ResultSet rr = database.select_trip(from, to);

        while (rr.next()) {
            f = true;

        }

        return f;

    }

    public static ResultSet select_from_to() throws SQLException {

        try {
            p = conn.prepareStatement("select city.from ,city.to from city;");

            resultSet = p.executeQuery();

            return resultSet;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public static ResultSet show_specific_trip(Time time) throws SQLException {

        try {
            p = conn.prepareStatement("select trip.id,trip.price,trip.time,trip.available_seat ,train.id ,train.total_seats from trip JOIN city JOIN train ON city.id=trip.cityid and trip.trainid=train.id  and trip.time= ?  ");
               //   prm.setString(1,time);
            //   prm.setString(1, date.getEditor().getText());
            p.setTime(1, time);

            resultSet = p.executeQuery();

            return resultSet;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public static boolean show_trip_validation(Time time) throws SQLException {

        boolean f = false;

        try {
            p = conn.prepareStatement("select trip.id,trip.price,trip.time,trip.available_seat ,train.id ,train.total_seats from trip JOIN city JOIN train ON city.id=trip.cityid and trip.trainid=train.id  and trip.time= ?  ");
               //   prm.setString(1,time);
            //   prm.setString(1, date.getEditor().getText());
            p.setTime(1, time);

            resultSet = p.executeQuery();
            while (resultSet.next()) {
                f = true;

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        return f;

    }

    public static boolean delete_trip() throws SQLException {
     boolean f = false;
        statement = conn.createStatement();
        statement.execute("delete from ticket where cast(`ticket`.`date` as date) < curdate()");
        f = true;
        return f;

    }

}
