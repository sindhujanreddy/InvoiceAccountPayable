package com.altimetrik;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.altimetrik.DAO.InsertingData;
import com.altimetrik.controller.EmailAttachmentHandler;
import com.altimetrik.controller.RecieveEmailWithAttachment;
import com.altimetrik.services.ListOfAllInvoices;
import com.altimetrik.services.ListOfApprovalPendingInvoices;
import com.altimetrik.services.ListOfApprovedInvoices;
import com.altimetrik.services.ListOfRejectedInvoices;
import com.altimetrik.services.SearchInvoice;
import com.altimetrik.services.UpadateInvoiceStatus;

public class Application {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException, SQLException {
		Statement st = null;
		try {
			String pop3Host = "pop.gmail.com";
			String mailStoreType = "pop3";
			final String userName = "manvithabindhu@gmail.com";
			final String password = "Alti@123";

			// Call to receiveEmail
			RecieveEmailWithAttachment.receiveEmail(pop3Host, mailStoreType, userName, password);
			
			// call to attachmentReaderAndParser
			st = EmailAttachmentHandler.attachmentReaderAndParser(RecieveEmailWithAttachment.targetFile);
			
			Scanner scanner = new Scanner(System.in);
			int option = 0;
			do {
				System.out.println("WELCOME TO INVOICE ACCOUNT PAYABLE");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				System.out.println("\nSELECT THE CHOICE");
				System.out.println("1.LIST ALL INVOICES\t\t\n" + "2.UPDATE INVOICE STATUS\t\t\n"+ "3.SEARCH INVOICE\t\t\n"
						+ "4.LIST ALL APPROVED IVOICES\t\t\n" + "5.LIST OF APPROVAL PENDING INVOICES\t\t\n"
						+ "6.LIST ALL REJECTED IVOICES\t\t\n"  + "7.EXIT");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				option = scanner.nextInt();
				switch (option) {
				case 1:
					ListOfAllInvoices allInvoicesList = new ListOfAllInvoices();
					allInvoicesList.listAllInvoices(st);
					System.out.println("\n");
					break;
				case 2:
					UpadateInvoiceStatus status = new UpadateInvoiceStatus();
					status.update();
					break;
					
				case 3:
					ListOfApprovedInvoices approved = new ListOfApprovedInvoices();
					approved.listOfApprovedInvovices();
					System.out.println("\n");
					break;
				case 4:
					ListOfApprovalPendingInvoices pending = new ListOfApprovalPendingInvoices();
					pending.listApprovalPendingList(st);
					System.out.println("\n");
					break;
				case 5:
					ListOfRejectedInvoices reject = new ListOfRejectedInvoices();
					reject.listOfRejectedInvovices();
					break;
				case 6:
					System.out.println("Please enter the INVOICE NUMBER");
					String invoiceNUm = scanner.next();
					SearchInvoice si = new SearchInvoice();
					si.searchInvoice(st, invoiceNUm);
					System.out.println("\n");
					break;
				case 7:
					System.out.println("Thank You!");
					break;
				default:
					System.out.println("OOPs!!Sorry.....It's an Invalid Choice");
					break;
				}

				System.out.println("\n");
			} while (option != 0);

		} finally {
//			InsertingData.statement.close();
//			InsertingData.connection.close();
		}
	}

}
