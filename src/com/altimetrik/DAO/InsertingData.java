package com.altimetrik.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertingData {

	static String invoiceNum = "", invoice_Date = "", customer_PO = "", addres = "", totalAmount = "";
	static final String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
	static final String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	static final String user = "System";
	static final String password = "12345";
	public static Connection connection = null;
	public static Statement statement = null;

	public Statement insertInvoiceData(String invoiceNo, String invoiceDate, String customerPO, String address,
			String amount) {
		invoiceNum = invoiceNo;
		invoice_Date = invoiceDate;
		customer_PO = customerPO;
		addres = address;
		totalAmount = amount;

		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(dbUrl, user, password);
			statement = connection.createStatement();

			String insertData = "insert into INVOICE_ACCOUNT_PAYABLE values('" + invoiceNo + "','" + invoiceDate + "','"
					+ customerPO + "','" + address + "','" + amount + "'," + null + "," + null + ")";

			statement.executeUpdate(insertData);

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

		return statement;
	}
}
