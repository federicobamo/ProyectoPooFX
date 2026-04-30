package cr.ac.ucenfotec.baron.federico;
import cr.ac.ucenfotec.baron.federico.bl.entities.Subasta;
import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListarSubastasForm {

    private Service service;

    public ListarSubastasForm(Service service) {
        this.service = service;
    }

    public void mostrar(Stage stage) {
        Label titulo = new Label("Lista de Subastas");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().add(titulo);

        for (Subasta s : service.listarSubastas()) {
            service.subastaVencio(s);
            Label lbl = new Label(
                    "Creador: " + s.getCreador().getNombre() +
                            " | Precio: $" + s.getPrecioMinimo() +
                            " | Estado: " + s.getEstado() +
                            " | Tiempo: " + s.calcularTiempoRestante()
            );
            vbox.getChildren().add(lbl);
        }

        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> stage.close());
        vbox.getChildren().add(btnVolver);

        ScrollPane scroll = new ScrollPane(vbox);
        Scene scene = new Scene(scroll, 500, 400);
        stage.setTitle("Subastas");
        stage.setScene(scene);
        stage.show();
    }
}