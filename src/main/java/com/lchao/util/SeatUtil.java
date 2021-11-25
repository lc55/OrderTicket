package com.lchao.util;

import java.util.ArrayList;
import java.util.List;

public class SeatUtil {

    /**
     * 生成座位
     *
     * @param leftSeat
     * @param rightSeat
     * @param rowSeat
     * @return
     */
    public static List<String> generateSeats(Integer leftSeat, Integer rightSeat, Integer rowSeat) {
        List<String> seatList = new ArrayList<>();
        String[] letter = {"A", "B", "C", "D", "E"};
        int row = leftSeat + rightSeat;
        for (int i = 1; i <= rowSeat; i++) {
            for (int j = 1; j <= row; j++) {
                seatList.add(i + letter[j - 1]);
            }
        }
        return seatList;
    }

    /**
     * 生成带有车厢座位的座位号
     *
     * @param carriageNumber
     * @param seatNumber
     * @return
     */
    public static String generateSeatNumber(int carriageNumber, String seatNumber) {

        StringBuilder builder = new StringBuilder();
        if (carriageNumber < 10) {
            builder.append(0).append(carriageNumber).append("车");
        } else {
            builder.append(carriageNumber).append("车");
        }
        builder.append(" ");
        builder.append(seatNumber);
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateSeats(1, 1, 5));
    }
}
