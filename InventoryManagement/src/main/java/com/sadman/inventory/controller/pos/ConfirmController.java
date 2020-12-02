package com.sadman.inventory.controller.pos;

import com.sadman.inventory.entity.Item;
import com.sadman.inventory.pdf.PrintInvoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmController implements Initializable {

    @FXML
    private TextArea billingArea;
    @FXML
    private Label retailLabel;
    private double retail;
    private ObservableList<Item> items;
    private String barcode;
    private double discount;
    private double netpayable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        retailLabel.setText("Change: " + retail);


        StringBuilder details = new StringBuilder("Item Name\t\t\t" + "Cost\t\t\t" + "Quantity\t\t\t" + "Total\n");

        for (Item i : items) {
            netpayable += i.getTotal();
            details.append(i.getItemName())
                    .append("\t\t\t")
                    .append(i.getUnitPrice())
                    .append("\t\t\t")
                    .append(i.getQuantity())
                    .append("\t\t\t")
                    .append(i.getTotal())
                    .append("\n");
        }
        netpayable = netpayable - discount;
        details.append("\n");
        details.append("Discount : " + discount);
        details.append("\n");
        details.append("Netpayable : " + netpayable);

        billingArea.setText(details.toString());
    }

    public void setData(double retail, ObservableList<Item> items, String barcode, double discount) {
        this.retail = retail;
        this.items = FXCollections.observableArrayList(items);
        this.barcode = barcode;
        this.discount = discount;
    }

    @FXML
    public void doneAction(ActionEvent event) {
        billingArea.setText("");
        PrintInvoice pi = new PrintInvoice(items, barcode, discount, netpayable);
        pi.generateReport();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
