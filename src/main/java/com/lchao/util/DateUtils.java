package com.lchao.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final String yyyy_MM = "yyyy-MM";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

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
     * 通过分钟计算天数
     *
     * @param duration
     * @return
     */
    public static Integer getRequireAddDays(Integer duration) {
        return duration / (24 * 60);
    }

    /**
     * 日期加指定天数
     *
     * @param days
     * @return
     */
    public static String addDate(String dateStr, Integer days) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        LocalDate localDate = date.plusDays(days);
        return localDate.toString();
    }

    /**
     * 格式化日期为 yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(dateTime);
    }

    /**
     * 获取当天时间
     *
     * @return
     */
    public static String getToday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(LocalDateTime.now());
    }

    /**
     * 获取自定的日期格式
     *
     * @return
     */
    public static String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return formatter.format(LocalDateTime.now());
    }

    /**
     * 只留日期去掉时间
     *
     * @param dateTime
     * @return
     */
    public static String dateOrTime(String dateTime, String dateOrTime) {
        String[] s = dateTime.split(" ");
        if ("date".equals(dateOrTime)) {
            return s[0];
        } else if ("time".equals(dateOrTime)) {
            return s[1];
        } else {
            return "";
        }
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

    /**
     * 给不规范的时间格式化成 01:01
     *
     * @param time
     * @return
     */
    public static String formatTime(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        if (hour < 10 && split[0].length() != 2) {
            split[0] = 0 + split[0];
        }
        int minute = Integer.parseInt(split[1]);
        if (minute < 10 && split[1].length() != 2) {
            split[1] = 0 + split[1];
        }
        return split[0] + ":" + split[1];
    }

    public static boolean isContainsDate(String date2date, String date) {
        String[] s = date2date.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(s[0], formatter);
        LocalDate date2 = LocalDate.parse(s[1], formatter);
        LocalDate date3 = LocalDate.parse(date, formatter);
        return date3.isBefore(date1) || date3.isAfter(date2);
    }

    /*
        获取当前时间+8分钟的时间
     */
    public static String getNowAdd8(){
        LocalDateTime time = LocalDateTime.now().plusMinutes(8);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }

    /*
        获取当前时间戳
     */
    public static Long getTimeStamp(){
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static Boolean isBefore(String time){
        LocalDate dateTime = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dateTime.isBefore(LocalDate.now());
    }

    public static String getEndDateTime(String startDate,String startTime,Integer consume){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parse = LocalDateTime.parse(startDate + " " + startTime, formatter);
        LocalDateTime time = parse.plusMinutes(consume.longValue());
        return time.format(formatter);
    }
    public static void main(String[] args) {
//        System.out.println(checkTime("17:12", "17:12"));
//        System.out.println(addMinute("23:20", Integer.parseInt("50")));
//        System.out.println(isBefore("2021-09-26"));
//        System.out.println(getTimeStamp());
//        System.out.println(getEndDateTime("2021-09-28","08:30",30));
        System.out.println(isContainsDateTime("2021-09-28 16:21", "2021-09-28 16:25","2021-09-28 17:22", "2021-09-28 17:25"));
    }
}
