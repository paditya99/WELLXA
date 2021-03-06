package com.example.wellxa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class sliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public sliderAdapter(Context context){
        this.context=context;
    }

    public int[] slide_images={
            R.drawable.bmiscreen,
            R.drawable.caloriescreen,
            R.drawable.alertscreen
    };
    public String[] slide_headings={
          "Calculate your BMI and get suggestions" ,
          "Keep track of your daily calorie intake",
          "Get Vaccination alert on definite time"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView image=(ImageView)view.findViewById(R.id.slide_image);
        TextView text=(TextView)view.findViewById(R.id.heading);
        image.setImageResource(slide_images[position]);
        text.setText(slide_headings[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
