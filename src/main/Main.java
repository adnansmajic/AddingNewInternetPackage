package main;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL fxmlUrl = getClass().getClassLoader().getResource("view/add_package.fxml");
	HBox root = FXMLLoader.<HBox>load(fxmlUrl);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("view/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
