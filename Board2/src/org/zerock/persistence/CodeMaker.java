package org.zerock.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;

public class CodeMaker {

	public static void main(String[] args) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.23:1521:xe", "system", "101412");

		PreparedStatement pstmt = con.prepareStatement("select * from tbl_board2");

		ResultSet rs = pstmt.executeQuery();

		ResultSetMetaData meta = rs.getMetaData();

		int count = meta.getColumnCount();

		for (int i = 1; i <= count; i++) {

			int typeValue = meta.getColumnType(i);

			String colName = meta.getColumnName(i);

			System.out.print("private ");

			if (typeValue == Types.VARCHAR) {
				System.out.print("String ");
			} else if (typeValue == Types.NUMERIC) {
				System.out.print("int ");
			} else if (typeValue == Types.DATE || typeValue == Types.TIMESTAMP) {
				System.out.print("Date ");
			}

			System.out.println(colName.toLowerCase() + ";");

		}
	}

}
