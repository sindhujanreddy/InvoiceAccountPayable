package com.altimetrik.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.altimetrik.DAO.InsertingData;

public class ListOfApprovalPendingInvoices extends InsertingData {
	public void listApprovalPendingList(Statement statement) throws SQLException {
		try {
			String listOfApprovalPendingInvoices ="select invoiceNo from INVOICE_ACCOUNT_PAYABLE where INVOICE_STATUS='null'";
			ResultSet resultInvoicePendingList = statement.executeQuery(listOfApprovalPendingInvoices);
			if(resultInvoicePendingList.next()) {
				while (resultInvoicePendingList.next())
					System.out.println(resultInvoicePendingList.getString(1));
			}
			else
				System.out.println("Null data is not available...");
			
			resultInvoicePendingList.close();
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
