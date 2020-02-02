package com.example.wellxa;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.allyants.notifyme.NotifyMe;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public  class reminder2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Button done;
    RelativeLayout date;
    RelativeLayout time;

    Calendar now = Calendar.getInstance();
    TimePickerDialog tpd;

    private DatePickerDialog dpd;
    EditText editname, editvaccine,editage;

    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder2);
        done = (Button) findViewById(R.id.done);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editname = (EditText) findViewById(R.id.editname);
        date=(RelativeLayout)findViewById(R.id.date);

        time=(RelativeLayout)findViewById(R.id.time);
        editvaccine = (EditText) findViewById(R.id.editvaccine);
        editage = (EditText) findViewById(R.id.editage);
        dpd = DatePickerDialog.newInstance(
                reminder2.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        tpd = TimePickerDialog.newInstance(
                reminder2.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),
                false
        );
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpd.show(getFragmentManager(),"DatePickerdialog");
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tpd.show(getFragmentManager(),"TimePickerdialog");
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editname.getText())){
                    editname.setError("Input is required");

                }
                if(TextUtils.isEmpty(editage.getText())){
                    editage.setError("Input is required");

                }
                if(TextUtils.isEmpty(editvaccine.getText())){
                    editvaccine.setError("Input is required");

                }
                Intent i = new Intent(getApplicationContext(), reminder1.class);
                i.putExtra("Value1", editname.getText().toString());
                i.putExtra("Value2", editvaccine.getText().toString());
                // Set the request code to any code you like, you can identify the
                // callback via this code
                startActivity(i);

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
    public void onDateSet(DatePickerDialog view, int year, int monthOfyear, int dayOfmonth) {
        now.set(Calendar.YEAR, year);
        now.set(Calendar.MONTH, monthOfyear);
        now.set(Calendar.DAY_OF_MONTH, dayOfmonth);

    }
    @Override
    public void onTimeSet(TimePickerDialog view,int hourOfday, int minute, int second) {
        now.set(Calendar.HOUR_OF_DAY, hourOfday);
        now.set(Calendar.MINUTE, minute);
        now.set(Calendar.SECOND, second);
        NotifyMe notifyMe = new NotifyMe.Builder(getApplicationContext())
                .title(editname.getText().toString())
                .content(editvaccine.getText().toString())

                .color(255, 0, 0, 255)
                .led_color(255, 255, 255, 255)
                .time(now)
                .addAction(new Intent(), "Snooze", false)
                .key("test")
                .addAction(new Intent(), "Dismiss", true, false)
                .addAction(new Intent(), "Done")
                .large_icon(R.mipmap.ic_launcher_round)
                .build();
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
