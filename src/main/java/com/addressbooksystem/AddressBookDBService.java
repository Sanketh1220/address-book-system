/****************************************************
 * Purpose : Program is written to handle Database a contacts into address_book_service database
 *
 * @author Sanketh Chigurupalli
 * @version 1.0
 * @since 28-04-2021
 *
 ****************************************************/

package com.addressbooksystem;

import java.sql.*;

/**
 * Creating a connection between server and database
 */
public class AddressBookDBService {
    public static void main(String[] args) throws SQLException {

        /**
         *  Declaring variables for Database Url
         *  Username and password and intialized the Connection as null
         */
        String url = "jdbc:mysql://localhost:3306/address_book_service";
        String username = "root";
        String password = "Sanketh@11";
        Connection connection = null;

        /**
         *  Try catch method for assigning the drivers of MYSQL
         *  Connection is created between Driver and database
         *  Created statement for retreiving data from database
         *  Executed the statement
         *  ResultSetMetaData gets the meta data of the particular table
         */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            /**
             * Untill the table has next element it keeps printing data
             */
            while (resultSet.next()) {
                for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(resultSetMetaData.getColumnName(i) + " - " +columnValue );
                }
                System.out.println();
            }

            /**
             * Handling the ClassNotFound exception and SQL exception
             */
        } catch (ClassNotFoundException exception) {

        } finally {
            if(connection != null) {
                connection.close();
            }
        }
    }
}
