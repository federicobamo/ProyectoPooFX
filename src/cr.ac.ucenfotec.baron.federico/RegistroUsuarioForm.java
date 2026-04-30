package cr.ac.ucenfotec.baron.federico;
import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.Usuario;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class RegistroUsuarioForm {

    private Service service;

    public RegistroUsuarioForm(Service service) {
        this.service = service;
    }

    public void mostrar(Stage stage) {
        Label titulo = new Label("Registro de Usuario");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");

        TextField txtId = new TextField();
        txtId.setPromptText("Identificación");

        TextField txtCorreo = new TextField();
        txtCorreo.setPromptText("Correo electrónico");

        PasswordField txtContrasena = new PasswordField();
        txtContrasena.setPromptText("Contraseña");

        TextField txtFecha = new TextField();
        txtFecha.setPromptText("Fecha nacimiento (AAAA-MM-DD)");

        ComboBox<String> cmbTipo = new ComboBox<>();
        cmbTipo.getItems().addAll("Vendedor", "Coleccionista");
        cmbTipo.setPromptText("Tipo de usuario");

        Button btnRegistrar = new Button("Registrar");
        Button btnVolver = new Button("Volver");

        btnVolver.setOnAction(e -> stage.close());

        btnRegistrar.setOnAction(e -> {

            try {
                String nombre = txtNombre.getText();
                int id = Integer.parseInt(txtId.getText());
                String correo = txtCorreo.getText();
                String contrasena = txtContrasena.getText();
                LocalDate fecha = LocalDate.parse(txtFecha.getText());
                String tipo = cmbTipo.getValue();

                if (tipo.equals("Vendedor")) {
                    Usuario u = service.registrarVendedor(nombre, id, contrasena, correo, fecha, 0.0, "");
                    if (u == null) {
                        new Alert(Alert.AlertType.ERROR, "Debe ser mayor de edad").show();
                        return;
                    }
                } else {
                    Usuario u = service.registrarColeccionista(nombre, id, contrasena, correo, fecha, 0.0, "");
                    if (u == null) {
                        new Alert(Alert.AlertType.ERROR, "Debe ser mayor de edad").show();
                        return;
                    }
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Usuario registrado exitosamente");
                alert.show();

            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error: " + ex.getMessage());
                alert.show();
            }
        });

        VBox vbox = new VBox(10, titulo, txtNombre, txtId, txtCorreo,
                txtContrasena, txtFecha, cmbTipo, btnRegistrar, btnVolver);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 300, 450);
        stage.setTitle("Registro");
        stage.setScene(scene);
        stage.show();
    }
}
