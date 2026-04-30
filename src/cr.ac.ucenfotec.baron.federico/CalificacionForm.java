package cr.ac.ucenfotec.baron.federico;
import cr.ac.ucenfotec.baron.federico.bl.entities.Subasta;
import cr.ac.ucenfotec.baron.federico.bl.entities.EstadoSubasta;
import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

    public class CalificacionForm {

        private Service service;

        public CalificacionForm(Service service) {
            this.service = service;
        }

        public void mostrar(Stage stage) {
            Label titulo = new Label("Calificar");
            titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            ComboBox<String> cmbSubasta = new ComboBox<>();
            for (Subasta s : service.listarSubastas()) {
                if (s.getEstado() == EstadoSubasta.ADJUDICADA) {
                    cmbSubasta.getItems().add("Subasta de: " + s.getCreador().getNombre());
                }
            }
            cmbSubasta.setPromptText("Seleccione subasta");

            Label lblCalificacion = new Label("Calificación (1 al 5):");
            ComboBox<Integer> cmbCalificacion = new ComboBox<>();
            cmbCalificacion.getItems().addAll(1, 2, 3, 4, 5);
            cmbCalificacion.setPromptText("Seleccione calificación");

            Button btnCalificar = new Button("Calificar");
            Button btnVolver = new Button("Volver");

            btnVolver.setOnAction(e -> stage.close());

            btnCalificar.setOnAction(e -> {
                if (cmbSubasta.getValue() == null || cmbCalificacion.getValue() == null) {
                    new Alert(Alert.AlertType.ERROR, "Seleccione subasta y calificación").show();
                    return;
                }

                int index = cmbSubasta.getSelectionModel().getSelectedIndex();
                Subasta s = service.listarSubastas().stream()
                        .filter(sub -> sub.getEstado() == EstadoSubasta.ADJUDICADA)
                        .collect(java.util.stream.Collectors.toList()).get(index);

                service.calificarUsuario(s.getCreador(), cmbCalificacion.getValue());
                new Alert(Alert.AlertType.INFORMATION, "Calificación registrada: " + cmbCalificacion.getValue()).show();
            });

            VBox vbox = new VBox(10, titulo, cmbSubasta, lblCalificacion, cmbCalificacion, btnCalificar, btnVolver);
            vbox.setPadding(new Insets(20));

            Scene scene = new Scene(vbox, 300, 350);
            stage.setTitle("Calificación");
            stage.setScene(scene);
            stage.show();
        }
    }

