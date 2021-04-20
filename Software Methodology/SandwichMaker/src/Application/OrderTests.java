package Application;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests methods of the Order class
 * @author Steven Nguyen, Julian Romero
 */
public class OrderTests {
    @Test
    void add(){
        Order order = new Order();
        Chicken chicken = new Chicken();
        Beef beef = new Beef();
        OrderLine orderLine1 = new OrderLine(chicken,chicken.price());
        OrderLine orderLine2 = new OrderLine(beef,beef.price());

        order.add(orderLine1);
        order.add(orderLine2);

        assertEquals(orderLine1,order.getOrderLines().get(0), "Orderline when added should be the same");
        assertNotEquals(orderLine1,order.getOrderLines().get(1), "Orderline when added should not be the same");

    }
    @Test
    void remove(){
        Order order = new Order();
        Chicken chicken = new Chicken();
        Beef beef = new Beef();
        OrderLine orderLine1 = new OrderLine(chicken,chicken.price());
        OrderLine orderLine2 = new OrderLine(beef,beef.price());

        order.add(orderLine1);
        order.add(orderLine2);

        assertEquals(true,order.remove(orderLine2), "Orderline since removed should be true");
        assertEquals(false,order.remove(orderLine2), "Orderline since not in list should be false");
    }

    @Test
    void removeAll(){
        Order order = new Order();
        Chicken chicken = new Chicken();
        Beef beef = new Beef();
        OrderLine orderLine1 = new OrderLine(chicken,chicken.price());
        OrderLine orderLine2 = new OrderLine(beef,beef.price());

        order.add(orderLine1);
        order.add(orderLine2);
        order.removeAll();

        assertEquals(0,order.getOrderLines().size(), "Orderline array size should be zero after remove all");
    }

    @Test
    void totalPrice(){
        Order order1 = new Order();
        Order order2 = new Order();
        Chicken chicken = new Chicken();
        Beef beef = new Beef();
        OrderLine orderLine1 = new OrderLine(chicken,chicken.price());
        OrderLine orderLine2 = new OrderLine(beef,beef.price());

        order1.add(orderLine1);
        order1.add(orderLine2);

        assertEquals(19.98,order1.totalPrice(), "Order1 total price should be fifteen");
        assertEquals(0,order2.totalPrice(), "Order2 total price should be zero since nothing added");


    }
}