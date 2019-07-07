package com.altimetrik.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.altimetrik.DAO.InsertingData;

public class ListOfRejectedInvoices extends InsertingData {
	public void listOfRejectedInvovices() throws SQLException {
		try {
			String listOfRejectedInvoices ="select INVOICE_NUMBER from INVOICE_ACCOUNT_PAYABLE where INVOICE_STATUS='Rejected'";
			ResultSet resultRejectedList = statement.executeQuery(listOfRejectedInvoices);
			if(resultRejectedList.next()) {
				while (resultRejectedList.next())
					System.out.println(resultRejectedList.getString(1));
			}
			else
				System.out.println("Rejected data is not available...");
			resultRejectedList.close();
		

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

}
