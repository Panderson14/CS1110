// Patrick Anderson (psa5dg)

import java.text.DecimalFormat;
import java.util.Scanner;

public class LousListNumbers {
	
	public static double meanDurationInMinutes(String department) throws Exception
	{
		CachingURL webAddress = new CachingURL("http://stardock.cs.virginia.edu/louslist/Courses/view/"+department); //URL
		Scanner webReader = new Scanner(webAddress.openStream()); //Scans info in URL
		double sum = 0;
		double count = 0;
		double average = 0;
		
		while (webReader.hasNextLine()) //loops until there's no info left
		{
			String line = webReader.nextLine(); //makes current line a string
			String[] cells = line.split(";"); // splits data by the ";"
			int start = Integer.parseInt(cells[12]);
			int end = Integer.parseInt(cells[13]);
			if (start != -1)
			{
				if (end-start < 60) sum += (end-start);
				else if (end-start < 160) sum += (end-start)-40;
				else if (end-start < 260) sum += (end-start)-80;
				else if (end-start < 360) sum += (end-start)-120;
				else if (end-start < 460) sum += (end-start)-160;
				else if (end-start < 560) sum += (end-start)-200;
				count++;
			}
		}
		average = sum/count;
		
		webReader.close();
		return average;
	}
	
	public static void classLocations(String department, double lat, double lon, int digits) throws Exception
	{
		CachingURL webAddress = new CachingURL("http://stardock.cs.virginia.edu/louslist/Courses/view/"+department); //URL
		Scanner webReader = new Scanner(webAddress.openStream()); //Scans info in URL
		
		String zero = "0.";
		for (int i = 0; i < digits; i++)
		{
			zero += "0";
		}
		DecimalFormat one = new DecimalFormat(zero);
		String latString = one.format(lat);
		String lonString = one.format(lon);
		lat = Double.parseDouble(latString);
		lon = Double.parseDouble(lonString);
		
		while (webReader.hasNextLine()) //loops until there's no info left
		{
			String line = webReader.nextLine(); //makes current line a string
			String[] cells = line.split(";"); // splits data by the ";"
			if (cells[5].equals("Lecture") || cells[5].equals("Laboratory"))
			{
				double latCell = Double.parseDouble(cells[17]);
				double longCell = Double.parseDouble(cells[18]);
				String latCellString = one.format(latCell);
				String lonCellString = one.format(longCell);
				latCell = Double.parseDouble(latCellString);
				longCell = Double.parseDouble(lonCellString);
				
				if (lat == latCell && lon == longCell)
				{
					System.out.println(cells[0]+" "+cells[1]+" section "+cells[2]);
				}
			}
		}
		
		webReader.close();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(meanDurationInMinutes("DRAM"));
		LousListNumbers.classLocations("DRAM", 38.03, -78.50, 2);
	}
}
