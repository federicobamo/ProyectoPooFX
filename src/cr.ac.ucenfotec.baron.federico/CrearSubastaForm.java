package cr.ac.ucenfotec.baron.federico;

import cr.ac.ucenfotec.baron.federico.bl.entities.*;
import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.*;
import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CrearSubastaForm {

    private Service service;
    private Usuario usuarioActual;

    public CrearSubastaForm(Service service, Usuario usuarioActual) {
        this.service = service;
        this.usuarioActual = usuarioActual;
    }

    public void mostrar(Stage stage) {
        Label titulo = new Label("Crear Subasta");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField txtFecha = new TextField();
        txtFecha.setPromptText("Fecha vencimiento (AAAA-MM-DDTHH:MM)");

        TextField txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio mínimo");

        // Si es coleccionista, mostrar sus objetos
        ComboBox<String> cmbObjetos = new ComboBox<>();
        if (usuarioActual instanceof Coleccionista) {
            Coleccionista c = (Coleccionista) usuarioActual;
            for (Objeto o : c.getListaObjetos()) {
                cmbObjetos.getItems().add(o.getNombreObjeto());
            }
            cmbObjetos.setPromptText("Seleccione objeto");
        }

        // Si es vendedor, crear objeto nuevo
        TextField txtNombreObjeto = new TextField();
        txtNombreObjeto.setPromptText("Nombre del objeto");
        TextField txtDescripcion = new TextField();
        txtDescripcion.setPromptText("Descripción");
        TextField txtFechaCompra = new TextField();
        txtFechaCompra.setPromptText("Fecha compra (AAAA-MM-DD)");

        Button btnCrear = new Button("Crear Subasta");
        Button btnVolver = new Button("Volver");

        btnVolver.setOnAction(e -> stage.close());

        btnCrear.setOnAction(e -> {
            try {
                LocalDateTime fecha = LocalDateTime.parse(txtFecha.getText());
                double precio = Double.parseDouble(txtPrecio.getText());

                Subasta subasta = service.registroSubastas(fecha, usuarioActual, precio);

                if (usuarioActual instanceof Coleccionista) {
                    Coleccionista c = (Coleccionista) usuarioActual;
                    int index = cmbObjetos.getSelectionModel().getSelectedIndex();
                    subasta.getListaObjetos().add(c.getListaObjetos().get(index));
                } else {
                    Objeto obj = new Objeto(txtNombreObjeto.getText(), txtDescripcion.getText(),
                            EstadoObjeto.NUEVO, LocalDate.parse(txtFechaCompra.getText()));
                    subasta.getListaObjetos().add(obj);
                }

                new Alert(Alert.AlertType.INFORMATION, "Subasta creada exitosamente").show();

            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Error: " + ex.getMessage()).show();
            }
        });

        VBox vbox;
        if (usuarioActual instanceof Coleccionista) {
            vbox = new VBox(10, titulo, txtFecha, txtPrecio, cmbObjetos, btnCrear, btnVolver);
        } else {
            vbox = new VBox(10, titulo, txtFecha, txtPrecio, txtNombreObjeto, txtDescripcion, txtFechaCompra, btnCrear, btnVolver);
        }
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 300, 400);
        stage.setTitle("Crear Subasta");
        stage.setScene(scene);
        stage.show();
    }
}