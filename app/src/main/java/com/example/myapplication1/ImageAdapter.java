package com.example.myapplication1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
//Image adapter class
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public int[] images;
    public ImageAdapter(Context c){
        mContext = c;
        images = Data.images;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position)
    {
        return images[position];
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }
    //method to insert and scale the pictures
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        if (convertView == null)
        {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(700, 700));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        Glide.with(mContext).load(images[position]).into(imageView);
        return imageView;
    }
}
