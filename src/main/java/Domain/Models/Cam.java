package Domain.Models;


import Domain.Services.CodecCompressRatio;

/**
 * Камера.
 */
public class Cam {

    private String name;
    private Resolution resolution;
    private Integer frameRate;
    private CodecType codecType;
    private boolean isColor;

    public Cam(String name) {
        this.name = name;
        this.resolution = new Resolution();
        this.frameRate = 1;
        this.codecType = CodecType.MPEG4;
        this.isColor = true;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public Integer getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(Integer frameRate) {
        this.frameRate = frameRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CodecType getCodecType() {
        return codecType;
    }

    public void setCodecType(CodecType codecType) {
        this.codecType = codecType;
    }

    public long getBitrate() {
        return Math.round(resolution.getWidth() *
                resolution.getHeight() *
                frameRate *
                CodecCompressRatio.GetCompressRation(codecType) *
                (isColor ? 3 : 1));
    }

    public boolean isColor() {
        return isColor;
    }

    public void setIsColor(boolean color) {
        isColor = color;
    }
}

