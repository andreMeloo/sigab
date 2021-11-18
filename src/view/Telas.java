package view;

import controller.AdminController;
import controller.ProfessorController;
// import controller.ProfessorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.vo.UsuarioVO;

public class Telas extends Application {
    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    public static void setPrimaryStage(Stage primaryStage) {
        Telas.primaryStage = primaryStage;
    }

    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        primaryStage.setTitle("SIGAB");
		primaryStage.getIcons().add(new Image("view/VE/logo.png"));
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.show();
        telaLogin();
    }

    // ==> Tela de Login (Principal)
    public static void telaLogin() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("login/TelaLogin.fxml"));

        Scene cena = new Scene(root);
        primaryStage.setScene(cena);
    }



    // ==> Telas do usuário Aluno
    public static void telaInicialAluno(UsuarioVO aluno) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Alunos/AlunoTurmas.fxml"));
        Parent root = (Parent) floader.load();
        

        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Aluno");
        primaryStage.setScene(cena);
    }



    // ==> Telas do usuário Professor
    public static void telaInicialProfessor(UsuarioVO professor) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Professor/ProfTurmas.fxml"));
        Parent root = (Parent) floader.load();
        ProfessorController controller = floader.<ProfessorController>getController();
        controller.carregaTabelas();


        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Professor");
        primaryStage.setScene(cena);
        
    }



    // ==> Telas do usuário Admin
    public static void telaInicialAdmin(UsuarioVO usuario) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Admin/turmas.fxml"));
        Parent root = (Parent) floader.load();
        AdminController controller = floader.<AdminController>getController();

        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Administrador");
        primaryStage.setScene(cena);

        controller.carregaTabelas();
        controller.setUser(usuario);
    }

    public static void telaProfessores(UsuarioVO usuario) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Admin/professores.fxml"));
        Parent root = (Parent) floader.load();
        AdminController controller = floader.<AdminController>getController();

        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Administrador");
        primaryStage.setScene(cena);

        controller.carregaTabelas();
        controller.setUser(usuario);
    }

    public static void telaDisciplinas(UsuarioVO usuario) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Admin/disciplinas.fxml"));
        Parent root = (Parent) floader.load();
        AdminController controller = floader.<AdminController>getController();

        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Administrador");
        primaryStage.setScene(cena);

        controller.carregaTabelas();
        controller.setUser(usuario);
    }

    public static void telaAlunos(UsuarioVO usuario) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Admin/alunos.fxml"));
        Parent root = (Parent) floader.load();
        AdminController controller = floader.<AdminController>getController();
        controller.carregaTabelas();

        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Administrador");
        primaryStage.setScene(cena);

        
        controller.setUser(usuario);
    }

            // -- Telas de cadastro --
    public static void telaAdicionaTurma(UsuarioVO usuario) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Admin/adicionarTurma.fxml"));
        Parent root = (Parent) floader.load();
        AdminController controller = floader.<AdminController>getController();
        controller.codigoTurma();

        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Administrador");
        primaryStage.setScene(cena);

        controller.carregaChoiceBox();
        controller.setUser(usuario);
    }

    public static void telaAdicionaAluno(UsuarioVO usuario) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Admin/adicionarAluno.fxml"));
        Parent root = (Parent) floader.load();
        AdminController controller = floader.<AdminController>getController();
        controller.matricula();

        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Administrador");
        primaryStage.setScene(cena);

        
        controller.setUser(usuario);
    }

    public static void telaAdicionaProfessor(UsuarioVO usuario) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Admin/adicionarProfessor.fxml"));
        Parent root = (Parent) floader.load();
        AdminController controller = floader.<AdminController>getController();

        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Administrador");
        primaryStage.setScene(cena);

        
        controller.setUser(usuario);
    }

    public static void telaAdicionaDisciplina(UsuarioVO usuario) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Admin/adicionarDisciplina.fxml"));
        Parent root = (Parent) floader.load();
        AdminController controller = floader.<AdminController>getController();
        controller.codigoDiscilpina();

        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Administrador");
        primaryStage.setScene(cena);

        
        controller.setUser(usuario);
    }

    public static void main(String ... args) {
        launch();
    }
    

}
