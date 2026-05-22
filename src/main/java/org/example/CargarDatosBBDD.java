package org.example;

import java.sql.*;
import java.util.*;

import javafx.scene.control.TableView;

public class CargarDatosBBDD {
    // METODO QUE RECIBE LA TABLE-VIEW DE ARTISTA (ES LLAMADO DESDE VentanaTabla)
    public static void cargarDatos(TableView<Artista> tableView) {

        try (Connection conn = ConexionBBDD.conectar();
             Statement stmt = conn.createStatement();
             // CONSULTA QUE DEVUELVE UN LISTADO DE ARTISTAS CON SU GENERO MUSICAL
             ResultSet rs = stmt.executeQuery("SELECT NOMBRE_A, GENERO_MUSICAL FROM ARTISTA");) {
            while (rs.next()) {
                String nombre = rs.getString(1);
                String genero = rs.getString(2);
                /**
                 * CREA UN OBJETO ARTISTA CON LOS DATOS Y LOS AÑADE DIRECTAMENTE A LA TABLA
                 */
                tableView.getItems().add(new Artista(nombre, genero));
            }
        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e);
        }
    }

    // METODO QUE DEVUELVE UN SET DE STRING (ES LLAMADO DESDE VentanaEliminar)
    public static Set<String> cargarNombresArtistas() {
        //PREPARO UN SET VACÍO
        Set<String> artistas = new HashSet<>();
        try (Connection conn = ConexionBBDD.conectar();
             Statement stmt = conn.createStatement();
             //CONSULTA QUE DEVUELVE UNA LISTA CON LOS NOMBRES DE LOS ARTISTAS
             ResultSet rs = stmt.executeQuery("SELECT NOMBRE_A FROM ARTISTA");) {
            while (rs.next()) {
                String nombre = rs.getString(1);
                // LLENA EL SET CON LOS NOMBRES
                artistas.add(nombre);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e);
        }
        //DEVUELVE EL SET
        return artistas;
    }
}
