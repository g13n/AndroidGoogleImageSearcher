package me.g13n.gridimagesearch;

public enum ColorFilter {
    ANY              ("any"),
    RED              ("red"),
    GREEN            ("green"),
    BLUE             ("blue");

    ColorFilter(String color) {
        this.color= color;
    }

    @Override
    public String toString() {
        return color;
    }

    public static ColorFilter getEnum(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

    	for (ColorFilter cf : values()) {
            if (value.equalsIgnoreCase(cf.color)) {
                return cf;
            }
        }

        throw new IllegalArgumentException();
    }

    private final String color;
}
