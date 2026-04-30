package cr.ac.ucenfotec.baron.federico;

import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.Usuario;
import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModificarUsuarioForm {

    private Service service;

    public ModificarUsuarioForm(Service service) {
        this.service = service;
    }

    public void mostrar(Stage stage) {
        Label titulo = new Label("Modificar Usuario");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ComboBox<String> cmbUsuario = new ComboBox<>();
        for (Usuario u : service.listarUsuarios()) {
            cmbUsuario.getItems().add(u.getNombre() + " - " + u.getId());
        }
        cmbUsuario.setPromptText("Seleccione usuario");

        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nuevo nombre");

        TextField txtCorreo = new TextField();
        txtCorreo.setPromptText("Nuevo correo");

        Button btnModificar = new Button("Modificar");
        Button btnVolver = new Button("Volver");

        btnVolver.setOnAction(e -> stage.close());

        btnModificar.setOnAction(e -> {
            int index = cmbUsuario.getSelectionModel().getSelectedIndex();
            Usuario u = service.listarUsuarios().get(index);

            if (!txtNombre.getText().isEmpty()) u.setNombre(txtNombre.getText());
            if (!txtCorreo.getText().isEmpty()) u.setCorreoElectronico(txtCorreo.getText());

            new Alert(Alert.AlertType.INFORMATION, "Usuario modificado").show();
        });

        VBox vbox = new VBox(10, titulo, cmbUsuario, txtNombre, txtCorreo, btnModificar, btnVolver);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 300, 350);
        stage.setTitle("Modificar Usuario");
        stage.setScene(scene);
        stage.show();
    }
}