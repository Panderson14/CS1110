// Patrick Anderson (psa5dg)
import java.util.Scanner;
public class CreditCard {
	public static void main(String[] args) {		
		Scanner keyboard = new Scanner(System.in);		
		System.out.print("Type a credit card number (just digits): ");
		String creditNum = keyboard.next();
		int summer=0;
		int doubler=0;
		String charStr = "";
		String character = "";
		int last = 0;
		for (int i=creditNum.length(); i>0;i=i-2) {
			summer += (creditNum.charAt(i-1)-48);
		}
		if ((creditNum.length()%2.0)!=0) {
			for (int m=1; m<creditNum.length(); m=m+2) {
				charStr += creditNum.charAt(m);
			}
		}
		else {
			for (int j=0; j<creditNum.length(); j=j+2) {
				charStr += creditNum.charAt(j);
			}
		}
		for (int k=0; k<charStr.length();k++) {
			doubler = ((charStr.charAt(k)-48)*2);
			character += doubler;
		}
		for (int l=0; l<character.length();l++) {
			last +=(character.charAt(l)-48);
		}
		int validity = summer+last;
		if (validity%10==0 && validity !=0) {
			System.out.print(creditNum + " is a valid credit card number");
		}
		else {
			System.out.print(creditNum + " is not a valid credit card number");
		}		
		keyboard.close();
	}
}