package com.altimetrik.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.altimetrik.DAO.InsertingData;

public class UpadateInvoiceStatus extends InsertingData {
	public void update() throws SQLException {
		try {
			System.out.println(
					"Please enter the INVOICE NUMBER from the above displayed list, in order to update the status of respective Invoice");
			Scanner scanner = new Scanner(System.in);
			String invoiceNumb = scanner.next();
			int option = 0;
			System.out.println("Please select the update options provided..");
			System.out.println("1.Approved\t\t\n 2.Rejected\t\t\n 3.Exit\t\t\n");
			String squery = "select * from INVOICE_ACCOUNT_PAYABLE where INVOICE_NUMBER=invoiceNumb";
			ResultSet resultSet = statement.executeQuery(squery);
			switch (option) {
			case 1:
				while (resultSet.next()) {
					String getStatus = resultSet.getString(6);
					getStatus = "Approved";
					System.out.println(resultSet.getString(6));
				}
				break;
			case 2:
				while (resultSet.next()) {
					String getStatus = resultSet.getString(6);
					getStatus = "Rejected";
					System.out.println(resultSet.getString(6));
				}
				break;
			default:
				System.out.println("OOPs!!! Sorry.....It's an Invalid Choice");
				break;

			}
			resultSet.close();
			statement.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
