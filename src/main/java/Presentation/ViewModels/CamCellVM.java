package Presentation.ViewModels;

import Domain.Models.Cam;
import Presentation.Factories.CamEditorFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CamCellVM extends ListCell<Cam> {
    @FXML
    private Label camName;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button deleteCell;

    @FXML
    private Button editCell;

    @Override
    protected void updateItem(Cam cam, boolean empty) {
        super.updateItem(cam, empty);
        if (empty || cam == null) {

            setText(null);
            setGraphic(null);

        } else {
            initialize(cam);
        }
    }

    private void initialize(Cam cam) {
        var xmLLoader = new FXMLLoader(getClass().getResource("/Views/CamCell.fxml"));
        xmLLoader.setController(this);

        try {
            xmLLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        camName.setText(cam.getName());
        setText(null);
        setGraphic(anchorPane);

        deleteCell.setOnAction(e -> deleteCell(cam));
        editCell.setOnAction(e -> editCell(cam));
    }

    public void deleteCell(Cam camToDelete) {
        this.getListView().getItems().remove(camToDelete);
    }

    public void editCell(Cam camToEdit) {
        CamEditorFactory.createCamEditorWindow("Редактирование камеры", camToEdit, (c) -> this.getListView().refresh());
    }
}
