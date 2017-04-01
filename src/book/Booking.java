package book;

import java.sql.Time;

public class Booking {

    int id, price, available_seat;
    Time time;
    String source, destination;

    public Booking(int id, int price, Time time, String source, String destination, int availabe_seat) {
        this.id = id;
        this.price = price;
        this.time = time;
        this.source = source;
        this.destination = destination;
        this.available_seat = availabe_seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailable_seat() {
        return available_seat;
    }

    public void setAvailable_seat(int available_seats) {
        this.available_seat = available_seats;
    }

}
