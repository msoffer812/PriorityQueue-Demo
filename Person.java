/**
 * Parent class for shopper
 */
package shoppingHW;

public class Person 
{
	protected String name;
	
	public Person(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String n)
	{
		this.name = n;
	}
}
