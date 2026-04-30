package cr.ac.ucenfotec.baron.federico;
import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CategoriasForm {

    private Service service;

    public CategoriasForm(Service service) {
        this.service = service;
    }

    public void mostrar(Stage stage) {
        Label titulo = new Label("Gestión de Categorías");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField txtCategoria = new TextField();
        txtCategoria.setPromptText("Nombre de categoría");

        Button btnAgregar = new Button("Agregar Categoría");
        Button btnVolver = new Button("Volver");

        VBox listaView = new VBox(5);

        btnVolver.setOnAction(e -> stage.close());

        btnAgregar.setOnAction(e -> {
            String nombre = txtCategoria.getText();
            if (!nombre.isEmpty()) {
                service.agregarCategoria(nombre);
                listaView.getChildren().add(new Label("• " + nombre));
                txtCategoria.clear();
                new Alert(Alert.AlertType.INFORMATION, "Categoría agregada").show();
            }
        });

        VBox vbox = new VBox(10, titulo, txtCategoria, btnAgregar, listaView, btnVolver);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 300, 400);
        stage.setTitle("Categorías");
        stage.setScene(scene);
        stage.show();
    }
}