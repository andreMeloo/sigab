package view;

import controller.AdminController;
import controller.ProfessorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.vo.AlunoVO;
import model.vo.DisciplinaVO;
import model.vo.ProfessorVO;
import model.vo.TurmaVO;
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
        controller.carregaTabelas(professor);

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
        controller.carregaTabelas();

        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Administrador");
        primaryStage.setScene(cena);

        
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
    public static void telaAdicionaTurma(UsuarioVO usuario, TurmaVO turma) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Admin/adicionarTurma.fxml"));
        Parent root = (Parent) floader.load();
        AdminController controller = floader.<AdminController>getController();
        if (turma.getId() == null) {
            controller.carregaChoiceBox();
            controller.codigoTurma();
            Scene cena = new Scene(root);
            primaryStage.setTitle("SIGAB - Administrador");
            primaryStage.setScene(cena);
        } else {
            controller.carregaChoiceBox();
            controller.carregaEdicaoTurma(turma);
            Scene cena = new Scene(root);
            primaryStage.setTitle("SIGAB - Administrador");
            primaryStage.setScene(cena);
            controller.setTurmaEdita(turma);
        }
    }

    public static void telaAdicionaAluno(UsuarioVO usuario, AlunoVO aluno) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Admin/adicionarAluno.fxml"));
        Parent root = (Parent) floader.load();
        AdminController controller = floader.<AdminController>getController();
        if (aluno.getId() == null) {
            controller.matricula();
            Scene cena = new Scene(root);
            primaryStage.setTitle("SIGAB - Administrador");
            primaryStage.setScene(cena);
        } else {
            controller.carregaEdicaoAluno(aluno);
            Scene cena = new Scene(root);
            primaryStage.setTitle("SIGAB - Administrador");
            primaryStage.setScene(cena);
            controller.setAlunoEdit(aluno);
        }
        
        controller.setUser(usuario);
    }

    public static void telaAdicionaProfessor(UsuarioVO usuario, ProfessorVO professor) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Admin/adicionarProfessor.fxml"));
        Parent root = (Parent) floader.load();
        AdminController controller = floader.<AdminController>getController();
        if (professor.getId() == null) {
            Scene cena = new Scene(root);
            primaryStage.setTitle("SIGAB - Administrador");
            primaryStage.setScene(cena);
        } else {
            controller.carregaEdicaoProfessor(professor);
            Scene cena = new Scene(root);
            primaryStage.setTitle("SIGAB - Administrador");
            primaryStage.setScene(cena);
            controller.setProfessorEdit(professor);
        }
        
        controller.setUser(usuario);
    }

    public static void telaAdicionaDisciplina(UsuarioVO usuario, DisciplinaVO disciplina) throws Exception {
        FXMLLoader floader = new FXMLLoader(Telas.class.getResource("Admin/adicionarDisciplina.fxml"));
        Parent root = (Parent) floader.load();
        AdminController controller = floader.<AdminController>getController();
        if (disciplina.getId() == null) {
            controller.codigoDiscilpina();
            Scene cena = new Scene(root);
            primaryStage.setTitle("SIGAB - Administrador");
            primaryStage.setScene(cena);
        } else {
            controller.carregaEdicaoDisciplina(disciplina);
            Scene cena = new Scene(root);
            primaryStage.setTitle("SIGAB - Administrador");
            primaryStage.setScene(cena);
            controller.setDisciplinaEdit(disciplina);
        }
        
        controller.setUser(usuario);
    }

    public static void main(String ... args) {
        launch();
    }
    

}
