package org.example;

import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Optional;

public class VentanaTabla {
    // TABLE-VIEW ACCESIBLE DESDE CUALQUIER CLASE. CADA FILA DE LA TABLA
    // REPRESENTA UN OBJETO ARTISTA
    public static TableView<Artista> tableView;

    // METODO PARA CREAR UNA TABLE-VIEW
    public static void abrirVentanaTabla() {
        // VENTANA NUEVA
        Stage ventana2 = new Stage();
        // TITULO DE LA VENTANA
        ventana2.setTitle("Lista de Artistas");
        tableView = new TableView<>();

        // Definir columnas; NOMBRE - GENERO MUSICAL
        TableColumn<Artista, String> nombreCol = new TableColumn<>("Nombre");
        TableColumn<Artista, String> generoCol = new TableColumn<>("Genero Musical");

        /**
         * Asignar las propiedades del modelo a las columnas
         * SE DICE A CADA COLUMNA DE QUE CAMPO DEL OBJETO ARTISTA DEBE LEER LOS DATOS
         * PropertyValueFactory --> BUSCA AUTOMATICAMENTE LOS MÉTODOS getNombre() y getGenero() DEL MODELO
         */
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        generoCol.setCellValueFactory(new PropertyValueFactory<>("genero"));

        // ANCHO MÍNIMO DE CADA COLUMNA
        nombreCol.setMinWidth(200);
        generoCol.setMinWidth(200);

        // SE AÑADEN A LA TABLA
        tableView.getColumns().addAll(nombreCol, generoCol);

// --------------------------------------------------------------------------------------------------------------------
        //AÑADIDO PARA EL EJERCICIO 25
        //CARGAR DATOS
        ObservableList<Artista> listaOriginal = CargarDatosBBDD.cargarArtistas();

        //PARA ENVOLVER LA LISTA PRINCIPAL
        // p -> true =  AL INICIO SE MUESTRAN TODOS
        FilteredList<Artista> listaFiltrada = new FilteredList<>(listaOriginal, p -> true);

        //MOSTRAMOS LA TABLA FILTRADA
        tableView.setItems(listaFiltrada);

        //COMBOBOX PARA SELECCIONAR LOS GENEROS A MOSTRAR
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().add("Todos");

        // RECORRO LA LISTA ORIGINAL Y AÑADO LOS GÉNEROS AL COMBOBOX SIN REPETIR
        for (Artista artista : listaOriginal){
            if (!comboBox.getItems().contains(artista.getGenero())){
                comboBox.getItems().add(artista.getGenero());
            }
        }

        // VALOR POR DEFECTO
        comboBox.setValue("Todos");

        /**
         * valueProperty() --> representa el valor seleccionado del ComboBox como algo "vivo"
         * addListener() --> le pone un vigilante encima, cada vez que el valor del ComboBox cambia,
         *                   ejecuta el código de dentro automáticamente
         */
        comboBox.valueProperty().addListener((observable, valoranterior, nuevovalor) -> {
            /**
             * setPredicate --> condición tiene que cumplir un artista para mostrarse o no en la tabla,
             *                  recorre uno por uno todos los artistas de la lista y por cada uno decide
             */
            listaFiltrada.setPredicate(artista -> {if (nuevovalor == null ||
            nuevovalor.equals("Todos")) return true; return artista.getGenero().equalsIgnoreCase(nuevovalor);});
        });

//--------------------------------------------------------------------------------------------------------------------------------

        // AÑADIDO PARA EL EJERCICIO 26, ELIMINAR DESDE TABLE-VIEW


        Button botonEliminar = new Button("Eliminar");
        botonEliminar.setOnAction(event -> {
            // Obtiene el artista seleccionado en la tabla
            Artista artistaSeleccionado = tableView.getSelectionModel().getSelectedItem();
            if(artistaSeleccionado == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("No ha seleccionado ningún artista.");
                alert.showAndWait();
            } else {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmación");
                alerta.setContentText("¿Desea eliminar el artista de la Base de Datos?");

                //Para capturar que se pulsa aceptar o cancelar
                Optional<ButtonType> resultado = alerta.showAndWait();
                //Si se pulsa algún boton y es el de ok elimina de la base de datos
                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    //LLAMA AL MÉTODO PARA ELIMINAR AL ARTISTA, PASANDO POR PARÁMETRO EL NOMBRE
                    EliminarArtista.borrarArtista(artistaSeleccionado.getNombre());
                    // TAMBIÉN BORRO EL ARTISTA DE LA TABLA ORIGINAL, PUES SINO SEGUIRÍA MOSTRANDOLO
                    listaOriginal.remove(artistaSeleccionado);
                }
            }
        });

//----------------------------------------------------------------------------------------------------------------------

       //VBox vbox = new VBox(comboBox,tableView);
        BorderPane caja = new BorderPane();
        caja.setTop(comboBox);
        BorderPane.setAlignment(comboBox, Pos.TOP_RIGHT);
        caja.setCenter(tableView);
        caja.setBottom(botonEliminar);
        BorderPane.setAlignment(botonEliminar, Pos.BOTTOM_RIGHT);
        Scene scene = new Scene(caja, 402, 300);
        ventana2.setScene(scene);
        ventana2.show();

        //COMENTADO PARA EL EJERCICIO 25
        // LLAMA AL MÉTODO PARA CARGAR LOS ARTISTAS EN LA TABLE-VIEW, QUE ES PASADA POR PARÁMETRO
        //CargarDatosBBDD.cargarDatos(tableView);
    }


}
