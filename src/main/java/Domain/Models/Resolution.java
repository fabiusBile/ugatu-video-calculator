package Domain.Models;


/**
 * Разрешение камеры.
 */
public class Resolution {
    int Width;
    int Height;

    public Resolution(int width, int height) {
        Width = width;
        Height = height;
    }

    public Resolution() {
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }
}
