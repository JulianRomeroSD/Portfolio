package Application;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents an order
 * @author Steven Nguyen, Julian Romero
 */
public class Order implements Customizable {
    private static int lineNumber;
    private ArrayList<OrderLine> orderLines;

    /**
     * Constructs an order object with an arraylist and line number
     */
    public Order() {
        this.orderLines = new ArrayList<>();
    }

    /**
     * Adds an orderline line object
     * @param obj orderline object
     * @return boolean if the orderline is added to order object returns true otherwise false
     */
    @Override
    public boolean add(Object obj) {
        OrderLine orderLine = (OrderLine) obj;
        lineNumber++;
        return this.orderLines.add(orderLine);

    }
    /**
     * decrement all orderlines objects
     * @param lineNumber int of a linenumber
     */
    private void decrementSerial(int lineNumber){
        for(int i = lineNumber; i < orderLines.size();i++){
            orderLines.get(i).setLineNumber(orderLines.get(i).getLineNumber()-1);
        }
    }

    /**
     * gets an orderline arraylist from order object
     * @return arraylist returns array list of order
     */
    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    /**
     * removes an orderline line object
     * @param obj orderline object
     * @return boolean if the orderline is removed from order returns true otherwise false
     */
    @Override
    public boolean remove(Object obj) {
        OrderLine orderLine = (OrderLine) obj;
        decrementSerial(orderLine.getLineNumber());
        lineNumber--;
        return this.orderLines.remove(orderLine);
    }

    /**
     * removes all orderline objects
     */
    public void removeAll() {
        lineNumber = 0;
        this.orderLines.clear();

    }

    /**
     * calculates the total price in orderlines array list rounded to two decimals
     * Format: xx.xx
     * @return double of total price
     */
    public double totalPrice(){
        double total = 0;
        for(int i = 0; i < orderLines.size();i++){
            total += orderLines.get(i).getPrice();
        }
        total = Double.parseDouble(String.format("%.2f",total));
        return total;
    }

    /**
     * Finds the orderline in orderlines arraylist by using line number
     * @param lineNumber int of lineNumber
     *@return Orderline returns orderline object found by linenumber
     */
    public OrderLine find(int lineNumber) {
        for(int i = 0; i < orderLines.size(); i++){
            if(orderLines.get(i).getLineNumber() == lineNumber){
                return orderLines.get(i);
            }
        }
        return null;
    }

    /**
     * Returns the string representation of orderlines array
     */
    @Override
    public String toString() {
        String result =  "";
        Iterator<OrderLine> i = this.orderLines.iterator();

        while (i.hasNext())
            result += i.next().toString() + " | " + "\n";

        return result;
    }
}
