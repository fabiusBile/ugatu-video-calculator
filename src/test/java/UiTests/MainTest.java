package UiTests;

import Domain.Models.Cam;
import Domain.Models.CodecType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
public class MainTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        Parent mainNode = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    public void check_that_editor_opened(){
        clickOn("#addCamBtn");
        var popup = lookup("#camEditor");
        Assert.assertNotNull(popup);
    }

    @Test
    public void check_that_cam_added_successfully(){
        var cam = new Cam("cam");
        setCam(cam);
        Assert.assertEquals(1, lookup("#camsListView").queryListView().getItems().size());
    }

    @Test
    public void check_calculated_video_size(){
        var cams = new Cam[]{
          new Cam("cam",100,100,1, CodecType.h264,false),
          new Cam("cam2",100,100,1, CodecType.MPEG4,false),
          new Cam("cam3",100,100,10, CodecType.MPEG4,true),
          new Cam("cam4",100,100,25, CodecType.h264,true),
          new Cam("cam5",100,100,30, CodecType.h265,true),
          new Cam("cam5",100,100,60, CodecType.h265,true),
        };

        for (Cam cam : cams) {
            setCam(cam);
        }

        clickOn("#daysSelector");
        eraseText(1);
        write("1");
        clickOn("#calculateStorage");
        Assert.assertEquals("52.5 GB",lookup("#storageSizeField").queryLabeled().getText());
    }

    private void setCam(Cam cam){
        clickOn("#addCamBtn");
        setTextBoxValue("#resolutionW",cam.getResolution().getWidth());
        setTextBoxValue("#resolutionH",cam.getResolution().getHeight());

        setChoiceBoxValue("#frameRate",cam.getFrameRate());
        setChoiceBoxValue("#codec", cam.getCodecType());
        if(!cam.isColor()){
            clickOn("#isColor");
        }
        clickOn("#save");
    }

    private void setChoiceBoxValue(String id, Object value) {
        clickOn(id).clickOn(value.toString());
    }

    private void setTextBoxValue(String id, Object value){
        var text = lookup(id).queryTextInputControl();
        clickOn(text);
        eraseText(1);
        write(value.toString());
    }

}