package com.sadman.inventory.controller.product;

import com.sadman.inventory.entity.Category;
import com.sadman.inventory.entity.Product;
import com.sadman.inventory.entity.Supplier;
import com.sadman.inventory.interfaces.ProductInterface;
import com.sadman.inventory.model.CategoryModel;
import com.sadman.inventory.model.ProductModel;
import com.sadman.inventory.model.SupplierModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable, ProductInterface {

    @FXML
    private TextField nameField, priceField, quantityField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private ComboBox categoryBox, supplierBox;
    @FXML
    private Button saveButton;
    private ProductModel productModel;
    private CategoryModel categoryModel;
    private SupplierModel supplierModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productModel = new ProductModel();
        categoryModel = new CategoryModel();
        supplierModel = new SupplierModel();
        ObservableList<Category> categoryList = FXCollections.observableArrayList(categoryModel.getCategories());
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList(supplierModel.getSuppliers());
        categoryBox.setItems(categoryList);
        supplierBox.setItems(supplierList);
    }

    @FXML
    public void handleSave(ActionEvent event) {

        if (validateInput()) {

            Category category = (Category) categoryBox.getSelectionModel().getSelectedItem();
            Supplier supplier = (Supplier) supplierBox.getSelectionModel().getSelectedItem();

            Product product = new Product(
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Double.parseDouble(quantityField.getText()),
                    descriptionArea.getText(),
                    category,
                    supplier
            );

            productModel.saveProduct(product);
            PRODUCTLIST.clear();
            PRODUCTLIST.addAll(productModel.getProducts());

            ((Stage) saveButton.getScene().getWindow()).close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Product is added");
            alert.setContentText("Product is added successfully");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
        descriptionArea.setText("");
        categoryBox.valueProperty().setValue(null);
    }

    private boolean validateInput() {

        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }

        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "No valid price!\n";
        }

        if (quantityField.getText() == null || quantityField.getText().length() == 0) {
            errorMessage += "No valid quantity!\n";
        }

        if (descriptionArea.getText() == null || descriptionArea.getText().length() == 0) {
            errorMessage += "No email description!\n";
        }

        if (categoryBox.getSelectionModel().isEmpty()) {
            errorMessage += "Please select the category!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
