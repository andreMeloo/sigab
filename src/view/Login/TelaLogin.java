package view.Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TelaLogin extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
		
		Scene cena = new Scene(root);
		primaryStage.setTitle("SIGAB");
		primaryStage.resizableProperty().setValue(Boolean.FALSE);
		primaryStage.getIcons().add(new Image("view/VE/logo.png"));
		primaryStage.setScene(cena);
		primaryStage.show();
		
	}

}
