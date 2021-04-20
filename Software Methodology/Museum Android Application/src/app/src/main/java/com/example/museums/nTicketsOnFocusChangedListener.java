package com.example.museums;

import android.view.View;
import android.widget.EditText;

/**
 * This class represents an OnFocusChanged event listener for nTickets editTexts
 * @author Steven Nguyen, Julian Romero
 */
public class nTicketsOnFocusChangedListener implements View.OnFocusChangeListener {
    private static final int maxValue = 5;

    // Sets the text
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            EditText editText = (EditText) v;
            int value;

            // If the text cannot be parsed to an int, ignore
            try {
                value = Integer.parseInt(String.valueOf(editText.getText()));
            } catch (Exception e) {
                return;
            }

            // Check if value is above 5 to clear
            if (value > maxValue)
                editText.setText("");
        }
        return;
    }
}
