package tests;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection Factory using only JDBC (DriverManager.getConnection),
 * no Datasource nor Connection Pool
 */

public class TestConnectionFactory {

    public Connection initConnection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC",
                    "root",
                    "\"k3l4m5n6\""
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws SQLException {
//        ConnectionFactory factory = new ConnectionFactory();
//        Connection con = factory.initConnection();

        Connection con = new TestConnectionFactory().initConnection();

        System.out.println("Opening a connection");

        con.close();

    }
}
