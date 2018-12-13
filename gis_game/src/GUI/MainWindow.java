package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.sun.javafx.geom.Point2D;

import game_elements.Ratio;


public class MainWindow extends JFrame implements MouseListener, MenuListener, ActionListener, KeyListener
{
	public BufferedImage myImage=null;

	public MainWindow() 
	{
		initGUI();	
		this.addMouseListener(this);
		this.addKeyListener(this);
	}

	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		MenuItem item1 = new MenuItem("menu item 1");
		MenuItem item2 = new MenuItem("menu item 2");

		menuBar.add(menu);
		menu.add(item1);
		menu.add(item2);
		this.setMenuBar(menuBar);

		try {
			myImage = ImageIO.read(new File("image.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}		



	}

	int x = -1;
	int y = -1;

	ArrayList<Point2D> fruits = new ArrayList<Point2D>();

	public void paint(Graphics g)
	{
		g.drawImage(myImage, 0, 0,getWidth()-8,getHeight()-8, this);

		if(x!=-1 && y!=-1)
		{
			Iterator<Point2D> it = fruits.iterator();
			while(it.hasNext()) {
				Point2D current =it.next();
				int r = 20;
				x = (int)(getWidth() * current.x)-7;
				y = (int)(getHeight() * current.y)-7;
				g.setColor(Color.YELLOW);
				g.fillOval(x, y, r, r);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		
		x = arg.getX();
		y = arg.getY();
		Point p = new Point(x, y);
		double a =Ratio.pixel2Width(getWidth(), getHeight(), p);
 		double b = Ratio.pixel2Height(getWidth(), getHeight(), p);
 		Point2D point2D = new Point2D();
 	    point2D.x = (float)a;
 	    point2D.y= (float) b;
 	    fruits.add(point2D);
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse entered");

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


}
