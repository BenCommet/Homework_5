package com.example.bcom3_000.homework_5;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button calculateButton;
    Button clearButton;
    EditText longitudeP1;
    EditText latitudeP1;
    EditText longitudeP2;
    EditText latitudeP2;
    EditText distanceText;
    EditText bearingText;
    String currentDistanceUnits = "Miles";
    String currentBearingUnits = "Degrees";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.calculateButton = (Button) findViewById(R.id.calculateButton);
        this.clearButton = (Button) findViewById(R.id.clearButton);
        this.longitudeP1 = (EditText) findViewById(R.id.longitudeP1);
        this.latitudeP1 = (EditText) findViewById(R.id.latitudeP1);
        this.longitudeP2 = (EditText) findViewById(R.id.longitudeP2);
        this.distanceText = (EditText) findViewById(R.id.distanceField);
        this.bearingText = (EditText) findViewById(R.id.bearingField);
        this.latitudeP2 = (EditText) findViewById(R.id.latitudeP2);

        calculateButton.setOnClickListener(action -> {
            Double lon1 = Double.parseDouble(longitudeP1.getText().toString());
            Double lon2 = Double.parseDouble(longitudeP2.getText().toString());
            Double lat1 = Double.parseDouble(latitudeP1.getText().toString());
            Double lat2 = Double.parseDouble(latitudeP2.getText().toString());

            Location locationP1 = new Location("Point P1");
            Location locationP2 = new Location("Point P2");
            locationP1.setLongitude(lon1);
            locationP1.setLatitude(lat1);
            locationP2.setLongitude(lon2);
            locationP2.setLatitude(lat2);

            float distance = locationP1.distanceTo(locationP2);
            float bearing = locationP1.bearingTo(locationP2);

            if(currentDistanceUnits.equals("Kilometers")) {
                distanceText.setText(String.format("Distance: %.2f Kilometers", distance / 1000/.621371));
            }
            else {
                distanceText.setText(String.format("Distance: %.2f Miles", distance / 1000));
            }

            if(currentBearingUnits.equals("Mils")) {
                bearingText.setText(String.format("Bearing: %.2f Mils", bearing * 17.7777777));
            }
            else{
                bearingText.setText(String.format("Bearing: %.2f Degrees", bearing));
            }
        });

        clearButton.setOnClickListener(action -> {
            longitudeP1.setText("");
            longitudeP2.setText("");
            latitudeP1.setText("");
            latitudeP2.setText("");
            distanceText.setText("Distance: ");
            bearingText.setText("Bearing: ");
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings_Activity.class);
            intent.putExtra("distanceUnits", currentDistanceUnits);
            intent.putExtra("bearingUnits", currentBearingUnits);
            startActivityForResult(intent, 1);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                currentDistanceUnits =data.getStringExtra("distanceUnits");
                currentBearingUnits = data.getStringExtra("bearingUnits");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}
