package com.example.museums;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * This class represents the second page
 * The second page shows the ticket prices for museums and allows users to calculate prices
 * @author Steven Nguyen, Julian Romero
 */
public class MuseumInfoActivity extends AppCompatActivity {
    private Intent thisIntent;
    private Museum museum;
    private TextView museum_name_textView;
    private ImageView museum_icon_imageView;
    private EditText nTickets_students;
    private EditText nTickets_adults;
    private EditText nTickets_seniors;
    private TextView price_textView_students;
    private TextView price_textView_adults;
    private TextView price_textView_seniors;
    private TextView total_cost;

    private static final String parameterName = "museumName";

    private static final String priceFormatString = "$#.##";
    private static final DecimalFormat priceFormat = new DecimalFormat(priceFormatString);
    private static final String TicketPriceFormat = "%s per ticket";

    private static final String toastText = "Maximum of 5 tickets for each ticket type!";

    private static final double NYSalesTax = 0.04;

    // Setup activity on created from MuseumsActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.museum_info);

        thisIntent = getIntent();

        // Get the inputted museum name
        Bundle extras = thisIntent.getExtras();
        String museumName = extras.getString(parameterName);
        museum = new Museum(museumName);

        // Setup the instance variables
        referenceAllViews();

        // Setup nTickets editTexts to limit tickets to 5
        setupNTickets();

        // Load the data for the select museum
        loadMuseumData(museum);

        // Display the toast
        displayTicketsLimit();
        return;
    }

    // Reference all necessary views by id
    private void referenceAllViews() {
        thisIntent = getIntent();
        museum_name_textView = findViewById(R.id.museum_name_textView);
        museum_icon_imageView = findViewById(R.id.museum_icon_imageView);
        nTickets_students = findViewById(R.id.nTickets_students);
        nTickets_adults = findViewById(R.id.nTickets_adults);
        nTickets_seniors = findViewById(R.id.nTickets_seniors);
        price_textView_students = findViewById(R.id.price_textView_students);
        price_textView_adults = findViewById(R.id.price_textView_adults);
        price_textView_seniors = findViewById(R.id.price_textView_seniors);
        total_cost = findViewById(R.id.total_cost);
        return;
    }

    // Display the toast for limit on tickets
    private void displayTicketsLimit() {
        Context context = getApplicationContext();
        CharSequence text = toastText;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        return;
    }

    // Given the museum name, load the name, icon, and prices
    private void loadMuseumData(Museum museum) {
        museum_name_textView.setText(museum.getName());
        museum_icon_imageView.setImageResource(museum.getIcon());

        // Display the prices for each ticket type
        double pricePerStudent = museum.getPriceStudent();
        double pricePerAdult = museum.getPriceAdult();
        double pricePerSenior = museum.getPriceSenior();
        price_textView_students.setText(String.format(TicketPriceFormat, priceFormat.format(pricePerStudent)));
        price_textView_adults.setText(String.format(TicketPriceFormat, priceFormat.format(pricePerAdult)));
        price_textView_seniors.setText(String.format(TicketPriceFormat, priceFormat.format(pricePerSenior)));
        return;
    }

    // On Museum image or name clicked, open the homepage for the select museum
    public void openMuseumHomepage(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, museum.getHomepageUri());
        startActivity(browserIntent);
    }

    // On Calculate clicked, calculate the cost of all the tickets and output to screen
    public void calculateCost(View view) {
        String sStudentTickets = String.valueOf(nTickets_students.getText());
        String sAdultTickets = String.valueOf(nTickets_adults.getText());
        String sSeniorTickets = String.valueOf(nTickets_seniors.getText());

        int nStudentTickets, nAdultTickets, nSeniorTickets;

        // For each input, if no input, set to 0
        if (sStudentTickets.equals("")) {
            nStudentTickets = 0;
        } else {
            nStudentTickets = Integer.parseInt(sStudentTickets);
        }
        if (sAdultTickets.equals("")) {
            nAdultTickets = 0;
        } else {
            nAdultTickets = Integer.parseInt(sAdultTickets);
        }
        if (sSeniorTickets.equals("")) {
            nSeniorTickets = 0;
        } else {
            nSeniorTickets = Integer.parseInt(sSeniorTickets);
        }

        // Calculate cost
        double pricePerStudent = museum.getPriceStudent();
        double pricePerAdult = museum.getPriceAdult();
        double pricePerSenior = museum.getPriceSenior();

        double totalPrice =
                (1 + NYSalesTax) * (
                    (nStudentTickets * pricePerStudent)
                    + (nAdultTickets * pricePerAdult)
                    + (nSeniorTickets * pricePerSenior)
                );

        String displayPrice = priceFormat.format(totalPrice);
        total_cost.setText(displayPrice);
        return;
    }

    // Setup nTickets to clear the input when the value is above 5
    private void setupNTickets() {
        nTickets_students.setOnFocusChangeListener(new nTicketsOnFocusChangedListener());
        nTickets_adults.setOnFocusChangeListener(new nTicketsOnFocusChangedListener());
        nTickets_seniors.setOnFocusChangeListener(new nTicketsOnFocusChangedListener());
        return;
    }
}