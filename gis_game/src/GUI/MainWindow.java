package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.sun.javafx.geom.Point2D;

import Coords.Lat_lon_alt;
import File_format.Csv2Game;
import File_format.Game2Csv;
import algo.Solution;
import convert.Game_in_ratio;
import convert.Ratio;
import game_elements.Fruit;
import game_elements.Game;
import game_elements.Map;
import game_elements.Packman;



public class MainWindow extends JFrame implements MouseListener, MenuListener 
{
	BufferedImage myImage=null;
	private Map map = null;
	private Game game = null;
	private Solution solution = null;
	private Game_in_ratio ratio = null;

	public MainWindow() 
	{
		initGUI();
		this.addMouseListener(this); 
		Lat_lon_alt min = new Lat_lon_alt(32.10190,35.20236,0);
		Lat_lon_alt max = new Lat_lon_alt(32.10582,35.21234,0);
		map = new Map(myImage, min, max);
		game = new Game();
		ratio = new Game_in_ratio();
		solution = new Solution(game);
	}

	private void initGUI() 
	{
		initMENU();
		try {
			myImage = ImageIO.read(new File("image.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}


	private void initMENU() {
		MenuBar menuBar = new MenuBar();

		Menu files = new Menu("files");
		Menu manual = new Menu("manual");

		MenuItem load_csv = new MenuItem("load csv");
		load_csv(load_csv);
		MenuItem create_csv = new MenuItem("create csv");
		create_csv(create_csv);
		MenuItem create_kml = new MenuItem("create kml");
		create_kml(create_kml);

		MenuItem add_p = new MenuItem("add packmans");
		add_p(add_p);
		MenuItem add_f = new MenuItem("add fruits");
		add_f(add_f);
		MenuItem clear = new MenuItem("clear");
		clear(clear);
		MenuItem run_manual = new MenuItem("run");
		run_manual(run_manual);

		menuBar.add(files);
		menuBar.add(manual);

		files.add(load_csv);
		files.add(create_csv);
		files.add(create_kml);

		manual.add(add_p);
		manual.add(add_f);
		manual.add(clear);
		manual.add(run_manual);

		this.setMenuBar(menuBar);

	}








	private void load_csv(MenuItem load_csv) {
		load_csv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " +
							chooser.getSelectedFile().getName());
					String path = chooser.getSelectedFile().getPath();
					to_game(path);
				}
			}

			private void to_game(String path) {
				Csv2Game csv2game = new Csv2Game(path);
				try {
					Game g = csv2game.run();
					game.add_game(g);
					ratio.add_game(g, map);
				} catch (IOException e1) {
					System.out.println("invalid file!");
				}				
			}
		});
	}


	private void create_csv(MenuItem create_csv) {
		create_csv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				int returnVal = chooser.showOpenDialog(getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose this folder: " +
							chooser.getSelectedFile().getName());
					String path = chooser.getSelectedFile().getPath();
					to_csv(path);
				}
			}

			private void to_csv(String path) {
				String new_path = path + "\\game.csv";
				int counter = 1;
				while(new File(new_path).isFile()) {
					new_path = path + "\\game"+counter+".csv";
					counter++;
				}
				Game2Csv game2csv = new Game2Csv(new_path, game);
				game2csv.run();
			}
		});
	}


	private void create_kml(MenuItem create_kml) {
		// TODO Auto-generated method stub

	}



	private void add_p(MenuItem add_p) {
		add_p.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				packman_or_fruit = "packman";
			}
		});
	}



	private void add_f(MenuItem add_f) {
		add_f.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				packman_or_fruit = "fruit";
			}
		});

	}


	private void clear(MenuItem clear) {
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.clear();
				ratio.clear();
			}
		});
	}

	private void run_manual(MenuItem run_manual) {
		// TODO Auto-generated method stub

	}


	private String packman_or_fruit = "";


	public void paint(Graphics g)
	{
		int x, y;
		g.drawImage(myImage, 0, 0,getWidth()-8,getHeight()-8, this);

		Iterator<Point2D> it_p = ratio.getPackmans().iterator();
		while(it_p.hasNext()) {
			Point2D current =it_p.next();
			int r = 20;
			Point p = Ratio.ratio2Pixel(getWidth(), getHeight(), current);
			x = p.x-8;
			y = p.y-8;
			g.setColor(Color.YELLOW);
			g.fillOval(x, y, r, r);
		}

		Iterator<Point2D> it_f = ratio.getFruits().iterator();
		while(it_f.hasNext()) {
			Point2D current =it_f.next();
			int r = 12;
			Point p = Ratio.ratio2Pixel(getWidth(), getHeight(), current);
			x = p.x-4;
			y = p.y-4;
			g.setColor(Color.red);
			g.fillOval(x, y, r, r);
		}
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
	public void mouseClicked(MouseEvent e) {
		int x, y;
		if(packman_or_fruit.equals("packman")) {
			System.out.println("packman added!");
			System.out.println("("+ e.getX() + "," + e.getY() +")");
			x = e.getX();
			y = e.getY();
			Point p = new Point(x, y);
			Point2D p2D = Ratio.pixel2Ratio(getWidth(), getHeight(), p);
			ratio.add_packman(p2D);
			Packman packman = new Packman(Ratio.ratio2Lat_lon(map, p2D));
			game.add_packman(packman);
			repaint();
		}
		if(packman_or_fruit.equals("fruit")) {
			System.out.println("fruit added!");
			System.out.println("("+ e.getX() + "," + e.getY() +")");
			x = e.getX();
			y = e.getY();
			Point p = new Point(x, y);
			Point2D p2D = Ratio.pixel2Ratio(getWidth(), getHeight(), p);
			ratio.add_fruit(p2D);
			Fruit fruit = new Fruit(Ratio.ratio2Lat_lon(map, p2D));
			game.add_fruit(fruit);
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
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


}
