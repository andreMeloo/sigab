package view.Alunos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class perfil extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./perfil.fxml"));
        Scene cena = new Scene(root);

        primaryStage.setTitle("Diário");
        primaryStage.setScene(cena);
        primaryStage.show();
    }
}