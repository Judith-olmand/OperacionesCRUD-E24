package org.example;

import javafx.scene.control.Alert;

import java.sql.*;

public class EliminarArtista {
    public static void borrarArtista(String nombre){
        String eliminar = "DELETE ARTISTA WHERE NOMBRE_A LIKE ?";
        try (Connection conn = ConexionBBDD.conectar();
             PreparedStatement ps = conn.prepareStatement(eliminar)){

            ps.setString(1,nombre);
            ps.executeUpdate();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Información");
            alerta.setHeaderText("Completado");
            alerta.setContentText("Artista eliminado con éxito");
            alerta.showAndWait();

        } catch (SQLException e){
            System.out.println("Error al eliminar artista" +  e.getMessage());

        }

    }
}
