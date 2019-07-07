package com.altimetrik.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.altimetrik.DAO.InsertingData;

public class SearchInvoice extends InsertingData {
	public void searchInvoice(Statement statement, String invoiceNum) throws SQLException {
		try {

			String displayInvoiceDetails = "select * from INVOICE_ACCOUNT_PAYABLE where INVOICE_NUMBER='" + invoiceNum + "'";
			if (statement == null) {
				System.out.println("Statement is null...");
			} else {
				ResultSet resultSet = statement.executeQuery(displayInvoiceDetails);
				while (resultSet.next()) {
					System.out.println("Invoice Number :" + resultSet.getString(1));
					System.out.println("Invoice Date :" + resultSet.getString(2));
					System.out.println("Cust PO :" + resultSet.getString(3));
					System.out.println("Address :\n" + resultSet.getString(4));
					System.out.println("Total Amount: " + resultSet.getString(5));
					System.out.println("Invoice Status: " + resultSet.getString(6));
				}
				resultSet.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

}
