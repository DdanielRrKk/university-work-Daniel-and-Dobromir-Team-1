package Interfaces;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

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
		try 
		{
			Main.getInstance().setScene("/Interfaces/startMenu.fxml");
			mainStage.getIcons().add(new Image("/icon.png"));
			mainStage.setTitle("Library System");
			mainStage.setResizable(false);
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
