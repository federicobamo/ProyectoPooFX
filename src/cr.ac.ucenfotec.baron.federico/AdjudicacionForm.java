package cr.ac.ucenfotec.baron.federico;
import cr.ac.ucenfotec.baron.federico.bl.entities.Subasta;
import cr.ac.ucenfotec.baron.federico.bl.entities.EstadoSubasta;
import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

    public class AdjudicacionForm {

        private Service service;

        public AdjudicacionForm(Service service) {
            this.service = service;
        }

        public void mostrar(Stage stage) {

            Label titulo = new Label("Adjudicación de Subastas");
            titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            ComboBox<String> cmbSubasta = new ComboBox<>();
            for (Subasta s : service.listarSubastas()) {
                if (s.getEstado() == EstadoSubasta.ADJUDICADA) {
                    cmbSubasta.getItems().add("Ganador: " + service.ofertaGanadora(s).getColeccionista().getNombre());
                }
            }
            cmbSubasta.setPromptText("Seleccione subasta adjudicada");

            Button btnAceptar = new Button("Aceptar Adjudicación");
            Button btnVolver = new Button("Volver");

            btnAceptar.setOnAction(e -> {

                try {
                    int index = cmbSubasta.getSelectionModel().getSelectedIndex();
                    Subasta s = service.listarSubastas().stream()
                            .filter(sub -> sub.getEstado() == EstadoSubasta.ADJUDICADA)
                            .collect(java.util.stream.Collectors.toList()).get(index);

                    service.subastaVencio(s);

                    new Alert(Alert.AlertType.INFORMATION,
                            "Orden generada!\nGanador: " + service.ofertaGanadora(s).getColeccionista().getNombre() +
                                    "\nPrecio: $" + service.ofertaGanadora(s).getprecioOferta()).show();

                } catch (Exception ex) {
                    new Alert(Alert.AlertType.ERROR, "Error: " + ex.getMessage()).show();
                }
            });

            btnVolver.setOnAction(e -> stage.close());

            VBox vbox = new VBox(10, titulo, cmbSubasta, btnAceptar, btnVolver);
            vbox.setPadding(new Insets(20));

            Scene scene = new Scene(vbox, 350, 300);
            stage.setTitle("Adjudicación");
            stage.setScene(scene);
            stage.show();
        }
    }

