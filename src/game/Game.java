package game;

import game.Entities.Player;

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
import javax.swing.JPanel;

import Game.Frame;
import Game.Tile;



public class Game extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean[] mouseDown = new boolean[4];
	public static Image[] images = new Image[30];
	public static int cx, cy, sx, sy;
	public static Level l;
	public static Player p;
	
	public Game()
	{
		for(int x = 0; x < 30; x++)
		{
			if(getClass().getResource("/images/tiles/"+x+".png") != null) {
				images[x] = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/tiles/"+x+".png"));
				System.out.println("Trying to get image: /images/tiles/"+x+".png "+cy+" "+cx);
			}
		}
		setFocusable(true);
		l = new Level(0);
		p = l.getPlayer();
		
		addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent mEvt) {
				sx = mEvt.getX();
				sy = mEvt.getY();
				System.out.println("KEY PRESSED: ");
			}

		});
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Frame.maxX, Frame.maxY);
		Tile[][] tilelist = l.getTilesArray();
		for(int x = 0; x < l.sizeX; x++)
		{
			for(int y = 0; y < l.sizeY; y++)
			{
				
			}
		}
		for(Tile[] t: tilelist)
			for(Tile k : t) 
				if(k.x-cx > Tile.SIZE*-1 && k.x-cx < Frame.maxX()+Tile.SIZE && k.y-cy > Tile.SIZE*-1 && k.y-cy < Frame.maxY()+Tile.SIZE)
				{
					if(k.getType() != null)
						g2d.drawImage(k.getType(), k.x-cx, k.y-cy, null);
					if(k.getResource() != null)
						g2d.drawImage(k.getResource(), k.x-cx+Tile.SIZE/2, k.y-cy+Tile.SIZE/2, null);
					if(k.getUnit() != null)
						g2d.drawImage(k.unit.getImage(), k.x-cx+Tile.SIZE/4, k.y-cy+Tile.SIZE/4, null);
				}

		repaint();
	}
}
