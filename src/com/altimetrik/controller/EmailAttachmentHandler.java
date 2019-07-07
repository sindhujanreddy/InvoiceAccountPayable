package com.altimetrik.controller;

import java.io.File;
import java.sql.Statement;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.altimetrik.DAO.InsertingData;

public class EmailAttachmentHandler {
	public static String invoiceNo = "";
	public static String invoiceDate = "";
	public static String customerPO = "";
	public static String address = "";
	public static String amount = "";
	public static Statement insertstatement = null;

	public static Statement attachmentReaderAndParser(File targetFile) {

		try {
			File file = new File("D:\\javatraining\\Acushnet-3.pdf");
			PDDocument document = PDDocument
					.load(RecieveEmailWithAttachment.targetFile != null ? RecieveEmailWithAttachment.targetFile : file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String text = pdfStripper.getText(document);
			String lines[] = text.split("\n");
			for (int i = 0; i < lines.length; i++) {
				if (lines[i].trim().equals("Invoice No")) {
					invoiceNo = lines[i + 1].trim();
				}
				if (lines[i].trim().equals("Invoice Date"))
					invoiceDate = lines[i + 1].trim();
				if (lines[i].trim().equals("Customer P.O."))
					customerPO = lines[i + 1].trim();
				if (lines[i].trim().equals("Sold To")) {
					while (!lines[++i].trim().startsWith("Ship To"))
						address += lines[i] + " ";

				}

			}

			// Closing the document
			document.close();

			outer: for (int i = 0; i < lines.length; i++) {
				if (lines[i].trim().equals("Total Invoice")) {
					for (int j = i + 1; j < lines.length; j++) {
						if (!lines[j + 1].trim().startsWith("$")) {
							amount = lines[j].trim();
							amount = amount.replace(",", "");
							amount = amount.replace("$", "");
							break outer;
						}
					}
				}

			}

			System.out.println("Invoice No :" + invoiceNo);
			System.out.println("Invoice Date :" + invoiceDate);
			System.out.println("Cust PO :" + customerPO);
			System.out.println("Address :" + address);
			System.out.println("Total Amount " + amount);

			// call to insertInvoiceData
			InsertingData insertData = new InsertingData();
			insertstatement = insertData.insertInvoiceData(invoiceNo, invoiceDate, customerPO, address, amount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertstatement;

	}
}
