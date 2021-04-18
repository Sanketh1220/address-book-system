package com.addressbooksystem;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookMain {

    //create object of addressbook
    public AddressBook addressBook = new AddressBook();

    public static Map<String, AddressBook> addressBookListMap = new HashMap<>();

    public void addAddressBook(String addressBookName) {

        boolean flag = true;

        // flag=true declare to enter in to while loop
        while (flag) {
            Scanner sc= new Scanner(System.in);
            System.out.println("1.Add Contact");
            System.out.println("2.Edit Contact");
            System.out.println("3.Delete");
            System.out.println("4.Exit");
            System.out.println("Enter Choice: ");

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    addressBook.addContactDetails();
                    break;

                case 2:
                    System.out.println("Enter the Person First name to edit details: ");
                    String personName = sc.next();

                    boolean listEdited = addressBook.editContactDetails(personName);
                    if (listEdited) {
                        System.out.println("List Edited Successfully");
                    } else {
                        System.out.println("List Cannot be Edited");
                    }
                    break;

                case 3:
                    System.out.println("Enter the Contact to be deleted:");
                    String firstName = sc.next();
                    boolean listDeleted = addressBook.deleteContact(firstName);
                    if (listDeleted) {
                        System.out.println("Deleted Contact from the List");
                    } else {
                        System.out.println("List Cannot be Deleted");
                    }
                    break;
                default:

                    flag = true;

                case 4:
                    flag = false;
                    break;
            }
        }
        addressBookListMap.put(addressBookName, addressBook);
        System.out.println("Address Book Added Successfully");
    }

    public void searchPersonByState(String stateName) {
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            AddressBook value = entry.getValue();
            System.out.println("The Address Book: " + entry.getKey());
            value.getPersonNameByState(stateName);
        }
    }


    public void searchPersonByCity(String cityName) {
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            AddressBook value = entry.getValue();
            System.out.println("The Address Book: " + entry.getKey());
            value.getPersonNameByCity(cityName);
        }
    }

    public void viewPersonByStateUsingHashmap(String stateName) {
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            AddressBook value = entry.getValue();
            ArrayList<ContactOfPerson> contacts = value.personByState.entrySet().stream()
                    .filter(findState -> findState.getKey().equals(stateName)).map(Map.Entry::getValue).findFirst()
                    .orElse(null);
            for (ContactOfPerson contact : contacts) {
                System.out.println("First Name: " + contact.getFirstName() + " Last Name: " + contact.getLastName());
            }
        }
    }

    void viewPersonByCityUsingHashMap(String cityName) {
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            AddressBook value = entry.getValue();
            ArrayList<ContactOfPerson> contacts = value.personByCity.entrySet().stream()
                    .filter(findCity -> findCity.getKey().equals(cityName)).map(Map.Entry::getValue).findFirst()
                    .orElse(null);
            for (ContactOfPerson contact : contacts) {
                System.out.println("First Name: " + contact.getFirstName() + " Last Name: " + contact.getLastName());
            }
        }
    }

    public void CountByState(String state) {
        int count = 0;
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            for (int i = 0; i < (entry.getValue()).contactList.size(); i++) {
                ContactOfPerson contact = entry.getValue().contactList.get(i);

                if (state.equals(contact.getState())) {
                    count++;
                }
            }
        }
        System.out.println("Total Person Count in state " + state + ": " + count);
    }

    //CountByCity
    public void CountByCity(String city) {
        int countPersonInCity = 0;
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            for (int i = 0; i < (entry.getValue()).contactList.size(); i++) {
                ContactOfPerson d = entry.getValue().contactList.get(i);

                if (city.equals(d.getCity())) {
                    countPersonInCity++;
                }

            }
        }
        System.out.println("Total number of people in this city " + city + ": " + countPersonInCity);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Address Book System");
        AddressBookMain addressBookMain = new AddressBookMain();
        boolean flag = true;

        while (flag) {
            System.out.println("1.Add New Address Book");
            System.out.println("2.Search Contact from a city");
            System.out.println("3.Search Contact from a State");
            System.out.println("4.View contact By State ");
            System.out.println("5.View Contact by city ");
            System.out.println("6.Count Contact By State");
            System.out.println("7.Count Contact By City");
            System.out.println("8.Exit");

            System.out.println("Enter choice: ");
            int option = sc.nextInt();
            switch (option) {
                case 1: {
                    System.out.println("Enter the Name of Address Book: ");
                    String addressBookName = sc.next();
                    if (addressBookListMap.containsKey(addressBookName)) {
                        System.out.println("The Address book Already Exists");
                        addressBookMain.addAddressBook(addressBookName);
                        break;
                    } else {
                        addressBookMain.addAddressBook(addressBookName);
                        break;
                    }
                }

            }
        }
        System.out.println("Total Person Count in state " + state + ": " + count);
    }

    public void CountByCity(String city) {
        int countPersonInCity = 0;
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            for (int i = 0; i < (entry.getValue()).contactList.size(); i++) {
                ContactOfPerson d = entry.getValue().contactList.get(i);

                if (city.equals(d.getCity())) {
                    countPersonInCity++;
                }
            }
        }
        System.out.println("Total number of people in this city " + city + ": " + countPersonInCity);
    }

    public void sortContactByName() {
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            AddressBook value = entry.getValue();
            List<ContactOfPerson> sortedList = value.contactList.stream()
                    .sorted(Comparator.comparing(ContactOfPerson::getFirstName)).collect(Collectors.toList());

            for (ContactOfPerson contact : sortedList) {
                System.out.println("First Name: " + contact.getFirstName());
                System.out.println("Last Name: " + contact.getLastName());
                System.out.println("-------------------------");
            }
        }
    }

    void sortContactByState() {
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            AddressBook value = entry.getValue();
            List<ContactOfPerson> sortedList = value.contactList.stream()
                    .sorted(Comparator.comparing(ContactOfPerson::getState)).collect(Collectors.toList());

            for (ContactOfPerson contact : sortedList) {
                System.out.println("First Name: " + contact.getFirstName());
                System.out.println("Last Name: " + contact.getLastName());
                System.out.println("-------------------------------");
            }
        }
    }

    public void sortContactByCity() {
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            AddressBook value = entry.getValue();
            List<ContactOfPerson> sortedList = value.contactList.stream()
                    .sorted(Comparator.comparing(ContactOfPerson::getCity)).collect(Collectors.toList());

            for (ContactOfPerson contact : sortedList) {
                System.out.println("First Name: " + contact.getFirstName());
                System.out.println("Last Name: " + contact.getLastName());
                System.out.println("-------------------------------");

                case 5:
                    System.out.println("Enter Name of City: ");
                    String cityName1 = sc.next();
                    addressBookMain.viewPersonByCityUsingHashMap(cityName1);
                    break;
                case 6:
                    System.out.println("Enter Name of State: ");
                    String stateName2 = sc.next();
                    addressBookMain.CountByState(stateName2);
                    break;

                case 7:
                    System.out.println("Enter Name of City: ");
                    String cityName2 = sc.next();
                    addressBookMain.CountByCity(cityName2);
                    break;

                case 8:
                    flag = false;
                    break;
            }
        }
    }
}
