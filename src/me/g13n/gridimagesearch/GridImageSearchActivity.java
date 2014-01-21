package me.g13n.gridimagesearch;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class GridImageSearchActivity extends Activity {

    public GridImageSearchActivity() {
        FILTER_REQUEST = 9;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_image_search);
        
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance().init(config);

        GridView gvResultImages = (GridView) findViewById(R.id.gvResultImages);
        gvResultImages.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void onLoadMore(int offset, int totalItemsCount) {
				Log.v("Scroll listener", "Loading more ... " + offset + ", " + totalItemsCount);
				makeQuery(0);
			}
		});

        options = new GoogleImageSearchOptions();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == this.FILTER_REQUEST) {
            options = (ImageSearchOptions) data.getParcelableExtra("options");
            makeQuery(0);
        }
    }

    protected void makeQuery(int offset) {
        EditText etSearchTerm = (EditText) findViewById(R.id.etSearchTerm);
        GoogleImageSearch.search(etSearchTerm.getText().toString(), options.toString(), offset,
                new ImageSearchResultListener() {
                    @Override
                    public void success(JSONArray results) {
                    	ArrayList<Image> arrayOfImages = Image.fromJson(results);
                    	ImagesAdapter imagesAdapter = new ImagesAdapter(getBaseContext(), arrayOfImages);
                    	GridView gvResultImages = (GridView) findViewById(R.id.gvResultImages);
                    	gvResultImages.setAdapter(imagesAdapter);
                    	currentOffset += arrayOfImages.size();
                    }

                    @Override
                    public void failure(Exception ex) {
                        Toast.makeText(getBaseContext(), R.string.error_null, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grid_image_search_filter, menu);
        return true;
    }

    public void onFilter(MenuItem menuItem) {
        Intent filterOptions = new Intent(this, GridImageSearchFilterActivity.class);
        filterOptions.putExtra("options", (Parcelable) options);
        startActivityForResult(filterOptions, FILTER_REQUEST);
    }
    
    public void onSearch(View v) {
    	makeQuery(0);
	}
    
    public void onLoadMore(View v) {
    	makeQuery(currentOffset);
	}
    
    private int currentOffset = 0;

    private ImageSearchOptions options;

    final int FILTER_REQUEST;
}
