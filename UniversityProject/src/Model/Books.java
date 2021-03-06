package Model;

public class Books {
	
	private int ID;
	private int UpdateCounter;
	private String Title;
	private String Author;
	private String Genre;
	private int Condition;
	private boolean Available;
	
	public Books() 
	{
	}
	
	public Books(int updateCounter, String title, String author, String genre, int condition, boolean avaliable)
	{
		this.UpdateCounter = updateCounter;
		this.Title = title;
		this.Author = author;
		this.Genre = genre;
		this.Condition = condition;
		this.Available = avaliable;
	}
	
	public Books(int id, int updateCounter, String title, String author, String genre, int condition, boolean avaliable)
	{		
		this.ID = id;
		this.UpdateCounter = updateCounter;
		this.Title = title;
		this.Author = author;
		this.Genre = genre;
		this.Condition = condition;
		this.Available = avaliable;
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

	public boolean isAvailable() {
		return Available;
	}

	public void setAvailable(boolean Available) {
		this.Available = Available;
	}
	
}
