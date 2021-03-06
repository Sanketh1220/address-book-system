/****************************************************
 * Purpose : Program is written to handle Database a contacts into address_book_service database
 *
 * @author Sanketh Chigurupalli
 * @version 1.0
 * @since 09-05-2021
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
             * Here we are displaying a data
             * Looping through data
             * UC-16
             */
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts");

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

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

            /**
             * UC-18
             * Altered table using statement
             */
            Statement statement1 = connection.createStatement();
            statement1.executeUpdate("ALTER TABLE contacts ADD date_added date;");

            /**
             * Created a SQL statement to get contacts between particular interval
             */
            Statement statement2 = connection.createStatement();
            ResultSet resultSet2 = statement2.executeQuery("SELECT * from contacts WHERE date_added BETWEEN " +
                    "CAST('2017-01-01' AS DATE) AND DATE(NOW());");

            /**
             * UC-19
             * Getting a data of contacts by city or state name
             */
            Statement statement3 = connection.createStatement();
            ResultSet resultSet3 = statement3.executeQuery("Select * from contacts where city = 'Hyderabad';");
            ResultSetMetaData resultSetMetaData3 = resultSet3.getMetaData();

            /**
             * Untill the table has next element it keeps printing data
             */
            while (resultSet3.next()) {
                for(int i = 1; i <= resultSetMetaData3.getColumnCount(); i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet3.getString(i);
                    System.out.print(resultSetMetaData3.getColumnName(i) + " - " +columnValue );
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
