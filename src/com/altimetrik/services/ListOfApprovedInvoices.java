package com.altimetrik.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.altimetrik.DAO.InsertingData;
import com.altimetrik.controller.SendAcknowelededEmailToSender;

public class ListOfApprovedInvoices extends InsertingData {
	public void listOfApprovedInvovices() throws SQLException {
		try {

			String approvedInvoices = "select INVOICE_NUMBER from INVOICE_ACCOUNT_PAYABLE where INVOICE_STATUS='Approved'";
			ResultSet resultApprovedList = statement.executeQuery(approvedInvoices);
			if (resultApprovedList.next()) {
				while (resultApprovedList.next())
					System.out.println(resultApprovedList.getString(1));
			} else
				System.out.println("Approved status not available...");

			SendAcknowelededEmailToSender.send();
			resultApprovedList.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

}
