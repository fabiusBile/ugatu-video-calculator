package Presentation.Controllers;

import Domain.Models.Cam;
import Domain.Models.CodecType;
import Presentation.Formatters.IntegerFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class CamEditorController implements Initializable {

    @FXML
    public TextField camName;
    @FXML
    public TextField resolutionH;
    @FXML
    public TextField resolutionW;
    @FXML
    public ChoiceBox<Integer> frameRate;
    @FXML
    public ChoiceBox<CodecType> codec;
    @FXML
    public CheckBox isColor;

    private Cam cam;

    private Consumer<Cam> onSaveCallback;
    private ObservableList<Integer> frameRates = FXCollections.observableArrayList(
            1,
            10,
            20,
            25,
            30,
            60
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resolutionH.setTextFormatter(new IntegerFormatter());
        resolutionW.setTextFormatter(new IntegerFormatter());
        frameRate.setItems(frameRates);
        codec.setItems(FXCollections.observableArrayList(CodecType.values()));
    }

    public void setCam(Cam cam) {
        this.cam = cam;
        camName.setText(cam.getName());
        resolutionW.setText(Integer.toString(cam.getResolution().getWidth()));
        resolutionH.setText(Integer.toString(cam.getResolution().getHeight()));
        frameRate.setValue(cam.getFrameRate());
        codec.setValue(cam.getCodecType());
        isColor.setSelected(cam.isColor());
    }

    public void setOnSaveCallback(Consumer<Cam> callback) {
        onSaveCallback = callback;
    }

    public void save(ActionEvent actionEvent) {

        cam.setName(camName.getText());
        cam.getResolution().setHeight(Integer.parseInt(resolutionH.getText()));
        cam.getResolution().setWidth(Integer.parseInt(resolutionW.getText()));
        cam.setFrameRate(frameRate.getValue());
        cam.setCodecType(codec.getValue());
        cam.setIsColor(isColor.isSelected());

        if (onSaveCallback != null) {
            onSaveCallback.accept(cam);
        }

        closeForm(actionEvent);
    }

    public void cancel(ActionEvent actionEvent) {
        closeForm(actionEvent);
    }

    private void closeForm(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
