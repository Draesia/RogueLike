package game;

import game.Entities.Entity;
import game.Entities.Player;
import game.Entities.Zombie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import Generation.Dungeon;

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	public static boolean[] mouseDown = new boolean[4];
	public static Image[] images = new Image[255];
	public static int cx, cy, sx, sy, d;
	public static Level l;
	public static Player p;
	public static boolean gameRunning = true;
	public int update = 1;
	public static int score = 0;
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public static boolean[] controls = new boolean[9];

	public Game()
	{
		for(int x = 0; x < 255; x++)
		{
			if(getClass().getResource("/images/tiles/"+x+".png") != null) {
				images[x] = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/tiles/"+x+".png"));
			}
		}
		setFocusable(true);
		l = new Level(0);
		p = l.getPlayer();

		addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) {
				int id = e.getKeyCode();
				if(id == Settings.A) 	 controls[0] = true;
				if(id == Settings.W) 	 controls[1] = true;
				if(id == Settings.S) 	 controls[2] = true;
				if(id == Settings.D) 	 controls[3] = true;
				if(id == Settings.space) controls[4] = true;
				if(id == Settings.J) 	 controls[5] = true;
				if(id == Settings.K) 	 controls[6] = true;
				if(id == Settings.L) 	 controls[7] = true;
				if(id == Settings.I) 	 controls[8] = true;
				if(id == 69) nextLevel();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				int id = e.getKeyCode();
				if(id == Settings.A) 	 controls[0] = false;
				if(id == Settings.W) 	 controls[1] = false;
				if(id == Settings.S) 	 controls[2] = false;
				if(id == Settings.D) 	 controls[3] = false;
				if(id == Settings.space) controls[4] = false;
				if(id == Settings.J) 	 controls[5] = false;
				if(id == Settings.K) 	 controls[6] = false;
				if(id == Settings.L) 	 controls[7] = false;
				if(id == Settings.I) 	 controls[8] = false;
			}
		});

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent mEvt) {
				sx = mEvt.getX();
				sy = mEvt.getY();
				Dungeon.getDoors(l.getTileAt(sx/64, sy/64));
			}

		});

		runGameLoop();
	}

	public void runGameLoop()
	{
		Thread loop = new Thread()
		{
			public void run()
			{
				gameLoop();
			}
		};
		loop.start();
	}
	
	public void gameLoop()
	{
		long lastLoopTime = System.nanoTime();
		long lastFpsTime = 0;
		int fps = 0;
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   
		while (gameRunning && !p.isDead)
		{
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double)OPTIMAL_TIME);

			lastFpsTime += updateLength;
			fps++;
			
			if (lastFpsTime >= 1000000000)
			{
				lastFpsTime = 0;
				fps = 0;
			}

			update(delta);
			repaint();
			
			try {
				Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/4000000);
			} catch (Exception e) {}
		}
	}
	public void update(double delta)
	{

		for(int i = 0; i < entities.size(); i++)
		{
			Entity e = entities.get(i);
			e.update();
			if(e.cooldown > 0) e.cooldown--;
			else if (e.cooldown < 0) e.cooldown = 0;
			
			if(e.isDead) {i--; entities.remove(e); }
		}
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Frame.maxX(), Frame.maxY());
		if(Level.showtileList.size() > 0) {
			for(Tile t : l.showtileList)
			{
				if(t == null) continue;
				g2d.drawImage(images[t.id], t.x*Tile.SIZE+d, t.y*Tile.SIZE+d, null);
				if(t.getItem() != null)
				{
					g2d.drawImage(t.getItem().getImage(), t.x*Tile.SIZE+d+32, t.y*Tile.SIZE+d+32, null);
				}
			}
		}
		
		for(Entity e : entities)
		{
			g2d.drawImage(e.getRenderImage(), e.x+d, e.y+d, null);
		}
		
		g2d.drawImage(p.getImage(), p.x+d, p.y+d, null);
	}

	public static void nextLevel() 
	{
		entities.clear();
		l.create();
		p = l.getPlayer();
	}
}
