package me.g13n.gridimagesearch;

public abstract class ImageSearchOptions {
    public ImageSearchOptions() {
        this.imageSize   = ImageSize.ANY;
        this.colorFilter = ColorFilter.ANY;
        this.imageType   = ImageType.ANY;
        this.siteFilter  = "";
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    public void setImageSize(ImageSize imageSize) {
        this.imageSize = imageSize;
    }

    public ColorFilter getColorFilter() {
        return colorFilter;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    public String getSiteFilter() {
        return siteFilter;
    }

    public void setSiteFilter(String siteFilter) {
        this.siteFilter = siteFilter;
    }

    private ImageSize   imageSize;
    private ColorFilter colorFilter;
    private ImageType   imageType;
    private String      siteFilter;
}
