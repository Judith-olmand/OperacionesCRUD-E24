package org.example;

import javafx.scene.control.Alert;
import java.sql.*;

public class InsertarArtista {
    public static void nuevoArtista(String nombreA, String generoA, String paisA) {
        String insertar = "INSERT INTO artista (id_a ,nombre_a, genero_musical, pais_origen) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBBDD.conectar();
             Statement st = conn.createStatement();
             PreparedStatement ps = conn.prepareStatement(insertar)) {
            String consulta = "SELECT MAX(ID_A) FROM ARTISTA";
            ResultSet rs = st.executeQuery(consulta);
            rs.next();
            int idArtista = rs.getInt(1) + 1;

            ps.setInt(1, idArtista);
            ps.setString(2, nombreA);
            ps.setString(3, generoA);
            ps.setString(4, paisA);
            ps.executeUpdate();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Información");
            alerta.setHeaderText("Completado");
            alerta.setContentText("Artista insertado con éxito");
            alerta.showAndWait();

        } catch (SQLException e) {
            System.out.println("Error al insertar artista: " + e.getMessage());
        }
    }
}
