class Contacts{
   String fName, lName, address, city, state;
   Long zipCode, phoneNum;
   String email;

      Contacts(String fName, String lName, String address, String city, String state, long zipCode, long phoneNum, String email){
         this.fName = fName;
         this.lName = lName;
         this.address = address;
         this.city = city;
         this.state = state;
         this.zipCode = zipCode;
         this.phoneNum = phoneNum;
         this.email = email;
         System.out.println("First Name: " + fName);
         System.out.println("Last Name: " + lName);
         System.out.println("Address: " + address);
         System.out.println("City: " + city);
         System.out.println("State: " + state);
         System.out.println("Zip Code: " + zipCode);
         System.out.println("Phone Number: " + phoneNum);
         System.out.println("Email ID: " + email);
      }
}

public class AddressBookMain {

   public static void main(String args[]) {

      System.out.println("Welcome to Address Book");
      Contacts sanketh =  new Contacts("Sanketh", "Chigurupalli", "Alwal", "Hyderabad", "Telangana", 500015L, 9000990009L, "nothing@mymail.com");
   }
}
