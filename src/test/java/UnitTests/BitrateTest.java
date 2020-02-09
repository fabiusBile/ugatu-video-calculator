package UnitTests;

import Domain.Models.Cam;
import Domain.Models.CodecType;
import org.junit.Assert;
import org.junit.Test;

public class BitrateTest {
    @Test
    public void bitrateColorH264CalculatedSuccessfully() {
        var cam = new Cam("cam");
        cam.getResolution().setWidth(10);
        cam.getResolution().setHeight(10);
        cam.setFrameRate(24);
        cam.setCodecType(CodecType.h264);
        cam.setIsColor(true);

        var bitrate = Math.round(cam.getBitrate());
        Assert.assertEquals(2160, bitrate);
    }

    @Test
    public void bitrateH264CalculatedSuccessfully() {
        var cam = new Cam("cam");
        cam.getResolution().setWidth(10);
        cam.getResolution().setHeight(10);
        cam.setFrameRate(24);
        cam.setCodecType(CodecType.h264);
        cam.setIsColor(false);

        var bitrate = Math.round(cam.getBitrate());
        Assert.assertEquals(720, bitrate);
    }

    @Test
    public void bitrateColorMpeg4CalculatedSuccessfully() {
        var cam = new Cam("cam");
        cam.getResolution().setWidth(10);
        cam.getResolution().setHeight(10);
        cam.setFrameRate(24);
        cam.setCodecType(CodecType.MPEG4);
        cam.setIsColor(true);

        var bitrate = Math.round(cam.getBitrate());
        Assert.assertEquals(3600, bitrate);
    }

    @Test
    public void bitrateMpeg4CalculatedSuccessfully() {
        var cam = new Cam("cam");
        cam.getResolution().setWidth(10);
        cam.getResolution().setHeight(10);
        cam.setFrameRate(24);
        cam.setCodecType(CodecType.MPEG4);
        cam.setIsColor(false);

        var bitrate = Math.round(cam.getBitrate());
        Assert.assertEquals(1200, bitrate);
    }
}
