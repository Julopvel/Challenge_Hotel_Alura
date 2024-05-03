package jdbc.dao;

import jdbc.model.Guests;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestsDAO {

    private Connection connection;

    public GuestsDAO(Connection connection){
        this.connection = connection;
    }

    public void create(Guests guests){
        String query = "INSERT INTO guests (first_name, last_name, birth_date, phone_number, booking_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        
        try {
            //TODO Probar sin y con Statement.RETURN_GENERATED_KEYS 
            final PreparedStatement prst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            try (prst) {
                prst.setString(1, guests.getFirstName());
                prst.setString(2, guests.getLastName());
                prst.setDate(3, guests.getBirthDate());
                prst.setString(4, guests.getPhoneNumber());
                prst.setInt(5, guests.getBookingId());

                prst.executeUpdate();

                final ResultSet rst = prst.getGeneratedKeys();
                try (rst) {
                    while (rst.next()){
                        guests.setId(rst.getInt(1));
                        System.out.printf(
                                "New guest saved with ID %s%n", guests.getId());
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String firstName, String lastName, Date birthDate, String phoneNumber, int bookingId, int id){
        //TODO probar que pasa si se actualiza el booking_id de la BD

        String query = "UPDATE guests SET " +
                "first_name = ?, last_name = ?, birth_date = ?, phone_number = ?, booking_id = ? WHERE id = ?";

        try {
            final PreparedStatement prst = connection.prepareStatement(query);

            try(prst){
                /**
                 * El orden de los "?" detallados en la query de arriba se deben respetar aquí abajo
                 * al aplicar los métodos setDate, setInt, etc...
                 */
                prst.setString(1, firstName);
                prst.setString(2, lastName);
                prst.setDate(3, birthDate);
                prst.setString(4, phoneNumber);
                prst.setInt(5, bookingId);
                prst.setInt(6, id);

                prst.executeUpdate();
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        String query = "DELETE FROM guests WHERE id = ?";

        try {
            final PreparedStatement prst = connection.prepareStatement(query);

            try(prst){
                prst.setInt(1, id);
                prst.executeUpdate();
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Guests> list(){
        List<Guests> listaGuests = new ArrayList<>();
        String query = "SELECT id, first_name, last_name, birth_date, phone_number, booking_id from guests";

        try{
            final PreparedStatement prst = connection.prepareStatement(query);
            //TODO que pasa si almaceno ResultSet utilizando PreparedStatement.executeQuery()??
            try (prst){
                prst.execute();
                final ResultSet rst = prst.getResultSet();

                try (rst){
                    while (rst.next()){
                        Guests row = new Guests(
                                rst.getInt("id"),
                                rst.getString(2),
                                rst.getString("last_name"),
                                rst.getDate(4),
                                rst.getString("phone_number"),
                                rst.getInt(6)
                        );
                        listaGuests.add(row);
                    }
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaGuests;
    }

}
