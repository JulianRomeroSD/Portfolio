package Application;

/**
 * This class represents a chicken sandwich
 * @author Steven Nguyen, Julian Romero
 */
public class Chicken extends Sandwich {
    // Ingredients in every chicken sandwich
    private static final String[] INCLUDED_INGREDIENTS = {
            "Fried Chicken",
            "Spicy Sauce",
            "Pickles"
    };

    // Cost of a chicken sandwich with no extras
    private static final double BASE_PRICE = 8.99;

    // Location of image for a chicken sandwich
    private static String IMAGE_LOCATION = "Application/../Images/Chicken Sandwich.png";

    // Constructs a chicken sandwich with no extras
    public Chicken() {
        super();
    }

    // Calculates the cost
    public double price() {
        double extrasPrice = super.getPriceOfExtras();
        return BASE_PRICE + extrasPrice;
    }

    // Returns the included ingredients
    @Override
    public String[] getIncluded() {
        return INCLUDED_INGREDIENTS;
    }

    // Returns the image location
    @Override
    public String getImageLocation() {
        return IMAGE_LOCATION;
    }

    // Gets the string form
    @Override
    public String toString() {
        String result = "Chicken Sandwich with ";
        String extras = super.toString();

        for (String ingredient : INCLUDED_INGREDIENTS) {
            result += ingredient + " | ";
        }

        return result + extras;
    }
}