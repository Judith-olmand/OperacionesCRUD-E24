package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //PARA CARGAR LA FUENTE
        Font.loadFont(getClass().getResourceAsStream("/fonts/Macondo-Regular.ttf"), 14);

        //Título de la ventana principal
        primaryStage.setTitle("Ejercicio 24");

        Button insertar = new Button("Insertar nuevo artista");
        Button leer = new Button("Mostrar todos los artistas");
        Button actualizar = new Button("Actualizar un artista");
        Button eliminar = new Button("Eliminar un artista");

        insertar.setPrefSize(400,200);
        leer.setPrefSize(400,200);
        actualizar.setPrefSize(400,200);
        eliminar.setPrefSize(400,200);

        VBox root = new VBox(insertar, leer, actualizar, eliminar);
        Scene scene = new Scene(root,400,350);
        primaryStage.setScene(scene);
        primaryStage.show();

        insertar.setOnAction(e -> {VentanaInsertar.formularioInsertar();});
        leer.setOnAction(e -> {VentanaTabla.abrirVentanaTabla();});
        actualizar.setOnAction(e -> {VentanaActualizar.formularioActualizar();});
        eliminar.setOnAction(e -> {VentanaEliminar.formularioEliminar();});

        //PARA AÑADIR LOS ESTILOS DEL CSS
        scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
    }

    public static void main(String[] args) {
        launch(args);
    }
}