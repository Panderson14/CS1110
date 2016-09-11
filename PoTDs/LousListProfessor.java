//Patrick Anderson (psa5dg)

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LousListProfessor 
{
	public static void main(String[] args) throws Exception 
	{

		Scanner keyboard = new Scanner(System.in); //user-input
		String baseAddress = "http://stardock.cs.virginia.edu/louslist/Courses/view/"; //root URL
		
		System.out.print("Enter a department: ");
		String dept = keyboard.next();
		dept = dept.toUpperCase();
		CachingURL webAddress = new CachingURL(baseAddress+dept); //root URL + user department
		Scanner webReader = new Scanner(webAddress.openStream()); //Scans info in URL
		
		ArrayList<String> listOfProfs = new ArrayList<String>(); //ArrayList of prof names
		String instructor = "YOU FAILED"; //Initializes a string/stores instructor unless code fails
		int classCount = 1; // integer in ArrayList/stores number of classes for one prof
		String newName = ""; //Stores a name in the ArrayList to check to the previous name for equality
		ArrayList<String> narrowDown = new ArrayList<String>(); //narrows down professor list to the required instructor(s)
		
		while (webReader.hasNextLine()) //loops until there's no info left
		{ 
			String line = webReader.nextLine(); //makes current line a string
			String[] cells = line.split(";"); // splits data by the ";"
			
			if (cells[5].equals("Lecture") && !cells[4].equals("Staff")) 
			{
				listOfProfs.add(cells[4]);
			}
		}
		Collections.sort(listOfProfs); //Alphabetizes listOfProfs
		
		System.out.println(listOfProfs);
		do
		{
			for (int i = 0; i < listOfProfs.size(); i++)
			{
				if (newName.equals(listOfProfs.get(i))) narrowDown.add(listOfProfs.get(i));
				newName = listOfProfs.get(i);
			}
		if (narrowDown.size() == 0) break;
		listOfProfs.clear();
		listOfProfs.addAll(narrowDown);
		narrowDown.clear();
		System.out.println(listOfProfs);
		classCount++;
		if (listOfProfs.size() > 1 && (listOfProfs.get(0).equals(listOfProfs.get(1)) && listOfProfs.size() < 3)) {classCount++; break;}
		if (listOfProfs.size() == 1) break;
		} while (listOfProfs.size() > 1);
		
		instructor = listOfProfs.get(0);
		
		System.out.println(instructor+" teaches the most lecture sections ("+classCount+") in the "+dept+" department.");
		webReader.close();
		keyboard.close();
	}
}
