package Application;

/**
 * This class represents an order line
 * @author Steven Nguyen, Julian Romero
 */
public class OrderLine {
    private int lineNumber;
    private Sandwich sandwich;
    private double price;

    private static int line_number = 1;

    /**
     * Constructs an orderline that increments the line number
     * @param sandwich			the sandwich of the orderline
     * @param price			the last name of the profile
     */
    public OrderLine(Sandwich sandwich, double price) {
        this.lineNumber = line_number;
        line_number++;
        this.sandwich = sandwich;
        this.price = price;
    }

    /**
     * Gets the line number from orderline
     * @return	the linenumber
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Sets the line number for orderline
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * decrements the line number for orderline static int
     */
    public void decrementSerial(){
        line_number--;
    }

    /**
     * resets the line number for orderline static int to 1
     */
    public void reset(){
        line_number = 1;
    }
    /**
     * Gets the sandwich from orderline
     * @return	sandwich from orderline
     */
    public Sandwich getSandwich() {
        return sandwich;
    }

    /**
     * Gets the price from orderline
     * @return	double price from orderline
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the string representation from orderline
     * @return string representation of orderline
     */
    @Override
    public String toString() {
        String result = this.lineNumber + " ";
        result += this.sandwich.toString();
        result += " Price $" + this.price;
        return result;
    }
}