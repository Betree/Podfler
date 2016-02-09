package com.benjaminpiouffle.podfler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class AddPlantActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_plant);
        Button confirmButton = (Button) findViewById(R.id.add_plant_confirm_button);
        confirmButton.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("plantName", ((EditText)findViewById(R.id.inputPlantName)).getText().toString());
            returnIntent.putExtra("plantIP", ((EditText)findViewById(R.id.inputPlantIP)).getText().toString());
            setResult(RESULT_OK, returnIntent);
            finish();
        });
    }
}
