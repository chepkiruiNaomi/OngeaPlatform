package com.dasayantist.ongeaplatform.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dasayantist.ongeaplatform.R;

public class MainActivity extends AppCompatActivity {
Button talk,report,emergency,guide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*talk = (FancyButton) findViewById(R.id.talk);
       report = (FancyButton) findViewById(R.id.report);
        emergency = (FancyButton) findViewById(R.id.emergency);
        guide = (FancyButton) findViewById(R.id.guide);*/
    }

public  void talk(View view){
  Intent i = new Intent(MainActivity.this,CounsellorActivity.class);
    startActivity(i);
}
    public  void report(View view){
        Intent i = new Intent(MainActivity.this, ReportToActivity.class);
        startActivity(i);
    }
    public  void emergency(View view){
        Toast.makeText(MainActivity.this,"coming soon....",Toast.LENGTH_SHORT).show();
    }
    public  void guide(View view){
        startActivity(new Intent(MainActivity.this, QuickGuideActivity.class));
    }
}
