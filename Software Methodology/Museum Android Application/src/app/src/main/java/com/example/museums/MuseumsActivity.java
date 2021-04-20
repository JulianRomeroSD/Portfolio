package com.example.museums;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.museums.MuseumInfoActivity;

/**
 * This class represents the launch page of the Museums app
 * @author Steven Nguyen, Julian Romero
 */
public class MuseumsActivity extends AppCompatActivity {
    private static final String museumInfoActivityParameterName = "museumName";

    private static final String argForMet = "Metropolitan Museum of Art";
    private static final String argForGug = "Solomon R. Guggenheim Museum";
    private static final String argForNat = "American Museum of Natural History";
    private static final String argForMod = "The Museum of Modern Art";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.museums);
    }

    public void goToMet(View view) {
        Intent intent = new Intent(this, MuseumInfoActivity.class);
        intent.putExtra(museumInfoActivityParameterName, argForMet);
        startActivity(intent);
    }
    public void goToGug(View view) {
        Intent intent1 = new Intent(this, MuseumInfoActivity.class);
        intent1.putExtra(museumInfoActivityParameterName, argForGug);
        startActivity(intent1);
    }
    public void goToNatural(View view) {
        Intent intent2 = new Intent(this, MuseumInfoActivity.class);
        intent2.putExtra(museumInfoActivityParameterName, argForNat);
        startActivity(intent2);
    }
    public void goToModern(View view) {
        Intent intent3 = new Intent(this, MuseumInfoActivity.class);
        intent3.putExtra(museumInfoActivityParameterName, argForMod);
        startActivity(intent3);
    }

}
