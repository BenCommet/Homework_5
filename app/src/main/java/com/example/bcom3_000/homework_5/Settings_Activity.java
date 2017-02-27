package com.example.bcom3_000.homework_5;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import org.w3c.dom.Text;


public class Settings_Activity extends AppCompatActivity {
    TextView distanceText;
    TextView bearingText;
    String currentBearingUnits = getIntent().getStringExtra("bearingUnits");
    String currentDistanceUnits = getIntent().getStringExtra("distanceUnits");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_);

        distanceText = (TextView) findViewById(R.id.distanceText);
        distanceText.setText(currentDistanceUnits);

        bearingText = (TextView) findViewById(R.id.bearingText);
        bearingText.setText(currentBearingUnits);

        Spinner distance_spinner = (Spinner) findViewById(R.id.distance_spinner);
        Spinner bearing_spinner = (Spinner) findViewById(R.id.bearing_spinner);
        ArrayAdapter<CharSequence> distance_adapter = ArrayAdapter.createFromResource(this,
                R.array.distance, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> bearing_adapter = ArrayAdapter.createFromResource(this,
                R.array.bearing, android.R.layout.simple_spinner_item);
        distance_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        bearing_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        distance_spinner.setAdapter(distance_adapter);
        bearing_spinner.setAdapter(bearing_adapter);

        distance_spinner.setOnItemSelectedListener (new OnItemSelectedListener()  {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String selectedItem = distance_spinner.getSelectedItem().toString();
                distanceText = (TextView) findViewById(R.id.distanceText);
                distanceText.setText(selectedItem);
                currentDistanceUnits = selectedItem;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        bearing_spinner.setOnItemSelectedListener (new OnItemSelectedListener()  {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                bearingText = (TextView) findViewById(R.id.bearingText);
                bearingText.setText(bearing_spinner.getSelectedItem().toString());
                currentBearingUnits = bearing_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("distanceUnits", currentDistanceUnits);
                returnIntent.putExtra("bearingUnits", currentBearingUnits);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

}
