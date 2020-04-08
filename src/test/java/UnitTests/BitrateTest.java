package UnitTests;

import Domain.Models.Cam;
import Domain.Models.CodecType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BitrateTest {
    private class BitrateTestCase{
        public Cam cam;
        public int expectedBitrate;

        public BitrateTestCase(Cam cam, int expectedBitrate) {
            this.cam = cam;
            this.expectedBitrate = expectedBitrate;
        }
    }

    BitrateTestCase[] testCases;

    @Before
    public void setup(){
        var cam0 = new Cam("cam");
        cam0.getResolution().setWidth(10);
        cam0.getResolution().setHeight(10);
        cam0.setFrameRate(24);
        cam0.setCodecType(CodecType.h264);
        cam0.setIsColor(true);

        var cam1 = new Cam("cam");
        cam1.getResolution().setWidth(10);
        cam1.getResolution().setHeight(10);
        cam1.setFrameRate(24);
        cam1.setCodecType(CodecType.h264);
        cam1.setIsColor(false);

        var cam2 = new Cam("cam");
        cam2.getResolution().setWidth(10);
        cam2.getResolution().setHeight(10);
        cam2.setFrameRate(24);
        cam2.setCodecType(CodecType.MPEG4);
        cam2.setIsColor(true);

        var cam3 = new Cam("cam");
        cam3.getResolution().setWidth(10);
        cam3.getResolution().setHeight(10);
        cam3.setFrameRate(24);
        cam3.setCodecType(CodecType.MPEG4);
        cam3.setIsColor(false);

        var bitrate = Math.round(cam3.getBitrate());
        Assert.assertEquals(1200, bitrate);

        testCases = new BitrateTestCase[]{
                new BitrateTestCase(cam0, 2160),
                new BitrateTestCase(cam1, 720),
                new BitrateTestCase(cam2, 3600),
                new BitrateTestCase(cam3, 1200)
        };
    }

    @Test
    public void bitrate_calculated_successfully(){
        for (BitrateTestCase testCase : testCases) {
            var cam = testCase.cam;
            var bitrate = Math.round(cam.getBitrate());
            Assert.assertEquals(testCase.expectedBitrate,bitrate);
        }
    }
}
