package org.mag.mow.enums;

import java.util.Arrays;

public enum Orientation {

    NORTH("N"), EST("E"), WEST("W"), SOUTH("S");

    private String s;

     Orientation(String s) {
        this.s = s;
    }

    public static Orientation getOrientation(String s) {
         return Arrays.stream(values()).filter(v->v.equals(s)).findAny().orElse(NORTH);
    }
}
