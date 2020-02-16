package UgatuVideoCalculator;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Views/Main.fxml"));

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Калькулятор видеонаблюдения");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
