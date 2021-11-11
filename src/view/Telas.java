package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
    public static void telaAlunoPrincipal() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("Alunos/principalAlunoTurmas.fxml"));

        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Aluno");
        primaryStage.setScene(cena);
    }



    // ==> Telas do usuário Professor
    public static void telaProfessorPrincipal() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("Professor/principalProfTurmas.fxml"));

        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Professor");
        primaryStage.setScene(cena);
    }



    // ==> Telas do usuário Admin
    public static void telaAdminPrincipal() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("Admin/principalAdminTurmas.fxml"));

        Scene cena = new Scene(root);
        primaryStage.setTitle("SIGAB - Administrador");
        primaryStage.setScene(cena);
    }

    public static void main(String ... args) {
        launch();
    }
    

}
