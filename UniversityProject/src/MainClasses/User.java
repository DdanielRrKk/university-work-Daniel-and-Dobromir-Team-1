package MainClasses;

public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String address;
	private int rating;
	
	// default constructor
	public User() {
		id=0;
		firstName="";
		lastName="";
		phoneNumber="";
		email="";
		address="";
		rating=0;
	}
	
	// explicit constructor
	public User(int id,String firstName,String lastName, String phoneNumber, String email, String address, int rating) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.phoneNumber=phoneNumber;
		this.email=email;
		this.address=address;
		this.rating=rating;
	}

	// setters
	public void setId(int id) {this.id = id;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	public void setEmail(String s) {this.email=s;}
	public void setAddress(String address) {this.address = address;}
	public void setRating(int rating) {this.rating = rating;}
	
	//getters
	public int getId() {return id;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getPhoneNumber() {return phoneNumber;}
	public String getEmail() {return email;}
	public String getAddress() {return address;}
	public int getRating() {return rating;}
}
