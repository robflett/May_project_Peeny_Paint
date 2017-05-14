package com.codeclan.peenypaint;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button drawButton;
    Button tuneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        drawButton = (Button)findViewById(R.id.draw);
        tuneButton = (Button)findViewById(R.id.tune);
    }

    public void onDrawButtonClicked(View view) {
        Intent intent = new Intent(this, CanvasView.class);
        startActivity(intent);
    }

    public void onTuneButtonClicked(View view) {
        Intent intent = new Intent(this, ColourTuneActivity.class);
        startActivity(intent);
    }

}
