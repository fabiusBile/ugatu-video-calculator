package Presentation.Factories;

import Domain.Models.Cam;
import Presentation.Controllers.CamEditorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class CamEditorFactory {
    public static void createCamEditorWindow(String title, Cam camToEdit, Consumer<Cam> onSaveCallback) {
        try {
            var loader = new FXMLLoader(CamEditorFactory.class.getClassLoader().getResource("Views/CamEditor.fxml"));
            Parent root = loader.load();

            var controller = loader.<CamEditorController>getController();
            controller.setOnSaveCallback(onSaveCallback);
            controller.setCam(camToEdit);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
