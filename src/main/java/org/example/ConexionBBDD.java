package org.example;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBBDD {
//    // INSTITUTO
//    private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
//    private static final String user = "CONCIERTOS";
//    private static final String password = "1234";

    // CASA
    private static final String url = "jdbc:oracle:thin:@192.168.56.1:1521:xe";
    private static final String user = "CONCIERTOS";
    private static final String password = "1234";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la BD", e);
        }
    }
}
