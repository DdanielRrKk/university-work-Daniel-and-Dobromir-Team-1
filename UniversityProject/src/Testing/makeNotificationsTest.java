package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import BusinessLogic.OperatorFunctions;
import BusinessLogic.OperatorFunctions.HBoxCell;
import javafx.scene.control.ListView;

public class makeNotificationsTest {
	
	OperatorFunctions of=new OperatorFunctions();
	
	@Test
	public void makeNotificationsTest() {
		ListView<HBoxCell> list = new ListView<>();
		assertSame(list, of.makeNotifications());
	}

}
