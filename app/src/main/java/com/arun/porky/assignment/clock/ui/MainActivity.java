package com.arun.porky.assignment.clock.ui;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.arun.porky.assignment.clock.R;
import com.arun.porky.assignment.clock.global.FragmentOpener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        new FragmentOpener(this)
                .setManager(getSupportFragmentManager())
                .setContainer(R.id.f_container)
                .openReplace(new FragHome());

    }


}