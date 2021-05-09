/****************************************************
 * Purpose : Program is written to collect a contacts into different address books
 *
 * @author Sanketh Chigurupalli
 * @version 1.0
 * @since 28-04-2021
 *
 ****************************************************/

package com.addressbooksystem;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import com.google.gson.Gson;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<ContactOfPerson> contactList;
    public HashMap<String, ArrayList<ContactOfPerson>> personByState;
    public HashMap<String, ArrayList<ContactOfPerson>> personByCity;

    /**
     * parsing a city as String and contacts as arraylist
     * parsing a state as String and contacts as arraylist
     */
    public AddressBook() {
        personByCity = new HashMap<String, ArrayList<ContactOfPerson>>();
        personByState = new HashMap<String, ArrayList<ContactOfPerson>>();
        contactList = new ArrayList<>();
    }

    /**
     * method to add contact
     * @return
     */
    public ArrayList<ContactOfPerson> addContactDetails() {
        System.out.println("Enter the Details of ContactDetails");
        System.out.println("Enter the first name");
        String firstName = sc.next();
        if (checkDuplicate(firstName)) {
            System.out.println("Person is already exist");
        } else {
            System.out.println("Enter the Last name");
            String lastName = sc.next();
            System.out.println("Enter the Address");
            String address = sc.next();
            System.out.println("Enter the City");
            String city = sc.next();
            System.out.println("Enter the State");
            String state = sc.next();
            System.out.println("Enter the email");
            String email = sc.next();
            System.out.println("Enter the ZipCode");
            String zip = sc.next();
            System.out.println("Enter the Contact number");
            String phoneNumber = sc.next();
            ContactOfPerson contactofPerson = new ContactOfPerson(firstName, lastName, address, city, state, email, phoneNumber, zip);
            contactList.add(contactofPerson);
            if (!personByState.containsKey(state)) {
                personByState.put(state, new ArrayList<ContactOfPerson>());
            }
            personByState.get(state).add(contactofPerson);

            if (!personByCity.containsKey(city)) {
                personByCity.put(city, new ArrayList<ContactOfPerson>());
            }
            personByCity.get(city).add(contactofPerson);
        }
        return contactList;
    }

    /**
     * method to edit contact
     * @param Name
     * @return
     */
    public boolean editContactDetails(String Name) {
        int flag = 0;
        for (ContactOfPerson contact : contactList) {
            if (contact.getFirstName().equals(Name)) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Address: ");
                String address = sc.next();
                contact.setAddress(address);
                System.out.println("Enter City: ");
                String city = sc.next();
                contact.setCity(city);
                System.out.println("Enter State: ");
                String state = sc.next();
                contact.setState(state);
                System.out.println("Enter Email: ");
                String email = sc.next();
                contact.setEmail(email);
                System.out.println("Enter Phone Number:");
                String phoneNumber = sc.next();
                contact.setPhoneNumber(phoneNumber);
                System.out.println("Enter Zip Code: ");
                String zip = sc.next();
                contact.setZip(zip);
                flag = 1;
            }
        }
        return flag == 1;
    }

    /**
     * method to contact date by name
     * @param name
     * @return
     */
    public boolean deleteContact(String name) {
        int flag = 0;
        for (ContactOfPerson contact : contactList) {
            if (contact.getFirstName().equals(name)) {
                contactList.remove(contact);
                flag = 1;
                break;
            }
        }
        return flag == 1;
    }

    /**
     * checks for the duplicate person in the present data
     * @param fname
     * @return
     */
    public boolean checkDuplicate(String fname) {
        int flag = 0;
        for (ContactOfPerson p : contactList) {
            if (p.getFirstName().equals(fname)) {
                flag = 1;
                break;
            }
        }
        return flag == 1;
    }

    /**
     * getting a person by state
     * @param State
     */
    public void getPersonNameByState(String State) {
        List<ContactOfPerson> list = contactList.stream().filter(contactName -> contactName.getState().equals(State)).collect(Collectors.toList());
        for (ContactOfPerson contact : list) {
            System.out.println("First Name: " + contact.getFirstName());
            System.out.println("Last Name: " + contact.getLastName());
        }

    }

    /**
     * Getting a person name by city name
     * @param cityName
     */
    public void getPersonNameByCity(String cityName) {
        List<ContactOfPerson> list = contactList.stream().filter(contactName -> contactName.getCity().
                equals(cityName)).collect(Collectors.toList());
        for (ContactOfPerson contact : list) {
            System.out.println("First Name: " + contact.getFirstName());
            System.out.println("Last Name: " + contact.getLastName());
        }
    }

    /**
     * Writing a data into txt file
     * @param addressBookMain
     */
    public static void writeData(AddressBookMain addressBookMain) {
        StringBuffer personBuffer = new StringBuffer();
        contactList.forEach(person -> {
            String personDataString = person.toString().concat("\n");
            personBuffer.append(personDataString);
        });
        try {
            Files.lines(new File("Contacts.txt").toPath()).map(String::trim).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reading data from txt file
     * @param addressBookMain
     */
    public static void readData(AddressBookMain addressBookMain) {
        try {
            Files.lines(new File("Contacts.txt").toPath()).map(String::trim).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * writing a data into CSV file
     * @throws IOException
     * @throws CsvRequiredFieldEmptyException
     * @throws CsvDataTypeMismatchException
     */
    public static void writeDataToCSV() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (Writer writer = Files.newBufferedWriter(Paths.get("Contacts.csv"));) {
            StatefulBeanToCsvBuilder<ContactOfPerson> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<ContactOfPerson> beanWriter = builder.build();
            beanWriter.write(contactList);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reading a data into CSV file
     * @throws IOException
     */
    public static void readDataFromCSV() throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get("Contacts.csv"));
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println("First Name = " + nextRecord[3]);
                System.out.println("Last Name = " + nextRecord[4]);
                System.out.println("Address = " + nextRecord[0]);
                System.out.println("City = " + nextRecord[1]);
                System.out.println("State = " + nextRecord[6]);
                System.out.println("Email = " + nextRecord[2]);
                System.out.println("Phone Number = " + nextRecord[5]);
                System.out.println("Zip Code = " + nextRecord[7]);
            }
        }
    }

    /**
     * writing a data into JSON file
     * @throws IOException
     */
    public static void writeDataToJSon() throws IOException {
        {
            Path filePath = Paths.get("Contacts.json");
            Gson gson = new Gson();
            String json = gson.toJson(contactList);
            FileWriter writer = new FileWriter(String.valueOf(filePath));
            writer.write(json);
            writer.close();
        }
    }

    /**
     * reading a data from JSON file
     * @throws IOException
     */
    public static void readDataFromJson() throws IOException {
        ArrayList<ContactOfPerson> contactList = null;
        Path filePath = Paths.get("Contacts.json");
        try (Reader reader = Files.newBufferedReader(filePath);) {
            Gson gson = new Gson();
            contactList = new ArrayList<ContactOfPerson>(Arrays.asList(gson.fromJson(reader, ContactOfPerson[].class)));
            for (ContactOfPerson contact : contactList) {
                System.out.println("Firstname : " + contact.getFirstName());
                System.out.println("Lastname : " + contact.getLastName());
                System.out.println("Address : " + contact.getAddress());
                System.out.println("City : " + contact.getCity());
                System.out.println("State : " + contact.getState());
                System.out.println("Zip Code : " + contact.getZip());
                System.out.println("Phone number : " + contact.getPhoneNumber());
                System.out.println("Email : " + contact.getEmail());
            }
        }
    }
}
