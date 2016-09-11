// Patrick Anderson (psa5dg)
import java.util.Scanner;
public class Dating {
	public static void main(String[] args) {
		System.out.print("How old are you? ");
		Scanner keyboard = new Scanner(System.in);
		int age = keyboard.nextInt();
		System.out.println("You can date people between " + (age/2+7) +
				" and " + (age*2-13) + " years old");
		keyboard.close();
	}
}