package com.altimetrik.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.altimetrik.DAO.InsertingData;

public class ListOfAllInvoices extends InsertingData {
	public void listAllInvoices(Statement st) throws SQLException {
		try {
			String listOfAllInvoices = ("select * from INVOICE_ACCOUNT_PAYABLE");
			ResultSet res = statement.executeQuery(listOfAllInvoices);
			while (res.next()) {
				System.out.print("INVOICE NUMBER   :" + res.getString(1));
				System.out.print("INVOICE DATE     :" + res.getString(2));
				System.out.print("TOTAL AMOUNT     :" + res.getString(5));
				System.out.println("\n");

			}
			res.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
