
package cr.ac.ucenfotec.baron.federico;

import cr.ac.ucenfotec.baron.federico.bl.entities.Subasta;
import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.Usuario;
import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

    public class MisSubastasForm {

        private Service service;
        private Usuario usuario;

        public MisSubastasForm(Service service, Usuario usuario) {
            this.service = service;
            this.usuario = usuario;
        }

        public void mostrar(Stage stage) {
            Label titulo = new Label("Mis Subastas");
            titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            VBox vbox = new VBox(10);
            vbox.setPadding(new Insets(20));
            vbox.getChildren().add(titulo);

            for (Subasta s : service.listarSubastas()) {
                if (s.getCreador().getId() == usuario.getId()) {
                    Label lblSubasta = new Label();
                    lblSubasta.setText(
                            "Objeto: " + (s.getListaObjetos().isEmpty() ? "N/A" : s.getListaObjetos().get(0).getNombreObjeto()) +
                                    " | Estado: " + s.getEstado() +
                                    " | Tiempo: " + s.calcularTiempoRestante()
                    );

                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                        lblSubasta.setText(
                                "Objeto: " + (s.getListaObjetos().isEmpty() ? "N/A" : s.getListaObjetos().get(0).getNombreObjeto()) +
                                        " | Estado: " + s.getEstado() +
                                        " | Tiempo: " + s.calcularTiempoRestante()
                        );
                    }));
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();

                    vbox.getChildren().add(lblSubasta);
                }
            }

            Button btnVolver = new Button("Volver");
            btnVolver.setOnAction(e -> stage.close());
            vbox.getChildren().add(btnVolver);

            ScrollPane scroll = new ScrollPane(vbox);
            Scene scene = new Scene(scroll, 500, 400);
            stage.setTitle("Mis Subastas");
            stage.setScene(scene);
            stage.show();
        }
    }


