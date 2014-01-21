package me.g13n.gridimagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class GridImageSearchFilterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_image_search_filter);

        try {
            ImageSearchOptions options = (ImageSearchOptions) getIntent().getParcelableExtra("options");

            Spinner spImageSize = (Spinner) findViewById(R.id.spImageSize);
            ImageSize is = options.getImageSize();
            for (ImageSize i : ImageSize.values()) {
                if (i.equals(is)) {
                    spImageSize.setSelection(i.ordinal(), true);
                }
            }

            Spinner spColorFilter = (Spinner) findViewById(R.id.spColorFilter);
            ColorFilter cf = options.getColorFilter();
            for (ColorFilter c : ColorFilter.values()) {
                if (c.equals(cf)) {
                    spColorFilter.setSelection(c.ordinal(), true);
                }
            }

            Spinner spImageType = (Spinner) findViewById(R.id.spImageType);
            ImageType it = options.getImageType();
            for (ImageType i : ImageType.values()) {
                if (i.equals(it)) {
                    spImageType.setSelection(i.ordinal(), true);
                }
            }

            EditText etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
            etSiteFilter.setText(options.getSiteFilter());
        } catch (NullPointerException ex) {
            Toast.makeText(getBaseContext(), R.string.error_null, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        ImageSearchOptions options = (ImageSearchOptions) getIntent().getParcelableExtra("options");

        try {
            Spinner spImageSize = (Spinner) findViewById(R.id.spImageSize);
            options.setImageSize(ImageSize.getEnum(spImageSize.getSelectedItem().toString()));

            Spinner spColorFilter = (Spinner) findViewById(R.id.spColorFilter);
            options.setColorFilter(ColorFilter.getEnum(spColorFilter.getSelectedItem().toString()));

            Spinner spImageType = (Spinner) findViewById(R.id.spImageType);
            options.setImageType(ImageType.getEnum(spImageType.getSelectedItem().toString()));
        } catch (NullPointerException ex) {
            Toast.makeText(getBaseContext(), R.string.error_null, Toast.LENGTH_SHORT).show();
        }

        EditText etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
        options.setSiteFilter(etSiteFilter.getText().toString());

        Intent data = new Intent();
        data.putExtra("options", (Parcelable) options);
        setResult(RESULT_OK, data);
        finish();
    }
}
