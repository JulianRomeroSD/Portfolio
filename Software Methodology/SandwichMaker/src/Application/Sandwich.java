package Application;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a generic sandwich
 * @author Steven Nguyen, Julian Romero
 */
public abstract class Sandwich implements Customizable{
    // The maximum amount of extras in a sandwich
    static final int MAX_EXTRAS = 6;

    // The price of an additional ingredient
    static final double PER_EXTRA = 1.99;

    // Options for SandwichType
    private static final String[] SandwichTypeOptions = {
            "Chicken",
            "Beef",
            "Fish"
    };

    // Extra ingredients added to the sandwich
    protected ArrayList<Extra> Extras = new ArrayList<Extra>();

    // Getter for SandwichTypeOptions
    public static String[] getSandwichTypeOptions() {
        return SandwichTypeOptions;
    }

    // Constructs an empty sandwich
    public Sandwich() {}

    // Returns Extras
    public Object[] getExtras() {
        return this.Extras.toArray();
    }

    // Returns included ingredients for a given sandwich type
    public abstract String[] getIncluded();

    // Returns the location of the image to represent the sandwich
    public abstract String getImageLocation();

    // Calculate price
    public abstract double price();

    // Calculate price of extras
    public double getPriceOfExtras() {
        return this.Extras.size() * PER_EXTRA;
    }

    // Adds an extra ingredient
    @Override
    public boolean add(Object obj) {
        if (this.Extras.size() >= 6)
            return false;

        Extra ingredient = (Extra)obj;
        return this.Extras.add(ingredient);
    }

    // Removes an extra ingredient
    @Override
    public boolean remove(Object obj) {
        Extra ingredient = (Extra)obj;
        return this.Extras.remove(ingredient);
    }

    // Removes all extra ingredients
    public void removeAll() {
        this.Extras.clear();
        return;
    }

    // Returns the string form
    @Override
    public String toString() {
        String result = "";
        Iterator<Extra> i = this.Extras.iterator();

        while (i.hasNext())
            result += i.next().toString() + " | ";

        return result;
    }
}