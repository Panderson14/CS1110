// Patrick Anderson (psa5dg)
// Aaqil Abdullah (ama5kk) gave me a suggestion on the String scanner.
import java.util.Scanner;
public class BMR {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("What is you mass (kg)? ");
		int mass = keyboard.nextInt();
		System.out.print("What is you height (cm)? ");
		int height = keyboard.nextInt();
		System.out.print("What is you age (years)? ");
		int age = keyboard.nextInt();	
		System.out.print("Are you female (yes/no)? ");
		String gender = keyboard.next();
		if (gender.equals("yes")) {
			double bmrf = (10*mass)+(6.25*height)-(5*age)-161;
			System.out.println("Your BMR is " + bmrf + " Calories per day");
		}
		else {
			double bmrm = (10*mass)+(6.25*height)-(5*age)+5;
			System.out.println("Your BMR is " + bmrm + " Calories per day");
		}
		keyboard.close();
	}
}