package com.example.myapplication1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.Arrays;

public class FullImageViewActivity extends AppCompatActivity {

    ViewPager display;
    ImagePagerAdapter imagePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //add back button on the action bar when browsing images in full view
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view);

        //Setting up ViewPager
        display = (ViewPager) findViewById(R.id.display);
        imagePagerAdapter = new ImagePagerAdapter(this, Data.images);
        display.setAdapter(imagePagerAdapter);

        //Get which image to display from previous activity using the extra passed in
        display.setCurrentItem(getIntent().getIntExtra("pos", 0));
    }

    //Private inner class PagerAdapter for our ViewPager in this activity
    private class ImagePagerAdapter extends PagerAdapter {
        Context context;
        private int[] images;

        LayoutInflater inflater;

        public ImagePagerAdapter(Context context, int[] images) {
            this.context = context;
            this.images = Data.images;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        //Handles swiping of images (Entry)
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = inflater.inflate(R.layout.activity_full_image, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            Glide.with(FullImageViewActivity.this).load(images[position]).into(imageView);

            container.addView(itemView);

            return itemView;
        }

        //Handles swiping of images (Exit)
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout)object);
        }
    }
}
