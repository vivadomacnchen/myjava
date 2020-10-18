import java.net.*;

public class seprj {
	public static void main(String args[]) throws Exception
	{
		try 
		{
			Socket s = new Socket("192.168.1.102", 80);
			System.out.println("Connected!");
		}
		catch (SecurityException e) 
		{
		  System.out.println("SecurityException: could not connect.");
		}
	}
}
