package jdbc.dao;

import jdbc.model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    private Connection connection;

    //Constructor que crea una Connection
    public BookingDAO(Connection connection){
        this.connection = connection;
    }

    //TODO Falta listar

    public void create (Booking booking) {

        try {
            String query = "INSERT INTO bookings (check_in, check_out, price, payment_method) " +
                    "VALUES (?, ?, ?, ?)";

            final PreparedStatement pstm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            try (pstm) {
                pstm.setDate(1, booking.getCheckIn());
                pstm.setDate(2, booking.getCheckOut());
                pstm.setInt(3, booking.getPrice());
                pstm.setString(4, booking.getPaymentMethod());

                pstm.executeUpdate();

                final ResultSet rst = pstm.getGeneratedKeys();
                try (rst) {
                    while (rst.next()) {
                        booking.setId(rst.getInt(1));
                        System.out.printf(
                                "New booking saved with ID %s%n", booking.getId());
                    }
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(Date checkIn, Date checkOut, int price, String paymentMethod, int id) {
        try {
            String query = "UPDATE bookings SET " +
                    "check_in = ?, check_out = ?, price = ?, payment_method = ? WHERE id = ?";

            final PreparedStatement prst = connection.prepareStatement(query);
            try (prst) {
                /**
                 * El orden de los "?" detallados en la query de arriba se deben respetar aquí abajo
                 * al aplicar los métodos setDate, setInt, etc...
                 */
                prst.setDate(1, checkIn);
                prst.setDate(2, checkOut);
                prst.setInt(3, price);
                prst.setString(4, paymentMethod);
                prst.setInt(5, id);

                prst.executeUpdate();
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void delete(int id){
        String query = "DELETE FROM bookings WHERE id = ?";

        try (PreparedStatement prst = connection.prepareStatement(query)){
//             PreparedStatement prst = connection.prepareStatement(query);
            prst.setInt(1, id);
            prst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //This method list shall be used in the class *DisplayBookingsFrame (change name)*
    public List<Booking> list(){
        List<Booking> listaBooking = new ArrayList<>();
        String query = "SELECT id, check_in, check_out, price, payment_method FROM bookings";

        try{
            final PreparedStatement prst = connection.prepareStatement(query);

            try(prst){
                prst.execute();

                final ResultSet rst = prst.getResultSet();

                try(rst){
                    while (rst.next()){
                        Booking row = new Booking(
                                rst.getInt("id"),
                                rst.getDate("check_in"),
                                rst.getDate("check_out"),
                                rst.getInt("price"),
                                rst.getString("payment_method")
                        );
                        listaBooking.add(row);
                    }
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaBooking;
    }

    public List<Booking> bookingListId(int id){
        List<Booking> bookingList = new ArrayList<>();
        String query = "SELECT id, check_in, check_out, price, payment_method FROM bookings WHERE id = ?";

        try {
            final PreparedStatement prst = connection.prepareStatement(query);

            try (prst) {
                prst.setInt(1, id);

                ResultSet rst = prst.executeQuery();
                try (rst) {
                    while (rst.next()) {
                        Booking row = new Booking(
                                rst.getInt("id"),
                                rst.getDate("check_in"),
                                rst.getDate("check_out"),
                                rst.getInt("price"),
                                rst.getString("payment_method")
                        );
                        bookingList.add(row);
                    }

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookingList;
    }
    
}
