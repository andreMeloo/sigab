package view.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminDisciplinas extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./disciplinas.fxml"));
        Scene cena = new Scene(root);

        primaryStage.setTitle("Disciplinas");
        primaryStage.setScene(cena);
        primaryStage.show();
    }
}
