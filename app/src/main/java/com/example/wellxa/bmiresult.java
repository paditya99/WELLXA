package com.example.wellxa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class bmiresult extends AppCompatActivity {
    double weight,height_ft,height_inch,bmi;
    TextView bmivalue;
    TextView statement;
    ProgressBar p1;
    Switch s1;
    TextView suggestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiresult);
        statement=(TextView)findViewById(R.id.statement);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bmivalue=(TextView)findViewById(R.id.bmivalue);
        Bundle extras = getIntent().getExtras();
        ImageView i1=(ImageView)findViewById(R.id.happyimg);
        p1=(ProgressBar)findViewById(R.id.progressbar);
        ImageView i2=(ImageView)findViewById(R.id.sadimg);
        suggestions= (TextView)findViewById(R.id.suggestions);
        s1=(Switch)findViewById(R.id.switch1);
        String value1 = extras.getString("Value1");
        String value2 = extras.getString("Value2");
        String value3 = extras.getString("Value3");
        i1.setImageResource(R.drawable.happyicon);
        i2.setImageResource(R.drawable.sadicon);
        weight=Double.parseDouble(value1);
        height_ft=Double.parseDouble(value2);
        height_inch=Double.parseDouble(value3);
        bmi=weight/(((height_ft*0.3048)+(height_inch*0.0254))*((height_ft*0.3048)+(height_inch*0.0254)));
        bmi=Math.round(bmi*10.0)/10.0;
        int value = (int) bmi;
        p1.setProgress(value);
        bmivalue.setText(Double.toString(bmi));

        if(bmi<18.5){
             i1.setVisibility(View.INVISIBLE);
            i2.setVisibility(View.VISIBLE);
             statement.setText("Your BMI is Underweight");
            s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(s1.isChecked()){
                        suggestions.setText("People who are underweight need to eat more often to consume enough calories to gain weight.\nConsume nutrient-dense foods, such as lean protein, whole grains, legumes, low-fat dairy products, nuts and seeds and fruits and vegetables. Limit foods that are high in saturated fat, cholesterol, sugars and sodium, as these can increase your risks for certain health conditions.");
                    }
                    if(!s1.isChecked()){
                        suggestions.setText("");
                    }
                }
            });
        }
        else if(bmi>=18.5 && bmi<=24.9){
            i1.setVisibility(View.VISIBLE);
            i2.setVisibility(View.INVISIBLE);
            statement.setText("Your BMI is Normal");
            s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(s1.isChecked()){
                        suggestions.setText("You are within a healthy weight range for young and middle-aged adults. \n You need not to worry but having a balanced diet is recommendable.");
                    }
                    if(!s1.isChecked()){
                        suggestions.setText("");
                    }

                }
            });
        }
        else if(bmi>=25 && bmi<=29.9){
            i1.setVisibility(View.INVISIBLE);
            i2.setVisibility(View.VISIBLE);
            statement.setText("Your BMI is Overweight");
            s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(s1.isChecked()){
                        suggestions.setText("You may be advised to lose some weight for health reasons. You are recommended to talk to your doctor or a dietitian for advice.\n Make small and realistic changes to get your calorie count down.\n Keep the fats and fast foods away.");
                    }
                    if(!s1.isChecked()){
                        suggestions.setText("");
                    }
                }
            });
        }
        else{
            i1.setVisibility(View.INVISIBLE);
            i2.setVisibility(View.VISIBLE);
            statement.setText("You are Obese");
            s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(s1.isChecked()){
                        suggestions.setText("You may be advised to lose some weight for health reasons. You are recommended to talk to your doctor or a dietitian for advice.\n Make small and realistic changes to get your calorie count down.\n Keep the fats and fast foods away.");
                    }
                    if(!s1.isChecked()){
                        suggestions.setText("");
                    }
                }
            });
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
