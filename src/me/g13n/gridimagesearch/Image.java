package me.g13n.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Image {
	public static Image fromJson(JSONObject jsonObject) {
		Image img = new Image();
		
		try {
			img.setContent(jsonObject.getString("content"));
			img.setHeight(jsonObject.getInt("height"));
			img.setOriginalContextUrl(jsonObject.getString("originalContextUrl"));
			img.setTbHeight(jsonObject.getInt("tbHeight"));
			img.setTbUrl(jsonObject.getString("tbUrl"));
			img.setTbWidth(jsonObject.getInt("tbWidth"));
			img.setTitle(jsonObject.getString("title"));
			img.setUrl(jsonObject.getString("url"));
			img.setWidth(jsonObject.getInt("width"));
		} catch (JSONException ex) {
			return null;
		}
		
		return img;
	}

	public static ArrayList<Image> fromJson(JSONArray jsonArray) {
		ArrayList<Image> images = new ArrayList<Image>(jsonArray.length());
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = null;
			try {
				jsonObject = jsonArray.getJSONObject(i);
			} catch (JSONException ex) {
				continue;
			}
			
			if (jsonObject != null) {
				Image img = Image.fromJson(jsonObject);
				if (img != null) {
					images.add(img);
				}
			}
		}
		
		return images;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getOriginalContextUrl() {
		return originalContextUrl;
	}

	public void setOriginalContextUrl(String originalContextUrl) {
		this.originalContextUrl = originalContextUrl;
	}

	public int getTbHeight() {
		return tbHeight;
	}

	public void setTbHeight(int tbHeight) {
		this.tbHeight = tbHeight;
	}

	public String getTbUrl() {
		return tbUrl;
	}

	public void setTbUrl(String tbUrl) {
		this.tbUrl = tbUrl;
	}

	public int getTbWidth() {
		return tbWidth;
	}

	public void setTbWidth(int tbWidth) {
		this.tbWidth = tbWidth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	private String content;
	private int height;
	private String originalContextUrl;
	private int tbHeight;
	private String tbUrl;
	private int tbWidth;
	private String title;
	private String url;
	private int width;
}
