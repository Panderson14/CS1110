
public class Tester {

	public static void main(String[] args) {
		LibraryBook book = new LibraryBook("Horton Hears a Hoo", "Dr. Seuss");
		book.isCheckedOut();     // returns false
		book.getDaysUntilDue();      // returns 0
		book.getWhoCheckedOut(); // returns ""
		book.checkOut("Matt");       // returns true
		book.isCheckedOut();     // returns true
		book.checkOut("Steph");      // returns false
		book.advanceDay();           // returns false
		for(int x = 0; x < 12; x++) {
		  book.advanceDay();     // returns false each time (12 total)
		}
		System.out.println(book.toString());         // returns "Horton Hears a Hoo - Dr. Seuss\nChecked out by Matt for 1 more day(s)."
		book.advanceDay();           // returns true
		System.out.println(book.toString());         // returns "Horton Hears a Hoo - Dr. Seuss"
	}
}
