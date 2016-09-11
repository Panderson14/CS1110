// Patrick Anderson (psa5dg)
// I used this for some info on character replacement: http://stackoverflow.com/questions/6952363/replace-a-character-at-a-specific-index-in-a-string
import java.util.Random;
import java.util.Scanner;
public class Hangman
{
	public static void main(String[] args) throws Exception
	{
		System.out.print("Enter a word: ");
		Scanner keyboard = new Scanner(System.in);
		String input = keyboard.nextLine();
		if (input.equals(""))
		{
			System.out.print("Enter a URL: ");
			String url = keyboard.nextLine();
			CachingURL webAddress = new CachingURL(url);
			System.out.println("Selecting a random word.");
			Scanner webReader = new Scanner(webAddress.openStream());
			while (webReader.hasNextLine())
			{
				String line = webReader.nextLine();
				String[] options = line.split(",");
				input = options[new Random().nextInt(options.length)];
			}
			webReader.close();
		}
		input = input.toUpperCase();
		String clue = "";
		int checker = 0;
		for (int i = 0; i < input.length(); i++)
		{
			clue += "-";
		}
		int countDown = 6;
		int finisher = input.length();
		
		StringBuilder corrector = new StringBuilder(clue);
		
		
		do
		{
			System.out.print("["+corrector+"] You have "+countDown+" left, enter a letter: ");
			char guess = keyboard.next().charAt(0);
			guess = Character.toUpperCase(guess);
			checker = 0;
			finisher = input.length();
			for (int j = 0; j < input.length(); j++)
			{
				if (guess == input.charAt(j))
				{
					corrector.setCharAt(j, guess);
					checker++;
				}
				else if (guess != input.charAt(j))
				{
					clue += "-";
				}
				if (corrector.charAt(j) != '-')
				{
					finisher--;
				}
			}
			if (finisher == 0) {break;}
			if (checker == 0)
			{
				System.out.println("Incorrect!");
				countDown--;
			}
			else if (checker != 0)
			{
				System.out.println("Correct!");
			}

		} while (countDown > 0);
		
		if (countDown == 0)
		{
			System.out.println("You lose! The word was \""+input+"\"");
		}
		else
		{
			System.out.println("You win! The word was \""+input+"\"");
		}
		
		
		keyboard.close();
	}
}