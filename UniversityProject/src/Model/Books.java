package Model;

public class Books {
	
	private int ID;
	private String Title;
	private String Author;
	private String Genre;
	private int Condition;
	private boolean Available;
	
	public Books() {	}
	
	public Books(String title, String author, String genre, int condition, boolean avaliable){
		this.Title = title;
		this.Author = author;
		this.Genre = genre;
		this.Condition = condition;
		this.Available = avaliable;
	}
	
	public Books(int id, String title, String author, String genre, int condition, boolean avaliable){
		this.ID = id;
		this.Title = title;
		this.Author = author;
		this.Genre = genre;
		this.Condition = condition;
		this.Available = avaliable;
	}
	
	// setters
	public void setID(int id) { this.ID = id; }
	public void setTitle(String title) { Title = title; }
	public void setAuthor(String author) { Author = author; }
	public void setGenre(String genre) { Genre = genre; }
	public void setCondition(int condition) { this.Condition = condition; }
	public void setAvailable(boolean available) { this.Available = available; }

	// getters
	public int getID() { return ID; }
	public String getTitle() { return Title; }
	public String getAuthor() {	return Author; }
	public String getGenre() { return Genre; }
	public int getCondition() {	return Condition; }
	public boolean getAvailable() { return Available; }

}
