//Patrick Anderson (psa5dg)

import java.util.ArrayList;

public class Library {
	
	private String name;
	private ArrayList<LibraryBook> books;
	private ArrayList<String> members;
	
	
	public Library(String name, ArrayList<LibraryBook> books, ArrayList<String> members)
//The constructor should save the the name of the library, the books list, and the members list.
	{
		this.name = name;
		this.books = books;
		this.members = members;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	public void setBooks(ArrayList<LibraryBook> books)
	{
		this.books = books;
	}
	public ArrayList<LibraryBook> getBooks()
	{
		return this.books;
	}
	public void setMembers(ArrayList<String> members)
	{
		this.members = members;
	}
	public ArrayList<String> getMembers()
	{
		return this.members;
	}
	
	public boolean addBook(LibraryBook book)
//	The addBook(LibraryBook book) and addMember(String memberName) 
//	should attempt to add the parameter to the appropriate list without adding duplicates. 
//	If the parameter is already in the list and thus cannot be added again, 
//	the method should return false. If the parameter is successfully added to the list, 
//			the method should return true.
	{
		if (this.books.contains(book))
		{
			return false;
		}
		else
		{
			this.books.add(book);
			return true;
		}
	}
	
	public boolean addMember(String memberName)
	{
		if (this.members.contains(memberName))
		{
			return false;
		}
		else
		{
			this.members.add(memberName);
			return true;
		}
	}
	
	public boolean hasBook(LibraryBook book)
//The hasBook(LibraryBook book) method should return true if the library has the given book 
//		in its ArrayList of LibraryBooks.
	{	
		if(this.books.contains(book))
		{
			return true;
		}
		else return false;
	}
	
	public ArrayList<LibraryBook> getAllBooksByAuthor(String author)
//The getAllBooksByAuthor(String author) method should return an ArrayList of books 
//	written by the given author.
	{
		ArrayList<LibraryBook> booksByAuthor = new ArrayList<LibraryBook>();
		
		
		for (LibraryBook i : this.books)
		{
			if (i.getAuthor().equals(author))
			{
				booksByAuthor.add(i);
			}
		}
		return booksByAuthor;
	}
	
	
	public ArrayList<LibraryBook> getAllAvailableBooksByAuthor(String author)
// The getAllAvailableBooksByAuthor(String author) method should return an ArrayList of books 
//	written by the given author that are not currently checked out.	
	{
		ArrayList<LibraryBook> booksByAuthor = new ArrayList<LibraryBook>();
		
		
		for (LibraryBook i : this.books)
		{
			if (i.getAuthor().equals(author) && !i.isCheckedOut())
			{
				booksByAuthor.add(i);
			}
		}
		return booksByAuthor;
	}
	
	public boolean checkOutBook(String member, LibraryBook book)
//	The checkOutBook(String member, LibraryBook book) method should 
//	attempt to call LibraryBook’s checkOut method on the given book if and only if 
//	the library has that member and that book in the appropriate ArrayLists. 
//	The method returns true if you are able to successfully call LibraryBook’s checkOut method, 
//	otherwise the method should return false.
	{
		if (this.books.contains(book) && this.members.contains(member))
			if(book.checkOut(member))
		{
			return true;
		}
		else	return false;
		return false;
	}

	public int advanceDay()
//The advanceDay() method should advance the day for every book in the library 
//	and count how many books have been returned on a given day. 
//	Here, you should rely on LibraryBook’s advanceDay() method to “do the work,” 
//	and should not be manipulating any LibraryBook’s fields.	
	{
		int count = 0;
		for (LibraryBook i : this.books)
		{
			if (i.advanceDay())
			{
				count++;
			}
		}
		return count;
	}
	
	public String toString()
//The toString() method will return a string of the format “Name - # books and # members”. 
//	See the examples below. The quotation marks in the examples just denote 
//	the beginning and end of the string - the string you return should not contain quotation marks.
	{
		return this.name+" - "+this.books.size()+" books and "+this.members.size()+" members";
	}
	
}
