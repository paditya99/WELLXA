package com.example.wellxa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class healthguide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthguide);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

   public class SectionsPagerAdapter extends FragmentPagerAdapter{

        public SectionsPagerAdapter(healthguide healthguide, FragmentManager fm){ super(fm); }

        public Fragment getItem(int position){
            switch (position){
                case 0:
                    Tab1Contacts tab1=new Tab1Contacts();
                    return tab1;
                case 1:
                    Tab2Chats tab2=new Tab2Chats();
                    return tab2;
                case 2:
                    Tab3Online tab3=new Tab3Online();
                    return tab3;
                default:
                    return null;


            }

        }
        @Override
       public  int getCount(){
            return 3;
        }
        @Override
       public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "General";
                case 1:
                    return "Exercise";
                case 2:
                    return "Emergency";
            }
            return null;
        }
   }

    public void onexitpressed(){
        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
        alertdialogbuilder.setTitle("Confirm Exit");
        alertdialogbuilder.setIcon(R.drawable.ic_exit);
        alertdialogbuilder.setMessage("Are you sure you want to exit?");
        alertdialogbuilder.setCancelable(false);
        alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                finish();
            }
        });
        alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog=alertdialogbuilder.create();
        alertDialog.show();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Intent i=new Intent(getApplicationContext(),about.class);
                startActivity(i);
                return true;
            case R.id.item2:
                onexitpressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}