package jdbc.controller;

import jdbc.dao.GuestsDAO;
import jdbc.model.Guests;
import tests.TestConnectionFactory;

import java.sql.Date;
import java.util.List;

public class GuestsController {

    private GuestsDAO guestsDAO;

    public GuestsController(){
        //TODO -> Cambiar a ConnectionFactiory con DataSource y Pool
        TestConnectionFactory conFactory = new TestConnectionFactory();
        this.guestsDAO = new GuestsDAO(conFactory.initConnection());

    }

    public void create(Guests guests){
        this.guestsDAO.create(guests);

    }

    public void update(String firstName, String lastName, Date birthDate, String phoneNumber, int bookingId, int id){
        this.guestsDAO.update(firstName, lastName, birthDate, phoneNumber, bookingId, id);
    }

    public void delete(int id){
        this.guestsDAO.delete(id);
    }

    public List<Guests> list() {
        return this.guestsDAO.list();
    }

    public List<Guests> listId(int bookingId){
        return this.guestsDAO.guestsListId(bookingId);
    }
}
