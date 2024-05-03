package jdbc.controller;

import jdbc.dao.BookingDAO;
import jdbc.model.Booking;
import tests.TestConnectionFactory;

import java.sql.Date;
import java.util.List;

public class BookingController {

    private BookingDAO bookingDAO;

    public BookingController(){
        //TODO -> Cambiar a ConnectionFactiory con DataSource y Pool
        var conFactory = new TestConnectionFactory();
        this.bookingDAO = new BookingDAO(conFactory.initConnection());
    }

    public void create(Booking booking){
        this.bookingDAO.create(booking);
    }

    public void update(Date checkIn, Date checkOut, int price, String paymentMethod, int id){
        this.bookingDAO.update(checkIn, checkOut, price, paymentMethod, id);
    }

    public void delete(int id){
        this.bookingDAO.delete(id);
    }

    public List<Booking> list(){
        return this.bookingDAO.list();
    }
}
