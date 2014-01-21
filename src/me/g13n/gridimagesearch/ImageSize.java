package me.g13n.gridimagesearch;

import android.util.Log;

public enum ImageSize {
    ANY    ("any"),
    SMALL  ("small"),
    MEDIUM ("medium"),
    LARGE  ("large");

    ImageSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size;
    }

    public static ImageSize getEnum(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        Log.v("ImageSize", "getEnum(" + value + ")");

        for (ImageSize is : values()) {
            if (value.equalsIgnoreCase(is.size)) {
                return is;
            }
        }

        throw new IllegalArgumentException();
    }

    private final String size;
}
