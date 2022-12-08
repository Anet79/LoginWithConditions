package com.anet.logininwithconditions;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;

public class StepCounterActivity extends AppCompatActivity {
    private MaterialTextView main_LBL_steps;
    private StepDetector stepDetector;
    private Button step_count_BTN_done;
    private boolean didTreeSteps = false;


    private StepDetector.CallBack_steps callBack_steps = new StepDetector.CallBack_steps() {
        @Override
        public void oneStep() {
            main_LBL_steps.setText("" + stepDetector.stepCounter);
        }

//        @Override
//        public void yemenStep() {
//            main_LBL_steps.setText("" + stepDetector.stepCounter);
//        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        findViews();
        initButtons();


        stepDetector = new StepDetector(this, callBack_steps);
        didTreeSteps=getIntent().getExtras().getBoolean("didTreeSteps");
    }

    private void initButtons() {
        step_count_BTN_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("pttt", "Steps: \n" + String.valueOf(stepDetector.getStepCounter()));
                if (stepDetector.getStepCounter() >= 3) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("didTreeSteps", true);
                    Intent i = new Intent(StepCounterActivity.this, MainActivity.class);
                    i.putExtras(bundle);
                   // i.putExtra("didTreeSteps", true);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "You need at least 3 steps!", Toast.LENGTH_LONG).show();
                    stepDetector.stepCounter = 0;
                    main_LBL_steps.setText("" + stepDetector.stepCounter);

                }


            }
        });

    }

    private void findViews() {
        main_LBL_steps = findViewById(R.id.main_LBL_steps);
        step_count_BTN_done = findViewById(R.id.step_count_BTN_done);

    }

    @Override
    protected void onResume() {
        super.onResume();

        stepDetector.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        stepDetector.stop();
    }

    public StepDetector getStepDetector() {
        return stepDetector;
    }
}






