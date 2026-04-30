package cr.ac.ucenfotec.baron.federico;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.Usuario;

public class LoginForm extends Application {

    @Override
    public void start(Stage stage) {

        Service service = new Service();

        Label titulo = new Label("Inicio de Sesión");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label lblCorreo = new Label("Correo:");
        TextField txtCorreo = new TextField();

        Label lblContrasena = new Label("Contraseña:");
        PasswordField txtContrasena = new PasswordField();

        Button btnLogin = new Button("Iniciar Sesión");

        btnLogin.setOnAction(e -> {
            String correo = txtCorreo.getText();
            String contrasena = txtContrasena.getText();

            Usuario usuario = service.inicarSesion(correo, contrasena);

            if (usuario == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Credenciales incorrectas");
                alert.show();
            } else {
                MenuPrincipal menu = new MenuPrincipal(service, usuario);
                menu.mostrar(stage);

            }
        });

        VBox vbox = new VBox(10, titulo, lblCorreo, txtCorreo, lblContrasena, txtContrasena, btnLogin);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-alignment: center;");

        Scene scene = new Scene(vbox, 300, 250);
        stage.setTitle("Login - Subastas");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        System.setProperty("javafx.verbose", "true");
        System.out.println("Iniciando...");
        LoginForm.launch(LoginForm.class, args);
    }
}