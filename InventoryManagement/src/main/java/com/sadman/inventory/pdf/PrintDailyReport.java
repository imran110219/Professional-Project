package com.sadman.inventory.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.sadman.inventory.entity.Invoice;
import com.sadman.inventory.model.InvoiceModel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Sadman
 */
public class PrintDailyReport {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private double totalSum = 0;

    public void generateReport() {

        try {
            Document document = new Document();

            Calendar cal = Calendar.getInstance();

            int date = cal.get(Calendar.DATE);
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);

            FileOutputStream fs = new FileOutputStream("C:/data/Daily-Report"+"-"+year+"-"+month+"-"+date+".pdf");

            PdfWriter writer = PdfWriter.getInstance(document, fs);
            document.open();

            addDate(document);
            addTitle(document);
            addTable(document);
            addSummary(document);

            document.close();
        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void addDate(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Paragraph paragraph = new Paragraph("Date: " + simpleDateFormat.format(new Date()), smallBold);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        preface.add(paragraph);
        document.add(preface);
    }

    private void addTitle(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();
        Paragraph paragraph = new Paragraph("Daily Report", catFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        preface.add(paragraph);
        addEmptyLine(preface,1);
        document.add(preface);
    }

    private void addTable(Document document) throws DocumentException {

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

        InvoiceModel invoiceModel = new InvoiceModel();

        List<Invoice> invoices = invoiceModel.getInvoicesByDate();

        if(!invoices.isEmpty()) {
            for (Invoice i : invoices) {
                table.addCell(i.getEmployee().getUserName());
                table.addCell(String.valueOf(i.getPayable()));
                table.addCell(String.valueOf(i.getDate()));
                totalSum += i.getPayable();
            }
        }
        else {
            PdfPCell cell = new PdfPCell();
            Paragraph paragraph = new Paragraph("No Transaction Today");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            cell.setPhrase(paragraph);
            cell.setColspan(3);
            table.addCell(cell);
        }

        document.add(table);
    }

    private void addSummary(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface,2);
        Paragraph paragraph = new Paragraph("Total Sale: " + totalSum + " BDT", smallBold);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        preface.add(paragraph);
        document.add(preface);
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
