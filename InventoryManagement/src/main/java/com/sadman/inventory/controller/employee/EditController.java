package com.sadman.inventory.controller.employee;

import com.sadman.inventory.entity.Employee;
import com.sadman.inventory.interfaces.EmployeeInterface;
import com.sadman.inventory.model.EmployeeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable, EmployeeInterface {

    @FXML
    private TextField firstField, lastField, usernameField, emailField, phoneField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextArea addressArea;
    @FXML
    private Button saveButton;
    private long selectedEmployeeId;
    private EmployeeModel employeeModel;
    private Employee employee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeModel = new EmployeeModel();
        resetValues();
    }

    public void setEmployee(Employee employee, long selectedEmployeeId) {
        this.employee = employee;
        this.selectedEmployeeId = selectedEmployeeId;
        setData();
    }

    @FXML
    public void handleSave(ActionEvent event) {

        if (validateInput()) {

            Employee editedEmployee = new Employee(
                    employee.getId(),
                    firstField.getText(),
                    lastField.getText(),
                    usernameField.getText(),
                    emailField.getText(),
                    DigestUtils.sha1Hex(passwordField.getText()),
                    phoneField.getText(),
                    addressArea.getText()
            );

            employeeModel.updateEmployee(editedEmployee);
            EMPLOYEELIST.set((int) selectedEmployeeId, editedEmployee);

            ((Stage) saveButton.getScene().getWindow()).close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Employee Updated!");
            alert.setContentText("Employee is updated successfully");
            alert.showAndWait();
        }
    }

    private void setData() {
        firstField.setText(employee.getFirstName());
        lastField.setText(employee.getLastName());
        usernameField.setText(employee.getUserName());
        emailField.setText(employee.getEmail());
        passwordField.setText(employee.getPassword());
        phoneField.setText(employee.getPhone());
        addressArea.setText(employee.getAddress());
    }

    private void resetValues() {
        firstField.setText("");
        lastField.setText("");
        usernameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        phoneField.setText("");
        addressArea.setText("");
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        resetValues();
    }

    private boolean validateInput() {

        String errorMessage = "";

        if (firstField.getText() == null || firstField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }

        if (lastField.getText() == null || lastField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }

        if (usernameField.getText() == null || usernameField.getText().length() == 0) {
            errorMessage += "No valid username!\n";
        }

        else if (employeeModel.checkUser(usernameField.getText())) {
            errorMessage += "Duplicate username!\n";
        }

        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "No valid email!\n";
        }

        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "No valid password!\n";
        }

        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage += "No valid phone number!\n";
        }

        if (addressArea.getText() == null || addressArea.getText().length() == 0) {
            errorMessage += "No email address!\n";
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
