package org.example;

import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;

public class VentanaActualizar {
    public static void formularioActualizar(){
        //CREA UNA NUEVA VENTANA QUE SERÁ UN FORMULARIO
        Stage formulario = new Stage();
        formulario.setTitle("Actualizar Artista");
        // CREO UN SET STRING Y LO LLENO LLAMANDO AL METODO INDICADO
        Set<String> artistas = CargarDatosBBDD.cargarNombresArtistas();

        //Etiqueta
        Label nombreArtista = new Label("Nombre Artista:");
        // CREO UN DESPLEGABLE
        ComboBox<String> listaArtista  = new ComboBox<>();
        // LLENO EL DESPLEGABLE CON EL SET ANTERIOR
        listaArtista.getItems().addAll(artistas);


        //Array para usar en el desplegable generos
        String[] generosMusicales = {
                "Pop",
                "Rock",
                "Hip-Hop",
                "Rap",
                "R&B",
                "Dance",
                "Reggaeton",
                "Country",
                "Jazz",
                "Metal"
        };
        Label generosMusicEtiqueta = new Label("Generos Musicales:");
        //Desplegable
        ComboBox<String> generos = new ComboBox();
        generos.getItems().addAll(generosMusicales);

        //Array para usar en el desplegable paises
        String[] paises = {
                "Estados Unidos",
                "Reino Unido",
                "España",
                "Colombia",
                "Puerto Rico",
                "México",
                "Canada",
                "Australia",
                "Jamaica",
                "Francia",
                "Alemania"
        };
        Label paisesEtiqueta = new Label("Paises Etiquetas:");
        ComboBox<String> pais = new ComboBox();
        pais.getItems().addAll(paises);

        // CREO UN BOTÓN PARA ACTUALIZAR EL ARTISTA SELECCIONADO
        Button botonActualizar = new Button("Actualizar");

        /*
        COLUMNA     0                           1
        +------------------------+------------------------+
        |      nombreArtista     |      listaArtista      |     FILA 0
        +------------------------+------------------------+
        |  generosMusicEtiqueta  |  generos(desplegable)  |     FILA 1
        +------------------------+------------------------+
        |     paisesEtiqueta     |   paises(desplegable)  |     FILA 2
        +------------------------+------------------------+
        */
        GridPane tabla = new GridPane();
        tabla.setHgap(15);
        tabla.setVgap(15);
        tabla.add(nombreArtista, 0, 0);
        tabla.add(listaArtista, 1, 0);
        tabla.add(generosMusicEtiqueta, 0, 1);
        tabla.add(generos, 1, 1);
        tabla.add(paisesEtiqueta, 0, 2);
        tabla.add(pais, 1, 2);



        // CONTENEDOR
        BorderPane borderPane = new BorderPane();
        // COLOCO EN LA PARTE DE ARRIBA DE LA VENTANA LA "CAJA HORIZONTAL"
        borderPane.setTop(tabla);
        // Y EN LA PARTE BAJA DE LA VENTANA EL BOTÓN
        borderPane.setBottom(botonActualizar);
        // LO ALINEO A LA DERECHA DE LA PARTE BAJA
        BorderPane.setAlignment(botonActualizar, Pos.BOTTOM_RIGHT);

        botonActualizar.setOnAction(e -> {
            if (listaArtista.getSelectionModel().getSelectedItem() != null &&
                    pais.getSelectionModel().getSelectedItem() != null &&
                    generos.getSelectionModel().getSelectedItem() != null) {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmación");
                alerta.setContentText("¿Desea actualizar el artista de la Base de Datos?");

                //Para capturar que se pulsa aceptar o cancelar
                Optional<ButtonType> resultado = alerta.showAndWait();
                //Si se pulsa algún boton y es el de ok elimina de la base de datos
                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    String nombreA = listaArtista.getSelectionModel().getSelectedItem();
                    String  generoA = generos.getSelectionModel().getSelectedItem();
                    String  paisA = pais.getSelectionModel().getSelectedItem();
                    ActualizarArtista.modificarArtista(nombreA, generoA, paisA);
                    formulario.close();
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setContentText("Seleccione todos los campos para actualizar");
                alerta.showAndWait();
            }
        });

        Scene escena = new Scene(borderPane,400, 350);
        //PARA AÑADIR LOS ESTILOS DEL CSS
        escena.getStylesheets().add(VentanaActualizar.class.getResource("/css/estilos.css").toExternalForm());

        formulario.setScene(escena);
        formulario.show();
    }
}
