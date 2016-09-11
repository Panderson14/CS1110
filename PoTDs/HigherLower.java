// Patrick Anderson (psa5dg)
// I used this to understand random numbers: http://java.about.com/od/javautil/a/randomnumbers.htm
import java.util.Scanner;
import java.util.Random;
public class HigherLower {
	public static void main(String[] args) {
		System.out.print ("What should the answer be? " );
		Scanner keyboard = new Scanner(System.in);
		int number = keyboard.nextInt();
		if (number == -1) {
			Random random = new Random();
			int pickedNumber = random.nextInt(100) + 1;
			numberGame(pickedNumber, keyboard);
		}
		else numberGame(number, keyboard);
		keyboard.close();
	}
	public static void numberGame (int number1,Scanner keyboard) {
		for (int i = 0; i < 5; i = i + 1 ) {
				System.out.print("Guess a number: ");
				int guess = keyboard.nextInt();
				if (guess == number1) {
					System.out.println("You win!");
					break;
				}
				else {
					if (i < 4) {
						if (guess < number1) {
							System.out.println("The number is higher than that.");
						}
						if (guess > number1) {
							System.out.println("The number is lower than that.");
						}
					}
					else {
						System.out.println("You lose; the number was " + number1 + ".");
					}
				}
		}
	}
}