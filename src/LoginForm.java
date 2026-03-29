import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LoginForm extends Application {

    @Override
    public void start(Stage stage) {

        Label titulo = new Label("Inicio de Sesión");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label lblCorreo = new Label("Correo:");
        TextField txtCorreo = new TextField();

        Label lblContrasena = new Label("Contraseña:");
        PasswordField txtContrasena = new PasswordField();

        Button btnLogin = new Button("Iniciar Sesión");

        VBox vbox = new VBox(10, titulo, lblCorreo, txtCorreo, lblContrasena, txtContrasena, btnLogin);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-alignment: center;");

        Scene scene = new Scene(vbox, 300, 250);
        stage.setTitle("Login - Subastas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}