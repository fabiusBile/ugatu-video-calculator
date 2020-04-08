package UnitTests;

import Domain.Models.Cam;
import Domain.Services.VideoSizeCalculator;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.util.List;

public class VideoSizeCalculatorTest {

    @Test
    public void calculateVideoSize() {
        var cam = new Cam("test");
        cam.setFrameRate(10);
        cam.getResolution().setHeight(1);

        cam.getResolution().setWidth(2);

        var result  = VideoSizeCalculator.CalculateVideoSize(List.of(cam), Duration.ofSeconds(10));
        Assert.assertEquals(300,result);
    }
}