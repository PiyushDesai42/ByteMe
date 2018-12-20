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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Coords.Gps_vector;
import Coords.Lat_lon_alt;
import Coords.MyCoords;
import File_format.Csv2Game;
import File_format.Game2Csv;
import algo.Path;
import algo.Solution;
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

	public MainWindow() 
	{
		initGUI();
		this.addMouseListener(this); 
		Lat_lon_alt min = new Lat_lon_alt(32.10190,35.20236,0);
		Lat_lon_alt max = new Lat_lon_alt(32.10582,35.21234,0);
		map = new Map(myImage, min, max);
		game = new Game();
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

		Menu files = new Menu("File");
		Menu manual = new Menu("Manual");

		MenuItem load_csv = new MenuItem("import csv file");
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

		menuBar.add(manual);
		menuBar.add(files);

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
				packman_or_fruit = "none";
				run = "don't_run";
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					if(chooser.getSelectedFile().getAbsoluteFile().toString().endsWith(".csv")) {
						System.out.println("You chose to open this file: " +
								chooser.getSelectedFile().getName());
						String path = chooser.getSelectedFile().getPath();
						to_game(path);
					}
					else {
						System.out.println("Not a csv file!");
					}
				}
			}

			private void to_game(String path) {
				Csv2Game csv2game = new Csv2Game(path);
				try {
					Game g = csv2game.run();
					game.add_game(g);
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
				packman_or_fruit = "none";
				run = "don't_run";
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
		// TODO Auto-generated method stub 				packman_or_fruit = "none"; 		run = "don't_run";

	}



	private void add_p(MenuItem add_p) {
		add_p.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				packman_or_fruit = "packman";
				run = "don't_run";
			}
		});
	}



	private void add_f(MenuItem add_f) {
		add_f.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				packman_or_fruit = "fruit";
				run = "don't_run";
			}
		});

	}


	private void clear(MenuItem clear) {
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				packman_or_fruit = "none";
				run = "don't_run";
				game.clear();
				repaint();
			}
		});
	}

	private class Two_lat_lon_alt{
		private Color c;
		private Lat_lon_alt gps0;
		private Lat_lon_alt gps1;

		public Two_lat_lon_alt(Lat_lon_alt gps0, Lat_lon_alt gps1, Color c) {
			this.gps0 = new Lat_lon_alt(gps0);
			this.gps1 = new Lat_lon_alt(gps1);
			this.c = c;
		}

	}

	private final Lock lock = new ReentrantLock();
	private ArrayList<Two_lat_lon_alt> lines = new ArrayList<Two_lat_lon_alt>();

	private void run_manual(MenuItem run_manual) {
		run_manual.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				run = "run";
				solution = new Solution(game);





				class run_path implements Runnable{

					private Path path;
					public run_path(Path path) {
						this.path=path;
					}

					@Override
					public void run() {

						Random rand = new Random();
						float r = rand.nextFloat();
						float g = rand.nextFloat();
						float b = rand.nextFloat();
						Color randomColor = new Color(r, g, b);

						MyCoords coords = new MyCoords();
						Packman packman = path.getPackman();
						Iterator<Fruit> it = path.getFruits().iterator();
						while(run.equals("run") && it.hasNext()) {
							Lat_lon_alt original_p = new Lat_lon_alt(packman.getGps_point());
							Fruit current_fruit = it.next();
							Gps_vector vector = coords.vector3D(packman.getGps_point(), current_fruit.getGps_point());
							double vector_size = Math.sqrt(vector.x()*vector.x()+vector.y()*vector.y());
							Gps_vector unit_vector = new Gps_vector(vector.x()/vector_size, vector.y()/vector_size, 0);
							double step_lat = unit_vector.x() * packman.getMeters_per_sec();
							double step_lon = unit_vector.y() * packman.getMeters_per_sec();			
							Gps_vector step_vector = new Gps_vector(step_lat, step_lon, 0);
							while(run.equals("run") &&
									coords.distance3D(packman.getGps_point(), current_fruit.getGps_point())>packman.getRadius()) {
								Lat_lon_alt new_gps_point = coords.add(packman.getGps_point(), step_vector);
								try {

									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								packman.setGps_point(new_gps_point);
								repaint();
							}
							try {
								lock.lock();
								lines.add(new Two_lat_lon_alt(original_p, packman.getGps_point(),randomColor));
							} finally {
								lock.unlock();
							}
						}
					}

				}

				ArrayList<Thread> threads = new ArrayList<Thread>();
				Iterator<Path> it = solution.getPaths().iterator();
				while(it.hasNext()) {
					run_path r = new run_path(it.next());
					threads.add( new Thread(r));
				}
				Iterator<Thread> itt = threads.iterator();
				while(itt.hasNext()) {
					itt.next().start();
				}

				System.out.println("Congradulations!\n"
						+ "you managed to eat all of the fruit in: "+solution.getTime()+" seconds!");


			}
		});
	}

	private String packman_or_fruit = "none";
	private String run = "don't_run";

	public void paint(Graphics g)
	{
		g.drawImage(myImage, 0, 0,getWidth()-8,getHeight()-8, this);
		int x, y;

		Iterator<Packman> it_p = game.getPackmans().iterator();
		while(it_p.hasNext()) {
			Packman current_p =it_p.next();
			Point p = Ratio.lat_lon2Pixel(map, current_p.getGps_point(), getWidth(), getHeight());
			x = p.x-15;
			y = p.y-15;
			BufferedImage P_Image=null;
			try {
				P_Image = ImageIO.read(new File("pacman.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(P_Image, x, y, getWidth()/50, getHeight()/30,this);
		}

		Iterator<Fruit> it_f = game.getFruits().iterator();
		while(it_f.hasNext()) {
			Fruit current_f =it_f.next();
			Point p = Ratio.lat_lon2Pixel(map, current_f.getGps_point(), getWidth(), getHeight());
			x = p.x-4;
			y = p.y-4;
			g.setColor(Color.red);
			g.fillOval(x, y, getWidth()/100, getHeight()/50);
		}

		try {
			lock.lock();

			if(run.equals("run")) {
				Point p1 = new Point();
				Point p2 = new Point();



				Iterator<Two_lat_lon_alt> it = lines.iterator();
				while (it.hasNext()) {
					Two_lat_lon_alt double_gps = it.next();
					p1 = Ratio.lat_lon2Pixel(map, double_gps.gps0, getWidth(), getHeight());
					p2 = 	Ratio.lat_lon2Pixel(map, double_gps.gps1, getWidth(), getHeight());
					g.setColor(double_gps.c);
					g.drawLine(p1.x, p1.y, p2.x, p2.y);
				}
			}
			else {
				lines.clear();
			}
		} finally {
			lock.unlock();
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
			Packman packman = new Packman(Ratio.pixel2Lat_lon(map, getWidth(), getHeight(), p));
			System.out.println(packman.getGps_point());
			game.add_packman(packman);
			repaint();
		}
		if(packman_or_fruit.equals("fruit")) {
			System.out.println("fruit added!");
			System.out.println("("+ e.getX() + "," + e.getY() +")");
			x = e.getX();
			y = e.getY();
			Point p = new Point(x, y);
			Fruit fruit = new Fruit(Ratio.pixel2Lat_lon(map, getWidth(), getHeight(), p));
			System.out.println(fruit.getGps_point());
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
