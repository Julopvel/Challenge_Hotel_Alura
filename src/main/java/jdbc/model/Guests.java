package jdbc.model;

import java.sql.Date;

public class Guests {

    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phoneNumber;
    private int bookingId;



    public Guests(int id, String firstName, String lastName, Date birthDate, String phoneNumber, int bookingId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.bookingId = bookingId;
    }

    public Guests(String firstName, String lastName, Date birthDate, String phoneNumber, int bookingId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.bookingId = bookingId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getBookingId() {
        return bookingId;
    }

    @Override
    public String toString() {
        return String.format("{id: %s, firstName: %s, lastName: %s, birthDate: %s, phoneNumber: %s, bookingId: %s}",
                this.id, this.firstName, this.lastName, this.birthDate, this.phoneNumber, this.bookingId);
    }

}
