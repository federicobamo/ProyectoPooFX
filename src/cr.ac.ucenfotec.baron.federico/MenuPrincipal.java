package cr.ac.ucenfotec.baron.federico;

import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.Usuario;
import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class MenuPrincipal {

    private Service service;
    private Usuario usuario;

    public MenuPrincipal(Service service, Usuario usuario) {
        this.service = service;
        this.usuario = usuario;
    }

    public void mostrar(Stage stage) {

            Label titulo = new Label("Bienvenido " + usuario.getNombre());
            titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            Button btnRegistrarUsuario = new Button("Registrar Usuario");
            Button btnListarUsuarios = new Button("Listar Usuarios");
            Button btnCrearSubasta = new Button("Crear Subasta");
            Button btnListarSubastas = new Button("Listar Subastas");
            Button btnCrearOferta = new Button("Crear Oferta");
            Button btnModificarUsuario = new Button("Modificar Usuario");
            Button btnActivarUsuario = new Button("Activar/Inactivar Usuario");
            Button btnCategorias = new Button("Gestión de Categorías");
            Button btnSeleccionarModerador = new Button("Seleccionar Moderador");
            Button btnAdjudicacion = new Button("Adjudicación");
            Button btnMisSubastas = new Button("Mis Subastas");
            Button btnCalificacion = new Button("Calificar");
            Button btnSalir = new Button("Salir");


        btnRegistrarUsuario.setOnAction(e -> {
            new RegistroUsuarioForm(service).mostrar(new Stage());
            });
        btnListarUsuarios.setOnAction(e -> {
            new ListarUsuariosForm(service).mostrar(new Stage());
            });
        btnCrearSubasta.setOnAction(e -> {
            new CrearSubastaForm(service, usuario).mostrar(new Stage());
            });
        btnListarSubastas.setOnAction(e -> {
            new ListarSubastasForm(service).mostrar(new Stage());
            });
        btnCrearOferta.setOnAction(e -> {
            new CrearOfertaForm(service, usuario).mostrar(new Stage());
            });
        btnModificarUsuario.setOnAction(e -> {
            new ModificarUsuarioForm(service).mostrar(new Stage());
            });
        btnActivarUsuario.setOnAction(e -> {
            new ActivarUsuarioForm(service).mostrar(new Stage());
            });
        btnCategorias.setOnAction(e -> {
            new CategoriasForm(service).mostrar(new Stage());
            });
        btnSeleccionarModerador.setOnAction(e -> {
            new SeleccionarModeradorForm(service).mostrar(new Stage());
            });
        btnAdjudicacion.setOnAction(e -> {
            new AdjudicacionForm(service).mostrar(new Stage()); });

        btnCalificacion.setOnAction(e -> {
            new CalificacionForm(service).mostrar(new Stage()); });

        btnMisSubastas.setOnAction(e -> {
            new MisSubastasForm(service, usuario).mostrar(new Stage()); });

        btnSalir.setOnAction(e -> stage.close());

        VBox vbox = new VBox(10, titulo, btnRegistrarUsuario, btnListarUsuarios,
                btnCrearSubasta, btnListarSubastas, btnCrearOferta,
                btnModificarUsuario, btnActivarUsuario, btnCategorias,
                btnSeleccionarModerador, btnAdjudicacion, btnMisSubastas,
                btnCalificacion, btnSalir);

            vbox.setPadding(new Insets(20));
            vbox.setStyle("-fx-alignment: center;");

            ScrollPane scroll = new ScrollPane(vbox);
            Scene scene = new Scene(scroll, 300, 500);
            stage.setTitle("Menú Principal");
            stage.setScene(scene);
            stage.show();
        }
    }

