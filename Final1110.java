//Patrick Anderson (psa5dg)


// The Case for CS
	// We see computers everywhere. Cars have 20-50 computers.
	// Computer programming involves making algorithms, which are a set of unambiguous instructions.
	// Three type of errors: 
		//	Syntax--red squiggly lines 
		//	Semantic--program will crash 
		//	Logic--program will not perform according to plan.

// The Parts of a Program
	//	{}, (), ;
		//	{} Encloses a group of statements.
		//	() Are used to give information to a method, or are used in a method header.
		//	; Marks the end of a statement.

//	Under the Hood
	//	How memory works in Java; the stack and the heap
		//	The stack is where variable pointers go, such as with a complex type, and primitive types
		//	The actual values of the complex type variable is found in the heap, and objects
	//	What does it mean for code to be “better?” - speed, easier to read, fewer bugs, quicker to write
		//	Maintain, modify, and update 2
		//	Correctness 1
		//	Performance
		//	Cost

//	Images
	//	The concept of a picture and pixel as represented in Java 
		//	(R,G,B) 0-255 black-white
	//	(basically, a 2D array of pixels, where a pixel is a single color point)
	//	General complexity of most image algorithms we looked at (n2)
	//	General idea of the various image algorithms worked (you will not have to code them; you may have to read them)




	//This tells the computer where to find the Scanner class in the Java library, and then imports that class.
	//Part of the Java API (Application Programming Interface)
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;

	//	This is the class header.
	//	Public is an access specifier, meaning this class can be accessed by other classes, 
	//	as opposed to private, which can only be accessed by its own class.
	//	Class indicates the beginning of the class definition.
	//	Final1110 is the name of the class.
public class Final1110 {
	
	//	final static int i = 10;	SCOPE: will be replaced by the local "i".
	final static int I = 10;
	
	//	This is a field
	//	We make fields private to hide data from other programs,
	//	in order to prevent data corruption.
	//	We make methods public to be used in other classes, to execute functions
	//	and to set and get the values of fields.
	private int x;
	
	
	//	This is a method header, specifically, of the method called "main".
	//	Static means that only one static instance of this method exists, regardless of how many instances exist.
	//	Void returns nothing.
	//	String[] args is an array of strings called "args" that are stored from the command line if the user types in any inputs.
	public static void main(String[] args) throws Exception {
			
		System.out.println("Hello World!");
		
		//primitiveDataTypes();
		//decisionStructures();
		//loops();
		//fileIO();
		//System.out.println(methods(4.2,4.3));
		//classes();
		//arraysAndArrayLists();
		//System.out.println(recursionAndAlgorithms(6));

	}
	
	public static void primitiveDataTypes()
	{
		//		Primitive Data Types
			//	byte	1 byte
			//	short	2 bytes
			//	int		4 bytes
			//	long	8 bytes
			//	float	4 bytes
			//	double	8 bytes
			//	boolean	1 bit
			//	char	2 bytes
		
		int i = 5;	//	SCOPE: a local variable within the main method.
		i++;
		i+=2;
		
		double d = 6.2;
		d = Math.sqrt(26);
		
		char c = 'c';
		char shifted = (char) (c+5);

		//	i = d; gets an error
		//	i = (int) d; does not get an error
		//	d = i; does not get an error
		
		//	c = i; gets an error
		//	c = (char) i; does not get an error
		//	i = c; does not get an error
		
		System.out.println((int)shifted); //	Casts a char as an int via the ACII table.
		System.out.println((int)d);	//	Truncates value.
		System.out.println((float)i); //	Adds a decimal.
		System.out.println(5/2); // Truncates.
		System.out.println(5/2.0); // Doesn't truncate.
		System.out.println(i);
		System.out.println(2*-2);
		System.out.println(1+2.3-2.3);
		System.out.println('1'+'1');
		System.out.println("hello".charAt(1));
		System.out.println(shifted);
	}
	
	public static void decisionStructures()
	{
		//		Decision Structures
				//	If, If-Else, Else
				//	Scope
				//	Switch
		int i = 50;
		boolean b = i == 50;
		if (i < 50) System.out.println(i + " is less than 50!"); //false
		else System.out.println("Hi!"); //defaults to this if all above if statements are false.
		if (b) System.out.println(i + " is 50!"); //true
		if (i > 40) System.out.println(i + " is greater than 40!"); //true
		else if (i > 30) System.out.println(i + " is greater than 30!"); //true, but else prevents it from running
		if (b)
		{
			int j = 40;
			System.out.println(j);
		}
		//	System.out.println(j); ERROR due to scope.
		
		String p = "Patrick";
		
		//	Used for making a block of statements similar to a bunch of if statements.
		switch (p)
		{
			case ("Mark"):
				System.out.println("Mark");
				break;
			case ("John"):
				System.out.println("John");
				break;
			default:
				System.out.println(p);
				break;
		}
	}
	
	public static void loops()
	{
		//	Loops
			// for, while, and do-while loops
		
		for (int i=0; i<5; i++)
		{
			System.out.println(i);
		}
		
		//	Used for arrays and arrayLists
		int[] nums = {5, 6, 4, 2, 1};
		for (int num : nums)
		{
			System.out.println(num);
		}
		
		char c = 'A';
		do
		{
			System.out.println(c);
			c++;
		} while (c<70);
		
		while (c<70)
		{
			System.out.println("I'm in the loop bro!");
		}
	}
	
	public static void fileIO() throws Exception
	{
		//	File I/O
			//	Read a CSV file and split a line into an array
		
		//Open a URL IMPORTANT: openStream() is the main difference between it and a local file
		URL url = new URL("http://stardock.cs.virginia.edu/louslist/Courses/view/CS");
		Scanner webReader = new Scanner(url.openStream());
		
		//Split lines read in from a file by commas
		while (webReader.hasNextLine())
		{
			//String line = webReader.nextLine();
			//String[] cells = line.split(";");
		}
		webReader.close();
		
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
	
	public static int methods(double floater, double floatee)
	{
		//	Methods
			//	public means that it can be accessed by methods outside this class
			//	static means that it belongs to this class, not an instance of this class
			//	int is the type of variable that will be returned, void returns nothing
			//	method is the name
			//	double floater, double floatee are the parameters that will be passed down into the method
			//	pass by value makes a copy in the method
			//	pass by reference modifies the contents of the address in the method
		int returner = (int)(floater+floatee);
		return returner;
		//often times, use void to change a private field, use return to access a private field
	}
	
	public static void classes()
	{
				Final1110 test = new Final1110(3);
				int x = test.getX();
				System.out.println(x);
	}
	
	public static void arraysAndArrayLists()
	{
		//	Arrays/ArrayLists
			//	Know how to initialize and use 1D and 2D arrays
			//	Know how an ArrayList is different than an array and how you use one
		
		//Arrays are static, and cannot change size
		String[] words = new String[3];
		words[2] = "hi";
		
		//Another array
		int[] nums = {5, 6, 4, 2, 1};
		
		//A 2D array
		double[][] scores = new double[3][4]; //	rowsXcolumns
		scores[2][1] = 95; //	Row 2 Column 1
		
		//Another 2D array. The inner brackets keep the contents of each row. So row 0 has 1, 2, 3.
		double[][] chickens = { {1,2,3}, {4,5,6}, {7,8,9} };
		
		//A 2D array with different amounts of columns per row.
		int[][] ragged = new int[4][];
		ragged[0] = new int[5];
		ragged[1] = new int[6];
		
		//ArrayList uses complex data types, and can dynamically adjust its size
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(5);
		numbers.add(6);
		numbers.add(7);
		numbers.remove(0);									//rows			//columns
		System.out.println(words[2]+nums[1]+numbers.get(1)+chickens.length+chickens[0].length);
	}

	public static int recursionAndAlgorithms(int n)
	{
		//	Recursion and Algorithms
			//	Base Case
			//	Calls Itself
			//	Changes Input
			//	Linear search, Binary search, Merge sort, and Bubble sort.

		//	Base Case
		if(n <= 1) {
			return n;
		} else {	//Calls itself and changes the input.
			return recursionAndAlgorithms(n - 1) + recursionAndAlgorithms(n - 2);
		}
		
		//Linear Search
			//	If we want to find an element, we simply use a for loop and loop as many as n times
			//	until we find the desired element.
		
		//Binary Search
			//	If the list is in order, we can cut it in half a number of times,
			//	assessing one half until we find the element. We can do this log2(n) times.
		
		//Merge Sort
			//	Split the list in half multiple times, and then sort each half.
			//	Then merge each half together correctly. This can take log2(n) times.
		
		//Bubble Sort
			//	We compare an element to the adjacent element, and swap them if they are out of order.
			//	This might take n^2 times.
		
		//Selection Sort
			//	Finds the smallest element, and adds it to a new list. Takes n^2 time.

	}
	
	public Final1110()
	{
		//	My own default constructor replaces Java's default constructor, which would make x = 0.
		this.x = 5;
	}
	
	public Final1110(int x)
	{
		this.x = x;
	}
	
	public void setX(int x)
	{
		//	This is a method, and a setter (mutator)
		this.x = x;
	}
	
	public int getX()
	{
		//	This is a getter (accessor)
		return this.x;
	}
}