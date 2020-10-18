package Model;

public class Books {
	
	private int ID;
	private String Title;
	private String Author;
	private String Genre;
	private int Condition;
	private boolean bAvailable;
	
	public Books() 
	{
	}
	
	public Books(String title, String author, String genre, int condition, boolean avaliable)
	{
		this.Title = title;
		this.Author = author;
		this.Genre = genre;
		this.Condition = condition;
		this.bAvailable = avaliable;
	}
	
	public Books(int id, String title, String author, String genre, int condition, boolean avaliable)
	{
		this.ID = id;
		this.Title = title;
		this.Author = author;
		this.Genre = genre;
		this.Condition = condition;
		this.bAvailable = avaliable;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int lID) {
		this.ID = ID;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public int getCondition() {
		return Condition;
	}

	public void setCondition(int lCondition) {
		this.Condition = lCondition;
	}

	public boolean isbAvailable() {
		return bAvailable;
	}

	public void setbAvailable(boolean bAvailable) {
		this.bAvailable = bAvailable;
	}
	
}
