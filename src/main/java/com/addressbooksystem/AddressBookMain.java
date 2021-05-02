/****************************************************
 * Purpose : Program is written to collect a contacts into different address books
 *
 * @author Sanketh Chigurupalli
 * @version 1.0
 * @since 28-04-2021
 *
 ****************************************************/

package com.addressbooksystem;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookMain {
    public AddressBook addressBook = new AddressBook();

    /**
     * Implemented HashMap and String as Key and AddressBook as Value
     */
    public static Map<String, AddressBook> addressBookListMap = new HashMap<>();

    public void addAddressBook(String addressBookName) {

        boolean flag = true;
        /**
         * Options to add edit delete or exit
         */
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

                case 4:
                    flag = false;
                    break;

            }
        }
        addressBookListMap.put(addressBookName, addressBook);
        System.out.println("Address Book Added Successfully");
    }

    /**
     * iterating through addressbook using state name and getting person
     * @param stateName
     */
    public void searchPersonByState(String stateName) {
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            AddressBook value = entry.getValue();
            System.out.println("The Address Book: " + entry.getKey());
            value.getPersonNameByState(stateName);
        }
    }

    /**
     * iterating through addressbook using city name and getting person
     * @param cityName
     */
    public void searchPersonByCity(String cityName) {
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            AddressBook value = entry.getValue();
            System.out.println("The Address Book: " + entry.getKey());
            value.getPersonNameByCity(cityName);
        }
    }

    /**
     * iterating through addressbook using city name and getting persons
     * @param stateName
     */
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

    /**
     * getting count by state
     * @param state
     */
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

    /**
     * getting count by city
     * @param city
     */
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

    /**
     * sorting by name method
     */
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

    /**
     * sorting by state method
     */
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

    /**
     * sorting by city
     */
    public void sortContactByCity() {
        for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
            AddressBook value = entry.getValue();
            List<ContactOfPerson> sortedList = value.contactList.stream()
                    .sorted(Comparator.comparing(ContactOfPerson::getCity)).collect(Collectors.toList());

            for (ContactOfPerson contact : sortedList) {
                System.out.println("First Name: " + contact.getFirstName());
                System.out.println("Last Name: " + contact.getLastName());
                System.out.println("-------------------------------");
            }
        }
    }
}
