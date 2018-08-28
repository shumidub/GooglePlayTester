package com.shumidub.googleplaytester;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    boolean finishActivity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btnStartTest);
        editText = findViewById(R.id.et);
        button.setOnClickListener(v->goToPlayMarket());
    }


    public void goToPlayMarket() {
        Context context = this;
        Uri uri = Uri.parse("market://details?id="+ editText.getText().toString());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) { }
    }

    @Override
    public void onBackPressed() {
        if (finishActivity) finish();
        else Toast.makeText(this, "For exit press back again", Toast.LENGTH_SHORT).show();
        finishActivity = true;
        new Thread(()-> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finishActivity = false;
        });
        super.onBackPressed();
    }
}
