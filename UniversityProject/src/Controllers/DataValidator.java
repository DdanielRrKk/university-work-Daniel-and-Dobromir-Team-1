package Controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator 
{	
	public DataValidator() 
	{
	}

	public boolean validateUserName(String strData)
	{
		Pattern p = Pattern.compile("[^A-Za-z0-9@._-]");
	    Matcher m = p.matcher(strData);
	    boolean b = m.find();
	    if (b)
	    	return false;
	    return true;
	}
	
	public boolean validateName(String strData)
	{	
		Pattern p = Pattern.compile("[^A-Za-z]");
	    Matcher m = p.matcher(strData);
	    boolean b = m.find();
	    if (b)
	    	return false;
	    return true;
	}
	
	public boolean validateAddress(String strData)
	{
		Pattern p = Pattern.compile("[^A-Za-z0-9.,-]");
	    Matcher m = p.matcher(strData);
	    boolean b = m.find();
	    if (b)
	    	return false;
	    return true;
	}
	
	public boolean validateUCN(String strData)
	{
		Pattern p = Pattern.compile("[^0-9]");
	    Matcher m = p.matcher(strData);
	    boolean b = m.find();
	    if (b)
	    	return false;
	    return true;

	}
	
	public boolean validatePhoneNumber(String strData)
	{
		Pattern p = Pattern.compile("[^+0-9]");
	    Matcher m = p.matcher(strData);
	    boolean b = m.find();
	    if (b)
	    	return false;
	    return true;
	}

	public boolean validateEmail(String strData) 
	{
		Pattern p = Pattern.compile("[^A-Za-z0-9@._-]");
	    Matcher m = p.matcher(strData);
	    boolean b = m.find();
	    if (b)
	    	return false;
	    return true;
	}
	
	public boolean validateSize(String strData, int maxSize)
	{
		if(strData.length() > maxSize)
			return false;
		return true;
	}
}
