// Patrick Anderson (psa5dg)
// I used this for log info: http://blog.dreasgrech.com/2010/02/finding-logarithm-of-any-base-in-java.html
// I used this for power info: http://www.tutorialspoint.com/java/number_pow.htm
import java.util.Scanner;
public class Nim {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("The Game of Nim\n");
		System.out.print("Number of marbles are in the pile: ");
		int pile = keyboard.nextInt(); // Number of marbles in pile
		System.out.print("Who will start? (p or c): ");	
		char starter = keyboard.next(".").charAt(0); // Who will start?
		System.out.println("The pile has "+pile+" marbles in it.");	
		if (starter == 'c') {
			do {
				pile = computer(keyboard, pile);
				if (pile!=0) {
					pile = player(keyboard, pile);
				}
			} while (pile !=0);
		}
		else if (starter == 'p') {
			do {
				pile = player(keyboard, pile);
				if (pile!=0) {
					pile = computer(keyboard, pile);
				}
			} while (pile !=0);
		}		
		keyboard.close();
	}	
	public static int computer(Scanner keyboard, int pile) {
		int compPile = 0;
		compPile = pile;
		if (compPile == 1) {
			System.out.println("The computer takes 1 marble(s).");
			System.out.println("The player wins!");
			pile = 0;
			return pile;
		}
		else {
				int logFull = (int) (Math.log(compPile)/Math.log(2));
				int newPile = (int)Math.pow(2,logFull)-1;
				if (compPile-newPile > compPile/2) {
					newPile = compPile-1;	
				}
				System.out.println("The computer takes "+(compPile-newPile)+" marble(s).");
				System.out.println("The pile has "+newPile+" marbles in it.");
				compPile = newPile;
				pile = compPile;
				return pile;
		}
	}
	public static int player(Scanner keyboard, int pile) {
		int marOpt;
		if (pile == 1) {
			do {
				System.out.print("How many marbles do you want to take? (1-1): ");
				marOpt = keyboard.nextInt();
			} while (marOpt!=1);
			System.out.println("The computer wins!");
			pile = 0;
			return pile;
		}
		else {
			do {
				System.out.print("How many marbles do you want to take? (1-"+(pile/2)+"): ");
				marOpt = keyboard.nextInt();
			} while (marOpt>(pile/2)||marOpt<1);
			System.out.println("The pile has "+(pile-marOpt)+" marbles in it.");
			pile = pile-marOpt;
			return pile;
		}
	}
}