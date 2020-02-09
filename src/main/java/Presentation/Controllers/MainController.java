package Presentation.Controllers;

import Domain.Models.Cam;
import Domain.Services.VideoSizeCalculator;
import Presentation.Factories.CamEditorFactory;
import Presentation.Formatters.IntegerFormatter;
import Presentation.Utils.StorageSizePresenter;
import Presentation.ViewModels.CamCellVM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Spinner<Integer> minutesSelector;
    @FXML
    public Spinner<Integer> hoursSelector;
    @FXML
    public Spinner<Integer> daysSelector;
    @FXML
    public Label storageSizeField;
    @FXML
    private ListView<Cam> camsListView;

    private ObservableList<Cam> camsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        minutesSelector.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60));
        hoursSelector.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24));
        daysSelector.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 365));

        for (var selector : new Spinner[]{minutesSelector, hoursSelector, daysSelector}) {
            selector.getEditor().setTextFormatter(new IntegerFormatter());
        }

        camsListView.setItems(camsList);
        camsListView.setCellFactory(s -> new CamCellVM());
    }

    public void addCam(ActionEvent actionEvent) {
        openCamEditor();
    }

    public void calculateStorage() {
        var storageTime = calculateStorageTime();
        var storageSize = VideoSizeCalculator.CalculateVideoSize(camsList, storageTime);
        storageSizeField.setText(StorageSizePresenter.getHumanReadableSize(storageSize));
    }

    private Duration calculateStorageTime() {
        var duration = Duration.ofDays(daysSelector.getValue())
                .plusHours(hoursSelector.getValue())
                .plusMinutes(minutesSelector.getValue());

        return duration;
    }

    private void openCamEditor() {
        CamEditorFactory.createCamEditorWindow("Добавление камеры",
                new Cam(String.format("Камера №%d", camsList.size() + 1)),
                (cam) -> camsList.add(cam));
    }
}
