package view.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminTurmas extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./turmas.fxml"));
        Scene cena = new Scene(root);

        primaryStage.setTitle("Turmas");
        primaryStage.setScene(cena);
        primaryStage.show();
    }
}
