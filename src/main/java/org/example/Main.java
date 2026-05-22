package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
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
        Scene scene = new Scene(root,300,250);
        primaryStage.setScene(scene);
        primaryStage.show();

        insertar.setOnAction(e -> {VentanaInsertar.formularioInsertar();});
        leer.setOnAction(e -> {VentanaTabla.abrirVentanaTabla();});
        actualizar.setOnAction(e -> {VentanaActualizar.formularioActualizar();});
        eliminar.setOnAction(e -> {VentanaEliminar.formularioEliminar();});

    }

    public static void main(String[] args) {
        launch(args);
    }

}