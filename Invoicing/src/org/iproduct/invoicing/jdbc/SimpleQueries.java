/* COPYRIGHT & LICENSE HEADER
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
*
* IPTInvoiceServer is part of multi-tier client-server invoicing application 
* using JPA, Swing and RMI technologies.
* 
* Copyright (c) 2012 - 2016 IPT - Intellectual Products & Technologies Ltd.
* All rights reserved.
*
* E-mail: office@iproduct.org
* Web: http://iproduct.org/
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License (the "License")
* as published by the Free Software Foundation version 2 of the License.
* You may not use this file except in compliance with the License. You can
* obtain a copy of the License at root directory of this project in file
* LICENSE.txt.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License along
* with this program; if not, write to the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
* When distributing the software, include this COPYRIGHT & LICENSE HEADER
* in each file, and include the License file LICENSE.txt in the root directory
* of your distributable.
*
* GPL Classpath Exception:
* IPT - Intellectual Products & Technologies (IPT) designates this particular
* file as subject to the "Classpath" exception as provided by IPT in
* the GPL Version 2 License file that accompanies this code.
*
* In case you modify this file,
* please add the appropriate notice below the existing Copyright notices,
* with the fields enclosed in brackets {} replaced by your own identification:
* "Portions Copyright (c) {year} {name of copyright owner}"
*/

package org.iproduct.invoicing.jdbc;

import static org.iproduct.invoicing.jdbc.DBConnect.dbDriver;
import static org.iproduct.invoicing.jdbc.DBConnect.dbURL;
import static org.iproduct.invoicing.jdbc.DBConnect.password;
import static org.iproduct.invoicing.jdbc.DBConnect.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SimpleQueries {

	public static void main(String[] args) {
		// 1. Load Db Driver
		try {
			Class.forName(dbDriver);
			// 2. & 3. Crate DB connection and statement
			try (Connection c = DriverManager.getConnection(dbURL, user, password); 
				Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT)) {
//				// 4. Execute query
//				PreparedStatement ps = c.prepareStatement("SELECT * FROM items WHERE price > ?",
//						ResultSet.TYPE_SCROLL_INSENSITIVE, 
//						ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
//				ps.setDouble(1, 5.00);
//				
//				ResultSet rs = ps.executeQuery();
				// 5. Process ResultSet
//				printTable(rs);
				//OR Execute update
				int numberUpdates = s.executeUpdate("INSERT INTO items (name,vendor,price)"
						+ " VALUES ('Dummy Product', 'Noname Vendor', 15.60);");
//				int numberUpdates = s.executeUpdate("UPDATE items SET price=60.2"
//						+ " WHERE name='Dummy Product';");
//				int numberUpdates = s.executeUpdate("DELETE FROM items "
//						+ " WHERE name='Dummy Product';");
//				System.out.println("\nNumber records changed: " + numberUpdates);
				ResultSet rs = s.executeQuery("SELECT * FROM items");
				printTable(rs);
				
				//Experiments ...
				System.out.println();
				//RS update
//				rs.absolute(3);
//				rs.updateDouble("price", 2.5);
//				rs.updateRow();
				
				//RS insert
//				rs.moveToInsertRow(); // moves cursor to the insert row
//		        rs.updateString("name", "Notebook"); // updates the
//		        rs.updateString("vendor", "Office 1"); // updates the
//		          // first column of the insert row to be AINSWORTH
//		        rs.updateDouble("price", 9.7); // updates the second column to be 35
//		        rs.updateString("group", "consumables"); // updates the
//		        rs.insertRow();
//		        rs.moveToCurrentRow();
				
				//RS delete
//				rs.absolute(5);
//				rs.deleteRow();
//
//		        System.out.println();
//				int count = rs.getMetaData().getColumnCount();
//				for (int column = 1; column <= count; column++) {
//					String value = rs.getString(column);
//					System.out.print(String.format("%-16.16s |", value));
//				}
//				System.out.println();
//				
//				System.out.println();
//				rs = s.executeQuery("SELECT * FROM items");
//				printTable(rs);
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void printTable(ResultSet rs) throws SQLException {
		int count = rs.getMetaData().getColumnCount();
		for (int i = 1; i <= count; i++) {
			String name = rs.getMetaData().getColumnName(i);
			System.out.print(String.format("%-16s |", name));
		}
		System.out.println();

		while (rs.next()) {
			for (int column = 1; column <= count; column++) {
				String value = rs.getString(column);
				System.out.print(String.format("%-16.16s |", value));
			}
			System.out.println();
		}
	}

}
