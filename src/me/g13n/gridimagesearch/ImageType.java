package me.g13n.gridimagesearch;

public enum ImageType {
    ANY      ("any"),
    FACE     ("face"),
    PHOTO    ("photo"),
    CLIP_ART ("clipart");

    ImageType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static ImageType getEnum(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        for (ImageType it : values()) {
            if (value.equalsIgnoreCase(it.type)) {
                return it;
            }
        }

        throw new IllegalArgumentException();
    }

    private final String type;
}
