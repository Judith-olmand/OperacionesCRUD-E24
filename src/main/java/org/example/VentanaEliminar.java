package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class VentanaEliminar {
    public static void formularioEliminar(){
        //CREA UNA NUEVA VENTANA QUE SERÁ UN FORMULARIO
        Stage formulario = new Stage();
        formulario.setTitle("Eliminar Artista");
        // CREO UN SET STRING Y LO LLENO LLAMANDO AL METODO INDICADO
        Set<String> artistas = CargarDatosBBDD.cargarNombresArtistas();

        //Etiqueta
        Label nombreArtista = new Label("Nombre Artista:");
        // CREO UN DESPLEGABLE
        ComboBox<String> listaArtista  = new ComboBox<>();
        // LLENO EL DESPLEGABLE CON EL SET ANTERIOR
        listaArtista.getItems().addAll(artistas);

        // CREO UN BOTÓN PARA ELIMINAR EL ARTISTA SELECCIONADO
        Button botonEliminar = new Button("Eliminar");
        // COLOCO EN HORIZONTAL LA ETIQUETA CON LA LISTA
        HBox horizontal = new HBox(nombreArtista, listaArtista);

        // CONTENEDOR
        BorderPane borderPane = new BorderPane();
        // COLOCO EN LA PARTE DE ARRIBA DE LA VENTANA LA "CAJA HORIZONTAL"
        borderPane.setTop(horizontal);
        // Y EN LA PARTE BAJA DE LA VENTANA EL BOTÓN
        borderPane.setBottom(botonEliminar);
        // LO ALINEO A LA DERECHA DE LA PARTE BAJA
        BorderPane.setAlignment(botonEliminar, Pos.BOTTOM_RIGHT);

        botonEliminar.setOnAction(e -> {
            if (listaArtista.getSelectionModel().getSelectedItem() != null) {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmación");
                alerta.setContentText("¿Desea eliminar el artista a la Base de Datos?");

                //Para capturar que se pulsa aceptar o cancelar
                Optional<ButtonType> resultado = alerta.showAndWait();
                //Si se pulsa algún boton y es el de ok elimina de la base de datos
                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    String nombreA = listaArtista.getSelectionModel().getSelectedItem();
                    EliminarArtista.borrarArtista(nombreA);
                    formulario.close();
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setContentText("Seleccione un artista a eliminar");
                alerta.showAndWait();
            }
        });

        Scene escena = new Scene(borderPane,300, 200);
        formulario.setScene(escena);
        formulario.show();
    }
}
