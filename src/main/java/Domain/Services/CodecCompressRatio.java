package Domain.Services;

import Domain.Models.CodecType;

import java.util.HashMap;
import java.util.Map;

public class CodecCompressRatio {

    private static Map<CodecType, Double> compressionRates = new HashMap<>() {
        {
            put(CodecType.MPEG4, 0.5);
            put(CodecType.h264, 0.3);
            put(CodecType.h265, 0.1);
        }
    };

    public static double GetCompressRation(CodecType codecType) {
        return compressionRates.get(codecType);
    }
}
