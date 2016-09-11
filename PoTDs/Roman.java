// Patrick Anderson (psa5dg)
import java.util.Scanner;
public class Roman {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter an integer: ");
		int number = keyboard.nextInt();
		if (number < 1 || number > 3999) {
			System.out.println ("Input must be between 1 and 3999");
		}
		else {
			String[] ones = {"","I","II","III","IV","V","VI","VII","VIII","IX",""};
			String[] tens = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC",""};
			String[] hundreds = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM",""};
			String[] thousands = {"","M","MM","MMM"};			
			int one = (((number%1000)%100)%10);
			int ten = (((number/10)%100)%10);
			int hundred = ((number/100)%10);
			int thousand = number/1000;			
			System.out.println("In roman numerals, "+number+" is "+thousands[thousand]+hundreds[hundred]+tens[ten]+ones[one]);
		}
		keyboard.close();
	}
}