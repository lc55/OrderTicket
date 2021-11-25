package com.lchao.util;

public class Distance {

    public static String[] getCoordinate(String longitude, String latitude) {
        String[] coordinate = new String[2];
        coordinate[0] = longitude;
        coordinate[1] = latitude;
        return coordinate;
    }

    public static Double getDistance(String[] x, String[] y) {
        Double x_2 = Math.pow((Double.parseDouble(x[0]) - Double.parseDouble(y[0])), 2);
        Double y_2 = Math.pow((Double.parseDouble(x[1]) - Double.parseDouble(y[1])), 2);
        return Math.sqrt(x_2 + y_2);
    }
}
