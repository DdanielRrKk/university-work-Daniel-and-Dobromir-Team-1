package Model;

import java.sql.Date;

public class Issue 
{
	private int ID;
	private int BookID;
	private int AccountID;
	private Date IssueDate;
	private Date ReturnDate;
	private Date ReturnedDate;
	private int ReturnedCondition;
	
	public Issue()
	{
	}
	
	public Issue(int bookID, int accountID, Date issueDate, Date returnDate, Date returnedDate, int returnedCondition) 
	{
		BookID = bookID;
		AccountID = accountID;
		IssueDate = issueDate;
		ReturnDate = returnDate;
		ReturnedDate = returnedDate;
		ReturnedCondition = returnedCondition;
	}

	public Issue(int iD, int bookID, int accountID, Date issueDate, Date returnDate, Date returnedDate, int returnedCondition) 
	{
		ID = iD;
		BookID = bookID;
		AccountID = accountID;
		IssueDate = issueDate;
		ReturnDate = returnDate;
		ReturnedDate = returnedDate;
		ReturnedCondition = returnedCondition;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getBookID() {
		return BookID;
	}

	public void setBookID(int bookID) {
		BookID = bookID;
	}

	public int getAccountID() {
		return AccountID;
	}

	public void setAccountID(int accountID) {
		AccountID = accountID;
	}

	public Date getIssueDate() {
		return IssueDate;
	}

	public void setIssueDate(Date date) {
		IssueDate = date;
	}

	public Date getReturnDate() {
		return ReturnDate;
	}

	public void setReturnDate(Date returnDate) {
		ReturnDate = returnDate;
	}

	public Date getReturnedDate() {
		return ReturnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		ReturnedDate = returnedDate;
	}

	public int getReturnedCondition() {
		return ReturnedCondition;
	}

	public void setReturnedCondition(int returnedCondition) {
		ReturnedCondition = returnedCondition;
	}
	
	
}
