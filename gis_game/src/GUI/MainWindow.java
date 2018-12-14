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

import algo.Ratio;



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
		
		Menu csv = new Menu("load csv file");
		Menu manual = new Menu("manual");
		
		MenuItem load_csv = new MenuItem("load csv to gameboard");
		MenuItem run_csv = new MenuItem("run");
		
		MenuItem add_p = new MenuItem("add packmans");
		MenuItem add_f = new MenuItem("add fruits");
		MenuItem run_manual = new MenuItem("run");

		menuBar.add(csv);
		menuBar.add(manual);
		
		csv.add(load_csv);
		csv.add(run_csv);
		
		manual.add(add_p);
		manual.add(add_f);
		manual.add(run_manual);
		
		this.setMenuBar(menuBar);

		try {
			myImage = ImageIO.read(new File("image.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}		



	}

	private int x = -1;
	private int y = -1;

	private ArrayList<Point2D> fruits = new ArrayList<Point2D>();

	public void paint(Graphics g)
	{
		g.drawImage(myImage, 0, 0,getWidth()-8,getHeight()-8, this);

		if(x!=-1 && y!=-1)
		{
			Iterator<Point2D> it = fruits.iterator();
			while(it.hasNext()) {
				Point2D current =it.next();
				int r = 20;
				Point p = Ratio.ratio2Pixel(getWidth(), getHeight(), current);
				x = p.x-8;
				y = p.y-8;
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
 		Point2D ratio = Ratio.pixel2Ratio(getWidth(), getHeight(), p);
 	    fruits.add(ratio);
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
