package Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class handles the cart scene
 * @author Steven Nguyen, Julian Romero
 */
public class CartController {

    @FXML
    private ListView<String> cartOrderView;

    @FXML
    private TextField orderTotalCart;

    private Order currentOrder;
    private OrderLine currentOrderLine;
    private Stage cartStage;

    /**
     * sets up an order object
     */
    @FXML
    void initialize(){
        currentOrder = new Order();
        return;
    }

    /**
     * gets the stage from main
     */
    public void stagePasser(Stage cartFromMain){
        cartStage = cartFromMain;

    }

    /**
     * Makes an orderline object that stores the sandwich input and adds it in current order
     * @param sandwich sandwiches
     */
    public void add(Sandwich sandwich){
        currentOrderLine = new OrderLine(sandwich,sandwich.price());
        currentOrder.add(currentOrderLine);
        cartOrderView.getItems().add(this.currentOrderLine.toString());
        orderTotalCart.setText(String.valueOf(currentOrder.totalPrice()));
    }

    /**
     * shows the cartstage for ordercontroller
     */
    public void show(){
        cartStage.show();
    }
    /**
     * gets the index of the selected item for different number of digits
     * @param selectedItems a selected item from observable list
     * @return int returns the index of selected item
     */
    private int getIndex(ObservableList<String> selectedItems){
        int index;
        if(selectedItems.get(0).substring(1,2).contains(" ")) {
            index = Integer.parseInt(selectedItems.get(0).substring(0,1));
            return index;
        }
        else if(selectedItems.get(0).substring(2,3).contains(" ")){
            index = Integer.parseInt(selectedItems.get(0).substring(0,2));
            return index;
        }
        else{
            index = Integer.parseInt(selectedItems.get(0).substring(0,3));
            return index;
        }
    }

    @FXML
    void addOrderLine(ActionEvent event) {
        int index;
        MultipleSelectionModel<String> selectionModel = cartOrderView.getSelectionModel();
        if(selectionModel.isEmpty()){
            return;
        }

        ObservableList<String> selectedItems = selectionModel.getSelectedItems();
        index = getIndex(selectedItems);

        //found order line with same serial and put in current order
        OrderLine lineOrder = this.currentOrder.find(index);
        //found order line and add identical sandwich to list using add method
        this.add(lineOrder.getSandwich());
    }

    @FXML
    void backToOrder(ActionEvent event) {//TODO setup interaction
        cartStage.close();
        return;
    }

    @FXML
    void clearOrder(ActionEvent event) {
        currentOrder.removeAll();
        cartOrderView.getItems().clear();
        this.currentOrderLine.reset();
        this.orderTotalCart.setText(String.valueOf(currentOrder.totalPrice()));
    }

    @FXML
    void removeOrderLine(ActionEvent event) {
        MultipleSelectionModel<String> selectionModel = cartOrderView.getSelectionModel();
        if(selectionModel.isEmpty()){
            return;
        }
        ObservableList<String> selectedItems = selectionModel.getSelectedItems();
        int index;
        index = getIndex(selectedItems);
        OrderLine lineOrder = this.currentOrder.find(index);

        this.currentOrder.remove(lineOrder);
        this.currentOrderLine.decrementSerial();
        cartOrderView.getItems().clear();
        String result;

        for(int i = 0; i < this.currentOrder.getOrderLines().size();i++){
            result = this.currentOrder.getOrderLines().get(i).toString();
            cartOrderView.getItems().add(result);
        }

        this.orderTotalCart.setText(String.valueOf(currentOrder.totalPrice()));
    }

    @FXML
    void saveOrderToFile(ActionEvent event) {
        //MultipleSelectionModel<String> selectionModel = cartOrderView.getSelectionModel();
        if(cartOrderView.getItems().isEmpty()){
            return;
        }

        FileChooser fileChooser = new FileChooser();
        Stage primaryStage = new Stage();

        fileChooser.setTitle("Save File");
        //adds .txt extension to all text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File orderLine = fileChooser.showSaveDialog(primaryStage);

        if(orderLine == null){
            return;
        }
        saveTextToFile(this.currentOrder.toString(),orderLine);

    }
    /**
     * Saves the text from the line order to a file
     * @param content,file Takes the strings in the database and file location
     */
    private void saveTextToFile(String content, File file){
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
        }
    }
}
