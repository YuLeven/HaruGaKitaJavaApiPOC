package util;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-23
 */
public class Time {
    public static Integer millisecondsToHours(Integer ms) {
        return ms / 3600;
    }

    static String millisecondsToFormattedHourString(Integer ms) {
        return String.format("%02d:%02d", Time.millisecondsToHours(ms), 0);
    }
}
