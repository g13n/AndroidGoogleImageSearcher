package me.g13n.gridimagesearch;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ImagesAdapter extends ArrayAdapter<Image> {
	public ImagesAdapter(Context context, ArrayList<Image> objects) {
		super(context, R.layout.item_image, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Image img = getItem(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image, null);
		}
		
		ImageView ivResult = (ImageView) convertView.findViewById(R.id.imgResult);
		ImageLoader.getInstance().displayImage(img.getUrl(), ivResult);
		return convertView;
	}
}
