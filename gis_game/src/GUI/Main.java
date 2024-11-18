package GUI;

import javax.swing.JFrame;


/**
 * This is a main class for activating our GUI.
 * @author Eitan Lichtman, Netanel Indik
 */
public class Main 
{
	
	/**
	 * Main method for activating GUI.
	 * @param args
	 */
	public static void main(String[] args)
	{
		MainWindow window = new MainWindow();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth()-8,window.myImage.getHeight()-8);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}


