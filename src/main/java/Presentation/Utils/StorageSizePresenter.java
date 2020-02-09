package Presentation.Utils;

public class StorageSizePresenter {
    public static String getHumanReadableSize(long sizeInBytes) {
        int unit = 1024;
        if (sizeInBytes < unit) return sizeInBytes + " B";
        int exp = (int) (Math.log(sizeInBytes) / Math.log(unit));
        var pre = "KMGTPE".charAt(exp - 1);
        return String.format("%.1f %sB", sizeInBytes / Math.pow(unit, exp), pre);
    }
}
