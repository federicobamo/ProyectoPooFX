import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegistroForm extends Application {

        @Override
        public void start(Stage stage) {

            Label titulo = new Label("Registro de usuarios");
            titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            javafx.scene.control.Label lblNombre = new javafx.scene.control.Label("nombre:");
            TextField txtNombre = new TextField();

            javafx.scene.control.Label lblId = new javafx.scene.control.Label("Identificación:");
            TextField txtId = new TextField();

            javafx.scene.control.Label lblcorreElectronico = new Label("Correo:");
            TextField txtCorreoElectronico = new TextField();

            javafx.scene.control.Label lblContrasena = new Label("Contraseña:");
            TextField txtContrasena = new TextField();

            Label lblFechaNacimiento = new Label("Fecha de Nacimiento (AAAA-MM-DD):");
            TextField txtFechaNacimiento = new TextField();

            Button btnRegistro = new Button("Registrarse");

            VBox vbox = new VBox(10, titulo, lblNombre, txtNombre, lblId, txtId,
                    lblcorreElectronico, txtCorreoElectronico,
                    lblContrasena, txtContrasena,
                    lblFechaNacimiento, txtFechaNacimiento, btnRegistro);
            vbox.setPadding(new Insets(20));
            vbox.setStyle("-fx-alignment: center;");
            Scene scene = new Scene(vbox, 300, 250);
            stage.setTitle("Login - Subastas");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            Application.launch(RegistroForm.class, args);
        }
    }

