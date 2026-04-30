package cr.ac.ucenfotec.baron.federico;

import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.Coleccionista;
import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.Usuario;
import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SeleccionarModeradorForm {

    private Service service;

    public SeleccionarModeradorForm(Service service) {
        this.service = service;
    }

    public void mostrar(Stage stage) {
        Label titulo = new Label("Seleccionar Moderador");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ComboBox<String> cmbColeccionista = new ComboBox<>();
        for (Usuario u : service.listarUsuarios()) {
            if (u instanceof Coleccionista) {
                cmbColeccionista.getItems().add(u.getNombre() + " - " + u.getId());
            }
        }
        cmbColeccionista.setPromptText("Seleccione coleccionista");

        Button btnSeleccionar = new Button("Seleccionar como Moderador");
        Button btnVolver = new Button("Volver");

        btnVolver.setOnAction(e -> stage.close());

        btnSeleccionar.setOnAction(e -> {
            int index = cmbColeccionista.getSelectionModel().getSelectedIndex();
            service.seleccionarComoModerador(index);
            new Alert(Alert.AlertType.INFORMATION, "Moderador seleccionado exitosamente").show();
        });

        VBox vbox = new VBox(10, titulo, cmbColeccionista, btnSeleccionar, btnVolver);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 300, 300);
        stage.setTitle("Seleccionar Moderador");
        stage.setScene(scene);
        stage.show();
    }
}