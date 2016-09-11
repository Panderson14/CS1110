//Patrick Anderson (psa5dg)

public class Person {

	private String givenName;
	private String familyName;
	private boolean givenComesFirst;
	
	public Person(String givn, String famn, boolean givcf)
	{
		givenName = givn;
		familyName = famn;
		givenComesFirst = givcf;
	}
	public Person(String givn, String famn)
	{
		givenName = givn;
		familyName = famn;
		givenComesFirst = true;
	}
	public void setGivenName(String givn)
	{
		givenName = givn;
	}
	public String getGivenName()
	{
		return givenName;
	}
	public void setFamilyName(String famn)
	{
		familyName = famn;
	}
	public String getFamilyName()
	{
		return familyName;
	}
	public boolean isGivenFirst()
	{
		return givenComesFirst;
	}
	public boolean sameFamilyAs(Person person)
	{	
		if (this.getFamilyName().equals(person.getFamilyName()))
				{
					return true;
				}
		else return false;
	}
	public Person childWithGivenName(String givn)
	{
		String famn = this.getFamilyName();
		boolean givcf = this.isGivenFirst();
		Person child = new Person(givn, famn, givcf);
		return child;
	}
	public String toString()
	{
		if (givenComesFirst)
		{
			return givenName+" "+familyName;
		}
		else
		{
			return familyName+" "+givenName;
		}
	}	
}