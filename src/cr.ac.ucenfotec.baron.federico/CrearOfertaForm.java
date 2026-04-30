package cr.ac.ucenfotec.baron.federico;
import cr.ac.ucenfotec.baron.federico.bl.entities.Subasta;
import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.Usuario;
import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CrearOfertaForm {

    private Service service;
    private Usuario usuarioActual;

    public CrearOfertaForm(Service service, Usuario usuarioActual) {
        this.service = service;
        this.usuarioActual = usuarioActual;
    }

    public void mostrar(Stage stage) {
        Label titulo = new Label("Crear Oferta");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ComboBox<String> cmbSubasta = new ComboBox<>();
        for (int i = 0; i < service.listarSubastas().size(); i++) {
            cmbSubasta.getItems().add("Subasta #" + (i+1) + " - " +
                    service.listarSubastas().get(i).getCreador().getNombre());
        }
        cmbSubasta.setPromptText("Seleccione una subasta");

        TextField txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio ofertado");

        Button btnOfertar = new Button("Ofertar");
        Button btnVolver = new Button("Volver");

        btnVolver.setOnAction(e -> stage.close());

        btnOfertar.setOnAction(e -> {
            try {
                int index = cmbSubasta.getSelectionModel().getSelectedIndex();
                Subasta subasta = service.listarSubastas().get(index);
                double precio = Double.parseDouble(txtPrecio.getText());

                if (!service.subastaActiva(subasta)) {
                    new Alert(Alert.AlertType.ERROR, "La subasta no está activa").show();
                    return;
                }

                if (!service.puedeOfertar(usuarioActual)) {
                    new Alert(Alert.AlertType.ERROR, "Solo coleccionistas pueden ofertar").show();
                    return;
                }

                if (!service.ofertaValida(precio, subasta.getPrecioMinimo())) {
                    new Alert(Alert.AlertType.ERROR, "El precio debe ser mayor al mínimo").show();
                    return;
                }

                service.registrarOfertas(subasta, usuarioActual, precio);
                new Alert(Alert.AlertType.INFORMATION, "Oferta registrada exitosamente").show();

            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Error: " + ex.getMessage()).show();
            }
        });

        VBox vbox = new VBox(10, titulo, cmbSubasta, txtPrecio, btnOfertar, btnVolver);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 350, 300);
        stage.setTitle("Crear Oferta");
        stage.setScene(scene);
        stage.show();
    }
}