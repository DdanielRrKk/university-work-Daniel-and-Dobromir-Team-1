package Interfaces;

import java.io.IOException;
import java.sql.SQLException;

import BusinessLogic.AccountsData;
import BusinessLogic.BooksData;
import Model.Accounts;
import Model.Books;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application 
{
	private static Main instance;
	private Stage mainStage;
	private int ID;
	
	public Main()
	{
		this.ID = 0;
		instance = this;
	}
	
	@Override
	public void start(Stage primaryStage) 
	{
		mainStage = primaryStage;
		mainStage.setResizable(false);
		try 
		{
			Parent root = FXMLLoader.load(getClass().getResource("startMenu.fxml"));
			Scene scene = new Scene(root,385,202);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Library System");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) 
		{
			e.printStackTrace();
		}	
		
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	public void setScene(String pathFXML)
	{
		try 
		{
			Parent root = FXMLLoader.load(getClass().getResource(pathFXML));
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
			mainStage.show();
			mainStage.centerOnScreen();
            
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static Main getInstance()
	{
		return instance;
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
}
