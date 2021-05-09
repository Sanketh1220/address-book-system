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

            /**
             * Created a prepared statement for writing a data into contacts
             * Intializing a set of attributes to execute them
             * UC-17
             */
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("insert into contacts (first_name, last_name, address, city, state, zip_code, phone_number, email)" +
                            "values(?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1,"Vamsi");
            preparedStatement.setString(2,"Krishna");
            preparedStatement.setString(3,"Thimali");
            preparedStatement.setString(4, "kolkata");
            preparedStatement.setString(5, "Bengal");
            preparedStatement.setInt(6, 89654);
            preparedStatement.setString(7, "7654431221");
            preparedStatement.setString(8, "vamsi.krishna@gmail.com");

            int j = preparedStatement.executeUpdate();



            Statement statement = connection.createStatement();

            /**
             * Here we are displaying a data
             * Looping through data
             */
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

            connection.close();

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
