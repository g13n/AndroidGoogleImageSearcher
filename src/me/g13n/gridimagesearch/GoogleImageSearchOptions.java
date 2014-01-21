package me.g13n.gridimagesearch;

import android.os.Parcel;
import android.os.Parcelable;

public class GoogleImageSearchOptions extends ImageSearchOptions implements Parcelable {
    public GoogleImageSearchOptions() {
        super();
    }

    public GoogleImageSearchOptions(Parcel in) {
        String v;

        v = in.readString();
        this.setImageSize(ImageSize.getEnum(v.replaceFirst("^.*=", "")));

        v = in.readString();
        this.setColorFilter(ColorFilter.getEnum(v.replaceFirst("^.*=", "")));

        v = in.readString();
        this.setImageType(ImageType.getEnum(v.replaceFirst("^.*=", "")));

        v = in.readString();
        this.setSiteFilter(v.replaceFirst("^.*=", ""));
    }

    @Override
    public String toString() {
        return "imgsz="          + this.getImageSize().toString()   + "&" +
               "imgcolor="       + this.getColorFilter().toString() + "&" +
               "imgtype="        + this.getImageType().toString()   + "&" +
                "ac_sitefilter=" + this.getSiteFilter();
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString("imgsz=" + this.getImageSize().toString());
        out.writeString("imgcolor=" + this.getColorFilter().toString());
        out.writeString("imgtype=" + this.getImageType().toString());
        out.writeString("as_sitesearch=" + this.getSiteFilter());
    }

    public static final Creator<GoogleImageSearchOptions> CREATOR =
            new Creator<GoogleImageSearchOptions>() {
        @Override
        public GoogleImageSearchOptions createFromParcel(Parcel in) {
            return new GoogleImageSearchOptions(in);
        }

        @Override
        public GoogleImageSearchOptions[] newArray(int size) {
            return new GoogleImageSearchOptions[size];
        }
    };
}
