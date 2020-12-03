package com.sadman.inventory.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.sadman.inventory.entity.Item;
import com.sadman.inventory.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

public class PrintInvoice {

    private final ObservableList<Item> items;
    private final String barcode;
    private final double discount;
    private final double netpayable;

    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private double totalSum = 0;

    public PrintInvoice(ObservableList<Item> items, String barcode, double discount, double netpayable) {
        this.items = FXCollections.observableArrayList(items);
        this.barcode = barcode;
        this.discount = discount;
        this.netpayable = netpayable;
    }

    public void generateReport() {

        try {
            Document document = new Document();

            Calendar cal = Calendar.getInstance();

            int date = cal.get(Calendar.DATE);
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);

            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);

            FileOutputStream fs = new FileOutputStream("C:/data/Report"+"-"+year+"-"+month+"-"+date+"-"+hour+"-"+minute+"-"+second+".pdf");
            PdfWriter writer = PdfWriter.getInstance(document, fs);
            document.open();

            Paragraph paragraph = new Paragraph("Product ID");
            document.add(paragraph);
            addEmptyLine(paragraph, 5);

            PdfContentByte cb = writer.getDirectContent();
            BarcodeEAN codeEAN = new BarcodeEAN();
            codeEAN.setCodeType(codeEAN.EAN13);
            codeEAN.setCode(barcode);
            document.add(codeEAN.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.DARK_GRAY));
            addEmptyLine(paragraph, 5);

            PdfPTable table = createTable();
            document.add(table);
            addSummary(document);

            document.close();
        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private PdfPTable createTable() {

        PdfPTable table = new PdfPTable(4);
        PdfPCell c1 = new PdfPCell(new Phrase("Item"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantity"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for (Item i : items) {
            table.addCell(i.getItemName());
            table.addCell(String.valueOf(i.getUnitPrice()));
            table.addCell(String.valueOf(i.getQuantity()));
            table.addCell(String.valueOf(i.getTotal()));
        }

        return table;
    }

    private void addSummary(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface,2);
        Paragraph paragraph1 = new Paragraph("Discount: " + discount + " BDT", smallBold);
        paragraph1.setAlignment(Element.ALIGN_LEFT);
        preface.add(paragraph1);
        addEmptyLine(preface,1);
        Paragraph paragraph2 = new Paragraph("Netpayable: " + netpayable + " BDT", smallBold);
        paragraph2.setAlignment(Element.ALIGN_LEFT);
        preface.add(paragraph2);
        addEmptyLine(preface,1);
        Paragraph paragraph3 = new Paragraph("In Word: " + Util.convertNumberToWord((int)netpayable) + " BDT", smallBold);
        paragraph3.setAlignment(Element.ALIGN_LEFT);
        preface.add(paragraph3);
        document.add(preface);
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
