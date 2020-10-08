package MainClasses;

public class Account extends User {
	
	private String userName;
	private String password;
	private int accessLevel;
	
	// default constructor
		public Account() {			
			userName="";
			password="";
			accessLevel=0;
		}
		
		// explicit constructor
		public Account(String userName, String password, int accessLevel, int id,String firstName,String lastName, String phoneNumber, String email, String address, int rating) {			
			super(id,firstName,lastName,phoneNumber,email,address,rating);
			this.userName=userName;
			this.password=password;
			this.accessLevel=accessLevel;
		}
		
		// setters
		public void setUserName(String userName) {this.userName = userName;}
		public void setPassword(String password) {this.password = password;}
		public void setAccessLevel(int accessLevel) {this.accessLevel = accessLevel;}
		
		// getters		
		public String getUserName() {return userName;}
		public String getPassword() {return password;}
		public int getAccessLevel() {return accessLevel;}
}
