package org.example;

import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
         * addListener() --> le pone un vigilante encima, cada vez que el valor del ComboBox cambia, ejecuta el código de dentro automáticamente.
         *
         */
        comboBox.valueProperty().addListener((observable, valoranterior, nuevovalor) -> {
            listaFiltrada.setPredicate(artista -> {if (nuevovalor == null ||
            nuevovalor.equals("Todos")) return true; return artista.getGenero().equalsIgnoreCase(nuevovalor);});
        });

//--------------------------------------------------------------------------------------------------------------------------------

        VBox vbox = new VBox(comboBox,tableView);
        Scene scene = new Scene(vbox, 402, 300);
        ventana2.setScene(scene);
        ventana2.show();

        //COMENTADO PARA EL EJERCICIO 25
        // LLAMA AL MÉTODO PARA CARGAR LOS ARTISTAS EN LA TABLE-VIEW, QUE ES PASADA POR PARÁMETRO
        //CargarDatosBBDD.cargarDatos(tableView);
    }


}
