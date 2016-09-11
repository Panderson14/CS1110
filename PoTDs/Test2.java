//jah6vg   jca3nq

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
public class Test2 {
	public static void main(String[] args) throws Exception {
//		urlReader();
//		fileReader();
//		arrayBuilder();
//		System.out.println(methodExample(4.2,3.7));
		System.out.println(fib(6));
	}

	
	
	public static void urlReader() throws Exception
	{
		//Open a URL IMPORTANT: openStream() is the main difference between it and a local file
		URL url = new URL("http://stardock.cs.virginia.edu/louslist/Courses/view/CS");
		Scanner webReader = new Scanner(url.openStream());
		
		//Split lines read in from a file by commas
		url.openStream();
		while (webReader.hasNextLine())
		{
			//String line = webReader.nextLine();
			//String[] cells = line.split(";");
		}
		webReader.close();
	}
	
	public static void fileReader() throws Exception
	{
		
		//Open a local file
		File myFile = new File("MyFriends.txt");
		Scanner inputFile = new Scanner(myFile);
		inputFile.close();
		
		//Write output to a file
		FileWriter fwriter = new FileWriter("MyFriends.txt", true);
		PrintWriter outputFile = new PrintWriter(fwriter);
		outputFile.println("Hey there!");
		outputFile.close();
	}
	
	public static void arrayBuilder()
	{
		//Arrays are static, and cannot change size
		String[] words = new String[3];
		words[2] = "hi";
		
		//Another array
		int[] nums = {5, 6, 4, 2, 1};
		
		//ArrayList uses complex data types, and can dynamically adjust its size
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(5);
		numbers.add(6);
		numbers.add(7);
		numbers.remove(0);
		System.out.println(words[2]+nums[1]+numbers.get(1));
	}
	
	//public means that it can be accessed by methods outside this class
	//static means that it belongs to this class, not an instance of this class
	//int is the type of variable that will be returned, void returns nothing
	//methodExample is the name
	//double floater, double floatee are the parameters that will be passed down into the method
	public static int methodExample(double floater, double floatee)
	{
		int returner = (int)(floater+floatee);
		return returner;
		//often times, use void to change a private field, use return to access a private field
	}
	
	//int n is how many recursions for the fibonacci
	public static int fib(int n)
	{
	    if(n <= 1) {
	        return n;
	    } else {
	        return fib(n - 1) + fib(n - 2);
	    }
	}
}
