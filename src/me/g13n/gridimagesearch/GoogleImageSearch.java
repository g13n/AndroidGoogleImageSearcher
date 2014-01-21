package me.g13n.gridimagesearch;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleImageSearch {
    public static void search(String query, String filters,
    		int offset, final ImageSearchResultListener listener) {
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(formatQuery(query, filters, offset), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    int status = (int) response.getInt("responseStatus");
                    if (status != 200) {
                        listener.failure(new HttpException());
                    }
                    listener.success(response.getJSONObject("responseData").getJSONArray("results"));
                } catch (JSONException ex) {
                    listener.failure(ex);
                }
            }
        });
    }

    protected static String formatQuery(String query, String filters, int offset) {
        return GOOGLE_IMAGE_SEARCH_URL + "?q=" + query + "&v=1.0&" + filters +
        		(offset > 0 ? "&start=" + offset : "");
    }

    protected static final String GOOGLE_IMAGE_SEARCH_URL =
            "https://ajax.googleapis.com/ajax/services/search/images";
}
