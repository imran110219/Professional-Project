package com.sadman.inventory.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.sadman.inventory.entity.Invoice;
import com.sadman.inventory.entity.Item;
import com.sadman.inventory.model.InvoiceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author Sadman
 */
public class PrintDailyReport {

    private InvoiceModel invoiceModel;
    private ObservableList<Item> items;
//    private String barcode;

    public void generateReport() {

        try {
            Document document = new Document();
            FileOutputStream fs = new FileOutputStream("Report_Daily.pdf");
            PdfWriter writer = PdfWriter.getInstance(document, fs);
            document.open();

            Paragraph paragraph = new Paragraph("Daily Report");
            document.add(paragraph);
            addEmptyLine(paragraph, 5);

//            PdfContentByte cb = writer.getDirectContent();
//            BarcodeEAN codeEAN = new BarcodeEAN();
//            codeEAN.setCodeType(codeEAN.EAN13);
//            document.add(codeEAN.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.DARK_GRAY));
            addEmptyLine(paragraph, 5);

            PdfPTable table = createTable();
            document.add(table);

            document.close();
        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private PdfPTable createTable() {

        PdfPTable table = new PdfPTable(3);
        PdfPCell c1 = new PdfPCell(new Phrase("Employee"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for (Invoice i : invoiceModel.getInvoicesByDate()) {
            table.addCell(i.getEmployee().getUserName());
            table.addCell(String.valueOf(i.getPayable()));
            table.addCell(String.valueOf(i.getDate()));
        }
        return table;
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
