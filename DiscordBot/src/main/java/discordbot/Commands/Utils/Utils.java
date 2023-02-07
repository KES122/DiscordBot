package discordbot.Commands.Utils;

import org.apache.commons.lang3.time.DurationFormatUtils;

public abstract class Utils {
    private static final String DURATION_FORMAT = "mm:ss";
    private static final String DURATION_FORMAT_LONG = "HH:mm:ss";
    private static final String DURATION_FORMAT_ULTRA = "y лет M м. d д. HH:mm:ss";

    public static String formatDuration(long duration) {
        return DurationFormatUtils.formatDuration(duration, DURATION_FORMAT);
    }

    public static String formatLongDuration(long duration) {
        return DurationFormatUtils.formatDuration(duration, DURATION_FORMAT_LONG);
    }
    public static String formatUltraLongDuration(long duration){
        return DurationFormatUtils.formatDuration(duration, DURATION_FORMAT_ULTRA);
    }
}