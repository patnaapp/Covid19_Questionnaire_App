package com.bih.nic.saathi;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bih.nic.saathi.DataBaseHelper.DataBaseHelper;
import com.bih.nic.saathi.Model.ImageItem;

import java.util.ArrayList;


public class GridViewAdapter extends ArrayAdapter {
	private Context context;
	private int layoutResourceId;
	private ArrayList data = new ArrayList();
	ArrayList<String> Name = new ArrayList<>();
	DataBaseHelper dataBaseHelper;
     String USERID="";
     int size=0;
	public GridViewAdapter(Context context, int layoutResourceId, ArrayList data, ArrayList<String> name) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
		this.Name = name;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.imageTitle = (TextView) row.findViewById(R.id.text);
			holder.image = (ImageView) row.findViewById(R.id.image);
			holder.count = (TextView) row.findViewById(R.id.count);
			holder.count.setVisibility(View.GONE);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}
		try{
			USERID = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("USER_ID", "");
		}catch (Exception e){
			e.printStackTrace();
		}
        dataBaseHelper =new DataBaseHelper(getContext());
		size=dataBaseHelper.getNumberTotalOfPendingData(USERID);
		ImageItem item = (ImageItem) data.get(position);
		holder.imageTitle.setText(Name.get(position));
		holder.image.setImageBitmap(item.getImage());
		if (position == 2) {

			holder.count.setVisibility(View.VISIBLE);
			holder.count.setText(String.valueOf(size));
		} else {

			holder.count.setVisibility(View.GONE);
		}

		return row;
	}

	static class ViewHolder {
		TextView imageTitle,count;
		ImageView image;
	}

	@Override
	public boolean isEnabled(int position) {
		if(position == 2) {

			return false;
		}
		return true;
	}
}