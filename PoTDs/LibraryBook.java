//Patrick Anderson (psa5dg)

public class LibraryBook {
	
	private String title;
	private String author;
	private boolean checkedOut;
	private int daysUntilDue;
	private String whoCheckedOut;
	
	public LibraryBook(String ti, String auth)
	{
		title = ti;
		author = auth;
		checkedOut = false;
		daysUntilDue = 0;
		whoCheckedOut = "";
	}
	
	public void setTitle(String ti)
	{
		title = ti;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setAuthor(String auth)
	{
		author = auth;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public boolean isCheckedOut()
	{
		return checkedOut;
	}
	
	public int getDaysUntilDue()
	{
		return daysUntilDue;
	}
	
	public String getWhoCheckedOut()
	{
		return whoCheckedOut;
	}
	
	public boolean checkOut(String who)
	{
		if (checkedOut == true)
		{
			return false;
		}
		else
		{
			checkedOut = true;
			daysUntilDue = 14;
			whoCheckedOut = who;
			return true;
		}
	}
	
	public boolean advanceDay()
	{
		if (checkedOut == false)
		{
			return false;
		}
		else
		{
			daysUntilDue--;
			if (daysUntilDue > 0)
			{
				return false;
			}
			else
			{
				checkedOut = false;
				whoCheckedOut = "";
				return true;
			}
		}
	}
	
	public String toString()
	{
		if (checkedOut == false)
		{
			return title + " - " + author;
		}
		else
		{
			return title+" - "+author+"\nChecked out by "+whoCheckedOut+" for "+daysUntilDue+" more day(s).";
		}
	}
}