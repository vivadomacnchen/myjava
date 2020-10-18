import java.util.*;
import java.util.HashMap; 
import java.lang.reflect.*;
//import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Sheep implements Cloneable { 
    HashMap flock = new HashMap( ); 
 
    public Sheep clone( ) { 
        try { 
            return (Sheep)super.clone( ); 
        } catch (CloneNotSupportedException e ) {  
            throw new Error("This should never happen!"); 
        } 
    } 
}

class TESTmyJavaComponent extends JComponent
{
	public void paintComponent(java.awt.Graphics g)
	{
		g.drawString("TestMYjava",300,200);
	}
}


public class seprj 
{
	public static void mymethod()
	{
		//int i=0;
		JFrame frame =new JFrame("TESTmymethod");
		JLabel label = new JLabel("TESTmyJavaLabel!", JLabel.CENTER );
		label.setBounds(20,80,100,40);
		label.setText("LABEL");
		frame.getContentPane().add( label );
		frame.add(new TESTmyJavaComponent());
		frame.setSize(500,500);
		frame.setVisible(true);
		//return 0;
	}
	public static void main(String args[]) 
	{
		TypeVariable []	tv = List.class.getTypeParameters();
		System.out.println( tv[0].getName() ); // E

		class StringList extends ArrayList<String> { }

		Type type = StringList.class.getGenericSuperclass();
		System.out.println( type );  //java.util.ArrayList<java.lang.String>
		ParameterizedType pt = (ParameterizedType)type;
		System.out.println( pt.getActualTypeArguments()[0] );
		System.out.println("\n"+args[0]);
		System.out.println("\n" + args[1]);
		try
		{

		  Class c = Class.forName( args[0] );
		  Method m = c.getMethod( args[1] );
		  //Object arg=null;
		  //Object ret =  m.invoke( new seprj(),arg);
		  //Object ret =  m.invoke( new seprj(),args);
		  Object ret =  m.invoke( null);
		  System.out.println("\n"+args[0]);
		  System.out.println("\n"+args[1]);
		  System.out.println(
			  "Invoked static method: " + args[1]
			  + " of class: " + args[0]
			  + " with no args\nResults: ");
		}
		catch ( ClassNotFoundException e )
		{
			System.out.println( e );
		  // Class.forName(  ) can't find the class
		}
		catch ( NoSuchMethodException e2 )
		{
			System.out.println("\nTEST\n");
			System.out.println( e2 );
		  // that method doesn't exist
		}
		catch ( IllegalAccessException e3 )
		{
			System.out.println( e3 );
		  // we don't have permission to invoke that method
		}
		catch ( InvocationTargetException e )
		{
			System.out.println( e );
			// an exception ocurred while invoking that method
			System.out.println("Method threw an: " + e.getTargetException(  ) );
		}
		Sheep one = new Sheep();
		Sheep two = one.clone();
	}
	
}

