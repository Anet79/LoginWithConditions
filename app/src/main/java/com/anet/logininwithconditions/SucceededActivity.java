package com.anet.logininwithconditions;





import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;

public class SucceededActivity extends AppCompatActivity {
    private MaterialTextView succeeded_TVW_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succeeded);
        findViews();


    }
    private void findViews(){
        succeeded_TVW_end=findViewById(R.id.succeeded_TVW_end);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
