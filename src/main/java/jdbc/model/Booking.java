package jdbc.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 * TODO: Modify the java.sql.Date variables to java.time.LocalDate
 * Check: https://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date
 */

public class Booking {

    private int id;
    private Date checkIn;
    private Date checkOut;
    private int price;
    private String paymentMethod;

    public Booking(Date checkIn, Date checkOut, int price, String paymentMethod) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
        this.paymentMethod = paymentMethod;
    }

    public Booking(int id, Date checkIn, Date checkOut, int price, String paymentMethod) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
        this.paymentMethod = paymentMethod;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public int getPrice() {
        return price;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

}