package tests;

import java.sql.*;

/**
 * Test inserting data into MySQL DataBase using PreparedStatement
 */

public class TestPreparedStatement {

    public static void main(String[] args) throws SQLException {
        TestConnectionFactory factory = new TestConnectionFactory();
        Connection con = factory.initConnection();

        //1. PreparedStatement statement
        //2. statement.execute()
        //3. ResultSet

        String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_pago) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        Date fechaEntrada = Date.valueOf("2032-10-21");
        Date fechaSalida = Date.valueOf("2032-10-30");
        String valor = "5400";
        String formaPago = "Efectivo";

        stm.setDate(1, fechaEntrada);
        stm.setDate(2, fechaSalida);
        stm.setString(3, valor);
        stm.setString(4, formaPago);

        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();

        while (rst.next()){
            Integer id = rst.getInt(1);
            System.out.println("El id creado fue: " + id);
        }
    }
}
