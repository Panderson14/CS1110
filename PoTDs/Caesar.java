// Patrick Anderson (psa5dg)
import java.util.Scanner;
public class Caesar {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter your cipher text: ");
		String cText = keyboard.next();
		System.out.print("The decoded phrase is: ");
		for (int i=0; i<12; i++) {			
			char letter = cText.charAt(i);
			letter = (char) (letter-3);
			System.out.print(letter);
		}
		keyboard.close();
	}
}