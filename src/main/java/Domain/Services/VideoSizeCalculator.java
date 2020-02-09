package Domain.Services;

import Domain.Models.Cam;

import java.time.Duration;
import java.util.Collection;

/**
 * Расчитывает объем видео для камер.
 */
public class VideoSizeCalculator {
    public static long CalculateVideoSize(Collection<Cam> cams, Duration duration) {
        return cams.stream()
                .mapToLong(c -> c.getBitrate() * duration.getSeconds())
                .sum();
    }
}
