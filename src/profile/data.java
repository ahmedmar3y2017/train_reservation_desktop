/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profile;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ahmed
 */
public class data {
    
    SimpleIntegerProperty userid;
    SimpleIntegerProperty tripid;
    
    SimpleStringProperty from;
    SimpleStringProperty to;
    SimpleStringProperty date;
    SimpleStringProperty time;
    SimpleIntegerProperty seat_number;
    SimpleIntegerProperty price;

//    Simple
    public data(int userid, int tripid, String from, String to, String date, String time, int seat_number, int price) {
        this.userid = new SimpleIntegerProperty(userid);
        this.tripid = new SimpleIntegerProperty(tripid);
        
        this.from = new SimpleStringProperty(from);
        this.to = new SimpleStringProperty(to);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.seat_number = new SimpleIntegerProperty(seat_number);
        this.price = new SimpleIntegerProperty(price);
        
    }
//       public data( String username, String password) {
////        this.id = new SimpleIntegerProperty(id);
//        
//        this.username = new SimpleStringProperty(username);
//        this.password = new SimpleStringProperty(password);
//        
//    }

    public int getUserid() {
        return userid.get();
    }
    
    public void setUserid(int userid) {
        this.userid.set(userid);
    }
    
    public String getFrom() {
        return from.get();
    }
    
    public void setFrom(String from) {
        this.from.set(from);
    }
    
    public String getTo() {
        return to.get();
    }
    
    public void setTo(String to) {
        this.to.set(to);
    }
    
    public String getDate() {
        return date.get();
    }
    
    public void setDate(String date) {
        this.date.set(date);
    }
    
    public String getTime() {
        return time.get();
    }
    
    public void setTime(String time) {
        this.time.set(time);
    }
    
    public int getSeat_number() {
        return seat_number.get();
    }
    
    public void setSeat_number(int seat_number) {
        this.seat_number.set(seat_number);
    }
    
    public int getPrice() {
        return price.get();
    }
    
    public void setPrice(int price) {
        this.price.set(price);
    }
    
    public int getTripid() {
        return tripid.get();
    }
    
    public void setTripid(int tripid) {
        this.tripid.set(tripid);
    }
    
}
