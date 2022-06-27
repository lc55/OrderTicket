package com.lchao.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final long SECOND = 1000L;
    public static final long MINUTE = 60000L;
    public static final long HOUR = 3600000L;
    public static final long DAY = 86400000L;
    public static final long WEEK = 604800000L;

    // 默认时区
    private static Clock clock = Clock.systemDefaultZone();

    /**
     * 时间加分钟
     *
     * @param time
     * @param step
     * @return
     */
    public static String addMinute(String time, Integer step) {
        LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        return localTime.plusMinutes(step).toString();
    }

    /**
     * 分钟转成 00:00 格式
     *
     * @param minute
     * @return
     */
    public static String minuteToTime(Integer minute) {
        if (minute == 0) {
            return minute.toString();
        }
        if (minute < 60) {
            StringBuilder builder = new StringBuilder();
            if (minute < 10) {
                builder.append("0").append(minute);
            } else {
                builder.append(minute);
            }
            return "00:" + builder;
        }
        String[] time = new String[2];
        Integer hour = minute / 60;
        Integer min = minute % 60;
        if (hour < 10) {
            time[0] = "0" + hour;
        } else {
            time[0] = String.valueOf(hour);
        }

        if (min < 10) {
            time[1] = "0" + min;
        } else {
            time[1] = String.valueOf(min);
        }
        return String.join(":", time);
    }

    /**
     * 比较时间
     *
     * @param startTime
     * @param curTime
     * @return
     */
    public static Boolean checkTime(String startTime, String curTime) {
        LocalTime start = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime cur = LocalTime.parse(curTime, DateTimeFormatter.ofPattern("HH:mm"));
        return start.isBefore(cur);
    }

    /**
     * 是否为当天
     *
     * @return localDate
     */
    public static boolean isToday(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        return LocalDate.now().equals(date);
    }

    /**
     * 判断两个时间段有没有交集
     *
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */
    public static boolean isContainsDateTime(String start1, String end1, String start2, String end2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dt1 = LocalDateTime.parse(start1, formatter);
        LocalDateTime dt2 = LocalDateTime.parse(end1, formatter);
        LocalDateTime dt3 = LocalDateTime.parse(start2, formatter);
        LocalDateTime dt4 = LocalDateTime.parse(end2, formatter);
        return !(dt4.isBefore(dt1) || dt3.isAfter(dt2));
    }

    /*
        获取当前时间+8分钟的时间
     */
    public static String getNowAdd8() {
        LocalDateTime time = LocalDateTime.now().plusMinutes(8);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }

    /*
        获取当前时间戳
     */
    public static Long getTimeStamp() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static Boolean isBefore(String time) {
        LocalDate dateTime = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dateTime.isBefore(LocalDate.now());
    }

    public static String getEndDateTime(String startDate, String startTime, Integer consume) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parse = LocalDateTime.parse(startDate + " " + startTime, formatter);
        LocalDateTime time = parse.plusMinutes(consume.longValue());
        return time.format(formatter);
    }

    /**
     * 获取指定分钟之后的时间戳
     * @param afterMinute 分钟数
     * @return 时间戳
     */
    public static Long getAfterMinuteTimeStamp(Long afterMinute) {
        return GetAfterDayOfMillis(getTimeStamp(), clock, afterMinute);
    }

    public static Long GetAfterDayOfMillis(Long startTime, Clock clock, Long afterMinute) {
        return Instant.ofEpochMilli(startTime + afterMinute * MINUTE).atZone(clock.getZone()).toEpochSecond() * 1000;
    }

    public static void main(String[] args) {
        System.out.println(getAfterMinuteTimeStamp(30L));
    }
}
