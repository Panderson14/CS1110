// Patrick Anderson (psa5dg)

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LousListMethods
{	
	public static void courseConflicts(String department, int hour, int minute) throws Exception
	{
		CachingURL webAddress = new CachingURL("http://stardock.cs.virginia.edu/louslist/Courses/view/"+department); //URL
		Scanner webReader = new Scanner(webAddress.openStream()); //Scans info in URL
		String combine = hour+"";
		if (minute < 10) combine += "0";
		combine += minute;
		int compare = Integer.parseInt(combine); 
		
		while (webReader.hasNextLine()) //loops until there's no info left
		{
			String line = webReader.nextLine(); //makes current line a string
			String[] cells = line.split(";"); // splits data by the ";"
			int start = Integer.parseInt(cells[12]);
			int end = Integer.parseInt(cells[13]);
			if (compare >= start && compare < end)
			{
				System.out.println(cells[0]+" "+cells[1]+" section "+cells[2]);
			}		
		}
		webReader.close();
	}
		
	public static ArrayList<String> instructors(String department) throws Exception
	{
		CachingURL webAddress = new CachingURL("http://stardock.cs.virginia.edu/louslist/Courses/view/"+department); //URL
		Scanner webReader = new Scanner(webAddress.openStream()); //Scans info in URL
		ArrayList<String> instructors = new ArrayList<String>(); //initialize ArrayList
		ArrayList<String> instructorsFinal = new ArrayList<String>(); //initialize ArrayList
		String checker = ""; //initialize a string comparison
		
		while (webReader.hasNextLine()) //loops until there's no info left
		{
			String line = webReader.nextLine(); //makes current line a string
			String[] cells = line.split(";"); // splits data by the ";"
			instructors.add(cells[4]); //Adds each instructor to ArrayList
		}
		Collections.sort(instructors); //Alphabetizes instructors	

		instructorsFinal.add(instructors.get(0)); //always add the first name to the final list
		for (int i = 0; i < instructors.size()-1; i++)
		{
			checker = instructors.get(i+1);
			if (!checker.equals(instructors.get(i)))
			{
				instructorsFinal.add(instructors.get(i+1));
			}
		}
		/*if (!instructors.get(instructors.size()-1).equals(instructors.get(instructors.size()-2))) //checks last value to penultimate value
		{
			instructorsFinal.add(instructors.get(instructors.size()-1));
		}*/
		
		webReader.close();
		return instructorsFinal;
	}
		
	/*public static void main(String[] args) throws Exception
	{
		//LousListMethods.courseConflicts("CS", 12, 50);
		System.out.println(LousListMethods.instructors("CS"));
	}*/
}