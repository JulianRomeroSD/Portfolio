package Application;

import Application.OrderController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class starts the ordering program
 * @author Steven Nguyen, Julian Romero
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the order scene
            FXMLLoader orderSceneFXML = new FXMLLoader(getClass().getResource("Order.fxml"));
            Scene orderScene = (Scene)orderSceneFXML.load();
            primaryStage.setScene(orderScene);
            primaryStage.setTitle("Order");

            // Load the shopping cart scene
            FXMLLoader cartSceneFXML = new FXMLLoader(getClass().getResource("Cart.fxml"));
            Scene cartScene = (Scene)cartSceneFXML.load();
            Stage cartStage = new Stage();
            cartStage.setScene(cartScene);
            cartStage.setTitle("Cart");

            // Pass over the cart controller into the orderController to be used when showing the cart
            OrderController orderController = orderSceneFXML.<OrderController>getController();
            CartController cartController = cartSceneFXML.<CartController>getController();


            orderController.setCartController(cartController);
            cartController.stagePasser(cartStage);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}