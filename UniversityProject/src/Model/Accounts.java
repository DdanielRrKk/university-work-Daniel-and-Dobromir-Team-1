package Model;

public class Accounts 
{
	private int ID;
	private int UpdateCounter;
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
	private boolean Approved;
	public Accounts() 
	{
	}
	
	public Accounts(int updateCounter, String firstName, String lastName, String uCN, String phoneNumber, String email, String address, String username, String password, int roleID, int ratingID, boolean approvedAccount) 
	{
		this.UpdateCounter = updateCounter;
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
		this.Approved = approvedAccount;
	}
	
	public Accounts(int iD, int updateCounter, String firstName, String lastName, String uCN, String phoneNumber, String email, String address, String username, String password, int roleID, int ratingID, boolean approvedAccount) 
	{
		this.ID = iD;
		this.UpdateCounter = updateCounter;
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
		this.Approved = approvedAccount;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getUpdateCounter()
	{
		return UpdateCounter;
	}
	
	public void setUpdateCounter(int updateCounter)
	{
		this.UpdateCounter = updateCounter;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getUCN() {
		return UCN;
	}

	public void setUCN(String uCN) {
		UCN = uCN;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	public String getAddress() {
		return Address;
	}
	
	public void setAddress(String address) {
		Address = address;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getRoleID() {
		return RoleID;
	}

	public void setRoleID(Integer roleID) {
		RoleID = roleID;
	}

	public int getRatingID() {
		return RatingID;
	}

	public void setRatingID(Integer ratingID) {
		RatingID = ratingID;
	}
	
	public void setApproved(boolean approvedAcc)
	{
		this.Approved = approvedAcc;
	}
	
	public boolean isApproved()
	{
		return Approved;
	}
}