package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.SelectionMode;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.text.DecimalFormat;

/**
 * This class handles the order scene
 * @author Steven Nguyen, Julian Romero
 */
public class OrderController {

    // DecimalFormat to use when formatting a double as a price
    private final String PRICE_FORMAT_STRING = "$#.##";
    private final DecimalFormat PRICE_FORMAT = new DecimalFormat(PRICE_FORMAT_STRING);

    @FXML
    private ComboBox<String> SandwichType;

    @FXML
    private TextArea IncludedIngredientsTextArea;

    @FXML
    private ImageView SandwichImageView;

    @FXML
    private ListView<String> AvailableExtrasListView;

    @FXML
    private ListView<String> ExtrasListView;

    @FXML
    private TextField TotalPrice;

    @FXML
    private TextArea Output;

    // The controller to handle Cart.fxml
    private CartController CartController;

    // The current sandwich
    private Sandwich currentSandwich;

    @FXML
    void OnAddExtra(ActionEvent event) {
        if (currentSandwich == null) {
            output("You must select a type of sandwich before adding an extra.\n");
            return;
        }

        MultipleSelectionModel<String> selectionModel = AvailableExtrasListView.getSelectionModel();
        ObservableList<String> selectedItems = selectionModel.getSelectedItems();
        Iterator<String> i = selectedItems.iterator();

        // For each item selected, remove from sandwich
        while (i.hasNext()) {
            Extra extra = new Extra(i.next());
            boolean isAdded = currentSandwich.add(extra);

            if (!isAdded) {
                output(extra.toString() + " was not added! Cannot have more than 6 extras.\n");
            }
        }

        refreshExtraIngredients();
        refreshPrice();

        return;
    }

    @FXML
    void OnAddToOrder(ActionEvent event) {
        if (currentSandwich == null) {
            output("You must select a type of sandwich before adding it to the order.\n");
            return;
        }

        System.out.println(CartController == null);
        if (CartController == null)
            return;

        CartController.add(currentSandwich);
        output("Added to cart: " + currentSandwich.toString() + "\n");
        clearInput();

        return;
    }

    @FXML
    void OnRemoveAllExtras(ActionEvent event) {
        if (currentSandwich == null)
            return;

        currentSandwich.removeAll();

        refreshExtraIngredients();
        refreshPrice();

        return;
    }

    @FXML
    void OnRemoveExtra(ActionEvent event) {
        if (currentSandwich == null)
            return;

        MultipleSelectionModel<String> selectionModel = ExtrasListView.getSelectionModel();
        ObservableList<String> selectedItems = selectionModel.getSelectedItems();
        Iterator<String> i = selectedItems.iterator();

        // For each item selected, remove from sandwich
        while (i.hasNext()) {
            Extra extra = new Extra(i.next());
            boolean isRemoved = currentSandwich.remove(extra);

            if (!isRemoved) {
                output("ERROR: " + extra.toString() + " was not removed!\n");
            }
        }

        refreshExtraIngredients();
        refreshPrice();

        return;
    }

    @FXML
    void OnSandwichTypeSelected(ActionEvent event) {
        String selected = (String)SandwichType.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;

        if (selected.equals("Chicken")) {
            currentSandwich = new Chicken();
        } else if (selected.equals("Beef")) {
            currentSandwich = new Beef();
        } else if (selected.equals("Fish")) {
            currentSandwich = new Fish();
        }

        refreshImage();
        refreshIncludedIngredients();
        refreshExtraIngredients();
        refreshPrice();

        return;
    }

    @FXML
    void OnShowOrder(ActionEvent event) {
        CartController.show();
        return;
    }

    @FXML
    void initialize() {
        assert SandwichType != null : "fx:id=\"SandwichType\" was not injected: check your FXML file 'Order.fxml'.";
        assert IncludedIngredientsTextArea != null : "fx:id=\"IncludedIngredientsListView\" was not injected: check your FXML file 'Order.fxml'.";
        assert SandwichImageView != null : "fx:id=\"SandwichImageView\" was not injected: check your FXML file 'Order.fxml'.";
        assert AvailableExtrasListView != null : "fx:id=\"AvailableExtrasListView\" was not injected: check your FXML file 'Order.fxml'.";
        assert ExtrasListView != null : "fx:id=\"ExtrasListView\" was not injected: check your FXML file 'Order.fxml'.";
        assert TotalPrice != null : "fx:id=\"TotalPrice\" was not injected: check your FXML file 'Order.fxml'.";

        setupSandwichTypes();
        AvailableExtrasListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        SandwichType.getSelectionModel().selectFirst();
        OnSandwichTypeSelected(null);

        return;
    }

    // Passes the cart scene from the main class to the order controller
    public void setCartController(CartController cartController) {
        CartController = cartController;
        return;
    }

    // Sets up the options for the SandwichType ComboBox
    private void setupSandwichTypes() {
        ObservableList<String> items = SandwichType.getItems();
        ArrayList<String> options = new ArrayList<String>(Arrays.asList(Sandwich.getSandwichTypeOptions()));

        items.setAll(options);

        return;
    }

    // Adds text to the UI output
    private void output(String text) {
        Output.appendText(text);
        return;
    }

    // Sets the image view to represent the current sandwich
    private void refreshImage() {
        if (currentSandwich == null)
            return;

        String imageLocation = currentSandwich.getImageLocation();

        Image image;
        try {
            image = new Image(imageLocation);
        } catch(NullPointerException e) {
            //output("Image not found.");
            System.out.println("Image not found.");
            return;
        } catch(IllegalArgumentException e) {
            //output("Image invalid.");
            System.out.println("Image invalid.");
            return;
        }

        SandwichImageView.setImage(image);

        return;
    }

    // Sets the text of the IncludedIngredientsTextArea
    private void refreshIncludedIngredients() {
        if (currentSandwich == null)
            return;

        String[] includedIngredients = currentSandwich.getIncluded();

        String text = "";
        for (String ingredient : includedIngredients) {
            text += ingredient + "\n";
        }

        IncludedIngredientsTextArea.setText(text);

        return;
    }

    // Sets the items of the ExtrasListView to the current extras of the sandwich
    // Sets the items of the AvailableExtrasListView to the extras unequipped
    private void refreshExtraIngredients() {
        Object[] extras;
        if (currentSandwich == null) {
            extras = new Object[0];
        } else {
            extras = currentSandwich.getExtras();
        }

        ArrayList<String> extraIngredients = new ArrayList<String>();
        ObservableList<String> items;

        // Convert all extras to strings
        for(Object obj : extras) {
            Extra extra = (Extra)obj;
            extraIngredients.add(extra.toString());
        }

        // Display all extras' strings into the ExtrasListView
        items = ExtrasListView.getItems();
        items.setAll(extraIngredients);

        ArrayList<String> availableExtras = new ArrayList<String>();

        // Get all the ingredients not equipped
        for (String ingredient : Extra.getAvailableExtrasOptions()) {
            if (!extraIngredients.contains(ingredient)) {
                availableExtras.add(ingredient);
            }
        }

        // Display all unequipped extras' strings in the AvailableExtrasListView
        items = AvailableExtrasListView.getItems();
        items.setAll(availableExtras);

        return;
    }

    // Sets the TotalPrice to the price of the base sandwich + the price of all the extras
    private void refreshPrice() {
        double price;
        if (currentSandwich == null) {
            price = 0;
        } else {
            price = currentSandwich.price();
        }

        TotalPrice.setText(PRICE_FORMAT.format(price));

        return;
    }

    // Clear the screen and sandwich
    private void clearInput() {
        SandwichType.getSelectionModel().selectFirst();
        OnSandwichTypeSelected(null);

        return;
    }
}
