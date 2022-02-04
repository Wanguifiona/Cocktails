package com.moringaschool.cocktails;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.startButton)
    Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mStartButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mStartButton) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
    }
}