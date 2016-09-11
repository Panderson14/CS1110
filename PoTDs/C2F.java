// Patrick Anderson (psa5dg)
// For the conversion calculation, I used http://www.rapidtables.com/convert/temperature/celsius-to-fahrenheit.htm
import java.util.Scanner;
public class C2F {
	public static void main(String[]args)
	{
		System.out.print ("What is the temperature in Celsius? ");
		Scanner keyboard = new Scanner(System.in);
		double temp = keyboard.nextDouble();
		temp = temp*9/5 + 32;
		System.out.println("It is "+ temp  +" degrees Fahrenheit");
		keyboard.close();
	}
}