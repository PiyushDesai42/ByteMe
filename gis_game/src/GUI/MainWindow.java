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
import File_format.Solution2Kml;
import algo.Path;
import algo.Solution;
import convert.Ratio;
import game_elements.Fruit;
import game_elements.Game;
import game_elements.Map;
import game_elements.Packman;



/**
 * This is a GUI class that represents our whole game in graphics.
 * Including options to add packmans and fruits, to run the algorithm,
 * to load a csv file and to create a csv kml file.
 * @author Eitan Lichtman, Netanel Indik
 */
public class MainWindow extends JFrame implements MouseListener, MenuListener 
{

	BufferedImage myImage=null;

	/**
	 * This constructor initiates our GUI
	 */
	public MainWindow() 
	{
		initGUI();
		this.addMouseListener(this); 
		Lat_lon_alt min = new Lat_lon_alt(32.10190,35.20236,0);
		Lat_lon_alt max = new Lat_lon_alt(32.10582,35.21234,0);
		map = new Map(myImage, min, max);
		game = new Game();
		solution = new Solution();
	}

	/**
	 * This method loads a csv file and converts it in to our game board.
	 * @param load_csv
	 */
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
					for(int i = 0; i < g.getFruits().size(); i++) {
						try {
							lock.lock();
							fruit_type.add(diff_fruits[(int) (Math.random()*4)]);
						} finally {
							lock.unlock();
						}
					}
				} catch (IOException e1) {
					System.out.println("invalid file!");
				}				
			}
		});
	}

	/**
	 * This method creates a csv file from our game board to a chosen folder.
	 * @param create_csv
	 */
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
					try {
						to_csv(path);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			private void to_csv(String path) throws IOException {
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

	/**
	 * This method creates a kml file from our game board to a chosen folder.
	 * It works with or without running the game first.
	 * @param create_kml
	 */
	private void create_kml(MenuItem create_kml) {
		create_kml.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				packman_or_fruit = "none";
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				int returnVal = chooser.showOpenDialog(getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose this folder: " +
							chooser.getSelectedFile().getName());
					String path = chooser.getSelectedFile().getPath();
					to_kml(path);
				}
			}

			private void to_kml(String path) {
				String new_path = path + "\\solution.kml";
				int counter = 1;
				while(new File(new_path).isFile()) {
					new_path = path + "\\solution"+counter+".kml";
					counter++;
				}
				Solution2Kml solution2kml;
				try {
					if(run.equals("run"))
						solution2kml = new Solution2Kml(new_path, solution);
					else
						solution2kml = new Solution2Kml(new_path, game);
					solution2kml.run();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * This method adds a packman to our game board.
	 * @param add_p
	 */
	private void add_p(MenuItem add_p) {
		add_p.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				packman_or_fruit = "packman";
				run = "don't_run";
			}
		});
	}


	/**
	 * This method adds a fruit to our game board.
	 * @param add_f
	 */
	private void add_f(MenuItem add_f) {
		add_f.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				packman_or_fruit = "fruit";
				run = "don't_run";
			}
		});

	}

	/**
	 * This method clears our game board.
	 * @param clear
	 */
	private void clear(MenuItem clear) {
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				packman_or_fruit = "none";
				run = "don't_run";
				game.clear();
				solution.getPaths().clear();
				try {
					lock.lock();
					fruit_type.clear();
				} finally {
					lock.unlock();
				}
				repaint();
			}
		});
	}

	/**
	 * This is a subclass that represents two gps points
	 * in order to draw a line between them.
	 */
	private class Two_lat_lon_alt{
		private Color c;
		private Lat_lon_alt gps0;
		private Lat_lon_alt gps1;

		/**
		 * This constructor receives and updates two gps points and a color.
		 * @param gps0
		 * @param gps1
		 * @param c
		 */
		public Two_lat_lon_alt(Lat_lon_alt gps0, Lat_lon_alt gps1, Color c) {
			this.gps0 = new Lat_lon_alt(gps0);
			this.gps1 = new Lat_lon_alt(gps1);
			this.c = c;
		}

	}


	/**
	 * This method run the algorithm on our game board.
	 * @param run_manual
	 */
	private void run_manual(MenuItem run_manual) {
		run_manual.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(run.equals("run")) {
					run="don't_run";
					repaint();
				}
				else {
					run = "run";
					solution = new Solution(game);


					/**
					 * This is a subclass which is used as a thread
					 * for running the algorithm on the game board.
					 */
					class run_path implements Runnable{

						private Path path;
						public run_path(Path path) {
							this.path=path;
						}

						/**
						 * This method runs the thread.
						 */
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
								double step_lat = unit_vector.x()*packman.getMeters_per_sec();
								double step_lon = unit_vector.y()* packman.getMeters_per_sec();
								if(step_lon>=0)
									packman.setOrientation("right");
								else
									packman.setOrientation("left");
								Gps_vector step_vector = new Gps_vector(step_lat, step_lon, 0);
								while(run.equals("run") &&
										(coords.distance3D(packman.getGps_point(), current_fruit.getGps_point())>packman.getMeters_per_sec()
												&& coords.distance3D(packman.getGps_point(), current_fruit.getGps_point())>packman.getRadius())) {
									Lat_lon_alt new_gps_point = coords.add(packman.getGps_point(), step_vector);
									try {
										Thread.sleep(15);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									packman.setGps_point(new_gps_point);
									repaint();
								}
								try {
									lock.lock();
									lines.add(new Two_lat_lon_alt(original_p, packman.getGps_point(),randomColor));
									int index = game.getFruits().indexOf(current_fruit);
									game.getFruits().remove(current_fruit);
									if(run=="run")
										fruit_type.remove(index);
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

			}
		});
	}

/**
 * This method draws the game board every time repaint() is called.
 */
	public void paint(Graphics g)
	{
		g.drawImage(myImage, 0, 0,getWidth()-8,getHeight()-8, this);
		int x, y;
		try {
			lock.lock();

			Iterator<Packman> it_p = game.getPackmans().iterator();
			while(it_p.hasNext()) {
				Packman current_p =it_p.next();
				Point p = Ratio.lat_lon2Pixel(map, current_p.getGps_point(), getWidth(), getHeight());
				x = p.x-10;
				y = p.y-10;
				BufferedImage P_Image=null;
				try {
					if(current_p.getOrientation()=="right")
						P_Image = ImageIO.read(new File("gis_game\\Images\\pacman_right.png"));
					if(current_p.getOrientation()=="left")
						P_Image = ImageIO.read(new File("gis_game\\Images\\pacman_left.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(P_Image, x, y, (int)current_p.getRadius()*getWidth()/40, (int)current_p.getRadius()*getHeight()/20,this);
			}

			Iterator<String> it_s = fruit_type.iterator();
			Iterator<Fruit> it_f = game.getFruits().iterator();
			while(it_f.hasNext() && it_f.hasNext()) {
				Fruit current_f =it_f.next();
				Point p = Ratio.lat_lon2Pixel(map, current_f.getGps_point(), getWidth(), getHeight());
				x = p.x-8;
				y = p.y-8;
				BufferedImage P_Image=null;
				try {
					P_Image = ImageIO.read(new File("gis_game\\Images\\" + it_s.next()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.drawImage(P_Image, x, y, getWidth()/60, getHeight()/30,this);

			}

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

	/**
	 * This method says what to do when the mouse is clicked in different situations.
	 */
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
			try {
				lock.lock();
				fruit_type.add(diff_fruits[(int) (Math.random()*4)]);
			} finally {
				lock.unlock();
			}

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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}





	//********************private data and methods********************

	private Map map = null;
	private Game game = null;
	private Solution solution = null;
	private String packman_or_fruit = "none";
	private String run = "don't_run";
	private String[] diff_fruits = {"3d_strawberry.png","3d_apple.png","3d_cherry.png","3d_orange.png"};
	private ArrayList<String> fruit_type = new ArrayList<String>();
	private final Lock lock = new ReentrantLock();
	private ArrayList<Two_lat_lon_alt> lines = new ArrayList<Two_lat_lon_alt>();


	private void initGUI() 
	{
		initMENU();
		try {
			myImage = ImageIO.read(new File("gis_game\\Images\\image.jpg"));
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

}
