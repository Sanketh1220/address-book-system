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
		} 
}

public class AddressBookMain {	

	public static void main(String args[]) {
	
		System.out.println("Welcome to Address Book");
	}
}
