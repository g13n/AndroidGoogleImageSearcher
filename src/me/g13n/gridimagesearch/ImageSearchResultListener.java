package me.g13n.gridimagesearch;

import org.json.JSONArray;

public interface ImageSearchResultListener {
    public void success(JSONArray results);
    public void failure(Exception ex);
}
