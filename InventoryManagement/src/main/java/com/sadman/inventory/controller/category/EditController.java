package com.sadman.inventory.controller.category;

import com.sadman.inventory.entity.Category;
import com.sadman.inventory.interfaces.CategoryInterface;
import com.sadman.inventory.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable, CategoryInterface {

    @FXML
    private TextField typeField;
    @FXML
    private TextArea descriptionArea;
    private long selectedCategoryId;
    @FXML
    private Button saveButton;
    private CategoryModel categoryModel;
    private Category category;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        categoryModel = new CategoryModel();
        resetValues();
    }

    public void setCategory(Category category, long selectedCategoryId) {
        this.category = category;
        this.selectedCategoryId = selectedCategoryId;
        setData();
    }

    @FXML
    public void handleSave(ActionEvent event) {

        if (validateInput()) {

            Category editedCategory = new Category(
                    category.getId(),
                    typeField.getText(),
                    descriptionArea.getText()
            );

            categoryModel.updateCategory(editedCategory);
            CATEGORYLIST.clear();
            CATEGORYLIST.addAll(categoryModel.getCategories());

            ((Stage) saveButton.getScene().getWindow()).close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Category Updated!");
            alert.setContentText("Category is updated successfully");
            alert.showAndWait();
        }
    }

    private void setData() {
        typeField.setText(category.getType());
        descriptionArea.setText(category.getDescription());
    }

    private void resetValues() {
        typeField.setText("");
        descriptionArea.setText("");
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        resetValues();
    }

    private boolean validateInput() {

        String errorMessage = "";

        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }

        else if (!categoryModel.getCategory(selectedCategoryId).getType().equals(typeField.getText()) && categoryModel.checkCategory(typeField.getText())) {
            errorMessage += "Duplicate category type!\n";
        }

        if (descriptionArea.getText() == null || descriptionArea.getText().length() == 0) {
            errorMessage += "No email description!\n";
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
