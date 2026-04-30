package cr.ac.ucenfotec.baron.federico;
import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

    public class ListarUsuariosForm {

        private Service service;

        public ListarUsuariosForm(Service service) {
            this.service = service;
        }

        public void mostrar(Stage stage) {
            Label titulo = new Label("Lista de Usuarios");
            titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            VBox vbox = new VBox(10);
            vbox.setPadding(new Insets(20));
            vbox.getChildren().add(titulo);

            for (Usuario u : service.listarUsuarios()) {
                String tipo = u instanceof Moderador ? "Moderador" :
                        u instanceof Vendedor ? "Vendedor" : "Coleccionista";
                Label lbl = new Label(u.getNombre() + " - " + tipo + " - " + u.getCorreoElectronico());
                vbox.getChildren().add(lbl);
            }

            Button btnVolver = new Button("Volver");
            btnVolver.setOnAction(e -> stage.close());
            vbox.getChildren().add(btnVolver);

            Scene scene = new Scene(vbox, 400, 400);
            stage.setTitle("Usuarios");
            stage.setScene(scene);
            stage.show();
        }
    }

