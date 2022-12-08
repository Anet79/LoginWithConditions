package com.anet.logininwithconditions;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    public static final String STEPS_COUNT = "STEPS_COUNT";
    private ImageView main_IMG_check_wifi;
    private ImageView main_IMG_check_steps;
    private Button main_TVW_motion;
    private Button btnNext;
    private int counter = 0;
    private boolean didTreeSteps = false;
    private TextInputLayout username;
    private TextInputLayout password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initButtons();
        CheckIfTrue();
    }

    private void CheckIfTrue() {
        if (isNetworkAvailable()) {
            updateCounter();
            updateUI(main_IMG_check_wifi);
        }

        Intent intent = getIntent();
        // Get the extras (if there are any)
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("didTreeSteps")) {
                didTreeSteps = getIntent().getExtras().getBoolean("didTreeSteps");
                if (getIntent().getExtras().getBoolean("didTreeSteps")) {
                    Log.d("pttt", "from main: \n" + String.valueOf(didTreeSteps));
                    updateCounter();
                    updateUI(main_IMG_check_steps);
                }



            }
        }


    }


    //update UI if the user did right condition
    private void updateUI(ImageView isGood) {
        isGood.setImageResource(R.drawable.ic_baseline_check_24);

    }


    private void initButtons() {
        main_TVW_motion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StepCounterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("didTreeSteps", true);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();


            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("pttt",password.getEditText().getText().toString());
               if (checkUsername() && checkPassword()) {
                   // Toast.makeText(getApplicationContext(), "Username and Password are VALID!", Toast.LENGTH_SHORT).show();
                    if (counter==2){
                      //  Toast.makeText(getApplicationContext(), "Congratulations, you succeeded", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SucceededActivity.class);
                        startActivity(intent);
                        finish();





                    }
                }
            }
        });

    }

    private void findViews() {
        main_IMG_check_wifi = findViewById(R.id.main_IMG_check_wifi);
        main_IMG_check_steps = findViewById(R.id.main_IMG_check_steps);
        main_TVW_motion = findViewById(R.id.main_TVW_motion);
        btnNext = findViewById(R.id.btnNext);
        username = findViewById(R.id.edtUsername);
        password = findViewById(R.id.edtPassword);
    }


    //check if we have connection to wifi
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private void updateCounter() {
        counter++;
        Log.d("counter",String.valueOf(counter));
    }
    private boolean checkPassword() {
        Log.d("pttt",this.password.getEditText().getText().toString());
        return Validator.PatternPassword(this.password.getEditText().getText().toString());
    }

    private boolean checkUsername() {
        return Validator.PatternUsername(this.username.getEditText().getText().toString());
    }


}

