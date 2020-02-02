package com.example.wellxa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class signupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    @InjectView(R.id.username) EditText _nameText;
    @InjectView(R.id.email) EditText _emailText;
    @InjectView(R.id.pass) EditText _passwordText;
    @InjectView(R.id.registerbtn) Button _signupButton;
    @InjectView(R.id.textView7) TextView _loginLink;

    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });

        t1=(TextView)findViewById(R.id.textView7);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),loginActivity.class);
                startActivity(i);
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(signupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();
        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Toast.makeText(getApplicationContext(), "Signup Successful", Toast.LENGTH_LONG).show();
        Intent i=new Intent(getApplicationContext(),dashboardActivity.class);
        i.putExtra("Value1", _nameText.getText().toString());
        i.putExtra("Value2", _emailText.getText().toString());
        startActivity(i);
        finish();

    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }


        public static class sliderAdapter extends PagerAdapter {
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
                "Calculate your BMI",
                "Keep track of calorie intake",
                "Have vaccination alert"
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
            layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view= layoutInflater.inflate(R.layout.slide_layout,container, false);
            ImageView image=(ImageView)view.findViewById(R.id.slide_image);
            TextView text=(TextView) view.findViewById(R.id.heading);
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
}
