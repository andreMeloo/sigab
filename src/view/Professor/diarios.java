package view.Professor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class diarios extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./diarios.fxml"));
        Scene cena = new Scene(root);

        primaryStage.setTitle("Diário");
        primaryStage.setScene(cena);
        primaryStage.show();
    }
}