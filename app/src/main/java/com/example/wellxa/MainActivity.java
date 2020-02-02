package com.example.wellxa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout dotLayout;
    private sliderAdapter Adapter;
    private TextView[] mdots;
    private Button backbutt;
    private Button nextbutt;
    private int currPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       backbutt=(Button)findViewById(R.id.backbutton);
        nextbutt=(Button)findViewById(R.id.nextbutton);
        viewPager=(ViewPager)findViewById(R.id.viewPager1);
        dotLayout=(LinearLayout)findViewById(R.id.dotsPageViewer);

        Adapter=new sliderAdapter(this);
        viewPager.setAdapter(Adapter);
        adddotsIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);

        nextbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(currPage+1);
                if((currPage+1)==3){
                    nextbutt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(getApplicationContext(),signupActivity.class);
                            startActivity(i);
                        }
                    });
                }
            }
        });
        backbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(currPage-1);
            }
        });

    }

    public void adddotsIndicator(int pos){
        mdots=new TextView[3];
        dotLayout.removeAllViews();
        for(int i=0;i<mdots.length;i++){
            mdots[i]=new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            dotLayout.addView(mdots[i]);

        }
        if(mdots.length>0){
            mdots[pos].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }
   ViewPager.OnPageChangeListener viewListener =new ViewPager.OnPageChangeListener() {
       @Override
       public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

       }

       @Override
       public void onPageSelected(int position) {
            adddotsIndicator(position);
            currPage=position;
            if(position==0){
                nextbutt.setEnabled(true);
                backbutt.setEnabled(false);
                backbutt.setVisibility(View.INVISIBLE);
                nextbutt.setText("Next");
                backbutt.setText("");
            }
            else if(position==(mdots.length-1)){
                nextbutt.setEnabled(true);
                backbutt.setEnabled(true);
                backbutt.setVisibility(View.VISIBLE);
                nextbutt.setText("Finish");
                backbutt.setText("Back");
            }
            else{
                nextbutt.setEnabled(true);
                backbutt.setEnabled(true);
                backbutt.setVisibility(View.VISIBLE);
                nextbutt.setText("Next");
                backbutt.setText("Back");
            }
       }

       @Override
       public void onPageScrollStateChanged(int position) {

       }
   };


}
