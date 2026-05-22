package org.example;

import java.sql.*;
import javafx.scene.control.Alert;


public class ActualizarArtista {
    public static void modificarArtista(String nombre, String genero, String pais){
        String actualizar = "UPDATE ARTISTA SET GENERO_MUSICAL = ?, PAIS_ORIGEN = ? WHERE NOMBRE_A = ?";

        try (Connection conn = ConexionBBDD.conectar();
             PreparedStatement ps = conn.prepareStatement(actualizar)) {
            ps.setString(1, genero);
            ps.setString(2, pais);
            ps.setString(3, nombre);
            ps.executeUpdate();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Información");
            alerta.setHeaderText("Completado");
            alerta.setContentText("Artista actualizado con éxito");
            alerta.showAndWait();

        } catch (SQLException e) {
            System.out.println("Error al actualizar artista" +  e.getMessage());
        }
    }
}
