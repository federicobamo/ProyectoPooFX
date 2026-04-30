package cr.ac.ucenfotec.baron.federico;
import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.Usuario;
import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ActivarUsuarioForm {

    private Service service;

    public ActivarUsuarioForm(Service service) {
        this.service = service;
    }

    public void mostrar(Stage stage) {
        Label titulo = new Label("Activar/Inactivar Usuario");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ComboBox<String> cmbUsuario = new ComboBox<>();
        for (Usuario u : service.listarUsuarios()) {
            cmbUsuario.getItems().add(u.getNombre() + " - Activo: " + u.isActivo());
        }
        cmbUsuario.setPromptText("Seleccione usuario");

        Button btnActivar = new Button("Activar");
        Button btnInactivar = new Button("Inactivar");
        Button btnVolver = new Button("Volver");

        btnVolver.setOnAction(e -> stage.close());

        btnActivar.setOnAction(e -> {
            int index = cmbUsuario.getSelectionModel().getSelectedIndex();
            Usuario u = service.listarUsuarios().get(index);
            u.setActivo(true);
            new Alert(Alert.AlertType.INFORMATION, "Usuario activado").show();
        });

        btnInactivar.setOnAction(e -> {
            int index = cmbUsuario.getSelectionModel().getSelectedIndex();
            Usuario u = service.listarUsuarios().get(index);
            u.setActivo(false);
            new Alert(Alert.AlertType.INFORMATION, "Usuario inactivado").show();
        });

        VBox vbox = new VBox(10, titulo, cmbUsuario, btnActivar, btnInactivar, btnVolver);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 300, 300);
        stage.setTitle("Activar/Inactivar");
        stage.setScene(scene);
        stage.show();
    }
}