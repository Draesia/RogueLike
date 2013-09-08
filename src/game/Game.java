package game;

import game.Entities.Bullet;
import game.Entities.Entity;
import game.Entities.Player;
import game.Entities.Zombie;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	public static boolean[] mouseDown = new boolean[4];
	public static HashMap<String, Image> images = new HashMap<String, Image>();
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
		setFocusable(true);
		l = new Level(0);
		nextLevel();

		addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) {

				int id = e.getKeyCode();
				if(id == Settings.A) 	 controls[0] = true;
				if(id == Settings.W) 	 controls[1] = true;
				if(id == Settings.S) 	 controls[2] = true;
				if(id == Settings.D) 	 controls[3] = true;
				if(id == Settings.space) {
					for(Entity e1 : entities)
					{
						System.out.println("Entities: "+e1.getClass().getSimpleName()+" X:"+e1.x+" Y:"+e1.y);
					}
					controls[4] = true;
				}
				if(id == Settings.J) 	 controls[5] = true;
				if(id == Settings.K) 	 controls[6] = true;
				if(id == Settings.L) 	 controls[7] = true;
				if(id == Settings.I) 	 controls[8] = true;
				if(id == 69) {nextLevel(); score = 0;}
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
				Zombie z = new Zombie();
				z.x = sx-((sx+32)%64)-32;
				z.y = sy-((sy+32)%64)-32;
				entities.add(z);
				System.out.println("Created a Zombie");
			}

		});

		runGameLoop();
	}
	public static Image getImage(String s)
	{
		Image i = images.get(s);
		if(i == null)
		{
			images.put(""+s, Toolkit.getDefaultToolkit().getImage(Toolkit.getDefaultToolkit().getClass().getResource("/images/tiles/"+s+".png")));
		}
		return i;
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
		while (gameRunning)
		{
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double)OPTIMAL_TIME);
			lastFpsTime += updateLength;
			
			if(!p.isDead)
				update();
			repaint();

			try {
				Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/4000000);
			} catch (Exception e) {}
		}
	}
	public void update()
	{
		if(entities.size() == 1) { nextLevel(); return; }
		for(int i = 0; i < entities.size(); i++)
		{
			Entity e = entities.get(i);
			if(e.isDead) {i--; entities.remove(e); continue;}
			if(!e.onScreen() && !(e instanceof Bullet)) continue;
			if(e.cooldown > 0) e.cooldown--;
			else if (e.cooldown < 0) e.cooldown = 0;

			e.update();

		}
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Frame.maxX(), Frame.maxY());
		if(Level.showtileList.size() > 0) {
			for(Tile t : l.showtileList) {
				if(t == null) continue;
				g2d.drawImage(t.getImage(), t.x*Tile.SIZE+d, t.y*Tile.SIZE+d, null);
				if(t.getItem() != null) {
					g2d.drawImage(t.getItem().getImage(), t.x*Tile.SIZE+d+32, t.y*Tile.SIZE+d+32, null);
				}
			}
		}
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			g2d.drawImage(e.getRenderImage(), e.x+d, e.y+d, null);
		}
		g2d.drawImage(p.getImage(), p.x+d, p.y+d, null);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Consolas", Font.PLAIN, 32);
		g2d.setFont(font);
		g2d.setColor(Color.white);
		g2d.drawString("Score: "+score, 40, 120); 
	}

	public static void nextLevel() 
	{
		entities.clear();
		l.create();	
		p = l.getPlayer();
		l.addMonsters();
	}
}
