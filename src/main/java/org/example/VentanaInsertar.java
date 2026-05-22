package org.example;

import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Optional;

public class VentanaInsertar {
    public static void formularioInsertar(){
        Stage formulario = new Stage();
        formulario.setTitle("Insertar Artista");

        //Etiqueta
        Label nombreArtista = new Label("Nombre Artista:");
        //Campo para escribir
        TextField nombreArtistaTF = new TextField();
        //Texto de fondo para saber que escribir
        nombreArtistaTF.setPromptText("Nombre Artista");

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
        //Por defecto sale seleccionado el primero
        generos.setValue(generosMusicales[0]);

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
        pais.setValue(paises[0]);



        /*
        COLUMNA     0                           1
        +------------------------+------------------------+
        |      nombreArtista     |   campoNombreArtista   |     FILA 0
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
        tabla.add(nombreArtistaTF, 1, 0);
        tabla.add(generosMusicEtiqueta, 0, 1);
        tabla.add(generos, 1, 1);
        tabla.add(paisesEtiqueta, 0, 2);
        tabla.add(pais, 1, 2);

        Button botonInsertar = new Button("Insertar");


        BorderPane borderPane = new BorderPane();
        borderPane.setTop(tabla);
        borderPane.setBottom(botonInsertar);
        BorderPane.setAlignment(botonInsertar, Pos.BOTTOM_RIGHT);

        botonInsertar.setOnAction(e -> {
            if (nombreArtistaTF.getText().equals("")) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setContentText("Ingrese todos los campos para guardar");
                alerta.showAndWait();
            } else {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmación");
                alerta.setContentText("¿Desea añadir el artista a la Base de Datos?");

                //Para capturar que se pulsa aceptar o cancelar
                Optional<ButtonType> resultado = alerta.showAndWait();
                //Si se pulsa algún boton y es el de ok añade a la base de datos
                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    String nombreA = nombreArtistaTF.getText();
                    String paisA = pais.getValue();
                    String generoA = generos.getValue();
                    InsertarArtista.nuevoArtista(nombreA,generoA,paisA);
                    formulario.close();
                }
            }
        });

        Scene escena = new Scene(borderPane,300, 200);
        formulario.setScene(escena);
        formulario.show();
    }
}
