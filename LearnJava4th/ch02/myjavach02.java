import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class myjavach02
{
	public static void main(String[] args)
	{
		JFrame frame =new JFrame("TESTmyJava");
		JFrame frame2 = new JFrame( "HelloJava2" );
		JFrame frame3 = new JFrame( "mYtESTJava3" );
		JLabel label = new JLabel("TESTmyJavaLabel!", JLabel.CENTER );
		label.setBounds(20,80,100,40);
		label.setText("LABEL");
		frame.getContentPane().add( label );
		frame.add(new TESTmyJavaComponent());
		frame.setSize(500,500);
		frame.setVisible(true);
		//
		
		frame2.add( new HelloComponent2("Hello Java!") );
		frame2.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame2.setSize( 300, 300 );
		frame2.setVisible( true );
		//
		frame3.add( new HelloComponent3("Javaframe3!") );
		frame3.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame3.setSize( 300, 300 );
		frame3.setVisible( true );
		//
		JFrame frame4 = new JFrame( "jframe4" );
		frame4.add( new HelloComponent4("TEST4!") );
		frame4.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame4.setSize( 600, 600 );
		frame4.setVisible( true );
		//
	}
}
class TESTmyJavaComponent extends JComponent
{
	public void paintComponent(java.awt.Graphics g)
	{
		g.drawString("TestMYjava",300,200);
	}
}

class HelloComponent2 extends JComponent
	implements MouseMotionListener
{
  String theMessage;
  int messageX = 125, messageY = 95; // Coordinates of the message

  public HelloComponent2( String message ) {
    theMessage = message;
    addMouseMotionListener(this);
  }

  public void paintComponent( Graphics g ) {
    g.drawString(theMessage, messageX, messageY);
  }

  public void mouseDragged(MouseEvent e) {
    // Save the mouse coordinates and paint the message.
    messageX = e.getX();
    messageY = e.getY();
    repaint();
  }

  public void mouseMoved(MouseEvent e) { }
}

class HelloComponent3 extends JComponent
	implements MouseMotionListener, ActionListener
{
  String theMessage;
  int messageX = 125, messageY = 95;  // Coordinates of the message

  JButton theButton;

  int colorIndex;  // Current index into someColors
  static Color[] someColors = {
	Color.black, Color.red, Color.green, Color.blue, Color.magenta };

  public HelloComponent3( String message ) {
    theMessage = message;
    theButton = new JButton("Change Color");
    setLayout( new FlowLayout() );
    add( theButton );
    theButton.addActionListener( this );
    addMouseMotionListener( this );
  }

  public void paintComponent( Graphics g ) {
    g.drawString(theMessage, messageX, messageY);
  }

  public void mouseDragged( MouseEvent e ) {
    messageX = e.getX();
    messageY = e.getY();
    repaint();
  }

  public void mouseMoved( MouseEvent e ) {}

  public void actionPerformed( ActionEvent e ) {
    // Did somebody push our button?
    if (e.getSource() == theButton)
      changeColor();
  }

  synchronized private void changeColor() {
    // Change the index to the next color, awkwardly.
    if (++colorIndex == someColors.length)
      colorIndex = 0;
    setForeground(currentColor(  )); // Use the new color.
    repaint(  ); // Paint again so we can see the change.
  }

  synchronized private Color currentColor() {
    return someColors[colorIndex];
  }
}

class HelloComponent4 extends JComponent
    implements MouseMotionListener, ActionListener, Runnable
{
  String theMessage;
  int messageX = 125, messageY = 95; // Coordinates of the message

  JButton theButton;

  int colorIndex; // Current index into someColors.
  static Color[] someColors = {
	Color.black, Color.red, Color.green, Color.blue, Color.magenta };

  boolean blinkState;

  public HelloComponent4( String message ) 
  {
    theMessage = message;
    theButton = new JButton("Change Color");
    setLayout( new FlowLayout() );
    add( theButton );
    theButton.addActionListener( this );
    addMouseMotionListener( this );
    Thread t = new Thread( this );
    t.start();
  }

  public void paintComponent( Graphics g ) {
    g.setColor(blinkState ? getBackground() : currentColor(  ));
    g.drawString(theMessage, messageX, messageY);
  }

  public void mouseDragged(MouseEvent e) {
    messageX = e.getX();
    messageY = e.getY();
    repaint();
  }

  public void mouseMoved(MouseEvent e) { }

  public void actionPerformed( ActionEvent e ) {
    if ( e.getSource() == theButton )
      changeColor();
  }

  synchronized private void changeColor() {
    if (++colorIndex == someColors.length)
      colorIndex = 0;
    setForeground( currentColor() );
    repaint();
  }

  synchronized private Color currentColor(  ) {
    return someColors[colorIndex];
  }

  public void run(  ) {
    try {
      while(true) {
        blinkState = !blinkState; // Toggle blinkState.
        repaint(); // Show the change.
        Thread.sleep(300);
      }
    } catch (InterruptedException ie) { }
  }
}
