package MainClasses;

public class Book {
	
	private int id;
	private String title;
	private String author;
	private String genre;
	private int condition;
	private boolean available;
	
	//default constructor
	public Book() {
		id=0;
		title="";
		author="";
		genre="";
		condition=0;
		available=false;
	}
	
	//explicit constructor
	public Book(int id,String title,String author,String genre,int condition, boolean available) {
		this.id=id;
		this.title=title;
		this.author=author;
		this.genre=genre;
		this.condition=condition;
		this.available=available;
	}
	
	//setter
	public void setId(int i) {id=i;}
	public void setTitle(String s) {title=s;}
	public void setAuthor(String s) {author=s;}
	public void setGenre(String s) {genre=s;}
	public void setCondition(int i) {condition=i;}
	public void setAvailable(boolean b) {available=b;}
	
	//getters
	public int getId() {return id;}
	public String getTitle() {return title;}
	public String getAuthor() {return author;}
	public String getGenre() {return genre;}
	public int getCondition() {return condition;}
	public boolean getAvailable() {return available;}
	
}
