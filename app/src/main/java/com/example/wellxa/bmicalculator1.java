package com.example.wellxa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class bmicalculator1 extends AppCompatActivity {
    Button b1;
    EditText e1,e2,e3,e4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator1);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        b1=(Button)findViewById(R.id.calculate);
        e4=(EditText)findViewById(R.id.editage);
        e1=(EditText)findViewById(R.id.editweight);

        e2=(EditText)findViewById(R.id.editheight1);
        e3=(EditText)findViewById(R.id.editheight2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(e1.getText())){
                    e1.setError("Input is required");

                }
                if(TextUtils.isEmpty(e2.getText())){
                    e2.setError("Input is required");

                }
                if(TextUtils.isEmpty(e3.getText())){
                    e3.setError("Input is required");

                }
                if(TextUtils.isEmpty(e4.getText())){
                    e4.setError("Input is required");

                }

                else {
                    Intent i = new Intent(getApplicationContext(), bmiresult.class);
                    i.putExtra("Value1", e1.getText().toString());
                    i.putExtra("Value2", e2.getText().toString());
                    i.putExtra("Value3", e3.getText().toString());

                    startActivity(i);
                }
            }
        });
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
