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
import javafx.scene.control.*;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Spinner<Integer> monthsSelector;
    @FXML
    public Spinner<Integer> weeksSelector;
    @FXML
    public Spinner<Integer> daysSelector;
    @FXML
    public Label storageSizeField;
    @FXML
    public Button addCamBtn;
    @FXML
    private ListView<Cam> camsListView;

    private ObservableList<Cam> camsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        monthsSelector.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60));
        weeksSelector.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24));
        daysSelector.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 365));

        for (var selector : new Spinner[]{monthsSelector, weeksSelector, daysSelector}) {
            selector.getEditor().setTextFormatter(new IntegerFormatter());
        }

        camsListView.setItems(camsList);
        camsListView.setCellFactory(listView -> new CamCellVM());
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

        return Duration.ofDays(daysSelector.getValue())
                .plusDays(weeksSelector.getValue()*7)
                .plusDays(monthsSelector.getValue()*30);
    }

    private void openCamEditor() {
        CamEditorFactory.createCamEditorWindow("Добавление камеры",
                new Cam(String.format("Камера №%d", camsList.size() + 1)),
                (cam) -> camsList.add(cam));
    }
}
