package Model;

public class Accounts 
{
	private int ID;
	private String FirstName;
	private String LastName;
	private String UCN;
	private String PhoneNumber;
	private String Email;
	private String Address;
	private String Username;
	private String Password;
	private int RoleID;
	private int RatingID;
	
	public Accounts() 
	{
	}
	
	public Accounts(String firstName, String lastName, String uCN, String phoneNumber, String email, String address, String username, String password, int roleID, int ratingID) 
	{
		this.FirstName = firstName;
		this.LastName = lastName;
		this.UCN = uCN;
		this.PhoneNumber = phoneNumber;
		this.Email = email;
		this.Address = address;
		this.Username = username;
		this.Password = password;
		this.RoleID = roleID;
		this.RatingID = ratingID;
	}
	
	public Accounts(int iD, String firstName, String lastName, String uCN, String phoneNumber, String email, String address, String username, String password, int roleID, int ratingID) {
		this.ID = iD;
		this.FirstName = firstName;
		this.LastName = lastName;
		this.UCN = uCN;
		this.PhoneNumber = phoneNumber;
		this.Email = email;
		this.Address = address;
		this.Username = username;
		this.Password = password;
		this.RoleID = roleID;
		this.RatingID = ratingID;
	}
	
	// setters
	public void setID(int ID) {	this.ID = ID; }
	public void setFirstName(String firstName) { FirstName = firstName; }
	public void setLastName(String lastName) { LastName = lastName; }
	public void setUCN(String ucn) { UCN = ucn; }
	public void setPhoneNumber(String phoneNumber) { PhoneNumber = phoneNumber; }
	public void setEmail(String email) { Email = email; }
	public void setAddress(String address) { Address = address; }
	public void setUsername(String username) { Username = username; }
	public void setPassword(String password) { Password = password; }
	public void setRoleID(Integer roleID) { RoleID = roleID; }
	public void setRatingID(Integer ratingID) { RatingID = ratingID; }
	
	// getters
	public int getID() { return ID; }
	public String getFirstName() { return FirstName; }
	public String getLastName() { return LastName; }
	public String getUCN() { return UCN; }
	public String getPhoneNumber() { return PhoneNumber; }
	public String getEmail() { return Email; }
	public String getAddress() { return Address; }
	public String getUsername() { return Username; }
	public String getPassword() { return Password; }
	public int getRoleID() { return RoleID; }
	public int getRatingID() { return RatingID; }

}
